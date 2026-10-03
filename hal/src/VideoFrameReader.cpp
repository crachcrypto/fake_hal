#include "VideoFrameReader.h"

#include <media/NdkMediaExtractor.h>
#include <media/NdkMediaCodec.h>
#include <media/NdkMediaFormat.h>
#include <media/NdkMediaError.h>

#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <poll.h>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <chrono>

#ifndef FAKE_HAL_TEST_BUILD
#include <sys/system_properties.h>
#endif

#define LOG_TAG "FakeHAL_VideoReader"
#include <log/log.h>
#include "FakeHALLog.h"


#define COLOR_FormatYUV420Planar      19
#define COLOR_FormatYUV420SemiPlanar  21
#define COLOR_FormatYUV420PackedSemiPlanar 39

namespace fake_hal {

// ─── Global stream server (shared by all VideoFrameReader instances) ────
static std::mutex              g_streamMutex;
static std::vector<uint8_t>    g_streamBuf;
static int                     g_streamWidth  = 0;
static int                     g_streamHeight = 0;
static bool                    g_streamReady  = false;
static std::atomic<bool>       g_streamRunning{false};
static std::atomic<bool>       g_streamActive{false};
static int                     g_serverFd = -1;
static std::thread             g_serverThread;

static bool g_recvAll(int fd, uint8_t* buf, size_t len) {
    size_t got = 0;
    while (got < len) {
        ssize_t n = ::recv(fd, buf + got, len - got, 0);
        if (n <= 0) return false;
        got += (size_t)n;
    }
    return true;
}

static void g_handleClient(int clientFd) {
    uint8_t header[12];
    uint32_t frameCount = 0;
    while (g_streamRunning.load()) {
        if (!g_recvAll(clientFd, header, 12)) break;
        if (header[0]!='F'||header[1]!='H'||header[2]!='A'||header[3]!='L') {
            FHAL_E("StreamServer: bad magic"); break;
        }
        uint32_t w = header[4]|(header[5]<<8)|(header[6]<<16)|(header[7]<<24);
        uint32_t h = header[8]|(header[9]<<8)|(header[10]<<16)|(header[11]<<24);
        if (w==0||h==0||w>7680||h>4320) { FHAL_E("StreamServer: bad dims %ux%u",w,h); break; }
        size_t payloadSize = (size_t)(w*h*3/2);
        std::vector<uint8_t> frameBuf(payloadSize);
        if (!g_recvAll(clientFd, frameBuf.data(), payloadSize)) break;
        {
            std::lock_guard<std::mutex> lk(g_streamMutex);
            g_streamBuf = std::move(frameBuf);
            g_streamWidth = (int)w; g_streamHeight = (int)h;
            g_streamReady = true;
        }
        if (++frameCount % 300 == 1)
            FHAL_I("StreamServer: frame #%u (%ux%u)", frameCount, w, h);
    }
}

static void g_serverLoop() {
    while (g_streamRunning.load()) {
        struct pollfd pfd{}; pfd.fd = g_serverFd; pfd.events = POLLIN;
        if (::poll(&pfd, 1, 500) <= 0) continue;
        if (!(pfd.revents & POLLIN)) continue;
        struct sockaddr_in ca{}; socklen_t cl = sizeof(ca);
        int cfd = ::accept(g_serverFd, (struct sockaddr*)&ca, &cl);
        if (cfd < 0) continue;
        int nd = 1; setsockopt(cfd, IPPROTO_TCP, TCP_NODELAY, &nd, sizeof(nd));
        char addr[INET_ADDRSTRLEN];
        inet_ntop(AF_INET, &ca.sin_addr, addr, sizeof(addr));
        FHAL_I("StreamServer: client %s:%d", addr, ntohs(ca.sin_port));
        g_streamActive.store(true);
        g_handleClient(cfd);
        g_streamActive.store(false);
        ::close(cfd);
        FHAL_I("StreamServer: client disconnected, fallback to MP4");
    }
}

void VideoFrameReader::startGlobalStreamServer(int port) {
    if (g_streamRunning.load()) return;
    g_serverFd = ::socket(AF_INET, SOCK_STREAM, 0);
    if (g_serverFd < 0) { FHAL_E("StreamServer: socket: %s", strerror(errno)); return; }
    int opt = 1; setsockopt(g_serverFd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));
    struct sockaddr_in a{}; a.sin_family=AF_INET; a.sin_addr.s_addr=INADDR_ANY; a.sin_port=htons((uint16_t)port);
    if (::bind(g_serverFd,(struct sockaddr*)&a,sizeof(a))<0) {
        FHAL_E("StreamServer: bind(%d): %s", port, strerror(errno));
        ::close(g_serverFd); g_serverFd=-1; return;
    }
    if (::listen(g_serverFd,1)<0) {
        FHAL_E("StreamServer: listen: %s", strerror(errno));
        ::close(g_serverFd); g_serverFd=-1; return;
    }
    g_streamRunning.store(true);
    g_serverThread = std::thread(g_serverLoop);
    FHAL_I("StreamServer: listening on port %d", port);
}

VideoFrameReader::VideoFrameReader(const std::string& filePath)
    : filePath_(filePath) {}

VideoFrameReader::~VideoFrameReader() {
    stopStreamServer();
    close();
}

bool VideoFrameReader::open(int targetWidth, int targetHeight) {
    targetWidth_  = targetWidth;
    targetHeight_ = targetHeight;


    int fd = ::open(filePath_.c_str(), O_RDONLY);
    if (fd < 0) {
        FHAL_E("video file not found: %s -- place your video there. Error: %s",
              filePath_.c_str(), strerror(errno));
        return false;
    }

    struct stat st;
    fstat(fd, &st);
    off64_t fileSize = st.st_size;

    extractor_ = AMediaExtractor_new();
    media_status_t status = AMediaExtractor_setDataSourceFd(
        extractor_, fd, 0, fileSize);
    ::close(fd);

    if (status != AMEDIA_OK) {
        FHAL_E("VideoFrameReader: setDataSourceFd failed: status=%d", status);
        AMediaExtractor_delete(extractor_);
        extractor_ = nullptr;
        return false;
    }

    if (!findVideoTrack()) {
        FHAL_E("VideoFrameReader: no video track found in %s", filePath_.c_str());
        return false;
    }

    AMediaExtractor_selectTrack(extractor_, videoTrackIndex_);


    const char* mime = nullptr;
    AMediaFormat_getString(format_, AMEDIAFORMAT_KEY_MIME, &mime);

    codec_ = AMediaCodec_createDecoderByType(mime);
    if (!codec_) {
        FHAL_E("VideoFrameReader: cannot create decoder for %s", mime);
        return false;
    }

    status = AMediaCodec_configure(codec_, format_, nullptr, nullptr, 0);
    if (status != AMEDIA_OK) {
        FHAL_E("VideoFrameReader: codec configure failed: status=%d", status);
        return false;
    }

    status = AMediaCodec_start(codec_);
    if (status != AMEDIA_OK) {
        FHAL_E("VideoFrameReader: codec start failed: status=%d", status);
        return false;
    }


    size_t nv21Size = (size_t)(width_ * height_ * 3 / 2);
    lastFrameBuf_.resize(nv21Size, 128);
    // Size convBuf for native video resolution (decoder output), not target size
    int32_t natW = 0, natH = 0;
    AMediaFormat_getInt32(format_, AMEDIAFORMAT_KEY_WIDTH, &natW);
    AMediaFormat_getInt32(format_, AMEDIAFORMAT_KEY_HEIGHT, &natH);
    if (natW <= 0) natW = width_;
    if (natH <= 0) natH = height_;
    size_t nativeNv21 = (size_t)(natW * natH * 3 / 2);
    convBuf_.resize(std::max(nativeNv21, nv21Size) * 2);

    isOpen_ = true;
    FHAL_I("VideoFrameReader: opened %s (%dx%d @ %d fps)",
          filePath_.c_str(), width_, height_, fps_);
    return true;
}

bool VideoFrameReader::findVideoTrack() {
    size_t numTracks = AMediaExtractor_getTrackCount(extractor_);
    for (size_t i = 0; i < numTracks; i++) {
        AMediaFormat* fmt = AMediaExtractor_getTrackFormat(extractor_, i);
        const char* mime = nullptr;
        AMediaFormat_getString(fmt, AMEDIAFORMAT_KEY_MIME, &mime);
        if (mime && strncmp(mime, "video/", 6) == 0) {
            videoTrackIndex_ = (int)i;
            format_ = fmt;


            int32_t w = 0, h = 0;
            AMediaFormat_getInt32(fmt, AMEDIAFORMAT_KEY_WIDTH, &w);
            AMediaFormat_getInt32(fmt, AMEDIAFORMAT_KEY_HEIGHT, &h);

            if (targetWidth_ > 0 && targetHeight_ > 0) {
                width_  = targetWidth_;
                height_ = targetHeight_;
            } else {
                width_  = w;
                height_ = h;
            }


            float fpsF = 30.0f;
            AMediaFormat_getFloat(fmt, AMEDIAFORMAT_KEY_FRAME_RATE, &fpsF);
            fps_ = (int)fpsF;

            FHAL_I("VideoFrameReader: found video track %zu: %s %dx%d @ %.1f fps",
                  i, mime, w, h, fpsF);
            return true;
        }
        AMediaFormat_delete(fmt);
    }
    return false;
}

void VideoFrameReader::close() {
    isOpen_ = false;
    stopStreamServer();
    if (codec_) {
        AMediaCodec_stop(codec_);
        AMediaCodec_delete(codec_);
        codec_ = nullptr;
    }
    if (extractor_) {
        AMediaExtractor_delete(extractor_);
        extractor_ = nullptr;
    }
    if (format_) {


        format_ = nullptr;
    }
}


void VideoFrameReader::i420ToNV21(const uint8_t* src, uint8_t* dst, int w, int h) {
    int ySize  = w * h;
    int uvSize = w * h / 4;

    const uint8_t* Y = src;
    const uint8_t* U = src + ySize;
    const uint8_t* V = src + ySize + uvSize;


    memcpy(dst, Y, ySize);


    uint8_t* dstUV = dst + ySize;
    for (int i = 0; i < uvSize; i++) {
        dstUV[i * 2 + 0] = V[i];
        dstUV[i * 2 + 1] = U[i];
    }
}


void VideoFrameReader::nv12ToNV21(const uint8_t* src, uint8_t* dst, int w, int h) {
    int ySize = w * h;
    memcpy(dst, src, ySize);

    const uint8_t* srcUV = src + ySize;
    uint8_t*       dstUV = dst + ySize;
    int uvSize = ySize / 2;

    for (int i = 0; i < uvSize; i += 2) {
        dstUV[i + 0] = srcUV[i + 1];
        dstUV[i + 1] = srcUV[i + 0];
    }
}

// Rotate an NV21 frame 90° counter-clockwise. Output dimensions are (h x w);
// caller is responsible for sizing dst (= w*h*3/2 bytes, dimensions just
// transpose). Used when the source video orientation doesn't match the
// requested camera stream orientation (e.g. portrait selfie 720x1280 vs
// landscape stream 640x480).
void VideoFrameReader::rotateNV21_90CCW(const uint8_t* src, int srcW, int srcH,
                                        uint8_t* dst) {
    const int dstW = srcH;
    const int dstH = srcW;

    // Y plane: dst(c,r) = src(srcW-1-r, c)
    for (int r = 0; r < dstH; ++r) {
        const int srcCol = srcW - 1 - r;
        const uint8_t* srcCol0 = src + srcCol;
        uint8_t* dstRow = dst + (size_t)r * dstW;
        for (int c = 0; c < dstW; ++c) {
            dstRow[c] = srcCol0[c * srcW];
        }
    }

    // UV plane (interleaved V,U) at half resolution. Treat each (V,U) pair
    // as one chroma sample and rotate the chroma grid the same way.
    const int srcUVW = srcW / 2;
    const int dstUVW = dstW / 2;
    const int dstUVH = dstH / 2;
    const uint8_t* srcUV = src + (size_t)srcW * srcH;
    uint8_t*       dstUV = dst + (size_t)dstW * dstH;
    for (int r = 0; r < dstUVH; ++r) {
        const int srcCol = srcUVW - 1 - r;
        for (int c = 0; c < dstUVW; ++c) {
            const int srcRow = c;
            const uint8_t* p = srcUV + ((size_t)srcRow * srcUVW + srcCol) * 2;
            uint8_t* q = dstUV + ((size_t)r * dstUVW + c) * 2;
            q[0] = p[0]; // V
            q[1] = p[1]; // U
        }
    }
}


// Two strategies, picked by scale:
//   * Heavy upscale (dst >= 2x src in both dims, common case 640x480 -> 4080x3072
//     when the producer asks for full sensor preview): nearest-neighbour with
//     per-source-row template + row replication via memcpy. At 6x6 upscale a
//     bilinear filter is visually indistinguishable from nearest-neighbour
//     (each source pixel covers ~36 destination pixels), but bilinear with
//     float-math is ~5x slower. Drops a 4080x3072 resize from ~120 ms to ~20 ms.
//   * Otherwise (small or non-trivial scale, e.g. JPEG capture intermediate
//     paths): keep the original bilinear fallback for quality.
//
// Aspect-ratio handling: when src and dst aspect ratios differ (e.g. our 4:3
// source video being scaled into a 16:9 sumsub stream) a uniform scale would
// stretch the content, distorting documents/faces. We instead center-crop the
// source to the destination aspect ratio first -- this is what real camera
// ISPs do when the producer requests an output with a non-native aspect (the
// sensor area outside the crop window is just discarded).
//
// Both branches use Q8/Q16 fixed-point indexing to avoid floats in the hot
// loop.
void VideoFrameReader::resizeNV21(const uint8_t* src, int srcW, int srcH,
                                   uint8_t* dst, int dstW, int dstH)
{
    // Center-crop the source to the destination aspect ratio so resize is a
    // pure uniform scale (no stretching). cropOriginX/Y are in source pixels;
    // cropW/H cover the visible portion of the source. Even values keep the
    // chroma subsampled grid aligned.
    int cropW = srcW;
    int cropH = srcH;
    int cropX = 0;
    int cropY = 0;
    {
        const int64_t srcAR_q16 = ((int64_t)srcW << 16) / std::max(1, srcH);
        const int64_t dstAR_q16 = ((int64_t)dstW << 16) / std::max(1, dstH);
        if (srcAR_q16 > dstAR_q16) {
            // Source is wider than destination: crop sides.
            cropW = (int)(((int64_t)srcH * dstW) / dstH);
            if (cropW > srcW) cropW = srcW;
            cropW &= ~1;
            cropX = ((srcW - cropW) / 2) & ~1;
        } else if (srcAR_q16 < dstAR_q16) {
            // Source is taller than destination: crop top/bottom.
            cropH = (int)(((int64_t)srcW * dstH) / dstW);
            if (cropH > srcH) cropH = srcH;
            cropH &= ~1;
            cropY = ((srcH - cropH) / 2) & ~1;
        }
    }

    const bool heavyUpscale = (dstW >= 4 * cropW) && (dstH >= 4 * cropH);

    // ---- Y plane ----
    if (heavyUpscale) {
        std::vector<int32_t> xMap(dstW);
        const int64_t scaleX_q16 = ((int64_t)cropW << 16) / dstW;
        for (int x = 0; x < dstW; ++x) {
            int sx = cropX + (int)((scaleX_q16 * x) >> 16);
            if (sx >= cropX + cropW) sx = cropX + cropW - 1;
            xMap[x] = sx;
        }

        std::vector<uint8_t> rowTmpl(dstW);
        const int64_t scaleY_q16 = ((int64_t)cropH << 16) / dstH;
        int lastSrcY = -1;
        for (int y = 0; y < dstH; ++y) {
            int sy = cropY + (int)((scaleY_q16 * y) >> 16);
            if (sy >= cropY + cropH) sy = cropY + cropH - 1;
            if (sy != lastSrcY) {
                const uint8_t* srcRow = src + sy * srcW;
                for (int x = 0; x < dstW; ++x) rowTmpl[x] = srcRow[xMap[x]];
                lastSrcY = sy;
            }
            memcpy(dst + (size_t)y * dstW, rowTmpl.data(), (size_t)dstW);
        }
    } else {
        const int64_t scaleX_q16 = ((int64_t)cropW << 16) / dstW;
        const int64_t scaleY_q16 = ((int64_t)cropH << 16) / dstH;
        for (int y = 0; y < dstH; ++y) {
            int srcY_q16 = (int)(scaleY_q16 * y);
            int y0 = cropY + (srcY_q16 >> 16);
            int fy = (srcY_q16 >> 8) & 0xFF;
            int y1 = std::min(y0 + 1, cropY + cropH - 1);
            const uint8_t* row0 = src + y0 * srcW;
            const uint8_t* row1 = src + y1 * srcW;
            uint8_t* outRow = dst + (size_t)y * dstW;
            for (int x = 0; x < dstW; ++x) {
                int srcX_q16 = (int)(scaleX_q16 * x);
                int x0 = cropX + (srcX_q16 >> 16);
                int fx = (srcX_q16 >> 8) & 0xFF;
                int x1 = std::min(x0 + 1, cropX + cropW - 1);
                int a = row0[x0], b = row0[x1], c = row1[x0], d = row1[x1];
                int top = a * (256 - fx) + b * fx;
                int bot = c * (256 - fx) + d * fx;
                int v = (top * (256 - fy) + bot * fy) >> 16;
                if (v < 0) v = 0; else if (v > 255) v = 255;
                outRow[x] = (uint8_t)v;
            }
        }
    }

    // ---- UV plane (interleaved V,U) ----
    const int srcUVW = srcW / 2;
    const int dstUVW = dstW / 2, dstUVH = dstH / 2;
    const int cropUVW = cropW / 2, cropUVH = cropH / 2;
    const int cropUVX = cropX / 2, cropUVY = cropY / 2;
    const uint8_t* srcUV = src + (size_t)srcW * srcH;
    uint8_t*       dstUV = dst + (size_t)dstW * dstH;

    if (heavyUpscale) {
        std::vector<int32_t> xMapUV(dstUVW);
        const int64_t scaleUVX_q16 = ((int64_t)cropUVW << 16) / dstUVW;
        for (int x = 0; x < dstUVW; ++x) {
            int sx = cropUVX + (int)((scaleUVX_q16 * x) >> 16);
            if (sx >= cropUVX + cropUVW) sx = cropUVX + cropUVW - 1;
            xMapUV[x] = sx;
        }

        std::vector<uint16_t> rowTmplUV(dstUVW);
        const int64_t scaleUVY_q16 = ((int64_t)cropUVH << 16) / dstUVH;
        int lastSrcY = -1;
        for (int y = 0; y < dstUVH; ++y) {
            int sy = cropUVY + (int)((scaleUVY_q16 * y) >> 16);
            if (sy >= cropUVY + cropUVH) sy = cropUVY + cropUVH - 1;
            if (sy != lastSrcY) {
                const uint16_t* srcRowUV =
                    reinterpret_cast<const uint16_t*>(srcUV) + sy * srcUVW;
                for (int x = 0; x < dstUVW; ++x) rowTmplUV[x] = srcRowUV[xMapUV[x]];
                lastSrcY = sy;
            }
            memcpy(dstUV + (size_t)y * dstUVW * 2,
                   rowTmplUV.data(),
                   (size_t)dstUVW * 2);
        }
    } else {
        const int64_t scaleUVX_q16 = ((int64_t)cropUVW << 16) / dstUVW;
        const int64_t scaleUVY_q16 = ((int64_t)cropUVH << 16) / dstUVH;
        for (int y = 0; y < dstUVH; ++y) {
            int srcY_q16 = (int)(scaleUVY_q16 * y);
            int y0 = cropUVY + (srcY_q16 >> 16);
            int fy = (srcY_q16 >> 8) & 0xFF;
            int y1 = std::min(y0 + 1, cropUVY + cropUVH - 1);
            for (int x = 0; x < dstUVW; ++x) {
                int srcX_q16 = (int)(scaleUVX_q16 * x);
                int x0 = cropUVX + (srcX_q16 >> 16);
                int fx = (srcX_q16 >> 8) & 0xFF;
                int x1 = std::min(x0 + 1, cropUVX + cropUVW - 1);
                for (int c = 0; c < 2; ++c) {
                    int a = srcUV[(y0*srcUVW + x0)*2 + c];
                    int b = srcUV[(y0*srcUVW + x1)*2 + c];
                    int cc = srcUV[(y1*srcUVW + x0)*2 + c];
                    int d = srcUV[(y1*srcUVW + x1)*2 + c];
                    int top = a * (256 - fx) + b * fx;
                    int bot = cc * (256 - fx) + d * fx;
                    int v = (top * (256 - fy) + bot * fy) >> 16;
                    if (v < 0) v = 0; else if (v > 255) v = 255;
                    dstUV[(y*dstUVW + x)*2 + c] = (uint8_t)v;
                }
            }
        }
    }
}

bool VideoFrameReader::nextFrame(uint8_t* outBuffer) {
    if (!isOpen_) return false;

    lastFrameFromStream_ = false;

    if (streamEnabled_) {
        std::lock_guard<std::mutex> lk(g_streamMutex);
        bool active = g_streamActive.load();
        bool hasBuf = !g_streamBuf.empty();
        static int dbgCnt = 0;
        if (++dbgCnt % 30 == 1) {
            FHAL_I("DBG nextFrame: active=%d hasBuf=%d bufSz=%zu gW=%d gH=%d myW=%d myH=%d streamEnabled=%d",
                   (int)active, (int)hasBuf, g_streamBuf.size(),
                   g_streamWidth, g_streamHeight, width_, height_, (int)streamEnabled_);
        }
        if ((active || hasBuf) && hasBuf) {
            if (g_streamWidth == width_ && g_streamHeight == height_) {
                memcpy(outBuffer, g_streamBuf.data(), width_ * height_ * 3 / 2);
            } else {
                resizeNV21(g_streamBuf.data(), g_streamWidth, g_streamHeight,
                           outBuffer, width_, height_);
            }
            {
                std::lock_guard<std::mutex> lk2(lastFrameMutex_);
                memcpy(lastFrameBuf_.data(), outBuffer, width_ * height_ * 3 / 2);
            }
            lastFrameFromStream_ = true;
            if (dbgCnt % 30 == 1) {
                FHAL_I("DBG nextFrame: -> STREAM path (active=%d)", (int)active);
            }
            return true;
        }
        if (dbgCnt % 30 == 1) {
            FHAL_I("DBG nextFrame: -> MP4 fallback (active=%d hasBuf=%d)", (int)active, (int)hasBuf);
        }
    }

    if (!codec_ || !extractor_) return false;

    static constexpr int64_t TIMEOUT_US = 50'000;
    using namespace std::chrono;

    bool gotFrame = false;
    int  retries  = 0;

    while (!gotFrame && retries < 10) {

        ssize_t inBufIdx = AMediaCodec_dequeueInputBuffer(codec_, TIMEOUT_US);
        if (inBufIdx >= 0) {
            size_t bufSize = 0;
            uint8_t* buf = AMediaCodec_getInputBuffer(codec_, inBufIdx, &bufSize);

            ssize_t sampleSize = AMediaExtractor_readSampleData(extractor_, buf, bufSize);
            int64_t pts = AMediaExtractor_getSampleTime(extractor_);

            if (sampleSize < 0) {

                FHAL_D("VideoFrameReader: end of file, looping");
                AMediaCodec_queueInputBuffer(codec_, inBufIdx, 0, 0, 0,
                                             AMEDIACODEC_BUFFER_FLAG_END_OF_STREAM);
                AMediaExtractor_seekTo(extractor_, 0, AMEDIAEXTRACTOR_SEEK_CLOSEST_SYNC);
                AMediaCodec_flush(codec_);
                AMediaCodec_start(codec_);
                retries++;
                continue;
            }

            AMediaCodec_queueInputBuffer(codec_, inBufIdx, 0, sampleSize, pts, 0);
            AMediaExtractor_advance(extractor_);
        }


        AMediaCodecBufferInfo info;
        ssize_t outBufIdx = AMediaCodec_dequeueOutputBuffer(codec_, &info, TIMEOUT_US);

        if (outBufIdx >= 0) {
            size_t outSize = 0;
            uint8_t* outBuf = AMediaCodec_getOutputBuffer(codec_, outBufIdx, &outSize);

            if (outBuf && !(info.flags & AMEDIACODEC_BUFFER_FLAG_END_OF_STREAM)) {

                AMediaFormat* outFmt = AMediaCodec_getOutputFormat(codec_);
                int32_t colorFmt = COLOR_FormatYUV420SemiPlanar;
                AMediaFormat_getInt32(outFmt, AMEDIAFORMAT_KEY_COLOR_FORMAT, &colorFmt);
                int32_t decW = width_, decH = height_;
                AMediaFormat_getInt32(outFmt, AMEDIAFORMAT_KEY_WIDTH, &decW);
                AMediaFormat_getInt32(outFmt, AMEDIAFORMAT_KEY_HEIGHT, &decH);
                AMediaFormat_delete(outFmt);


                uint8_t* nv21Src = convBuf_.data();
                if (colorFmt == COLOR_FormatYUV420Planar) {
                    i420ToNV21(outBuf, nv21Src, decW, decH);
                } else {

                    nv12ToNV21(outBuf, nv21Src, decW, decH);
                }

                // Auto-rotate when source orientation does not match the
                // destination orientation. The Camera2 framework rotates the
                // sensor buffer by ANDROID_SENSOR_ORIENTATION (=90 here) to
                // produce upright preview, which assumes a landscape sensor
                // raster. A user-supplied portrait selfie video (e.g. 720x1280
                // captured with a phone in portrait mode) would otherwise
                // either be center-cropped to a narrow horizontal band of the
                // face (after my aspect-correct resize) or left as a small
                // 4:3 letterboxed rectangle in the middle of the screen
                // (without aspect handling). Pre-rotating 90° CCW so the
                // source becomes landscape-with-head-on-left lets the
                // framework's CW90 rotation restore an upright display while
                // preserving the full content via uniform scaling.
                bool srcPortrait = decH > decW;
                bool dstPortrait = height_ > width_;
                if (srcPortrait != dstPortrait) {
                    rotatedBuf_.resize((size_t)decW * decH * 3 / 2);
                    rotateNV21_90CCW(nv21Src, decW, decH, rotatedBuf_.data());
                    nv21Src = rotatedBuf_.data();
                    std::swap(decW, decH);
                }

                if (decW != width_ || decH != height_) {
                    resizeNV21(nv21Src, decW, decH, outBuffer, width_, height_);
                } else {
                    memcpy(outBuffer, nv21Src, width_ * height_ * 3 / 2);
                }


                {
                    std::lock_guard<std::mutex> lk(lastFrameMutex_);
                    memcpy(lastFrameBuf_.data(), outBuffer, width_ * height_ * 3 / 2);
                }

                gotFrame = true;
            }

            AMediaCodec_releaseOutputBuffer(codec_, outBufIdx, false);

        } else if (outBufIdx == AMEDIACODEC_INFO_OUTPUT_FORMAT_CHANGED) {
            FHAL_D("VideoFrameReader: output format changed");
        }

        retries++;
    }

    if (!gotFrame) {

        return lastFrame(outBuffer);
    }

    return gotFrame;
}

bool VideoFrameReader::lastFrame(uint8_t* outBuffer) {
    std::lock_guard<std::mutex> lk(lastFrameMutex_);
    if (lastFrameBuf_.empty()) return false;
    memcpy(outBuffer, lastFrameBuf_.data(), lastFrameBuf_.size());
    return true;
}

void VideoFrameReader::startStreamServer(int) {}
void VideoFrameReader::stopStreamServer() {}
void VideoFrameReader::streamServerLoop() {}
void VideoFrameReader::handleClient(int) {}

}

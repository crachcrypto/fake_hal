#pragma once

#include <media/NdkMediaExtractor.h>
#include <media/NdkMediaCodec.h>
#include <media/NdkMediaFormat.h>

#include <cstdint>
#include <string>
#include <vector>
#include <memory>
#include <mutex>
#include <thread>
#include <atomic>

namespace fake_hal {


class VideoFrameReader {
public:
    explicit VideoFrameReader(const std::string& filePath);
    ~VideoFrameReader();

    // Start the global TCP stream server (call once from main)
    static void startGlobalStreamServer(int port);


    bool open(int targetWidth = 0, int targetHeight = 0);
    void close();

    bool isOpen() const { return isOpen_; }
    int width() const { return width_; }
    int height() const { return height_; }

    int getWidth()  const { return width_; }
    int getHeight() const { return height_; }
    int getFps()    const { return fps_; }


    bool nextFrame(uint8_t* outBuffer);

    bool lastFrame(uint8_t* outBuffer);

    bool isStreamActive() const { return streamActive_.load(); }

    void setStreamEnabled(bool enabled) { streamEnabled_ = enabled; }

    bool lastFrameFromStream() const { return lastFrameFromStream_; }

private:
    std::string filePath_;
    int targetWidth_ = 0, targetHeight_ = 0;
    int width_ = 0, height_ = 0;
    int fps_ = 30;

    AMediaExtractor* extractor_ = nullptr;
    AMediaCodec*     codec_     = nullptr;
    AMediaFormat*    format_    = nullptr;

    bool isOpen_ = false;
    bool streamEnabled_ = true;
    bool lastFrameFromStream_ = false;


    std::vector<uint8_t> lastFrameBuf_;
    std::mutex lastFrameMutex_;


    std::vector<uint8_t> convBuf_;
    std::vector<uint8_t> rotatedBuf_;

    int videoTrackIndex_ = -1;

    bool findVideoTrack();
    bool seekToStart();


    void convertToNV21(const uint8_t* src, int srcFormat, uint8_t* dst, int w, int h);


    static void resizeNV21(
        const uint8_t* src, int srcW, int srcH,
        uint8_t* dst, int dstW, int dstH
    );


    static void i420ToNV21(const uint8_t* src, uint8_t* dst, int w, int h);

    static void nv12ToNV21(const uint8_t* src, uint8_t* dst, int w, int h);

    static void rotateNV21_90CCW(const uint8_t* src, int srcW, int srcH,
                                 uint8_t* dst);

    // --- Live TCP stream receiver ---
    // Wire protocol per frame: "FHAL" (4B) + width LE32 + height LE32 + NV21 payload
    void startStreamServer(int port);
    void stopStreamServer();
    void streamServerLoop();
    void handleClient(int clientFd);

    int serverFd_ = -1;
    std::thread serverThread_;
    std::atomic<bool> streamRunning_{false};
    std::atomic<bool> streamActive_{false};

    std::vector<uint8_t> streamBuf_;
    int streamWidth_ = 0, streamHeight_ = 0;
    std::mutex streamMutex_;
    bool streamFrameReady_ = false;
};

}

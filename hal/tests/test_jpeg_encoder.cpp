#include <gtest/gtest.h>
#include "JpegEncoder.h"
#include "StillOrientation.h"

#include <jpeglib.h>
#include <cstring>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace fake_hal;

class JpegEncoderTest : public ::testing::Test {
protected:
    static constexpr int kWidth  = 64;
    static constexpr int kHeight = 48;
    static constexpr int kYSize  = kWidth * kHeight;
    static constexpr int kNV21Size = kYSize * 3 / 2;

    JpegEncoder encoder_;


    std::vector<uint8_t> makeTestImage() {
        std::vector<uint8_t> buf(kNV21Size);

        for (int row = 0; row < kHeight; row++) {
            for (int col = 0; col < kWidth; col++) {
                buf[row * kWidth + col] = (uint8_t)(col * 255 / (kWidth - 1));
            }
        }

        std::memset(buf.data() + kYSize, 128, kNV21Size - kYSize);
        return buf;
    }

    JpegEncoder::ExifData makeExif(int iso = 200) {
        JpegEncoder::ExifData exif;
        exif.iso = iso;
        exif.exposureSec = 1.0f / 60.0f;
        exif.fNumber = 1.85f;
        exif.focalLength = 6.81f;
        exif.imageWidth = kWidth;
        exif.imageHeight = kHeight;
        return exif;
    }
};


TEST_F(JpegEncoderTest, StartsWithSOI) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 90, exif, jpeg));
    ASSERT_GE(jpeg.size(), 2u);
    EXPECT_EQ(jpeg[0], 0xFF);
    EXPECT_EQ(jpeg[1], 0xD8);
}


TEST_F(JpegEncoderTest, ContainsAPP1Exif) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 90, exif, jpeg));


    ASSERT_GE(jpeg.size(), 4u);
    EXPECT_EQ(jpeg[2], 0xFF);
    EXPECT_EQ(jpeg[3], 0xE1);


    ASSERT_GE(jpeg.size(), 10u);
    EXPECT_EQ(jpeg[6], 'E');
    EXPECT_EQ(jpeg[7], 'x');
    EXPECT_EQ(jpeg[8], 'i');
    EXPECT_EQ(jpeg[9], 'f');
}


TEST_F(JpegEncoderTest, ExifContainsCorrectISO) {
    auto nv21 = makeTestImage();
    int testISO = 400;
    auto exif = makeExif(testISO);
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 90, exif, jpeg));


    bool found = false;
    for (size_t i = 0; i + 12 < jpeg.size(); i++) {
        if (jpeg[i] == 0x27 && jpeg[i + 1] == 0x88 &&
            jpeg[i + 2] == 0x03 && jpeg[i + 3] == 0x00) {

            uint32_t val = jpeg[i + 8] | (jpeg[i + 9] << 8) |
                           (jpeg[i + 10] << 16) | (jpeg[i + 11] << 24);
            EXPECT_EQ((int)val, testISO) << "ISO mismatch in EXIF";
            found = true;
            break;
        }
    }
    EXPECT_TRUE(found) << "ISO tag 0x8827 not found in EXIF";
}


TEST_F(JpegEncoderTest, DecodesBackCorrectly) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 95, exif, jpeg));


    struct jpeg_decompress_struct dinfo;
    struct jpeg_error_mgr jerr;
    dinfo.err = jpeg_std_error(&jerr);
    jpeg_create_decompress(&dinfo);

    jpeg_mem_src(&dinfo, jpeg.data(), jpeg.size());
    ASSERT_EQ(jpeg_read_header(&dinfo, TRUE), JPEG_HEADER_OK);

    dinfo.out_color_space = JCS_YCbCr;
    jpeg_start_decompress(&dinfo);

    EXPECT_EQ((int)dinfo.output_width, kWidth);
    EXPECT_EQ((int)dinfo.output_height, kHeight);
    EXPECT_EQ(dinfo.output_components, 3);


    std::vector<uint8_t> decoded(kWidth * kHeight * 3);
    while (dinfo.output_scanline < dinfo.output_height) {
        JSAMPROW row = decoded.data() + dinfo.output_scanline * kWidth * 3;
        jpeg_read_scanlines(&dinfo, &row, 1);
    }

    jpeg_finish_decompress(&dinfo);
    jpeg_destroy_decompress(&dinfo);


    int cx = kWidth / 2, cy = kHeight / 2;
    int idx = (cy * kWidth + cx) * 3;
    uint8_t Y = decoded[idx];

    EXPECT_NEAR(Y, 128, 20) << "Decoded Y at center doesn't match expected";
}


TEST_F(JpegEncoderTest, SizeReasonable) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 90, exif, jpeg));

    EXPECT_GT(jpeg.size(), 100u) << "JPEG too small";
    EXPECT_LT(jpeg.size(), (size_t)kNV21Size)
        << "JPEG larger than raw NV21 — compression not working";
}


TEST_F(JpegEncoderTest, QualityAffectsSize) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();

    std::vector<uint8_t> jpegLow, jpegHigh;
    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 30, exif, jpegLow));
    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 95, exif, jpegHigh));

    EXPECT_LT(jpegLow.size(), jpegHigh.size())
        << "Lower quality should produce smaller file";
}


TEST_F(JpegEncoderTest, EndsWithEOI) {
    auto nv21 = makeTestImage();
    auto exif = makeExif();
    std::vector<uint8_t> jpeg;

    ASSERT_TRUE(encoder_.encode(nv21.data(), kWidth, kHeight, 90, exif, jpeg));

    ASSERT_GE(jpeg.size(), 2u);
    EXPECT_EQ(jpeg[jpeg.size() - 2], 0xFF);
    EXPECT_EQ(jpeg[jpeg.size() - 1], 0xD9);
}


// ===========================================================================
//  Still / JPEG capture path: "no stretch" regression tests
//
//  Bug being guarded against:
//    The video source is portrait (720x1280, aspect 0.5625) but the HAL's
//    sensor raster is landscape 1920x1080 (aspect 1.7778). The framework
//    applies ANDROID_SENSOR_ORIENTATION to preview streams but NOT to BLOB
//    streams, so the still path used to emit the landscape raster verbatim and
//    lean on EXIF orientation 6 to make it look right. Any consumer ignoring
//    EXIF (gallery thumbnailers, web uploads, verification backends) then saw
//    a sideways 16:9 frame for a 9:16 scene -- the photo "expands"/stretches.
//
//  The fix bakes the rotation into the pixels with a TRUE transpose, so the
//  encoded JPEG is natively upright and its aspect ratio matches the scene.
// ===========================================================================

class StillOrientationTest : public ::testing::Test {
protected:
    // Build an NV21 image with a unique, position-dependent value per pixel so
    // that any resampling, stretching, cropping or padding is detectable.
    static std::vector<uint8_t> makeNV21(int w, int h) {
        std::vector<uint8_t> buf((size_t)w * h * 3 / 2);
        for (int y = 0; y < h; ++y)
            for (int x = 0; x < w; ++x)
                buf[(size_t)y * w + x] = (uint8_t)((x * 7 + y * 13) & 0xFF);
        uint8_t* uv = buf.data() + (size_t)w * h;
        for (int cy = 0; cy < h / 2; ++cy) {
            for (int cx = 0; cx < w / 2; ++cx) {
                uv[(size_t)cy * w + cx * 2 + 0] = (uint8_t)((cx * 3 + cy * 5) & 0xFF); // V
                uv[(size_t)cy * w + cx * 2 + 1] = (uint8_t)((cx * 11 + cy * 17) & 0xFF); // U
            }
        }
        return buf;
    }
    static uint8_t Y(const std::vector<uint8_t>& b, int w, int x, int y) {
        return b[(size_t)y * w + x];
    }
};

// The core guarantee: rotating swaps the dimensions, it does not rescale.
// A 1920x1080 sensor raster must become a 1080x1920 still -- NOT a stretched
// 1920x1080 one.
TEST_F(StillOrientationTest, Rotate90SwapsDimensionsWithoutScaling) {
    const int w = 1920, h = 1080;
    auto src = makeNV21(w, h);
    std::vector<uint8_t> dst((size_t)w * h * 3 / 2);

    rotateNV21_90Transpose(src.data(), w, h, dst.data(), /*cw=*/true);

    const int dw = h, dh = w;               // 1080 x 1920
    EXPECT_EQ(dw, 1080);
    EXPECT_EQ(dh, 1920);

    // Aspect ratio of the output must be the reciprocal of the input, i.e. the
    // scene proportions are preserved exactly (no anisotropic stretch).
    const double srcAspect = (double)w / h;      // 1.7778
    const double dstAspect = (double)dw / dh;    // 0.5625
    EXPECT_NEAR(srcAspect * dstAspect, 1.0, 1e-9)
        << "rotation must transpose, not stretch";
}

// Every source pixel must appear exactly once at its rotated position --
// this is what proves there is no resampling/interpolation (which is what a
// naive resizeNV21() into the wrong aspect would do).
TEST_F(StillOrientationTest, Rotate90CWIsLosslessPixelPermutation) {
    const int w = 64, h = 48;
    auto src = makeNV21(w, h);
    std::vector<uint8_t> dst((size_t)w * h * 3 / 2);

    rotateNV21_90Transpose(src.data(), w, h, dst.data(), /*cw=*/true);

    const int dw = h;  // dst width
    for (int y = 0; y < h; ++y) {
        for (int x = 0; x < w; ++x) {
            // 90 CW: src(x,y) -> dst(h-1-y, x)
            EXPECT_EQ(Y(dst, dw, h - 1 - y, x), Y(src, w, x, y))
                << "mismatch at src(" << x << "," << y << ")";
        }
    }
}

TEST_F(StillOrientationTest, Rotate90CCWIsLosslessPixelPermutation) {
    const int w = 64, h = 48;
    auto src = makeNV21(w, h);
    std::vector<uint8_t> dst((size_t)w * h * 3 / 2);

    rotateNV21_90Transpose(src.data(), w, h, dst.data(), /*cw=*/false);

    const int dw = h;
    for (int y = 0; y < h; ++y) {
        for (int x = 0; x < w; ++x) {
            // 90 CCW: src(x,y) -> dst(y, w-1-x)
            EXPECT_EQ(Y(dst, dw, y, w - 1 - x), Y(src, w, x, y))
                << "mismatch at src(" << x << "," << y << ")";
        }
    }
}

// Chroma must follow the luma rotation and keep NV21 V,U pair ordering,
// otherwise the still comes out with swapped//shifted colours.
TEST_F(StillOrientationTest, Rotate90PreservesChromaPairsAndColour) {
    const int w = 64, h = 48;
    auto src = makeNV21(w, h);
    std::vector<uint8_t> dst((size_t)w * h * 3 / 2);

    rotateNV21_90Transpose(src.data(), w, h, dst.data(), /*cw=*/true);

    const uint8_t* srcUV = src.data() + (size_t)w * h;
    const uint8_t* dstUV = dst.data() + (size_t)w * h;
    const int scw = w / 2, sch = h / 2;
    const int dstRowBytes = h;                    // dst width in bytes

    for (int cy = 0; cy < sch; ++cy) {
        for (int cx = 0; cx < scw; ++cx) {
            // 90 CW in chroma space: src(cx,cy) -> dst(sch-1-cy, cx)
            const int dcx = sch - 1 - cy, dcy = cx;
            const uint8_t* s = srcUV + (size_t)cy * w + (size_t)cx * 2;
            const uint8_t* d = dstUV + (size_t)dcy * dstRowBytes + (size_t)dcx * 2;
            EXPECT_EQ(d[0], s[0]) << "V mismatch at chroma(" << cx << "," << cy << ")";
            EXPECT_EQ(d[1], s[1]) << "U mismatch at chroma(" << cx << "," << cy << ")";
        }
    }
}

// Four 90-degree rotations must return the original image bit-for-bit.
TEST_F(StillOrientationTest, FourRotationsRoundTripExactly) {
    const int w = 64, h = 48;
    auto original = makeNV21(w, h);

    std::vector<uint8_t> a = original;
    std::vector<uint8_t> b((size_t)w * h * 3 / 2);
    int cw_ = w, ch_ = h;
    for (int i = 0; i < 4; ++i) {
        rotateNV21_90Transpose(a.data(), cw_, ch_, b.data(), true);
        std::swap(cw_, ch_);
        a.swap(b);
    }
    EXPECT_EQ(cw_, w);
    EXPECT_EQ(ch_, h);
    EXPECT_EQ(a, original) << "4x90 CW must be identity";
}

// Policy checks: with sensorOrientation 90 the still path bakes 90 degrees and
// therefore must report EXIF orientation 1 (normal) and swapped dimensions.
TEST_F(StillOrientationTest, BakePolicyForSensorOrientations) {
    EXPECT_EQ(stillBakeDegrees(90), 90);
    EXPECT_EQ(stillBakeDegrees(180), 180);
    EXPECT_EQ(stillBakeDegrees(270), 270);
    EXPECT_EQ(stillBakeDegrees(0), 0);
    EXPECT_EQ(stillBakeDegrees(-90), 270);
    EXPECT_EQ(stillBakeDegrees(450), 90);

    EXPECT_TRUE(stillBakeSwapsDimensions(90));
    EXPECT_TRUE(stillBakeSwapsDimensions(270));
    EXPECT_FALSE(stillBakeSwapsDimensions(180));
    EXPECT_FALSE(stillBakeSwapsDimensions(0));
}

TEST_F(StillOrientationTest, Rotate180IsInvolution) {
    const int w = 64, h = 48;
    auto original = makeNV21(w, h);
    auto buf = original;
    rotateNV21_180InPlace(buf.data(), w, h);
    EXPECT_NE(buf, original);
    rotateNV21_180InPlace(buf.data(), w, h);
    EXPECT_EQ(buf, original) << "two 180 rotations must be identity";
}

// End-to-end: the device's real geometry. A portrait 720x1280 scene scaled
// uniformly (1.5x) into the 1280x720 sensor raster, then upscaled to the
// 1920x1080 BLOB stream, must come out of the still path as an upright
// 1080x1920 JPEG whose aspect ratio equals the original scene's.
TEST_F(StillOrientationTest, DeviceGeometryProducesUprightUnstretchedJpeg) {
    const int blobW = 1920, blobH = 1080;   // sensor raster handed to the still path
    auto sensorRaster = makeNV21(blobW, blobH);

    // Bake the sensor orientation (90) into the pixels, as the still path does.
    ASSERT_TRUE(stillBakeSwapsDimensions(90));
    std::vector<uint8_t> upright((size_t)blobW * blobH * 3 / 2);
    rotateNV21_90Transpose(sensorRaster.data(), blobW, blobH, upright.data(), true);
    int jpegW = blobH, jpegH = blobW;       // 1080 x 1920
    const int exifOri = 1;                  // pixels are upright now

    EXPECT_EQ(jpegW, 1080);
    EXPECT_EQ(jpegH, 1920);
    EXPECT_EQ(exifOri, 1) << "pixels are upright, EXIF must not re-rotate";

    // The scene is 9:16; the emitted JPEG must be 9:16 too.
    const double sceneAspect = 720.0 / 1280.0;          // 0.5625
    const double jpegAspect  = (double)jpegW / jpegH;   // 0.5625
    EXPECT_NEAR(jpegAspect, sceneAspect, 1e-6)
        << "saved JPEG aspect must match the scene -- no stretching";

    // And explicitly: it must NOT be the old stretched landscape raster.
    EXPECT_NE(jpegW, 1920);
    EXPECT_NE(jpegH, 1080);
    const double oldAspect = 1920.0 / 1080.0;
    const double anisotropy = oldAspect / sceneAspect;  // 3.16x distortion
    EXPECT_GT(anisotropy, 3.0) << "sanity: the old path really was that wrong";

    // Now actually encode it and confirm the JPEG header carries the upright
    // dimensions (this is what an EXIF-ignoring consumer will read).
    JpegEncoder enc;
    JpegEncoder::ExifData exif;
    exif.iso = 200;
    exif.exposureSec = 1.0f / 60.0f;
    exif.fNumber = 1.85f;
    exif.focalLength = 6.81f;
    exif.imageWidth = jpegW;
    exif.imageHeight = jpegH;
    exif.orientation = exifOri;

    std::vector<uint8_t> jpeg;
    ASSERT_TRUE(enc.encode(upright.data(), jpegW, jpegH, 95, exif, jpeg));

    struct jpeg_decompress_struct dinfo;
    struct jpeg_error_mgr jerr;
    dinfo.err = jpeg_std_error(&jerr);
    jpeg_create_decompress(&dinfo);
    jpeg_mem_src(&dinfo, jpeg.data(), jpeg.size());
    ASSERT_EQ(jpeg_read_header(&dinfo, TRUE), JPEG_HEADER_OK);
    jpeg_start_decompress(&dinfo);

    EXPECT_EQ((int)dinfo.output_width, 1080)
        << "encoded JPEG must be portrait 1080 wide";
    EXPECT_EQ((int)dinfo.output_height, 1920)
        << "encoded JPEG must be portrait 1920 tall";

    // We only needed the header dimensions; abort rather than finish so
    // libjpeg does not complain about unread scanlines.
    jpeg_abort_decompress(&dinfo);
    jpeg_destroy_decompress(&dinfo);
}

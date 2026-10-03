#ifndef FAKE_HAL_STILL_ORIENTATION_H
#define FAKE_HAL_STILL_ORIENTATION_H

// Still-capture (JPEG/BLOB) orientation helpers.
//
// The camera framework applies ANDROID_SENSOR_ORIENTATION to preview /
// IMPLEMENTATION_DEFINED streams but NOT to BLOB streams.  The still path
// therefore has to bake the rotation into the pixels itself, otherwise the
// saved JPEG keeps the sensor raster (e.g. 1920x1080 landscape) for a scene
// the user framed in portrait, and only looks correct in EXIF-aware viewers.
//
// These helpers are kept in a header (rather than static in
// FakeCameraDevice.cpp) so they can be unit-tested directly.

#include <cstdint>
#include <cstddef>

namespace fake_hal {

// True 90-degree rotation of an NV21 image with TRANSPOSED output dimensions.
//
// Unlike the preview-path rotateNV21_90Or270Fit(), this does NOT letterbox the
// rotated image back into the original WxH raster: the destination raster is
// H x W.  Nothing is scaled, cropped or padded, so the aspect ratio of the
// scene is preserved exactly and no stretching can occur.
//
//   src : w x h NV21  (Y plane w*h bytes, then h/2 rows of w bytes holding
//                      w/2 interleaved V,U pairs)
//   dst : h x w NV21, caller-allocated with w*h*3/2 bytes
//   cw  : true  -> rotate 90 degrees clockwise
//         false -> rotate 90 degrees counter-clockwise
//
// w and h must both be even (NV21 4:2:0 chroma subsampling).
inline void rotateNV21_90Transpose(const uint8_t* src, int w, int h,
                                   uint8_t* dst, bool cw) {
    // ---- Y plane: src is w x h, dst is h x w -------------------------------
    for (int dy = 0; dy < w; ++dy) {
        for (int dx = 0; dx < h; ++dx) {
            int sx, sy;
            if (cw) { sx = dy;         sy = h - 1 - dx; }
            else    { sx = w - 1 - dy; sy = dx;         }
            dst[(size_t)dy * h + dx] = src[(size_t)sy * w + sx];
        }
    }

    // ---- Chroma plane (NV21 = interleaved V,U pairs) -----------------------
    // src chroma: h/2 rows of w bytes (w/2 pairs per row)
    // dst chroma: w/2 rows of h bytes (h/2 pairs per row)
    const uint8_t* srcUV = src + (size_t)w * h;
    uint8_t*       dstUV = dst + (size_t)w * h;
    const int scw = w / 2;   // src chroma pairs per row
    const int sch = h / 2;   // src chroma rows
    for (int dy = 0; dy < scw; ++dy) {
        for (int dx = 0; dx < sch; ++dx) {
            int sx, sy;
            if (cw) { sx = dy;           sy = sch - 1 - dx; }
            else    { sx = scw - 1 - dy; sy = dx;           }
            uint8_t*       d = dstUV + (size_t)dy * h + (size_t)dx * 2;
            const uint8_t* s = srcUV + (size_t)sy * w + (size_t)sx * 2;
            d[0] = s[0];   // V
            d[1] = s[1];   // U
        }
    }
}

// 180-degree rotation of an NV21 image, in place. Dimensions are unchanged.
inline void rotateNV21_180InPlace(uint8_t* data, int w, int h) {
    const int ySize = w * h;
    for (int i = 0; i < ySize / 2; ++i) {
        uint8_t t = data[i];
        data[i] = data[ySize - 1 - i];
        data[ySize - 1 - i] = t;
    }
    uint8_t* uv = data + ySize;
    const int uvSize = ySize / 2;
    for (int i = 0; i < uvSize / 2; i += 2) {
        uint8_t t0 = uv[i];
        uv[i] = uv[uvSize - 2 - i];
        uv[uvSize - 2 - i] = t0;
        uint8_t t1 = uv[i + 1];
        uv[i + 1] = uv[uvSize - 1 - i];
        uv[uvSize - 1 - i] = t1;
    }
}

// Given the sensor orientation, report the EXIF orientation the still path
// should emit AFTER baking the rotation into the pixels.  Because the pixels
// are made upright, this is always 1 ("normal") for the rotations we handle.
// Returns the number of degrees that will be baked in (0/90/180/270).
inline int stillBakeDegrees(int sensorOrientation) {
    int r = ((sensorOrientation % 360) + 360) % 360;
    if (r == 90 || r == 180 || r == 270) return r;
    return 0;
}

// True when baking the rotation swaps the output width/height.
inline bool stillBakeSwapsDimensions(int sensorOrientation) {
    int r = stillBakeDegrees(sensorOrientation);
    return r == 90 || r == 270;
}

}  // namespace fake_hal

#endif  // FAKE_HAL_STILL_ORIENTATION_H

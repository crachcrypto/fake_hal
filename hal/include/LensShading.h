#pragma once

#include <cstdint>
#include <cmath>
#include <algorithm>
#include <vector>

namespace fake_hal {


class LensShading {
public:

    explicit LensShading(float k = 0.4f) : k_(k) {}


    void apply(uint8_t* nv21, int width, int height) {
        // Cache a per-pixel Q10 gain map keyed on (width,height,k_). Once built,
        // each frame is a single multiply + shift per Y sample, which the
        // compiler vectorises into 16-byte NEON multiplies. At 4080x3072 this
        // turns the previous ~120 ms scalar pass with a per-pixel sqrt+divide
        // into a ~10-15 ms memory-bound pass.
        if (cacheW_ != width || cacheH_ != height || cacheK_ != k_) {
            rebuildGain(width, height);
        }

        const uint16_t* gainQ10 = gainMapQ10_.data();
        const int N = width * height;
        for (int i = 0; i < N; ++i) {
            int v = ((int)nv21[i] * (int)gainQ10[i]) >> 10;
            if (v > 255) v = 255;
            nv21[i] = (uint8_t)v;
        }
    }


    float gainAt(float r) const {
        return 1.0f / (1.0f + k_ * r * r);
    }

    float getK() const { return k_; }
    void  setK(float k) { k_ = k; }

private:
    void rebuildGain(int width, int height) {
        cacheW_ = width;
        cacheH_ = height;
        cacheK_ = k_;
        gainMapQ10_.assign((size_t)width * (size_t)height, 1024);

        const float cx = width  * 0.5f;
        const float cy = height * 0.5f;
        const float invMaxDistSq = 1.0f / (cx * cx + cy * cy);

        for (int row = 0; row < height; ++row) {
            float dy = row - cy;
            float dy2 = dy * dy;
            uint16_t* dst = gainMapQ10_.data() + (size_t)row * width;
            for (int col = 0; col < width; ++col) {
                float dxf = col - cx;
                float rSq = (dxf * dxf + dy2) * invMaxDistSq;
                float gain = 1.0f / (1.0f + k_ * rSq);
                int q = (int)std::lround(gain * 1024.0f);
                if (q < 0) q = 0;
                if (q > 65535) q = 65535;
                dst[col] = (uint16_t)q;
            }
        }
    }

    float k_;
    std::vector<uint16_t> gainMapQ10_;
    int cacheW_ = 0;
    int cacheH_ = 0;
    float cacheK_ = -1.0f;
};

}

#include "NoiseOverlay.h"
#include "FakeHALLog.h"
#include <cmath>
#include <algorithm>
#include <cstring>

namespace fake_hal {

NoiseOverlay::NoiseOverlay(float readNoiseSigma,
                           float shotNoiseFactor,
                           uint64_t fpnSeed)
    : readNoiseSigma_(readNoiseSigma)
    , shotNoiseFactor_(shotNoiseFactor)
    , fpnSeed_(fpnSeed)
    , profile_(SensorProfile::defaultProfile())
    , rngY_((uint32_t)(fpnSeed ^ 0xAAAA5555ULL))
    , rngUV_((uint32_t)(fpnSeed ^ 0x5555AAAAULL))
{
}

void NoiseOverlay::setStrength(float strength) {
    std::lock_guard<std::recursive_mutex> lock(fpnMutex_);
    strength_ = std::clamp(strength, 0.0f, 3.0f);
    FHAL_I("NoiseOverlay: strength set to %.2f", strength_);
}

void NoiseOverlay::setSensorFingerprint(const std::string& deviceSerial,
                                         const std::string& modelName,
                                         const std::string& cameraId) {
    std::lock_guard<std::recursive_mutex> lock(fpnMutex_);

    // Select per-camera noise profile (front vs back differ on real hardware)
    profile_ = SensorProfile::profileForCamera(cameraId);

    // FNV-1a hash including cameraId -- ensures unique FPN per camera
    uint64_t hash = 14695981039346656037ULL;
    auto hashBytes = [&](const std::string& s) {
        for (char c : s) {
            hash ^= (uint8_t)c;
            hash *= 1099511628211ULL;
        }
    };
    hashBytes(deviceSerial);
    hash ^= 0xDEADC0DEULL;
    hashBytes(modelName);
    hash ^= 0xCA3E4A00ULL;   // separator before camera ID
    hashBytes(cameraId);      // <-- key fix: different seed per camera

    fpnSeed_ = hash;
    FHAL_I("NoiseOverlay[cam%s]: FPN seed=0x%016llx profile={rn=%.1f fpn=%.3f shot=%.1f col=%.3f}",
           cameraId.c_str(), (unsigned long long)fpnSeed_,
           profile_.readNoiseBase, profile_.fpnAmplitude,
           profile_.shotNoiseScale, profile_.fpnColumnBias);

    // Reset cached FPN pattern so it regenerates with new seed
    fpnWidth_ = fpnHeight_ = 0;
    fpnMap_.clear();
    rowFpnMap_.clear();
    colFpnMap_.clear();
    hotPixels_.clear();
    deadPixels_.clear();
}

void NoiseOverlay::ensureFPN(int width, int height) {
    if (fpnWidth_ == width && fpnHeight_ == height) return;

    fpnWidth_  = width;
    fpnHeight_ = height;
    fpnMap_.resize(width * height);

    // Use profile-driven FPN amplitude (back camera has stronger FPN)
    float fpnSigma = 1.2f + profile_.fpnAmplitude * 80.0f;

    std::mt19937 fpnRng((uint32_t)fpnSeed_);
    std::normal_distribution<float> fpnDist(0.0f, fpnSigma);

    for (int i = 0; i < width * height; i++) {
        int val = (int)fpnDist(fpnRng);
        fpnMap_[i] = (int8_t)std::clamp(val, -4, 4);
    }


    rowFpnMap_.resize(height);
    std::normal_distribution<float> rowDist(0.0f, 0.8f);
    for (int i = 0; i < height; i++)
        rowFpnMap_[i] = (int8_t)std::clamp((int)rowDist(fpnRng), -3, 3);

    // Column FPN varies by sensor profile (CMOS sensors have different column biases)
    colFpnMap_.resize(width);
    float colSigma = 0.4f + profile_.fpnColumnBias * 50.0f;
    std::normal_distribution<float> colDist(0.0f, colSigma);
    for (int i = 0; i < width; i++)
        colFpnMap_[i] = (int8_t)std::clamp((int)colDist(fpnRng), -2, 2);

    // Hot/dead pixel defects — real GN1 sensors have ~50-200 per megapixel
    hotPixels_.clear();
    deadPixels_.clear();
    int numHot  = std::max(3, width * height / 5000);
    int numDead = std::max(2, width * height / 8000);
    std::uniform_int_distribution<int> posDist(0, width * height - 1);
    for (int i = 0; i < numHot;  i++) hotPixels_.push_back(posDist(fpnRng));
    for (int i = 0; i < numDead; i++) deadPixels_.push_back(posDist(fpnRng));
}

void NoiseOverlay::ensureNoiseTile() {
    if (noiseTileReady_) return;
    // Deterministic seed so the noise pattern remains stable across sessions
    // (avoids subtle frame-to-frame "shimmer" some KYC liveness checks flag).
    std::mt19937 tileRng(0xC0FFEE05ULL ^ fpnSeed_);
    std::normal_distribution<float> dist(0.0f, kNoiseTileSigma);
    for (int i = 0; i < kNoiseTileSize; ++i) {
        int v = (int)dist(tileRng);
        if (v < -32767) v = -32767;
        else if (v > 32767) v = 32767;
        noiseTile_[i] = (int16_t)v;
    }
    noiseTileReady_ = true;
}

void NoiseOverlay::applyLuma(uint8_t* yPlane, int width, int height,
                             float isoGain)
{
    std::lock_guard<std::recursive_mutex> lock(fpnMutex_);
    if (strength_ <= 0.001f) return;
    ensureFPN(width, height);
    ensureNoiseTile();

    // Use profile-driven noise parameters
    const float isoScale = std::sqrt(isoGain);
    const float readNoiseEff = profile_.readNoiseBase * readNoiseSigma_ / 3.0f * strength_;
    const float shotScale = profile_.shotNoiseScale * strength_;
    const float fpnStr = profile_.fpnAmplitude / 0.012f;  // normalize to default
    const float invTileSigma = 1.0f / kNoiseTileSigma;

    // Precompute Q8 scale per luma value: scale[L] = sigma(L) / kNoiseTileSigma * 256.
    // The hot loop becomes one LUT read + one mul/shift instead of a sqrt and
    // a Gaussian RNG call per pixel (~10x cheaper at 4080x3072).
    int16_t lumaScaleQ8[256];
    for (int L = 0; L < 256; ++L) {
        float luma = L / 255.0f;
        float sigma = std::sqrt(
            readNoiseEff * readNoiseEff * isoScale * isoScale
            + shotNoiseFactor_ * shotScale * luma * 255.0f * isoScale);
        int s = (int)std::lround(sigma * invTileSigma * 256.0f);
        if (s < 0) s = 0;
        if (s > 32767) s = 32767;
        lumaScaleQ8[L] = (int16_t)s;
    }

    // Precompute combined row+col FPN per coordinate so the inner loop is just
    // load+lookup+lookup+mul+add+clamp.
    const int N = width * height;

    // Pre-scale FPN maps to Q8 so the hot loop is pure integer mul/shift/add
    // and auto-vectorises cleanly on arm64. Costs O(W+H+W*H) of cheap math up
    // front but saves a per-pixel std::lround inside the inner loop.
    const int rowScaleQ8 = (int)std::lround(0.6f * strength_ * 256.0f);
    const int colScaleQ8 = (int)std::lround(0.3f * strength_ * 256.0f);
    const int perPixScaleQ8 = (int)std::lround(0.4f * fpnStr * strength_ * 256.0f);

    // Drift noise tile cursor each call so identical-looking noise patterns
    // don't repeat frame-to-frame (would defeat anti-detection randomisation).
    uint32_t cursor = noiseTileCursorY_;
    cursor = cursor * 1664525u + 1013904223u; // LCG step per frame
    noiseTileCursorY_ = cursor;

    // Precompute column FPN once per row sweep.
    std::vector<int16_t> colFpnPre(width);
    for (int x = 0; x < width; ++x) {
        int v = ((int)colFpnMap_[x] * colScaleQ8) >> 8;
        if (v < -127) v = -127; else if (v > 127) v = 127;
        colFpnPre[x] = (int16_t)v;
    }

    for (int y = 0; y < height; ++y) {
        const int rowFpn = ((int)rowFpnMap_[y] * rowScaleQ8) >> 8;
        uint8_t* row = yPlane + y * (size_t)width;
        const int8_t* fpnRow = fpnMap_.data() + (size_t)y * width;
        const uint32_t rowCursor = cursor + (uint32_t)(y * width);
        const int16_t* colPre = colFpnPre.data();
        for (int x = 0; x < width; ++x) {
            uint8_t L = row[x];
            int16_t tileSample = noiseTile_[(rowCursor + (uint32_t)x) & (kNoiseTileSize - 1)];
            int temporal = (tileSample * (int)lumaScaleQ8[L]) >> 8;
            int perPix = ((int)fpnRow[x] * perPixScaleQ8) >> 8;
            int newVal = (int)L + temporal + rowFpn + (int)colPre[x] + perPix;
            if (newVal < 0) newVal = 0;
            else if (newVal > 255) newVal = 255;
            row[x] = (uint8_t)newVal;
        }
    }

    for (int idx : hotPixels_)  if (idx < N) yPlane[idx] = 255;
    for (int idx : deadPixels_) if (idx < N) yPlane[idx] = 0;
}

void NoiseOverlay::apply(uint8_t* nv21, int width, int height,
                         float isoGain)
{
    if (strength_ <= 0.001f) return;
    // Lock is acquired inside applyLuma and for UV section below
    applyLuma(nv21, width, height, isoGain);

    std::lock_guard<std::recursive_mutex> lock(fpnMutex_);
    ensureNoiseTile();

    uint8_t* uvPlane = nv21 + width * height;
    int uvSize = width * height / 2;

    const float uvSigma = profile_.readNoiseBase * 0.5f * std::sqrt(isoGain) * strength_;
    const int uvScaleQ8 = (int)std::lround(uvSigma / kNoiseTileSigma * 256.0f);

    uint32_t cursor = noiseTileCursorUV_;
    cursor = cursor * 1664525u + 1013904223u;
    noiseTileCursorUV_ = cursor;

    for (int i = 0; i < uvSize; ++i) {
        int16_t s = noiseTile_[(cursor + (uint32_t)i) & (kNoiseTileSize - 1)];
        int noise = (s * uvScaleQ8) >> 8;
        int newVal = (int)uvPlane[i] + noise;
        if (newVal < 0) newVal = 0;
        else if (newVal > 255) newVal = 255;
        uvPlane[i] = (uint8_t)newVal;
    }
}

}

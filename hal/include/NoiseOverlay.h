#pragma once

#include <cstdint>
#include <random>
#include <string>
#include <mutex>
#include <vector>

namespace fake_hal {

/// Per-camera noise profile -- real sensors differ between front/back
struct SensorProfile {
    float readNoiseBase;   // ADU RMS at ISO 100
    float fpnAmplitude;    // relative FPN strength 0..1
    float shotNoiseScale;  // shot noise multiplier
    float fpnColumnBias;   // column FPN strength (CMOS sensors differ here)

    static SensorProfile profileForCamera(const std::string& cameraId) {
        if (cameraId == "1") {  // front: smaller sensor, higher noise floor
            return {4.0f, 0.008f, 1.3f, 0.004f};
        }
        // back (default): larger sensor, lower noise, more pronounced FPN
        return {2.5f, 0.015f, 1.0f, 0.008f};
    }

    static SensorProfile defaultProfile() {
        return {3.0f, 0.012f, 1.0f, 0.006f};
    }
};


class NoiseOverlay {
public:

    explicit NoiseOverlay(
        float readNoiseSigma = 3.0f,
        float shotNoiseFactor = 0.35f,
        uint64_t fpnSeed = 0xDEADBEEF42ULL
    );


    void apply(uint8_t* nv21, int width, int height, float isoGain = 1.0f);


    void applyLuma(uint8_t* yPlane, int width, int height, float isoGain = 1.0f);

    /// Runtime strength multiplier from controller UI: 0 disables synthetic
    /// sensor noise, 1 is default, values up to 3 exaggerate it for testing.
    void setStrength(float strength);

    /// Set FPN fingerprint per camera -- seed differs by cameraId
    void setSensorFingerprint(const std::string& deviceSerial,
                              const std::string& modelName,
                              const std::string& cameraId = "0");

private:
    float readNoiseSigma_;
    float shotNoiseFactor_;
    float strength_ = 1.0f;


    uint64_t fpnSeed_ = 0xFEEDFACE12345678ULL;

    /// Per-camera sensor noise profile
    SensorProfile profile_;

    std::vector<int8_t> fpnMap_;
    int fpnWidth_ = 0, fpnHeight_ = 0;


    std::vector<int8_t> rowFpnMap_;


    std::vector<int8_t> colFpnMap_;


    std::vector<int> hotPixels_;
    std::vector<int> deadPixels_;


    std::mt19937 rngY_;
    std::mt19937 rngUV_;

    // Precomputed Gaussian noise tile: pulling samples from a fixed-size LUT
    // is roughly an order of magnitude faster than calling
    // std::normal_distribution per pixel (which dominates frame time at high
    // resolutions). Generated once with sigma=NOISE_TILE_SIGMA and scaled per
    // pixel via integer arithmetic.
    static constexpr int kNoiseTileSize = 16384; // power of two for cheap mod
    static constexpr float kNoiseTileSigma = 32.0f;
    int16_t noiseTile_[kNoiseTileSize];
    bool noiseTileReady_ = false;
    uint32_t noiseTileCursorY_ = 0;
    uint32_t noiseTileCursorUV_ = 0;
    void ensureNoiseTile();

    void ensureFPN(int width, int height);

    /// Protects FPN state during concurrent apply()/setSensorFingerprint()
    mutable std::recursive_mutex fpnMutex_;
};

}

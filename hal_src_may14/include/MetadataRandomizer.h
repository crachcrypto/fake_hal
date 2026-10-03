#pragma once

#include <camera/CameraMetadata.h>
#include <system/camera_metadata.h>
#include <random>
#include <cstdint>
#include <string>

namespace fake_hal {


class MetadataRandomizer {
public:
    MetadataRandomizer();


    void fill(uint32_t frameNumber, android::CameraMetadata* meta, int64_t timestampNs,
              int frameHeight = 1080, int64_t frameDurationNs = 33'333'333LL);


    void advance();

    /// Set per-camera metadata ranges (front vs back differ)
    void setCameraProfile(const std::string& cameraId);

    /// Override ISO range from runtime config. The base drifts around the
    /// middle of this range so UI changes are visible in result metadata.
    void setIsoRange(float minIso, float maxIso);

private:

    float iso_;
    float exposureMs_;
    float aperture_;


    float gainR_, gainGr_, gainGb_, gainB_;


    float focusDiopters_;


    int64_t rollingShutterSkewNs_;
    int64_t frameDurationNs_;


    std::mt19937 rng_;
    std::normal_distribution<float> stdNorm_;
    std::uniform_real_distribution<float> uni_;


    float drift(float current, float mean, float theta, float sigma);

    // Per-camera min/max/base -- set by setCameraProfile()
    float kMinISO_ = 50.0f;
    float kMaxISO_ = 3200.0f;
    float kBaseISO_ = 100.0f;

    float kMinExpMs_ = 0.5f;
    float kMaxExpMs_ = 33.3f;
    float kBaseExpMs_ = 16.0f;

    float focalLengthMm_ = 6.81f;

public:

    float getCurrentISO() const { return iso_; }


    float getCurrentExposureMs() const { return exposureMs_; }
};

}

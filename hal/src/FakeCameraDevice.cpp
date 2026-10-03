#include <thread>
#include <chrono>
#include <random>
#include <utils/SystemClock.h>
#include "FakeCameraDevice.h"
#include "GrallocHelper.h"
#include "RollingShutter.h"
#include "LensShading.h"
#include "StillOrientation.h"   // still/JPEG orientation bake (unit-tested)

#include <sys/system_properties.h>

#include <hardware/camera3.h>
#include <hardware/gralloc1.h>
#include <vndk/hardware_buffer.h>
#include <android/hardware_buffer.h>
#include <time.h>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <sys/mman.h>
#include <cstdlib>

#ifndef FAKE_HAL_TEST_BUILD
#include <sys/system_properties.h>
#include <cutils/native_handle.h>
#include <aidl/android/hardware/common/NativeHandle.h>
#include <aidl/android/hardware/camera/common/Status.h>
#endif

#define LOG_TAG "FakeHAL_Device"
#include <log/log.h>
#include "FakeHALLog.h"

// #region agent log
#define FHAL_DEBUG_LOG "/data/local/tmp/fhal_debug.log"
static void dbgLog(const char* hyp, const char* loc, const char* msg,
                   const char* json_data) {
    FILE* f = fopen(FHAL_DEBUG_LOG, "a");
    if (!f) return;
    struct timespec ts; clock_gettime(CLOCK_REALTIME, &ts);
    long long ms = (long long)ts.tv_sec * 1000 + ts.tv_nsec / 1000000;
    fprintf(f, "{\"sessionId\":\"d4053e\",\"hypothesisId\":\"%s\","
               "\"location\":\"%s\",\"message\":\"%s\","
               "\"data\":%s,\"timestamp\":%lld}\n",
            hyp, loc, msg, json_data, ms);
    fclose(f);
}
// #endregion

#ifndef FAKE_HAL_TEST_BUILD
// AOSP build: AIDL types use enum classes, NativeHandle instead of buffer_handle_t
using ::aidl::android::hardware::graphics::common::PixelFormat;
using ::aidl::android::hardware::graphics::common::BufferUsage;
using ::aidl::android::hardware::common::NativeHandle;

static native_handle_t* nativeHandleFromAidl(const NativeHandle& nh) {
    native_handle_t* h = native_handle_create(nh.fds.size(), nh.ints.size());
    if (!h) return nullptr;
    for (size_t i = 0; i < nh.fds.size(); i++) h->data[i] = nh.fds[i].get();
    for (size_t i = 0; i < nh.ints.size(); i++) h->data[nh.fds.size() + i] = nh.ints[i];
    return h;
}

// Helper to extract int32_t from format field (PixelFormat enum in AOSP, int32_t in tests)
template<typename T>
static inline int32_t fmtToInt(T val) { return static_cast<int32_t>(val); }
#else
// Test build: format is already int32_t
template<typename T>
static inline int32_t fmtToInt(T val) { return static_cast<int32_t>(val); }
#endif

namespace fake_hal {

static std::string detectForegroundPackage();
static bool isChromeWebClient(const std::string& packageName);

struct RuntimeConfig {
    float noiseLevel = 1.0f;
    int isoMin = 100;
    int isoMax = 800;
    bool qrCleanMode = false;
    bool backRotate180 = false;
    bool frontRotate180 = false;
    bool frontMirror = false;
    bool chromeFrontRotate180 = false;
    bool chromeFrontMirror = false;
    bool gyroEnabled = false;
    float gyroStrength = 0.1f;
    int previewRotation = 0;
    bool previewMirrorH = false;
    bool previewMirrorV = false;
    int sensorOrientation = 90;
    bool preNormalized = false;
};

static bool parseBoolValue(const char* value) {
    return value && (strcmp(value, "1") == 0 ||
                     strcmp(value, "true") == 0 ||
                     strcmp(value, "TRUE") == 0 ||
                     strcmp(value, "on") == 0);
}

static RuntimeConfig readRuntimeConfig() {
    RuntimeConfig cfg;
    FILE* f = fopen("/data/local/tmp/fakehal.conf", "r");
    if (!f) return cfg;

    char line[256];
    while (fgets(line, sizeof(line), f)) {
        char* eq = strchr(line, '=');
        if (!eq) continue;
        *eq = '\0';
        char* key = line;
        char* val = eq + 1;
        val[strcspn(val, "\r\n")] = '\0';

        if (strcmp(key, "noise_level") == 0) {
            cfg.noiseLevel = std::clamp(strtof(val, nullptr), 0.0f, 3.0f);
        } else if (strcmp(key, "iso_min") == 0) {
            cfg.isoMin = std::clamp(atoi(val), 50, 6400);
        } else if (strcmp(key, "iso_max") == 0) {
            cfg.isoMax = std::clamp(atoi(val), 50, 6400);
        } else if (strcmp(key, "qr_mode") == 0) {
            cfg.qrCleanMode = parseBoolValue(val);
        } else if (strcmp(key, "back_rotate_180") == 0) {
            cfg.backRotate180 = parseBoolValue(val);
        } else if (strcmp(key, "front_rotate_180") == 0) {
            cfg.frontRotate180 = parseBoolValue(val);
        } else if (strcmp(key, "front_mirror") == 0) {
            cfg.frontMirror = parseBoolValue(val);
        } else if (strcmp(key, "chrome_front_rotate_180") == 0) {
            cfg.chromeFrontRotate180 = parseBoolValue(val);
        } else if (strcmp(key, "chrome_front_mirror") == 0) {
            cfg.chromeFrontMirror = parseBoolValue(val);
        } else if (strcmp(key, "gyro_enabled") == 0) {
            cfg.gyroEnabled = parseBoolValue(val);
        } else if (strcmp(key, "gyro_strength") == 0) {
            cfg.gyroStrength = std::clamp(strtof(val, nullptr), 0.0f, 1.0f);
        } else if (strcmp(key, "preview_rotation") == 0) {
            cfg.previewRotation = ((atoi(val) % 360) + 360) % 360;
        } else if (strcmp(key, "preview_mirror_h") == 0) {
            cfg.previewMirrorH = parseBoolValue(val);
        } else if (strcmp(key, "preview_mirror_v") == 0) {
            cfg.previewMirrorV = parseBoolValue(val);
        } else if (strcmp(key, "sensor_orientation") == 0) {
            cfg.sensorOrientation = std::clamp(atoi(val), 0, 360);
        } else if (strcmp(key, "pre_normalized") == 0) {
            cfg.preNormalized = parseBoolValue(val);
        }
    }
    fclose(f);

    if (cfg.isoMin > cfg.isoMax) std::swap(cfg.isoMin, cfg.isoMax);
    return cfg;
}


static android::CameraMetadata buildPixel7MainCharacteristics() {
    android::CameraMetadata meta;


    uint8_t facing = ANDROID_LENS_FACING_BACK;
    meta.update(ANDROID_LENS_FACING, &facing, 1);


    float physSize[2] = {8.64f, 6.48f};
    meta.update(ANDROID_SENSOR_INFO_PHYSICAL_SIZE, physSize, 2);


    int32_t pixelArray[2] = {4080, 3072};
    meta.update(ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE, pixelArray, 2);


    int32_t activeArray[4] = {0, 0, 4080, 3072};
    meta.update(ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE, activeArray, 4);


    float aperture = 1.85f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_APERTURES, &aperture, 1);


    float focalLen = 6.81f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_FOCAL_LENGTHS, &focalLen, 1);


    int32_t isoRange[2] = {50, 3200};
    meta.update(ANDROID_SENSOR_INFO_SENSITIVITY_RANGE, isoRange, 2);


    int64_t expRange[2] = {14'000LL, 1'000'000'000LL};
    meta.update(ANDROID_SENSOR_INFO_EXPOSURE_TIME_RANGE, expRange, 2);


    int64_t frameDurRange[2] = {33'333'333LL, 200'000'000LL};
    meta.update(ANDROID_SENSOR_INFO_MAX_FRAME_DURATION, &frameDurRange[1], 1);


    uint8_t cfa = ANDROID_SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_BGGR;
    meta.update(ANDROID_SENSOR_INFO_COLOR_FILTER_ARRANGEMENT, &cfa, 1);


    uint8_t hwLevel = ANDROID_INFO_SUPPORTED_HARDWARE_LEVEL_FULL;
    meta.update(ANDROID_INFO_SUPPORTED_HARDWARE_LEVEL, &hwLevel, 1);


    uint8_t caps[] = {
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS,
    };
    meta.update(ANDROID_REQUEST_AVAILABLE_CAPABILITIES, caps, sizeof(caps));


    uint8_t pipelineDepth = 4;
    meta.update(ANDROID_REQUEST_PIPELINE_MAX_DEPTH, &pipelineDepth, 1);


    std::vector<int32_t> streamConfigs;
    auto addConfig = [&](int32_t fmt, int32_t w, int32_t h) {
        streamConfigs.push_back(fmt);
        streamConfigs.push_back(w);
        streamConfigs.push_back(h);
        streamConfigs.push_back(ANDROID_SCALER_AVAILABLE_STREAM_CONFIGURATIONS_OUTPUT);
    };


    addConfig(HAL_PIXEL_FORMAT_BLOB, 4080, 3072);
    addConfig(HAL_PIXEL_FORMAT_BLOB, 1920, 1080);


    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 4080, 3072);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 1920, 1080);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 1280, 720);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 640, 480);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 320, 240);


    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1920, 1080);
    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1280, 720);
    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 640, 480);

    meta.update(ANDROID_SCALER_AVAILABLE_STREAM_CONFIGURATIONS,
                streamConfigs.data(), streamConfigs.size());


    std::vector<int64_t> minDurations;
    auto addDur = [&](int32_t fmt, int32_t w, int32_t h, int64_t durNs) {
        minDurations.push_back(fmt);
        minDurations.push_back(w);
        minDurations.push_back(h);
        minDurations.push_back(durNs);
    };
    constexpr int64_t k30fps = 33'333'333LL;
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 4080, 3072, k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 1280, 720,  k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 640, 480,   k30fps);
    addDur(HAL_PIXEL_FORMAT_BLOB, 4080, 3072, k30fps);
    addDur(HAL_PIXEL_FORMAT_BLOB, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1280, 720,  k30fps);

    meta.update(ANDROID_SCALER_AVAILABLE_MIN_FRAME_DURATIONS,
                minDurations.data(), minDurations.size());


    uint8_t aeModes[] = {ANDROID_CONTROL_AE_MODE_OFF, ANDROID_CONTROL_AE_MODE_ON};
    meta.update(ANDROID_CONTROL_AE_AVAILABLE_MODES, aeModes, 2);


    uint8_t afModes[] = {
        ANDROID_CONTROL_AF_MODE_OFF,
        ANDROID_CONTROL_AF_MODE_AUTO,
        ANDROID_CONTROL_AF_MODE_CONTINUOUS_PICTURE,
        ANDROID_CONTROL_AF_MODE_CONTINUOUS_VIDEO,
    };
    meta.update(ANDROID_CONTROL_AF_AVAILABLE_MODES, afModes, 4);


    uint8_t awbModes[] = {
        ANDROID_CONTROL_AWB_MODE_OFF,
        ANDROID_CONTROL_AWB_MODE_AUTO,
        ANDROID_CONTROL_AWB_MODE_DAYLIGHT,
        ANDROID_CONTROL_AWB_MODE_CLOUDY_DAYLIGHT,
    };
    meta.update(ANDROID_CONTROL_AWB_AVAILABLE_MODES, awbModes, 4);


    uint8_t oisModes[] = {
        ANDROID_LENS_OPTICAL_STABILIZATION_MODE_OFF,
        ANDROID_LENS_OPTICAL_STABILIZATION_MODE_ON
    };
    meta.update(ANDROID_LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION, oisModes, 2);


    uint8_t nrModes[] = {
        ANDROID_NOISE_REDUCTION_MODE_OFF,
        ANDROID_NOISE_REDUCTION_MODE_FAST,
        ANDROID_NOISE_REDUCTION_MODE_HIGH_QUALITY,
    };
    meta.update(ANDROID_NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, nrModes, 3);


    float focusRange[2] = {0.0f, 10.0f};
    meta.update(ANDROID_LENS_INFO_MINIMUM_FOCUS_DISTANCE, &focusRange[1], 1);


    uint8_t croppingType = ANDROID_SCALER_CROPPING_TYPE_CENTER_ONLY;
    meta.update(ANDROID_SCALER_CROPPING_TYPE, &croppingType, 1);


    float maxZoom = 8.0f;
    meta.update(ANDROID_SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, &maxZoom, 1);

    // Pixel 7 back camera reports SENSOR_ORIENTATION=90 (the sensor's natural
    // frame is the device-portrait view rotated 90° CW). The camera framework
    // rotates HAL buffers by SENSOR_ORIENTATION to display upright in portrait.
    // The HAL delivers buffers pre-rotated to compensate (see FakeCameraDeviceSession::fillYUVBuffer).
    int32_t sensorOrientation = 90;
    meta.update(ANDROID_SENSOR_ORIENTATION, &sensorOrientation, 1);

    // --- JPEG metadata (required for BLOB streams) ---
    int32_t jpegMaxSize = 4080 * 3072 * 3 / 2 + 65536;  // ~19MB
    meta.update(ANDROID_JPEG_MAX_SIZE, &jpegMaxSize, 1);

    int32_t jpegThumbSizes[] = {0, 0, 160, 120, 320, 240};
    meta.update(ANDROID_JPEG_AVAILABLE_THUMBNAIL_SIZES, jpegThumbSizes, 6);

    // --- Required metadata for Google Camera ---

    // Stall durations (required by CameraService)
    std::vector<int64_t> stallDurations;
    auto addStall = [&](int32_t fmt, int32_t w, int32_t h, int64_t stall) {
        stallDurations.push_back(fmt);
        stallDurations.push_back(w);
        stallDurations.push_back(h);
        stallDurations.push_back(stall);
    };
    addStall(HAL_PIXEL_FORMAT_BLOB, 4080, 3072, 100000000LL);
    addStall(HAL_PIXEL_FORMAT_BLOB, 1920, 1080, 50000000LL);
    addStall(HAL_PIXEL_FORMAT_YCbCr_420_888, 4080, 3072, 0);
    addStall(HAL_PIXEL_FORMAT_YCbCr_420_888, 1920, 1080, 0);
    addStall(HAL_PIXEL_FORMAT_YCbCr_420_888, 1280, 720, 0);
    addStall(HAL_PIXEL_FORMAT_YCbCr_420_888, 640, 480, 0);
    addStall(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1920, 1080, 0);
    addStall(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1280, 720, 0);
    addStall(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 640, 480, 0);
    meta.update(ANDROID_SCALER_AVAILABLE_STALL_DURATIONS,
                stallDurations.data(), stallDurations.size());

    // Max output streams: [raw, processed, processed_stalling(jpeg)]
    int32_t maxStreams[3] = {0, 3, 1};
    meta.update(ANDROID_REQUEST_MAX_NUM_OUTPUT_STREAMS, maxStreams, 3);

    // Partial result count
    int32_t partialCount = 1;
    meta.update(ANDROID_REQUEST_PARTIAL_RESULT_COUNT, &partialCount, 1);

    // Max regions [AE, AWB, AF]
    int32_t maxRegions[3] = {1, 0, 1};
    meta.update(ANDROID_CONTROL_MAX_REGIONS, maxRegions, 3);

    // AE compensation
    int32_t aeCompRange[2] = {-24, 24};
    meta.update(ANDROID_CONTROL_AE_COMPENSATION_RANGE, aeCompRange, 2);
    camera_metadata_rational_t aeCompStep = {1, 6};
    meta.update(ANDROID_CONTROL_AE_COMPENSATION_STEP, &aeCompStep, 1);

    // Flash
    uint8_t flashAvail = ANDROID_FLASH_INFO_AVAILABLE_FALSE;
    meta.update(ANDROID_FLASH_INFO_AVAILABLE, &flashAvail, 1);

    // Face detection
    uint8_t fdModes[] = {ANDROID_STATISTICS_FACE_DETECT_MODE_OFF};
    meta.update(ANDROID_STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES, fdModes, 1);
    int32_t maxFaces = 0;
    meta.update(ANDROID_STATISTICS_INFO_MAX_FACE_COUNT, &maxFaces, 1);

    // Sync max latency
    int32_t syncLatency = ANDROID_SYNC_MAX_LATENCY_UNKNOWN;
    meta.update(ANDROID_SYNC_MAX_LATENCY, &syncLatency, 1);

    // Available request/result keys (minimal set)
    int32_t requestKeys[] = {
        ANDROID_CONTROL_AE_MODE,
        ANDROID_CONTROL_AF_MODE,
        ANDROID_CONTROL_AWB_MODE,
        ANDROID_CONTROL_MODE,
        ANDROID_SCALER_CROP_REGION,
        ANDROID_SENSOR_EXPOSURE_TIME,
        ANDROID_SENSOR_SENSITIVITY,
    };
    meta.update(ANDROID_REQUEST_AVAILABLE_REQUEST_KEYS, requestKeys, 7);

    int32_t resultKeys[] = {
        ANDROID_CONTROL_AE_MODE,
        ANDROID_CONTROL_AE_STATE,
        ANDROID_CONTROL_AF_MODE,
        ANDROID_CONTROL_AF_STATE,
        ANDROID_CONTROL_AWB_MODE,
        ANDROID_CONTROL_AWB_STATE,
        ANDROID_CONTROL_MODE,
        ANDROID_SENSOR_EXPOSURE_TIME,
        ANDROID_SENSOR_SENSITIVITY,
        ANDROID_SENSOR_TIMESTAMP,
        ANDROID_SCALER_CROP_REGION,
        ANDROID_STATISTICS_FACE_DETECT_MODE,
        ANDROID_SYNC_FRAME_NUMBER,
    };
    meta.update(ANDROID_REQUEST_AVAILABLE_RESULT_KEYS, resultKeys, 13);

    int32_t charKeys[] = {
        ANDROID_LENS_FACING,
        ANDROID_SENSOR_ORIENTATION,
        ANDROID_SENSOR_INFO_PHYSICAL_SIZE,
        ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE,
        ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE,
        ANDROID_SENSOR_INFO_SENSITIVITY_RANGE,
        ANDROID_SENSOR_INFO_EXPOSURE_TIME_RANGE,
        ANDROID_SENSOR_INFO_MAX_FRAME_DURATION,
        ANDROID_LENS_INFO_AVAILABLE_APERTURES,
        ANDROID_LENS_INFO_AVAILABLE_FOCAL_LENGTHS,
        ANDROID_SCALER_AVAILABLE_STREAM_CONFIGURATIONS,
        ANDROID_SCALER_AVAILABLE_MIN_FRAME_DURATIONS,
        ANDROID_SCALER_AVAILABLE_STALL_DURATIONS,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES,
        ANDROID_REQUEST_MAX_NUM_OUTPUT_STREAMS,
        ANDROID_REQUEST_PARTIAL_RESULT_COUNT,
        ANDROID_REQUEST_PIPELINE_MAX_DEPTH,
        ANDROID_INFO_SUPPORTED_HARDWARE_LEVEL,
        ANDROID_CONTROL_AE_AVAILABLE_MODES,
        ANDROID_CONTROL_AF_AVAILABLE_MODES,
        ANDROID_CONTROL_AWB_AVAILABLE_MODES,
        ANDROID_CONTROL_AE_COMPENSATION_RANGE,
        ANDROID_CONTROL_AE_COMPENSATION_STEP,
        ANDROID_FLASH_INFO_AVAILABLE,
        ANDROID_SYNC_MAX_LATENCY,
    };
    meta.update(ANDROID_REQUEST_AVAILABLE_CHARACTERISTICS_KEYS, charKeys, 25);

    // Control mode
    uint8_t ctrlModes[] = {ANDROID_CONTROL_MODE_OFF, ANDROID_CONTROL_MODE_AUTO};
    meta.update(ANDROID_CONTROL_AVAILABLE_MODES, ctrlModes, 2);

    // Scene modes
    uint8_t sceneModes[] = {ANDROID_CONTROL_SCENE_MODE_DISABLED};
    meta.update(ANDROID_CONTROL_AVAILABLE_SCENE_MODES, sceneModes, 1);

    // Effect modes
    uint8_t effectModes[] = {ANDROID_CONTROL_EFFECT_MODE_OFF};
    meta.update(ANDROID_CONTROL_AVAILABLE_EFFECTS, effectModes, 1);

    // Video stabilization
    uint8_t vstabModes[] = {ANDROID_CONTROL_VIDEO_STABILIZATION_MODE_OFF};
    meta.update(ANDROID_CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES, vstabModes, 1);

    // AE target FPS ranges
    int32_t fpsRanges[] = {15, 30, 30, 30};
    meta.update(ANDROID_CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES, fpsRanges, 4);

    // Antibanding
    uint8_t abModes[] = {
        ANDROID_CONTROL_AE_ANTIBANDING_MODE_OFF,
        ANDROID_CONTROL_AE_ANTIBANDING_MODE_AUTO
    };
    meta.update(ANDROID_CONTROL_AE_AVAILABLE_ANTIBANDING_MODES, abModes, 2);

    // Timestamp source
    uint8_t tsSource = ANDROID_SENSOR_INFO_TIMESTAMP_SOURCE_REALTIME;
    meta.update(ANDROID_SENSOR_INFO_TIMESTAMP_SOURCE, &tsSource, 1);

    return meta;
}

static android::CameraMetadata buildPixel7FrontCharacteristics() {
    android::CameraMetadata meta = buildPixel7MainCharacteristics();


    uint8_t facing = ANDROID_LENS_FACING_FRONT;
    meta.update(ANDROID_LENS_FACING, &facing, 1);

    float aperture = 2.2f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_APERTURES, &aperture, 1);

    float focalLen = 4.0f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_FOCAL_LENGTHS, &focalLen, 1);


    // Keep front sensor dimensions identical to the back camera. Some clients
    // (OpenCamera, Camera2 stock viewers) compute the preview/crop window
    // from ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE; if that array doesn't match
    // ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE the client falls back to a tiny
    // safe-default preview (we observed 640x480 landscape rectangle in the
    // middle of the screen instead of full-fit selfie). buildPixel7Main…
    // already published 4080x3072 for both arrays — let the override match.
    int32_t pixelArray[2] = {4080, 3072};
    meta.update(ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE, pixelArray, 2);
    int32_t activeArray[4] = {0, 0, 4080, 3072};
    meta.update(ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE, activeArray, 4);

    // SENSOR_ORIENTATION matches the back camera (90°). On a real Pixel the
    // front sensor reports 270°, but using 90° here lets the HAL deliver
    // identically-pre-rotated buffers for both cameras and produces the same
    // upright preview/capture behavior in both orientations. Front camera
    // identity (LENS_FACING_FRONT, focal length) is preserved.
    int32_t frontOrientation = 90;
    meta.update(ANDROID_SENSOR_ORIENTATION, &frontOrientation, 1);

    return meta;
}


// ---- Pixel 4 (flame) camera characteristics ----
// Pixel 4 rear: Sony IMX363, 12.2 MP, f/1.7, 4.44mm focal, 1/2.55" sensor
static android::CameraMetadata buildPixel4MainCharacteristics() {
    android::CameraMetadata meta;

    uint8_t facing = ANDROID_LENS_FACING_BACK;
    meta.update(ANDROID_LENS_FACING, &facing, 1);

    // Sony IMX363: 1/2.55" sensor = ~5.64 x 4.23 mm
    float physSize[2] = {5.64f, 4.23f};
    meta.update(ANDROID_SENSOR_INFO_PHYSICAL_SIZE, physSize, 2);

    // 12.2 MP = 4032x3024
    int32_t pixelArray[2] = {4032, 3024};
    meta.update(ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE, pixelArray, 2);

    int32_t activeArray[4] = {0, 0, 4032, 3024};
    meta.update(ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE, activeArray, 4);

    float aperture = 1.7f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_APERTURES, &aperture, 1);

    float focalLen = 4.44f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_FOCAL_LENGTHS, &focalLen, 1);

    int32_t isoRange[2] = {50, 6400};
    meta.update(ANDROID_SENSOR_INFO_SENSITIVITY_RANGE, isoRange, 2);

    int64_t expRange[2] = {13'000LL, 1'000'000'000LL};
    meta.update(ANDROID_SENSOR_INFO_EXPOSURE_TIME_RANGE, expRange, 2);

    int64_t frameDurRange[2] = {33'333'333LL, 200'000'000LL};
    meta.update(ANDROID_SENSOR_INFO_MAX_FRAME_DURATION, &frameDurRange[1], 1);

    uint8_t cfa = ANDROID_SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGGB;
    meta.update(ANDROID_SENSOR_INFO_COLOR_FILTER_ARRANGEMENT, &cfa, 1);

    uint8_t hwLevel = ANDROID_INFO_SUPPORTED_HARDWARE_LEVEL_FULL;
    meta.update(ANDROID_INFO_SUPPORTED_HARDWARE_LEVEL, &hwLevel, 1);

    uint8_t caps[] = {
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING,
        ANDROID_REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS,
    };
    meta.update(ANDROID_REQUEST_AVAILABLE_CAPABILITIES, caps, sizeof(caps));

    uint8_t pipelineDepth = 4;
    meta.update(ANDROID_REQUEST_PIPELINE_MAX_DEPTH, &pipelineDepth, 1);

    std::vector<int32_t> streamConfigs;
    auto addConfig = [&](int32_t fmt, int32_t w, int32_t h) {
        streamConfigs.push_back(fmt);
        streamConfigs.push_back(w);
        streamConfigs.push_back(h);
        streamConfigs.push_back(ANDROID_SCALER_AVAILABLE_STREAM_CONFIGURATIONS_OUTPUT);
    };

    addConfig(HAL_PIXEL_FORMAT_BLOB, 4032, 3024);
    addConfig(HAL_PIXEL_FORMAT_BLOB, 1920, 1080);

    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 4032, 3024);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 1920, 1080);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 1280, 720);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 640, 480);
    addConfig(HAL_PIXEL_FORMAT_YCbCr_420_888, 320, 240);

    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1920, 1080);
    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1280, 720);
    addConfig(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 640, 480);

    meta.update(ANDROID_SCALER_AVAILABLE_STREAM_CONFIGURATIONS,
                streamConfigs.data(), streamConfigs.size());

    std::vector<int64_t> minDurations;
    auto addDur = [&](int32_t fmt, int32_t w, int32_t h, int64_t durNs) {
        minDurations.push_back(fmt);
        minDurations.push_back(w);
        minDurations.push_back(h);
        minDurations.push_back(durNs);
    };
    constexpr int64_t k30fps = 33'333'333LL;
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 4032, 3024, k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 1280, 720,  k30fps);
    addDur(HAL_PIXEL_FORMAT_YCbCr_420_888, 640, 480,   k30fps);
    addDur(HAL_PIXEL_FORMAT_BLOB, 4032, 3024, k30fps);
    addDur(HAL_PIXEL_FORMAT_BLOB, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1920, 1080, k30fps);
    addDur(HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED, 1280, 720,  k30fps);

    meta.update(ANDROID_SCALER_AVAILABLE_MIN_FRAME_DURATIONS,
                minDurations.data(), minDurations.size());

    uint8_t aeModes[] = {ANDROID_CONTROL_AE_MODE_OFF, ANDROID_CONTROL_AE_MODE_ON};
    meta.update(ANDROID_CONTROL_AE_AVAILABLE_MODES, aeModes, 2);

    uint8_t afModes[] = {
        ANDROID_CONTROL_AF_MODE_OFF,
        ANDROID_CONTROL_AF_MODE_AUTO,
        ANDROID_CONTROL_AF_MODE_CONTINUOUS_PICTURE,
        ANDROID_CONTROL_AF_MODE_CONTINUOUS_VIDEO,
    };
    meta.update(ANDROID_CONTROL_AF_AVAILABLE_MODES, afModes, 4);

    uint8_t awbModes[] = {
        ANDROID_CONTROL_AWB_MODE_OFF,
        ANDROID_CONTROL_AWB_MODE_AUTO,
        ANDROID_CONTROL_AWB_MODE_DAYLIGHT,
        ANDROID_CONTROL_AWB_MODE_CLOUDY_DAYLIGHT,
    };
    meta.update(ANDROID_CONTROL_AWB_AVAILABLE_MODES, awbModes, 4);

    uint8_t oisModes[] = {
        ANDROID_LENS_OPTICAL_STABILIZATION_MODE_OFF,
        ANDROID_LENS_OPTICAL_STABILIZATION_MODE_ON
    };
    meta.update(ANDROID_LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION, oisModes, 2);

    uint8_t nrModes[] = {
        ANDROID_NOISE_REDUCTION_MODE_OFF,
        ANDROID_NOISE_REDUCTION_MODE_FAST,
        ANDROID_NOISE_REDUCTION_MODE_HIGH_QUALITY,
    };
    meta.update(ANDROID_NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, nrModes, 3);

    float focusRange[2] = {0.0f, 10.0f};
    meta.update(ANDROID_LENS_INFO_MINIMUM_FOCUS_DISTANCE, &focusRange[1], 1);

    uint8_t croppingType = ANDROID_SCALER_CROPPING_TYPE_CENTER_ONLY;
    meta.update(ANDROID_SCALER_CROPPING_TYPE, &croppingType, 1);

    float maxZoom = 8.0f;
    meta.update(ANDROID_SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, &maxZoom, 1);

    // Pixel 4 sensor orientation matches Pixel 7 (rear=90, front=270).
    int32_t pixel4Orientation = 90;
    meta.update(ANDROID_SENSOR_ORIENTATION, &pixel4Orientation, 1);

    return meta;
}

// Pixel 4 front: 8 MP, f/2.0, 3.0mm focal
static android::CameraMetadata buildPixel4FrontCharacteristics() {
    android::CameraMetadata meta = buildPixel4MainCharacteristics();

    uint8_t facing = ANDROID_LENS_FACING_FRONT;
    meta.update(ANDROID_LENS_FACING, &facing, 1);

    float aperture = 2.0f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_APERTURES, &aperture, 1);

    float focalLen = 3.0f;
    meta.update(ANDROID_LENS_INFO_AVAILABLE_FOCAL_LENGTHS, &focalLen, 1);

    // 8 MP = 3264x2448
    int32_t pixelArray[2] = {3264, 2448};
    meta.update(ANDROID_SENSOR_INFO_PIXEL_ARRAY_SIZE, pixelArray, 2);

    int32_t activeArray[4] = {0, 0, 3264, 2448};
    meta.update(ANDROID_SENSOR_INFO_ACTIVE_ARRAY_SIZE, activeArray, 4);

    // Match Pixel 7 front: use 90° for HAL-uniform pre-rotation.
    int32_t pixel4FrontOrientation = 90;
    meta.update(ANDROID_SENSOR_ORIENTATION, &pixel4FrontOrientation, 1);

    return meta;
}

// Determine device model at runtime (or default to Pixel 7)
static std::string getDeviceCodename() {
#ifdef __ANDROID__
    char prop[92] = {};
    if (__system_property_get("ro.product.device", prop) > 0) return prop;
#endif
    return "panther"; // default
}


FakeCameraDevice::FakeCameraDevice(const std::string& cameraId,
                                   const std::string& videoFilePath,
                                   const std::string& deviceSerial,
                                   const std::string& deviceModel)
    : cameraId_(cameraId), videoFilePath_(videoFilePath)
    , deviceSerial_(deviceSerial), deviceModel_(deviceModel)
{
    std::string codename = getDeviceCodename();
    bool isPixel4 = (codename == "flame" || codename == "coral");

    if (cameraId == "0") {
        characteristics_ = isPixel4 ? buildPixel4MainCharacteristics()
                                   : buildPixel7MainCharacteristics();
    } else {
        characteristics_ = isPixel4 ? buildPixel4FrontCharacteristics()
                                   : buildPixel7FrontCharacteristics();
    }
}

ndk::ScopedAStatus FakeCameraDevice::getCameraCharacteristics(
    ::aidl::android::hardware::camera::device::CameraMetadata* chars)
{
#ifdef FAKE_HAL_TEST_BUILD
    // In test builds, CameraMetadata is aliased to android::CameraMetadata (mock).
    // Copy the characteristics directly since the mock doesn't support serialization.
    *chars = characteristics_;
#else
    camera_metadata_t* raw = characteristics_.release();
    if (raw) {
        size_t sz = get_camera_metadata_size(raw);
        chars->metadata.assign((uint8_t*)raw, (uint8_t*)raw + sz);
        characteristics_.acquire(raw);
    }
#endif
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDevice::getPhysicalCameraCharacteristics(
    const std::string&,
    ::aidl::android::hardware::camera::device::CameraMetadata*)
{
    return ndk::ScopedAStatus::fromServiceSpecificError(
        static_cast<int32_t>(::aidl::android::hardware::camera::common::Status::ILLEGAL_ARGUMENT));
}

ndk::ScopedAStatus FakeCameraDevice::getResourceCost(CameraResourceCost* cost) {
    cost->resourceCost = 50;
    cost->conflictingDevices.clear();
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDevice::isStreamCombinationSupported(
    const StreamConfiguration&, bool* support)
{
    *support = true;
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDevice::open(
    const std::shared_ptr<ICameraDeviceCallback>& callback,
    std::shared_ptr<ICameraDeviceSession>* session)
{
    FHAL_I("FakeCameraDevice[%s]: open()", cameraId_.c_str());
    *session = ndk::SharedRefBase::make<FakeCameraDeviceSession>(
        cameraId_, videoFilePath_, callback, deviceSerial_, deviceModel_);
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDevice::openInjectionSession(
    const std::shared_ptr<ICameraDeviceCallback>&,
    std::shared_ptr<ICameraInjectionSession>*)
{
    return ndk::ScopedAStatus::fromServiceSpecificError(
        static_cast<int32_t>(::aidl::android::hardware::camera::common::Status::OPERATION_NOT_SUPPORTED));
}

ndk::ScopedAStatus FakeCameraDevice::setTorchMode(bool) {
    return ndk::ScopedAStatus::ok();
}
ndk::ScopedAStatus FakeCameraDevice::turnOnTorchWithStrengthLevel(int32_t) {
    return ndk::ScopedAStatus::ok();
}
ndk::ScopedAStatus FakeCameraDevice::getTorchStrengthLevel(int32_t* lvl) {
    *lvl = 0;
    return ndk::ScopedAStatus::ok();
}


FakeCameraDeviceSession::FakeCameraDeviceSession(
    const std::string& cameraId,
    const std::string& videoFilePath,
    const std::shared_ptr<ICameraDeviceCallback>& callback,
    const std::string& deviceSerial,
    const std::string& deviceModel)
    : cameraId_(cameraId)
    , videoFilePath_(videoFilePath)
    , callback_(callback)
{
    RuntimeConfig runtimeCfg = readRuntimeConfig();
    qrCleanMode_ = runtimeCfg.qrCleanMode;
    backRotate180_ = runtimeCfg.backRotate180;
    frontRotate180_ = runtimeCfg.frontRotate180;
    frontMirror_ = runtimeCfg.frontMirror;
    chromeFrontRotate180_ = runtimeCfg.chromeFrontRotate180;
    chromeFrontMirror_ = runtimeCfg.chromeFrontMirror;
    gyroEnabled_ = runtimeCfg.gyroEnabled;
    gyroStrength_ = runtimeCfg.gyroStrength;
    previewRotation_ = runtimeCfg.previewRotation;
    previewMirrorH_ = runtimeCfg.previewMirrorH;
    previewMirrorV_ = runtimeCfg.previewMirrorV;

    videoReader_ = std::make_unique<VideoFrameReader>(videoFilePath);
    {
        char streamCamProp[PROP_VALUE_MAX] = {0};
        __system_property_get("fakehal.stream.camera", streamCamProp);
        std::string streamCam(streamCamProp);
        bool enableStream = false;
        if (streamCam == "both") {
            enableStream = true;
        } else if (streamCam == "back") {
            enableStream = (cameraId == "0");
        } else {
            enableStream = (cameraId == "1");
        }
        videoReader_->setStreamEnabled(enableStream);
        FHAL_I("FakeCameraDeviceSession[%s]: stream %s (fakehal.stream.camera=%s)",
               cameraId.c_str(), enableStream ? "ENABLED" : "DISABLED",
               streamCam.empty() ? "front(default)" : streamCam.c_str());
    }
    // Tell the reader our advertised sensor orientation so it can pre-rotate
    // each frame and counteract the camera framework's later rotation.
    // We use 90° for both cameras (see characteristics builders): this lets
    // the HAL produce identically-pre-rotated buffers for back and front so
    // both preview and capture come out upright in portrait UI.
    sensorOrientation_ = runtimeCfg.sensorOrientation;
    preNormalized_ = runtimeCfg.preNormalized;
    // (Previously: videoReader_->setSensorOrientation(...). Removed — the
    // reader picks up source orientation directly from the decoded frame and
    // applies a portrait↔landscape pre-rotation when it differs from the
    // requested stream orientation, which is what we actually need.)
    FHAL_I("sensorOrientation from config: %d, preNormalized: %d",
           sensorOrientation_, runtimeCfg.preNormalized ? 1 : 0);
    metaRand_    = std::make_unique<MetadataRandomizer>();
    noiseOverlay_= std::make_unique<NoiseOverlay>(3.0f, 0.35f, 0xDEADBEEF42ULL);
    metaRand_->setCameraProfile(cameraId);
    metaRand_->setIsoRange((float)runtimeCfg.isoMin, (float)runtimeCfg.isoMax);
    noiseOverlay_->setSensorFingerprint(deviceSerial, deviceModel, cameraId);
    noiseOverlay_->setStrength(qrCleanMode_ ? 0.0f : runtimeCfg.noiseLevel);
    FHAL_I("FakeCameraDeviceSession[%s]: FPN fingerprint + camera profile set",
          cameraId_.c_str());
    FHAL_I("FakeCameraDeviceSession[%s]: runtime config noise=%.2f iso=%d-%d qrClean=%d",
          cameraId_.c_str(), runtimeCfg.noiseLevel, runtimeCfg.isoMin,
          runtimeCfg.isoMax, qrCleanMode_ ? 1 : 0);
    FHAL_I("FakeCameraDeviceSession[%s]: transforms backRotate180=%d frontRotate180=%d frontMirror=%d chromeFrontRotate180=%d chromeFrontMirror=%d gyro=%d",
          cameraId_.c_str(), backRotate180_ ? 1 : 0, frontRotate180_ ? 1 : 0,
          frontMirror_ ? 1 : 0, chromeFrontRotate180_ ? 1 : 0,
          chromeFrontMirror_ ? 1 : 0, gyroEnabled_ ? 1 : 0);
    gyroWarp_    = std::make_unique<GyroWarp>(
        "/sys/bus/iio/devices/iio:device0",
        cameraId == "1" ? 90 : 40,
        cameraId == "1" ? 12.0f : 20.0f,
        gyroStrength_);
    jpegEncoder_ = std::make_unique<JpegEncoder>();
    tsSync_      = std::make_unique<TimestampSync>();

    if (gyroEnabled_) gyroWarp_->start();

    // #region agent log — H6/H7: gyro startup state
    {
        char buf[256];
        snprintf(buf, sizeof(buf),
                 "{\"cam\":\"%s\",\"gyroEnabled\":%s,\"gyroStarted\":%s,"
                 "\"sensorOri\":%d,\"noiseLevel\":%.2f,\"gyroStrength\":%.3f}",
                 cameraId.c_str(),
                 gyroEnabled_ ? "true" : "false",
                 gyroEnabled_ ? "true" : "false",
                 sensorOrientation_,
                 runtimeCfg.noiseLevel,
                 gyroStrength_);
        dbgLog("H6_H7", "FakeCameraDevice.cpp:ctor", "session_init", buf);
    }
    // #endregion

    workerRunning_ = true;
    workerThread_ = std::thread(&FakeCameraDeviceSession::workerLoop, this);

    FHAL_I("FakeCameraDeviceSession[%s]: created", cameraId_.c_str());
}

FakeCameraDeviceSession::~FakeCameraDeviceSession() {
    close();
}

ndk::ScopedAStatus FakeCameraDeviceSession::close() {
    bool expected = true;
    if (!workerRunning_.compare_exchange_strong(expected, false)) {
        // Already closed or closing
        return ndk::ScopedAStatus::ok();
    }
    queueCv_.notify_all();
    if (workerThread_.joinable()) workerThread_.join();
    if (gyroWarp_) gyroWarp_->stop();
    // Free all cached buffer handles
    {
        std::lock_guard<std::mutex> lk(bufferCacheMutex_);
        for (auto& kv : bufferCache_) {
            native_handle_close(kv.second);
            native_handle_delete(kv.second);
        }
        bufferCache_.clear();
    }
    if (videoReader_) videoReader_->close();
    FHAL_I("FakeCameraDeviceSession[%s]: closed", cameraId_.c_str());
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::configureStreams(
    const StreamConfiguration& config,
    std::vector<HalStream>* halStreams)
{
    activeStreams_ = config.streams;
    halStreams->clear();

    clientPackage_ = detectForegroundPackage();
    chromeWebClient_ = isChromeWebClient(clientPackage_);
    FHAL_I("FakeCameraDeviceSession[%s]: foregroundPackage=%s chromeWebClient=%d",
          cameraId_.c_str(),
          clientPackage_.empty() ? "<unknown>" : clientPackage_.c_str(),
          chromeWebClient_ ? 1 : 0);


    int targetW = 640, targetH = 480;
    for (const auto& s : config.streams) {
        int fmt = fmtToInt(s.format);
        FHAL_I("configureStreams: stream id=%d fmt=0x%x size=%dx%d", s.id, fmt, s.width, s.height);
        if (fmt == HAL_PIXEL_FORMAT_YCbCr_420_888 ||
            fmt == HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED ||
            fmt == HAL_PIXEL_FORMAT_BLOB) {
            if (s.width * s.height > targetW * targetH) {
                targetW = s.width;
                targetH = s.height;
            }
        }
    }

    // Always open at native resolution for maximum quality;
    // fillYUVBuffer resizes from fullResBuf_ to the requested preview size.
    if (!videoReader_->isOpen()) {
        if (!videoReader_->open(0, 0)) {
            FHAL_E("Cannot open video: video file not found");
        }
    }
    int nativeW = videoReader_->width();
    int nativeH = videoReader_->height();
    if (nativeW <= 0) nativeW = targetW;
    if (nativeH <= 0) nativeH = targetH;
    FHAL_I("configureStreams: native video=%dx%d, preview target=%dx%d", nativeW, nativeH, targetW, targetH);

    // Preview buffer at max requested stream size
    size_t nv21Size = (size_t)(targetW * targetH * 3 / 2);
    yuvBuf_.resize(nv21Size, 128);
    tmpBuf_.resize(nv21Size);


    for (const auto& s : config.streams) {
        HalStream hs;
        hs.id = s.id;
        hs.overrideFormat = s.format;

        if (fmtToInt(s.format) == HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED) {
#ifndef FAKE_HAL_TEST_BUILD
            hs.overrideFormat = static_cast<PixelFormat>(HAL_PIXEL_FORMAT_YCbCr_420_888);
#else
            hs.overrideFormat = HAL_PIXEL_FORMAT_YCbCr_420_888;
#endif
        }

#ifndef FAKE_HAL_TEST_BUILD
        hs.producerUsage = static_cast<BufferUsage>(
            static_cast<int64_t>(GRALLOC1_PRODUCER_USAGE_CAMERA | GRALLOC1_PRODUCER_USAGE_CPU_WRITE_OFTEN));
        hs.consumerUsage = static_cast<BufferUsage>(0);
#else
        hs.producerUsage = static_cast<int64_t>(
            GRALLOC1_PRODUCER_USAGE_CAMERA | GRALLOC1_PRODUCER_USAGE_CPU_WRITE_OFTEN);
        hs.consumerUsage = 0;
#endif
        hs.maxBuffers    = 4;
        hs.supportOffline = false;

        halStreams->push_back(hs);
    }

    FHAL_I("FakeCameraDeviceSession[%s]: configureStreams -> %zu streams, target %dx%d",
          cameraId_.c_str(), config.streams.size(), targetW, targetH);
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::processCaptureRequest(
    const std::vector<CaptureRequest>& requests,
    const std::vector<BufferCache>&,
    int32_t* numRequestProcessed)
{
    if (flushing_) {
        *numRequestProcessed = 0;
        return ndk::ScopedAStatus::ok();
    }

    {
        std::lock_guard<std::mutex> lk(queueMutex_);
        for (auto& req : const_cast<std::vector<CaptureRequest>&>(requests)) {
            struct timespec ts;
            clock_gettime(CLOCK_MONOTONIC, &ts);
            int64_t nowNs = (int64_t)ts.tv_sec * 1'000'000'000LL + ts.tv_nsec;

            requestQueue_.push({std::move(req), nowNs});
        }
    }
    queueCv_.notify_one();
    *numRequestProcessed = (int32_t)requests.size();
    return ndk::ScopedAStatus::ok();
}

void FakeCameraDeviceSession::workerLoop() {
    while (workerRunning_) {
        PendingRequest req;
        {
            std::unique_lock<std::mutex> lk(queueMutex_);
            queueCv_.wait(lk, [this] {
                return !requestQueue_.empty() || !workerRunning_;
            });
            if (!workerRunning_) break;
            req = std::move(requestQueue_.front());
            requestQueue_.pop();
        }
        processOneRequest(req);
    }
}



static void resizeNV21(const uint8_t* src, int srcW, int srcH,
                       uint8_t* dst, int dstW, int dstH) {
    // Bilinear interpolation for Y plane
    for (int y = 0; y < dstH; y++) {
        float srcY = (y + 0.5f) * srcH / dstH - 0.5f;
        int y0 = (int)srcY;
        int y1 = y0 + 1;
        float fy = srcY - y0;
        if (y0 < 0) { y0 = 0; fy = 0; }
        if (y1 >= srcH) y1 = srcH - 1;
        for (int x = 0; x < dstW; x++) {
            float srcX = (x + 0.5f) * srcW / dstW - 0.5f;
            int x0 = (int)srcX;
            int x1 = x0 + 1;
            float fx = srcX - x0;
            if (x0 < 0) { x0 = 0; fx = 0; }
            if (x1 >= srcW) x1 = srcW - 1;
            float v = (1-fy)*((1-fx)*src[y0*srcW+x0] + fx*src[y0*srcW+x1])
                    +    fy *((1-fx)*src[y1*srcW+x0] + fx*src[y1*srcW+x1]);
            dst[y * dstW + x] = (uint8_t)(v + 0.5f);
        }
    }
    // Bilinear interpolation for UV plane (NV21: VU interleaved)
    const uint8_t* srcUV = src + srcW * srcH;
    uint8_t* dstUV = dst + dstW * dstH;
    int srcUVH = srcH / 2, dstUVH = dstH / 2;
    int srcUVW = srcW / 2, dstUVW = dstW / 2;
    for (int y = 0; y < dstUVH; y++) {
        float srcY = (y + 0.5f) * srcUVH / dstUVH - 0.5f;
        int y0 = (int)srcY;
        int y1 = y0 + 1;
        float fy = srcY - y0;
        if (y0 < 0) { y0 = 0; fy = 0; }
        if (y1 >= srcUVH) y1 = srcUVH - 1;
        for (int x = 0; x < dstUVW; x++) {
            float srcX = (x + 0.5f) * srcUVW / dstUVW - 0.5f;
            int x0 = (int)srcX;
            int x1 = x0 + 1;
            float fx = srcX - x0;
            if (x0 < 0) { x0 = 0; fx = 0; }
            if (x1 >= srcUVW) x1 = srcUVW - 1;
            // V channel
            float v = (1-fy)*((1-fx)*srcUV[y0*srcW+x0*2] + fx*srcUV[y0*srcW+x1*2])
                    +    fy *((1-fx)*srcUV[y1*srcW+x0*2] + fx*srcUV[y1*srcW+x1*2]);
            dstUV[y * dstW + x * 2] = (uint8_t)(v + 0.5f);
            // U channel
            float u = (1-fy)*((1-fx)*srcUV[y0*srcW+x0*2+1] + fx*srcUV[y0*srcW+x1*2+1])
                    +    fy *((1-fx)*srcUV[y1*srcW+x0*2+1] + fx*srcUV[y1*srcW+x1*2+1]);
            dstUV[y * dstW + x * 2 + 1] = (uint8_t)(u + 0.5f);
        }
    }
}

static void rotateNV21_180(uint8_t* data, int w, int h) {
    int ySize = w * h;
    for (int i = 0; i < ySize / 2; ++i)
        std::swap(data[i], data[ySize - 1 - i]);
    uint8_t* uv = data + ySize;
    int uvSize = ySize / 2;
    for (int i = 0; i < uvSize / 2; i += 2) {
        std::swap(uv[i],     uv[uvSize - 2 - i]);
        std::swap(uv[i + 1], uv[uvSize - 1 - i]);
    }
}

static void mirrorNV21Horizontal(uint8_t* data, int w, int h) {
    for (int y = 0; y < h; ++y) {
        uint8_t* row = data + y * w;
        for (int x = 0; x < w / 2; ++x) {
            std::swap(row[x], row[w - 1 - x]);
        }
    }

    uint8_t* uv = data + w * h;
    int chromaRows = h / 2;
    int pairCount = w / 2;
    for (int y = 0; y < chromaRows; ++y) {
        uint8_t* row = uv + y * w;
        for (int pair = 0; pair < pairCount / 2; ++pair) {
            int left = pair * 2;
            int right = (pairCount - 1 - pair) * 2;
            std::swap(row[left], row[right]);
            std::swap(row[left + 1], row[right + 1]);
        }
    }
}

static void mirrorNV21Vertical(uint8_t* data, int w, int h) {
    int ySize = w * h;
    for (int y = 0; y < h / 2; ++y) {
        uint8_t* top = data + y * w;
        uint8_t* bottom = data + (h - 1 - y) * w;
        for (int x = 0; x < w; ++x) std::swap(top[x], bottom[x]);
    }

    uint8_t* uv = data + ySize;
    int chromaRows = h / 2;
    for (int y = 0; y < chromaRows / 2; ++y) {
        uint8_t* top = uv + y * w;
        uint8_t* bottom = uv + (chromaRows - 1 - y) * w;
        for (int x = 0; x < w; ++x) std::swap(top[x], bottom[x]);
    }
}

static void rotateNV21_90Or270Fit(uint8_t* data, uint8_t* tmp, int w, int h, bool cw) {
    memset(tmp, 0, (size_t)(w * h));
    memset(tmp + w * h, 128, (size_t)(w * h / 2));

    const float rotW = (float)h;
    const float rotH = (float)w;
    const float scale = std::min((float)w / rotW, (float)h / rotH);
    const float offX = ((float)w - rotW * scale) * 0.5f;
    const float offY = ((float)h - rotH * scale) * 0.5f;

    for (int y = 0; y < h; ++y) {
        float ry = ((float)y - offY) / scale;
        if (ry < 0.0f || ry >= rotH) continue;
        for (int x = 0; x < w; ++x) {
            float rx = ((float)x - offX) / scale;
            if (rx < 0.0f || rx >= rotW) continue;
            int sx = cw ? (int)ry : (w - 1 - (int)ry);
            int sy = cw ? (h - 1 - (int)rx) : (int)rx;
            if (sx >= 0 && sx < w && sy >= 0 && sy < h) {
                tmp[y * w + x] = data[sy * w + sx];
            }
        }
    }

    const uint8_t* srcUV = data + w * h;
    uint8_t* dstUV = tmp + w * h;
    int cwSrc = w / 2;
    int chSrc = h / 2;
    float rotCW = (float)chSrc;
    float rotCH = (float)cwSrc;
    float cScale = std::min((float)cwSrc / rotCW, (float)chSrc / rotCH);
    float cOffX = ((float)cwSrc - rotCW * cScale) * 0.5f;
    float cOffY = ((float)chSrc - rotCH * cScale) * 0.5f;
    for (int y = 0; y < chSrc; ++y) {
        float ry = ((float)y - cOffY) / cScale;
        if (ry < 0.0f || ry >= rotCH) continue;
        for (int x = 0; x < cwSrc; ++x) {
            float rx = ((float)x - cOffX) / cScale;
            if (rx < 0.0f || rx >= rotCW) continue;
            int sx = cw ? (int)ry : (cwSrc - 1 - (int)ry);
            int sy = cw ? (chSrc - 1 - (int)rx) : (int)rx;
            if (sx >= 0 && sx < cwSrc && sy >= 0 && sy < chSrc) {
                int dst = y * w + x * 2;
                int src = sy * w + sx * 2;
                dstUV[dst] = srcUV[src];
                dstUV[dst + 1] = srcUV[src + 1];
            }
        }
    }

    memcpy(data, tmp, (size_t)(w * h * 3 / 2));
}

static std::string detectForegroundPackage() {
#ifdef FAKE_HAL_TEST_BUILD
    return "";
#else
    // Only consider lines describing the actually-focused activity. Earlier
    // versions grepped the entire `dumpsys window` output and would match
    // Chrome from RecentTasks / saved instance state even when another app was
    // foreground, which made the BT.601/Chrome-aware code paths take the wrong
    // branch (notably: sending BT.709 chroma to OpenCamera and getting a blue
    // cast). Restricting to mCurrentFocus / mFocusedApp / mResumedActivity is
    // both fast and unambiguous.
    FILE* pipe = popen(
        "/system/bin/dumpsys window 2>/dev/null | "
        "grep -E 'mCurrentFocus|mFocusedApp|mResumedActivity' | head -5",
        "r");
    if (!pipe) return "";

    std::string focused;
    char buffer[512];
    while (fgets(buffer, sizeof(buffer), pipe)) {
        focused += buffer;
        if (focused.size() > 4096) break;
    }
    pclose(pipe);

    const char* packages[] = {
        "com.android.chrome",
        "org.chromium.chrome",
        "com.chrome.beta",
        "com.chrome.dev",
        "net.sourceforge.opencamera",
    };
    for (const char* packageName : packages) {
        if (focused.find(packageName) != std::string::npos) {
            return packageName;
        }
    }
    return "";
#endif
}

static bool isChromeWebClient(const std::string& packageName) {
    return packageName.find("chrome") != std::string::npos ||
           packageName.find("chromium") != std::string::npos;
}

void FakeCameraDeviceSession::fillYUVBuffer(uint32_t width, uint32_t height) {
    size_t needed = (size_t)(width * height * 3 / 2);
    if (yuvBuf_.size() != needed) yuvBuf_.resize(needed, 128);
    if (tmpBuf_.size() != needed) tmpBuf_.resize(needed);
    // FIX #1: Re-detect foreground package on every frame so that switching
    // between Chrome and OpenCamera (or having Chrome in Recent Tasks) does
    // not lock the wrong UV-swap / BT.601 path for the entire session.
    {
        const std::string pkg = detectForegroundPackage();
        const bool nowChrome = isChromeWebClient(pkg);
        if (nowChrome != chromeWebClient_) {
            chromeWebClient_ = nowChrome;
            clientPackage_ = pkg;
            FHAL_I("fillYUVBuffer[%s]: foreground changed -> %s chromeWebClient=%d",
                   cameraId_.c_str(),
                   pkg.empty() ? "<unknown>" : pkg.c_str(),
                   chromeWebClient_ ? 1 : 0);
        }
    }

    // Optional solid-colour test pattern (kept for color-pipeline diagnostics).
    // Enable with: setprop fakehal.testpattern 1 -- writes RGB(100,150,200)
    // worth of BT.709 limited YCbCr (Y=139, Cb=155, Cr=104) to the frame.
    char propBuf[PROP_VALUE_MAX] = {0};
    __system_property_get("fakehal.testpattern", propBuf);
    if (propBuf[0] == '1') {
        memset(yuvBuf_.data(), 139, (size_t)width * height);
        uint8_t* uv = yuvBuf_.data() + (size_t)width * height;
        for (size_t i = 0; i < (size_t)width * height / 2; i += 2) {
            uv[i + 0] = 104; // V (NV21 layout)
            uv[i + 1] = 155; // U
        }
        return;
    }

    int vw = videoReader_->width();
    int vh = videoReader_->height();
    bool needsResize = (vw != (int)width || vh != (int)height);

    if (needsResize) {
        size_t fullSize = (size_t)(vw * vh * 3 / 2);
        if (fullResBuf_.size() != fullSize) fullResBuf_.resize(fullSize, 128);
        if (!videoReader_->nextFrame(fullResBuf_.data())) {
            FHAL_W("FakeCameraDeviceSession: video reader returned false, using last frame");
            videoReader_->lastFrame(fullResBuf_.data());
        }
        resizeNV21(fullResBuf_.data(), vw, vh, yuvBuf_.data(), (int)width, (int)height);
    } else {
        fullResBuf_.clear();
        if (!videoReader_->nextFrame(yuvBuf_.data())) {
            FHAL_W("FakeCameraDeviceSession: video reader returned false, using last frame");
            videoReader_->lastFrame(yuvBuf_.data());
        }
    }

    const bool fromStream = videoReader_->lastFrameFromStream();

    if (!fromStream) {
        // Deblock only for MP4 frames (h264 artifacts); live stream is raw.
        const int bsz = 8;
        uint8_t* Y = yuvBuf_.data();
        for (uint32_t y = 0; y < height; ++y) {
            uint8_t* row = Y + y * width;
            for (uint32_t bx = bsz; bx < width; bx += bsz) {
                if (bx >= 2 && bx + 1 < width) {
                    int a = row[bx-2], b = row[bx-1], c = row[bx], d = row[bx+1];
                    row[bx-1] = (uint8_t)((a + 2*b + c + 2) >> 2);
                    row[bx]   = (uint8_t)((b + 2*c + d + 2) >> 2);
                }
            }
        }
        for (uint32_t by = bsz; by < height; by += bsz) {
            if (by < 2 || by + 1 >= height) continue;
            uint8_t* r0 = Y + (by-2)*width;
            uint8_t* r1 = Y + (by-1)*width;
            uint8_t* r2 = Y + by*width;
            uint8_t* r3 = Y + (by+1)*width;
            for (uint32_t x = 0; x < width; ++x) {
                int a = r0[x], b = r1[x], c = r2[x], d = r3[x];
                r1[x] = (uint8_t)((a + 2*b + c + 2) >> 2);
                r2[x] = (uint8_t)((b + 2*c + d + 2) >> 2);
            }
        }
        uint8_t* UV = yuvBuf_.data() + width * height;
        uint32_t uvW = width, uvH = height / 2;
        const int cbsz = 8;
        for (uint32_t y = 0; y < uvH; ++y) {
            uint8_t* row = UV + y * uvW;
            for (uint32_t bx = cbsz; bx + 1 < uvW; bx += cbsz) {
                if (bx >= 2) {
                    int a = row[bx-2], b = row[bx-1], c = row[bx], d = row[bx+1];
                    row[bx-1] = (uint8_t)((a + 2*b + c + 2) >> 2);
                    row[bx]   = (uint8_t)((b + 2*c + d + 2) >> 2);
                }
            }
        }
    }

    RuntimeConfig liveCfg = readRuntimeConfig();
    qrCleanMode_ = liveCfg.qrCleanMode;
    backRotate180_ = liveCfg.backRotate180;
    frontRotate180_ = liveCfg.frontRotate180;
    frontMirror_ = liveCfg.frontMirror;
    chromeFrontRotate180_ = liveCfg.chromeFrontRotate180;
    chromeFrontMirror_ = liveCfg.chromeFrontMirror;
    previewRotation_ = liveCfg.previewRotation;
    previewMirrorH_ = liveCfg.previewMirrorH;
    previewMirrorV_ = liveCfg.previewMirrorV;
    gyroStrength_ = liveCfg.gyroStrength;
    if (gyroWarp_) gyroWarp_->setStrength(gyroStrength_);
    if (gyroEnabled_ != liveCfg.gyroEnabled) {
        gyroEnabled_ = liveCfg.gyroEnabled;
        if (gyroEnabled_) gyroWarp_->start(); else gyroWarp_->stop();
    }

    // Sensor-realism passes (lens shading falloff, FPN/temporal noise,
    // rolling-shutter shear) are applied at <=1080p but skipped above to keep
    // very large preview streams (4K/full sensor) above 25 fps. The artefacts
    // these passes introduce are statistically detectable mainly on the small
    // preview/face-crop a verifier actually analyses; on 12 MP frames the
    // contribution is dominated by JPEG/compression noise downstream anyway,
    // so the cost/benefit doesn't justify the per-pixel passes there.
    const bool wantPostFx = !fromStream && !qrCleanMode_ &&
        ((uint64_t)width * height <= (uint64_t)1920 * 1080);

    // #region agent log
    {
        static int fxLogCount = 0;
        if (fxLogCount++ % 30 == 0) {
            float gyroPitch = 0, gyroRoll = 0;
            if (gyroWarp_) { gyroPitch = gyroWarp_->getPitch(); gyroRoll = gyroWarp_->getRoll(); }
            char buf[512];
            snprintf(buf, sizeof(buf),
                     "{\"cam\":\"%s\",\"res\":\"%dx%d\",\"wantPostFx\":%s,"
                     "\"gyroEnabled\":%s,\"gyroWarpActive\":%s,"
                     "\"gyroPitch\":%.4f,\"gyroRoll\":%.4f,\"gyroStrength\":%.3f,"
                     "\"qrClean\":%s,\"chrome\":%s}",
                     cameraId_.c_str(), (int)width, (int)height,
                     wantPostFx ? "true" : "false",
                     gyroEnabled_ ? "true" : "false",
                     gyroWarp_ ? "true" : "false",
                     gyroPitch, gyroRoll,
                     gyroStrength_,
                     qrCleanMode_ ? "true" : "false",
                     chromeWebClient_ ? "true" : "false");
            dbgLog("H1_H3", "FakeCameraDevice.cpp:fillYUV", "postfx_gyro_state", buf);
        }
    }
    // #endregion

    if (wantPostFx) {
        if (gyroEnabled_ && gyroWarp_) gyroWarp_->apply(yuvBuf_.data(), (int)width, (int)height, tmpBuf_.data());

        float isoGain = metaRand_->getCurrentISO() / 100.0f;

        {
            static LensShading lensShading(0.4f);
            lensShading.apply(yuvBuf_.data(), (int)width, (int)height);
        }

        noiseOverlay_->apply(yuvBuf_.data(), (int)width, (int)height, isoGain);

        {
            static RollingShutter rollingShutter(33000.0f, 200.0f);
            float gyroRate = (gyroEnabled_ && gyroWarp_)
                ? gyroWarp_->getRoll() * (M_PI / 180.0f) * gyroStrength_
                : 0.0f;
            rollingShutter.apply(yuvBuf_.data(), (int)width, (int)height,
                                 tmpBuf_.data(), gyroRate);
        }
    }

    bool mirrorPreviewH = previewMirrorH_;
    bool mirrorPreviewV = previewMirrorV_;
    int rotationPreview = previewRotation_;
    if (cameraId_ == "0") {
        if (backRotate180_) rotationPreview = (rotationPreview + 180) % 360;
    } else if (cameraId_ == "1") {
        mirrorPreviewH = mirrorPreviewH != (chromeWebClient_ ? chromeFrontMirror_ : frontMirror_);
        if (chromeWebClient_ ? chromeFrontRotate180_ : frontRotate180_) rotationPreview = (rotationPreview + 180) % 360;
    }

    FHAL_I("fillYUV cam=%s size=%dx%d mirrorH=%d mirrorV=%d rotation=%d gyro=%d chrome=%d sensorOri=%d",
           cameraId_.c_str(), (int)width, (int)height,
           mirrorPreviewH ? 1 : 0, mirrorPreviewV ? 1 : 0, rotationPreview,
           gyroEnabled_ ? 1 : 0, chromeWebClient_ ? 1 : 0, sensorOrientation_);

    // Rotation first, then mirrors — preview_mirror_h/v match on-screen axes (mirroring before
    // 90°/270° rotation made "horizontal" look like a vertical flip to users).
    if (rotationPreview == 180) {
        rotateNV21_180(yuvBuf_.data(), (int)width, (int)height);
    } else if (rotationPreview == 90 || rotationPreview == 270) {
        rotateNV21_90Or270Fit(yuvBuf_.data(), tmpBuf_.data(), (int)width, (int)height, rotationPreview == 90);
    }
    if (mirrorPreviewH) mirrorNV21Horizontal(yuvBuf_.data(), (int)width, (int)height);
    if (mirrorPreviewV) mirrorNV21Vertical(yuvBuf_.data(), (int)width, (int)height);
}

bool FakeCameraDeviceSession::writeYUVToBuffer(
    const buffer_handle_t& handle,
    const uint8_t* nv21, int width, int height, int32_t streamFormat)
{
    if (!handle || !nv21 || width <= 0 || height <= 0) {
        FHAL_E("writeYUVToBuffer: invalid parameters");
        return false;
    }

    if (streamFormat == HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED ||
        streamFormat == HAL_PIXEL_FORMAT_YCbCr_420_888) {
        android_ycbcr ycbcr;
        if (!GrallocHelper::getInstance().lockYCbCr(handle, width, height,
                                                    GRALLOC_USAGE_SW_WRITE_OFTEN, &ycbcr)) {
            FHAL_E("gralloc lockYCbCr failed: buffer=%p size=%dx%d version=%s",
                  (void*)handle, width, height,
                  GrallocHelper::getInstance().versionString());

            void* rawPtr = nullptr;
            if (!GrallocHelper::getInstance().lock(handle, width, height,
                                                   GRALLOC_USAGE_SW_WRITE_OFTEN, &rawPtr,
                                                   HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED)) {
                return false;
            }

            size_t yuvSize = (size_t)(width * height * 3 / 2);
            memcpy(rawPtr, nv21, yuvSize);
            GrallocHelper::getInstance().unlock(handle);
            return true;
        }

        const uint8_t* srcY = nv21;
        const uint8_t* srcVU = nv21 + (size_t)(width * height);

        // Chrome WebRTC consumes the gralloc planes via the documented
        // android_ycbcr semantics (cb pointer = Cb data, cr pointer = Cr data)
        // and decodes with a BT.709 matrix, which matches our encoded source
        // exactly -- so for Chrome we write standard layout and don't recolor.
        //
        // Native Android Camera2 SurfaceTexture path on this device, however,
        // ignores the cb/cr pointer offsets returned by lockYCbCr and reads
        // the interleaved chroma plane assuming NV21 byte order (V then U)
        // regardless. That's been confirmed via a solid colour test pattern:
        // writing Cb=155,Cr=104 to the cb/cr planes per gralloc semantics
        // surfaces in OpenCamera/SurfaceTexture as the swapped pair (~RGB
        // equivalent of decoding Cb=104,Cr=155). To compensate, for non-Chrome
        // consumers we deliberately *swap* which plane gets U vs V at the
        // gralloc level. Combined with running the chroma values through a
        // BT.709->BT.601 matrix, this lets the SurfaceTexture shader recover
        // the original sRGB.
        // After applying the polosit GrallocHelper (PLANE_LAYOUTS-based cb/cr
        // assignment), gralloc correctly reports NV21 layout (Cr at base[0],
        // Cb at base[1]). The old swapUVForConsumer compensated for an
        // incorrect cb/cr mapping that no longer exists — keeping it inverts
        // the channels. The video is now re-encoded as bt709, so no BT.601
        // matrix conversion is needed either.
        const bool swapUVForConsumer = false;
        const bool convertToBt601 = false;

        // Build per-byte LUTs once per frame (256 bytes each, fits in L1):
        //   yLUT  : Y_lim709 -> Y_full601 (Y_full = (Y - 16) * 255/219, clamped)
        //   cbLUT : limited centred Cb -> full-range centred contribution scaled
        //   ... stored as Q8 fixed point so the inner loop is pure int math.
        // BT.709 limited -> BT.601 full chroma cross-matrix (derived by
        // chaining BT.709-limited->RGB and RGB->BT.601-full):
        //   newCb = 1.128 *(Cb-128) - 0.126 *(Cr-128)
        //   newCr = -0.082*(Cb-128) + 1.120 *(Cr-128)
        // Scaled to Q10 fixed point:
        constexpr int CB_CB = 1155;  // round(1.128 * 1024)
        constexpr int CB_CR = -129;  // round(-0.126 * 1024)
        constexpr int CR_CB = -84;   // round(-0.082 * 1024)
        constexpr int CR_CR = 1147;  // round(1.120 * 1024)

        uint8_t yLUT[256];
        if (convertToBt601) {
            for (int i = 0; i < 256; ++i) {
                int v = (int)((i - 16) * 255 + 219 / 2) / 219;
                if (v < 0) v = 0; else if (v > 255) v = 255;
                yLUT[i] = (uint8_t)v;
            }
        }

        for (int y = 0; y < height; ++y) {
            const uint8_t* srcRow = srcY + y * width;
            uint8_t* dstRow = static_cast<uint8_t*>(ycbcr.y) + y * ycbcr.ystride;
            if (convertToBt601) {
                for (int x = 0; x < width; ++x) dstRow[x] = yLUT[srcRow[x]];
            } else {
                memcpy(dstRow, srcRow, (size_t)width);
            }
        }

        const int chromaHeight = height / 2;
        const int chromaWidth = width / 2;

        // Detect a contiguous semi-planar target where Cb and Cr planes share the
        // same memory (NV12: cb=base, cr=base+1; NV21: cr=base, cb=base+1).
        const bool semiPlanarContig =
            (ycbcr.chroma_step == 2) &&
            (ycbcr.cstride == (size_t)width);
        const intptr_t cbCrDelta = semiPlanarContig
            ? ((const uint8_t*)ycbcr.cr - (const uint8_t*)ycbcr.cb) : 0;
        const bool targetIsNV12 = semiPlanarContig && cbCrDelta == 1;
        const bool targetIsNV21 = semiPlanarContig && cbCrDelta == -1;

        auto convertPair = [](uint8_t cb709, uint8_t cr709,
                              uint8_t& cb601, uint8_t& cr601) {
            const int dCb = (int)cb709 - 128;
            const int dCr = (int)cr709 - 128;
            int ncb = ((CB_CB * dCb + CB_CR * dCr) >> 10) + 128;
            int ncr = ((CR_CB * dCb + CR_CR * dCr) >> 10) + 128;
            if (ncb < 0) ncb = 0; else if (ncb > 255) ncb = 255;
            if (ncr < 0) ncr = 0; else if (ncr > 255) ncr = 255;
            cb601 = (uint8_t)ncb;
            cr601 = (uint8_t)ncr;
        };

        if (targetIsNV21 && !convertToBt601) {
            // Source layout already matches target and no color transform needed:
            // bulk copy entire UV plane.
            uint8_t* dstUV = static_cast<uint8_t*>(ycbcr.cr); // base of interleaved VU
            const size_t uvBytes = (size_t)chromaHeight * (size_t)width;
            memcpy(dstUV, srcVU, uvBytes);
        } else if (targetIsNV12 && !convertToBt601) {
            // NV21 (V,U) -> NV12 (U,V). Treat as packed uint16_t [V|U] -> [U|V]
            // (little-endian: bytes [V,U] read as 0xUUVV -> bswap16 -> 0xVVUU = bytes [U,V]).
            // Compiler auto-vectorises this loop to arm64 NEON `rev16` instructions.
            uint16_t* dstUV = reinterpret_cast<uint16_t*>(ycbcr.cb); // base of interleaved UV
            const uint16_t* srcUV = reinterpret_cast<const uint16_t*>(srcVU);
            const size_t pairs = (size_t)chromaHeight * (size_t)chromaWidth;
            for (size_t i = 0; i < pairs; ++i) {
                dstUV[i] = __builtin_bswap16(srcUV[i]);
            }
        } else if (targetIsNV12 && convertToBt601) {
            // Single tight pass for non-Chrome consumers: read source NV21
            // (V,U), convert (Cb,Cr) BT.709 -> BT.601 full, then write
            // *swapped* into the interleaved chroma plane (V at base+0, U at
            // base+1) -- this matches the byte order the SurfaceTexture shader
            // actually reads (see the long comment above for the diagnosis).
            uint8_t* dstUV = static_cast<uint8_t*>(ycbcr.cb);
            const size_t pairs = (size_t)chromaHeight * (size_t)chromaWidth;
            for (size_t i = 0; i < pairs; ++i) {
                uint8_t v = srcVU[i * 2];
                uint8_t u = srcVU[i * 2 + 1];
                uint8_t u2, v2;
                convertPair(u, v, u2, v2);
                if (swapUVForConsumer) {
                    dstUV[i * 2 + 0] = v2;
                    dstUV[i * 2 + 1] = u2;
                } else {
                    dstUV[i * 2 + 0] = u2;
                    dstUV[i * 2 + 1] = v2;
                }
            }
        } else if (targetIsNV21 && convertToBt601) {
            uint8_t* dstUV = static_cast<uint8_t*>(ycbcr.cr); // base of interleaved chroma
            const size_t pairs = (size_t)chromaHeight * (size_t)chromaWidth;
            for (size_t i = 0; i < pairs; ++i) {
                uint8_t v = srcVU[i * 2];
                uint8_t u = srcVU[i * 2 + 1];
                uint8_t u2, v2;
                convertPair(u, v, u2, v2);
                if (swapUVForConsumer) {
                    dstUV[i * 2 + 0] = u2;
                    dstUV[i * 2 + 1] = v2;
                } else {
                    dstUV[i * 2 + 0] = v2;
                    dstUV[i * 2 + 1] = u2;
                }
            }
        } else {
            // Generic fallback: arbitrary stride/step (e.g. padded rows or planar).
            for (int y = 0; y < chromaHeight; ++y) {
                uint8_t* dstCb = static_cast<uint8_t*>(ycbcr.cb) + y * ycbcr.cstride;
                uint8_t* dstCr = static_cast<uint8_t*>(ycbcr.cr) + y * ycbcr.cstride;
                const uint8_t* srcRow = srcVU + y * width;
                for (int x = 0; x < chromaWidth; ++x) {
                    uint8_t v = srcRow[x * 2];
                    uint8_t u = srcRow[x * 2 + 1];
                    if (convertToBt601) {
                        uint8_t u2, v2;
                        convertPair(u, v, u2, v2);
                        u = u2; v = v2;
                    }
                    if (ycbcr.chroma_step == 1) {
                        dstCb[x] = u;
                        dstCr[x] = v;
                    } else {
                        // Semi-planar with non-contiguous Cb/Cr or padded rows.
                        dstCb[x * ycbcr.chroma_step] = u;
                        dstCr[x * ycbcr.chroma_step] = v;
                    }
                }
            }
        }

        GrallocHelper::getInstance().unlock(handle);
    } else {
        void* ptr = nullptr;
        if (!GrallocHelper::getInstance().lock(handle, width, height,
                                               GRALLOC_USAGE_SW_WRITE_OFTEN, &ptr, HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED)) {
            FHAL_E("gralloc lock failed: buffer=%p size=%dx%d version=%s",
                  (void*)handle, width, height,
                  GrallocHelper::getInstance().versionString());
            return false;
        }

        size_t yuvSize = (size_t)(width * height * 3 / 2);
        memcpy(ptr, nv21, yuvSize);
        GrallocHelper::getInstance().unlock(handle);
    }

    return true;
}


#ifndef CAMERA_BLOB_ID_JPEG
#define CAMERA_BLOB_ID_JPEG 0x00FF
#endif

bool FakeCameraDeviceSession::writeJPEGToBuffer(
    const buffer_handle_t& handle,
    const uint8_t* nv21, int width, int height,
    const MetadataRandomizer& meta, int exifOrientation)
{
    if (!handle || !nv21 || width <= 0 || height <= 0) {
        FHAL_E("writeJPEGToBuffer: invalid parameters");
        return false;
    }


    // For BLOB format, the gralloc buffer is allocated as ANDROID_JPEG_MAX_SIZE bytes.
    // Do NOT use s.width (which is pixel width, not buffer size).
    int32_t blobBufSize = (int32_t)(width * height * 3 / 2 + 65536);  // match ANDROID_JPEG_MAX_SIZE


    void* blobPtr = nullptr;
    if (!GrallocHelper::getInstance().lock(handle, blobBufSize, 1,
                                           GRALLOC_USAGE_SW_WRITE_OFTEN, &blobPtr)) {
        FHAL_E("JPEG gralloc lock failed: buffer=%p blobSize=%d version=%s",
              (void*)handle, blobBufSize,
              GrallocHelper::getInstance().versionString());
        return false;
    }


    JpegEncoder::ExifData exifData;
    exifData.imageWidth  = width;
    exifData.imageHeight = height;
    exifData.iso         = (int)meta.getCurrentISO();
    exifData.exposureSec = meta.getCurrentExposureMs() / 1000.0f;
    exifData.fNumber     = 1.85f;
    exifData.focalLength = 6.81f;
    exifData.orientation  = exifOrientation;


    int jpegQuality = 95;

    std::vector<uint8_t> jpegData;
    if (!jpegEncoder_->encode(nv21, width, height, jpegQuality, exifData, jpegData)) {
        FHAL_E("JPEG encode failed: size=%dx%d reason=encoder_error", width, height);
        GrallocHelper::getInstance().unlock(handle);
        return false;
    }


    struct CameraBlob {
        uint32_t blobId;
        uint32_t blobSize;
    };

    if ((int64_t)jpegData.size() + (int64_t)sizeof(CameraBlob) > (int64_t)blobBufSize) {
        FHAL_E("JPEG encode failed: size=%zu + trailer %zu exceeds blob buffer %d",
              jpegData.size(), sizeof(CameraBlob), blobBufSize);
        GrallocHelper::getInstance().unlock(handle);
        return false;
    }


    uint8_t* buf = static_cast<uint8_t*>(blobPtr);
    memcpy(buf, jpegData.data(), jpegData.size());


    CameraBlob* trailer = reinterpret_cast<CameraBlob*>(
        buf + blobBufSize - sizeof(CameraBlob));
    trailer->blobId   = CAMERA_BLOB_ID_JPEG;
    trailer->blobSize = (uint32_t)jpegData.size();


    GrallocHelper::getInstance().unlock(handle);

    FHAL_D("writeJPEGToBuffer: wrote %zu bytes JPEG to blob buffer %d via %s",
          jpegData.size(), blobBufSize,
          GrallocHelper::getInstance().versionString());
    return true;
}

void FakeCameraDeviceSession::processOneRequest(const PendingRequest& pending) {
    const CaptureRequest& req = pending.request;


    tsSync_->markFrameStart();
    int64_t timestampNs = tsSync_->getExposureStartNs();


    int width = 1920, height = 1080;
    for (const auto& s : activeStreams_) {
        if (fmtToInt(s.format) == HAL_PIXEL_FORMAT_YCbCr_420_888 ||
            fmtToInt(s.format) == HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED) {
            width  = s.width;
            height = s.height;
            break;
        }
    }


    // fill() first: advance() randomizes metadata values,
    // then fillYUVBuffer() reads getCurrentISO() — ensures consistency
    android::CameraMetadata resultMeta;
    metaRand_->fill(frameNumber_, &resultMeta, timestampNs, height,
                    tsSync_->getActualFrameDurationNs());

    {
        int32_t curIso = (int32_t)metaRand_->getCurrentISO();
        float curExpMs = metaRand_->getCurrentExposureMs();
        FHAL_D("Frame #%u ts=%lld ISO=%d exposure=%.3fms",
               frameNumber_.load(), (long long)timestampNs,
               curIso, curExpMs);
    }

    // #region agent log — H10: check if client sends crop region (zoom)
    if (frameNumber_.load() % 60 == 1) {
#ifndef FAKE_HAL_TEST_BUILD
        int32_t cropRect[4] = {-1,-1,-1,-1};
        if (!req.settings.metadata.empty()) {
            const camera_metadata_t* rawS =
                reinterpret_cast<const camera_metadata_t*>(req.settings.metadata.data());
            camera_metadata_ro_entry_t ent;
            if (find_camera_metadata_ro_entry(rawS, ANDROID_SCALER_CROP_REGION, &ent) == 0 && ent.count >= 4) {
                for (int i = 0; i < 4; i++) cropRect[i] = ent.data.i32[i];
            }
        }
        char buf[256];
        snprintf(buf, sizeof(buf), "{\"frame\":%u,\"cropRegion\":[%d,%d,%d,%d]}",
                 frameNumber_.load(), cropRect[0], cropRect[1], cropRect[2], cropRect[3]);
        dbgLog("H10", "FakeCameraDevice.cpp:processOneRequest", "crop_region", buf);
#endif
    }
    // #endregion

    // Frame pacing: target ~30fps with realistic jitter (real ISP timing
    // varies +-2ms per frame due to readout, 3A convergence, thermal throttle).
    {
        static auto lastFrameTime = std::chrono::steady_clock::now();
        static std::mt19937 jitterRng(std::random_device{}());
        static std::normal_distribution<float> jitterDist(0.0f, 1.2f);
        auto now = std::chrono::steady_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(now - lastFrameTime);
        int jitterMs = (int)std::clamp(jitterDist(jitterRng), -2.0f, 3.0f);
        int targetMs = 33 + jitterMs;
        // #region agent log
        if (frameNumber_.load() % 30 == 0) {
            char buf[256];
            snprintf(buf, sizeof(buf),
                     "{\"frame\":%u,\"elapsedMs\":%lld,\"sleepMs\":%lld,\"cam\":\"%s\",\"res\":\"%dx%d\"}",
                     frameNumber_.load(), (long long)elapsed.count(),
                     elapsed.count() < (long long)targetMs ? (long long)targetMs - elapsed.count() : 0LL,
                     cameraId_.c_str(), width, height);
            dbgLog("H2", "FakeCameraDevice.cpp:processOneRequest", "frame_timing", buf);
        }
        // #endregion
        if (elapsed.count() < (long long)targetMs) {
            std::this_thread::sleep_for(std::chrono::milliseconds((long long)targetMs - elapsed.count()));
        }
        lastFrameTime = std::chrono::steady_clock::now();
    }
    FHAL_I("processOneRequest: frame #%u about to fillYUV %dx%d", frameNumber_.load(), width, height);
    fillYUVBuffer((uint32_t)width, (uint32_t)height);
    FHAL_I("processOneRequest: fillYUV done, processing %zu output buffers", req.outputBuffers.size());

    std::vector<StreamBuffer> outputBuffers;
    for (const auto& ob : req.outputBuffers) {
        StreamBuffer sb;
        sb.streamId = ob.streamId;
        sb.bufferId  = ob.bufferId;
        sb.status    = BufferStatus::OK;


        int32_t fmt = HAL_PIXEL_FORMAT_YCbCr_420_888;
        for (const auto& s : activeStreams_) {
            if (s.id == ob.streamId) {
                fmt = fmtToInt(s.format);
                break;
            }
        }

        if (fmt == HAL_PIXEL_FORMAT_BLOB) {
#ifndef FAKE_HAL_TEST_BUILD
            int jpegOri = 0;
            if (!req.settings.metadata.empty()) {
                const camera_metadata_t* rawSettings =
                    reinterpret_cast<const camera_metadata_t*>(req.settings.metadata.data());
                camera_metadata_ro_entry_t ent;
                if (find_camera_metadata_ro_entry(rawSettings, ANDROID_JPEG_ORIENTATION, &ent) == 0)
                    jpegOri = ent.data.i32[0];
            }
            // VideoFrameReader pre-rotates the YUV buffer by
            // (displayRotation - sensorOrientation) so the camera framework's
            // SENSOR_ORIENTATION rotation produces an upright preview.
            // For a still capture, however, the JPEG itself is delivered to
            // the app verbatim (the framework does not apply SENSOR_ORIENTATION
            // to BLOB streams). The app expects the captured pixels to be in
            // sensor-native orientation and uses EXIF orientation to indicate
            // the display rotation.
            // Strategy: we already deliver upright pixels, so write EXIF=1
            // (no rotation) to tell viewers to display them as-is.
            // Map sensorOrientation to EXIF orientation tag.
            // With pre-rotated media (converter sets rotation=sensorOri),
            // the HAL delivers pixels in sensor-native orientation.
            // EXIF tells viewers how to rotate for upright display.
            int exifOri = 1; // default: normal
            // JPEG BLOB is not rotated by the camera framework, so EXIF must
            // indicate the sensor orientation regardless of preNormalized.
            // Viewers apply EXIF to display the image upright.
            if (sensorOrientation_ == 90) exifOri = 6;       // rotate 90 CW
            else if (sensorOrientation_ == 180) exifOri = 3;  // rotate 180
            else if (sensorOrientation_ == 270) exifOri = 8;  // rotate 270 CW
            FHAL_I("JPEG EXIF: sensorOri=%d preNorm=%d -> exifOri=%d",
                   sensorOrientation_, preNormalized_ ? 1 : 0, exifOri);
            FHAL_I("JPEG_ORIENTATION from request: %d, sensorOri=%d -> EXIF=%d", jpegOri, sensorOrientation_, exifOri);
            // Find BLOB stream dimensions
            int blobW = width, blobH = height;
            for (const auto& s : activeStreams_) {
                if (s.id == ob.streamId) {
                    blobW = s.width;
                    blobH = s.height;
                    break;
                }
            }
            // Undo the 180-degree rotation applied in fillYUVBuffer (only needed for preview).
            // Keep the existing front mirror behavior for JPEG captures.
            std::vector<uint8_t> unrotated(yuvBuf_.begin(), yuvBuf_.end());
            bool undoPreviewRotate180 = false;
            if (cameraId_ == "0") {
                undoPreviewRotate180 = backRotate180_;
            } else if (cameraId_ == "1") {
                undoPreviewRotate180 = chromeWebClient_ ? chromeFrontRotate180_ : frontRotate180_;
            }
            if (undoPreviewRotate180) {
                rotateNV21_180(unrotated.data(), width, height);
            }

            // Upscale YUV to BLOB resolution. The YUV buffer is already
            // pre-rotated (by VideoFrameReader::nextFrame) so the framework's
            // SENSOR_ORIENTATION rotation produces an upright preview. The
            // BLOB stream is delivered to the app verbatim, so we keep the
            // pixels as-is and rely on EXIF orientation (set above) to tell
            // the viewer how to display them. EXIF=1 (no rotation) is correct
            // for the back camera where the buffer's content matches the
            // captured-orientation. For the front camera the framework's
            // preview path applies an additional 180° relative to the back
            // path, so the captured pixels need a 180° pre-rotation here to
            // match the buffer to "upright" prior to JPEG encoding.
            std::vector<uint8_t> unrotatedAdjusted(unrotated);
            if (cameraId_ == "1") {
                rotateNV21_180(unrotatedAdjusted.data(), width, height);
            }
            std::vector<uint8_t> blobYuv;
            const uint8_t* jpegSrc = unrotatedAdjusted.data();
            int jpegW = width, jpegH = height;
            if (blobW != width || blobH != height) {
                size_t blobSize = (size_t)(blobW * blobH * 3 / 2);
                int vw = videoReader_->width();
                int vh = videoReader_->height();
                if (!fullResBuf_.empty() && blobW == vw && blobH == vh) {
                    blobYuv.assign(fullResBuf_.begin(), fullResBuf_.end());
                    if (undoPreviewRotate180)
                        rotateNV21_180(blobYuv.data(), blobW, blobH);
                    if (cameraId_ == "1")
                        rotateNV21_180(blobYuv.data(), blobW, blobH);
                    FHAL_I("BLOB: using full-res decoded frame %dx%d", blobW, blobH);
                } else {
                    blobYuv.resize(blobSize);
                    if (!fullResBuf_.empty()) {
                        std::vector<uint8_t> nativeSrc(fullResBuf_);
                        if (cameraId_ == "1")
                            rotateNV21_180(nativeSrc.data(), vw, vh);
                        resizeNV21(nativeSrc.data(), vw, vh,
                                   blobYuv.data(), blobW, blobH);
                        FHAL_I("BLOB: upscaled from NATIVE %dx%d to %dx%d", vw, vh, blobW, blobH);
                    } else {
                        resizeNV21(unrotatedAdjusted.data(), width, height,
                                   blobYuv.data(), blobW, blobH);
                        FHAL_I("BLOB: upscaled from PREVIEW %dx%d to %dx%d", width, height, blobW, blobH);
                    }
                }
                jpegSrc = blobYuv.data();
                jpegW = blobW;
                jpegH = blobH;
            }
            // --- STILL/JPEG ORIENTATION FIX --------------------------------
            // The camera framework applies ANDROID_SENSOR_ORIENTATION to the
            // preview/IMPLEMENTATION_DEFINED streams but NOT to BLOB streams.
            // Up to this point the still buffer therefore still holds the
            // sensor-raster version of the scene: a 1920x1080 landscape frame
            // whose content is rotated 90 deg relative to what the user framed
            // in the (correct) portrait preview.
            //
            // Previously this was papered over by shipping that landscape
            // raster verbatim and setting EXIF orientation 6.  That only looks
            // right in EXIF-aware viewers.  Gallery thumbnailers, web uploads,
            // ID-verification backends and any consumer that ignores the EXIF
            // tag see a sideways frame whose 16:9 raster does not match the
            // 9:16 scene -- i.e. the photo appears rotated and stretched
            // ("the picture expands") even though the preview was fine.
            //
            // Fix: bake the rotation into the pixels with a true transpose
            // (no scaling, no crop, no letterbox) and emit EXIF orientation 1.
            // The stored raster is then natively upright 1080x1920 and matches
            // the preview's proportions in *every* viewer, EXIF-aware or not.
            // Only the still path is touched; preview/Chrome are unaffected.
            std::vector<uint8_t> stillUpright;
            int stillRot = stillBakeDegrees(sensorOrientation_);
            // Optional runtime override, for calibrating/servicing a device
            // without a rebuild:  setprop vendor.fakehal.stillrot <0|90|180|270>
            {
                char pv[PROP_VALUE_MAX] = {0};
                if (__system_property_get("vendor.fakehal.stillrot", pv) > 0 && pv[0]) {
                    int v = atoi(pv);
                    stillRot = ((v % 360) + 360) % 360;
                    FHAL_I("STILL: rotation overridden by property -> %d deg", stillRot);
                }
            }
            const bool evenDims = (jpegW % 2) == 0 && (jpegH % 2) == 0;
            if ((stillRot == 90 || stillRot == 270) && evenDims) {
                stillUpright.resize((size_t)jpegW * jpegH * 3 / 2);
                rotateNV21_90Transpose(jpegSrc, jpegW, jpegH,
                                       stillUpright.data(), /*cw=*/stillRot == 90);
                jpegSrc = stillUpright.data();
                std::swap(jpegW, jpegH);
                exifOri = 1;
                FHAL_I("STILL: baked %d deg into pixels -> %dx%d, EXIF=1",
                       stillRot, jpegW, jpegH);
            } else if (stillRot == 180 && evenDims) {
                stillUpright.assign(jpegSrc,
                                    jpegSrc + (size_t)jpegW * jpegH * 3 / 2);
                rotateNV21_180InPlace(stillUpright.data(), jpegW, jpegH);
                jpegSrc = stillUpright.data();
                exifOri = 1;
                FHAL_I("STILL: baked 180 deg into pixels -> %dx%d, EXIF=1",
                       jpegW, jpegH);
            } else {
                FHAL_I("STILL: no bake (rot=%d dims=%dx%d), EXIF=%d",
                       stillRot, jpegW, jpegH, exifOri);
            }

            FHAL_I("processOneRequest: BLOB stream %d - encoding JPEG %dx%d (from %dx%d) orient=%d exif=%d",
                   ob.streamId, jpegW, jpegH, width, height, jpegOri, exifOri);
            native_handle_t* blobHandle = nullptr;
            bool blobOwnHandle = false;
            if (!ob.buffer.fds.empty()) {
                blobHandle = nativeHandleFromAidl(ob.buffer);
                blobOwnHandle = true;
                {
                    std::lock_guard<std::mutex> lk(bufferCacheMutex_);
                    auto it = bufferCache_.find(ob.bufferId);
                    if (it != bufferCache_.end()) {
                        native_handle_close(it->second);
                        native_handle_delete(it->second);
                    }
                    bufferCache_[ob.bufferId] = native_handle_clone(blobHandle);
                }
            } else {
                std::lock_guard<std::mutex> lk(bufferCacheMutex_);
                auto it = bufferCache_.find(ob.bufferId);
                if (it != bufferCache_.end()) {
                    blobHandle = it->second;
                    blobOwnHandle = false;
                }
            }
            if (blobHandle) {
                if (!writeJPEGToBuffer(blobHandle, jpegSrc, jpegW, jpegH, *metaRand_, exifOri)) {
                    FHAL_E("processOneRequest: JPEG write failed for stream %d", ob.streamId);
                    sb.status = BufferStatus::ERROR;
                }
            } else {
                FHAL_E("processOneRequest: no BLOB buffer for bufferId=%lld", (long long)ob.bufferId);
                sb.status = BufferStatus::ERROR;
            }
            if (blobOwnHandle && blobHandle) {
                native_handle_delete(blobHandle);
            }
#endif
        } else {
#ifndef FAKE_HAL_TEST_BUILD
            // Buffer cache: CameraService sends full handle only on first use of bufferId
            native_handle_t* bufHandle = nullptr;
            bool ownHandle = false;
            if (!ob.buffer.fds.empty()) {
                // New buffer - cache it by bufferId
                bufHandle = nativeHandleFromAidl(ob.buffer);
                ownHandle = true;
                {
                    std::lock_guard<std::mutex> lk(bufferCacheMutex_);
                    auto it = bufferCache_.find(ob.bufferId);
                    if (it != bufferCache_.end()) {
                        native_handle_close(it->second);
                        native_handle_delete(it->second);
                    }
                    bufferCache_[ob.bufferId] = native_handle_clone(bufHandle);
                }
            } else {
                // Empty handle - use cached
                std::lock_guard<std::mutex> lk(bufferCacheMutex_);
                auto it = bufferCache_.find(ob.bufferId);
                if (it != bufferCache_.end()) {
                    bufHandle = it->second;
                    ownHandle = false;
                }
            }

            if (bufHandle) {
                writeYUVToBuffer(bufHandle, yuvBuf_.data(), width, height, fmt);
            } else {
                FHAL_E("processOneRequest: no buffer for bufferId=%lld", (long long)ob.bufferId);
            }
            if (ownHandle && bufHandle) {
                native_handle_delete(bufHandle);
            }
#else
            writeYUVToBuffer(ob.buffer, yuvBuf_.data(), width, height, fmt);
#endif
        }


#ifndef FAKE_HAL_TEST_BUILD
        sb.releaseFence = ::aidl::android::hardware::common::NativeHandle();
#else
        sb.releaseFence = ndk::ScopedFileDescriptor(-1);
#endif
        outputBuffers.push_back(std::move(sb));
    }


    CaptureResult result;
    result.frameNumber       = frameNumber_++;
    result.outputBuffers     = std::move(outputBuffers);
    result.inputBuffer.streamId = -1;
    result.partialResult     = 1;


    camera_metadata_t* rawMeta = resultMeta.release();
    if (rawMeta) {
        size_t metaSize = get_camera_metadata_size(rawMeta);
        result.result.metadata.assign(
            (uint8_t*)rawMeta,
            (uint8_t*)rawMeta + metaSize
        );
        free_camera_metadata(rawMeta);
    } else {
        FHAL_W("processOneRequest: resultMeta was empty for frame #%u",
               frameNumber_.load());
    }


    if (callback_) {
        // Send shutter notification first (required by Camera HAL spec)
        {
            using namespace aidl::android::hardware::camera::device;
            NotifyMsg shutterMsg;
            ShutterMsg shutter;
            shutter.frameNumber = result.frameNumber;
            shutter.timestamp = timestampNs;
            shutterMsg.set<NotifyMsg::shutter>(shutter);
            std::vector<NotifyMsg> msgs;
            msgs.push_back(std::move(shutterMsg));
            callback_->notify(msgs);
        }

        std::vector<CaptureResult> results;
        results.push_back(std::move(result));
        auto cbStatus = callback_->processCaptureResult(results);
        FHAL_I("processOneRequest: processCaptureResult status=%d frame=%u", cbStatus.getStatus(), result.frameNumber);
    }
}


ndk::ScopedAStatus FakeCameraDeviceSession::constructDefaultRequestSettings(
    RequestTemplate type,
    ::aidl::android::hardware::camera::device::CameraMetadata* meta)
{
        android::CameraMetadata settings;
    uint8_t aeMode = ANDROID_CONTROL_AE_MODE_ON;
    settings.update(ANDROID_CONTROL_AE_MODE, &aeMode, 1);
    uint8_t afMode = ANDROID_CONTROL_AF_MODE_CONTINUOUS_PICTURE;
    settings.update(ANDROID_CONTROL_AF_MODE, &afMode, 1);
    uint8_t awbMode = ANDROID_CONTROL_AWB_MODE_AUTO;
    settings.update(ANDROID_CONTROL_AWB_MODE, &awbMode, 1);
    uint8_t controlMode = ANDROID_CONTROL_MODE_AUTO;
    settings.update(ANDROID_CONTROL_MODE, &controlMode, 1);
    uint8_t captureIntent = ANDROID_CONTROL_CAPTURE_INTENT_PREVIEW;
    if (type == RequestTemplate::STILL_CAPTURE) captureIntent = ANDROID_CONTROL_CAPTURE_INTENT_STILL_CAPTURE;
    else if (type == RequestTemplate::VIDEO_RECORD) captureIntent = ANDROID_CONTROL_CAPTURE_INTENT_VIDEO_RECORD;
    else if (type == RequestTemplate::VIDEO_SNAPSHOT) captureIntent = ANDROID_CONTROL_CAPTURE_INTENT_VIDEO_SNAPSHOT;
    settings.update(ANDROID_CONTROL_CAPTURE_INTENT, &captureIntent, 1);

#ifdef FAKE_HAL_TEST_BUILD
    // In test builds, CameraMetadata is aliased to android::CameraMetadata (mock).
    *meta = settings;
#else
    camera_metadata_t* raw = settings.release();
    if (raw) {
        size_t sz = get_camera_metadata_size(raw);
        meta->metadata.assign((uint8_t*)raw, (uint8_t*)raw + sz);
        free_camera_metadata(raw);
    }
#endif
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::flush() {
    flushing_ = true;
    std::lock_guard<std::mutex> lk(queueMutex_);
    while (!requestQueue_.empty()) requestQueue_.pop();
    flushing_ = false;
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::getCaptureRequestMetadataQueue(
    ::aidl::android::hardware::common::fmq::MQDescriptor<int8_t, ::aidl::android::hardware::common::fmq::SynchronizedReadWrite>*)
{
    // FMQ not used - metadata passed inline
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::getCaptureResultMetadataQueue(
    ::aidl::android::hardware::common::fmq::MQDescriptor<int8_t, ::aidl::android::hardware::common::fmq::SynchronizedReadWrite>*)
{
    // FMQ not used - metadata passed inline
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraDeviceSession::isReconfigurationRequired(
    const ::aidl::android::hardware::camera::device::CameraMetadata&,
    const ::aidl::android::hardware::camera::device::CameraMetadata&,
    bool* out)
{ *out = false; return ndk::ScopedAStatus::ok(); }

ndk::ScopedAStatus FakeCameraDeviceSession::signalStreamFlush(
    const std::vector<int32_t>&, int32_t)
{ return ndk::ScopedAStatus::ok(); }

ndk::ScopedAStatus FakeCameraDeviceSession::switchToOffline(
    const std::vector<int32_t>&,
    CameraOfflineSessionInfo*,
    std::shared_ptr<ICameraOfflineSession>*)
{
    return ndk::ScopedAStatus::fromServiceSpecificError(
        static_cast<int32_t>(::aidl::android::hardware::camera::common::Status::OPERATION_NOT_SUPPORTED));
}

ndk::ScopedAStatus FakeCameraDeviceSession::repeatingRequestEnd(
    int32_t, const std::vector<int32_t>&)
{ return ndk::ScopedAStatus::ok(); }

}

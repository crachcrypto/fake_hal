#include "GyroWarp.h"

#include <fcntl.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>

#include <cstdio>
#include <cstring>
#include <cerrno>
#include <cmath>
#include <algorithm>
#include <chrono>
#include <vector>
#ifdef __ANDROID__
#include <sys/system_properties.h>
#endif

#define LOG_TAG "FakeHAL_GyroWarp"
#include <log/log.h>
#include "FakeHALLog.h"

namespace fake_hal {

static constexpr float DEG_PER_RAD = 180.0f / M_PI;

#ifdef __ANDROID__
static bool readDebugFloatProperty(const char* key, float* outValue) {
    char value[PROP_VALUE_MAX] = {};
    if (__system_property_get(key, value) <= 0 || value[0] == '\0') {
        return false;
    }

    errno = 0;
    char* end = nullptr;
    float parsed = strtof(value, &end);
    if (end == value || errno != 0) {
        return false;
    }

    *outValue = parsed;
    return true;
}
#endif

GyroWarp::GyroWarp(const std::string& iioDevicePath,
                   int maxShiftPx, float maxAngleDeg, float effectStrength)
    : iioDevicePath_(iioDevicePath)
    , maxShiftPx_(maxShiftPx)
    , maxAngleDeg_(maxAngleDeg)
    , strength_(std::clamp(effectStrength, 0.0f, 1.0f))
{}

GyroWarp::~GyroWarp() {
    stop();
}

void GyroWarp::setStrength(float s) {
    strength_ = std::clamp(s, 0.0f, 1.0f);
}

bool GyroWarp::openIIO() {
    FHAL_I("GyroWarp: scanning IIO devices...");

    auto tryOpen = [](const std::string& path) -> int {
        int fd = open(path.c_str(), O_RDONLY | O_NONBLOCK);
        if (fd < 0) {
            FHAL_V("GyroWarp: cannot open %s: %s", path.c_str(), strerror(errno));
        }
        return fd;
    };

    std::vector<std::string> iioBasePaths = {
        "/sys/bus/iio/devices/iio:device0",
        "/sys/bus/iio/devices/iio:device1",
        "/sys/bus/iio/devices/iio:device2",
        "/sys/bus/iio/devices/iio:device3",
    };

    for (const auto& base : iioBasePaths) {
        int gx = tryOpen(base + "/in_anglvel_x_raw");
        int gy = tryOpen(base + "/in_anglvel_y_raw");
        if (gx >= 0 && gy >= 0) {
            fdGyroX_ = gx;
            fdGyroY_ = gy;

            char scalePath[256];
            snprintf(scalePath, sizeof(scalePath), "%s/in_anglvel_scale", base.c_str());
            FILE* f = fopen(scalePath, "r");
            if (f) {
                fscanf(f, "%f", &gyroScale_);
                fclose(f);
            } else {
                gyroScale_ = 0.000266316f;
            }
            FHAL_I("GyroWarp: opened gyroscope at %s, scale=%f", base.c_str(), gyroScale_);
            break;
        }
        if (gx >= 0) close(gx);
        if (gy >= 0) close(gy);
    }

    for (const auto& base : iioBasePaths) {
        int ax = tryOpen(base + "/in_accel_x_raw");
        int ay = tryOpen(base + "/in_accel_y_raw");
        int az = tryOpen(base + "/in_accel_z_raw");
        if (ax >= 0 && ay >= 0 && az >= 0) {
            fdAccelX_ = ax;
            fdAccelY_ = ay;
            fdAccelZ_ = az;

            char scalePath[256];
            snprintf(scalePath, sizeof(scalePath), "%s/in_accel_scale", base.c_str());
            FILE* f = fopen(scalePath, "r");
            if (f) {
                fscanf(f, "%f", &accelScale_);
                fclose(f);
            } else {
                accelScale_ = 0.000598144f;
            }
            FHAL_I("GyroWarp: opened accelerometer at %s", base.c_str());
            break;
        }
        if (ax >= 0) close(ax);
        if (ay >= 0) close(ay);
        if (az >= 0) close(az);
    }

    return (fdGyroX_ >= 0 && fdGyroY_ >= 0);
}

float GyroWarp::readSysfs(int fd) {
    if (fd < 0) return 0.0f;
    char buf[32] = {};
    lseek(fd, 0, SEEK_SET);
    ssize_t n = read(fd, buf, sizeof(buf) - 1);
    if (n <= 0) return 0.0f;
    return (float)atof(buf);
}

bool GyroWarp::openAndroidSensors() {
#ifdef __ANDROID__
    sensorManager_ = ASensorManager_getInstanceForPackage("android.hardware.camera.provider-fake");
    if (!sensorManager_) {
        FHAL_W("GyroWarp: ASensorManager_getInstanceForPackage failed");
        return false;
    }

    gravitySensor_ = ASensorManager_getDefaultSensor(sensorManager_, ASENSOR_TYPE_GRAVITY);
    accelSensor_ = ASensorManager_getDefaultSensor(sensorManager_, ASENSOR_TYPE_ACCELEROMETER);
    usingAndroidSensors_ = (gravitySensor_ != nullptr || accelSensor_ != nullptr);
    if (usingAndroidSensors_) {
        FHAL_I("GyroWarp: Android sensor fallback ready (gravity=%d accel=%d)",
               gravitySensor_ ? 1 : 0,
               accelSensor_ ? 1 : 0);
        return true;
    }

    FHAL_W("GyroWarp: Android gravity/accelerometer sensors unavailable");
#endif
    return false;
}

void GyroWarp::updateFromGravity(float gx, float gy, float gz, float alpha) {
    float absPitch = atan2f(gx, sqrtf(gy * gy + gz * gz)) * DEG_PER_RAD;
    float absRoll  = atan2f(gy, sqrtf(gx * gx + gz * gz)) * DEG_PER_RAD;

    if (!baselineReady_) {
        baselinePitchDeg_ = absPitch;
        baselineRollDeg_ = absRoll;
        baselineReady_ = true;
        FHAL_I("GyroWarp: baseline calibrated pitch=%.2f roll=%.2f",
               baselinePitchDeg_, baselineRollDeg_);
    }

    float relPitch = absPitch - baselinePitchDeg_;
    float relRoll = absRoll - baselineRollDeg_;

    filtPitch_ = (1.0f - alpha) * filtPitch_ + alpha * relPitch;
    filtRoll_  = (1.0f - alpha) * filtRoll_ + alpha * relRoll;

    filtPitch_ = std::clamp(filtPitch_, -maxAngleDeg_, maxAngleDeg_);
    filtRoll_  = std::clamp(filtRoll_,  -maxAngleDeg_, maxAngleDeg_);

    pitchDeg_.store(filtPitch_);
    rollDeg_.store(filtRoll_);
}

bool GyroWarp::start() {
    baselineReady_ = false;
    bool haveIio = openIIO();
    bool haveNdk = false;
    if (!haveIio) {
        haveNdk = openAndroidSensors();
        if (!haveNdk) {
            FHAL_W("GyroWarp: no IIO gyroscope found and Android sensor fallback unavailable");
        }
    }

    // #region agent log — H7: sensor availability
    {
        FILE* f = fopen("/data/local/tmp/fhal_debug.log", "a");
        if (f) {
            struct timespec ts; clock_gettime(CLOCK_REALTIME, &ts);
            long long ms = (long long)ts.tv_sec * 1000 + ts.tv_nsec / 1000000;
            fprintf(f, "{\"sessionId\":\"d4053e\",\"hypothesisId\":\"H7\","
                       "\"location\":\"GyroWarp.cpp:start\",\"message\":\"sensor_avail\","
                       "\"data\":{\"haveIIO\":%s,\"haveNDK\":%s,\"fdGyroX\":%d,\"fdGyroY\":%d},"
                       "\"timestamp\":%lld}\n",
                    haveIio ? "true" : "false", haveNdk ? "true" : "false",
                    fdGyroX_, fdGyroY_, ms);
            fclose(f);
        }
    }
    // #endregion

    running_ = true;
    readerThread_ = std::thread(&GyroWarp::readerLoop, this);
    return true;
}

void GyroWarp::stop() {
    running_ = false;
    if (readerThread_.joinable()) readerThread_.join();
    if (fdGyroX_  >= 0) { close(fdGyroX_);  fdGyroX_  = -1; }
    if (fdGyroY_  >= 0) { close(fdGyroY_);  fdGyroY_  = -1; }
    if (fdAccelX_ >= 0) { close(fdAccelX_); fdAccelX_ = -1; }
    if (fdAccelY_ >= 0) { close(fdAccelY_); fdAccelY_ = -1; }
    if (fdAccelZ_ >= 0) { close(fdAccelZ_); fdAccelZ_ = -1; }
    usingAndroidSensors_ = false;
    baselineReady_ = false;
}

void GyroWarp::readerLoop() {
    using namespace std::chrono;
#ifdef __ANDROID__
    if (usingAndroidSensors_ && sensorManager_) {
        sensorLooper_ = ALooper_prepare(ALOOPER_PREPARE_ALLOW_NON_CALLBACKS);
        if (sensorLooper_) {
            sensorQueue_ = ASensorManager_createEventQueue(sensorManager_, sensorLooper_, 1, nullptr, nullptr);
            const ASensor* activeSensor = gravitySensor_ ? gravitySensor_ : accelSensor_;
            if (sensorQueue_ && activeSensor) {
                ASensorEventQueue_enableSensor(sensorQueue_, activeSensor);
                ASensorEventQueue_setEventRate(sensorQueue_, activeSensor, 10000);
                FHAL_I("GyroWarp: Android sensor fallback enabled via %s",
                       gravitySensor_ ? "gravity" : "accelerometer");
            } else {
                FHAL_W("GyroWarp: failed to create Android sensor event queue");
                usingAndroidSensors_ = false;
            }
        } else {
            FHAL_W("GyroWarp: failed to prepare Android looper");
            usingAndroidSensors_ = false;
        }
    }
#endif
    auto lastTime = steady_clock::now();

    while (running_) {
#ifdef __ANDROID__
        if (usingAndroidSensors_ && sensorQueue_) {
            ALooper_pollOnce(10, nullptr, nullptr, nullptr);

            ASensorEvent event;
            bool updated = false;
            while (ASensorEventQueue_hasEvents(sensorQueue_) > 0 &&
                   ASensorEventQueue_getEvents(sensorQueue_, &event, 1) > 0) {
                if (event.type == ASENSOR_TYPE_GRAVITY ||
                    event.type == ASENSOR_TYPE_ACCELEROMETER) {
                    updateFromGravity(event.vector.x, event.vector.y, event.vector.z,
                                      gravitySensor_ ? 0.18f : 0.10f);
                    updated = true;
                }
            }

            if (updated && (std::abs(filtPitch_) > 1.0f || std::abs(filtRoll_) > 1.0f)) {
                FHAL_D("GyroWarp: android pitch=%.2f roll=%.2f", filtPitch_, filtRoll_);
            }
            continue;
        }
#endif
        auto now = steady_clock::now();
        float dt = duration_cast<microseconds>(now - lastTime).count() / 1'000'000.0f;
        lastTime = now;
        if (dt <= 0.0f || dt > 0.1f) dt = 0.01f;

        float gx = readSysfs(fdGyroX_) * gyroScale_;
        float gy = readSysfs(fdGyroY_) * gyroScale_;

        float ax = readSysfs(fdAccelX_) * accelScale_;
        float ay = readSysfs(fdAccelY_) * accelScale_;
        float az = readSysfs(fdAccelZ_) * accelScale_;

        float accelPitch = atan2f(ax, sqrtf(ay*ay + az*az)) * DEG_PER_RAD;
        float accelRoll  = atan2f(ay, sqrtf(ax*ax + az*az)) * DEG_PER_RAD;

        if ((fdAccelX_ >= 0 || fdAccelY_ >= 0 || fdAccelZ_ >= 0) && !baselineReady_) {
            baselinePitchDeg_ = accelPitch;
            baselineRollDeg_ = accelRoll;
            baselineReady_ = true;
            FHAL_I("GyroWarp: IIO baseline calibrated pitch=%.2f roll=%.2f",
                   baselinePitchDeg_, baselineRollDeg_);
        }

        accelPitch -= baselinePitchDeg_;
        accelRoll -= baselineRollDeg_;

        filtPitch_ = 0.98f * (filtPitch_ + gx * DEG_PER_RAD * dt) + 0.02f * accelPitch;
        filtRoll_  = 0.98f * (filtRoll_  + gy * DEG_PER_RAD * dt) + 0.02f * accelRoll;

        filtPitch_ = std::clamp(filtPitch_, -maxAngleDeg_, maxAngleDeg_);
        filtRoll_  = std::clamp(filtRoll_,  -maxAngleDeg_, maxAngleDeg_);

        pitchDeg_.store(filtPitch_);
        rollDeg_.store(filtRoll_);

        std::this_thread::sleep_for(milliseconds(10));
    }

#ifdef __ANDROID__
    if (sensorQueue_ && sensorManager_) {
        if (gravitySensor_) {
            ASensorEventQueue_disableSensor(sensorQueue_, gravitySensor_);
        }
        if (accelSensor_ && accelSensor_ != gravitySensor_) {
            ASensorEventQueue_disableSensor(sensorQueue_, accelSensor_);
        }
        ASensorManager_destroyEventQueue(sensorManager_, sensorQueue_);
        sensorQueue_ = nullptr;
    }
    if (sensorLooper_) {
        sensorLooper_ = nullptr;
    }
#endif
}

void GyroWarp::shiftNV21(const uint8_t* src, uint8_t* dst,
                          int width, int height, int dx, int dy)
{
    for (int y = 0; y < height; y++) {
        int srcY = y - dy;
        for (int x = 0; x < width; x++) {
            int srcX = x - dx;
            if (srcX >= 0 && srcX < width && srcY >= 0 && srcY < height) {
                dst[y * width + x] = src[srcY * width + srcX];
            } else {
                int clampX = std::clamp(srcX, 0, width - 1);
                int clampY = std::clamp(srcY, 0, height - 1);
                dst[y * width + x] = src[clampY * width + clampX];
            }
        }
    }

    int uvWidth  = width  / 2;
    int uvHeight = height / 2;
    int dxUV = dx / 2;
    int dyUV = dy / 2;

    const uint8_t* srcUV = src    + width * height;
    uint8_t*       dstUV = dst    + width * height;

    for (int y = 0; y < uvHeight; y++) {
        int srcY = y - dyUV;
        for (int x = 0; x < uvWidth; x++) {
            int srcX = x - dxUV;
            int clampX = std::clamp(srcX, 0, uvWidth  - 1);
            int clampY = std::clamp(srcY, 0, uvHeight - 1);

            dstUV[(y * uvWidth + x) * 2 + 0] = srcUV[(clampY * uvWidth + clampX) * 2 + 0];
            dstUV[(y * uvWidth + x) * 2 + 1] = srcUV[(clampY * uvWidth + clampX) * 2 + 1];
        }
    }
}

void GyroWarp::apply(uint8_t* nv21, int width, int height, uint8_t* tmpBuf)
{
    float pitch = pitchDeg_.load();
    float roll  = rollDeg_.load();
#ifdef __ANDROID__
    float forcedPitch = 0.0f;
    float forcedRoll = 0.0f;
    if (readDebugFloatProperty("debug.fakehal.force_pitch_deg", &forcedPitch)) {
        pitch = std::clamp(forcedPitch, -maxAngleDeg_, maxAngleDeg_);
    }
    if (readDebugFloatProperty("debug.fakehal.force_roll_deg", &forcedRoll)) {
        roll = std::clamp(forcedRoll, -maxAngleDeg_, maxAngleDeg_);
    }
#endif

    pitch *= strength_;
    roll *= strength_;

    int dx = (int)((roll  / maxAngleDeg_) * maxShiftPx_);
    int dy = (int)((pitch / maxAngleDeg_) * maxShiftPx_);

    if (std::abs(pitch) > 2.0f || std::abs(roll) > 2.0f) {
        FHAL_D("GyroWarp: pitch=%.2f roll=%.2f dx=%d dy=%d", pitch, roll, dx, dy);
    }

    if (std::abs(dx) < 1 && std::abs(dy) < 1) return;


    size_t bufSize = (size_t)(width * height * 3 / 2);
    std::memcpy(tmpBuf, nv21, bufSize);
    shiftNV21(tmpBuf, nv21, width, height, dx, dy);
}

}

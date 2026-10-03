#pragma once

#include <cstdint>
#include <atomic>
#include <thread>
#include <mutex>
#include <string>

#ifdef __ANDROID__
#include <android/looper.h>
#include <android/sensor.h>
#endif

namespace fake_hal {


class GyroWarp {
public:

    explicit GyroWarp(
        const std::string& iioDevicePath = "/sys/bus/iio/devices/iio:device0",
        int maxShiftPx = 40,
        float maxAngleDeg = 20.0f,
        float effectStrength = 1.0f
    );

    ~GyroWarp();


    bool start();
    void stop();

    /** Multiplier 0..1 applied to pitch/roll in apply() (rolling shutter uses same scale in HAL). */
    void setStrength(float s);

    void apply(uint8_t* nv21, int width, int height, uint8_t* tmpBuf);


    float getPitch() const { return pitchDeg_.load(); }
    float getRoll()  const { return rollDeg_.load();  }


    static void shiftNV21(
        const uint8_t* src, uint8_t* dst,
        int width, int height,
        int dx, int dy
    );

private:
    std::string iioDevicePath_;
    int maxShiftPx_;
    float maxAngleDeg_;
    float strength_{1.0f};


    std::atomic<float> pitchDeg_{0.0f};
    std::atomic<float> rollDeg_{0.0f};


    float filtPitch_ = 0.0f;
    float filtRoll_  = 0.0f;


    int fdGyroX_ = -1, fdGyroY_ = -1;
    int fdAccelX_ = -1, fdAccelY_ = -1, fdAccelZ_ = -1;
    float gyroScale_ = 1.0f;
    float accelScale_ = 1.0f;

    std::thread readerThread_;
    std::atomic<bool> running_{false};

    void readerLoop();

    bool openIIO();
    bool openAndroidSensors();
    float readSysfs(int fd);
    float readAngle(int fd, float scale);
    void updateFromGravity(float gx, float gy, float gz, float alpha);

    bool usingAndroidSensors_ = false;
    bool baselineReady_ = false;
    float baselinePitchDeg_ = 0.0f;
    float baselineRollDeg_ = 0.0f;

#ifdef __ANDROID__
    ASensorManager* sensorManager_ = nullptr;
    ASensorEventQueue* sensorQueue_ = nullptr;
    const ASensor* gravitySensor_ = nullptr;
    const ASensor* accelSensor_ = nullptr;
    ALooper* sensorLooper_ = nullptr;
#endif

};

}

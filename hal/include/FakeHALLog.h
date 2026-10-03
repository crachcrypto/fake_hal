// FakeHALLog.h — встроенный логгер FakeHAL v7.0
// Пишет в logcat (tag=FakeHAL) и /data/local/tmp/fakehAL.log
// Debug включается созданием файла /data/local/tmp/fakehAL_debug
#pragma once

#ifdef FAKE_HAL_TEST_BUILD
// ===== Test build: stub macros that compile but do nothing special =====
#include <cstdio>
#include <cstdarg>

#define FHAL_E(fmt, ...) fprintf(stderr, "[E] FakeHAL: " fmt "\n", ##__VA_ARGS__)
#define FHAL_W(fmt, ...) fprintf(stderr, "[W] FakeHAL: " fmt "\n", ##__VA_ARGS__)
#define FHAL_I(fmt, ...) fprintf(stderr, "[I] FakeHAL: " fmt "\n", ##__VA_ARGS__)
#define FHAL_D(fmt, ...) fprintf(stderr, "[D] FakeHAL: " fmt "\n", ##__VA_ARGS__)
#define FHAL_V(fmt, ...) ((void)0)

namespace fake_hal {
    inline void installSignalHandlers() {}
}

#else
// ===== Android build: full logger =====
#include <android/log.h>
#include <stdio.h>
#include <time.h>
#include <sys/stat.h>
#include <string.h>
#include <stdarg.h>
#include <unistd.h>
#include <sys/syscall.h>
#include <errno.h>

#define FHAL_LOG_TAG    "FakeHAL"
#define FHAL_LOG_FILE   "/data/local/tmp/fakehAL.log"
#define FHAL_DEBUG_FLAG "/data/local/tmp/fakehAL_debug"
#define FHAL_LOG_MAX_BYTES  (2 * 1024 * 1024)  // 2MB
#define FHAL_LOG_KEEP_FILES 3

namespace fake_hal {

class FakeHALLogger {
public:
    static FakeHALLogger& get() {
        static FakeHALLogger inst;
        return inst;
    }

    // Check debug flag without syscall overhead — cache for 500ms
    bool isDebugEnabled() {
        struct timespec now;
        clock_gettime(CLOCK_MONOTONIC, &now);
        long long nowNs = (long long)now.tv_sec * 1000000000LL + now.tv_nsec;
        long long lastNs = (long long)lastFlagCheck_ * 1000000000LL + lastFlagCheckNs_;
        if ((nowNs - lastNs) > 500000000LL) {
            struct stat st;
            debugEnabled_ = (stat(FHAL_DEBUG_FLAG, &st) == 0);
            lastFlagCheck_ = now.tv_sec;
            lastFlagCheckNs_ = now.tv_nsec;
        }
        return debugEnabled_;
    }

    void log(int prio, const char* level, const char* fmt, ...) __attribute__((format(printf, 4, 5))) {
        va_list ap1, ap2;
        va_start(ap1, fmt);
        va_copy(ap2, ap1);

        // 1. logcat
        __android_log_vprint(prio, FHAL_LOG_TAG, fmt, ap1);
        va_end(ap1);

        // 2. file
        writeToFile(level, fmt, ap2);
        va_end(ap2);
    }

private:
    bool debugEnabled_ = false;
    time_t lastFlagCheck_ = 0;
    long lastFlagCheckNs_ = 0;

    FakeHALLogger() = default;

    void writeToFile(const char* level, const char* fmt, va_list ap) {
        // Rotation check
        struct stat st;
        if (stat(FHAL_LOG_FILE, &st) == 0 && st.st_size > FHAL_LOG_MAX_BYTES) {
            rotateLogs();
        }

        FILE* f = fopen(FHAL_LOG_FILE, "a");
        if (!f) return;

        // Timestamp
        struct timespec ts;
        clock_gettime(CLOCK_REALTIME, &ts);
        struct tm tm_info;
        localtime_r(&ts.tv_sec, &tm_info);
        char timebuf[32];
        strftime(timebuf, sizeof(timebuf), "%m-%d %H:%M:%S", &tm_info);
        fprintf(f, "%s.%03ld %5d %5ld %s FakeHAL: ",
                timebuf, ts.tv_nsec / 1000000,
                getpid(), syscall(SYS_gettid), level);
        vfprintf(f, fmt, ap);
        fprintf(f, "\n");
        fflush(f);
        fclose(f);
    }

    void rotateLogs() {
        // .log.2 -> delete, .log.1 -> .log.2, .log -> .log.1
        char buf1[256], buf2[256];
        for (int i = FHAL_LOG_KEEP_FILES - 1; i >= 1; i--) {
            snprintf(buf1, sizeof(buf1), "%s.%d", FHAL_LOG_FILE, i);
            snprintf(buf2, sizeof(buf2), "%s.%d", FHAL_LOG_FILE, i + 1);
            if (i + 1 >= FHAL_LOG_KEEP_FILES) {
                remove(buf1);
            } else {
                rename(buf1, buf2);
            }
        }
        snprintf(buf1, sizeof(buf1), "%s.1", FHAL_LOG_FILE);
        rename(FHAL_LOG_FILE, buf1);
    }
};

// Forward declare signal handler installer
void installSignalHandlers();

} // namespace fake_hal

// Macros — use everywhere in code
#define FHAL_E(fmt, ...) ::fake_hal::FakeHALLogger::get().log(ANDROID_LOG_ERROR,   "E", fmt, ##__VA_ARGS__)
#define FHAL_W(fmt, ...) ::fake_hal::FakeHALLogger::get().log(ANDROID_LOG_WARN,    "W", fmt, ##__VA_ARGS__)
#define FHAL_I(fmt, ...) ::fake_hal::FakeHALLogger::get().log(ANDROID_LOG_INFO,    "I", fmt, ##__VA_ARGS__)
#define FHAL_D(fmt, ...) do { if (::fake_hal::FakeHALLogger::get().isDebugEnabled()) \
    ::fake_hal::FakeHALLogger::get().log(ANDROID_LOG_DEBUG,  "D", fmt, ##__VA_ARGS__); } while(0)
#define FHAL_V(fmt, ...) do { if (::fake_hal::FakeHALLogger::get().isDebugEnabled()) \
    ::fake_hal::FakeHALLogger::get().log(ANDROID_LOG_VERBOSE,"V", fmt, ##__VA_ARGS__); } while(0)

#endif // FAKE_HAL_TEST_BUILD

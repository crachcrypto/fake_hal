#include "FakeCameraProvider.h"
#include "VideoFrameReader.h"
#include "FakeHALLog.h"
#include <unistd.h>
#ifndef FAKE_HAL_TEST_BUILD
#include <sys/system_properties.h>
#endif
#include <sys/stat.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>

// v7.5: Write an early marker to file BEFORE anything else.
// This helps diagnose cases where the binary is killed before
// the logger subsystem initializes (e.g., missing shared libs,
// SELinux denials on logcat, etc.)
static void write_early_marker() {
    const char* marker = "/data/local/tmp/fakehAL_alive";
    int fd = open(marker, O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (fd >= 0) {
        char buf[128];
        int len = snprintf(buf, sizeof(buf),
            "FakeHAL v7.6 alive PID=%d UID=%d\n", getpid(), getuid());
        if (len > 0) {
            // Ignore write errors — best-effort diagnostic
            ssize_t unused __attribute__((unused)) = write(fd, buf, len);
        }
        close(fd);
    }
    // Also write to stderr in case it's redirected to a log file
    fprintf(stderr, "FakeHAL v7.6: early marker PID=%d UID=%d\n", getpid(), getuid());
    fflush(stderr);
}

int main(int argc, char* argv[]) {
    // FIRST THING: Write an early marker file so service.sh can verify
    // the binary actually started executing (not just that a PID exists).
    write_early_marker();

    const char* videoPathBack  = "/data/local/tmp/fake_video.mp4";
    const char* videoPathFront = nullptr;  // null = same as back
    if (argc > 1) videoPathBack = argv[1];
    if (argc > 2) videoPathFront = argv[2];
    if (!videoPathFront) videoPathFront = videoPathBack;

    FHAL_I("=== FakeHAL v7.6 starting ===");
    FHAL_I("PID=%d UID=%d GID=%d", getpid(), getuid(), getgid());

    // Read SELinux context
    {
        char ctx[256] = "unknown";
        FILE* f = fopen("/proc/self/attr/current", "r");
        if (f) { if (fgets(ctx, sizeof(ctx), f)) {} fclose(f); }
        FHAL_I("SELinux context: %s", ctx);
    }

    // Log command-line args for debugging
    FHAL_I("argc=%d", argc);
    for (int i = 0; i < argc; i++) {
        FHAL_I("  argv[%d]=%s", i, argv[i]);
    }

    fake_hal::installSignalHandlers();

    FHAL_I("Video path back: %s", videoPathBack);
    FHAL_I("Video path front: %s", videoPathFront);

    // Check video file existence before starting
    for (const char* vp : {videoPathBack, videoPathFront}) {
        struct stat st;
        if (stat(vp, &st) != 0) {
            FHAL_E("video file not found: %s (errno=%d: %s) -- place your video there",
                   vp, errno, strerror(errno));
        } else {
            FHAL_I("Video file found: %s (size=%lld bytes)", vp, (long long)st.st_size);
        }
    }

    // Start global TCP stream server for live video from PC
    {
#ifndef FAKE_HAL_TEST_BUILD
        char portBuf[256] = {0};
        __system_property_get("fakehal.stream.port", portBuf);
        int streamPort = portBuf[0] ? atoi(portBuf) : 0;
#else
        int streamPort = 0;
#endif
        if (streamPort > 0) {
            FHAL_I("Starting global stream server on port %d...", streamPort);
            fake_hal::VideoFrameReader::startGlobalStreamServer(streamPort);
        }
    }

    FHAL_I("Calling FakeCameraProvider::instantiate()...");
    fake_hal::FakeCameraProvider::instantiate(videoPathBack, videoPathFront);
    // instantiate() calls ABinderProcess_joinThreadPool() which blocks forever.
    // If we reach here, something went wrong.
    FHAL_E("instantiate() returned unexpectedly!");
    return 1;
}

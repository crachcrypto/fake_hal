// FakeHALSignalHandler.cpp — crash handler for FakeHAL v7.0
// Catches SIGSEGV, SIGABRT, SIGFPE, SIGBUS and logs before dying
#include "FakeHALLog.h"

#ifndef FAKE_HAL_TEST_BUILD

#include <signal.h>
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

namespace fake_hal {

static void signalHandler(int sig, siginfo_t* info, void* /*ctx*/) {
    const char* signame = (sig == SIGSEGV) ? "SIGSEGV" :
                          (sig == SIGABRT) ? "SIGABRT" :
                          (sig == SIGFPE)  ? "SIGFPE"  :
                          (sig == SIGBUS)  ? "SIGBUS"  : "UNKNOWN";

    FHAL_E("=== CRASH: signal %s (%d) addr=%p pid=%d ===",
           signame, sig, info ? info->si_addr : nullptr, getpid());

    // Try to report DSO name and offset
    if (info && info->si_addr) {
        Dl_info dl;
        if (dladdr(info->si_addr, &dl) && dl.dli_fname) {
            FHAL_E("  in: %s (+%p)", dl.dli_fname,
                   (void*)((char*)info->si_addr - (char*)dl.dli_fbase));
        }
    }

    FHAL_E("Check logcat: adb logcat -s FakeHAL");
    FHAL_E("Check log file: /data/local/tmp/fakehAL.log");

    // Give logger time to flush
    usleep(100000);

    // Restore default handler and re-raise
    struct sigaction sa{};
    sa.sa_handler = SIG_DFL;
    sigaction(sig, &sa, nullptr);
    raise(sig);
}

void installSignalHandlers() {
    struct sigaction sa{};
    sa.sa_sigaction = signalHandler;
    sa.sa_flags = SA_SIGINFO | SA_RESETHAND;
    sigemptyset(&sa.sa_mask);

    sigaction(SIGSEGV, &sa, nullptr);
    sigaction(SIGABRT, &sa, nullptr);
    sigaction(SIGFPE,  &sa, nullptr);
    sigaction(SIGBUS,  &sa, nullptr);

    FHAL_I("Signal handlers installed (SIGSEGV/SIGABRT/SIGFPE/SIGBUS)");
}

} // namespace fake_hal

#endif // FAKE_HAL_TEST_BUILD

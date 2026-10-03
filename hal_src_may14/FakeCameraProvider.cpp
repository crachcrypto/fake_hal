#include "FakeCameraProvider.h"
#include "FakeCameraDevice.h"
#include "FakeHALLog.h"

#include <aidl/android/hardware/camera/common/Status.h>
#include <android/binder_manager.h>
#include <android/binder_process.h>

#include <cstdio>
#include <cstring>
#include <fstream>
#include <unistd.h>
#include <stdlib.h>
#include <sys/system_properties.h>

#define LOG_TAG "FakeHAL_Provider"
#include <log/log.h>

namespace fake_hal {

const std::vector<std::string> FakeCameraProvider::kCameraIds = {"device@1.0/internal/0", "device@1.0/internal/1"};

std::string FakeCameraProvider::readDeviceSerial() {

    char buf[64] = {};
    FILE* f = fopen("/sys/devices/soc0/serial_number", "r");
    if (f) {
        if (fgets(buf, sizeof(buf), f)) {
            fclose(f);

            size_t len = strlen(buf);
            while (len > 0 && (buf[len-1] == '\n' || buf[len-1] == '\r'))
                buf[--len] = '\0';
            if (len > 0) return buf;
        } else {
            fclose(f);
        }
    }

#ifdef __ANDROID__

    char prop[92] = {};
    if (__system_property_get("ro.serialno", prop) > 0) return prop;
#endif


    std::ifstream idFile("/data/misc/fake_hal_id");
    if (idFile.good()) {
        std::string id;
        std::getline(idFile, id);
        if (!id.empty()) return id;
    }

    return "UNKNOWN_SERIAL";
}

std::string FakeCameraProvider::readDeviceProp(const char* propName) {
#ifdef __ANDROID__
    char prop[92] = {};
    if (__system_property_get(propName, prop) > 0) return prop;
#endif
    (void)propName;
    return "Pixel 7";
}

FakeCameraProvider::FakeCameraProvider(const std::string& backVideoPath, const std::string& frontVideoPath)
    : backVideoPath_(backVideoPath)
    , frontVideoPath_(frontVideoPath)
    , noiseOverlay_(3.0f, 0.35f)
{

    std::string serial = readDeviceSerial();
    std::string model  = readDeviceProp("ro.product.model");
    noiseOverlay_.setSensorFingerprint(serial, model);
    FHAL_I("NoiseOverlay: FPN fingerprint from serial '%s' (model=%s)",
          serial.c_str(), model.c_str());


    for (const auto& fullName : kCameraIds) {
        // Extract short id (e.g. "0") from full name if compound
        std::string shortId = fullName;
        auto lastSlash = fullName.rfind('/');
        if (lastSlash != std::string::npos) shortId = fullName.substr(lastSlash + 1);
        std::string videoForCam = (shortId == "0") ? backVideoPath_ : frontVideoPath_;
        devices_[shortId] = ndk::SharedRefBase::make<FakeCameraDevice>(shortId, videoForCam, serial, model);
    }
    FHAL_I("FakeCameraProvider: initialized back=%s front=%s cameras=%zu",
          backVideoPath_.c_str(), frontVideoPath_.c_str(), devices_.size());
}

FakeCameraProvider::~FakeCameraProvider() {
    FHAL_I("FakeCameraProvider: destroyed");
}

ndk::ScopedAStatus FakeCameraProvider::setCallback(
    const std::shared_ptr<ICameraProviderCallback>& callback)
{
    std::lock_guard<std::mutex> lk(callbackMutex_);
    callback_ = callback;
    FHAL_I("FakeCameraProvider: setCallback registered");
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::getVendorTags(
    std::vector<VendorTagSection>* vts)
{
    vts->clear();
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::getCameraIdList(
    std::vector<std::string>* cameraIds)
{
    *cameraIds = kCameraIds;
    FHAL_I("FakeCameraProvider: getCameraIdList -> [%s]",
          kCameraIds.size() == 2 ? "device@1.0/internal/0, device@1.0/internal/1" : "device@1.0/internal/0");
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::getCameraDeviceInterface(
    const std::string& cameraDeviceName,
    std::shared_ptr<ICameraDevice>* device)
{

    std::string id = cameraDeviceName;

    auto pos = id.rfind('/');
    if (pos != std::string::npos) id = id.substr(pos + 1);

    auto it = devices_.find(id);
    if (it == devices_.end()) {
        FHAL_E("FakeCameraProvider: unknown camera id: %s", cameraDeviceName.c_str());
        return ndk::ScopedAStatus::fromServiceSpecificError(
            static_cast<int32_t>(::aidl::android::hardware::camera::common::Status::ILLEGAL_ARGUMENT));
    }

    *device = it->second;
    FHAL_I("FakeCameraProvider: getCameraDeviceInterface(%s) -> OK", id.c_str());
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::notifyDeviceStateChange(int64_t deviceState) {
    FHAL_I("FakeCameraProvider: notifyDeviceStateChange(0x%lx)", (long)deviceState);
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::getConcurrentCameraIds(
    std::vector<ConcurrentCameraIdCombination>* concurrentCameraIds)
{
    concurrentCameraIds->clear();
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus FakeCameraProvider::isConcurrentStreamCombinationSupported(
    const std::vector<CameraIdAndStreamCombination>&,
    bool* support)
{
    *support = false;
    return ndk::ScopedAStatus::ok();
}


void FakeCameraProvider::instantiate(const std::string& backVideoPath, const std::string& frontVideoPath) {
    FHAL_I("instantiate: setting thread pool max=4");
    ABinderProcess_setThreadPoolMaxThreadCount(4);

    FHAL_I("instantiate: creating FakeCameraProvider...");
    auto provider = ndk::SharedRefBase::make<FakeCameraProvider>(backVideoPath, frontVideoPath);

    const std::string serviceName =
        std::string(ICameraProvider::descriptor) + "/internal/0";

    FHAL_I("Registering service: %s", serviceName.c_str());

    // v7.5: Check if service is declared in VINTF BEFORE attempting registration.
    // This provides a clear diagnostic if the VINTF manifest is missing.
    bool isDeclared = AServiceManager_isDeclared(serviceName.c_str());
    if (isDeclared) {
        FHAL_I("VINTF check: %s IS declared in VINTF manifest", serviceName.c_str());
    } else {
        FHAL_E("VINTF check: %s is NOT declared in VINTF manifest!", serviceName.c_str());
        FHAL_E("This means AServiceManager_addService will FAIL with VINTF declaration error.");
        FHAL_E("Check: 1) /vendor/etc/vintf/manifest/fake_camera_hal.xml exists and is correct");
        FHAL_E("       2) KernelSU overlay is active");
        FHAL_E("       3) servicemanager was restarted after VINTF changes");
        // Don't exit — still attempt registration in case isDeclared is wrong
        // (e.g., stability annotation not set, which bypasses VINTF check)
    }

    // Retry registration up to 10 times (increased from 5) with better diagnostics
    binder_status_t status = STATUS_FAILED_TRANSACTION;
    for (int attempt = 0; attempt < 10; attempt++) {
        FHAL_I("AServiceManager_addService attempt %d...", attempt);
        status = AServiceManager_addService(
            provider->asBinder().get(),
            serviceName.c_str()
        );
        if (status == STATUS_OK) {
            FHAL_I("AServiceManager_addService succeeded on attempt %d", attempt);
            break;
        }
        FHAL_W("AServiceManager_addService attempt %d failed: status=%d, retrying in 2s...",
               attempt, status);
        sleep(2);
    }

    if (status != STATUS_OK) {
        if (status == -1 /* EPERM - permission denied */) {
            FHAL_E("SELinux denied: check policy. AServiceManager_addService('%s') returned PERMISSION_DENIED (%d). "
                   "Run: adb logcat -s avc", serviceName.c_str(), status);
        } else {
            FHAL_E("Failed to register '%s': status=%d. "
                   "Possible causes: VINTF manifest missing/cached, servicemanager not ready, SELinux, or binder error.",
                   serviceName.c_str(), status);
        }
        FHAL_E("isDeclared=%d", isDeclared);
        _exit(1);  // Exit with error so service.sh can detect the failure
    }

    FHAL_I("ICameraProvider/internal/0 registered successfully");
    FHAL_I("ICameraProvider/internal/0 ready — entering binder thread pool");
    ABinderProcess_joinThreadPool();
}

}

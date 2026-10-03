
#define LOG_TAG "FakeHAL_Gralloc"
#include <log/log.h>
#include "FakeHALLog.h"
#include "GrallocHelper.h"

#include <android/hardware/graphics/mapper/4.0/IMapper.h>
#include <mutex>
#include <unordered_map>
#include <sys/mman.h>

using IMapper4 = android::hardware::graphics::mapper::V4_0::IMapper;
using Error4 = android::hardware::graphics::mapper::V4_0::Error;

namespace fake_hal {

static android::sp<IMapper4> sMapper;

GrallocHelper& GrallocHelper::getInstance() {
    static GrallocHelper instance;
    return instance;
}

GrallocHelper::GrallocHelper() {
    (void)mapperService_;
    sMapper = IMapper4::getService();
    if (sMapper != nullptr) {
        version_ = GrallocVersion::GRALLOC4;
        FHAL_I("GrallocHelper: IMapper4 connected directly");
    } else {
        version_ = GrallocVersion::MMAP_FALLBACK;
        FHAL_E("GrallocHelper: IMapper4 NOT available!");
    }
}

const char* GrallocHelper::versionString() const {
    return version_ == GrallocVersion::GRALLOC4 ? "IMapper4 direct" : "unavailable";
}

bool GrallocHelper::lockYCbCr(buffer_handle_t handle, int width, int height,
                              uint32_t usage, android_ycbcr* outYCbCr) {
    if (!outYCbCr) return false;
    void* ptr = nullptr;
    if (!lock(handle, width, height, usage, &ptr, HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED)) {
        return false;
    }

    const native_handle_t* nh = static_cast<const native_handle_t*>(handle);
    if (!nh || nh->numFds < 2) {
        FHAL_E("GrallocHelper: lockYCbCr requires >=2 fds, got %d", nh ? nh->numFds : -1);
        unlock(handle);
        return false;
    }

    size_t uvSize = (size_t)(width * height / 2);
    void* uvPtr = mmap(nullptr, uvSize, PROT_READ | PROT_WRITE, MAP_SHARED, nh->data[1], 0);
    if (uvPtr == MAP_FAILED) {
        size_t chromaPlaneSize = (size_t)(width * height / 4);
        if (nh->numFds >= 3) {
            void* cbPtr = mmap(nullptr, chromaPlaneSize, PROT_READ | PROT_WRITE, MAP_SHARED, nh->data[1], 0);
            void* crPtr = mmap(nullptr, chromaPlaneSize, PROT_READ | PROT_WRITE, MAP_SHARED, nh->data[2], 0);
            if (cbPtr != MAP_FAILED && crPtr != MAP_FAILED) {
                {
                    std::lock_guard<std::mutex> lk(mappingsMutex_);
                    mappings_[handle] = {cbPtr, chromaPlaneSize, nh->data[1], crPtr, chromaPlaneSize, nh->data[2]};
                }

                outYCbCr->y = ptr;
                outYCbCr->cb = cbPtr;
                outYCbCr->cr = crPtr;
                outYCbCr->ystride = (size_t)width;
                outYCbCr->cstride = (size_t)(width / 2);
                outYCbCr->chroma_step = 1;

                FHAL_I("GrallocHelper: planar lockYCbCr y=%p cb=%p cr=%p ys=%zu cs=%zu step=%zu numFds=%d numInts=%d",
                       outYCbCr->y, outYCbCr->cb, outYCbCr->cr,
                       outYCbCr->ystride, outYCbCr->cstride, outYCbCr->chroma_step,
                       handle->numFds, handle->numInts);
                return true;
            }
            if (cbPtr != MAP_FAILED) munmap(cbPtr, chromaPlaneSize);
            if (crPtr != MAP_FAILED) munmap(crPtr, chromaPlaneSize);
        }

        FHAL_E("GrallocHelper: mmap UV plane failed fd=%d size=%zu", nh->data[1], uvSize);
        unlock(handle);
        return false;
    }

    {
        std::lock_guard<std::mutex> lk(mappingsMutex_);
        mappings_[handle] = {uvPtr, uvSize, nh->data[1], nullptr, 0, -1};
    }

    outYCbCr->y = ptr;
    outYCbCr->cb = uvPtr;
    outYCbCr->cr = static_cast<uint8_t*>(uvPtr) + 1;
    outYCbCr->ystride = (size_t)width;
    outYCbCr->cstride = (size_t)width;
    outYCbCr->chroma_step = 2;

    FHAL_I("GrallocHelper: lockYCbCr y=%p cb=%p cr=%p ys=%zu cs=%zu step=%zu numFds=%d numInts=%d",
           outYCbCr->y, outYCbCr->cb, outYCbCr->cr,
           outYCbCr->ystride, outYCbCr->cstride, outYCbCr->chroma_step,
           handle->numFds, handle->numInts);
    return true;
}

bool GrallocHelper::lock(buffer_handle_t handle, int width, int height,
                         uint32_t usage, void** outPtr, int32_t format) {
    (void)format;
    if (!handle || !outPtr) return false;
    *outPtr = nullptr;
    if (!sMapper) return false;

    // Step 1: importBuffer (no validation - just register with mapper)
    native_handle_t* importedHandle = nullptr;
    auto impRet = sMapper->importBuffer(
        android::hardware::hidl_handle(handle),
        [&](Error4 err, void* buf) {
            if (err == Error4::NONE && buf) {
                importedHandle = static_cast<native_handle_t*>(buf);
            } else {
                FHAL_E("IMapper4::importBuffer err=%d", (int)err);
            }
        });
    
    if (!impRet.isOk() || !importedHandle) {
        FHAL_E("IMapper4::importBuffer HIDL failed");
        return false;
    }

    // Step 2: lock
    IMapper4::Rect accessRegion = {0, 0, width, height};
    bool success = false;
    auto lockRet = sMapper->lock(
        importedHandle,
        static_cast<uint64_t>(usage),
        accessRegion,
        android::hardware::hidl_handle(),
        [&](Error4 err, void* mappedPtr) {
            if (err == Error4::NONE && mappedPtr) {
                *outPtr = mappedPtr;
                success = true;
            } else {
                FHAL_E("IMapper4::lock err=%d", (int)err);
            }
        });

    if (!lockRet.isOk() || !success) {
        sMapper->freeBuffer(importedHandle);
        return false;
    }

    {
        std::lock_guard<std::mutex> lk(mappingsMutex_);
        importedHandles_[handle] = importedHandle;
    }

    // Query plane layout metadata (StandardMetadataType::PLANE_LAYOUTS = 6)
    IMapper4::MetadataType plType;
    plType.name = "android.hardware.graphics.common.StandardMetadataType";
    plType.value = 6; // PLANE_LAYOUTS
    sMapper->get(importedHandle, plType,
        [&](Error4 err, const android::hardware::hidl_vec<uint8_t>& data) {
            if (err == Error4::NONE) {
                FHAL_I("GrallocHelper: planeLayout metadata size=%zu", data.size());
                // Dump first 128 bytes of metadata for analysis
                const uint8_t* d = data.data();
                size_t n = std::min(data.size(), (size_t)128);
                char hex[512];
                for (size_t i = 0; i < n && i*3 < sizeof(hex)-4; i++)
                    sprintf(hex + i*3, "%02x ", d[i]);
                FHAL_I("GrallocHelper: planeLayout hex: %s", hex);
            } else {
                FHAL_E("GrallocHelper: get PLANE_LAYOUTS err=%d", (int)err);
            }
        });

    FHAL_I("GrallocHelper: locked %dx%d -> %p", width, height, *outPtr);
    return true;
}

int GrallocHelper::unlock(buffer_handle_t handle) {
    if (!handle || !sMapper) return -1;

    {
        std::lock_guard<std::mutex> lk(mappingsMutex_);
        auto it = mappings_.find(handle);
        if (it != mappings_.end()) {
            if (it->second.ptr && it->second.size > 0) {
                munmap(it->second.ptr, it->second.size);
            }
            if (it->second.ptr2 && it->second.size2 > 0) {
                munmap(it->second.ptr2, it->second.size2);
            }
            mappings_.erase(it);
        }
    }

    native_handle_t* importedHandle = nullptr;
    {
        std::lock_guard<std::mutex> lk(mappingsMutex_);
        auto it = importedHandles_.find(handle);
        if (it != importedHandles_.end()) {
            importedHandle = it->second;
            importedHandles_.erase(it);
        }
    }
    if (!importedHandle) return -1;

    int fenceFd = -1;
    sMapper->unlock(importedHandle,
        [&](Error4 err, const android::hardware::hidl_handle& fence) {
            if (err != Error4::NONE) {
                FHAL_E("IMapper4::unlock err=%d", (int)err);
            }
            if (fence.getNativeHandle() && fence.getNativeHandle()->numFds > 0) {
                fenceFd = dup(fence.getNativeHandle()->data[0]);
            }
        });

    sMapper->freeBuffer(importedHandle);
    return fenceFd;
}

// Unused stubs
bool GrallocHelper::lockGralloc4(buffer_handle_t, int, int, uint32_t, void**) { return false; }
int GrallocHelper::unlockGralloc4(buffer_handle_t) { return -1; }
bool GrallocHelper::lockGralloc3(buffer_handle_t, int, int, uint32_t, void**) { return false; }
int GrallocHelper::unlockGralloc3(buffer_handle_t) { return -1; }
bool GrallocHelper::lockMmap(buffer_handle_t, int, int, uint32_t, void**) { return false; }
int GrallocHelper::unlockMmap(buffer_handle_t) { return -1; }

}

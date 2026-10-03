
#define LOG_TAG "FakeHAL_Gralloc"
#include <log/log.h>
#include "FakeHALLog.h"
#include "GrallocHelper.h"

#include <android/hardware/graphics/mapper/4.0/IMapper.h>
#include <gralloctypes/Gralloc4.h>
#include <mutex>
#include <unordered_map>
#include <sys/mman.h>

using IMapper4 = android::hardware::graphics::mapper::V4_0::IMapper;
using Error4 = android::hardware::graphics::mapper::V4_0::Error;
using aidl::android::hardware::graphics::common::PlaneLayout;
using aidl::android::hardware::graphics::common::PlaneLayoutComponent;
using aidl::android::hardware::graphics::common::PlaneLayoutComponentType;

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

// Returns true and fills |outLayouts| with the real per-plane layout (offset,
// stride, component types) as reported by the gralloc implementation via
// StandardMetadataType::PLANE_LAYOUTS. This is the ONLY reliable source of
// stride/offset information for IMPLEMENTATION_DEFINED buffers -- guessing
// ystride=width or treating native_handle_t ints as extra mmap-able fds
// (the old approach) breaks as soon as the allocator pads/aligns a plane
// differently from `width`, which is exactly what happens for some
// resolutions on this device's gralloc4 (gs201/gchips) and produces the
// "polosit" vertical-banding artefact.
static bool queryPlaneLayouts(void* importedHandle, std::vector<PlaneLayout>* outLayouts) {
    if (!sMapper || !importedHandle || !outLayouts) return false;

    bool gotData = false;
    android::hardware::hidl_vec<uint8_t> rawData;
    auto ret = sMapper->get(
        importedHandle, android::gralloc4::MetadataType_PlaneLayouts,
        [&](Error4 err, const android::hardware::hidl_vec<uint8_t>& data) {
            if (err == Error4::NONE && data.size() > 0) {
                rawData = data;
                gotData = true;
            } else {
                FHAL_E("GrallocHelper: get PLANE_LAYOUTS err=%d size=%zu", (int)err, data.size());
            }
        });

    if (!ret.isOk() || !gotData) return false;

    android::status_t status = android::gralloc4::decodePlaneLayouts(rawData, outLayouts);
    if (status != android::OK || outLayouts->empty()) {
        FHAL_E("GrallocHelper: decodePlaneLayouts failed status=%d planes=%zu",
               (int)status, outLayouts->size());
        return false;
    }
    return true;
}

static bool planeHasComponent(const PlaneLayout& plane, PlaneLayoutComponentType type) {
    for (const PlaneLayoutComponent& c : plane.components) {
        if (c.type.name == GRALLOC4_STANDARD_PLANE_LAYOUT_COMPONENT_TYPE &&
            c.type.value == static_cast<int64_t>(type)) {
            return true;
        }
    }
    return false;
}

bool GrallocHelper::lockYCbCr(buffer_handle_t handle, int width, int height,
                              uint32_t usage, android_ycbcr* outYCbCr) {
    if (!outYCbCr) return false;
    void* ptr = nullptr;
    void* importedForMeta = nullptr;
    if (!lock(handle, width, height, usage, &ptr, HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED)) {
        return false;
    }

    // Grab the imported handle we just registered in lock() so we can query
    // its real plane layout metadata (same object IMapper4 knows about).
    {
        std::lock_guard<std::mutex> lk(mappingsMutex_);
        auto it = importedHandles_.find(handle);
        if (it != importedHandles_.end()) importedForMeta = it->second;
    }

    std::vector<PlaneLayout> layouts;
    if (importedForMeta && queryPlaneLayouts(importedForMeta, &layouts) && layouts.size() >= 2) {
        // Plane 0 is always Y for every semi-planar/planar YUV layout this
        // device's gralloc4 reports (NV12/NV21/YV12) -- use its real stride
        // instead of assuming ystride == width.
        const PlaneLayout& yPlane = layouts[0];
        outYCbCr->y = static_cast<uint8_t*>(ptr) + yPlane.offsetInBytes;
        outYCbCr->ystride = (size_t)yPlane.strideInBytes;

        if (layouts.size() == 2) {
            // Semi-planar (NV12/NV21): plane 1 carries both CB and CR
            // interleaved. Component order tells us which byte comes first.
            const PlaneLayout& uvPlane = layouts[1];
            uint8_t* uvBase = static_cast<uint8_t*>(ptr) + uvPlane.offsetInBytes;
            // Find the byte offset of CB and CR within the plane's interleaved
            // sample to be robust to either NV12 (CB@0,CR@1) or NV21
            // (CR@0,CB@1) ordering, instead of assuming one or the other.
            int cbByteOff = 0, crByteOff = 1;
            for (const PlaneLayoutComponent& c : uvPlane.components) {
                if (c.type.name != GRALLOC4_STANDARD_PLANE_LAYOUT_COMPONENT_TYPE) continue;
                if (c.type.value == static_cast<int64_t>(PlaneLayoutComponentType::CB)) {
                    cbByteOff = (int)(c.offsetInBits / 8);
                } else if (c.type.value == static_cast<int64_t>(PlaneLayoutComponentType::CR)) {
                    crByteOff = (int)(c.offsetInBits / 8);
                }
            }
            outYCbCr->cb = uvBase + cbByteOff;
            outYCbCr->cr = uvBase + crByteOff;
            outYCbCr->cstride = (size_t)uvPlane.strideInBytes;
            outYCbCr->chroma_step = 2;
        } else {
            // Fully planar (>=3 planes): find which plane is CB and which is CR.
            const PlaneLayout* cbPlane = nullptr;
            const PlaneLayout* crPlane = nullptr;
            for (size_t i = 1; i < layouts.size(); ++i) {
                if (planeHasComponent(layouts[i], PlaneLayoutComponentType::CB)) cbPlane = &layouts[i];
                if (planeHasComponent(layouts[i], PlaneLayoutComponentType::CR)) crPlane = &layouts[i];
            }
            if (!cbPlane) cbPlane = &layouts[1];
            if (!crPlane) crPlane = &layouts[layouts.size() > 2 ? 2 : 1];
            outYCbCr->cb = static_cast<uint8_t*>(ptr) + cbPlane->offsetInBytes;
            outYCbCr->cr = static_cast<uint8_t*>(ptr) + crPlane->offsetInBytes;
            outYCbCr->cstride = (size_t)cbPlane->strideInBytes;
            outYCbCr->chroma_step = 1;
        }

        FHAL_I("GrallocHelper: lockYCbCr(PLANE_LAYOUTS) y=%p cb=%p cr=%p ys=%zu cs=%zu step=%zu planes=%zu",
               outYCbCr->y, outYCbCr->cb, outYCbCr->cr,
               outYCbCr->ystride, outYCbCr->cstride, outYCbCr->chroma_step, layouts.size());
        for (size_t i = 0; i < layouts.size(); ++i) {
            FHAL_I("GrallocHelper: DIAG plane[%zu] %s", i, layouts[i].toString().c_str());
        }
        return true;
    }

    // Fallback: PLANE_LAYOUTS metadata unavailable/unparseable on this
    // gralloc implementation. Last resort -- assume tightly packed NV21
    // with ystride == width. This can still band on padded allocations,
    // but only triggers when the proper metadata path above is missing.
    FHAL_E("GrallocHelper: PLANE_LAYOUTS unavailable, falling back to width-stride assumption "
           "(buffer=%p size=%dx%d) -- may band if gralloc pads rows", (void*)handle, width, height);

    outYCbCr->y = ptr;
    outYCbCr->cb = static_cast<uint8_t*>(ptr) + (size_t)width * height + 1;
    outYCbCr->cr = static_cast<uint8_t*>(ptr) + (size_t)width * height;
    outYCbCr->ystride = (size_t)width;
    outYCbCr->cstride = (size_t)width;
    outYCbCr->chroma_step = 2;
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

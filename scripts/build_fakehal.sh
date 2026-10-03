#!/bin/bash
cd /root/aosp
export LC_ALL=C.UTF-8
source build/envsetup.sh
lunch aosp_cf_arm64_phone-userdebug
m android.hardware.camera.provider-fake -j8
echo "BUILD_DONE exit=$?"

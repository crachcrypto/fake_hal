#!/system/bin/sh
SKIPUNZIP=0

ui_print "==============================="
ui_print "  FakeHAL v7.4 Camera Provider"
ui_print "  AIDL internal/0 for Pixel 7"
ui_print "  Android 13 (panther/cheetah)"
ui_print "==============================="

# Check architecture
if [ "$ARCH" != "arm64" ]; then
    ui_print "ERROR: Only arm64 supported"
    exit 1
fi

# Set binary permissions and SELinux context
set_perm "$MODPATH/system/vendor/bin/hw/android.hardware.camera.provider-fake" \
    root root 0755 "u:object_r:hal_camera_default_exec:s0"

# Set permissions on VINTF manifest and RC file
if [ -f "$MODPATH/system/vendor/etc/vintf/manifest/fake_camera_hal.xml" ]; then
    set_perm "$MODPATH/system/vendor/etc/vintf/manifest/fake_camera_hal.xml" \
        root root 0644 "u:object_r:vendor_configs_file:s0"
fi
if [ -f "$MODPATH/system/vendor/etc/init/fake_camera_hal.rc" ]; then
    set_perm "$MODPATH/system/vendor/etc/init/fake_camera_hal.rc" \
        root root 0644 "u:object_r:vendor_configs_file:s0"
fi

ui_print "Binary installed: /vendor/bin/hw/android.hardware.camera.provider-fake"
ui_print "VINTF manifest installed"
ui_print "RC file installed"
ui_print "Reboot to activate FakeHAL"
ui_print "v7.4: Fixed VINTF duplicate conflict"

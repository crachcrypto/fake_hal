#!/system/bin/sh

# FakeHAL v7.4 service.sh
# KernelSU late_start hook.
# Kills stock Lyric camera provider and starts FakeHAL as cameraserver user.
# Pixel 7 (panther/cheetah) Android 13 — AIDL internal/0
#
# v7.4: Fix VINTF conflict — bind-mount EMPTY manifest over stock to eliminate
#        duplicate ICameraProvider/internal/0 entries. Properly stop and disable
#        stock Lyric provider (init .rc bind-mount is ineffective since init
#        already parsed .rc at boot). Active re-kill after cameraserver restart.

MODDIR="${0%/*}"
LOG="/data/local/tmp/fakehAL_service.log"
BINARY="$MODDIR/system/vendor/bin/hw/android.hardware.camera.provider-fake"
VIDEO_PATH="/data/local/tmp/fake_video.mp4"
STOCK_SERVICE="vendor.camera-provider-2-7-google"
STOCK_BINARY="android.hardware.camera.provider@2.7-service-google"

# Paths for bind-mount overlay
STOCK_VINTF="/vendor/etc/vintf/manifest/android.hardware.camera.provider@2.7-service-google-apex.xml"
EMPTY_VINTF="/data/local/tmp/empty_camera_vintf.xml"

log_msg() {
    echo "[$(date '+%H:%M:%S')] $1" >> "$LOG"
}

# Wait for boot_completed
i=0
while [ "$(getprop sys.boot_completed)" != "1" ]; do
    sleep 1
    i=$((i+1))
    if [ "$i" -ge 120 ]; then
        log_msg "ERROR: boot timeout after 120s"
        exit 1
    fi
done
sleep 2

log_msg "=== FakeHAL v7.4 service.sh start ==="

# Copy video if bundled with module
if [ -f "$MODDIR/fake_video.mp4" ] && [ ! -f "$VIDEO_PATH" ]; then
    cp "$MODDIR/fake_video.mp4" "$VIDEO_PATH"
    chmod 644 "$VIDEO_PATH"
    chown root:root "$VIDEO_PATH"
    log_msg "Copied video to $VIDEO_PATH"
fi

# Verify video file
if [ ! -f "$VIDEO_PATH" ]; then
    log_msg "WARNING: $VIDEO_PATH not found — camera will fail to open"
fi

# Verify binary exists
if [ ! -f "$BINARY" ]; then
    log_msg "ERROR: binary not found: $BINARY"
    exit 1
fi

# Set SELinux context on binary
chcon u:object_r:hal_camera_default_exec:s0 "$BINARY" 2>/dev/null
log_msg "SELinux context set on binary"

# Also ensure the video file is readable by cameraserver
chcon u:object_r:shell_data_file:s0 "$VIDEO_PATH" 2>/dev/null
chmod 644 "$VIDEO_PATH" 2>/dev/null

# Ensure the log file is writable
touch /data/local/tmp/fakehAL.log 2>/dev/null
chmod 666 /data/local/tmp/fakehAL.log 2>/dev/null
chcon u:object_r:shell_data_file:s0 /data/local/tmp/fakehAL.log 2>/dev/null

# ============================================================
# STEP 1: Bind-mount an EMPTY VINTF manifest over the stock one.
#
# WHY EMPTY (not our manifest): KernelSU's Magic Mount already
# overlays our fake_camera_hal.xml into /vendor/etc/vintf/manifest/.
# That file declares ICameraProvider/internal/0. If we bind-mounted
# our manifest OVER the stock path, cameraserver would see TWO files
# both declaring ICameraProvider/internal/0 — causing the exact
# same conflict we're trying to fix. By bind-mounting an EMPTY
# (but valid XML) manifest over the stock path, we remove the
# stock declaration. Only our overlay remains.
# ============================================================

# Create a minimal valid but empty VINTF manifest fragment.
# This must be valid XML that the VINTF framework can parse
# without error, but declares no HAL services.
cat > "$EMPTY_VINTF" << 'XMLEOF'
<manifest version="1.0" type="device">
</manifest>
XMLEOF

chmod 644 "$EMPTY_VINTF" 2>/dev/null
chcon u:object_r:vendor_configs_file:s0 "$EMPTY_VINTF" 2>/dev/null

if [ -f "$STOCK_VINTF" ]; then
    mount --bind "$EMPTY_VINTF" "$STOCK_VINTF"
    if [ $? -eq 0 ]; then
        log_msg "Bind-mounted EMPTY VINTF manifest over stock: $STOCK_VINTF"
        # Verify the bind-mount took effect
        if grep -q "ICameraProvider" "$STOCK_VINTF" 2>/dev/null; then
            log_msg "ERROR: Bind-mount verification failed — stock VINTF still has ICameraProvider!"
        else
            log_msg "Verified: stock VINTF no longer declares ICameraProvider"
        fi
    else
        log_msg "ERROR: Failed to bind-mount VINTF manifest over $STOCK_VINTF (rc=$?)"
        log_msg "CRITICAL: VINTF conflict will persist — cameraserver may crash!"
    fi
else
    log_msg "WARNING: Stock VINTF not found at $STOCK_VINTF — may already be removed"
fi

# ============================================================
# STEP 2: Stop stock Lyric camera provider thoroughly.
#
# NOTE on .rc bind-mount: We do NOT bind-mount over the stock
# .rc file because it is USELESS. Init parses all .rc files at
# early boot, long before service.sh runs at late_start. The
# service definition is already in init's in-memory state. A
# bind-mount over the .rc file only affects future file reads,
# but init never re-reads .rc files. Instead, we must use
# property-based controls to stop and disable the service.
# ============================================================

# Stop via init property control
setprop ctl.stop "$STOCK_SERVICE" 2>/dev/null
log_msg "Sent ctl.stop to $STOCK_SERVICE"

# Also stop via interface name — more reliable for AIDL services
setprop ctl.interface_stop "android.hardware.camera.provider/ICameraProvider/internal/0" 2>/dev/null
log_msg "Sent ctl.interface_stop for ICameraProvider/internal/0"

# Small delay for init to process the stop
sleep 1

# Force kill the stock binary as backup
killall -9 "$STOCK_BINARY" 2>/dev/null
log_msg "Sent killall -9 to $STOCK_BINARY"

# Wait and verify stock provider is dead
retries=0
while [ "$retries" -lt 10 ]; do
    if ! pidof "$STOCK_BINARY" > /dev/null 2>&1; then
        log_msg "Stock provider confirmed dead after $retries retries"
        break
    fi
    log_msg "Stock provider still alive, killing again (attempt $retries)..."
    setprop ctl.stop "$STOCK_SERVICE" 2>/dev/null
    killall -9 "$STOCK_BINARY" 2>/dev/null
    sleep 1
    retries=$((retries + 1))
done

if pidof "$STOCK_BINARY" > /dev/null 2>&1; then
    log_msg "ERROR: Could not kill stock provider after $retries attempts!"
fi

sleep 1

# ============================================================
# STEP 3: Start FakeHAL via init service.
#
# The .rc file installed by KernelSU overlay declares the
# fake_camera_hal service with user=cameraserver, group=camera,
# and the correct SELinux context. Using ctl.start is the proper
# Android way to launch HAL services.
# ============================================================

setprop ctl.start fake_camera_hal
log_msg "Started fake_camera_hal via setprop ctl.start"

# Wait for FakeHAL to register with servicemanager
sleep 3
BINARY_NAME="$(basename "$BINARY")"
alive_checks=0
while [ "$alive_checks" -lt 5 ]; do
    if pidof "$BINARY_NAME" > /dev/null 2>&1; then
        REAL_PID=$(pidof "$BINARY_NAME")
        log_msg "FakeHAL confirmed alive: PID=$REAL_PID (check $alive_checks)"
        break
    fi
    log_msg "FakeHAL not found, retrying start (attempt $alive_checks)..."

    # Retry: try init service first
    setprop ctl.start fake_camera_hal 2>/dev/null
    sleep 3
    if pidof "$BINARY_NAME" > /dev/null 2>&1; then
        REAL_PID=$(pidof "$BINARY_NAME")
        log_msg "FakeHAL confirmed alive via init service: PID=$REAL_PID"
        break
    fi

    # Fallback: manual launch if init service doesn't work
    # (e.g., if .rc file isn't properly overlay-mounted)
    log_msg "Init service failed, trying manual launch as root (fallback)..."
    nohup /system/bin/runcon u:r:hal_camera_default:s0 \
        "$BINARY" "$VIDEO_PATH" \
        >> /data/local/tmp/fakehAL.log 2>&1 &
    log_msg "FakeHAL manual fallback started: shell PID=$!"
    sleep 3

    alive_checks=$((alive_checks + 1))
done

if ! pidof "$BINARY_NAME" > /dev/null 2>&1; then
    log_msg "ERROR: FakeHAL failed to start after $alive_checks attempts!"
    log_msg "Dumping last 20 lines of fakehAL.log for debug:"
    tail -20 /data/local/tmp/fakehAL.log >> "$LOG" 2>/dev/null
fi

# ============================================================
# STEP 4: Restart cameraserver so it re-reads VINTF manifests.
#
# The bind-mounted VINTF now only shows our FakeHAL declaration
# (the stock declaration was replaced with an empty manifest).
# cameraserver will discover only ICameraProvider/internal/0
# from our fake_camera_hal.xml and connect to FakeHAL.
# ============================================================

sleep 2
setprop ctl.restart cameraserver
log_msg "cameraserver restarted (VINTF conflict resolved via bind-mount)"

# ============================================================
# STEP 5: Post-restart: re-kill stock if init auto-restarted it.
#
# CRITICAL: Since init still has Lyric's service definition in
# memory (the .rc bind-mount trick does NOT work), init MAY
# auto-restart the stock provider when cameraserver restarts or
# when it detects the service died. We MUST check and re-kill.
# ============================================================

sleep 3

# Check if stock came back and kill it persistently
post_retries=0
while [ "$post_retries" -lt 5 ]; do
    if pidof "$STOCK_BINARY" > /dev/null 2>&1; then
        log_msg "WARNING: Stock provider reappeared after cameraserver restart (attempt $post_retries)"
        setprop ctl.stop "$STOCK_SERVICE" 2>/dev/null
        setprop ctl.interface_stop "android.hardware.camera.provider/ICameraProvider/internal/0" 2>/dev/null
        killall -9 "$STOCK_BINARY" 2>/dev/null
        sleep 1
        post_retries=$((post_retries + 1))
    else
        log_msg "Confirmed: stock provider not running after cameraserver restart"
        break
    fi
done

if [ "$post_retries" -ge 5 ] && pidof "$STOCK_BINARY" > /dev/null 2>&1; then
    log_msg "ERROR: Stock provider keeps restarting! Init is respawning it."
    log_msg "Attempting continuous suppression via disable property..."
    # As a last resort, try to mark the service as disabled
    # This uses the init.svc. property to track state
    setprop ctl.stop "$STOCK_SERVICE" 2>/dev/null
    killall -9 "$STOCK_BINARY" 2>/dev/null
fi

# ============================================================
# STEP 6: Final verification
# ============================================================

sleep 2

# Verify FakeHAL is still alive after all the restarts
if pidof "$BINARY_NAME" > /dev/null 2>&1; then
    FINAL_PID=$(pidof "$BINARY_NAME")
    log_msg "SUCCESS: FakeHAL running (PID=$FINAL_PID)"
else
    log_msg "ERROR: FakeHAL not running at final check!"
    # One last attempt to start it
    setprop ctl.start fake_camera_hal 2>/dev/null
    sleep 2
    if pidof "$BINARY_NAME" > /dev/null 2>&1; then
        FINAL_PID=$(pidof "$BINARY_NAME")
        log_msg "RECOVERED: FakeHAL restarted (PID=$FINAL_PID)"
    else
        log_msg "FATAL: FakeHAL could not be started. Manual intervention needed."
        log_msg "Dumping last 20 lines of fakehAL.log for debug:"
        tail -20 /data/local/tmp/fakehAL.log >> "$LOG" 2>/dev/null
    fi
fi

# Final stock check
if pidof "$STOCK_BINARY" > /dev/null 2>&1; then
    log_msg "ERROR: Stock provider is STILL running at final check!"
else
    log_msg "Confirmed: stock provider dead at final check"
fi

log_msg "=== FakeHAL v7.4 service.sh complete ==="
log_msg "Watch: adb logcat -s FakeHAL"
log_msg "Enable debug: adb shell touch /data/local/tmp/fakehAL_debug"

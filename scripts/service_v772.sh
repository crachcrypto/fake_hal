#!/system/bin/sh
# FakeHAL v7.7.2 - service.sh
#
# PATCHED: auto-launch HAL at boot (removed exit 0 before STEP 4)
# Uses setsid for reliable daemonization

MODDIR="${0%/*}"
FAKE_BIN="${MODDIR}/files/fake_camera_provider"
VINTF_SRC="${MODDIR}/files/fake_camera_hal.xml"
LOG="/data/local/tmp/fakecam_v76.log"
VIDEO_DST="/data/local/tmp/fake_video.mp4"
BACK_VIDEO_DST="$VIDEO_DST"
FRONT_VIDEO_DST="$VIDEO_DST"
CONF_PATH="/data/local/tmp/fakehal.conf"
AIDL_SVC="android.hardware.camera.provider.ICameraProvider/internal/0"
FHAL_LOG="/data/local/tmp/fakehAL.log"
FHAL_ALIVE="/data/local/tmp/fakehAL_alive"
CRASH_COUNT_FILE="/data/local/tmp/fakehal_crash_count"

APEX_BIN="/apex/com.google.pixel.camera.hal/bin/hw/android.hardware.camera.provider@2.7-service-google"
VENDOR_BIN="/vendor/bin/hw/android.hardware.camera.provider@2.7-service-google"

VINTF_DEST="/vendor/etc/vintf/manifest/fake_camera_hal.xml"
STOCK_VINTF="/vendor/etc/vintf/manifest/android.hardware.camera.provider@2.7-service-google-apex.xml"

log() {
    echo "[$(date '+%H:%M:%S')] [service.sh] $1" >> "$LOG"
}

i=0
while [ "$(getprop sys.boot_completed)" != "1" ]; do
    sleep 1
    i=$((i+1))
    [ "$i" -ge 120 ] && { log "ERROR: boot timeout"; exit 1; }
done
sleep 5

log "============================================"
log "=== FakeHAL v7.7.2 service.sh ==="
log "============================================"

if [ -f "$CRASH_COUNT_FILE" ]; then
    COUNT=$(cat "$CRASH_COUNT_FILE" 2>/dev/null)
else
    COUNT=0
fi
COUNT=$((COUNT + 1))
echo "$COUNT" > "$CRASH_COUNT_FILE"
log "Boot attempt #$COUNT"

if [ "$COUNT" -ge 4 ]; then
    log "SAFETY: Too many boot attempts ($COUNT >= 4), aborting to prevent bootloop"
    exit 0
fi

if [ -f "${MODDIR}/fake_video.mp4" ] && [ ! -f "$VIDEO_DST" ]; then
    cp "${MODDIR}/fake_video.mp4" "$VIDEO_DST"
    chmod 644 "$VIDEO_DST"
    chcon u:object_r:shell_data_file:s0 "$VIDEO_DST" 2>/dev/null
    log "Copied video"
fi

if [ -f "$CONF_PATH" ]; then
    CONF_BACK=$(grep '^back_video=' "$CONF_PATH" 2>/dev/null | tail -1 | cut -d= -f2-)
    CONF_FRONT=$(grep '^front_video=' "$CONF_PATH" 2>/dev/null | tail -1 | cut -d= -f2-)
    [ -n "$CONF_BACK" ] && BACK_VIDEO_DST="$CONF_BACK"
    [ -n "$CONF_FRONT" ] && FRONT_VIDEO_DST="$CONF_FRONT"
fi

[ -f "$BACK_VIDEO_DST" ] || BACK_VIDEO_DST="$VIDEO_DST"
[ -f "$FRONT_VIDEO_DST" ] || FRONT_VIDEO_DST="$BACK_VIDEO_DST"
log "Media back=$BACK_VIDEO_DST front=$FRONT_VIDEO_DST"

for F in "$FHAL_LOG" "$FHAL_ALIVE"; do
    touch "$F" 2>/dev/null
    chmod 666 "$F" 2>/dev/null
    chcon u:object_r:shell_data_file:s0 "$F" 2>/dev/null
done

# STEP 0: SELinux rules
log "--- STEP 0: SELinux rules ---"
apply_sepolicy() {
    RULE="$1"
    if command -v ksud >/dev/null 2>&1; then
        ksud sepolicy patch "$RULE" 2>/dev/null
    elif command -v magiskpolicy >/dev/null 2>&1; then
        magiskpolicy --live "$RULE" 2>/dev/null
    elif command -v supolicy >/dev/null 2>&1; then
        supolicy --live "$RULE" 2>/dev/null
    fi
}
apply_sepolicy "allow init hal_camera_default process2 nosuid_transition"
apply_sepolicy "allow init hal_camera_default_exec file execute_no_trans"
apply_sepolicy "allow init hal_camera_default process transition"
apply_sepolicy "allow init hal_camera_default_exec file execute"
apply_sepolicy "allow init hal_camera_default_exec file { read open getattr map }"
apply_sepolicy "allow hal_camera_default shell_data_file file { read write create open getattr setattr map append }"
apply_sepolicy "allow hal_camera_default shell_data_file dir { search read write open getattr add_name create }"
apply_sepolicy "allow hal_camera_default vendor_configs_file file { read open getattr }"
apply_sepolicy "allow hal_camera_default tmpfs file { read write create open getattr map }"
apply_sepolicy "allow hal_camera_default servicemanager binder { call transfer }"
apply_sepolicy "allow hal_camera_default apex_data_file file { read open getattr execute map }"
apply_sepolicy "allow hal_camera_default apex_data_file dir { search read open getattr }"
log "SELinux rules applied"

# Remount vendor rw for VINTF bind-mount
mount -o remount,rw /vendor 2>/dev/null
# STEP 1: Bind-mount VINTF manifest
log "--- STEP 1: VINTF bind-mount ---"
VINTF_TMP="/data/local/tmp/fake_camera_hal.xml"
if [ -f "$VINTF_SRC" ]; then
    cp "$VINTF_SRC" "$VINTF_TMP"
else
    cat > "$VINTF_TMP" << 'XMLEOF'
<manifest version="2.0" type="device">
    <hal format="aidl">
        <name>android.hardware.camera.provider</name>
        <version>1</version>
        <interface>
            <name>ICameraProvider</name>
            <instance>internal/0</instance>
        </interface>
        <fqname>ICameraProvider/internal/0</fqname>
    </hal>
</manifest>
XMLEOF
fi
chmod 644 "$VINTF_TMP"
chcon u:object_r:vendor_configs_file:s0 "$VINTF_TMP" 2>/dev/null
if [ -f "$VINTF_DEST" ]; then
    mount --bind "$VINTF_TMP" "$VINTF_DEST" 2>/dev/null
else
    touch "$VINTF_DEST" 2>/dev/null
    mount --bind "$VINTF_TMP" "$VINTF_DEST" 2>/dev/null
fi
log "VINTF bind-mount: rc=$?"
mount -o remount,ro /vendor 2>/dev/null

# STEP 2: Suppress stock HIDL VINTF
log "--- STEP 2: Suppress stock VINTF ---"
if [ -f "$STOCK_VINTF" ]; then
    EMPTY_TMP="/data/local/tmp/empty_vintf.xml"
    cat > "$EMPTY_TMP" << 'EMPTYEOF'
<manifest version="1.0" type="device">
</manifest>
EMPTYEOF
    chmod 644 "$EMPTY_TMP"
    chcon u:object_r:vendor_configs_file:s0 "$EMPTY_TMP" 2>/dev/null
    mount --bind "$EMPTY_TMP" "$STOCK_VINTF" 2>/dev/null
    log "Stock VINTF suppressed: rc=$?"
fi

# STEP 3: Stop stock camera provider
log "--- STEP 3: Stop stock camera provider ---"
setprop ctl.stop vendor.camera-provider-2-7-google 2>/dev/null
sleep 1
pkill -f "camera.provider@2.7-service-google" 2>/dev/null
sleep 1
log "Stock camera provider stopped"

# STEP 4: Launch FakeHAL (auto-start, using setsid for daemonization)
log "--- STEP 4: Launch FakeHAL (autostart + setsid) ---"
cp "${MODDIR}/files/afbc_encoder.so" /data/local/tmp/ 2>/dev/null; cp "${MODDIR}/files/gralloc_uv_fix.so" /data/local/tmp/ 2>/dev/null; chmod 644 /data/local/tmp/afbc_encoder.so /data/local/tmp/gralloc_uv_fix.so 2>/dev/null; chcon u:object_r:shell_data_file:s0 /data/local/tmp/afbc_encoder.so /data/local/tmp/gralloc_uv_fix.so 2>/dev/null
FHAL_RUN="/data/local/tmp/fake_camera_provider"
cp "$FAKE_BIN" "$FHAL_RUN"
chmod 755 "$FHAL_RUN"
chcon u:object_r:shell_data_file:s0 "$FHAL_RUN" 2>/dev/null
chcon u:object_r:shell_data_file:s0 "$VIDEO_DST" 2>/dev/null
chcon u:object_r:shell_data_file:s0 "$BACK_VIDEO_DST" 2>/dev/null
chcon u:object_r:shell_data_file:s0 "$FRONT_VIDEO_DST" 2>/dev/null
> "$FHAL_LOG" 2>/dev/null
chmod 666 "$FHAL_LOG" 2>/dev/null

# Check if FakeHAL is already running on port 9080
if ss -tlnp 2>/dev/null | grep -q ":9080"; then
    log "FakeHAL already running on port 9080, skipping duplicate launch"
else
    pkill -9 -f fake_camera_provider 2>/dev/null
    sleep 1
    LD_PRELOAD=/data/local/tmp/afbc_encoder.so:/data/local/tmp/gralloc_uv_fix.so setsid "$FHAL_RUN" "$BACK_VIDEO_DST" "$FRONT_VIDEO_DST" >> "$FHAL_LOG" 2>&1 &
    PID1=$!
    sleep 5

    if kill -0 "$PID1" 2>/dev/null; then
        log "GOOD: FakeHAL launched (PID=$PID1) from $FHAL_RUN"
    else
        log "ERROR: FakeHAL launch failed!"
        [ -s "$FHAL_LOG" ] && { log "Binary output:"; cat "$FHAL_LOG" >> "$LOG" 2>/dev/null; }
    fi
fi

# STEP 5: Restart cameraserver (polling until cameras registered)
log "--- STEP 5: Restart cameraserver (polling) ---"
CAMERAS_OK=0
for attempt in 1 2 3 4 5; do
    setprop ctl.restart cameraserver
    sleep 5
    NCAM=$(dumpsys media.camera 2>/dev/null | grep "Number of camera devices:" | grep -oE "[0-9]+" | head -1)
    if [ "$NCAM" = "2" ]; then
        log "STEP 5: cameraserver sees $NCAM cameras on attempt $attempt"
        CAMERAS_OK=1
        break
    fi
    log "STEP 5: attempt $attempt - cameras=$NCAM, retrying..."
done
if [ "$CAMERAS_OK" != "1" ]; then
    log "STEP 5: WARNING - cameras not registered after 10 attempts!"
fi
sleep 3

# STEP 6: Check results
log "--- STEP 6: Check results ---"
DUMPSYS=$(dumpsys media.camera 2>/dev/null)
NUM_CAM=$(echo "$DUMPSYS" | grep "Number of camera devices:" | head -1)
log "Camera: $NUM_CAM"

if echo "$NUM_CAM" | grep -q ": 0"; then
    log "ERROR: 0 cameras! Diagnostics..."
    dmesg 2>/dev/null | grep -E "(avc.*camera|avc.*hal_camera|nosuid_transition|execute_no_trans)" | tail -20 >> "$LOG" 2>/dev/null
    ps -A 2>/dev/null | grep -iE "(camera|fake)" >> "$LOG" 2>/dev/null
    log "Service: $(service list 2>/dev/null | grep ICameraProvider)"
    if [ -s "$FHAL_LOG" ]; then
        log "Binary log (last 20):"
        tail -20 "$FHAL_LOG" >> "$LOG" 2>/dev/null
    fi
    logcat -d 2>/dev/null | grep -iE "(CameraProvider|CameraService|FakeHAL|nosuid|execute_no_trans|camera.*error)" | tail -30 >> "$LOG" 2>/dev/null
else
    log "SUCCESS: $NUM_CAM"
    echo "0" > "$CRASH_COUNT_FILE"
fi

# STEP 7: Rotation bridge daemon
BRIDGE=/data/adb/modules/FakeHAL/rotation_bridge.sh
if [ -x "$BRIDGE" ] && [ ! -f /data/local/tmp/.fakehal_bridge_off ]; then
    log "starting rotation_bridge daemon"
    rm -f /data/local/tmp/.rotation_bridge.pid
    (setsid sh "$BRIDGE" </dev/null >/data/local/tmp/rotation_bridge.out 2>&1 &)
fi

log "============================================"

# STEP 8: Source tracker daemon (tracks original filenames for HAL)
TRACKER_SRC="${MODDIR}/source_tracker.sh"
TRACKER_DST="/data/local/tmp/source_tracker.sh"
if [ -f "$TRACKER_SRC" ]; then
    cp "$TRACKER_SRC" "$TRACKER_DST"
    chmod 755 "$TRACKER_DST"
    chcon u:object_r:shell_data_file:s0 "$TRACKER_DST" 2>/dev/null
    pkill -f source_tracker 2>/dev/null
    sleep 1
    setsid sh "$TRACKER_DST" </dev/null >/dev/null 2>&1 &
    log "source_tracker daemon started (PID=$!)"
fi

log "=== FakeHAL v7.7.2 complete ==="
log "============================================"

# STEP 9: Config guard daemon (ensures rotation keys survive writeConfig)
GUARD_SRC="${MODDIR}/fakehal_watchdog.sh"
GUARD_DST="/data/local/tmp/fakehal_watchdog.sh"
if [ -f "$GUARD_SRC" ]; then
    if grep -q "HARDENED\|v2\|anti-bootloop" "$GUARD_SRC" 2>/dev/null; then
        cp "$GUARD_SRC" "$GUARD_DST"
        log "watchdog: deployed hardened v2 from module"
    else
        log "WARNING: watchdog in module is NOT hardened v2, skipping copy to preserve v2 on device"
    fi
    chmod 755 "$GUARD_DST"
    pkill -f fakehal_watchdog 2>/dev/null
    sleep 1
    setsid sh "$GUARD_DST" </dev/null >/dev/null 2>&1 &
    log "fakehal_watchdog daemon started (PID=$!)"
fi

# --- FH_FIREWALL: block apps from probing stream port 9080 (except FakeHAL controller uids) ---
fh_fw() {
  EXEMPT="10258 10280 10285 10286 10288"
  for IPT in iptables ip6tables; do
    while $IPT -D OUTPUT -p tcp --dport 9080 -m owner --uid-owner 10000-99999 -j DROP 2>/dev/null; do :; done
    for u in $EXEMPT; do while $IPT -D OUTPUT -p tcp --dport 9080 -m owner --uid-owner $u -j ACCEPT 2>/dev/null; do :; done; done
    $IPT -I OUTPUT -p tcp --dport 9080 -m owner --uid-owner 10000-99999 -j DROP 2>/dev/null
    for u in $EXEMPT; do $IPT -I OUTPUT -p tcp --dport 9080 -m owner --uid-owner $u -j ACCEPT 2>/dev/null; done
  done
}
fh_fw
log "FH_FIREWALL applied (9080 app-block)"

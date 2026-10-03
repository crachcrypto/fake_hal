#!/system/bin/sh
# fakehal_watchdog.sh v2 (HARDENED by engineer bootloop-fix 2026-06-24)
# Unified watchdog for FakeHAL. Handles: config keys, HAL process, cameraserver health.
# Logs to /data/local/tmp/watchdog.log
#
# v2 SAFETY CHANGES (anti-bootloop):
#  - KILL SWITCH: if /data/local/tmp/.fakehal_watchdog_off exists -> exit immediately.
#  - CRASH GUARD: stop restarting HAL after MAX_HAL_RESTARTS failures; sets kill switch.
#  - cameraserver restart is rate-limited (MAX_CAM_RESTARTS) so we never hammer
#    `setprop ctl.restart cameraserver` in a tight loop (root cause of repeat bootloop).

CONF=/data/local/tmp/fakehal.conf
HAL_BIN=/data/local/tmp/fake_camera_provider
AFBC_SO=/data/adb/modules/FakeHAL/afbc_encoder.so
UVFIX_SO=/data/adb/modules/FakeHAL/gralloc_uv_fix.so
HAL_LOG=/data/local/tmp/fakehAL.log
WLOG=/data/local/tmp/watchdog.log
BACK_VIDEO=/data/local/tmp/fakehal_back.mp4
FRONT_VIDEO=/data/local/tmp/fakehal_front.mp4
BOOT_FAST_PERIOD=3
NORMAL_PERIOD=10
STATUS_PERIOD=30

# --- anti-bootloop guard config ---
OFF_MARKER=/data/local/tmp/.fakehal_watchdog_off
MAX_HAL_RESTARTS=3      # after this many failed HAL restarts -> give up (no more restarts)
MAX_CAM_RESTARTS=3      # after this many cameraserver restarts -> stop hammering
HAL_RESTART_COUNT=0
CAM_RESTART_COUNT=0

wlog() { echo "$(date '+%Y-%m-%d %H:%M:%S') $1" >> "$WLOG"; }

# Honor the kill switch at any time.
check_killswitch() {
    if [ -f "$OFF_MARKER" ]; then
        wlog "KILL SWITCH present ($OFF_MARKER) — watchdog exiting (no restarts)."
        exit 0
    fi
}

ensure_key() {
    KEY="$1"; DEFAULT="$2"
    grep -q "^${KEY}=" "$CONF" 2>/dev/null || {
        echo "${KEY}=${DEFAULT}" >> "$CONF"
        wlog "config: added missing ${KEY}=${DEFAULT}"
    }
}

ensure_config() {
    [ -f "$CONF" ] || return
    ensure_key front_rotate_180 1
    ensure_key back_rotate_180 0
    ensure_key front_mirror 1
    ensure_key chrome_front_rotate_180 0
    ensure_key chrome_front_mirror 1
    ensure_key preview_rotation 0
    ensure_key preview_mirror_h 0
    ensure_key preview_mirror_v 0
    ensure_key gyro_enabled 0
    ensure_key gyro_strength 0.1
    ensure_key front_user_set 1
    ensure_key sensor_orientation 90
    ensure_key pre_normalized 0
}

check_hal() {
    PID=$(pidof fake_camera_provider 2>/dev/null)
    if [ -z "$PID" ]; then
        # CRASH GUARD: do not restart endlessly.
        if [ "$HAL_RESTART_COUNT" -ge "$MAX_HAL_RESTARTS" ]; then
            wlog "HAL DEAD but restart budget exhausted ($HAL_RESTART_COUNT/$MAX_HAL_RESTARTS) — NOT restarting. Setting kill switch."
            touch "$OFF_MARKER" 2>/dev/null
            check_killswitch
            return 1
        fi
        HAL_RESTART_COUNT=$((HAL_RESTART_COUNT + 1))
        wlog "HAL DEAD — restart attempt $HAL_RESTART_COUNT/$MAX_HAL_RESTARTS"
        LD_PRELOAD="$UVFIX_SO:$AFBC_SO" setsid "$HAL_BIN" "$BACK_VIDEO" "$FRONT_VIDEO" </dev/null >>"$HAL_LOG" 2>&1 &
        sleep 3
        PID=$(pidof fake_camera_provider 2>/dev/null)
        if [ -n "$PID" ]; then
            wlog "HAL restarted: pid=$PID"
            # rate-limited cameraserver restart
            if [ "$CAM_RESTART_COUNT" -lt "$MAX_CAM_RESTARTS" ]; then
                CAM_RESTART_COUNT=$((CAM_RESTART_COUNT + 1))
                setprop ctl.restart cameraserver
                sleep 5
                wlog "cameraserver restarted after HAL recovery ($CAM_RESTART_COUNT/$MAX_CAM_RESTARTS)"
            else
                wlog "cameraserver restart budget exhausted — skipping restart to avoid bootloop"
            fi
        else
            wlog "HAL RESTART FAILED (attempt $HAL_RESTART_COUNT/$MAX_HAL_RESTARTS)"
        fi
        return 1
    fi
    # HAL is alive and healthy -> reset budgets so a future isolated glitch still gets help.
    if [ "$HAL_RESTART_COUNT" -ne 0 ] || [ "$CAM_RESTART_COUNT" -ne 0 ]; then
        HAL_RESTART_COUNT=0
        CAM_RESTART_COUNT=0
    fi
    return 0
}

check_cameras() {
    # Only act on a clear "0 / empty" signal, and never hammer restarts.
    NCAM=$(dumpsys media.camera 2>/dev/null | grep "Number of camera devices:" | grep -oE "[0-9]+" | head -1)
    if [ "$NCAM" = "2" ]; then
        return 0
    fi
    if [ "$CAM_RESTART_COUNT" -ge "$MAX_CAM_RESTARTS" ]; then
        wlog "cameraserver sees '$NCAM' cameras but restart budget exhausted — NOT restarting (anti-bootloop)."
        return 1
    fi
    CAM_RESTART_COUNT=$((CAM_RESTART_COUNT + 1))
    wlog "cameraserver sees '$NCAM' cameras (expected 2) — restarting ($CAM_RESTART_COUNT/$MAX_CAM_RESTARTS)"
    setprop ctl.restart cameraserver
    sleep 5
    NCAM2=$(dumpsys media.camera 2>/dev/null | grep "Number of camera devices:" | grep -oE "[0-9]+" | head -1)
    wlog "after restart: cameras=$NCAM2"
    return 1
}

# --- main loop ---
check_killswitch
wlog "watchdog v2 (hardened) started (pid=$$) max_hal_restarts=$MAX_HAL_RESTARTS max_cam_restarts=$MAX_CAM_RESTARTS"
CYCLE=0
LAST_STATUS=0

while true; do
    check_killswitch
    CYCLE=$((CYCLE + 1))

    # faster checks during first 60 seconds
    if [ $CYCLE -le 20 ]; then
        SLEEP=$BOOT_FAST_PERIOD
    else
        SLEEP=$NORMAL_PERIOD
    fi

    ensure_config
    check_hal
    HAL_OK=$?

    # check cameras less frequently (every ~30s)
    NOW=$(date +%s 2>/dev/null || echo 0)
    if [ $((NOW - LAST_STATUS)) -ge $STATUS_PERIOD ] || [ $HAL_OK -ne 0 ]; then
        check_cameras
        LAST_STATUS=$NOW
        if [ $((CYCLE % 60)) -eq 0 ]; then
            PID=$(pidof fake_camera_provider 2>/dev/null)
            NCAM=$(dumpsys media.camera 2>/dev/null | grep "Number of camera devices:" | grep -oE "[0-9]+" | head -1)
            wlog "status: hal_pid=$PID cameras=$NCAM cycle=$CYCLE hal_restarts=$HAL_RESTART_COUNT cam_restarts=$CAM_RESTART_COUNT"
        fi
    fi

    sleep $SLEEP
done

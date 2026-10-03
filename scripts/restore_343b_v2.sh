#!/system/bin/sh
# Restore stock 343b53b8 — kill HAL BEFORE cp (fix Text file busy).
set -u
TMP=/data/local/tmp
HAL_BIN=$TMP/fake_camera_provider
STOCK_BAK=$TMP/fake_camera_provider.bak_343b53b8_before_chromafps_1784473329
AFBC_SO=/data/adb/modules/FakeHAL/afbc_encoder.so
BACK=$TMP/fakehal_back.mp4
FRONT=$TMP/fakehal_front.mp4
LOG=$TMP/fakehAL.log
OFF=$TMP/.fakehal_watchdog_off

echo "=== 0. freeze watchdog ==="
touch "$OFF"
pkill -f fakehal_watchdog 2>/dev/null
sleep 1
echo "watchdog remaining: $(pgrep -f fakehal_watchdog)"

echo "=== 1. kill HAL FIRST ==="
pkill -9 -f 'fake_camera_provider /data' 2>/dev/null
sleep 3
echo "remaining HAL pid: $(pidof fake_camera_provider)"

echo "=== 2. install stock 343b (now file not busy) ==="
cp "$STOCK_BAK" "$HAL_BIN"
chmod 755 "$HAL_BIN"
chcon u:object_r:shell_data_file:s0 "$HAL_BIN" 2>/dev/null
echo "installed file md5: $(md5sum $HAL_BIN)"

echo "=== 3. clear log ==="
: > "$LOG"
chmod 666 "$LOG"

echo "=== 4. restart HAL with LD_PRELOAD + 30fps sources ==="
LD_PRELOAD="$AFBC_SO" setsid "$HAL_BIN" "$BACK" "$FRONT" </dev/null >>"$LOG" 2>&1 &
sleep 5

echo "=== 5. verify ==="
PID=$(pidof fake_camera_provider)
echo "new pid=$PID"
echo "exe md5: $(md5sum /proc/$PID/exe 2>/dev/null)"
echo "afbc maps: $(grep -c afbc_encoder /proc/$PID/maps 2>/dev/null)"
tr '\0' ' ' < /proc/$PID/cmdline; echo

echo "=== 6. restart cameraserver ==="
setprop ctl.restart cameraserver
sleep 6
echo "cameras: $(dumpsys media.camera 2>/dev/null | grep 'Number of camera devices:' | head -1)"

echo "=== 7. release killswitch + restart watchdog ==="
rm -f "$OFF"
setsid sh /data/adb/modules/FakeHAL/fakehal_watchdog.sh </dev/null >/dev/null 2>&1 &
sleep 2
echo "watchdog pid: $(pgrep -f fakehal_watchdog)"
echo "=== verify watchdog target file still 343b (respawn safety) ==="
md5sum "$HAL_BIN"
echo "=== DONE ==="

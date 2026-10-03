#!/system/bin/sh
# Restore stock 343b53b8 HAL binary + LD_PRELOAD afbc_encoder.so, keep 30fps sources.
set -u
TMP=/data/local/tmp
HAL_BIN=$TMP/fake_camera_provider
STOCK_BAK=$TMP/fake_camera_provider.bak_343b53b8_before_chromafps_1784473329
AFBC_SO=/data/adb/modules/FakeHAL/afbc_encoder.so
BACK=$TMP/fakehal_back.mp4
FRONT=$TMP/fakehal_front.mp4
LOG=$TMP/fakehAL.log
OFF=$TMP/.fakehal_watchdog_off
STAMP=$(date +%s)

echo "=== 0. freeze watchdog (kill switch) ==="
touch "$OFF"
pkill -f fakehal_watchdog 2>/dev/null
sleep 1
echo "killswitch: $(ls -la $OFF)"

echo "=== 1. backup current running binary (52bc) ==="
CUR_MD5=$(md5sum "$HAL_BIN" | awk '{print $1}')
echo "current file md5=$CUR_MD5"
cp "$HAL_BIN" "$TMP/fake_camera_provider.bak_52bc_before_restore343b_$STAMP"
echo "saved bak: fake_camera_provider.bak_52bc_before_restore343b_$STAMP"

echo "=== 2. verify stock bak md5 (expect 343b53b8ec00ecc987ceb4bc7b2ae74d) ==="
md5sum "$STOCK_BAK"

echo "=== 3. install stock 343b over HAL_BIN (watchdog respawn target) ==="
cp "$STOCK_BAK" "$HAL_BIN"
chmod 755 "$HAL_BIN"
chcon u:object_r:shell_data_file:s0 "$HAL_BIN" 2>/dev/null
echo "installed file md5: $(md5sum $HAL_BIN)"

echo "=== 4. kill old HAL ==="
pkill -9 -f 'fake_camera_provider /data' 2>/dev/null
sleep 2
echo "remaining: $(pidof fake_camera_provider)"

echo "=== 5. clear log for clean counts ==="
: > "$LOG"
chmod 666 "$LOG"

echo "=== 6. restart HAL with LD_PRELOAD + 30fps sources ==="
echo "back=$BACK front=$FRONT"
ls -la "$BACK" "$FRONT"
LD_PRELOAD="$AFBC_SO" setsid "$HAL_BIN" "$BACK" "$FRONT" </dev/null >>"$LOG" 2>&1 &
sleep 5

echo "=== 7. verify running ==="
PID=$(pidof fake_camera_provider)
echo "new pid=$PID"
echo "exe md5: $(md5sum /proc/$PID/exe 2>/dev/null)"
echo "afbc maps: $(grep -c afbc_encoder /proc/$PID/maps 2>/dev/null)"
tr '\0' ' ' < /proc/$PID/cmdline; echo

echo "=== 8. restart cameraserver ==="
setprop ctl.restart cameraserver
sleep 6
echo "cameras: $(dumpsys media.camera 2>/dev/null | grep 'Number of camera devices:' | head -1)"

echo "=== 9. release watchdog killswitch + restart watchdog ==="
rm -f "$OFF"
setsid sh /data/adb/modules/FakeHAL/fakehal_watchdog.sh </dev/null >/dev/null 2>&1 &
sleep 2
echo "watchdog pid: $(pgrep -f fakehal_watchdog)"
echo "=== DONE ==="

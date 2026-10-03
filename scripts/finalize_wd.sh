#!/system/bin/sh
OFF=/data/local/tmp/.fakehal_watchdog_off
echo "=== current HAL ==="
PID=$(pidof fake_camera_provider)
echo "hal pid=$PID"
md5sum /proc/$PID/exe 2>/dev/null
echo "afbc=$(grep -c afbc_encoder /proc/$PID/maps 2>/dev/null)"
echo "=== watchdogs before ==="
pgrep -f fakehal_watchdog
echo "=== freeze + kill all watchdogs ==="
touch "$OFF"
pkill -9 -f fakehal_watchdog 2>/dev/null
sleep 2
echo "watchdogs after kill: $(pgrep -f fakehal_watchdog)"
echo "hal still alive: $(pidof fake_camera_provider)"
echo "=== start single watchdog ==="
rm -f "$OFF"
setsid sh /data/adb/modules/FakeHAL/fakehal_watchdog.sh </dev/null >/dev/null 2>&1 &
sleep 3
echo "watchdog now: $(pgrep -f fakehal_watchdog)"
echo "hal final: $(pidof fake_camera_provider)"
echo "file md5: $(md5sum /data/local/tmp/fake_camera_provider)"

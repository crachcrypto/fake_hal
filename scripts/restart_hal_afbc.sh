#!/system/bin/sh
export PATH=/system/bin:/system/xbin:/vendor/bin:$PATH
touch /data/local/tmp/.fakehal_watchdog_off
pkill -f fakehal_watchdog 2>/dev/null || true
pkill -9 -f fake_camera_provider 2>/dev/null || true
sleep 2
md5sum /data/local/tmp/fake_camera_provider
: > /data/local/tmp/fakehAL.log
chmod 666 /data/local/tmp/fakehAL.log
LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so \
  setsid /data/local/tmp/fake_camera_provider \
  /data/local/tmp/fakehal_back.mp4 \
  /data/local/tmp/fakehal_front.mp4 \
  </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &
sleep 4
p=$(pidof fake_camera_provider)
echo "pid=$p"
md5sum /proc/$p/exe
echo "maps=$(grep -c afbc /proc/$p/maps 2>/dev/null)"
tr '\0' '\n' < /proc/$p/environ 2>/dev/null | grep LD_PRELOAD
setprop ctl.restart cameraserver
sleep 3
rm -f /data/local/tmp/.fakehal_watchdog_off
setsid sh /data/adb/modules/FakeHAL/fakehal_watchdog.sh </dev/null >>/data/local/tmp/watchdog.log 2>&1 &
sleep 1
p=$(pidof fake_camera_provider)
echo "FINAL pid=$p maps=$(grep -c afbc /proc/$p/maps 2>/dev/null)"
pgrep -a fakehal_watchdog | head -2

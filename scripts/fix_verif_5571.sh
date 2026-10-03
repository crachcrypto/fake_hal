#!/system/bin/sh
# FakeHAL VERIF-PATH fix: swap 83a611e5 -> 343b53b8 (UVWRITE-FIX + multi-fd contiguous)
# Targets the 640x480 dual-YUV Onfido/gemini path where 83a611e5's old UV code
# fails: "mmap UV plane failed" + "may stripe on aligned bufs" -> banding/fringing.
set -x
TS=$(date +%s)
HAL_BIN=/data/local/tmp/fake_camera_provider
STAGE=/data/local/tmp/_stage_343b53b8.bin
AFBC_SO=/data/adb/modules/FakeHAL/afbc_encoder.so
BACK=/data/local/tmp/fakehal_back.mp4
FRONT=/data/local/tmp/fakehal_front.mp4
OFF=/data/local/tmp/.fakehal_watchdog_off
LOG=/data/local/tmp/fakehAL.log

echo "=== STEP 0: engage watchdog kill switch (temp) ==="
touch "$OFF"

echo "=== STEP 1: snapshot current ==="
ps -A | grep -iE 'fake_camera|provider-fake' | grep -v grep
md5sum "$HAL_BIN"

echo "=== STEP 2: verify staged binary md5 (expect 343b53b8) ==="
md5sum "$STAGE"

echo "=== STEP 3: kill current HAL ==="
pkill -9 -f fake_camera_provider 2>/dev/null
sleep 2
ps -A | grep -iE 'fake_camera|provider-fake' | grep -v grep || echo "NO_FAKE_HAL_PROCS"

echo "=== STEP 4: backup current 83a611e5 binary ==="
cp -f "$HAL_BIN" /data/local/tmp/fake_camera_provider.bak_83a611e5_before_verif_fix_${TS}
md5sum /data/local/tmp/fake_camera_provider.bak_83a611e5_before_verif_fix_${TS}

echo "=== STEP 5: install 343b53b8 ==="
cp -f "$STAGE" "$HAL_BIN"
chmod 755 "$HAL_BIN"
chown shell:shell "$HAL_BIN" 2>/dev/null
md5sum "$HAL_BIN"   # expect 343b53b8ec00ecc987ceb4bc7b2ae74d

echo "=== STEP 6: fresh log + clean single start (LD_PRELOAD afbc, matching watchdog convention) ==="
: > "$LOG"
chmod 666 "$LOG"
LD_PRELOAD="$AFBC_SO" setsid "$HAL_BIN" "$BACK" "$FRONT" </dev/null >>"$LOG" 2>&1 &
sleep 5

echo "=== STEP 7: verify single process + afbc + md5 ==="
ps -A | grep -iE 'fake_camera|provider-fake' | grep -v grep
PID=$(pidof fake_camera_provider)
echo "HAL pid=$PID  md5:"; md5sum /proc/$PID/exe 2>&1
AFBC=$(cat /proc/$PID/maps 2>/dev/null | grep -c afbc)
echo "AFBC_maps=$AFBC"

echo "=== STEP 8: restart cameraserver + count ==="
setprop ctl.restart cameraserver
sleep 6
dumpsys media.camera 2>/dev/null | grep "Number of camera devices:"

echo "=== STEP 9: release watchdog kill switch ==="
rm -f "$OFF"
echo "killswitch removed"
echo "=== DONE (verif fix 343b53b8) ==="

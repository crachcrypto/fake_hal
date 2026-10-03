#!/usr/bin/env bash
# Runs ON THE VPS. Deploys the chroma+fps patched HAL to 5571 (emulator-5570).
set -e
S=localhost:5571
ADB="adb -s $S"
NEWMD5=52bc1750b127ebfc25ce75ac5e949d48
TS=$(date +%s)

echo "== connect =="
adb connect $S 2>&1 || true
$ADB get-serialno

echo "== push staged binary to device =="
$ADB push /tmp/bin_chromafps /data/local/tmp/fake_camera_provider.chromafps
$ADB shell "su -c 'md5sum /data/local/tmp/fake_camera_provider.chromafps'"

echo "== engage watchdog killswitch, backup, install =="
$ADB shell "su -c '
  touch /data/local/tmp/.fakehal_watchdog_off 2>/dev/null || true
  echo 1 > /data/adb/modules/FakeHAL/.watchdog_off 2>/dev/null || true
  CUR=/data/local/tmp/fake_camera_provider
  cp \$CUR \${CUR}.bak_343b53b8_before_chromafps_'"$TS"'
  md5sum \${CUR}.bak_343b53b8_before_chromafps_'"$TS"'
  cp /data/local/tmp/fake_camera_provider.chromafps \$CUR
  chmod 755 \$CUR
  echo INSTALLED_MD5:; md5sum \$CUR
'"

echo "== restart HAL with LD_PRELOAD (afbc) =="
$ADB shell "su -c '
  pkill -9 -f fake_camera_provider 2>/dev/null; sleep 2
  : > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log
  LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so setsid /data/local/tmp/fake_camera_provider /data/local/tmp/fakehal_back.mp4 /data/local/tmp/fakehal_front.mp4 </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &
  sleep 4
  P=\$(pidof fake_camera_provider); echo HAL_PID=\$P
  echo AFBC_MAPS=\$(grep -c afbc /proc/\$P/maps 2>/dev/null)
  echo EXE_MD5:; md5sum /proc/\$P/exe
  setprop ctl.restart cameraserver
  sleep 6
  echo CAMERAS=\$(dumpsys media.camera 2>/dev/null | grep -c \"Camera 0\\|Camera 1\")
  rm -f /data/local/tmp/.fakehal_watchdog_off 2>/dev/null || true
  rm -f /data/adb/modules/FakeHAL/.watchdog_off 2>/dev/null || true
'"
echo "== DONE. expected EXE_MD5 = $NEWMD5 =="

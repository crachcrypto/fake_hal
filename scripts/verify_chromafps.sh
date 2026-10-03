#!/usr/bin/env bash
# Runs ON THE VPS. Verifies chroma path + measures FPS on 5571.
S=localhost:5571
ADB="adb -s $S"
adb connect $S 2>&1 || true

echo "=================== CHROMA PATH CHECK (640x480 verif) ==================="
$ADB shell "su -c '
  echo \"--- GARBAGECOFF-DIAG lines (should show mgCOff used, plCOff ignored) ---\"
  grep GARBAGECOFF-DIAG /data/local/tmp/fakehAL.log | tail -6
  echo \"--- multi-fd lockYCbCr geom (should be mali-handle / contigCOff=307200) ---\"
  grep \"multi-fd lockYCbCr\" /data/local/tmp/fakehAL.log | tail -4
  echo \"--- COUNTS ---\"
  echo mmap_UV_failed=\$(grep -c \"mmap UV plane failed\" /data/local/tmp/fakehAL.log)
  echo may_stripe=\$(grep -c \"may stripe on aligned bufs\" /data/local/tmp/fakehAL.log)
  echo geom_planelayouts=\$(grep -c \"geom=planelayouts\" /data/local/tmp/fakehAL.log)
  echo geom_malihandle=\$(grep -c \"geom=mali-handle\" /data/local/tmp/fakehAL.log)
  echo frames640=\$(grep -c \"640x480\" /data/local/tmp/fakehAL.log)
'"

echo "=================== ADVERTISED FPS (dumpsys) ==================="
$ADB shell "su -c 'dumpsys media.camera 2>/dev/null | grep -iE \"fps|frameDuration|minFrame\" | head -20'"

echo "=================== FPS MEASURE (frame_timing over live window) ==================="
$ADB shell "su -c '
  N1=\$(grep -c frame_timing /data/local/tmp/fakehAL.log)
  sleep 5
  N2=\$(grep -c frame_timing /data/local/tmp/fakehAL.log)
  echo \"frame_timing delta over 5s = \$((N2-N1)) -> FPS ~ \$(( (N2-N1)/5 ))\"
  echo \"--- last frame_timing lines (elapsedMs/sleepMs/res) ---\"
  grep frame_timing /data/local/tmp/fakehAL.log | tail -8
'"

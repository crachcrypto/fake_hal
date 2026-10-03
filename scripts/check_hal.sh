#!/system/bin/sh
for P in $(pgrep fake_camera_provider); do
  echo "===PID=$P"
  cat /proc/$P/cmdline 2>/dev/null | tr '\0' ' '; echo
  cat /proc/$P/environ 2>/dev/null | tr '\0' '\n' | grep LD_PRELOAD
  echo "uvfix=$(grep -c gralloc_uv_fix /proc/$P/maps 2>/dev/null) afbc=$(grep -c afbc_encoder /proc/$P/maps 2>/dev/null)"
done

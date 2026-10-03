#!/system/bin/sh
P=$(pidof fake_camera_provider)
echo "PID=$P"
for pp in $P; do
  echo "--- $pp"
  cat /proc/$pp/environ 2>/dev/null | tr '\0' '\n' | grep LD_PRELOAD
  echo "uvfix=$(grep -c gralloc_uv_fix /proc/$pp/maps 2>/dev/null) afbc=$(grep -c afbc_encoder /proc/$pp/maps 2>/dev/null)"
  md5sum /proc/$pp/exe 2>/dev/null
done
echo "SHIMDIAG:"
cat /data/local/tmp/shim_diag.txt 2>&1 | head -5

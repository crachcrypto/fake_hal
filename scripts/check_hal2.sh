#!/system/bin/sh
P=27506
echo "PID=$P"
cat /proc/$P/environ 2>/dev/null | tr '\0' '\n' | grep LD_PRELOAD
echo "uvfix=$(grep -c gralloc_uv_fix /proc/$P/maps 2>/dev/null) afbc=$(grep -c afbc_encoder /proc/$P/maps 2>/dev/null)"
echo "exe md5:"
md5sum /proc/$P/exe 2>/dev/null

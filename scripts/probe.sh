for p in 220 223 519 520 18473; do
  echo "pid=$p exe=$(readlink /proc/$p/exe 2>/dev/null)"
  md5sum /proc/$p/exe 2>/dev/null
done
echo "=== restart scripts ==="
ls -la /data/local/tmp/*.sh 2>/dev/null
echo "=== FakeHAL module ==="
ls -la /data/adb/modules/FakeHAL/ 2>/dev/null

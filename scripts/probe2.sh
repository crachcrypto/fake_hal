echo "=== ps for fake_camera / camera provider ==="
ps -A 2>/dev/null | grep -iE 'fake_cam|camera_provider|fakehal' | grep -v grep
echo "=== which pid actually runs /data/local/tmp/fake_camera_provider ==="
for p in $(ps -A -o PID 2>/dev/null | tail -n +2); do
  l=$(readlink /proc/$p/exe 2>/dev/null)
  case "$l" in
    *fake_camera_provider*) echo "pid=$p -> $l";;
  esac
done
echo "=== ss 9080/8080 ==="
ss -tlnp 2>/dev/null | grep -E '9080|8080'
echo "=== yuv23 prop ==="
getprop | grep -iE 'fakehal|yuv23' 2>/dev/null

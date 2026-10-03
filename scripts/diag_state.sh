#!/system/bin/sh
echo ===PIDS===
PIDS=$(pgrep -f 'fake_camera_provider /data')
echo "$PIDS"
echo ===EXE_MD5===
for p in $PIDS; do echo "pid $p"; md5sum /proc/$p/exe 2>/dev/null; done
echo ===FILE_MD5===
md5sum /data/local/tmp/fake_camera_provider 2>/dev/null
echo ===AFBC_MAPS===
for p in $PIDS; do echo "pid $p afbc maps: $(grep -c afbc_encoder /proc/$p/maps 2>/dev/null)"; done
echo ===CMDLINE===
for p in $PIDS; do echo "pid $p:"; tr '\0' ' ' < /proc/$p/cmdline; echo; done
echo ===VIDEO_SRC===
ls -la /data/local/tmp/fakehal_*.mp4 2>/dev/null
md5sum /data/local/tmp/fakehal_back.mp4 /data/local/tmp/fakehal_front.mp4 2>/dev/null
echo ===AFBC_SO===
ls -la /data/adb/modules/FakeHAL/afbc_encoder.so /data/local/tmp/afbc_encoder.so 2>/dev/null
echo ===LOG_TAIL_640===
grep -a '640' /data/local/tmp/fakehAL.log 2>/dev/null | tail -20
echo ===LOG_COUNTS===
echo "mali-handle: $(grep -ac 'geom=mali-handle' /data/local/tmp/fakehAL.log 2>/dev/null)"
echo "planelayouts: $(grep -ac 'geom=planelayouts' /data/local/tmp/fakehAL.log 2>/dev/null)"
echo "mmap UV failed: $(grep -ac 'mmap UV plane failed' /data/local/tmp/fakehAL.log 2>/dev/null)"
echo "may stripe: $(grep -ac 'may stripe' /data/local/tmp/fakehAL.log 2>/dev/null)"
echo ===CAM_CLIENT===
dumpsys media.camera 2>/dev/null | grep -a -i -E 'Client package|PID' | head -10

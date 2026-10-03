#!/system/bin/sh
# Deploy FakeHAL + license gate to a phone. Run from PC: push this folder to /sdcard, then run via su.
TMP=/data/local/tmp
for f in fake_camera_provider afbc_encoder.so gralloc_uv_fix.so fakehal.conf fake_camera_hal.xml fake_video.mp4 fake_video_front.mp4 fakehal_gate.sh; do
  cp -f /sdcard/fakehal_ready/$f $TMP/$f
done
chmod 755 $TMP/fake_camera_provider $TMP/fakehal_gate.sh
chmod 644 $TMP/*.so $TMP/*.conf $TMP/*.xml $TMP/*.mp4
chcon u:object_r:shell_data_file:s0 $TMP/fake_camera_provider $TMP/fakehal_gate.sh $TMP/*.so $TMP/*.mp4 $TMP/*.conf $TMP/*.xml
mkdir -p /data/adb/modules/FakeHAL
cp -f $TMP/afbc_encoder.so $TMP/gralloc_uv_fix.so /data/adb/modules/FakeHAL/
chmod 644 /data/adb/modules/FakeHAL/*.so
printf 'id=FakeHAL\nname=FakeHAL Camera Provider\nversion=v7.7.2\nversionCode=772\nauthor=FakeHAL Team\ndescription=Custom AIDL Camera Provider\n' > /data/adb/modules/FakeHAL/module.prop
echo 'Enter license key into /data/local/tmp/fakehal_key then run: sh /data/local/tmp/fakehal_gate.sh once'

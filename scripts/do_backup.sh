#!/system/bin/sh
# Full FakeHAL working-state backup (Pinterest spoof + OpenCamera 1.49.1 + Camera2 fix)
TS=$(date +%Y%m%d_%H%M%S)
STG=/data/local/tmp/backup_stage_$TS
OUT=/data/local/tmp/phone_backup_cam2green_$TS.tgz
mkdir -p "$STG"

# HAL binary + variants
cp -a /data/local/tmp/fake_camera_provider "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fake_camera_provider.bak_pre_gcam "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fake_camera_hal.xml "$STG"/ 2>/dev/null
# preload libs
cp -a /data/local/tmp/afbc_encoder.so "$STG"/ 2>/dev/null
cp -a /data/local/tmp/gralloc_uv_fix.so "$STG"/ 2>/dev/null
# videos (all slots) + clean photo
cp -a /data/local/tmp/fake_video.mp4 "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fake_video_front.mp4 "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fake_video.slot_a.mp4 "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fake_video.slot_b.mp4 "$STG"/ 2>/dev/null
cp -a /data/local/tmp/pin_clean.jpg "$STG"/ 2>/dev/null
# config + gate + key
cp -a /data/local/tmp/fakehal.conf "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fakehal_gate.sh "$STG"/ 2>/dev/null
cp -a /data/local/tmp/fakehal_key "$STG"/ 2>/dev/null
# autostart scripts
cp -a /data/local/tmp/service.sh "$STG"/service_tmp.sh 2>/dev/null
cp -a /data/adb/modules/FakeHAL/service.sh "$STG"/module_service.sh 2>/dev/null
cp -a /data/adb/service.d/fakehal_boot.sh "$STG"/serviced_fakehal_boot.sh 2>/dev/null
cp -a /data/adb/modules/FakeHAL/module.prop "$STG"/module.prop 2>/dev/null
# helper scripts
cp -a /data/local/tmp/set_camera2.sh "$STG"/ 2>/dev/null
cp -a /data/local/tmp/reinst_oc.sh "$STG"/ 2>/dev/null
cp -a /data/local/tmp/install_oc.sh "$STG"/ 2>/dev/null
# OpenCamera apk + prefs (Camera2 enabled)
cp -a /data/local/tmp/oc1491.apk "$STG"/ 2>/dev/null
cp -a /data/data/net.sourceforge.opencamera/shared_prefs/net.sourceforge.opencamera_preferences.xml "$STG"/opencamera_preferences.xml 2>/dev/null
# proof screenshot
cp -a /data/local/tmp/oc_cam2.png "$STG"/PROOF_green_oc_cam2.png 2>/dev/null

# manifest with md5 + versions + live props
{
  echo "FakeHAL backup $TS"
  echo "device serial: $(getprop ro.serialno)"
  echo "android: $(getprop ro.build.version.release)"
  echo "OpenCamera versionName: $(dumpsys package net.sourceforge.opencamera | grep versionName | head -1)"
  echo "camera_api pref: $(grep -o preference_camera_api_camera2 "$STG"/opencamera_preferences.xml)"
  echo "fakehal.stream.camera: $(getprop fakehal.stream.camera)"
  echo "HAL pid: $(pidof fake_camera_provider)"
  echo "--- md5 ---"
  md5sum "$STG"/* 2>/dev/null
} > "$STG"/MANIFEST.txt

cd /data/local/tmp && tar -czf "$OUT" "backup_stage_$TS" 2>/dev/null
echo "OUT=$OUT"
ls -la "$OUT"
md5sum "$OUT"
rm -rf "$STG"

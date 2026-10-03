#!/system/bin/sh
touch /data/local/tmp/.fakehal_watchdog_off
kill -9 $(pidof fake_camera_provider) 2>/dev/null
sleep 3
cp /data/local/tmp/fcp_patched_upload /data/local/tmp/fake_camera_provider
chmod 755 /data/local/tmp/fake_camera_provider
md5sum /data/local/tmp/fake_camera_provider > /data/local/tmp/swap_result.txt 2>&1
pidof fake_camera_provider >> /data/local/tmp/swap_result.txt 2>&1
echo "swap done" >> /data/local/tmp/swap_result.txt

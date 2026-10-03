#!/bin/bash
# Деплой GCam-фикса на телефон. Запускать на VPS ПОСЛЕ поднятия туннеля (порт 5555).
set -e
D=localhost:5559
BIN=/root/fakehal_gcam_build/fake_camera_provider
adb connect $D
echo '[*] backup current binary'
adb -s $D shell "su -c 'cp /data/local/tmp/fake_camera_provider /data/local/tmp/fake_camera_provider.bak_pre_gcam 2>/dev/null || true'"
echo '[*] push new binary'
adb -s $D push $BIN /data/local/tmp/fake_camera_provider
adb -s $D shell "su -c 'chmod 755 /data/local/tmp/fake_camera_provider'"
echo '[*] restart HAL via gate'
adb -s $D shell "su -c 'sh /data/local/tmp/fakehal_gate.sh once'"
sleep 2
echo -n '[*] HAL pid: '; adb -s $D shell "su -c 'pidof fake_camera_provider'"
echo '[+] done. Now test GCam and Open Camera.'

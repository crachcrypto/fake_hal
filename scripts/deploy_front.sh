#!/bin/bash
D=localhost:5559
V=${1:-/root/front_180.mp4}
timeout 12 adb connect $D >/dev/null 2>&1
timeout 20 adb -s $D shell 'su -c "cp -n /data/local/tmp/fake_video_front.mp4 /data/local/tmp/fake_video_front.geomfix.bak.mp4"'
timeout 60 adb -s $D push $V /data/local/tmp/new_front.mp4
timeout 20 adb -s $D shell 'su -c "cp /data/local/tmp/new_front.mp4 /data/local/tmp/fake_video_front.mp4; chmod 666 /data/local/tmp/fake_video_front.mp4"'
timeout 12 adb -s $D reverse tcp:8787 tcp:8787
timeout 30 adb -s $D shell 'su -c "sh /data/local/tmp/fakehal_gate.sh once"'
timeout 12 adb -s $D shell 'su -c "setprop ctl.restart cameraserver"'
sleep 5
timeout 12 adb -s $D shell 'su -c "dumpsys media.camera | grep -c internal/0"'

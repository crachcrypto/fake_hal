#!/bin/bash
D=localhost:5559
N=$1
timeout 12 adb -s $D shell 'su -c "am force-stop com.android.chrome"'
sleep 1
timeout 12 adb -s $D shell 'su -c "am start -a android.intent.action.VIEW -d https://webcamtests.com/ -n com.android.chrome/com.google.android.apps.chrome.Main"' >/dev/null
sleep 10
timeout 12 adb -s $D shell 'su -c "input tap 540 1528"'
sleep 8
timeout 20 adb -s $D shell "su -c \"screencap -p /data/local/tmp/c$N.png\""
timeout 20 adb -s $D pull /data/local/tmp/c$N.png /root/progress_$N.png

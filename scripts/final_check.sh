#!/bin/bash
D=localhost:5559
timeout 12 adb -s $D shell 'su -c "am force-stop com.android.chrome"'
sleep 1
timeout 12 adb -s $D shell 'su -c "am force-stop net.sourceforge.opencamera"'
sleep 1
timeout 12 adb -s $D shell 'su -c "am start -n net.sourceforge.opencamera/net.sourceforge.opencamera.MainActivity"' >/dev/null
sleep 7
timeout 20 adb -s $D shell 'su -c "screencap -p /data/local/tmp/gp.png"'
timeout 20 adb -s $D pull /data/local/tmp/gp.png /root/geomfix_preview.png
timeout 12 adb -s $D shell 'su -c "input keyevent KEYCODE_CAMERA"'
sleep 6
LAST=$(timeout 12 adb -s $D shell 'su -c "ls -t /sdcard/DCIM/OpenCamera/*.jpg | head -1"' | tr -d '\r')
echo "LASTJPG=$LAST"
timeout 12 adb -s $D shell "su -c \"cp $LAST /data/local/tmp/last.jpg; chmod 666 /data/local/tmp/last.jpg\""
timeout 30 adb -s $D pull /data/local/tmp/last.jpg /root/geomfix_photo.jpg
identify /root/geomfix_photo.jpg 2>/dev/null || ffprobe -v error -select_streams v:0 -show_entries stream=width,height -of csv=p=0 /root/geomfix_photo.jpg
exiftool -Orientation -ImageWidth -ImageHeight /root/geomfix_photo.jpg 2>/dev/null

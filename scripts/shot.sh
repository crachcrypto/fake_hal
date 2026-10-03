#!/bin/sh
# screenshot helper: $1 = remote label
D=localhost:5559
timeout 20 adb -s $D shell "su -c \"screencap -p /data/local/tmp/$1.png\""
timeout 20 adb -s $D pull /data/local/tmp/$1.png /root/shots/$1.png
ls -l /root/shots/$1.png

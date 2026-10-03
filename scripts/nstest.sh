#!/system/bin/sh
echo ---NSENTER-LS
nsenter -t 1223 -m -- ls -la /data/adb/lspd/config 2>&1
echo ---REALPATH-via-mountinfo
grep -iE "adb|lspd" /proc/1223/mountinfo 2>&1
echo ---MAPS-DB
grep -i "modules_config" /proc/1223/maps 2>&1 | head
echo ---OPEN-REDIRECT
cat /data/adb/susfs4ksu/sus_open_redirect.txt

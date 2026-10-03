#!/system/bin/sh
echo ---FD
ls -la /proc/1223/fd 2>&1 | grep -iE "db|lspd|config|\.log"
echo ---MOUNTINFO
grep -iE "lspd|/data/adb" /proc/1223/mountinfo
echo ---ROOTNS
readlink /proc/1223/ns/mnt
readlink /proc/1/ns/mnt
readlink /proc/self/ns/mnt

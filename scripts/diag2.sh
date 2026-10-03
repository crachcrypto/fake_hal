#!/system/bin/sh
echo ---NSENTER
which nsenter
ls -la /data/adb/ksu/bin 2>&1 | head
echo ---LOGTAIL
cat /proc/1223/fd/47 > /data/local/tmp/verbose.log 2>/dev/null
echo logsz=$(stat -c %s /data/local/tmp/verbose.log)
echo ---GREP-SCOPE
grep -iE "spoofkit|cached scope|browserguard|cache" /data/local/tmp/verbose.log | tail -40

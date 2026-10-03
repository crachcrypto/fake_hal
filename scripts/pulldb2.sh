#!/system/bin/sh
for FD in 60 66; do
  cat /proc/1223/fd/$FD > /data/local/tmp/mc.db 2>/data/local/tmp/err_$FD
  echo "fd=$FD rc=$? sz=$(stat -c %s /data/local/tmp/mc.db) err=$(cat /data/local/tmp/err_$FD)"
done
chmod 666 /data/local/tmp/mc.db*

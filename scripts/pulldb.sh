#!/system/bin/sh
cat /proc/1223/fd/60 > /data/local/tmp/mc.db 2>/tmp/e1; echo db=$? sz=$(stat -c %s /data/local/tmp/mc.db)
cat /proc/1223/fd/61 > /data/local/tmp/mc.db-wal 2>/dev/null; echo wal=$? sz=$(stat -c %s /data/local/tmp/mc.db-wal 2>/dev/null)
cat /proc/1223/fd/62 > /data/local/tmp/mc.db-shm 2>/dev/null; echo shm=$? sz=$(stat -c %s /data/local/tmp/mc.db-shm 2>/dev/null)
chmod 666 /data/local/tmp/mc.db*

#!/system/bin/sh
PID=$(ps -A | awk '$NF=="lspd"{print $2; exit}')
echo lspd_pid=$PID
nsenter -t "$PID" -m -- sh -c 'cat /data/adb/lspd/config/modules_config.db > /data/local/tmp/live.db; cat /data/adb/lspd/config/modules_config.db-wal > /data/local/tmp/live.db-wal; cat /data/adb/lspd/config/modules_config.db-shm > /data/local/tmp/live.db-shm'
chmod 666 /data/local/tmp/live.db*
ls -l /data/local/tmp/live.db*

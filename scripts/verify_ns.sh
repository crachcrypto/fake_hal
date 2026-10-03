#!/system/bin/sh
PID=$(ps -A | awk '$NF=="lspd"{print $2; exit}')
nsenter -t "$PID" -m -- sh -c 'cat /data/adb/lspd/config/modules_config.db > /data/local/tmp/check.db'
chmod 666 /data/local/tmp/check.db
ls -l /data/local/tmp/check.db

#!/system/bin/sh
PID=$(ps -A | awk '$NF=="lspd"{print $2; exit}')
echo lspd_pid=$PID
nsenter -t "$PID" -m -- sh -c '
  D=/data/adb/lspd/config
  cp $D/modules_config.db /data/local/tmp/backup_before_fix.db 2>/dev/null
  cat /data/local/tmp/fixed.db > $D/modules_config.db
  : > $D/modules_config.db-wal
  : > $D/modules_config.db-shm
  echo "--- after apply, scope table dump via strings:"
  ls -l $D
'

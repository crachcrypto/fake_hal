P=1198
B=/proc/$P/root/data/adb/lspd/config
cp $B/modules_config.db /sdcard/live.db 2>&1
cp $B/modules_config.db-wal /sdcard/live.db-wal 2>/dev/null
cp $B/modules_config.db-shm /sdcard/live.db-shm 2>/dev/null
chmod 666 /sdcard/live.db* 2>/dev/null
ls -la /sdcard/live.db*

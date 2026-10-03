P=1198
B=/proc/$P/root/data/adb/lspd/config
: > /sdcard/empty
echo "before size: $(ls -l $B/modules_config.db 2>&1)"
cp /sdcard/live_fixed.db $B/modules_config.db 2>&1 && echo "DB_WRITTEN"
cp /sdcard/empty $B/modules_config.db-wal 2>&1 && echo "WAL_EMPTIED"
cp /sdcard/empty $B/modules_config.db-shm 2>&1 && echo "SHM_EMPTIED"
echo "after size: $(ls -l $B/modules_config.db 2>&1)"
sync

#!/system/bin/sh
echo ===MODULE_DIR===
ls -la /data/adb/modules/FakeHAL/ 2>&1
echo ===SERVICE_SH===
cat /data/adb/modules/FakeHAL/service.sh 2>&1
echo ===POST_FS===
cat /data/adb/modules/FakeHAL/post-fs-data.sh 2>&1
echo ===RESTART_HAL===
cat /data/adb/modules/FakeHAL/restart_hal.sh 2>&1
echo ===WATCHDOG_FILES===
ls -la /data/adb/modules/FakeHAL/*.sh 2>&1
echo ===ANY_WATCHDOG_PROC===
ps -ef | grep -a -E 'watchdog|service.sh|restart_hal' | grep -v grep

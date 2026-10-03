#!/system/bin/sh
# FakeHAL boot autostart (KernelSU late_start service)
# Waits for boot completion + network, then launches the license gate watchdog loop,
# which verifies the key and keeps the HAL up.

MODDIR=${0%/*}
LOG=/data/local/tmp/fakehal_boot.log
echo "$(date '+%F %T') service.sh start" > "$LOG"

# wait for boot completed
i=0
while [ "$(getprop sys.boot_completed)" != "1" ] && [ $i -lt 120 ]; do
  sleep 2; i=$((i+1))
done
echo "$(date '+%F %T') boot_completed=$(getprop sys.boot_completed)" >> "$LOG"

# give network a moment
sleep 15

# launch gate watchdog loop (detached); it verifies license and keeps HAL alive
if [ -f /data/local/tmp/fakehal_gate.sh ]; then
  setsid sh /data/local/tmp/fakehal_gate.sh loop >/data/local/tmp/fakehal_gate.out 2>&1 &
  echo "$(date '+%F %T') gate loop launched pid=$!" >> "$LOG"
else
  echo "$(date '+%F %T') ERROR gate script missing" >> "$LOG"
fi

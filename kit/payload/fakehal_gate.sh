#!/system/bin/sh
# FakeHAL license gate + watchdog.
# - Reads key from /data/local/tmp/fakehal_key
# - Verifies against VPS /verify endpoint
# - valid   -> (re)starts HAL if not running
# - invalid -> kills HAL and refuses to start until a working key is written
#
# Install: /data/local/tmp/fakehal_gate.sh  (chmod 755, chcon shell_data_file)
# Run once (start+gate):     sh /data/local/tmp/fakehal_gate.sh once
# Run as watchdog loop:      setsid sh /data/local/tmp/fakehal_gate.sh loop &
#
# The FakeControl START button should call: sh /data/local/tmp/fakehal_gate.sh once

VPS="144.31.148.224:8787"        # verify endpoint host:port
KEYFILE="/data/local/tmp/fakehal_key"
STATUSFILE="/data/local/tmp/fakehal_gate.status"
LOG="/data/local/tmp/fakehal_gate.log"
INTERVAL=300                     # watchdog check every 5 min
TMP=/data/local/tmp

log(){ echo "$(date '+%Y-%m-%d %H:%M:%S') $*" >> "$LOG"; }

device_id(){
  getprop ro.serialno 2>/dev/null || settings get secure android_id 2>/dev/null || echo unknown
}

verify(){
  KEY=$(cat "$KEYFILE" 2>/dev/null | tr -d ' \r\n')
  DEV=$(device_id)
  if [ -z "$KEY" ]; then echo '{"valid":false,"reason":"no_key"}'; return; fi
  URL="http://$VPS/verify?key=$KEY&device=$DEV"
  # Find an HTTP tool: curl, wget, or busybox (KernelSU/Magisk absolute paths).
  BB=""
  for c in /data/adb/ksu/bin/busybox /data/adb/magisk/busybox busybox; do
    if command -v "$c" >/dev/null 2>&1 || [ -x "$c" ]; then BB="$c"; break; fi
  done
  if command -v curl >/dev/null 2>&1; then
    curl -s -m 8 "$URL" 2>/dev/null
  elif command -v wget >/dev/null 2>&1; then
    wget -q -T 8 -O - "$URL" 2>/dev/null
  elif [ -n "$BB" ]; then
    "$BB" wget -q -T 8 -O - "$URL" 2>/dev/null
  else
    echo '{"valid":false,"reason":"no_http_tool"}'
  fi
}

is_valid(){
  R=$(verify)
  echo "$R" > "$STATUSFILE"
  case "$R" in
    *'"valid":true'*|*'"valid": true'*) return 0 ;;
    *) return 1 ;;
  esac
}

hal_running(){ pidof fake_camera_provider >/dev/null 2>&1; }

start_hal(){
  hal_running && { log "HAL already running pid=$(pidof fake_camera_provider)"; return 0; }
  : > "$TMP/fakehAL.log"; chmod 666 "$TMP/fakehAL.log"
  LD_PRELOAD="$TMP/afbc_encoder.so:$TMP/gralloc_uv_fix.so" \
    setsid "$TMP/fake_camera_provider" "$TMP/fake_video.mp4" "$TMP/fake_video_front.mp4" \
    </dev/null >>"$TMP/fakehAL.log" 2>&1 &
  sleep 4
  setprop ctl.restart cameraserver
  sleep 3
  if hal_running; then log "HAL started pid=$(pidof fake_camera_provider)"; return 0
  else log "HAL FAILED to start"; return 1; fi
}

stop_hal(){
  if hal_running; then
    pkill -9 -f fake_camera_provider 2>/dev/null
    setprop ctl.restart vendor.camera-provider-2-7-google 2>/dev/null
    log "HAL KILLED (license gate)"
  fi
}

gate_once(){
  if is_valid; then
    log "license OK -> ensure HAL up"
    start_hal
  else
    R=$(cat "$STATUSFILE" 2>/dev/null)
    log "license INVALID ($R) -> kill + block"
    stop_hal
    return 1
  fi
}

case "$1" in
  once)  gate_once ;;
  stop)  stop_hal ;;
  status) cat "$STATUSFILE" 2>/dev/null ;;
  loop)
    log "watchdog loop start (interval=${INTERVAL}s)"
    while true; do
      gate_once
      sleep "$INTERVAL"
    done
    ;;
  *) echo "usage: $0 once|loop|stop|status" ;;
esac

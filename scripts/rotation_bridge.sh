#!/system/bin/sh
# rotation_bridge.sh v5.1 - source-file-swap rotation with detailed debug logging
#
# Changes from v5.0:
# - Added detailed NDJSON debug logging to /data/local/tmp/rotation_debug.log
# - Added resolution tracking for swapped files
# - Added timing instrumentation for each operation
# - Added status file /data/local/tmp/.rotation_status.json for UI consumption

CTRL_PKG="com.android.systemui.settings"
CONF=/data/local/tmp/fakehal.conf
DUMP=/data/local/tmp/.rb_dump.xml
STATES=/data/local/tmp/.rb_states.txt
LOG=/data/local/tmp/rotation_bridge.log
DEBUG_LOG=/data/local/tmp/rotation_debug.log
STATUS_FILE=/data/local/tmp/.rotation_status.json
KILL=/data/local/tmp/.fakehal_bridge_off
PID_FILE=/data/local/tmp/.rotation_bridge.pid
BB=/data/adb/ksu/bin/busybox

VID_BACK_BASE=/data/local/tmp/fake_video.mp4
VID_FRONT_BASE=/data/local/tmp/fake_video_front.mp4
ROT_DIR=/data/local/tmp

COOLDOWN=8

log(){ ts=$(date +%H:%M:%S); echo "[$ts] $*" >> "$LOG"; }
rotate_log(){ sz=$(stat -c %s "$LOG" 2>/dev/null); [ "${sz:-0}" -gt 262144 ] && mv -f "$LOG" "$LOG.1"; }

dlog(){
  ts=$(date +%s%N 2>/dev/null || date +%s)
  printf '{"ts":%s,"hyp":"%s","msg":"%s","data":{%s}}\n' "$ts" "$1" "$2" "$3" >> "$DEBUG_LOG"
}

write_status(){
  cat > "$STATUS_FILE" << EOFSTATUS
{"rot":$1,"cam":"$2","sen":$3,"mh":$4,"mv":$5,"back_file_sz":$6,"front_file_sz":$7,"hal_pid":"$8","bridge_pid":"$$","ts":"$(date +%H:%M:%S)","last_swap":"$9"}
EOFSTATUS
  chmod 644 "$STATUS_FILE"
}

conf_get(){ grep -E "^${1}=" "$CONF" 2>/dev/null | head -1 | sed -E "s/^[^=]*=//" ; }
conf_set(){
  if grep -q "^${1}=" "$CONF" 2>/dev/null; then
    sed -i "s|^${1}=.*|${1}=${2}|" "$CONF"
  else
    echo "${1}=${2}" >> "$CONF"
  fi
}

[ -f "$KILL" ] && { echo "bridge disabled"; exit 0; }

for pid in $(ls /proc 2>/dev/null | grep -E '^[0-9]+$'); do
  [ "$pid" = "$$" ] && continue
  cmd=$(tr '\0' ' ' < /proc/"$pid"/cmdline 2>/dev/null)
  case "$cmd" in
    *rotation_bridge.sh*)
      case "$cmd" in *fake_camera_provider*) continue ;; esac
      kill -9 "$pid" 2>/dev/null
      ;;
  esac
done
sleep 1

echo $$ > "$PID_FILE"
log "===== rotation_bridge v5.1 starting (pid=$$) ====="
dlog "H0" "bridge_start" "\"pid\":$$,\"version\":\"5.1\""

sed -i 's/^qr_mode=.*/qr_mode=1/' "$CONF"
sed -i 's/^noise_level=.*/noise_level=0/' "$CONF"
if ! grep -q "^qr_mode=" "$CONF"; then echo "qr_mode=1" >> "$CONF"; fi
if ! grep -q "^noise_level=" "$CONF"; then echo "noise_level=0" >> "$CONF"; fi
sed -i 's/^auto_rotate_portrait=.*/auto_rotate_portrait=0/' "$CONF"
if ! grep -q "^auto_rotate_portrait=" "$CONF"; then echo "auto_rotate_portrait=0" >> "$CONF"; fi
conf_set preview_rotation 0
conf_set back_rotate_180  0
conf_set front_rotate_180 1
log "noise guard + preview_rotation=0 enforced"

LAST=""
LAST_RESTART_TS=0
LAST_SEN=$(conf_get sensor_orientation); [ -z "$LAST_SEN" ] && LAST_SEN=90
LAST_ROT=""
LAST_CAM=""

swap_source(){
  swap_cam="$1"; swap_rot="$2"
  case "$swap_cam" in
    back)  base="$VID_BACK_BASE";  prefix="fake_video_rot" ;;
    front) base="$VID_FRONT_BASE"; prefix="fake_video_front_rot" ;;
    *) log "swap_source: bad cam '$swap_cam'"; return 1 ;;
  esac
  src="$ROT_DIR/${prefix}${swap_rot}.mp4"
  if [ ! -f "$src" ]; then
    log "swap_source: MISSING variant $src"
    dlog "H1" "swap_missing" "\"cam\":\"$swap_cam\",\"rot\":$swap_rot,\"file\":\"$src\""
    return 1
  fi
  src_sz=$(stat -c %s "$src" 2>/dev/null)
  cur_md5=$(md5sum "$base" 2>/dev/null | awk '{print $1}')
  new_md5=$(md5sum "$src"  2>/dev/null | awk '{print $1}')
  if [ "$cur_md5" = "$new_md5" ]; then
    log "swap_source: $swap_cam already at rot=$swap_rot (md5 match)"
    dlog "H4" "swap_skip_md5" "\"cam\":\"$swap_cam\",\"rot\":$swap_rot,\"md5\":\"$cur_md5\""
    return 2
  fi
  cp -f "$src" "$base"
  after_sz=$(stat -c %s "$base" 2>/dev/null)
  log "swap_source: $swap_cam rot=$swap_rot -> $base ($after_sz bytes)"
  dlog "H3" "swap_done" "\"cam\":\"$swap_cam\",\"rot\":$swap_rot,\"src_sz\":$src_sz,\"dst_sz\":$after_sz,\"src_md5\":\"$new_md5\""
  return 0
}

cleanup(){ rm -f "$PID_FILE" "$DUMP" "$STATES"; log "stopped"; exit 0; }
trap cleanup TERM INT

restart_hal(){
  t0=$(date +%s)
  killall -9 android.hardware.camera.provider-fake 2>/dev/null
  setprop fakehal.stream.port 9080
  setprop fakehal.stream.camera front
  sleep 2
  cd /data/local/tmp
  nohup setsid ./android.hardware.camera.provider-fake /data/local/tmp/fake_video.mp4 /data/local/tmp/fake_video_front.mp4 </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &
  disown 2>/dev/null
  sleep 3
  NEW=$(pidof android.hardware.camera.provider-fake)
  t1=$(date +%s)
  elapsed=$((t1 - t0))
  log "  HAL pid after restart: ${NEW:-NONE} (${elapsed}s)"
  dlog "H4" "hal_restart" "\"new_pid\":\"${NEW:-NONE}\",\"elapsed_s\":$elapsed"
}

parse_dump(){
  "$BB" awk -v RS='>' '
    BEGIN { last_chk = "" }
    /class="android.view.View"/ && /checkable="true"/ {
      if (match($0, /checked="[a-z]+"/)) {
        last_chk = substr($0, RSTART + 9, RLENGTH - 10)
      }
      next
    }
    /class="android.widget.TextView"/ {
      if (last_chk != "" && match($0, /text="[^"]*"/)) {
        print last_chk "|" substr($0, RSTART + 6, RLENGTH - 7)
        last_chk = ""
      }
    }
  ' "$DUMP" > "$STATES"
}

parse_rot_badge(){
  tr '>' '\n' < "$DUMP" | grep "TextView" | grep -E 'text="[0-9]+°"' | while IFS= read -r line; do
    txt=$(printf '%s' "$line" | sed -nE 's/.*text="([0-9]+)°".*/\1/p')
    bounds=$(printf '%s' "$line" | sed -nE 's/.*bounds="(\[[0-9]+,[0-9]+\]\[[0-9]+,[0-9]+\])".*/\1/p')
    [ -z "$txt" ] && continue
    [ -z "$bounds" ] && continue
    xa=$(printf '%s' "$bounds" | sed -nE 's/^\[([0-9]+),.*/\1/p')
    xb=$(printf '%s' "$bounds" | sed -nE 's/.*\]\[([0-9]+),[0-9]+\]$/\1/p')
    yb=$(printf '%s' "$bounds" | sed -nE 's/.*,([0-9]+)\]$/\1/p')
    [ -z "$xa" ] && continue
    [ -z "$xb" ] && continue
    [ -z "$yb" ] && continue
    dlog "H1" "badge_candidate" "\"txt\":\"$txt\",\"xa\":$xa,\"xb\":$xb,\"yb\":$yb"
    if [ "$xa" -ge 800 ] && [ "$xb" -le 1080 ] && [ "$yb" -ge 200 ] && [ "$yb" -le 750 ]; then
      echo "$txt"
      break
    fi
  done
}

CYCLE=0
while true; do
  [ -f "$KILL" ] && { log "kill-switch"; cleanup; }
  rotate_log
  CYCLE=$((CYCLE + 1))

  timeout 4 uiautomator dump "$DUMP" >/dev/null 2>&1
  if [ ! -s "$DUMP" ]; then sleep 1; continue; fi
  grep -q "package=\"$CTRL_PKG\"" "$DUMP" 2>/dev/null || { sleep 1; continue; }

  parse_dump
  [ ! -s "$STATES" ] && { sleep 1; continue; }

  CAM=""; MH=""; MV=""; SEN=""
  while IFS="|" read -r ck txt; do
    [ "$ck" != "true" ] && continue
    case "$txt" in
      "Mirror H")        MH=1 ;;
      "Mirror V")        MV=1 ;;
      "Back"|"Back (0)") CAM="back" ;;
      "Front"|"Front (1)") CAM="front" ;;
      "0°")        SEN="0" ;;
      "90°")       SEN="90" ;;
      "180°")      SEN="180" ;;
      "270°")      SEN="270" ;;
    esac
  done < "$STATES"

  ROW_MH=$(grep -c "Mirror H"  "$STATES" 2>/dev/null)
  ROW_MV=$(grep -c "Mirror V"  "$STATES" 2>/dev/null)
  [ "${ROW_MH:-0}"  -gt 0 ] && [ -z "$MH" ]  && MH=0
  [ "${ROW_MV:-0}"  -gt 0 ] && [ -z "$MV" ]  && MV=0
  [ -z "$MH" ]  && MH=$(conf_get preview_mirror_h); [ -z "$MH" ] && MH=0
  [ -z "$MV" ]  && MV=$(conf_get preview_mirror_v); [ -z "$MV" ] && MV=0

  [ -z "$CAM" ] && CAM="back"
  [ -z "$SEN" ] && SEN=$(conf_get sensor_orientation); [ -z "$SEN" ] && SEN=90

  ROT=$(parse_rot_badge)
  case "$ROT" in
    0|90|180|270) ;;
    *) ROT=0 ;;
  esac

  STAMP="$CAM|$ROT|$MH|$MV|$SEN"

  # Write status every cycle (for real-time monitoring)
  back_sz=$(stat -c %s "$VID_BACK_BASE" 2>/dev/null || echo 0)
  front_sz=$(stat -c %s "$VID_FRONT_BASE" 2>/dev/null || echo 0)
  hal_pid=$(pidof android.hardware.camera.provider-fake 2>/dev/null || echo "NONE")
  write_status "$ROT" "$CAM" "$SEN" "$MH" "$MV" "$back_sz" "$front_sz" "$hal_pid" "$(date +%H:%M:%S)"

  if [ "$STAMP" = "$LAST" ]; then sleep 1; continue; fi

  NOW=$(date +%s)
  if [ "$LAST" != "" ] && [ $((NOW - LAST_RESTART_TS)) -lt "$COOLDOWN" ]; then
    if [ "$SEN" = "$LAST_SEN" ] && [ "$ROT" = "$LAST_ROT" ] && [ "$CAM" = "$LAST_CAM" ]; then
      :
    else
      dlog "H5" "cooldown_skip" "\"elapsed\":$((NOW - LAST_RESTART_TS)),\"cooldown\":$COOLDOWN"
      sleep 1; continue
    fi
  fi

  log "DETECTED cam=$CAM rot=$ROT sensor=$SEN mirror_h=$MH mirror_v=$MV"
  dlog "H1" "detected" "\"cam\":\"$CAM\",\"rot\":$ROT,\"sen\":$SEN,\"mh\":$MH,\"mv\":$MV,\"cycle\":$CYCLE"

  conf_set preview_mirror_h "$MH"
  conf_set preview_mirror_v "$MV"
  conf_set preview_rotation 0
  conf_set back_rotate_180  0
  conf_set front_rotate_180 1
  conf_set sensor_orientation "$SEN"

  RESTART_NEEDED=0

  if [ "$ROT" != "$LAST_ROT" ] || [ "$CAM" != "$LAST_CAM" ]; then
    swap_source back  "$ROT";  br_back=$?
    swap_source front "$ROT";  br_front=$?
    [ "$br_back"  = "0" ] && RESTART_NEEDED=1
    [ "$br_front" = "0" ] && RESTART_NEEDED=1
    dlog "H3" "swap_result" "\"br_back\":$br_back,\"br_front\":$br_front,\"restart_needed\":$RESTART_NEEDED"
  fi

  if [ "$SEN" != "$LAST_SEN" ]; then
    RESTART_NEEDED=1
    log "  sensor_orientation changed ($LAST_SEN -> $SEN); needs HAL restart"
    dlog "H2" "sensor_change" "\"from\":$LAST_SEN,\"to\":$SEN"
  fi

  if [ "$RESTART_NEEDED" = "1" ]; then
    log "  restarting HAL"
    restart_hal
    LAST_RESTART_TS=$(date +%s)
  else
    log "  mirror-only change, HAL picks up live"
  fi

  LAST="$STAMP"
  LAST_SEN="$SEN"
  LAST_ROT="$ROT"
  LAST_CAM="$CAM"
  sleep 1
done

#!/bin/bash
###############################################################################
# phone_deep_backup.sh — ГЛУБОКИЙ бэкап FakeHAL с телефона (ТОЛЬКО ЧТЕНИЕ).
# Запускать на VPS ПОСЛЕ того как телефон АВТОРИЗОВАН (adb devices => device).
# Делает: базовый phone_backup.sh + глубокий поиск исходников + pull модуля/APK/zip.
###############################################################################
set -u
ADB=/usr/bin/adb
SERIAL="localhost:5557"
TS="$(date +%Y%m%d_%H%M%S)"
FULL="/root/phone_full_backup_${TS}"
FILELIST="/root/phone_filelist_${TS}.txt"
LOG="/root/phone_deep_backup_${TS}.log"
log(){ echo "[$(date '+%H:%M:%S')] $*" | tee -a "$LOG"; }

mkdir -p "$FULL"
log "START deep backup ts=$TS -> $FULL"

$ADB connect "$SERIAL" >/dev/null 2>&1
STATE="$($ADB -s "$SERIAL" get-state 2>&1)"
log "device state: $STATE"
if ! echo "$STATE" | grep -q "device"; then
  log "ERROR: устройство не в состоянии 'device' (state=$STATE). Нужна авторизация отладки на телефоне. ВЫХОД."
  exit 1
fi
ROOT_ID="$($ADB -s "$SERIAL" shell su -c 'id' 2>&1 | tr -d '\r')"
log "root: $ROOT_ID"
echo "$ROOT_ID" | grep -q uid=0 || { log "ERROR нет root"; exit 1; }

# --- 1. базовый бэкап ---
log "--- запуск /root/phone_backup.sh ---"
bash /root/phone_backup.sh 2>&1 | tail -5 | tee -a "$LOG"
LATEST_TAR="$(ls -t /root/phone_backup_*.tar.gz 2>/dev/null | head -1)"
log "базовый tar: $LATEST_TAR"
[ -n "$LATEST_TAR" ] && cp "$LATEST_TAR" "$FULL/"

# --- 2. глубокий find исходников ---
log "--- глубокий поиск исходников ---"
$ADB -s "$SERIAL" shell su -c 'find /data/local/tmp /sdcard /storage/emulated/0 /data/adb/modules /data/data -xdev -type f \( -name "*.cpp" -o -name "*.h" -o -name "*.hpp" -o -name "*.c" -o -name "*.kt" -o -name "*.java" -o -name "*.gradle" -o -name "*.gradle.kts" -o -name "*.sh" -o -name "*.xml" -o -name "*.conf" -o -name "*.md" -o -name "*.txt" -o -name "*.py" -o -name "build_hal*" -o -name "Android.mk" -o -name "Android.bp" -o -name "CMakeLists.txt" \) 2>/dev/null' 2>&1 | tr -d '\r' > "$FILELIST"
cp "$FILELIST" "$FULL/"
log "filelist строк: $(wc -l < "$FILELIST")"

# --- 3. pull модуля FakeHAL целиком ---
log "--- pull /data/adb/modules/FakeHAL ---"
mkdir -p "$FULL/pull_FakeHAL_module"
$ADB -s "$SERIAL" shell su -c "cd /data/adb/modules && tar cf - FakeHAL 2>/dev/null" 2>>"$LOG" | tar xf - -C "$FULL/pull_FakeHAL_module" 2>>"$LOG"
log "модуль файлов: $(find "$FULL/pull_FakeHAL_module" -type f 2>/dev/null | wc -l)"

# --- 4. исходники из filelist, относящиеся к fakehal, копируем поштучно ---
log "--- копирование найденных исходников (fake/hal/cpp/kt/gradle) ---"
mkdir -p "$FULL/sources"
grep -iE 'fake|hal|\.cpp$|\.h$|\.hpp$|\.kt$|\.gradle|Android\.(mk|bp)|CMakeLists' "$FILELIST" | while read -r f; do
  [ -z "$f" ] && continue
  safe="$(echo "$f" | sed 's#^/##; s#/#__#g')"
  $ADB -s "$SERIAL" shell su -c "cat '$f'" > "$FULL/sources/$safe" 2>>"$LOG"
  [ -s "$FULL/sources/$safe" ] || rm -f "$FULL/sources/$safe"
done
log "исходников скопировано: $(find "$FULL/sources" -type f 2>/dev/null | wc -l)"

# --- 5. FakeControl APK ---
log "--- FakeControl APK ---"
$ADB -s "$SERIAL" shell "pm list packages 2>/dev/null | grep -iE 'fake|hal|fold|control'" 2>&1 | tr -d '\r' | tee -a "$LOG" > "$FULL/matched_packages.txt"
APKPATH="$($ADB -s "$SERIAL" shell pm path com.fakehal.controller 2>/dev/null | tr -d '\r' | sed 's/package://' | head -1)"
log "apk path: $APKPATH"
if [ -n "$APKPATH" ]; then
  $ADB -s "$SERIAL" shell su -c "cat '$APKPATH'" > "$FULL/pull_fakecontrol.apk" 2>>"$LOG"
  [ -s "$FULL/pull_fakecontrol.apk" ] && log "APK pulled $(wc -c < "$FULL/pull_fakecontrol.apk") bytes" || { rm -f "$FULL/pull_fakecontrol.apk"; log "APK пустой"; }
fi

# --- 6. zip бэкапы на sdcard ---
log "--- zip на sdcard >1M ---"
$ADB -s "$SERIAL" shell su -c "find /sdcard -name '*.zip' -size +1M 2>/dev/null" 2>&1 | tr -d '\r' | tee -a "$LOG" > "$FULL/sdcard_zips.txt"

# --- 7. упаковка ---
log "--- упаковка ---"
{
  echo "# phone_full_backup MANIFEST $TS"
  echo "root: $ROOT_ID"
  echo "## md5"
  cd "$FULL" && find . -type f -print0 | sort -z | xargs -0 md5sum 2>/dev/null
} > "$FULL/MANIFEST.md"
tar czf "/root/phone_full_backup_${TS}.tar.gz" -C /root "phone_full_backup_${TS}" 2>>"$LOG"
SZ=$(ls -la "/root/phone_full_backup_${TS}.tar.gz" 2>/dev/null | awk '{print $5}')
log "DONE: /root/phone_full_backup_${TS}.tar.gz ($SZ bytes)"
echo "RESULT_TAR=/root/phone_full_backup_${TS}.tar.gz"
echo "RESULT_FILELIST=$FILELIST"

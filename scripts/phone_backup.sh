#!/bin/bash
###############################################################################
# phone_backup.sh — восстановление исходников/артефактов FakeHAL с телефона
# ТОЛЬКО ЧТЕНИЕ (adb pull). Ничего не меняет на телефоне. Идемпотентен.
# Требует поднятого reverse-туннеля: 5557 -> Windows adb -> Pixel 7.
###############################################################################
set -u

ADB=/usr/bin/adb
SERIAL="localhost:5557"
TS="$(date +%Y%m%d_%H%M%S)"
BASE="/root/phone_backup"
DEST="${BASE}/${TS}"
LOG="/root/phone_backup.log"
MANIFEST="${DEST}/MANIFEST.md"
MAX_VIDEO_MB=100

log() { echo "[$(date '+%Y-%m-%d %H:%M:%S')] $*" | tee -a "$LOG"; }

log "=========================================================="
log "phone_backup.sh START (ts=${TS})"
mkdir -p "$DEST"

# --- 1. connect с retry ---
CONNECTED=0
for i in 1 2 3 4 5; do
    OUT="$($ADB connect "$SERIAL" 2>&1)"
    log "adb connect attempt ${i}: ${OUT}"
    if echo "$OUT" | grep -qiE "connected to|already connected"; then
        CONNECTED=1
        break
    fi
    sleep 3
done
if [ "$CONNECTED" -ne 1 ]; then
    log "ERROR: не удалось подключиться к ${SERIAL}. Туннель поднят? Выход."
    exit 1
fi

# --- 2. wait-for-device (timeout 30s) ---
log "wait-for-device (timeout 30s)..."
if ! timeout 30 $ADB -s "$SERIAL" wait-for-device; then
    log "ERROR: wait-for-device timeout. Устройство недоступно. Выход."
    exit 1
fi
log "Устройство доступно: $($ADB -s "$SERIAL" get-state 2>&1)"

# --- 3. проверка root ---
ROOT_ID="$($ADB -s "$SERIAL" shell su -c 'id' 2>&1)"
log "root check: ${ROOT_ID}"
if ! echo "$ROOT_ID" | grep -q "uid=0"; then
    log "ERROR: нет root на телефоне (su -c id не вернул uid=0). Выход."
    exit 1
fi
log "ROOT OK"

# --- helpers ---
# su_pull SRC DESTSUBPATH : копирует файл через su cat (обходит права)
su_pull_file() {
    local src="$1" name="$2"
    local out="${DEST}/${name}"
    mkdir -p "$(dirname "$out")"
    # существует?
    local exists
    exists="$($ADB -s "$SERIAL" shell su -c "[ -e '$src' ] && echo YES || echo NO" 2>&1 | tr -d '\r')"
    if [ "$exists" != "YES" ]; then
        log "SKIP (нет на телефоне): $src"
        return 1
    fi
    # размер в байтах
    local sz
    sz="$($ADB -s "$SERIAL" shell su -c "stat -c%s '$src' 2>/dev/null || wc -c < '$src'" 2>&1 | tr -d '\r' | tr -dc '0-9')"
    [ -z "$sz" ] && sz=0
    $ADB -s "$SERIAL" shell su -c "cat '$src'" > "$out" 2>>"$LOG"
    local got
    got=$(wc -c < "$out" 2>/dev/null || echo 0)
    if [ "$got" -gt 0 ]; then
        log "PULLED: $src -> $out (${got} bytes, phone reported ${sz})"
        echo "$out"
        return 0
    else
        log "WARN: пустой файл после pull: $src"
        rm -f "$out"
        return 1
    fi
}

# su_pull_video : с проверкой размера (skip если > MAX_VIDEO_MB)
su_pull_video() {
    local src="$1" name="$2"
    local exists
    exists="$($ADB -s "$SERIAL" shell su -c "[ -e '$src' ] && echo YES || echo NO" 2>&1 | tr -d '\r')"
    if [ "$exists" != "YES" ]; then
        log "SKIP (нет на телефоне): $src"
        return 1
    fi
    local sz
    sz="$($ADB -s "$SERIAL" shell su -c "stat -c%s '$src' 2>/dev/null" 2>&1 | tr -d '\r' | tr -dc '0-9')"
    [ -z "$sz" ] && sz=0
    local mb=$(( sz / 1024 / 1024 ))
    if [ "$mb" -ge "$MAX_VIDEO_MB" ]; then
        log "SKIP VIDEO (слишком большое: ${mb}MB >= ${MAX_VIDEO_MB}MB): $src"
        return 1
    fi
    su_pull_file "$src" "$name"
}

# su_pull_dir : рекурсивно каталог через tar-стрим по su
su_pull_dir() {
    local src="$1" name="$2"
    local exists
    exists="$($ADB -s "$SERIAL" shell su -c "[ -d '$src' ] && echo YES || echo NO" 2>&1 | tr -d '\r')"
    if [ "$exists" != "YES" ]; then
        log "SKIP (нет каталога на телефоне): $src"
        return 1
    fi
    local out="${DEST}/${name}"
    mkdir -p "$out"
    local parent base
    parent="$(dirname "$src")"
    base="$(basename "$src")"
    $ADB -s "$SERIAL" shell su -c "cd '$parent' && tar cf - '$base' 2>/dev/null" 2>>"$LOG" | tar xf - -C "$out" 2>>"$LOG"
    local cnt
    cnt=$(find "$out" -type f 2>/dev/null | wc -l)
    log "PULLED DIR: $src -> $out (${cnt} файлов)"
    return 0
}

# --- 4. собираем артефакты ---
log "--- Сбор артефактов FakeHAL ---"

# основной бинарь
su_pull_file "/data/local/tmp/fake_camera_provider" "data_local_tmp/fake_camera_provider"

# .so библиотеки в /data/local/tmp
SO_LIST="$($ADB -s "$SERIAL" shell su -c "ls /data/local/tmp/*.so 2>/dev/null" 2>&1 | tr -d '\r')"
for so in $SO_LIST; do
    [ -n "$so" ] && su_pull_file "$so" "data_local_tmp/$(basename "$so")"
done

# скрипты
for s in run_fakehal.sh restart_fakehal.sh serve720.sh startsrv.sh oneshot2.sh soak720.sh; do
    su_pull_file "/data/local/tmp/$s" "data_local_tmp/$s"
done

# конфиг
su_pull_file "/data/local/tmp/fakehal.conf" "data_local_tmp/fakehal.conf"

# видео (с ограничением размера)
su_pull_video "/data/local/tmp/fake_video.mp4" "data_local_tmp/fake_video.mp4"
su_pull_video "/data/local/tmp/fake_video_front.mp4" "data_local_tmp/fake_video_front.mp4"

# каталог библиотек
su_pull_dir "/data/local/tmp/fhlibs" "data_local_tmp/fhlibs"

# KSU-модуль целиком
su_pull_dir "/data/adb/modules/FakeHAL" "data_adb/modules_FakeHAL"

# tricky_store
su_pull_file "/data/adb/tricky_store/keybox.xml" "data_adb/tricky_store/keybox.xml"
su_pull_file "/data/adb/tricky_store/target.txt" "data_adb/tricky_store/target.txt"

# лог
su_pull_file "/data/local/tmp/fakehAL.log" "data_local_tmp/fakehAL.log"

# --- 5. MANIFEST с md5 ---
log "--- Генерация MANIFEST ---"
{
    echo "# FakeHAL Phone Backup MANIFEST"
    echo ""
    echo "- Timestamp: ${TS}"
    echo "- Device: ${SERIAL}"
    echo "- Root id: ${ROOT_ID}"
    echo ""
    echo "## Артефакты (md5sum)"
    echo ""
    echo '```'
    cd "$DEST" && find . -type f ! -name "MANIFEST.md" -print0 | sort -z | xargs -0 md5sum 2>/dev/null
    echo '```'
    echo ""
    echo "## Размеры"
    echo ""
    echo '```'
    cd "$DEST" && find . -type f ! -name "MANIFEST.md" -exec ls -la {} \; 2>/dev/null
    echo '```'
} > "$MANIFEST"
log "MANIFEST записан: $MANIFEST"

# --- 6. tar+gzip ---
TARBALL="/root/phone_backup_${TS}.tar.gz"
tar czf "$TARBALL" -C "$BASE" "$TS" 2>>"$LOG"
if [ -f "$TARBALL" ]; then
    TSIZE=$(ls -la "$TARBALL" | awk '{print $5}')
    log "ARCHIVE READY: $TARBALL (${TSIZE} bytes)"
    echo "TARBALL=$TARBALL"
else
    log "ERROR: не удалось создать архив"
    exit 1
fi

log "phone_backup.sh DONE"
log "=========================================================="
exit 0

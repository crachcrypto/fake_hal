#!/system/bin/sh
APK=/data/local/tmp/oc1491.apk
LOG=/data/local/tmp/reinst_oc.log
SZ=$(stat -c %s "$APK")
echo "apk_size=$SZ" > "$LOG"
S=$(pm install-create -S "$SZ" 2>>"$LOG" | grep -oE '[0-9]+' | head -1)
echo "session=$S" >> "$LOG"
pm install-write -S "$SZ" "$S" base "$APK" >> "$LOG" 2>&1
echo "write_exit=$?" >> "$LOG"
pm install-commit "$S" >> "$LOG" 2>&1
echo "commit_exit=$?" >> "$LOG"

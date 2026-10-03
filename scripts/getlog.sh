LP=$(sh /sdcard/findlspd.sh 2>/dev/null | grep -oE 'LSPD_PID=[0-9]+' | head -1 | cut -d= -f2)
echo "LSPD=$LP"
B=/proc/$LP/root/data/adb/lspd/log
NB=$(ls -t $B/verbose_*.log 2>/dev/null | head -1)
echo "LOG=$NB"
cp "$NB" /sdcard/new.log 2>&1
chmod 666 /sdcard/new.log 2>&1
ls -la /sdcard/new.log

INITNS=$(readlink /proc/1/ns/mnt)
for p in $(ls /proc 2>/dev/null | grep -E '^[0-9]+$'); do
  # процесс, у кого среди открытых fd есть modules_config.db
  if ls -l /proc/$p/fd 2>/dev/null | grep -q 'modules_config.db'; then
    MNS=$(readlink /proc/$p/ns/mnt 2>/dev/null)
    EXE=$(readlink /proc/$p/exe 2>/dev/null)
    echo "LSPD_PID=$p ns=$MNS init_ns=$INITNS exe=$EXE"
    ls -l /proc/$p/fd 2>/dev/null | grep 'modules_config' 
  fi
done

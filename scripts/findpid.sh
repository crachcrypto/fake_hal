for p in $(ls /proc | grep -E '^[0-9]+$'); do
  if ls /proc/$p/root/data/adb/lspd/config/modules_config.db >/dev/null 2>&1; then
    echo "PID=$p cmd=$(cat /proc/$p/cmdline 2>/dev/null | tr '\0' ' ')"
  fi
done

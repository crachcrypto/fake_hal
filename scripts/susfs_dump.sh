#!/system/bin/sh
D=/data/adb/susfs4ksu
for f in config.sh sus_path.txt sus_path_loop.txt sus_open_redirect.txt sus_mount.txt try_umount.txt sus_maps.txt legit_mounts.txt; do
  echo "===== $f ====="
  cat "$D/$f"
done

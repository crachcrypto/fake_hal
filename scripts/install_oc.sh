#!/system/bin/sh
ID=$(pm install-create -r | grep -oE '[0-9]+' | head -1)
echo "session=$ID"
pm install-write "$ID" base /data/local/tmp/oc1491.apk
pm install-commit "$ID"
echo "commit_exit=$?"
dumpsys package net.sourceforge.opencamera | grep versionName
echo "SCRIPT_DONE"

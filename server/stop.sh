#!/bin/bash
PID=$(cat /root/fakehal_server/server.pid 2>/dev/null)
[ -n "$PID" ] && kill "$PID" && echo "Stopped PID=$PID" || echo "Not running"
rm -f /root/fakehal_server/server.pid

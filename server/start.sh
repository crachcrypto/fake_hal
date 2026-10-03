#!/bin/bash
cd /root/fakehal_server
python3 -m venv /root/fakehal_server/venv
/root/fakehal_server/venv/bin/pip install fastapi uvicorn "python-jose[cryptography]" pydantic -q
nohup /root/fakehal_server/venv/bin/uvicorn main:app --host 0.0.0.0 --port 7070 > /root/fakehal_server/server.log 2>&1 &
echo $! > /root/fakehal_server/server.pid
echo "Server started PID=$(cat server.pid)"

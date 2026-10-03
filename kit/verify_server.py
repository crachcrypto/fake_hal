#!/usr/bin/env python3
# FakeHAL license verify endpoint — read-only over keys.json (same file the bot writes).
# GET /verify?key=FH-XXXX-YYYY&device=SERIAL
#   -> {"valid":true/false,"reason":"...","expired":bool,"remaining_sec":int,"expires_at":"..."}
# Does NOT modify keys.json. Device binding is enforced read-only (bound elsewhere by bot).
import json, os, http.server, socketserver, urllib.parse
from datetime import datetime, timezone

KEYS_FILE = "/root/fakehal_server/keys.json"
PORT = 8787

def load_keys():
    try:
        with open(KEYS_FILE) as f:
            return json.load(f)
    except Exception:
        return {}

def check(key: str, device: str) -> dict:
    key = (key or "").strip()
    device = (device or "").strip()
    if not key:
        return {"valid": False, "reason": "no_key"}
    kd = load_keys().get(key)
    if not kd:
        return {"valid": False, "reason": "unknown_key"}
    if not kd.get("active"):
        return {"valid": False, "reason": "inactive"}
    exp = kd.get("expires_at", "")
    remaining = None
    if exp:
        try:
            dt = datetime.fromisoformat(exp)
            secs = int((dt - datetime.now(timezone.utc)).total_seconds())
            remaining = secs
            if secs <= 0:
                return {"valid": False, "reason": "expired",
                        "expired": True, "remaining_sec": 0, "expires_at": exp}
        except Exception:
            pass
    # device binding: if key already bound to devices and this one is not among them,
    # and quota is full -> reject. Empty devices list = not yet bound = allowed.
    devs = kd.get("devices", []) or []
    maxd = int(kd.get("max_devices", 1) or 1)
    if device and devs and device not in devs and len(devs) >= maxd:
        return {"valid": False, "reason": "device_limit",
                "remaining_sec": remaining, "expires_at": exp}
    return {"valid": True, "reason": "ok", "expired": False,
            "remaining_sec": remaining, "expires_at": exp}

class H(http.server.BaseHTTPRequestHandler):
    def _send(self, obj, code=200):
        body = json.dumps(obj).encode()
        self.send_response(code)
        self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)
    def do_GET(self):
        u = urllib.parse.urlparse(self.path)
        if u.path == "/health":
            return self._send({"ok": True})
        if u.path != "/verify":
            return self._send({"valid": False, "reason": "not_found"}, 404)
        q = urllib.parse.parse_qs(u.query)
        res = check(q.get("key", [""])[0], q.get("device", [""])[0])
        self._send(res)
    def log_message(self, *a):
        pass  # quiet

if __name__ == "__main__":
    socketserver.TCPServer.allow_reuse_address = True
    with socketserver.TCPServer(("0.0.0.0", PORT), H) as srv:
        print(f"FakeHAL verify server on :{PORT}")
        srv.serve_forever()

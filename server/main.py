import json
import uuid
import os
from datetime import datetime, timedelta, timezone
from typing import Optional, Dict, Any

from fastapi import FastAPI, HTTPException, Header, Depends
from jose import jwt, JWTError

import config
from models import AuthRequest, AuthResponse, CommandRequest, KeyCreateRequest

app = FastAPI(title="FakeHAL Licensing Server", version="1.0.0")

KEYS_FILE = "/root/fakehal_server/keys.json"
COMMANDS_FILE = "/root/fakehal_server/commands.json"


def load_json(path: str) -> dict:
    if os.path.exists(path):
        with open(path) as f:
            return json.load(f)
    return {}


def save_json(path: str, data: dict):
    with open(path, "w") as f:
        json.dump(data, f, indent=2, ensure_ascii=False)


def now_iso() -> str:
    return datetime.now(timezone.utc).isoformat()


def make_jwt(device_id: str, license_key: str, expires_at: str) -> str:
    payload = {
        "sub": device_id,
        "key": license_key,
        "exp": datetime.now(timezone.utc) + timedelta(hours=config.JWT_EXPIRE_HOURS),
        "expires_at": expires_at,
    }
    return jwt.encode(payload, config.JWT_SECRET, algorithm="HS256")


def decode_jwt(token: str) -> dict:
    try:
        return jwt.decode(token, config.JWT_SECRET, algorithms=["HS256"])
    except JWTError as e:
        raise HTTPException(status_code=401, detail=f"Invalid token: {e}")


def require_token(authorization: Optional[str] = Header(default=None)) -> dict:
    if not authorization or not authorization.startswith("Bearer "):
        raise HTTPException(status_code=401, detail="Missing Authorization header")
    return decode_jwt(authorization.split(" ", 1)[1])


def require_admin(x_admin_token: Optional[str] = Header(default=None)):
    if x_admin_token != config.ADMIN_TOKEN:
        raise HTTPException(status_code=403, detail="Invalid admin token")


# ── Public ────────────────────────────────────────────────────────────────────

@app.get("/api/status")
def get_status():
    keys = load_json(KEYS_FILE)
    commands = load_json(COMMANDS_FILE)
    devices_online = sum(len(v) for v in commands.values())
    return {
        "status": "ok",
        "version": "1.0.0",
        "keys_count": len(keys),
        "devices_online": devices_online,
    }


# ── Auth ──────────────────────────────────────────────────────────────────────

@app.post("/api/auth", response_model=AuthResponse)
def auth(req: AuthRequest):
    keys = load_json(KEYS_FILE)
    kd = keys.get(req.license_key)

    if not kd:
        return AuthResponse(success=False, token=None, message="Key not found", expires_at=None)
    if not kd.get("active"):
        return AuthResponse(success=False, token=None, message="Key inactive", expires_at=None)

    exp = kd.get("expires_at", "")
    if exp and datetime.now(timezone.utc) > datetime.fromisoformat(exp):
        return AuthResponse(success=False, token=None, message="Key expired", expires_at=None)

    devices: list = kd.get("devices", [])
    if req.device_id not in devices:
        if len(devices) >= kd.get("max_devices", 1):
            return AuthResponse(success=False, token=None, message="Device limit reached", expires_at=None)
        devices.append(req.device_id)
        kd["devices"] = devices
        keys[req.license_key] = kd
        save_json(KEYS_FILE, keys)

    token = make_jwt(req.device_id, req.license_key, exp)
    return AuthResponse(success=True, token=token, message="Authorized", expires_at=exp)


# ── Commands ──────────────────────────────────────────────────────────────────

@app.post("/api/command")
def post_command(req: CommandRequest, claims: Dict = Depends(require_token)):
    commands = load_json(COMMANDS_FILE)
    queue = commands.get(req.device_id, [])
    cmd_id = str(uuid.uuid4())
    queue.append({
        "id": cmd_id,
        "command": req.command,
        "params": req.params,
        "created_at": now_iso(),
    })
    commands[req.device_id] = queue
    save_json(COMMANDS_FILE, commands)
    return {"success": True, "command_id": cmd_id}


@app.get("/api/commands")
def get_commands(claims: Dict = Depends(require_token)):
    device_id = claims["sub"]
    commands = load_json(COMMANDS_FILE)
    queue = commands.pop(device_id, [])
    save_json(COMMANDS_FILE, commands)
    return {"device_id": device_id, "commands": queue}


# ── Admin: Keys ───────────────────────────────────────────────────────────────

@app.post("/api/key/create")
def create_key(req: KeyCreateRequest, _=Depends(require_admin)):
    keys = load_json(KEYS_FILE)
    k = req.key or str(uuid.uuid4())[:8].upper()
    if k in keys:
        raise HTTPException(status_code=409, detail="Key already exists")
    exp = (datetime.now(timezone.utc) + timedelta(days=req.expires_days)).isoformat()
    keys[k] = {
        "active": True,
        "description": req.description,
        "created_at": now_iso(),
        "expires_at": exp,
        "max_devices": req.max_devices,
        "devices": [],
    }
    save_json(KEYS_FILE, keys)
    return {"success": True, "key": k, "expires_at": exp}


@app.get("/api/keys")
def list_keys(_=Depends(require_admin)):
    return {"keys": load_json(KEYS_FILE)}


@app.delete("/api/key/{key}")
def deactivate_key(key: str, _=Depends(require_admin)):
    keys = load_json(KEYS_FILE)
    if key not in keys:
        raise HTTPException(status_code=404, detail="Key not found")
    keys[key]["active"] = False
    save_json(KEYS_FILE, keys)
    return {"success": True, "key": key, "active": False}

from pydantic import BaseModel
from typing import Optional, Dict, Any


class AuthRequest(BaseModel):
    license_key: str
    device_id: str


class AuthResponse(BaseModel):
    success: bool
    token: Optional[str]
    message: str
    expires_at: Optional[str]


class CommandRequest(BaseModel):
    device_id: str
    command: str
    params: Dict[str, Any] = {}


class KeyCreateRequest(BaseModel):
    key: Optional[str] = None
    description: str = ""
    max_devices: int = 1
    expires_days: int = 30

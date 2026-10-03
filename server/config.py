import os
ADMIN_TOKEN = os.environ.get("ADMIN_TOKEN", "")
JWT_SECRET  = os.environ.get("JWT_SECRET", "")
JWT_EXPIRE_HOURS = int(os.environ.get("JWT_EXPIRE_HOURS", "24"))
PORT = int(os.environ.get("PORT", "7070"))
HOST = os.environ.get("HOST", "0.0.0.0")

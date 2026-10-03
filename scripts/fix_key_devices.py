#!/usr/bin/env python3
import json, time, shutil, os

KEYS = "/root/fakehal_server/keys.json"
KEY = "FH-LIVE-76048702"
SERIAL = "2C101FDH200DZ4"
ANDROID_ID = "7fdeaa7799777790"

# backup
bak = f"{KEYS}.bak_androidid_{int(time.time())}"
shutil.copy2(KEYS, bak)
print("backup:", bak)

data = json.load(open(KEYS))
k = data[KEY]
devs = set(k.get("devices", []))
devs.add(SERIAL)
devs.add(ANDROID_ID)
k["devices"] = sorted(devs)
k["max_devices"] = max(k.get("max_devices", 1), len(k["devices"]))
k["active"] = True

json.dump(data, open(KEYS, "w"), indent=2)
print("devices now:", k["devices"])
print("max_devices:", k["max_devices"])
print("active:", k["active"])

import json, uuid, datetime, sys
KEYS="/root/fakehal_server/keys.json"
now=datetime.datetime.now(datetime.timezone.utc)
exp=now+datetime.timedelta(hours=1)
key=f"FH-TEMP-{str(uuid.uuid4())[:8].upper()}"
try:
    keys=json.load(open(KEYS))
except Exception:
    keys={}
keys[key]={
 "active":True,
 "description":"temp 1h test key",
 "created_at":now.isoformat(),
 "expires_at":exp.isoformat(),
 "max_devices":1,
 "devices":[],
 "user_id":0,
 "plan":"temp_1h",
 "tier":"full",
}
json.dump(keys,open(KEYS,"w"),indent=2,ensure_ascii=False)
print("KEY="+key)
print("EXPIRES_UTC="+exp.isoformat())

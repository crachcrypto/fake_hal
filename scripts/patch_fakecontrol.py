#!/usr/bin/env python3
import os, shutil

SVC = "/root/fc_decomp/smali_classes3/com/fakehal/controller/GpsSpoofService.smali"
WD_SRC = "/root/LicenseWatchdog.smali"
WD_DST = "/root/fc_decomp/smali_classes3/com/fakehal/controller/LicenseWatchdog.smali"

# 1. drop watchdog class into same dex dir
shutil.copyfile(WD_SRC, WD_DST)
print("watchdog class placed")

# 2. inject watchdog start into onCreate, after startForeground
s = open(SVC).read()

anchor = "invoke-virtual {p0, v1, v0}, Lcom/fakehal/controller/GpsSpoofService;->startForeground(ILandroid/app/Notification;)V"
assert anchor in s, "startForeground anchor not found"

inject = anchor + "\n\n" + \
"""    new-instance v3, Lcom/fakehal/controller/LicenseWatchdog;

    invoke-direct {v3, p0}, Lcom/fakehal/controller/LicenseWatchdog;-><init>(Lcom/fakehal/controller/GpsSpoofService;)V

    invoke-virtual {v3}, Lcom/fakehal/controller/LicenseWatchdog;->start()V"""

if "LicenseWatchdog;->start()V" not in s:
    s = s.replace(anchor, inject, 1)

# 3. bump onCreate .locals 3 -> 4 (only within onCreate)
oc_hdr = ".method public onCreate()V\n    .locals 3\n"
assert oc_hdr in s, "onCreate header (.locals 3) not found"
s = s.replace(oc_hdr, ".method public onCreate()V\n    .locals 4\n", 1)

open(SVC, "w").write(s)
print("onCreate patched: watchdog start injected, .locals bumped")

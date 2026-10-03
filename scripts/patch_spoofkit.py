#!/usr/bin/env python3
import os, re, glob

BASE = "/root/sk_decomp/smali/com/spoofkit/xposed"
MAIN = os.path.join(BASE, "SpoofKitModule.smali")

# ---- 1. main class: add fields + spoofActive() ----
s = open(MAIN).read()

if "licLastCheck:J" not in s:
    anchor = ".field private static configLoaded:Z\n"
    assert anchor in s, "configLoaded field anchor not found"
    s = s.replace(anchor, anchor +
        ".field private static licLastCheck:J\n\n"
        ".field private static licLastResult:Z\n")

SPOOF_ACTIVE = r'''.method public static spoofActive()Z
    .locals 6

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    sget-wide v2, Lcom/spoofkit/xposed/SpoofKitModule;->licLastCheck:J

    sub-long v4, v0, v2

    const-wide/16 v2, 0x1388

    cmp-long v2, v4, v2

    if-gez v2, :sk_reload

    sget-boolean v2, Lcom/spoofkit/xposed/SpoofKitModule;->licLastResult:Z

    return v2

    :sk_reload
    sget-object v2, Lcom/spoofkit/xposed/SpoofKitModule;->config:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Map;->clear()V

    const/4 v2, 0x0

    sput-boolean v2, Lcom/spoofkit/xposed/SpoofKitModule;->configLoaded:Z

    invoke-static {}, Lcom/spoofkit/xposed/SpoofKitModule;->loadConfig()V

    invoke-static {}, Lcom/spoofkit/xposed/SpoofKitModule;->licenseOk()Z

    move-result v2

    sput-wide v0, Lcom/spoofkit/xposed/SpoofKitModule;->licLastCheck:J

    sput-boolean v2, Lcom/spoofkit/xposed/SpoofKitModule;->licLastResult:Z

    return v2
.end method

'''

if "spoofActive()Z" not in s:
    anchor2 = ".method private static loadConfig()V"
    assert anchor2 in s, "loadConfig anchor not found"
    s = s.replace(anchor2, SPOOF_ACTIVE + anchor2, 1)

open(MAIN, "w").write(s)
print("main patched: fields + spoofActive added")

# ---- 2. wrapper each before/afterHookedMethod ----
SIG = r"Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;"

WRAPPER = (
    ".method protected {kind}HookedMethod({sig})V\n"
    "    .locals 1\n\n"
    "    invoke-static {{}}, Lcom/spoofkit/xposed/SpoofKitModule;->spoofActive()Z\n\n"
    "    move-result v0\n\n"
    "    if-nez v0, :sk_ok\n\n"
    "    return-void\n\n"
    "    :sk_ok\n"
    "    invoke-direct {{p0, p1}}, {cls}->{kind}HookedMethod_impl({sig})V\n\n"
    "    return-void\n"
    ".end method\n\n"
)

meth_re = re.compile(
    r'\.method protected (before|after)HookedMethod\(Lde/robv/android/xposed/XC_MethodHook\$MethodHookParam;\)V'
)

patched = 0
for f in glob.glob(os.path.join(BASE, "SpoofKitModule$*.smali")):
    t = open(f).read()
    if "HookedMethod_impl" in t:
        continue
    m = meth_re.search(t)
    if not m:
        continue
    kind = m.group(1)
    cls = re.search(r'^\.class .*?(L[^;]+;)', t, re.M).group(1)
    # rename original -> _impl (private)
    orig = f".method protected {kind}HookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V"
    impl = f".method private {kind}HookedMethod_impl(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V"
    t = t.replace(orig, impl, 1)
    # build wrapper and insert right before the (now renamed) impl method
    wrapper = WRAPPER.format(kind=kind, sig="Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;", cls=cls)
    t = t.replace(impl, wrapper + impl, 1)
    open(f, "w").write(t)
    patched += 1

print(f"callbacks wrapped: {patched}")

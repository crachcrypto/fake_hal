.class public Lcom/fakehal/controller/LicenseWatchdog;
.super Ljava/lang/Thread;
.source "LicenseWatchdog.java"


# instance fields
.field private final svc:Lcom/fakehal/controller/GpsSpoofService;


# direct methods
.method public constructor <init>(Lcom/fakehal/controller/GpsSpoofService;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    iput-object p1, p0, Lcom/fakehal/controller/LicenseWatchdog;->svc:Lcom/fakehal/controller/GpsSpoofService;

    return-void
.end method

.method private static expired()Z
    .locals 6

    invoke-static {}, Lcom/fakehal/controller/LicenseHolder;->isAuthorized()Z

    move-result v0

    if-nez v0, :cond_auth

    const/4 v0, 0x1

    return v0

    :cond_auth
    sget-object v0, Lcom/fakehal/controller/LicenseHolder;->expiresAt:Ljava/lang/String;

    if-nez v0, :cond_has

    const/4 v0, 0x0

    return v0

    :cond_has
    :try_start_0
    invoke-static {v0}, Ljava/time/Instant;->parse(Ljava/lang/CharSequence;)Ljava/time/Instant;

    move-result-object v0

    invoke-virtual {v0}, Ljava/time/Instant;->toEpochMilli()J

    move-result-wide v0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    cmp-long v4, v2, v0

    if-lez v4, :cond_ok

    const/4 v4, 0x1

    return v4

    :cond_ok
    const/4 v4, 0x0

    return v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    move-exception v0

    const/4 v0, 0x0

    return v0
.end method


# virtual methods
.method public run()V
    .locals 5

    :loop
    :try_start_0
    const-wide/32 v0, 0x7530

    invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_int

    goto :after_sleep

    :catch_int
    move-exception v0

    :after_sleep
    invoke-static {}, Lcom/fakehal/controller/LicenseWatchdog;->expired()Z

    move-result v0

    if-eqz v0, :loop

    invoke-static {}, Lcom/fakehal/controller/LicenseHolder;->clear()V

    :try_start_1
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v0

    const/4 v1, 0x3

    new-array v1, v1, [Ljava/lang/String;

    const/4 v2, 0x0

    const-string v3, "su"

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, "-c"

    aput-object v3, v1, v2

    const/4 v2, 0x2

    const-string v3, "rm -f /data/local/tmp/spoofkit/active_config"

    aput-object v3, v1, v2

    invoke-virtual {v0, v1}, Ljava/lang/Runtime;->exec([Ljava/lang/String;)Ljava/lang/Process;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Process;->waitFor()I
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_rm

    goto :after_rm

    :catch_rm
    move-exception v0

    :after_rm
    iget-object v0, p0, Lcom/fakehal/controller/LicenseWatchdog;->svc:Lcom/fakehal/controller/GpsSpoofService;

    invoke-virtual {v0}, Lcom/fakehal/controller/GpsSpoofService;->stopSelf()V

    return-void
.end method

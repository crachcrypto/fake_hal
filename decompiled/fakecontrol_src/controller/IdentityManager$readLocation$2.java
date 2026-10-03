package com.fakehal.controller;

import com.fakehal.controller.IdentityManager;
import com.fakehal.controller.RootShell;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/fakehal/controller/IdentityManager$LocationProfile;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$readLocation$2", f = "IdentityManager.kt", i = {}, l = {131}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class IdentityManager$readLocation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IdentityManager.LocationProfile>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityManager$readLocation$2(Continuation<? super IdentityManager$readLocation$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$readLocation$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IdentityManager.LocationProfile> continuation) {
        return ((IdentityManager$readLocation$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0055 A[Catch: Exception -> 0x00b0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b0, blocks: (B:11:0x0033, B:12:0x004f, B:14:0x0055, B:33:0x0069, B:18:0x007e, B:30:0x0086, B:21:0x0095, B:24:0x009d), top: B:10:0x0033 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        IdentityManager$readLocation$2 identityManager$readLocation$2;
        IdentityManager$readLocation$2 identityManager$readLocation$22;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                identityManager$readLocation$2 = this;
                try {
                    identityManager$readLocation$2.label = 1;
                    Object exec = RootShell.INSTANCE.exec("cat /data/local/tmp/spoofkit/active_config 2>/dev/null", identityManager$readLocation$2);
                    if (exec == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = exec;
                    try {
                        RootShell.Result result = (RootShell.Result) $result;
                        IdentityManager.LocationProfile loc = new IdentityManager.LocationProfile(false, null, null, 7, null);
                        for (String line : StringsKt.lines(result.getOutput())) {
                            if (StringsKt.startsWith$default(line, "gps_enabled=", false, 2, (Object) null)) {
                                loc = IdentityManager.LocationProfile.copy$default(loc, Intrinsics.areEqual(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), "true"), null, null, 6, null);
                            } else if (StringsKt.startsWith$default(line, "gps_lat=", false, 2, (Object) null)) {
                                loc = IdentityManager.LocationProfile.copy$default(loc, false, StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), null, 5, null);
                            } else if (StringsKt.startsWith$default(line, "gps_lon=", false, 2, (Object) null)) {
                                loc = IdentityManager.LocationProfile.copy$default(loc, false, null, StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), 3, null);
                            }
                        }
                        return loc;
                    } catch (Exception e) {
                        $result = $result2;
                        identityManager$readLocation$22 = identityManager$readLocation$2;
                        return new IdentityManager.LocationProfile(false, null, null, 7, null);
                    }
                } catch (Exception e2) {
                    identityManager$readLocation$22 = identityManager$readLocation$2;
                    return new IdentityManager.LocationProfile(false, null, null, 7, null);
                }
            case 1:
                identityManager$readLocation$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    identityManager$readLocation$2 = identityManager$readLocation$22;
                    $result2 = $result;
                    RootShell.Result result2 = (RootShell.Result) $result;
                    IdentityManager.LocationProfile loc2 = new IdentityManager.LocationProfile(false, null, null, 7, null);
                    while (r3.hasNext()) {
                    }
                    return loc2;
                } catch (Exception e3) {
                    return new IdentityManager.LocationProfile(false, null, null, 7, null);
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

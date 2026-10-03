package com.fakehal.controller;

import android.util.Log;
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
import org.json.JSONObject;

/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/fakehal/controller/IdentityManager$DeviceProfile;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$readSaved$2", f = "IdentityManager.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class IdentityManager$readSaved$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IdentityManager.DeviceProfile>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityManager$readSaved$2(Continuation<? super IdentityManager$readSaved$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$readSaved$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IdentityManager.DeviceProfile> continuation) {
        return ((IdentityManager$readSaved$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object $result;
        Object obj2;
        Object $result2;
        IdentityManager$readSaved$2 identityManager$readSaved$2;
        RootShell.Result result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                $result = obj;
                try {
                    this.label = 1;
                    Object exec = RootShell.INSTANCE.exec("cat /data/local/tmp/spoofkit/saved_identity.json 2>/dev/null", this);
                    if (exec == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    identityManager$readSaved$2 = this;
                    $result2 = exec;
                    try {
                        result = (RootShell.Result) $result2;
                        if (result.getExitCode() == 0) {
                            return null;
                        }
                        try {
                            if (StringsKt.isBlank(result.getOutput())) {
                                return null;
                            }
                            JSONObject json = new JSONObject(StringsKt.trim((CharSequence) result.getOutput()).toString());
                            String optString = json.optString("manufacturer", "");
                            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                            String optString2 = json.optString("model", "");
                            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                            String optString3 = json.optString("brand", "");
                            Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                            String optString4 = json.optString("device", "");
                            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                            String optString5 = json.optString("product", "");
                            Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                            String optString6 = json.optString("fingerprint", "");
                            Intrinsics.checkNotNullExpressionValue(optString6, "optString(...)");
                            String optString7 = json.optString("buildId", "");
                            Intrinsics.checkNotNullExpressionValue(optString7, "optString(...)");
                            String optString8 = json.optString("serial", "");
                            Intrinsics.checkNotNullExpressionValue(optString8, "optString(...)");
                            String optString9 = json.optString("androidId", "");
                            Intrinsics.checkNotNullExpressionValue(optString9, "optString(...)");
                            try {
                                String optString10 = json.optString("imei", "");
                                Intrinsics.checkNotNullExpressionValue(optString10, "optString(...)");
                                return new IdentityManager.DeviceProfile(optString, optString2, optString3, optString4, optString5, optString6, optString7, optString8, optString9, optString10);
                            } catch (Exception e) {
                                e = e;
                                $result2 = $result;
                                obj2 = null;
                                Log.e("IdentityManager", "readSaved failed", e);
                                return obj2;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            $result2 = $result;
                            obj2 = null;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        obj2 = null;
                        $result2 = $result;
                    }
                } catch (Exception e4) {
                    e = e4;
                    obj2 = null;
                    $result2 = $result;
                    Log.e("IdentityManager", "readSaved failed", e);
                    return obj2;
                }
            case 1:
                identityManager$readSaved$2 = this;
                $result2 = obj;
                try {
                    ResultKt.throwOnFailure($result2);
                    $result = $result2;
                    result = (RootShell.Result) $result2;
                    if (result.getExitCode() == 0) {
                    }
                } catch (Exception e5) {
                    e = e5;
                    obj2 = null;
                    Log.e("IdentityManager", "readSaved failed", e);
                    return obj2;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

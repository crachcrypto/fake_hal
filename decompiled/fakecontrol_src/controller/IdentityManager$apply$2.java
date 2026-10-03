package com.fakehal.controller;

import android.util.Log;
import androidx.compose.material3.MenuKt;
import com.fakehal.controller.IdentityManager;
import com.fakehal.controller.RootShell;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$apply$2", f = "IdentityManager.kt", i = {}, l = {MenuKt.InTransitionDuration}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class IdentityManager$apply$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ IdentityManager.DeviceProfile $profile;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IdentityManager$apply$2(IdentityManager.DeviceProfile deviceProfile, Continuation<? super IdentityManager$apply$2> continuation) {
        super(2, continuation);
        this.$profile = deviceProfile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$apply$2(this.$profile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((IdentityManager$apply$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IdentityManager$apply$2 identityManager$apply$2;
        Exception e;
        IdentityManager$apply$2 identityManager$apply$22;
        Object exec;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = false;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                identityManager$apply$2 = this;
                try {
                    JSONObject json = new JSONObject();
                    IdentityManager.DeviceProfile deviceProfile = identityManager$apply$2.$profile;
                    json.put("manufacturer", deviceProfile.getManufacturer());
                    json.put("model", deviceProfile.getModel());
                    json.put("brand", deviceProfile.getBrand());
                    json.put("device", deviceProfile.getDevice());
                    json.put("product", deviceProfile.getProduct());
                    json.put("fingerprint", deviceProfile.getFingerprint());
                    json.put("buildId", deviceProfile.getBuildId());
                    json.put("serial", deviceProfile.getSerial());
                    json.put("androidId", deviceProfile.getAndroidId());
                    json.put("imei", deviceProfile.getImei());
                    String jSONObject = json.toString(2);
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                    String escaped = StringsKt.replace$default(jSONObject, "'", "'\\''", false, 4, (Object) null);
                    String cmd = StringsKt.trimIndent("\n                    mkdir -p /data/local/tmp/spoofkit\n                    echo '" + escaped + "' > /data/local/tmp/spoofkit/saved_identity.json\n                    chmod 644 /data/local/tmp/spoofkit/saved_identity.json\n                    chcon u:object_r:shell_data_file:s0 /data/local/tmp/spoofkit/saved_identity.json 2>/dev/null || true\n                    echo OK\n                    ");
                    identityManager$apply$2.label = 1;
                    exec = RootShell.INSTANCE.exec(cmd, identityManager$apply$2);
                } catch (Exception $result3) {
                    e = $result3;
                    identityManager$apply$22 = identityManager$apply$2;
                    Log.e("IdentityManager", "apply failed", e);
                    return Boxing.boxBoolean(z);
                }
                if (exec == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result2 = $result;
                $result = exec;
                try {
                    RootShell.Result result = (RootShell.Result) $result;
                    z = StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                } catch (Exception e2) {
                    IdentityManager$apply$2 identityManager$apply$23 = identityManager$apply$2;
                    e = e2;
                    $result = $result2;
                    identityManager$apply$22 = identityManager$apply$23;
                    Log.e("IdentityManager", "apply failed", e);
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(z);
            case 1:
                identityManager$apply$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    identityManager$apply$2 = identityManager$apply$22;
                    $result2 = $result;
                    RootShell.Result result2 = (RootShell.Result) $result;
                    z = StringsKt.contains$default((CharSequence) result2.getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                } catch (Exception e3) {
                    e = e3;
                    Log.e("IdentityManager", "apply failed", e);
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(z);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

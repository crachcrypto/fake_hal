package com.fakehal.controller;

import android.util.Log;
import com.fakehal.controller.RootShell;
import com.fakehal.controller.TransformConfigManager;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TransformConfigManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.TransformConfigManager$save$2", f = "TransformConfigManager.kt", i = {}, l = {149}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class TransformConfigManager$save$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ TransformConfigManager.FullConfig $config;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformConfigManager$save$2(TransformConfigManager.FullConfig fullConfig, Continuation<? super TransformConfigManager$save$2> continuation) {
        super(2, continuation);
        this.$config = fullConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TransformConfigManager$save$2(this.$config, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((TransformConfigManager$save$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        TransformConfigManager$save$2 transformConfigManager$save$2;
        TransformConfigManager$save$2 transformConfigManager$save$22;
        Object exec;
        Object $result2;
        boolean ok;
        String str = "OK";
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                transformConfigManager$save$2 = this;
                try {
                    String json = TransformConfigManager.INSTANCE.fullConfigToJson(transformConfigManager$save$2.$config).toString(2);
                    Intrinsics.checkNotNull(json);
                    String escaped = StringsKt.replace$default(json, "'", "'\\''", false, 4, (Object) null);
                    transformConfigManager$save$2.label = 1;
                    exec = RootShell.INSTANCE.exec("echo '" + escaped + "' > /data/local/tmp/fakehal_transform.json && chmod 644 /data/local/tmp/fakehal_transform.json && (chcon u:object_r:shell_data_file:s0 /data/local/tmp/fakehal_transform.json 2>/dev/null || true) && echo OK", transformConfigManager$save$2);
                } catch (Exception e) {
                    e = e;
                    transformConfigManager$save$22 = transformConfigManager$save$2;
                    Log.e("TransformConfigMgr", "save: failed", e);
                    ok = false;
                    return Boxing.boxBoolean(ok);
                }
                if (exec == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result2 = $result;
                $result = exec;
                try {
                    RootShell.Result result = (RootShell.Result) $result;
                    ok = StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                    if (ok) {
                        str = "FAILED";
                    }
                    Log.i("TransformConfigMgr", "save: " + str);
                } catch (Exception e2) {
                    e = e2;
                    $result = $result2;
                    transformConfigManager$save$22 = transformConfigManager$save$2;
                    Log.e("TransformConfigMgr", "save: failed", e);
                    ok = false;
                    return Boxing.boxBoolean(ok);
                }
                return Boxing.boxBoolean(ok);
            case 1:
                transformConfigManager$save$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    transformConfigManager$save$2 = transformConfigManager$save$22;
                    $result2 = $result;
                    RootShell.Result result2 = (RootShell.Result) $result;
                    ok = StringsKt.contains$default((CharSequence) result2.getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                    if (ok) {
                    }
                    Log.i("TransformConfigMgr", "save: " + str);
                } catch (Exception e3) {
                    e = e3;
                    Log.e("TransformConfigMgr", "save: failed", e);
                    ok = false;
                    return Boxing.boxBoolean(ok);
                }
                return Boxing.boxBoolean(ok);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

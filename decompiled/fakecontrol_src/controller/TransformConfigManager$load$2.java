package com.fakehal.controller;

import android.util.Log;
import com.fakehal.controller.RootShell;
import com.fakehal.controller.TransformConfigManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TransformConfigManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/fakehal/controller/TransformConfigManager$FullConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.TransformConfigManager$load$2", f = "TransformConfigManager.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class TransformConfigManager$load$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TransformConfigManager.FullConfig>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransformConfigManager$load$2(Continuation<? super TransformConfigManager$load$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TransformConfigManager$load$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TransformConfigManager.FullConfig> continuation) {
        return ((TransformConfigManager$load$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000b. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        TransformConfigManager$load$2 transformConfigManager$load$2;
        Exception e;
        TransformConfigManager$load$2 transformConfigManager$load$22;
        Object $result2;
        RootShell.Result result;
        TransformConfigManager.FullConfig fullConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                transformConfigManager$load$2 = this;
                try {
                    transformConfigManager$load$2.label = 1;
                    Object exec = RootShell.INSTANCE.exec("cat /data/local/tmp/fakehal_transform.json 2>/dev/null", transformConfigManager$load$2);
                    if (exec == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = exec;
                    try {
                        result = (RootShell.Result) $result;
                        if (result.getExitCode() == 0 || !(true ^ StringsKt.isBlank(result.getOutput()))) {
                            Log.i("TransformConfigMgr", "load: no config found, using defaults");
                            fullConfig = new TransformConfigManager.FullConfig(null, null, 3, null);
                        } else {
                            JSONObject json = new JSONObject(StringsKt.trim((CharSequence) result.getOutput()).toString());
                            fullConfig = TransformConfigManager.INSTANCE.fullConfigFromJson(json);
                            Log.i("TransformConfigMgr", "load: loaded config from /data/local/tmp/fakehal_transform.json");
                        }
                        return fullConfig;
                    } catch (Exception e2) {
                        TransformConfigManager$load$2 transformConfigManager$load$23 = transformConfigManager$load$2;
                        e = e2;
                        $result = $result2;
                        transformConfigManager$load$22 = transformConfigManager$load$23;
                        Log.e("TransformConfigMgr", "load: failed to parse config", e);
                        return new TransformConfigManager.FullConfig(null, null, 3, null);
                    }
                } catch (Exception $result3) {
                    e = $result3;
                    transformConfigManager$load$22 = transformConfigManager$load$2;
                    Log.e("TransformConfigMgr", "load: failed to parse config", e);
                    return new TransformConfigManager.FullConfig(null, null, 3, null);
                }
            case 1:
                transformConfigManager$load$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    transformConfigManager$load$2 = transformConfigManager$load$22;
                    $result2 = $result;
                    result = (RootShell.Result) $result;
                    if (result.getExitCode() == 0) {
                        break;
                    }
                    Log.i("TransformConfigMgr", "load: no config found, using defaults");
                    fullConfig = new TransformConfigManager.FullConfig(null, null, 3, null);
                    return fullConfig;
                } catch (Exception e3) {
                    e = e3;
                    Log.e("TransformConfigMgr", "load: failed to parse config", e);
                    return new TransformConfigManager.FullConfig(null, null, 3, null);
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

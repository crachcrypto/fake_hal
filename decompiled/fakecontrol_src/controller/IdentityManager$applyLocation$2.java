package com.fakehal.controller;

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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$applyLocation$2", f = "IdentityManager.kt", i = {}, l = {176}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class IdentityManager$applyLocation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ IdentityManager.LocationProfile $loc;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IdentityManager$applyLocation$2(IdentityManager.LocationProfile locationProfile, Continuation<? super IdentityManager$applyLocation$2> continuation) {
        super(2, continuation);
        this.$loc = locationProfile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$applyLocation$2(this.$loc, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((IdentityManager$applyLocation$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IdentityManager$applyLocation$2 identityManager$applyLocation$2;
        IdentityManager$applyLocation$2 identityManager$applyLocation$22;
        Object exec;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = false;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                identityManager$applyLocation$2 = this;
                try {
                    String cmd = StringsKt.trimIndent("\n                    mkdir -p /data/local/tmp/spoofkit\n                    touch /data/local/tmp/spoofkit/active_config\n                    update_key() {\n                      key=\"$1\"; value=\"$2\"\n                      file=/data/local/tmp/spoofkit/active_config\n                      if grep -q \"^$key=\" \"$file\" 2>/dev/null; then\n                        sed -i \"s|^$key=.*|$key=$value|\" \"$file\"\n                      else\n                        echo \"$key=$value\" >> \"$file\"\n                      fi\n                    }\n                    update_key gps_enabled '" + (identityManager$applyLocation$2.$loc.getEnabled() ? "true" : "false") + "'\n                    update_key gps_lat '" + identityManager$applyLocation$2.$loc.getLat() + "'\n                    update_key gps_lon '" + identityManager$applyLocation$2.$loc.getLon() + "'\n                    chmod 644 /data/local/tmp/spoofkit/active_config\n                    echo OK\n                    ");
                    identityManager$applyLocation$2.label = 1;
                    exec = RootShell.INSTANCE.exec(cmd, identityManager$applyLocation$2);
                } catch (Exception e) {
                    identityManager$applyLocation$22 = identityManager$applyLocation$2;
                    return Boxing.boxBoolean(z);
                }
                if (exec == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result2 = $result;
                $result = exec;
                try {
                    z = StringsKt.contains$default((CharSequence) ((RootShell.Result) $result).getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                } catch (Exception e2) {
                    $result = $result2;
                    identityManager$applyLocation$22 = identityManager$applyLocation$2;
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(z);
            case 1:
                identityManager$applyLocation$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    identityManager$applyLocation$2 = identityManager$applyLocation$22;
                    $result2 = $result;
                    z = StringsKt.contains$default((CharSequence) ((RootShell.Result) $result).getOutput(), (CharSequence) "OK", false, 2, (Object) null);
                } catch (Exception e3) {
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(z);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

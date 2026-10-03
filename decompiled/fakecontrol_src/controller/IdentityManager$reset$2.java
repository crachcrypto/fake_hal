package com.fakehal.controller;

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
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$reset$2", f = "IdentityManager.kt", i = {}, l = {185}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class IdentityManager$reset$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityManager$reset$2(Continuation<? super IdentityManager$reset$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$reset$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((IdentityManager$reset$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IdentityManager$reset$2 identityManager$reset$2;
        IdentityManager$reset$2 identityManager$reset$22;
        Object exec;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = false;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                identityManager$reset$2 = this;
                try {
                    identityManager$reset$2.label = 1;
                    exec = RootShell.INSTANCE.exec("rm -f /data/local/tmp/spoofkit/saved_identity.json && echo OK", identityManager$reset$2);
                } catch (Exception e) {
                    identityManager$reset$22 = identityManager$reset$2;
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
                    identityManager$reset$22 = identityManager$reset$2;
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(z);
            case 1:
                identityManager$reset$22 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    identityManager$reset$2 = identityManager$reset$22;
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

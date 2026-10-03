package com.fakehal.controller;

import androidx.compose.runtime.snapshots.SnapshotStateMap;
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

/* compiled from: SecurityScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.SecurityScreenKt$SecurityScreen$runChecks$1$2$1", f = "SecurityScreen.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class SecurityScreenKt$SecurityScreen$runChecks$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SecurityCheck $check;
    final /* synthetic */ SnapshotStateMap<String, Boolean> $results;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecurityScreenKt$SecurityScreen$runChecks$1$2$1(SecurityCheck securityCheck, SnapshotStateMap<String, Boolean> snapshotStateMap, Continuation<? super SecurityScreenKt$SecurityScreen$runChecks$1$2$1> continuation) {
        super(2, continuation);
        this.$check = securityCheck;
        this.$results = snapshotStateMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SecurityScreenKt$SecurityScreen$runChecks$1$2$1(this.$check, this.$results, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SecurityScreenKt$SecurityScreen$runChecks$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SecurityScreenKt$SecurityScreen$runChecks$1$2$1 securityScreenKt$SecurityScreen$runChecks$1$2$1;
        boolean contains$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                securityScreenKt$SecurityScreen$runChecks$1$2$1 = this;
                securityScreenKt$SecurityScreen$runChecks$1$2$1.label = 1;
                Object exec = RootShell.INSTANCE.exec(securityScreenKt$SecurityScreen$runChecks$1$2$1.$check.getCommand(), securityScreenKt$SecurityScreen$runChecks$1$2$1);
                if (exec != coroutine_suspended) {
                    $result = exec;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                securityScreenKt$SecurityScreen$runChecks$1$2$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String output = StringsKt.trim((CharSequence) ((RootShell.Result) $result).getOutput()).toString();
        if (securityScreenKt$SecurityScreen$runChecks$1$2$1.$check.getInvertResult()) {
            contains$default = !StringsKt.contains$default((CharSequence) output, (CharSequence) securityScreenKt$SecurityScreen$runChecks$1$2$1.$check.getPassCondition(), false, 2, (Object) null);
        } else {
            contains$default = StringsKt.contains$default((CharSequence) output, (CharSequence) securityScreenKt$SecurityScreen$runChecks$1$2$1.$check.getPassCondition(), false, 2, (Object) null);
        }
        boolean pass = contains$default;
        securityScreenKt$SecurityScreen$runChecks$1$2$1.$results.put(securityScreenKt$SecurityScreen$runChecks$1$2$1.$check.getId(), Boxing.boxBoolean(pass));
        return Unit.INSTANCE;
    }
}

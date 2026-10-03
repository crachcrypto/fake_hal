package com.fakehal.controller;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SecurityScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.SecurityScreenKt$SecurityScreen$runChecks$1", f = "SecurityScreen.kt", i = {}, l = {193}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class SecurityScreenKt$SecurityScreen$runChecks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $isRunning;
    final /* synthetic */ SnapshotStateMap<String, Boolean> $results;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecurityScreenKt$SecurityScreen$runChecks$1(MutableState<Boolean> mutableState, SnapshotStateMap<String, Boolean> snapshotStateMap, Continuation<? super SecurityScreenKt$SecurityScreen$runChecks$1> continuation) {
        super(2, continuation);
        this.$isRunning = mutableState;
        this.$results = snapshotStateMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SecurityScreenKt$SecurityScreen$runChecks$1 securityScreenKt$SecurityScreen$runChecks$1 = new SecurityScreenKt$SecurityScreen$runChecks$1(this.$isRunning, this.$results, continuation);
        securityScreenKt$SecurityScreen$runChecks$1.L$0 = obj;
        return securityScreenKt$SecurityScreen$runChecks$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SecurityScreenKt$SecurityScreen$runChecks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SecurityScreenKt$SecurityScreen$runChecks$1 securityScreenKt$SecurityScreen$runChecks$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = (CoroutineScope) this.L$0;
                this.$isRunning.setValue(Boxing.boxBoolean(true));
                Iterable $this$forEach$iv = SecurityScreenKt.getSECURITY_CHECKS();
                SnapshotStateMap<String, Boolean> snapshotStateMap = this.$results;
                for (Object element$iv : $this$forEach$iv) {
                    SecurityCheck it = (SecurityCheck) element$iv;
                    snapshotStateMap.put(it.getId(), null);
                }
                Iterable $this$forEach$iv2 = SecurityScreenKt.getSECURITY_CHECKS();
                SnapshotStateMap<String, Boolean> snapshotStateMap2 = this.$results;
                for (Object element$iv2 : $this$forEach$iv2) {
                    SecurityCheck check = (SecurityCheck) element$iv2;
                    BuildersKt__Builders_commonKt.launch$default($this$launch, Dispatchers.getIO(), null, new SecurityScreenKt$SecurityScreen$runChecks$1$2$1(check, snapshotStateMap2, null), 2, null);
                }
                this.label = 1;
                if (DelayKt.delay(3000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                securityScreenKt$SecurityScreen$runChecks$1 = this;
                break;
            case 1:
                securityScreenKt$SecurityScreen$runChecks$1 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        securityScreenKt$SecurityScreen$runChecks$1.$isRunning.setValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }
}

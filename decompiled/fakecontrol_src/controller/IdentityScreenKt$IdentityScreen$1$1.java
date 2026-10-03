package com.fakehal.controller;

import androidx.compose.runtime.MutableState;
import androidx.core.view.MotionEventCompat;
import com.fakehal.controller.IdentityManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IdentityScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$1$1", f = "IdentityScreen.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_3}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class IdentityScreenKt$IdentityScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $brand$delegate;
    final /* synthetic */ MutableState<String> $device$delegate;
    final /* synthetic */ MutableState<String> $fingerprint$delegate;
    final /* synthetic */ MutableState<String> $manufacturer$delegate;
    final /* synthetic */ MutableState<String> $model$delegate;
    final /* synthetic */ MutableState<String> $serial$delegate;
    final /* synthetic */ MutableState<String> $statusText$delegate;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IdentityScreenKt$IdentityScreen$1$1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super IdentityScreenKt$IdentityScreen$1$1> continuation) {
        super(2, continuation);
        this.$manufacturer$delegate = mutableState;
        this.$model$delegate = mutableState2;
        this.$brand$delegate = mutableState3;
        this.$device$delegate = mutableState4;
        this.$fingerprint$delegate = mutableState5;
        this.$serial$delegate = mutableState6;
        this.$statusText$delegate = mutableState7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityScreenKt$IdentityScreen$1$1(this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, this.$statusText$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((IdentityScreenKt$IdentityScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IdentityScreenKt$IdentityScreen$1$1 identityScreenKt$IdentityScreen$1$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                identityScreenKt$IdentityScreen$1$1 = this;
                identityScreenKt$IdentityScreen$1$1.label = 1;
                Object readCurrent = IdentityManager.INSTANCE.readCurrent(identityScreenKt$IdentityScreen$1$1);
                if (readCurrent != coroutine_suspended) {
                    $result = readCurrent;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                identityScreenKt$IdentityScreen$1$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        IdentityManager.DeviceProfile current = (IdentityManager.DeviceProfile) $result;
        identityScreenKt$IdentityScreen$1$1.$manufacturer$delegate.setValue(current.getManufacturer());
        identityScreenKt$IdentityScreen$1$1.$model$delegate.setValue(current.getModel());
        identityScreenKt$IdentityScreen$1$1.$brand$delegate.setValue(current.getBrand());
        identityScreenKt$IdentityScreen$1$1.$device$delegate.setValue(current.getDevice());
        identityScreenKt$IdentityScreen$1$1.$fingerprint$delegate.setValue(current.getFingerprint());
        identityScreenKt$IdentityScreen$1$1.$serial$delegate.setValue(current.getSerial());
        identityScreenKt$IdentityScreen$1$1.$statusText$delegate.setValue("Loaded current identity");
        return Unit.INSTANCE;
    }
}

package com.fakehal.controller;

import androidx.compose.runtime.MutableState;
import com.fakehal.controller.TransformConfigManager;
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
/* compiled from: CameraControlScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.CameraControlScreenKt$CameraControlScreen$1$1", f = "CameraControlScreen.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CameraControlScreenKt$CameraControlScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<TransformConfigManager.FullConfig> $fullConfig$delegate;
    final /* synthetic */ MutableState<String> $statusText$delegate;
    Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraControlScreenKt$CameraControlScreen$1$1(MutableState<TransformConfigManager.FullConfig> mutableState, MutableState<String> mutableState2, Continuation<? super CameraControlScreenKt$CameraControlScreen$1$1> continuation) {
        super(2, continuation);
        this.$fullConfig$delegate = mutableState;
        this.$statusText$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraControlScreenKt$CameraControlScreen$1$1(this.$fullConfig$delegate, this.$statusText$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraControlScreenKt$CameraControlScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CameraControlScreenKt$CameraControlScreen$1$1 cameraControlScreenKt$CameraControlScreen$1$1;
        MutableState<TransformConfigManager.FullConfig> mutableState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                cameraControlScreenKt$CameraControlScreen$1$1 = this;
                mutableState = cameraControlScreenKt$CameraControlScreen$1$1.$fullConfig$delegate;
                cameraControlScreenKt$CameraControlScreen$1$1.L$0 = mutableState;
                cameraControlScreenKt$CameraControlScreen$1$1.label = 1;
                Object load = TransformConfigManager.INSTANCE.load(cameraControlScreenKt$CameraControlScreen$1$1);
                if (load != coroutine_suspended) {
                    $result = load;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                MutableState<TransformConfigManager.FullConfig> mutableState2 = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                mutableState = mutableState2;
                cameraControlScreenKt$CameraControlScreen$1$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState.setValue((TransformConfigManager.FullConfig) $result);
        cameraControlScreenKt$CameraControlScreen$1$1.$statusText$delegate.setValue("Config loaded. Select a photo.");
        return Unit.INSTANCE;
    }
}

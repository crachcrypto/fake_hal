package com.fakehal.controller;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.fakehal.controller.FakeHalManager;
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
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.MainActivityKt$FakeHalScreen$1$1", f = "MainActivity.kt", i = {}, l = {109, 123}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MainActivityKt$FakeHalScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $backRotate180$delegate;
    final /* synthetic */ MutableState<Boolean> $chromeFrontMirror$delegate;
    final /* synthetic */ MutableState<Boolean> $chromeFrontRotate180$delegate;
    final /* synthetic */ MutableState<Boolean> $frontMirror$delegate;
    final /* synthetic */ MutableState<Boolean> $frontRotate180$delegate;
    final /* synthetic */ MutableState<Boolean> $isLoading$delegate;
    final /* synthetic */ MutableIntState $quickRotateDeg$delegate;
    final /* synthetic */ MutableState<FakeHalManager.Status> $status$delegate;
    final /* synthetic */ MutableState<String> $statusText$delegate;
    Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$FakeHalScreen$1$1(MutableState<FakeHalManager.Status> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, MutableState<Boolean> mutableState6, MutableState<Boolean> mutableState7, MutableState<Boolean> mutableState8, MutableIntState mutableIntState, Continuation<? super MainActivityKt$FakeHalScreen$1$1> continuation) {
        super(2, continuation);
        this.$status$delegate = mutableState;
        this.$isLoading$delegate = mutableState2;
        this.$statusText$delegate = mutableState3;
        this.$backRotate180$delegate = mutableState4;
        this.$frontRotate180$delegate = mutableState5;
        this.$frontMirror$delegate = mutableState6;
        this.$chromeFrontRotate180$delegate = mutableState7;
        this.$chromeFrontMirror$delegate = mutableState8;
        this.$quickRotateDeg$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$FakeHalScreen$1$1(this.$status$delegate, this.$isLoading$delegate, this.$statusText$delegate, this.$backRotate180$delegate, this.$frontRotate180$delegate, this.$frontMirror$delegate, this.$chromeFrontRotate180$delegate, this.$chromeFrontMirror$delegate, this.$quickRotateDeg$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$FakeHalScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        MutableState<FakeHalManager.Status> mutableState;
        MainActivityKt$FakeHalScreen$1$1 mainActivityKt$FakeHalScreen$1$1;
        FakeHalManager.Status FakeHalScreen$lambda$1;
        String str;
        MainActivityKt$FakeHalScreen$1$1 mainActivityKt$FakeHalScreen$1$12;
        FakeHalManager.Status FakeHalScreen$lambda$12;
        FakeHalManager.Status FakeHalScreen$lambda$13;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MutableState<FakeHalManager.Status> mutableState2 = this.$status$delegate;
                this.L$0 = mutableState2;
                this.label = 1;
                Object status = FakeHalManager.INSTANCE.getStatus(this);
                if (status == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = status;
                mutableState = mutableState2;
                mainActivityKt$FakeHalScreen$1$1 = this;
                mutableState.setValue((FakeHalManager.Status) $result);
                MainActivityKt.FakeHalScreen$lambda$5(mainActivityKt$FakeHalScreen$1$1.$isLoading$delegate, false);
                MutableState<String> mutableState3 = mainActivityKt$FakeHalScreen$1$1.$statusText$delegate;
                FakeHalScreen$lambda$1 = MainActivityKt.FakeHalScreen$lambda$1(mainActivityKt$FakeHalScreen$1$1.$status$delegate);
                if (FakeHalScreen$lambda$1.getHasRoot()) {
                    str = "Root not available";
                } else {
                    FakeHalScreen$lambda$12 = MainActivityKt.FakeHalScreen$lambda$1(mainActivityKt$FakeHalScreen$1$1.$status$delegate);
                    if (FakeHalScreen$lambda$12.isRunning()) {
                        FakeHalScreen$lambda$13 = MainActivityKt.FakeHalScreen$lambda$1(mainActivityKt$FakeHalScreen$1$1.$status$delegate);
                        str = "FakeHAL running (" + FakeHalScreen$lambda$13.getFrameCount() + " frames)";
                    } else {
                        str = "FakeHAL stopped";
                    }
                }
                mutableState3.setValue(str);
                mainActivityKt$FakeHalScreen$1$1.L$0 = null;
                mainActivityKt$FakeHalScreen$1$1.label = 2;
                $result = FakeHalManager.INSTANCE.readTransformState(mainActivityKt$FakeHalScreen$1$1);
                if ($result != coroutine_suspended) {
                    return coroutine_suspended;
                }
                mainActivityKt$FakeHalScreen$1$12 = mainActivityKt$FakeHalScreen$1$1;
                FakeHalManager.TransformState transformState = (FakeHalManager.TransformState) $result;
                MainActivityKt.FakeHalScreen$lambda$11(mainActivityKt$FakeHalScreen$1$12.$backRotate180$delegate, transformState.getBackRotate180());
                MainActivityKt.FakeHalScreen$lambda$14(mainActivityKt$FakeHalScreen$1$12.$frontRotate180$delegate, transformState.getFrontRotate180());
                MainActivityKt.FakeHalScreen$lambda$17(mainActivityKt$FakeHalScreen$1$12.$frontMirror$delegate, transformState.getFrontMirror());
                MainActivityKt.FakeHalScreen$lambda$20(mainActivityKt$FakeHalScreen$1$12.$chromeFrontRotate180$delegate, transformState.getChromeFrontRotate180());
                MainActivityKt.FakeHalScreen$lambda$23(mainActivityKt$FakeHalScreen$1$12.$chromeFrontMirror$delegate, transformState.getChromeFrontMirror());
                mainActivityKt$FakeHalScreen$1$12.$quickRotateDeg$delegate.setIntValue(transformState.getPreviewRotation());
                return Unit.INSTANCE;
            case 1:
                MutableState<FakeHalManager.Status> mutableState4 = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                mutableState = mutableState4;
                mainActivityKt$FakeHalScreen$1$1 = this;
                mutableState.setValue((FakeHalManager.Status) $result);
                MainActivityKt.FakeHalScreen$lambda$5(mainActivityKt$FakeHalScreen$1$1.$isLoading$delegate, false);
                MutableState<String> mutableState32 = mainActivityKt$FakeHalScreen$1$1.$statusText$delegate;
                FakeHalScreen$lambda$1 = MainActivityKt.FakeHalScreen$lambda$1(mainActivityKt$FakeHalScreen$1$1.$status$delegate);
                if (FakeHalScreen$lambda$1.getHasRoot()) {
                }
                mutableState32.setValue(str);
                mainActivityKt$FakeHalScreen$1$1.L$0 = null;
                mainActivityKt$FakeHalScreen$1$1.label = 2;
                $result = FakeHalManager.INSTANCE.readTransformState(mainActivityKt$FakeHalScreen$1$1);
                if ($result != coroutine_suspended) {
                }
                break;
            case 2:
                mainActivityKt$FakeHalScreen$1$12 = this;
                ResultKt.throwOnFailure($result);
                FakeHalManager.TransformState transformState2 = (FakeHalManager.TransformState) $result;
                MainActivityKt.FakeHalScreen$lambda$11(mainActivityKt$FakeHalScreen$1$12.$backRotate180$delegate, transformState2.getBackRotate180());
                MainActivityKt.FakeHalScreen$lambda$14(mainActivityKt$FakeHalScreen$1$12.$frontRotate180$delegate, transformState2.getFrontRotate180());
                MainActivityKt.FakeHalScreen$lambda$17(mainActivityKt$FakeHalScreen$1$12.$frontMirror$delegate, transformState2.getFrontMirror());
                MainActivityKt.FakeHalScreen$lambda$20(mainActivityKt$FakeHalScreen$1$12.$chromeFrontRotate180$delegate, transformState2.getChromeFrontRotate180());
                MainActivityKt.FakeHalScreen$lambda$23(mainActivityKt$FakeHalScreen$1$12.$chromeFrontMirror$delegate, transformState2.getChromeFrontMirror());
                mainActivityKt$FakeHalScreen$1$12.$quickRotateDeg$delegate.setIntValue(transformState2.getPreviewRotation());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

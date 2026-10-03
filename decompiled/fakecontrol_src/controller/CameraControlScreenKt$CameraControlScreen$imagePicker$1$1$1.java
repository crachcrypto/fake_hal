package com.fakehal.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CameraControlScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1", f = "CameraControlScreen.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Bitmap> $baseBitmap$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ Uri $it;
    final /* synthetic */ MutableState<String> $statusText$delegate;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1(MutableState<String> mutableState, Context context, Uri uri, MutableState<Bitmap> mutableState2, Continuation<? super CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1> continuation) {
        super(2, continuation);
        this.$statusText$delegate = mutableState;
        this.$context = context;
        this.$it = uri;
        this.$baseBitmap$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1(this.$statusText$delegate, this.$context, this.$it, this.$baseBitmap$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1 cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1 = this;
                cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.$statusText$delegate.setValue("Loading preview...");
                cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.label = 1;
                Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new CameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1$bmp$1(cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.$context, cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.$it, null), cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1);
                if (withContext != coroutine_suspended) {
                    $result = withContext;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Bitmap bmp = (Bitmap) $result;
        cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.$baseBitmap$delegate.setValue(bmp);
        MutableState<String> mutableState = cameraControlScreenKt$CameraControlScreen$imagePicker$1$1$1.$statusText$delegate;
        if (bmp != null) {
            str = "Loaded " + bmp.getWidth() + "x" + bmp.getHeight();
        } else {
            str = "Failed to load";
        }
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }
}

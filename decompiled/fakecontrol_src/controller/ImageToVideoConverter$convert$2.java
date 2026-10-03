package com.fakehal.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.fakehal.controller.ImageToVideoConverter;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ImageToVideoConverter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.ImageToVideoConverter$convert$2", f = "ImageToVideoConverter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ImageToVideoConverter$convert$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ ImageToVideoConverter.TransformConfig $config;
    final /* synthetic */ Context $context;
    final /* synthetic */ Uri $imageUri;
    final /* synthetic */ File $outputFile;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageToVideoConverter$convert$2(Context context, Uri uri, ImageToVideoConverter.TransformConfig transformConfig, File file, Continuation<? super ImageToVideoConverter$convert$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$imageUri = uri;
        this.$config = transformConfig;
        this.$outputFile = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageToVideoConverter$convert$2(this.$context, this.$imageUri, this.$config, this.$outputFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((ImageToVideoConverter$convert$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Bitmap bmp;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                boolean z = false;
                try {
                    bmp = ImageToVideoConverter.loadAndTransform$default(ImageToVideoConverter.INSTANCE, this.$context, this.$imageUri, this.$config, 0, 0, 24, null);
                } catch (Exception e) {
                    Log.e("ImageToVideoConverter", "convert failed", e);
                }
                if (bmp != null) {
                    ImageToVideoConverter.INSTANCE.encodeToMp4(bmp, this.$outputFile, bmp.getWidth(), bmp.getHeight(), this.$config.getSensorOrientation());
                    bmp.recycle();
                    z = true;
                    return Boxing.boxBoolean(z);
                }
                return Boxing.boxBoolean(false);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

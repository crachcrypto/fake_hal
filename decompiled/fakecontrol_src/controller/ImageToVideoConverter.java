package com.fakehal.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ImageToVideoConverter.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001;B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J0\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0086@¢\u0006\u0002\u0010\u001bJ0\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u001eJ0\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002J6\u0010%\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010'\u001a\u00020\u00042\b\b\u0002\u0010(\u001a\u00020\u0004H\u0002J\"\u0010)\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00162\b\b\u0002\u0010*\u001a\u00020\u0004J\u0010\u0010+\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010,\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J \u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0002J\u0016\u00103\u001a\u0002042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u00106\u001a\u000204H\u0002J(\u00107\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/fakehal/controller/ImageToVideoConverter;", "", "()V", "BITRATE", "", "DURATION_SEC", "FPS", "LONG_SIDE", "SHORT_SIDE", "TAG", "", "applyPreviewTransform", "Landroid/graphics/Bitmap;", "src", "rotation", "mirrorH", "", "mirrorV", "convert", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "outputFile", "Ljava/io/File;", "config", "Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;", "(Landroid/content/Context;Landroid/net/Uri;Ljava/io/File;Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertLegacy", "compensateFrontPreviewMirror", "(Landroid/content/Context;Landroid/net/Uri;Ljava/io/File;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeToMp4", "", "bitmap", "encW", "encH", "sensorOrientation", "loadAndTransform", "uri", "canvasW", "canvasH", "loadPreviewBitmap", "maxSize", "mirrorBitmapH", "mirrorBitmapV", "packArgbToNV12", "", "argb", "", "w", "h", "readExifRotation", "", "rotateBitmap", "degrees", "scaleToCanvas", "targetW", "targetH", "mode", "TransformConfig", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageToVideoConverter {
    public static final int $stable = 0;
    private static final int BITRATE = 8000000;
    private static final int DURATION_SEC = 3;
    private static final int FPS = 30;
    public static final ImageToVideoConverter INSTANCE = new ImageToVideoConverter();
    private static final int LONG_SIDE = 1920;
    private static final int SHORT_SIDE = 1080;
    private static final String TAG = "ImageToVideoConverter";

    private ImageToVideoConverter() {
    }

    /* compiled from: ImageToVideoConverter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\tHÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006 "}, d2 = {"Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;", "", "userRotation", "", "mirrorH", "", "mirrorV", "sensorOrientation", "aspectMode", "", "compensateFrontMirror", "(IZZILjava/lang/String;Z)V", "getAspectMode", "()Ljava/lang/String;", "getCompensateFrontMirror", "()Z", "getMirrorH", "getMirrorV", "getSensorOrientation", "()I", "getUserRotation", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TransformConfig {
        public static final int $stable = 0;
        private final String aspectMode;
        private final boolean compensateFrontMirror;
        private final boolean mirrorH;
        private final boolean mirrorV;
        private final int sensorOrientation;
        private final int userRotation;

        public TransformConfig() {
            this(0, false, false, 0, null, false, 63, null);
        }

        public static /* synthetic */ TransformConfig copy$default(TransformConfig transformConfig, int i, boolean z, boolean z2, int i2, String str, boolean z3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = transformConfig.userRotation;
            }
            if ((i3 & 2) != 0) {
                z = transformConfig.mirrorH;
            }
            boolean z4 = z;
            if ((i3 & 4) != 0) {
                z2 = transformConfig.mirrorV;
            }
            boolean z5 = z2;
            if ((i3 & 8) != 0) {
                i2 = transformConfig.sensorOrientation;
            }
            int i4 = i2;
            if ((i3 & 16) != 0) {
                str = transformConfig.aspectMode;
            }
            String str2 = str;
            if ((i3 & 32) != 0) {
                z3 = transformConfig.compensateFrontMirror;
            }
            return transformConfig.copy(i, z4, z5, i4, str2, z3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getUserRotation() {
            return this.userRotation;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getMirrorH() {
            return this.mirrorH;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getMirrorV() {
            return this.mirrorV;
        }

        /* renamed from: component4, reason: from getter */
        public final int getSensorOrientation() {
            return this.sensorOrientation;
        }

        /* renamed from: component5, reason: from getter */
        public final String getAspectMode() {
            return this.aspectMode;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getCompensateFrontMirror() {
            return this.compensateFrontMirror;
        }

        public final TransformConfig copy(int userRotation, boolean mirrorH, boolean mirrorV, int sensorOrientation, String aspectMode, boolean compensateFrontMirror) {
            Intrinsics.checkNotNullParameter(aspectMode, "aspectMode");
            return new TransformConfig(userRotation, mirrorH, mirrorV, sensorOrientation, aspectMode, compensateFrontMirror);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransformConfig)) {
                return false;
            }
            TransformConfig transformConfig = (TransformConfig) other;
            return this.userRotation == transformConfig.userRotation && this.mirrorH == transformConfig.mirrorH && this.mirrorV == transformConfig.mirrorV && this.sensorOrientation == transformConfig.sensorOrientation && Intrinsics.areEqual(this.aspectMode, transformConfig.aspectMode) && this.compensateFrontMirror == transformConfig.compensateFrontMirror;
        }

        public int hashCode() {
            return (((((((((Integer.hashCode(this.userRotation) * 31) + Boolean.hashCode(this.mirrorH)) * 31) + Boolean.hashCode(this.mirrorV)) * 31) + Integer.hashCode(this.sensorOrientation)) * 31) + this.aspectMode.hashCode()) * 31) + Boolean.hashCode(this.compensateFrontMirror);
        }

        public String toString() {
            return "TransformConfig(userRotation=" + this.userRotation + ", mirrorH=" + this.mirrorH + ", mirrorV=" + this.mirrorV + ", sensorOrientation=" + this.sensorOrientation + ", aspectMode=" + this.aspectMode + ", compensateFrontMirror=" + this.compensateFrontMirror + ")";
        }

        public TransformConfig(int userRotation, boolean mirrorH, boolean mirrorV, int sensorOrientation, String aspectMode, boolean compensateFrontMirror) {
            Intrinsics.checkNotNullParameter(aspectMode, "aspectMode");
            this.userRotation = userRotation;
            this.mirrorH = mirrorH;
            this.mirrorV = mirrorV;
            this.sensorOrientation = sensorOrientation;
            this.aspectMode = aspectMode;
            this.compensateFrontMirror = compensateFrontMirror;
        }

        public /* synthetic */ TransformConfig(int i, boolean z, boolean z2, int i2, String str, boolean z3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? false : z, (i3 & 4) != 0 ? false : z2, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "crop" : str, (i3 & 32) != 0 ? false : z3);
        }

        public final int getUserRotation() {
            return this.userRotation;
        }

        public final boolean getMirrorH() {
            return this.mirrorH;
        }

        public final boolean getMirrorV() {
            return this.mirrorV;
        }

        public final int getSensorOrientation() {
            return this.sensorOrientation;
        }

        public final String getAspectMode() {
            return this.aspectMode;
        }

        public final boolean getCompensateFrontMirror() {
            return this.compensateFrontMirror;
        }
    }

    public static /* synthetic */ Object convert$default(ImageToVideoConverter imageToVideoConverter, Context context, Uri uri, File file, TransformConfig transformConfig, Continuation continuation, int i, Object obj) {
        TransformConfig transformConfig2;
        if ((i & 8) == 0) {
            transformConfig2 = transformConfig;
        } else {
            transformConfig2 = new TransformConfig(0, false, false, 0, null, false, 63, null);
        }
        return imageToVideoConverter.convert(context, uri, file, transformConfig2, continuation);
    }

    public final Object convert(Context context, Uri imageUri, File outputFile, TransformConfig config, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new ImageToVideoConverter$convert$2(context, imageUri, config, outputFile, null), continuation);
    }

    public static /* synthetic */ Object convertLegacy$default(ImageToVideoConverter imageToVideoConverter, Context context, Uri uri, File file, boolean z, Continuation continuation, int i, Object obj) {
        boolean z2;
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return imageToVideoConverter.convertLegacy(context, uri, file, z2, continuation);
    }

    public final Object convertLegacy(Context context, Uri imageUri, File outputFile, boolean compensateFrontPreviewMirror, Continuation<? super Boolean> continuation) {
        TransformConfig config = new TransformConfig(0, false, false, 90, "crop", compensateFrontPreviewMirror);
        return convert(context, imageUri, outputFile, config, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Bitmap loadAndTransform$default(ImageToVideoConverter imageToVideoConverter, Context context, Uri uri, TransformConfig transformConfig, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 8) == 0) {
            i4 = i;
        } else {
            i4 = LONG_SIDE;
        }
        if ((i3 & 16) == 0) {
            i5 = i2;
        } else {
            i5 = SHORT_SIDE;
        }
        return imageToVideoConverter.loadAndTransform(context, uri, transformConfig, i4, i5);
    }

    private final Bitmap loadAndTransform(Context context, Uri uri, TransformConfig config, int canvasW, int canvasH) {
        float exifDeg = readExifRotation(context, uri);
        Log.i(TAG, "EXIF rotation: " + exifDeg);
        InputStream input = context.getContentResolver().openInputStream(uri);
        if (input == null) {
            return null;
        }
        Bitmap decoded = BitmapFactory.decodeStream(input);
        input.close();
        if (decoded == null) {
            return null;
        }
        Log.i(TAG, "Decoded: " + decoded.getWidth() + "x" + decoded.getHeight());
        Bitmap bmp = decoded;
        if (!(exifDeg == 0.0f)) {
            bmp = rotateBitmap(bmp, exifDeg);
        }
        if (config.getCompensateFrontMirror()) {
            bmp = mirrorBitmapH(bmp);
        }
        if (config.getMirrorH()) {
            bmp = mirrorBitmapH(bmp);
        }
        if (config.getMirrorV()) {
            bmp = mirrorBitmapV(bmp);
        }
        if (config.getUserRotation() != 0) {
            bmp = rotateBitmap(bmp, config.getUserRotation());
        }
        if (config.getSensorOrientation() != 0) {
            bmp = rotateBitmap(bmp, -config.getSensorOrientation());
        }
        return scaleToCanvas(bmp, canvasW, canvasH, config.getAspectMode());
    }

    public final float readExifRotation(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            InputStream stream = context.getContentResolver().openInputStream(uri);
            if (stream == null) {
                return 0.0f;
            }
            ExifInterface exif = new ExifInterface(stream);
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            stream.close();
            switch (ori) {
                case 3:
                    return 180.0f;
                case 6:
                    return 90.0f;
                case 8:
                    return 270.0f;
                default:
                    return 0.0f;
            }
        } catch (Exception e) {
            return 0.0f;
        }
    }

    public static /* synthetic */ Bitmap loadPreviewBitmap$default(ImageToVideoConverter imageToVideoConverter, Context context, Uri uri, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 1024;
        }
        return imageToVideoConverter.loadPreviewBitmap(context, uri, i);
    }

    public final Bitmap loadPreviewBitmap(Context context, Uri uri, int maxSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        float exifDeg = readExifRotation(context, uri);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        if (openInputStream != null) {
            InputStream inputStream = openInputStream;
            try {
                InputStream it = inputStream;
                BitmapFactory.decodeStream(it, null, opts);
                CloseableKt.closeFinally(inputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStream, th);
                    throw th2;
                }
            }
        }
        int w = opts.outWidth;
        int h = opts.outHeight;
        if (w <= 0 || h <= 0) {
            return null;
        }
        int sample = 1;
        while (w / sample > maxSize * 2 && h / sample > maxSize * 2) {
            sample *= 2;
        }
        BitmapFactory.Options decOpts = new BitmapFactory.Options();
        decOpts.inSampleSize = sample;
        InputStream stream = context.getContentResolver().openInputStream(uri);
        if (stream == null) {
            return null;
        }
        Bitmap bmp = BitmapFactory.decodeStream(stream, null, decOpts);
        stream.close();
        if (bmp == null) {
            return null;
        }
        return !(exifDeg == 0.0f) ? rotateBitmap(bmp, exifDeg) : bmp;
    }

    public final Bitmap applyPreviewTransform(Bitmap src, int rotation, boolean mirrorH, boolean mirrorV) {
        Intrinsics.checkNotNullParameter(src, "src");
        Bitmap bmp = src;
        if (mirrorH) {
            bmp = mirrorBitmapH(bmp);
        }
        if (mirrorV) {
            bmp = mirrorBitmapV(bmp);
        }
        return rotation != 0 ? rotateBitmap(bmp, rotation) : bmp;
    }

    private final Bitmap rotateBitmap(Bitmap src, float degrees) {
        if (degrees == 0.0f) {
            return src;
        }
        Matrix m = new Matrix();
        m.postRotate(degrees);
        Bitmap result = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
        Intrinsics.checkNotNullExpressionValue(result, "createBitmap(...)");
        if (!Intrinsics.areEqual(result, src)) {
            src.recycle();
        }
        return result;
    }

    private final Bitmap mirrorBitmapH(Bitmap src) {
        Matrix m = new Matrix();
        m.postScale(-1.0f, 1.0f, src.getWidth() / 2.0f, src.getHeight() / 2.0f);
        Bitmap result = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
        Intrinsics.checkNotNullExpressionValue(result, "createBitmap(...)");
        if (!Intrinsics.areEqual(result, src)) {
            src.recycle();
        }
        return result;
    }

    private final Bitmap mirrorBitmapV(Bitmap src) {
        Matrix m = new Matrix();
        m.postScale(1.0f, -1.0f, src.getWidth() / 2.0f, src.getHeight() / 2.0f);
        Bitmap result = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
        Intrinsics.checkNotNullExpressionValue(result, "createBitmap(...)");
        if (!Intrinsics.areEqual(result, src)) {
            src.recycle();
        }
        return result;
    }

    private final Bitmap scaleToCanvas(Bitmap src, int targetW, int targetH, String mode) {
        Bitmap canvas = Bitmap.createBitmap(targetW, targetH, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(canvas, "createBitmap(...)");
        Canvas c = new Canvas(canvas);
        c.drawColor(ViewCompat.MEASURED_STATE_MASK);
        float scaleX = targetW / src.getWidth();
        float scaleY = targetH / src.getHeight();
        float scale = Intrinsics.areEqual(mode, "crop") ? Math.max(scaleX, scaleY) : Math.min(scaleX, scaleY);
        Matrix m = new Matrix();
        float dx = (targetW - (src.getWidth() * scale)) / 2.0f;
        float dy = (targetH - (src.getHeight() * scale)) / 2.0f;
        m.postScale(scale, scale);
        m.postTranslate(dx, dy);
        c.drawBitmap(src, m, null);
        src.recycle();
        return canvas;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019c  */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.media.MediaCodec] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void encodeToMp4(Bitmap bitmap, File outputFile, int encW, int encH, int sensorOrientation) {
        MediaCodec mediaCodec;
        MediaMuxer mediaMuxer;
        int[] iArr;
        MediaCodec mediaCodec2;
        int i = (encW + 1) & 2147483646;
        ?? r1 = 2147483646 & (encH + 1);
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, r1 == true ? 1 : 0);
        createVideoFormat.setInteger("bitrate", BITRATE);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 1);
        createVideoFormat.setInteger("color-format", 2135033992);
        Intrinsics.checkNotNullExpressionValue(createVideoFormat, "apply(...)");
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType("video/avc");
        Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(...)");
        MediaCodec mediaCodec3 = createEncoderByType;
        mediaCodec3.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        mediaCodec3.start();
        MediaMuxer mediaMuxer2 = new MediaMuxer(outputFile.getAbsolutePath(), 0);
        boolean z = false;
        int[] iArr2 = new int[i * (r1 == true ? 1 : 0)];
        Bitmap createScaledBitmap = (bitmap.getWidth() == i && bitmap.getHeight() == r1) ? bitmap : Bitmap.createScaledBitmap(bitmap, i, r1 == true ? 1 : 0, true);
        Intrinsics.checkNotNull(createScaledBitmap);
        int[] iArr3 = iArr2;
        createScaledBitmap.getPixels(iArr2, 0, i, 0, 0, i, r1 == true ? 1 : 0);
        byte[] packArgbToNV12 = packArgbToNV12(iArr3, i, r1 == true ? 1 : 0);
        boolean z2 = false;
        int i2 = 0;
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int i3 = -1;
            boolean z3 = false;
            while (true) {
                boolean z4 = r1;
                if (z2) {
                    mediaMuxer = mediaMuxer2;
                    iArr = iArr3;
                    mediaCodec2 = mediaCodec3;
                } else {
                    try {
                        int dequeueInputBuffer = mediaCodec3.dequeueInputBuffer(10000L);
                        if (dequeueInputBuffer >= 0) {
                            ByteBuffer inputBuffer = mediaCodec3.getInputBuffer(dequeueInputBuffer);
                            Intrinsics.checkNotNull(inputBuffer);
                            if (i2 >= 90) {
                                mediaMuxer = mediaMuxer2;
                                iArr = iArr3;
                                mediaCodec2 = mediaCodec3;
                                try {
                                    mediaCodec3.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                                    z2 = true;
                                } catch (Throwable th) {
                                    th = th;
                                    z = z3;
                                    mediaMuxer2 = mediaMuxer;
                                    mediaCodec = mediaCodec2;
                                    mediaCodec.stop();
                                    mediaCodec.release();
                                    if (z) {
                                        mediaMuxer2.stop();
                                        mediaMuxer2.release();
                                    }
                                    throw th;
                                }
                            } else {
                                mediaMuxer = mediaMuxer2;
                                iArr = iArr3;
                                mediaCodec2 = mediaCodec3;
                                inputBuffer.clear();
                                inputBuffer.put(packArgbToNV12);
                                mediaCodec2.queueInputBuffer(dequeueInputBuffer, 0, packArgbToNV12.length, i2 * 33333, 0);
                                i2++;
                            }
                        } else {
                            mediaMuxer = mediaMuxer2;
                            iArr = iArr3;
                            mediaCodec2 = mediaCodec3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        z = z3;
                        mediaCodec = mediaCodec3;
                    }
                }
                r1 = mediaCodec2;
                try {
                    int dequeueOutputBuffer = r1.dequeueOutputBuffer(bufferInfo, 10000L);
                    if (dequeueOutputBuffer == -2) {
                        mediaMuxer2 = mediaMuxer;
                        try {
                            i3 = mediaMuxer2.addTrack(r1.getOutputFormat());
                            mediaMuxer2.start();
                            z3 = true;
                            mediaCodec3 = r1;
                            r1 = z4;
                            iArr3 = iArr;
                        } catch (Throwable th3) {
                            th = th3;
                            z = z3;
                            mediaCodec = r1;
                            mediaCodec.stop();
                            mediaCodec.release();
                            if (z) {
                            }
                            throw th;
                        }
                    } else {
                        mediaMuxer2 = mediaMuxer;
                        if (dequeueOutputBuffer >= 0) {
                            ByteBuffer outputBuffer = r1.getOutputBuffer(dequeueOutputBuffer);
                            Intrinsics.checkNotNull(outputBuffer);
                            if (bufferInfo.size > 0 && z3) {
                                outputBuffer.position(bufferInfo.offset);
                                outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                                mediaMuxer2.writeSampleData(i3, outputBuffer, bufferInfo);
                            }
                            r1.releaseOutputBuffer(dequeueOutputBuffer, false);
                            if ((bufferInfo.flags & 4) != 0) {
                                break;
                            }
                        }
                        mediaCodec3 = r1;
                        r1 = z4;
                        iArr3 = iArr;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    mediaMuxer2 = mediaMuxer;
                    z = z3;
                    mediaCodec = r1;
                }
            }
            r1.stop();
            r1.release();
            if (z3) {
                mediaMuxer2.stop();
                mediaMuxer2.release();
            }
        } catch (Throwable th5) {
            th = th5;
            mediaCodec = mediaCodec3;
        }
    }

    private final byte[] packArgbToNV12(int[] argb, int w, int h) {
        boolean z;
        int i;
        int i2 = w;
        int ySize = i2 * h;
        byte[] out = new byte[(ySize / 2) + ySize];
        int row = 0;
        while (true) {
            z = false;
            i = 255;
            if (row >= h) {
                break;
            }
            int rowBase = row * i2;
            for (int col = 0; col < i2; col++) {
                int p = argb[rowBase + col];
                int y = (((((((p >> 16) & 255) * 66) + (((p >> 8) & 255) * 129)) + ((p & 255) * 25)) + 128) >> 8) + 16;
                out[rowBase + col] = (byte) RangesKt.coerceIn(y, 0, 255);
            }
            row++;
        }
        int cw = i2 / 2;
        int ch = h / 2;
        int row2 = 0;
        while (row2 < ch) {
            int srcRow = row2 * 2 * i2;
            int uvOff = (row2 * i2) + ySize;
            int col2 = 0;
            while (col2 < cw) {
                int p2 = argb[(col2 * 2) + srcRow];
                int r = (p2 >> 16) & i;
                int g = (p2 >> 8) & i;
                int b = p2 & 255;
                int u = (((((r * (-38)) - (g * 74)) + (b * 112)) + 128) >> 8) + 128;
                int v = (((((r * 112) - (g * 94)) - (b * 18)) + 128) >> 8) + 128;
                out[uvOff + (col2 * 2)] = (byte) RangesKt.coerceIn(u, 0, 255);
                int cw2 = cw;
                int cw3 = RangesKt.coerceIn(v, 0, 255);
                out[(col2 * 2) + uvOff + 1] = (byte) cw3;
                col2++;
                z = false;
                i = 255;
                cw = cw2;
                ySize = ySize;
            }
            row2++;
            i2 = w;
        }
        return out;
    }
}

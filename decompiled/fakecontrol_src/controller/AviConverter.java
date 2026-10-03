package com.fakehal.controller;

import android.util.Log;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AviConverter.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004J\u0010\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/fakehal/controller/AviConverter;", "", "()V", "TAG", "", "VPS_WORK_DIR", "isAviFile", "", "path", "isAviMime", "mime", "logAviInstructions", "", "srcUri", "vpsConvertCommand", "vpsInput", "vpsOutput", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AviConverter {
    public static final int $stable = 0;
    public static final AviConverter INSTANCE = new AviConverter();
    private static final String TAG = "AviConverter";
    private static final String VPS_WORK_DIR = "/tmp/fakehal_avi_work";

    private AviConverter() {
    }

    public final boolean isAviFile(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        String lowerCase = path.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.endsWith$default(lowerCase, ".avi", false, 2, (Object) null);
    }

    public final boolean isAviMime(String mime) {
        return mime != null && (Intrinsics.areEqual(mime, "video/avi") || Intrinsics.areEqual(mime, "video/x-msvideo") || Intrinsics.areEqual(mime, "video/vnd.avi") || Intrinsics.areEqual(mime, "video/msvideo"));
    }

    public final String vpsConvertCommand(String vpsInput, String vpsOutput) {
        Intrinsics.checkNotNullParameter(vpsInput, "vpsInput");
        Intrinsics.checkNotNullParameter(vpsOutput, "vpsOutput");
        return "ffmpeg -i  -c:v libx264 -preset fast -crf 23 -vf scale=1920:1080:force_original_aspect_ratio=decrease,pad=1920:1080:(ow-iw)/2:(oh-ih)/2 -c:a aac -b:a 128k -y ";
    }

    public final void logAviInstructions(String srcUri) {
        Intrinsics.checkNotNullParameter(srcUri, "srcUri");
        Log.w(TAG, "AVI file detected: " + srcUri);
        Log.w(TAG, "AVI is NOT supported by Android MediaExtractor.");
        Log.w(TAG, "To convert AVI: agent should run ffmpeg on VPS (144.31.148.224):");
        Log.w(TAG, "  1. adb -s localhost:5557 pull <device_avi_path> /tmp/input.avi");
        Log.w(TAG, "  2. " + vpsConvertCommand("/tmp/input.avi", "/tmp/output.mp4"));
        Log.w(TAG, "  3. adb -s localhost:5557 push /tmp/output.mp4 /sdcard/fakehal_converted.mp4");
        Log.w(TAG, "  4. Call setBackVideo() with the converted file URI");
    }
}

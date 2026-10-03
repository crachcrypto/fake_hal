package com.fakehal.controller;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.fakehal.controller.ImageToVideoConverter;
import com.fakehal.controller.RootShell;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: FakeHalManager.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u001b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002hiB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u000fJ.\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001bJ.\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\"\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010#\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010 J\u0018\u0010$\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010%\u001a\u00020&H\u0082@¢\u0006\u0002\u0010 J\u0018\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\rH\u0002J\u000e\u0010*\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010 J\u000e\u0010+\u001a\u00020,H\u0086@¢\u0006\u0002\u0010 J\u0016\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J\u001e\u00102\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u00103J\u001e\u00104\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u00103J\u001e\u00105\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u00103J\u0016\u00106\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J\u0016\u00107\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J\u0016\u00108\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J\u0016\u00109\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J\u001e\u0010:\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u00103J\u0016\u0010;\u001a\u00020&2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J&\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010AJ\u000e\u0010B\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010 J\u000e\u0010C\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010 J\u000e\u0010D\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010 J&\u0010E\u001a\u00020\r2\u0006\u00101\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010HJ\u001e\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>H\u0086@¢\u0006\u0002\u0010LJ\u0016\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020OH\u0086@¢\u0006\u0002\u0010PJ\u0016\u0010Q\u001a\u00020\r2\u0006\u00101\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010/J.\u0010R\u001a\u00020\r2\u0006\u0010S\u001a\u00020O2\u0006\u0010T\u001a\u00020>2\u0006\u0010U\u001a\u00020>2\u0006\u0010V\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010WJæ\u0001\u0010X\u001a\u00020&2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010O2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010O2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u0004H\u0082@¢\u0006\u0002\u0010gR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/fakehal/controller/FakeHalManager;", "", "()V", "BACK_VIDEO", "", "BACK_VIDEO_A", "BACK_VIDEO_B", "BINARY_PATH", "CONF_PATH", "DEFAULT_VIDEO", "FRONT_VIDEO", "LOG_PATH", "activateBackSlot", "", "slot", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyWithTransform", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "camera", "config", "Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyMediaToTarget", "target", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compensateFrontPreviewMirror", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatus", "Lcom/fakehal/controller/FakeHalManager$Status;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isImageMime", "isKnownVideoExtension", "isProviderRunning", "isVideoMime", "mirrorActiveBackToFront", "", "parseConfigBool", "value", "default", "readActiveSlot", "readTransformState", "Lcom/fakehal/controller/FakeHalManager$TransformState;", "restartIfNeeded", "wasRunning", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setBackRotate180", "enabled", "setBackSlotA", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setBackSlotB", "setBackVideo", "setChromeFrontMirror", "setChromeFrontRotate180", "setFrontMirror", "setFrontRotate180", "setFrontVideo", "setGyroEnabled", "setLiveTransform", "rotation", "", "mirrorH", "mirrorV", "(IZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "stop", "swapBackSlot", "updateGeoSpoof", "lat", "lon", "(ZLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateIsoRange", "min", "max", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNoiseLevel", "level", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateQrMode", "updateSensorSettings", "noiseLevel", "isoMin", "isoMax", "qrMode", "(FIIZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeConfig", "backSlotA", "backSlotB", "activeSlot", "backRotate180", "frontRotate180", "frontMirror", "chromeFrontRotate180", "chromeFrontMirror", "gyroStrength", "previewRotation", "previewMirrorH", "previewMirrorV", "gyroEnabled", "streamCamera", "(Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Status", "TransformState", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FakeHalManager {
    public static final int $stable = 0;
    private static final String BACK_VIDEO = "/data/local/tmp/fake_video.mp4";
    private static final String BACK_VIDEO_A = "/data/local/tmp/fake_video.slot_a.mp4";
    private static final String BACK_VIDEO_B = "/data/local/tmp/fake_video.slot_b.mp4";
    private static final String BINARY_PATH = "/data/local/tmp/fake_camera_provider";
    private static final String CONF_PATH = "/data/local/tmp/fakehal.conf";
    private static final String DEFAULT_VIDEO = "/data/local/tmp/fake_video.mp4";
    private static final String FRONT_VIDEO = "/data/local/tmp/fake_video_front.mp4";
    public static final FakeHalManager INSTANCE = new FakeHalManager();
    private static final String LOG_PATH = "/data/local/tmp/fakehAL.log";

    private FakeHalManager() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0036, code lost:
    
        if (r0.equals("yes") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
    
        if (r0.equals(kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_OFF) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        if (r0.equals(kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_ON) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
    
        if (r0.equals("no") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005a, code lost:
    
        if (r0.equals("1") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
    
        if (r0.equals("0") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0024, code lost:
    
        if (r0.equals("false") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0068, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002d, code lost:
    
        if (r0.equals("true") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x005d, code lost:
    
        return true;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001a. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean parseConfigBool(String value, boolean r4) {
        String lowerCase = StringsKt.trim((CharSequence) value).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case 48:
                break;
            case 49:
                break;
            case 3521:
                break;
            case 3551:
                break;
            case 109935:
                break;
            case 119527:
                break;
            case 3569038:
                break;
            case 97196323:
                break;
            default:
                return r4;
        }
    }

    /* compiled from: FakeHalManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b3\b\u0087\b\u0018\u00002\u00020\u0001B¥\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007¢\u0006\u0002\u0010\u0016J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0007HÆ\u0003J\t\u00107\u001a\u00020\fHÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J©\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u0007HÆ\u0001J\u0013\u0010;\u001a\u00020\u00032\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0005HÖ\u0001J\t\u0010>\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010!R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!¨\u0006?"}, d2 = {"Lcom/fakehal/controller/FakeHalManager$Status;", "", "isRunning", "", "frameCount", "", "backVideo", "", "frontVideo", "hasRoot", "error", "noiseLevel", "", "isoMin", "isoMax", "backSlotA", "backSlotB", "activeSlot", "qrMode", "geoEnabled", "geoLat", "geoLon", "(ZILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;FIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;)V", "getActiveSlot", "()Ljava/lang/String;", "getBackSlotA", "getBackSlotB", "getBackVideo", "getError", "getFrameCount", "()I", "getFrontVideo", "getGeoEnabled", "()Z", "getGeoLat", "getGeoLon", "getHasRoot", "getIsoMax", "getIsoMin", "getNoiseLevel", "()F", "getQrMode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class Status {
        public static final int $stable = 0;
        private final String activeSlot;
        private final String backSlotA;
        private final String backSlotB;
        private final String backVideo;
        private final String error;
        private final int frameCount;
        private final String frontVideo;
        private final boolean geoEnabled;
        private final String geoLat;
        private final String geoLon;
        private final boolean hasRoot;
        private final boolean isRunning;
        private final int isoMax;
        private final int isoMin;
        private final float noiseLevel;
        private final boolean qrMode;

        public Status() {
            this(false, 0, null, null, false, null, 0.0f, 0, 0, null, null, null, false, false, null, null, 65535, null);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsRunning() {
            return this.isRunning;
        }

        /* renamed from: component10, reason: from getter */
        public final String getBackSlotA() {
            return this.backSlotA;
        }

        /* renamed from: component11, reason: from getter */
        public final String getBackSlotB() {
            return this.backSlotB;
        }

        /* renamed from: component12, reason: from getter */
        public final String getActiveSlot() {
            return this.activeSlot;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getQrMode() {
            return this.qrMode;
        }

        /* renamed from: component14, reason: from getter */
        public final boolean getGeoEnabled() {
            return this.geoEnabled;
        }

        /* renamed from: component15, reason: from getter */
        public final String getGeoLat() {
            return this.geoLat;
        }

        /* renamed from: component16, reason: from getter */
        public final String getGeoLon() {
            return this.geoLon;
        }

        /* renamed from: component2, reason: from getter */
        public final int getFrameCount() {
            return this.frameCount;
        }

        /* renamed from: component3, reason: from getter */
        public final String getBackVideo() {
            return this.backVideo;
        }

        /* renamed from: component4, reason: from getter */
        public final String getFrontVideo() {
            return this.frontVideo;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getHasRoot() {
            return this.hasRoot;
        }

        /* renamed from: component6, reason: from getter */
        public final String getError() {
            return this.error;
        }

        /* renamed from: component7, reason: from getter */
        public final float getNoiseLevel() {
            return this.noiseLevel;
        }

        /* renamed from: component8, reason: from getter */
        public final int getIsoMin() {
            return this.isoMin;
        }

        /* renamed from: component9, reason: from getter */
        public final int getIsoMax() {
            return this.isoMax;
        }

        public final Status copy(boolean isRunning, int frameCount, String backVideo, String frontVideo, boolean hasRoot, String error, float noiseLevel, int isoMin, int isoMax, String backSlotA, String backSlotB, String activeSlot, boolean qrMode, boolean geoEnabled, String geoLat, String geoLon) {
            Intrinsics.checkNotNullParameter(backVideo, "backVideo");
            Intrinsics.checkNotNullParameter(frontVideo, "frontVideo");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(backSlotA, "backSlotA");
            Intrinsics.checkNotNullParameter(backSlotB, "backSlotB");
            Intrinsics.checkNotNullParameter(activeSlot, "activeSlot");
            Intrinsics.checkNotNullParameter(geoLat, "geoLat");
            Intrinsics.checkNotNullParameter(geoLon, "geoLon");
            return new Status(isRunning, frameCount, backVideo, frontVideo, hasRoot, error, noiseLevel, isoMin, isoMax, backSlotA, backSlotB, activeSlot, qrMode, geoEnabled, geoLat, geoLon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Status)) {
                return false;
            }
            Status status = (Status) other;
            return this.isRunning == status.isRunning && this.frameCount == status.frameCount && Intrinsics.areEqual(this.backVideo, status.backVideo) && Intrinsics.areEqual(this.frontVideo, status.frontVideo) && this.hasRoot == status.hasRoot && Intrinsics.areEqual(this.error, status.error) && Float.compare(this.noiseLevel, status.noiseLevel) == 0 && this.isoMin == status.isoMin && this.isoMax == status.isoMax && Intrinsics.areEqual(this.backSlotA, status.backSlotA) && Intrinsics.areEqual(this.backSlotB, status.backSlotB) && Intrinsics.areEqual(this.activeSlot, status.activeSlot) && this.qrMode == status.qrMode && this.geoEnabled == status.geoEnabled && Intrinsics.areEqual(this.geoLat, status.geoLat) && Intrinsics.areEqual(this.geoLon, status.geoLon);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((Boolean.hashCode(this.isRunning) * 31) + Integer.hashCode(this.frameCount)) * 31) + this.backVideo.hashCode()) * 31) + this.frontVideo.hashCode()) * 31) + Boolean.hashCode(this.hasRoot)) * 31) + this.error.hashCode()) * 31) + Float.hashCode(this.noiseLevel)) * 31) + Integer.hashCode(this.isoMin)) * 31) + Integer.hashCode(this.isoMax)) * 31) + this.backSlotA.hashCode()) * 31) + this.backSlotB.hashCode()) * 31) + this.activeSlot.hashCode()) * 31) + Boolean.hashCode(this.qrMode)) * 31) + Boolean.hashCode(this.geoEnabled)) * 31) + this.geoLat.hashCode()) * 31) + this.geoLon.hashCode();
        }

        public String toString() {
            return "Status(isRunning=" + this.isRunning + ", frameCount=" + this.frameCount + ", backVideo=" + this.backVideo + ", frontVideo=" + this.frontVideo + ", hasRoot=" + this.hasRoot + ", error=" + this.error + ", noiseLevel=" + this.noiseLevel + ", isoMin=" + this.isoMin + ", isoMax=" + this.isoMax + ", backSlotA=" + this.backSlotA + ", backSlotB=" + this.backSlotB + ", activeSlot=" + this.activeSlot + ", qrMode=" + this.qrMode + ", geoEnabled=" + this.geoEnabled + ", geoLat=" + this.geoLat + ", geoLon=" + this.geoLon + ")";
        }

        public Status(boolean isRunning, int frameCount, String backVideo, String frontVideo, boolean hasRoot, String error, float noiseLevel, int isoMin, int isoMax, String backSlotA, String backSlotB, String activeSlot, boolean qrMode, boolean geoEnabled, String geoLat, String geoLon) {
            Intrinsics.checkNotNullParameter(backVideo, "backVideo");
            Intrinsics.checkNotNullParameter(frontVideo, "frontVideo");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(backSlotA, "backSlotA");
            Intrinsics.checkNotNullParameter(backSlotB, "backSlotB");
            Intrinsics.checkNotNullParameter(activeSlot, "activeSlot");
            Intrinsics.checkNotNullParameter(geoLat, "geoLat");
            Intrinsics.checkNotNullParameter(geoLon, "geoLon");
            this.isRunning = isRunning;
            this.frameCount = frameCount;
            this.backVideo = backVideo;
            this.frontVideo = frontVideo;
            this.hasRoot = hasRoot;
            this.error = error;
            this.noiseLevel = noiseLevel;
            this.isoMin = isoMin;
            this.isoMax = isoMax;
            this.backSlotA = backSlotA;
            this.backSlotB = backSlotB;
            this.activeSlot = activeSlot;
            this.qrMode = qrMode;
            this.geoEnabled = geoEnabled;
            this.geoLat = geoLat;
            this.geoLon = geoLon;
        }

        public /* synthetic */ Status(boolean z, int i, String str, String str2, boolean z2, String str3, float f, int i2, int i3, String str4, String str5, String str6, boolean z3, boolean z4, String str7, String str8, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? false : z, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? "" : str, (i4 & 8) != 0 ? "" : str2, (i4 & 16) != 0 ? false : z2, (i4 & 32) != 0 ? "" : str3, (i4 & 64) != 0 ? 1.0f : f, (i4 & 128) != 0 ? 100 : i2, (i4 & 256) != 0 ? 800 : i3, (i4 & 512) != 0 ? "" : str4, (i4 & 1024) != 0 ? "" : str5, (i4 & 2048) != 0 ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : str6, (i4 & 4096) != 0 ? false : z3, (i4 & 8192) != 0 ? false : z4, (i4 & 16384) != 0 ? "" : str7, (i4 & 32768) != 0 ? "" : str8);
        }

        public final boolean isRunning() {
            return this.isRunning;
        }

        public final int getFrameCount() {
            return this.frameCount;
        }

        public final String getBackVideo() {
            return this.backVideo;
        }

        public final String getFrontVideo() {
            return this.frontVideo;
        }

        public final boolean getHasRoot() {
            return this.hasRoot;
        }

        public final String getError() {
            return this.error;
        }

        public final float getNoiseLevel() {
            return this.noiseLevel;
        }

        public final int getIsoMin() {
            return this.isoMin;
        }

        public final int getIsoMax() {
            return this.isoMax;
        }

        public final String getBackSlotA() {
            return this.backSlotA;
        }

        public final String getBackSlotB() {
            return this.backSlotB;
        }

        public final String getActiveSlot() {
            return this.activeSlot;
        }

        public final boolean getQrMode() {
            return this.qrMode;
        }

        public final boolean getGeoEnabled() {
            return this.geoEnabled;
        }

        public final String getGeoLat() {
            return this.geoLat;
        }

        public final String getGeoLon() {
            return this.geoLon;
        }
    }

    /* compiled from: FakeHalManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\tHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/fakehal/controller/FakeHalManager$TransformState;", "", "backRotate180", "", "frontRotate180", "frontMirror", "chromeFrontRotate180", "chromeFrontMirror", "previewRotation", "", "(ZZZZZI)V", "getBackRotate180", "()Z", "getChromeFrontMirror", "getChromeFrontRotate180", "getFrontMirror", "getFrontRotate180", "getPreviewRotation", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TransformState {
        public static final int $stable = 0;
        private final boolean backRotate180;
        private final boolean chromeFrontMirror;
        private final boolean chromeFrontRotate180;
        private final boolean frontMirror;
        private final boolean frontRotate180;
        private final int previewRotation;

        public static /* synthetic */ TransformState copy$default(TransformState transformState, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = transformState.backRotate180;
            }
            if ((i2 & 2) != 0) {
                z2 = transformState.frontRotate180;
            }
            boolean z6 = z2;
            if ((i2 & 4) != 0) {
                z3 = transformState.frontMirror;
            }
            boolean z7 = z3;
            if ((i2 & 8) != 0) {
                z4 = transformState.chromeFrontRotate180;
            }
            boolean z8 = z4;
            if ((i2 & 16) != 0) {
                z5 = transformState.chromeFrontMirror;
            }
            boolean z9 = z5;
            if ((i2 & 32) != 0) {
                i = transformState.previewRotation;
            }
            return transformState.copy(z, z6, z7, z8, z9, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getBackRotate180() {
            return this.backRotate180;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getFrontRotate180() {
            return this.frontRotate180;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getFrontMirror() {
            return this.frontMirror;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getChromeFrontRotate180() {
            return this.chromeFrontRotate180;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getChromeFrontMirror() {
            return this.chromeFrontMirror;
        }

        /* renamed from: component6, reason: from getter */
        public final int getPreviewRotation() {
            return this.previewRotation;
        }

        public final TransformState copy(boolean backRotate180, boolean frontRotate180, boolean frontMirror, boolean chromeFrontRotate180, boolean chromeFrontMirror, int previewRotation) {
            return new TransformState(backRotate180, frontRotate180, frontMirror, chromeFrontRotate180, chromeFrontMirror, previewRotation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransformState)) {
                return false;
            }
            TransformState transformState = (TransformState) other;
            return this.backRotate180 == transformState.backRotate180 && this.frontRotate180 == transformState.frontRotate180 && this.frontMirror == transformState.frontMirror && this.chromeFrontRotate180 == transformState.chromeFrontRotate180 && this.chromeFrontMirror == transformState.chromeFrontMirror && this.previewRotation == transformState.previewRotation;
        }

        public int hashCode() {
            return (((((((((Boolean.hashCode(this.backRotate180) * 31) + Boolean.hashCode(this.frontRotate180)) * 31) + Boolean.hashCode(this.frontMirror)) * 31) + Boolean.hashCode(this.chromeFrontRotate180)) * 31) + Boolean.hashCode(this.chromeFrontMirror)) * 31) + Integer.hashCode(this.previewRotation);
        }

        public String toString() {
            return "TransformState(backRotate180=" + this.backRotate180 + ", frontRotate180=" + this.frontRotate180 + ", frontMirror=" + this.frontMirror + ", chromeFrontRotate180=" + this.chromeFrontRotate180 + ", chromeFrontMirror=" + this.chromeFrontMirror + ", previewRotation=" + this.previewRotation + ")";
        }

        public TransformState(boolean backRotate180, boolean frontRotate180, boolean frontMirror, boolean chromeFrontRotate180, boolean chromeFrontMirror, int previewRotation) {
            this.backRotate180 = backRotate180;
            this.frontRotate180 = frontRotate180;
            this.frontMirror = frontMirror;
            this.chromeFrontRotate180 = chromeFrontRotate180;
            this.chromeFrontMirror = chromeFrontMirror;
            this.previewRotation = previewRotation;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public /* synthetic */ TransformState(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, z2, z3, z4, z5, r6);
            int i3;
            if ((i2 & 32) == 0) {
                i3 = i;
            } else {
                i3 = 0;
            }
        }

        public final boolean getBackRotate180() {
            return this.backRotate180;
        }

        public final boolean getFrontRotate180() {
            return this.frontRotate180;
        }

        public final boolean getFrontMirror() {
            return this.frontMirror;
        }

        public final boolean getChromeFrontRotate180() {
            return this.chromeFrontRotate180;
        }

        public final boolean getChromeFrontMirror() {
            return this.chromeFrontMirror;
        }

        public final int getPreviewRotation() {
            return this.previewRotation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object isProviderRunning(Continuation<? super Boolean> continuation) {
        FakeHalManager$isProviderRunning$1 fakeHalManager$isProviderRunning$1;
        FakeHalManager$isProviderRunning$1 fakeHalManager$isProviderRunning$12;
        Object exec;
        if (continuation instanceof FakeHalManager$isProviderRunning$1) {
            fakeHalManager$isProviderRunning$1 = (FakeHalManager$isProviderRunning$1) continuation;
            if ((fakeHalManager$isProviderRunning$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$isProviderRunning$1.label -= Integer.MIN_VALUE;
                fakeHalManager$isProviderRunning$12 = fakeHalManager$isProviderRunning$1;
                Object $result = fakeHalManager$isProviderRunning$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$isProviderRunning$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$isProviderRunning$12.label = 1;
                        exec = rootShell.exec("pidof fake_camera_provider fake_camera_provider.new 2>/dev/null", fakeHalManager$isProviderRunning$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxBoolean(!StringsKt.isBlank(((RootShell.Result) exec).getOutput()));
            }
        }
        fakeHalManager$isProviderRunning$1 = new FakeHalManager$isProviderRunning$1(this, continuation);
        fakeHalManager$isProviderRunning$12 = fakeHalManager$isProviderRunning$1;
        Object $result2 = fakeHalManager$isProviderRunning$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$isProviderRunning$12.label) {
        }
        return Boxing.boxBoolean(!StringsKt.isBlank(((RootShell.Result) exec).getOutput()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object restartIfNeeded(boolean wasRunning, Continuation<? super Boolean> continuation) {
        return wasRunning ? start(continuation) : Boxing.boxBoolean(true);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002d. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0332 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0454 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x03ec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARN: Type inference failed for: r4v35, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getStatus(Continuation<? super Status> continuation) {
        FakeHalManager$getStatus$1 fakeHalManager$getStatus$1;
        FakeHalManager fakeHalManager;
        Object isRootAvailable;
        Object isProviderRunning;
        boolean booleanValue;
        Object exec;
        Object exec2;
        boolean z;
        int i;
        String str;
        int i2;
        int i3;
        String str2;
        String str3;
        CharSequence charSequence;
        String str4;
        String str5;
        Object obj;
        int i4;
        String str6;
        String str7;
        int i5;
        float f;
        int i6;
        Object obj2;
        Object obj3;
        boolean z2;
        String str8;
        String str9;
        int i7;
        int i8;
        String str10;
        float f2;
        String str11;
        String str12;
        int i9;
        boolean z3;
        Object obj4;
        int i10;
        String str13;
        float f3;
        int i11;
        String str14;
        String str15;
        String str16;
        String str17;
        int i12;
        int i13;
        String str18;
        int i14;
        boolean z4;
        boolean contains$default;
        String str19;
        String str20;
        if (continuation instanceof FakeHalManager$getStatus$1) {
            FakeHalManager$getStatus$1 fakeHalManager$getStatus$12 = (FakeHalManager$getStatus$1) continuation;
            if ((fakeHalManager$getStatus$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$getStatus$12.label -= Integer.MIN_VALUE;
                fakeHalManager$getStatus$1 = fakeHalManager$getStatus$12;
                Object obj5 = fakeHalManager$getStatus$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$getStatus$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj5);
                        fakeHalManager = this;
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = fakeHalManager;
                        fakeHalManager$getStatus$1.label = 1;
                        isRootAvailable = rootShell.isRootAvailable(fakeHalManager$getStatus$1);
                        if (isRootAvailable == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) isRootAvailable).booleanValue()) {
                            return new Status(false, 0, null, null, false, "Root access denied", 0.0f, 0, 0, null, null, null, false, false, null, null, 65503, null);
                        }
                        fakeHalManager$getStatus$1.L$0 = null;
                        fakeHalManager$getStatus$1.label = 2;
                        isProviderRunning = fakeHalManager.isProviderRunning(fakeHalManager$getStatus$1);
                        if (isProviderRunning == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) isProviderRunning).booleanValue();
                        RootShell rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.Z$0 = booleanValue;
                        fakeHalManager$getStatus$1.label = 3;
                        exec = rootShell2.exec("grep -c 'frame #' /data/local/tmp/fakehAL.log 2>/dev/null || echo 0", fakeHalManager$getStatus$1);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) ((RootShell.Result) exec).getOutput()).toString());
                        int intValue = intOrNull != null ? intOrNull.intValue() : 0;
                        RootShell rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.Z$0 = booleanValue;
                        fakeHalManager$getStatus$1.I$0 = intValue;
                        fakeHalManager$getStatus$1.label = 4;
                        exec2 = rootShell3.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$getStatus$1);
                        if (exec2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        z = booleanValue;
                        i = intValue;
                        RootShell.Result result = (RootShell.Result) exec2;
                        str = "/data/local/tmp/fake_video.mp4";
                        i2 = 100;
                        i3 = 800;
                        str2 = "";
                        int i15 = 0;
                        str3 = "/data/local/tmp/fake_video.mp4";
                        String str21 = "";
                        float f4 = 1.0f;
                        ?? r9 = 0;
                        charSequence = "YES";
                        str4 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        for (String str22 : StringsKt.lines(result.getOutput())) {
                            int i16 = i15;
                            boolean z5 = r9;
                            int i17 = i2;
                            int i18 = i3;
                            float f5 = f4;
                            if (StringsKt.startsWith$default(str22, "back_video=", false, 2, (Object) null)) {
                                str3 = StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str22, "front_video=", false, 2, (Object) null)) {
                                str = StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str22, "noise_level=", false, 2, (Object) null)) {
                                Float floatOrNull = StringsKt.toFloatOrNull(StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null));
                                f4 = floatOrNull != null ? floatOrNull.floatValue() : 1.0f;
                            } else {
                                f4 = f5;
                            }
                            String str23 = str3;
                            if (StringsKt.startsWith$default(str22, "iso_min=", false, 2, (Object) null)) {
                                Integer intOrNull2 = StringsKt.toIntOrNull(StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null));
                                i6 = intOrNull2 != null ? intOrNull2.intValue() : 100;
                            } else {
                                i6 = i17;
                            }
                            String str24 = str;
                            if (StringsKt.startsWith$default(str22, "iso_max=", false, 2, (Object) null)) {
                                Integer intOrNull3 = StringsKt.toIntOrNull(StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null));
                                i3 = intOrNull3 != null ? intOrNull3.intValue() : 800;
                            } else {
                                i3 = i18;
                            }
                            int i19 = i6;
                            if (StringsKt.startsWith$default(str22, "back_slot_a=", false, 2, (Object) null)) {
                                str2 = StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str22, "back_slot_b=", false, 2, (Object) null)) {
                                str21 = StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str22, "active_slot=", false, 2, (Object) null)) {
                                str4 = StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str22, "qr_mode=", false, 2, (Object) null)) {
                                r9 = Intrinsics.areEqual(StringsKt.substringAfter$default(str22, "=", (String) null, 2, (Object) null), "1");
                                i15 = i16;
                                i2 = i19;
                                str3 = str23;
                                str = str24;
                            } else {
                                r9 = z5;
                                i15 = i16;
                                i2 = i19;
                                str3 = str23;
                                str = str24;
                            }
                        }
                        int i20 = i15;
                        RootShell rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str3;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str21;
                        fakeHalManager$getStatus$1.L$4 = str4;
                        fakeHalManager$getStatus$1.L$5 = "";
                        fakeHalManager$getStatus$1.L$6 = "";
                        fakeHalManager$getStatus$1.Z$0 = z;
                        fakeHalManager$getStatus$1.I$0 = i;
                        fakeHalManager$getStatus$1.F$0 = f4;
                        fakeHalManager$getStatus$1.I$1 = i2;
                        fakeHalManager$getStatus$1.I$2 = i3;
                        fakeHalManager$getStatus$1.I$3 = r9;
                        str5 = "";
                        fakeHalManager$getStatus$1.I$4 = i20;
                        fakeHalManager$getStatus$1.label = 5;
                        obj5 = rootShell4.exec("cat /data/local/tmp/spoofkit/active_config 2>/dev/null", fakeHalManager$getStatus$1);
                        obj = coroutine_suspended;
                        if (obj5 != obj) {
                            return obj;
                        }
                        i4 = i20;
                        float f6 = f4;
                        str6 = str21;
                        str7 = "";
                        i5 = r9;
                        f = f6;
                        int i21 = i4;
                        obj2 = obj;
                        String str25 = str5;
                        for (String str26 : StringsKt.lines(((RootShell.Result) obj5).getOutput())) {
                            int i22 = i21;
                            int i23 = i5;
                            float f7 = f;
                            int i24 = i2;
                            int i25 = i3;
                            int areEqual = StringsKt.startsWith$default(str26, "gps_enabled=", false, 2, (Object) null) ? Intrinsics.areEqual(StringsKt.substringAfter$default(str26, "=", (String) null, 2, (Object) null), "true") : i22;
                            if (StringsKt.startsWith$default(str26, "gps_lat=", false, 2, (Object) null)) {
                                str25 = StringsKt.substringAfter$default(str26, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(str26, "gps_lon=", false, 2, (Object) null)) {
                                str7 = StringsKt.substringAfter$default(str26, "=", (String) null, 2, (Object) null);
                                i5 = i23;
                                i21 = areEqual;
                                i3 = i25;
                                i2 = i24;
                                f = f7;
                            } else {
                                i5 = i23;
                                i21 = areEqual;
                                i3 = i25;
                                i2 = i24;
                                f = f7;
                            }
                        }
                        int i26 = i21;
                        RootShell rootShell5 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str3;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str6;
                        fakeHalManager$getStatus$1.L$4 = str4;
                        fakeHalManager$getStatus$1.L$5 = str25;
                        fakeHalManager$getStatus$1.L$6 = str7;
                        fakeHalManager$getStatus$1.Z$0 = z;
                        fakeHalManager$getStatus$1.I$0 = i;
                        fakeHalManager$getStatus$1.F$0 = f;
                        fakeHalManager$getStatus$1.I$1 = i2;
                        fakeHalManager$getStatus$1.I$2 = i3;
                        fakeHalManager$getStatus$1.I$3 = i5;
                        fakeHalManager$getStatus$1.I$4 = i26;
                        String str27 = str3;
                        fakeHalManager$getStatus$1.label = 6;
                        obj5 = rootShell5.exec("[ -f '/data/local/tmp/fake_video.slot_a.mp4' ] && echo YES", fakeHalManager$getStatus$1);
                        if (obj5 != obj2) {
                            return obj2;
                        }
                        obj3 = obj2;
                        z2 = z;
                        str8 = str7;
                        str9 = str27;
                        i7 = i5;
                        i8 = i26;
                        int i27 = i;
                        str10 = str4;
                        f2 = f;
                        str11 = str25;
                        str12 = str6;
                        i9 = i27;
                        int i28 = i8;
                        int i29 = i7;
                        int i30 = i2;
                        int i31 = i3;
                        boolean contains$default2 = StringsKt.contains$default((CharSequence) ((RootShell.Result) obj5).getOutput(), charSequence, false, 2, (Object) null);
                        RootShell rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str9;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str12;
                        fakeHalManager$getStatus$1.L$4 = str10;
                        fakeHalManager$getStatus$1.L$5 = str11;
                        fakeHalManager$getStatus$1.L$6 = str8;
                        fakeHalManager$getStatus$1.Z$0 = z2;
                        fakeHalManager$getStatus$1.I$0 = i9;
                        fakeHalManager$getStatus$1.F$0 = f2;
                        fakeHalManager$getStatus$1.I$1 = i30;
                        fakeHalManager$getStatus$1.I$2 = i31;
                        fakeHalManager$getStatus$1.I$3 = i29;
                        z3 = z2;
                        fakeHalManager$getStatus$1.I$4 = i28;
                        fakeHalManager$getStatus$1.Z$1 = contains$default2;
                        fakeHalManager$getStatus$1.label = 7;
                        obj5 = rootShell6.exec("[ -f '/data/local/tmp/fake_video.slot_b.mp4' ] && echo YES", fakeHalManager$getStatus$1);
                        obj4 = obj3;
                        if (obj5 != obj4) {
                            return obj4;
                        }
                        i10 = i28;
                        str13 = str9;
                        f3 = f2;
                        i11 = i30;
                        str14 = str;
                        str15 = str11;
                        str16 = str10;
                        str17 = str8;
                        i12 = i29;
                        i13 = i31;
                        str18 = str2;
                        i14 = i9;
                        z4 = contains$default2;
                        contains$default = StringsKt.contains$default((CharSequence) ((RootShell.Result) obj5).getOutput(), charSequence, false, 2, (Object) null);
                        String str28 = null;
                        if (z4) {
                            str19 = "";
                        } else {
                            String str29 = str18;
                            if (StringsKt.isBlank(str29)) {
                                str29 = BACK_VIDEO_A;
                            }
                            str19 = str29;
                        }
                        if (contains$default) {
                            str20 = "";
                        } else {
                            String str30 = str12;
                            if (StringsKt.isBlank(str30)) {
                                str30 = BACK_VIDEO_B;
                            }
                            str20 = str30;
                        }
                        return new Status(z3, i14, str13, str14, true, str28, f3, i11, i13, str19, str20, str16, i12 == 0, i10 == 0, str15, str17, 32, null);
                    case 1:
                        fakeHalManager = (FakeHalManager) fakeHalManager$getStatus$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        isRootAvailable = obj5;
                        if (((Boolean) isRootAvailable).booleanValue()) {
                        }
                        break;
                    case 2:
                        ResultKt.throwOnFailure(obj5);
                        isProviderRunning = obj5;
                        booleanValue = ((Boolean) isProviderRunning).booleanValue();
                        RootShell rootShell22 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.Z$0 = booleanValue;
                        fakeHalManager$getStatus$1.label = 3;
                        exec = rootShell22.exec("grep -c 'frame #' /data/local/tmp/fakehAL.log 2>/dev/null || echo 0", fakeHalManager$getStatus$1);
                        if (exec == coroutine_suspended) {
                        }
                        Integer intOrNull4 = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) ((RootShell.Result) exec).getOutput()).toString());
                        if (intOrNull4 != null) {
                        }
                        RootShell rootShell32 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.Z$0 = booleanValue;
                        fakeHalManager$getStatus$1.I$0 = intValue;
                        fakeHalManager$getStatus$1.label = 4;
                        exec2 = rootShell32.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$getStatus$1);
                        if (exec2 == coroutine_suspended) {
                        }
                        break;
                    case 3:
                        booleanValue = fakeHalManager$getStatus$1.Z$0;
                        ResultKt.throwOnFailure(obj5);
                        exec = obj5;
                        Integer intOrNull42 = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) ((RootShell.Result) exec).getOutput()).toString());
                        if (intOrNull42 != null) {
                        }
                        RootShell rootShell322 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.Z$0 = booleanValue;
                        fakeHalManager$getStatus$1.I$0 = intValue;
                        fakeHalManager$getStatus$1.label = 4;
                        exec2 = rootShell322.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$getStatus$1);
                        if (exec2 == coroutine_suspended) {
                        }
                        break;
                    case 4:
                        int i32 = fakeHalManager$getStatus$1.I$0;
                        boolean z6 = fakeHalManager$getStatus$1.Z$0;
                        ResultKt.throwOnFailure(obj5);
                        exec2 = obj5;
                        i = i32;
                        z = z6;
                        RootShell.Result result2 = (RootShell.Result) exec2;
                        str = "/data/local/tmp/fake_video.mp4";
                        i2 = 100;
                        i3 = 800;
                        str2 = "";
                        int i152 = 0;
                        str3 = "/data/local/tmp/fake_video.mp4";
                        String str212 = "";
                        float f42 = 1.0f;
                        ?? r92 = 0;
                        charSequence = "YES";
                        str4 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        while (r21.hasNext()) {
                        }
                        int i202 = i152;
                        RootShell rootShell42 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str3;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str212;
                        fakeHalManager$getStatus$1.L$4 = str4;
                        fakeHalManager$getStatus$1.L$5 = "";
                        fakeHalManager$getStatus$1.L$6 = "";
                        fakeHalManager$getStatus$1.Z$0 = z;
                        fakeHalManager$getStatus$1.I$0 = i;
                        fakeHalManager$getStatus$1.F$0 = f42;
                        fakeHalManager$getStatus$1.I$1 = i2;
                        fakeHalManager$getStatus$1.I$2 = i3;
                        fakeHalManager$getStatus$1.I$3 = r92;
                        str5 = "";
                        fakeHalManager$getStatus$1.I$4 = i202;
                        fakeHalManager$getStatus$1.label = 5;
                        obj5 = rootShell42.exec("cat /data/local/tmp/spoofkit/active_config 2>/dev/null", fakeHalManager$getStatus$1);
                        obj = coroutine_suspended;
                        if (obj5 != obj) {
                        }
                        break;
                    case 5:
                        int i33 = fakeHalManager$getStatus$1.I$4;
                        i5 = fakeHalManager$getStatus$1.I$3;
                        int i34 = fakeHalManager$getStatus$1.I$2;
                        int i35 = fakeHalManager$getStatus$1.I$1;
                        f = fakeHalManager$getStatus$1.F$0;
                        i = fakeHalManager$getStatus$1.I$0;
                        z = fakeHalManager$getStatus$1.Z$0;
                        String str31 = (String) fakeHalManager$getStatus$1.L$6;
                        String str32 = (String) fakeHalManager$getStatus$1.L$5;
                        String str33 = (String) fakeHalManager$getStatus$1.L$4;
                        str6 = (String) fakeHalManager$getStatus$1.L$3;
                        i4 = i33;
                        String str34 = (String) fakeHalManager$getStatus$1.L$2;
                        String str35 = (String) fakeHalManager$getStatus$1.L$1;
                        str3 = (String) fakeHalManager$getStatus$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        charSequence = "YES";
                        str5 = str32;
                        str4 = str33;
                        str2 = str34;
                        i3 = i34;
                        obj = coroutine_suspended;
                        str7 = str31;
                        i2 = i35;
                        str = str35;
                        int i212 = i4;
                        obj2 = obj;
                        String str252 = str5;
                        while (r21.hasNext()) {
                        }
                        int i262 = i212;
                        RootShell rootShell52 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str3;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str6;
                        fakeHalManager$getStatus$1.L$4 = str4;
                        fakeHalManager$getStatus$1.L$5 = str252;
                        fakeHalManager$getStatus$1.L$6 = str7;
                        fakeHalManager$getStatus$1.Z$0 = z;
                        fakeHalManager$getStatus$1.I$0 = i;
                        fakeHalManager$getStatus$1.F$0 = f;
                        fakeHalManager$getStatus$1.I$1 = i2;
                        fakeHalManager$getStatus$1.I$2 = i3;
                        fakeHalManager$getStatus$1.I$3 = i5;
                        fakeHalManager$getStatus$1.I$4 = i262;
                        String str272 = str3;
                        fakeHalManager$getStatus$1.label = 6;
                        obj5 = rootShell52.exec("[ -f '/data/local/tmp/fake_video.slot_a.mp4' ] && echo YES", fakeHalManager$getStatus$1);
                        if (obj5 != obj2) {
                        }
                        break;
                    case 6:
                        int i36 = fakeHalManager$getStatus$1.I$4;
                        int i37 = fakeHalManager$getStatus$1.I$3;
                        int i38 = fakeHalManager$getStatus$1.I$2;
                        int i39 = fakeHalManager$getStatus$1.I$1;
                        float f8 = fakeHalManager$getStatus$1.F$0;
                        int i40 = fakeHalManager$getStatus$1.I$0;
                        boolean z7 = fakeHalManager$getStatus$1.Z$0;
                        str8 = (String) fakeHalManager$getStatus$1.L$6;
                        String str36 = (String) fakeHalManager$getStatus$1.L$5;
                        String str37 = (String) fakeHalManager$getStatus$1.L$4;
                        String str38 = (String) fakeHalManager$getStatus$1.L$3;
                        String str39 = (String) fakeHalManager$getStatus$1.L$2;
                        String str40 = (String) fakeHalManager$getStatus$1.L$1;
                        String str41 = (String) fakeHalManager$getStatus$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        charSequence = "YES";
                        f2 = f8;
                        str = str40;
                        str9 = str41;
                        z2 = z7;
                        str10 = str37;
                        i3 = i38;
                        str12 = str38;
                        str2 = str39;
                        i9 = i40;
                        str11 = str36;
                        i2 = i39;
                        i7 = i37;
                        i8 = i36;
                        obj3 = coroutine_suspended;
                        int i282 = i8;
                        int i292 = i7;
                        int i302 = i2;
                        int i312 = i3;
                        boolean contains$default22 = StringsKt.contains$default((CharSequence) ((RootShell.Result) obj5).getOutput(), charSequence, false, 2, (Object) null);
                        RootShell rootShell62 = RootShell.INSTANCE;
                        fakeHalManager$getStatus$1.L$0 = str9;
                        fakeHalManager$getStatus$1.L$1 = str;
                        fakeHalManager$getStatus$1.L$2 = str2;
                        fakeHalManager$getStatus$1.L$3 = str12;
                        fakeHalManager$getStatus$1.L$4 = str10;
                        fakeHalManager$getStatus$1.L$5 = str11;
                        fakeHalManager$getStatus$1.L$6 = str8;
                        fakeHalManager$getStatus$1.Z$0 = z2;
                        fakeHalManager$getStatus$1.I$0 = i9;
                        fakeHalManager$getStatus$1.F$0 = f2;
                        fakeHalManager$getStatus$1.I$1 = i302;
                        fakeHalManager$getStatus$1.I$2 = i312;
                        fakeHalManager$getStatus$1.I$3 = i292;
                        z3 = z2;
                        fakeHalManager$getStatus$1.I$4 = i282;
                        fakeHalManager$getStatus$1.Z$1 = contains$default22;
                        fakeHalManager$getStatus$1.label = 7;
                        obj5 = rootShell62.exec("[ -f '/data/local/tmp/fake_video.slot_b.mp4' ] && echo YES", fakeHalManager$getStatus$1);
                        obj4 = obj3;
                        if (obj5 != obj4) {
                        }
                        break;
                    case 7:
                        z4 = fakeHalManager$getStatus$1.Z$1;
                        i10 = fakeHalManager$getStatus$1.I$4;
                        i12 = fakeHalManager$getStatus$1.I$3;
                        int i41 = fakeHalManager$getStatus$1.I$2;
                        int i42 = fakeHalManager$getStatus$1.I$1;
                        float f9 = fakeHalManager$getStatus$1.F$0;
                        int i43 = fakeHalManager$getStatus$1.I$0;
                        boolean z8 = fakeHalManager$getStatus$1.Z$0;
                        String str42 = (String) fakeHalManager$getStatus$1.L$6;
                        String str43 = (String) fakeHalManager$getStatus$1.L$5;
                        String str44 = (String) fakeHalManager$getStatus$1.L$4;
                        str12 = (String) fakeHalManager$getStatus$1.L$3;
                        String str45 = (String) fakeHalManager$getStatus$1.L$2;
                        String str46 = (String) fakeHalManager$getStatus$1.L$1;
                        String str47 = (String) fakeHalManager$getStatus$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        str13 = str47;
                        charSequence = "YES";
                        str17 = str42;
                        str15 = str43;
                        str16 = str44;
                        i13 = i41;
                        i11 = i42;
                        f3 = f9;
                        i14 = i43;
                        z3 = z8;
                        str18 = str45;
                        str14 = str46;
                        contains$default = StringsKt.contains$default((CharSequence) ((RootShell.Result) obj5).getOutput(), charSequence, false, 2, (Object) null);
                        String str282 = null;
                        if (z4) {
                        }
                        if (contains$default) {
                        }
                        return new Status(z3, i14, str13, str14, true, str282, f3, i11, i13, str19, str20, str16, i12 == 0, i10 == 0, str15, str17, 32, null);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$getStatus$1 = new FakeHalManager$getStatus$1(this, continuation);
        Object obj52 = fakeHalManager$getStatus$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$getStatus$1.label) {
        }
    }

    private final boolean isImageMime(Context context, Uri uri) {
        String mime = context.getContentResolver().getType(uri);
        if (mime == null) {
            mime = "";
        }
        return StringsKt.startsWith$default(mime, "image/", false, 2, (Object) null);
    }

    private final boolean isVideoMime(Context context, Uri uri) {
        String mime = context.getContentResolver().getType(uri);
        if (mime == null) {
            mime = "";
        }
        return StringsKt.startsWith$default(mime, "video/", false, 2, (Object) null);
    }

    private final boolean isKnownVideoExtension(Context context, Uri uri) {
        String name = uri.getLastPathSegment();
        if (name == null) {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
            if (query != null) {
                Cursor cursor = query;
                try {
                    Cursor c = cursor;
                    String string = c.moveToFirst() ? c.getString(0) : null;
                    CloseableKt.closeFinally(cursor, null);
                    name = string;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor, th);
                        throw th2;
                    }
                }
            } else {
                name = null;
            }
            if (name == null) {
                name = "";
            }
        }
        String it = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(it, "toLowerCase(...)");
        return StringsKt.endsWith$default(it, ".mp4", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".avi", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".mkv", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".mov", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".3gp", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".webm", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".ts", false, 2, (Object) null) || StringsKt.endsWith$default(it, ".flv", false, 2, (Object) null);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0038. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003b  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.io.File] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object copyMediaToTarget(Context context, Uri uri, String str, Continuation<? super Boolean> continuation) {
        FakeHalManager$copyMediaToTarget$1 fakeHalManager$copyMediaToTarget$1;
        ?? r4;
        File file;
        Throwable th;
        Throwable th2;
        FakeHalManager$copyMediaToTarget$1 fakeHalManager$copyMediaToTarget$12;
        File resolve;
        Object exec;
        File resolve2;
        boolean z;
        String str2;
        String str3;
        String str4;
        Object convertLegacy$default;
        Object obj;
        Object exec2;
        File file2;
        File file3;
        try {
            if (continuation instanceof FakeHalManager$copyMediaToTarget$1) {
                fakeHalManager$copyMediaToTarget$1 = (FakeHalManager$copyMediaToTarget$1) continuation;
                if ((fakeHalManager$copyMediaToTarget$1.label & Integer.MIN_VALUE) != 0) {
                    fakeHalManager$copyMediaToTarget$1.label -= Integer.MIN_VALUE;
                    Object obj2 = fakeHalManager$copyMediaToTarget$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    r4 = fakeHalManager$copyMediaToTarget$1.label;
                    int i = 2;
                    switch (r4) {
                        case 0:
                            ResultKt.throwOnFailure(obj2);
                            boolean isImageMime = isImageMime(context, uri);
                            boolean z2 = !isImageMime && (isVideoMime(context, uri) || isKnownVideoExtension(context, uri));
                            if (isImageMime) {
                                File cacheDir = context.getCacheDir();
                                Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
                                resolve2 = FilesKt.resolve(cacheDir, "converted_image.mp4");
                                ImageToVideoConverter imageToVideoConverter = ImageToVideoConverter.INSTANCE;
                                fakeHalManager$copyMediaToTarget$1.L$0 = str;
                                fakeHalManager$copyMediaToTarget$1.L$1 = resolve2;
                                fakeHalManager$copyMediaToTarget$1.label = 1;
                                z = false;
                                str2 = "cp '";
                                str3 = str;
                                str4 = "' '";
                                convertLegacy$default = ImageToVideoConverter.convertLegacy$default(imageToVideoConverter, context, uri, resolve2, false, fakeHalManager$copyMediaToTarget$1, 8, null);
                                if (convertLegacy$default == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                if (((Boolean) convertLegacy$default).booleanValue()) {
                                    return Boxing.boxBoolean(z);
                                }
                                RootShell rootShell = RootShell.INSTANCE;
                                String str5 = str2 + resolve2.getAbsolutePath() + str4 + str3 + "' && chmod 644 '" + str3 + "' && (chcon u:object_r:shell_data_file:s0 '" + str3 + "' 2>/dev/null || true) && echo OK";
                                fakeHalManager$copyMediaToTarget$1.L$0 = resolve2;
                                obj = null;
                                fakeHalManager$copyMediaToTarget$1.L$1 = null;
                                i = 2;
                                fakeHalManager$copyMediaToTarget$1.label = 2;
                                exec2 = rootShell.exec(str5, fakeHalManager$copyMediaToTarget$1);
                                if (exec2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                file2 = resolve2;
                                file2.delete();
                                return Boxing.boxBoolean(StringsKt.contains$default(((RootShell.Result) exec2).getOutput(), "OK", z, i, obj));
                            }
                            if (!z2) {
                                return Boxing.boxBoolean(false);
                            }
                            File cacheDir2 = context.getCacheDir();
                            Intrinsics.checkNotNullExpressionValue(cacheDir2, "getCacheDir(...)");
                            r4 = FilesKt.resolve(cacheDir2, "temp_video.mp4");
                            try {
                                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                                if (openInputStream == null) {
                                    return Boxing.boxBoolean(false);
                                }
                                InputStream inputStream = openInputStream;
                                try {
                                    InputStream inputStream2 = inputStream;
                                    FileOutputStream fileOutputStream = new FileOutputStream((File) r4);
                                    try {
                                        fakeHalManager$copyMediaToTarget$12 = fakeHalManager$copyMediaToTarget$1;
                                    } catch (Throwable th3) {
                                        th2 = th3;
                                    }
                                    try {
                                        ByteStreamsKt.copyTo$default(inputStream2, fileOutputStream, 0, 2, null);
                                        try {
                                            CloseableKt.closeFinally(fileOutputStream, null);
                                            try {
                                                CloseableKt.closeFinally(inputStream, null);
                                                File cacheDir3 = context.getCacheDir();
                                                Intrinsics.checkNotNullExpressionValue(cacheDir3, "getCacheDir(...)");
                                                resolve = FilesKt.resolve(cacheDir3, "temp_video_1080p.mp4");
                                                VideoResampler videoResampler = VideoResampler.INSTANCE;
                                                String absolutePath = r4.getAbsolutePath();
                                                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                                                String absolutePath2 = resolve.getAbsolutePath();
                                                Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                                                File file4 = videoResampler.transcode(absolutePath, absolutePath2) ? resolve : r4;
                                                RootShell rootShell2 = RootShell.INSTANCE;
                                                String str6 = "cp '" + file4.getAbsolutePath() + "' '" + str + "' && chmod 644 '" + str + "' && (chcon u:object_r:shell_data_file:s0 '" + str + "' 2>/dev/null || true) && echo OK";
                                                fakeHalManager$copyMediaToTarget$12.L$0 = r4;
                                                fakeHalManager$copyMediaToTarget$12.L$1 = resolve;
                                                fakeHalManager$copyMediaToTarget$12.label = 3;
                                                exec = rootShell2.exec(str6, fakeHalManager$copyMediaToTarget$12);
                                                file3 = r4;
                                                if (exec == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                file3.delete();
                                                resolve.delete();
                                                return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "OK", false, 2, (Object) null));
                                            } catch (Exception e) {
                                                file = r4;
                                                file.delete();
                                                return Boxing.boxBoolean(false);
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                            try {
                                                throw th;
                                            } catch (Throwable th5) {
                                                CloseableKt.closeFinally(inputStream, th);
                                                throw th5;
                                            }
                                        }
                                    } catch (Throwable th6) {
                                        th2 = th6;
                                        try {
                                            throw th2;
                                        } catch (Throwable th7) {
                                            try {
                                                CloseableKt.closeFinally(fileOutputStream, th2);
                                                throw th7;
                                            } catch (Throwable th8) {
                                                th = th8;
                                                throw th;
                                            }
                                        }
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                }
                            } catch (Exception e2) {
                                file = r4;
                            }
                            break;
                        case 1:
                            resolve2 = (File) fakeHalManager$copyMediaToTarget$1.L$1;
                            String str7 = (String) fakeHalManager$copyMediaToTarget$1.L$0;
                            ResultKt.throwOnFailure(obj2);
                            str3 = str7;
                            str2 = "cp '";
                            convertLegacy$default = obj2;
                            z = false;
                            str4 = "' '";
                            if (((Boolean) convertLegacy$default).booleanValue()) {
                            }
                            break;
                        case 2:
                            file2 = (File) fakeHalManager$copyMediaToTarget$1.L$0;
                            ResultKt.throwOnFailure(obj2);
                            exec2 = obj2;
                            z = false;
                            obj = null;
                            file2.delete();
                            return Boxing.boxBoolean(StringsKt.contains$default(((RootShell.Result) exec2).getOutput(), "OK", z, i, obj));
                        case 3:
                            resolve = (File) fakeHalManager$copyMediaToTarget$1.L$1;
                            file = (File) fakeHalManager$copyMediaToTarget$1.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                file3 = file;
                                exec = obj2;
                                file3.delete();
                                resolve.delete();
                                return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "OK", false, 2, (Object) null));
                            } catch (Exception e3) {
                                file.delete();
                                return Boxing.boxBoolean(false);
                            }
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            switch (r4) {
            }
        } catch (Exception e4) {
            file = r4;
            file.delete();
            return Boxing.boxBoolean(false);
        }
        fakeHalManager$copyMediaToTarget$1 = new FakeHalManager$copyMediaToTarget$1(this, continuation);
        Object obj22 = fakeHalManager$copyMediaToTarget$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r4 = fakeHalManager$copyMediaToTarget$1.label;
        int i2 = 2;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object copyMediaToTarget(Context context, Uri uri, String target, boolean compensateFrontPreviewMirror, Continuation<? super Boolean> continuation) {
        FakeHalManager$copyMediaToTarget$3 fakeHalManager$copyMediaToTarget$3;
        File tempMp4;
        Object convertLegacy;
        String target2;
        boolean ok;
        Object exec;
        if (continuation instanceof FakeHalManager$copyMediaToTarget$3) {
            FakeHalManager$copyMediaToTarget$3 fakeHalManager$copyMediaToTarget$32 = (FakeHalManager$copyMediaToTarget$3) continuation;
            if ((fakeHalManager$copyMediaToTarget$32.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$copyMediaToTarget$32.label -= Integer.MIN_VALUE;
                fakeHalManager$copyMediaToTarget$3 = fakeHalManager$copyMediaToTarget$32;
                Object $result = fakeHalManager$copyMediaToTarget$3.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$copyMediaToTarget$3.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        boolean isImage = isImageMime(context, uri);
                        boolean isVideo = !isImage && (isVideoMime(context, uri) || isKnownVideoExtension(context, uri));
                        if (!isImage && !isVideo) {
                            return Boxing.boxBoolean(false);
                        }
                        if (!isImage) {
                            fakeHalManager$copyMediaToTarget$3.label = 3;
                            Object copyMediaToTarget = copyMediaToTarget(context, uri, target, fakeHalManager$copyMediaToTarget$3);
                            return copyMediaToTarget == coroutine_suspended ? coroutine_suspended : copyMediaToTarget;
                        }
                        File cacheDir = context.getCacheDir();
                        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
                        tempMp4 = FilesKt.resolve(cacheDir, "converted_image.mp4");
                        ImageToVideoConverter imageToVideoConverter = ImageToVideoConverter.INSTANCE;
                        boolean z = compensateFrontPreviewMirror;
                        fakeHalManager$copyMediaToTarget$3.L$0 = target;
                        fakeHalManager$copyMediaToTarget$3.L$1 = tempMp4;
                        fakeHalManager$copyMediaToTarget$3.label = 1;
                        convertLegacy = imageToVideoConverter.convertLegacy(context, uri, tempMp4, z, fakeHalManager$copyMediaToTarget$3);
                        if (convertLegacy == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        target2 = target;
                        ok = ((Boolean) convertLegacy).booleanValue();
                        if (ok) {
                            return Boxing.boxBoolean(false);
                        }
                        RootShell rootShell = RootShell.INSTANCE;
                        String str = "cp '" + tempMp4.getAbsolutePath() + "' '" + target2 + "' && chmod 644 '" + target2 + "' && (chcon u:object_r:shell_data_file:s0 '" + target2 + "' 2>/dev/null || true) && echo OK";
                        fakeHalManager$copyMediaToTarget$3.L$0 = tempMp4;
                        fakeHalManager$copyMediaToTarget$3.L$1 = null;
                        fakeHalManager$copyMediaToTarget$3.label = 2;
                        exec = rootShell.exec(str, fakeHalManager$copyMediaToTarget$3);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell.Result result = (RootShell.Result) exec;
                        tempMp4.delete();
                        return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "OK", false, 2, (Object) null));
                    case 1:
                        File tempMp42 = (File) fakeHalManager$copyMediaToTarget$3.L$1;
                        target2 = (String) fakeHalManager$copyMediaToTarget$3.L$0;
                        ResultKt.throwOnFailure($result);
                        tempMp4 = tempMp42;
                        convertLegacy = $result;
                        ok = ((Boolean) convertLegacy).booleanValue();
                        if (ok) {
                        }
                        break;
                    case 2:
                        File tempMp43 = (File) fakeHalManager$copyMediaToTarget$3.L$0;
                        ResultKt.throwOnFailure($result);
                        tempMp4 = tempMp43;
                        exec = $result;
                        RootShell.Result result2 = (RootShell.Result) exec;
                        tempMp4.delete();
                        return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) result2.getOutput(), (CharSequence) "OK", false, 2, (Object) null));
                    case 3:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$copyMediaToTarget$3 = new FakeHalManager$copyMediaToTarget$3(this, continuation);
        Object $result2 = fakeHalManager$copyMediaToTarget$3.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$copyMediaToTarget$3.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setBackVideo(Context context, Uri uri, Continuation<? super Boolean> continuation) {
        FakeHalManager$setBackVideo$1 fakeHalManager$setBackVideo$1;
        FakeHalManager fakeHalManager;
        Object copyMediaToTarget;
        boolean ok;
        Object $result;
        Object readActiveSlot;
        FakeHalManager fakeHalManager2;
        boolean ok2;
        RootShell rootShell;
        String str;
        boolean ok3;
        FakeHalManager fakeHalManager3;
        String activeSlot;
        FakeHalManager fakeHalManager4;
        boolean ok4;
        if (continuation instanceof FakeHalManager$setBackVideo$1) {
            FakeHalManager$setBackVideo$1 fakeHalManager$setBackVideo$12 = (FakeHalManager$setBackVideo$1) continuation;
            if ((fakeHalManager$setBackVideo$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$setBackVideo$12.label -= Integer.MIN_VALUE;
                fakeHalManager$setBackVideo$1 = fakeHalManager$setBackVideo$12;
                Object $result2 = fakeHalManager$setBackVideo$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$setBackVideo$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        fakeHalManager = this;
                        fakeHalManager$setBackVideo$1.L$0 = fakeHalManager;
                        fakeHalManager$setBackVideo$1.label = 1;
                        copyMediaToTarget = fakeHalManager.copyMediaToTarget(context, uri, "/data/local/tmp/fake_video.mp4", fakeHalManager$setBackVideo$1);
                        if (copyMediaToTarget == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (!ok) {
                            fakeHalManager$setBackVideo$1.L$0 = fakeHalManager;
                            fakeHalManager$setBackVideo$1.Z$0 = ok;
                            fakeHalManager$setBackVideo$1.label = 2;
                            readActiveSlot = fakeHalManager.readActiveSlot(fakeHalManager$setBackVideo$1);
                            if (readActiveSlot == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            fakeHalManager2 = fakeHalManager;
                            ok2 = ok;
                            String activeSlot2 = (String) readActiveSlot;
                            String activeTarget = !Intrinsics.areEqual(activeSlot2, "B") ? BACK_VIDEO_B : BACK_VIDEO_A;
                            rootShell = RootShell.INSTANCE;
                            str = "cp '/data/local/tmp/fake_video.mp4' '" + activeTarget + "' && chmod 644 '" + activeTarget + "' && (chcon u:object_r:shell_data_file:s0 '" + activeTarget + "' 2>/dev/null || true) && echo OK";
                            fakeHalManager$setBackVideo$1.L$0 = fakeHalManager2;
                            fakeHalManager$setBackVideo$1.L$1 = activeSlot2;
                            fakeHalManager$setBackVideo$1.Z$0 = ok2;
                            fakeHalManager$setBackVideo$1.label = 3;
                            if (rootShell.exec(str, fakeHalManager$setBackVideo$1) != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            ok3 = ok2;
                            fakeHalManager3 = fakeHalManager2;
                            activeSlot = activeSlot2;
                            if (Intrinsics.areEqual(activeSlot, "B")) {
                                $result = coroutine_suspended;
                                FakeHalManager fakeHalManager5 = fakeHalManager3;
                                boolean ok5 = ok3;
                                fakeHalManager$setBackVideo$1.L$0 = fakeHalManager5;
                                fakeHalManager$setBackVideo$1.L$1 = null;
                                fakeHalManager$setBackVideo$1.Z$0 = ok5;
                                fakeHalManager$setBackVideo$1.label = 5;
                                if (writeConfig$default(fakeHalManager5, null, null, null, BACK_VIDEO_A, null, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$setBackVideo$1, 262103, null) == $result) {
                                    return $result;
                                }
                                fakeHalManager4 = fakeHalManager5;
                                ok4 = ok5;
                            } else {
                                FakeHalManager fakeHalManager6 = fakeHalManager3;
                                boolean ok6 = ok3;
                                fakeHalManager$setBackVideo$1.L$0 = fakeHalManager6;
                                fakeHalManager$setBackVideo$1.L$1 = null;
                                fakeHalManager$setBackVideo$1.Z$0 = ok6;
                                fakeHalManager$setBackVideo$1.label = 4;
                                $result = coroutine_suspended;
                                if (writeConfig$default(fakeHalManager6, null, null, null, null, BACK_VIDEO_B, "B", null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$setBackVideo$1, 262095, null) == $result) {
                                    return $result;
                                }
                                fakeHalManager4 = fakeHalManager6;
                                ok4 = ok6;
                            }
                            fakeHalManager$setBackVideo$1.L$0 = fakeHalManager4;
                            fakeHalManager$setBackVideo$1.Z$0 = ok4;
                            fakeHalManager$setBackVideo$1.label = 6;
                            if (fakeHalManager4.mirrorActiveBackToFront(fakeHalManager$setBackVideo$1) == $result) {
                                return $result;
                            }
                            ok = ok4;
                            fakeHalManager = fakeHalManager4;
                            if (ok) {
                                return Boxing.boxBoolean(false);
                            }
                            fakeHalManager$setBackVideo$1.L$0 = null;
                            fakeHalManager$setBackVideo$1.label = 7;
                            Object start = fakeHalManager.start(fakeHalManager$setBackVideo$1);
                            return start == $result ? $result : start;
                        }
                        $result = coroutine_suspended;
                        if (ok) {
                        }
                    case 1:
                        fakeHalManager = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        copyMediaToTarget = $result2;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (!ok) {
                        }
                        break;
                    case 2:
                        ok2 = fakeHalManager$setBackVideo$1.Z$0;
                        fakeHalManager2 = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        readActiveSlot = $result2;
                        String activeSlot22 = (String) readActiveSlot;
                        if (!Intrinsics.areEqual(activeSlot22, "B")) {
                        }
                        rootShell = RootShell.INSTANCE;
                        str = "cp '/data/local/tmp/fake_video.mp4' '" + activeTarget + "' && chmod 644 '" + activeTarget + "' && (chcon u:object_r:shell_data_file:s0 '" + activeTarget + "' 2>/dev/null || true) && echo OK";
                        fakeHalManager$setBackVideo$1.L$0 = fakeHalManager2;
                        fakeHalManager$setBackVideo$1.L$1 = activeSlot22;
                        fakeHalManager$setBackVideo$1.Z$0 = ok2;
                        fakeHalManager$setBackVideo$1.label = 3;
                        if (rootShell.exec(str, fakeHalManager$setBackVideo$1) != coroutine_suspended) {
                        }
                        break;
                    case 3:
                        boolean ok7 = fakeHalManager$setBackVideo$1.Z$0;
                        activeSlot = (String) fakeHalManager$setBackVideo$1.L$1;
                        FakeHalManager fakeHalManager7 = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        ok3 = ok7;
                        fakeHalManager3 = fakeHalManager7;
                        if (Intrinsics.areEqual(activeSlot, "B")) {
                        }
                        fakeHalManager$setBackVideo$1.L$0 = fakeHalManager4;
                        fakeHalManager$setBackVideo$1.Z$0 = ok4;
                        fakeHalManager$setBackVideo$1.label = 6;
                        if (fakeHalManager4.mirrorActiveBackToFront(fakeHalManager$setBackVideo$1) == $result) {
                        }
                        ok = ok4;
                        fakeHalManager = fakeHalManager4;
                        if (ok) {
                        }
                        break;
                    case 4:
                        ok4 = fakeHalManager$setBackVideo$1.Z$0;
                        fakeHalManager4 = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        fakeHalManager$setBackVideo$1.L$0 = fakeHalManager4;
                        fakeHalManager$setBackVideo$1.Z$0 = ok4;
                        fakeHalManager$setBackVideo$1.label = 6;
                        if (fakeHalManager4.mirrorActiveBackToFront(fakeHalManager$setBackVideo$1) == $result) {
                        }
                        ok = ok4;
                        fakeHalManager = fakeHalManager4;
                        if (ok) {
                        }
                        break;
                    case 5:
                        ok4 = fakeHalManager$setBackVideo$1.Z$0;
                        fakeHalManager4 = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        fakeHalManager$setBackVideo$1.L$0 = fakeHalManager4;
                        fakeHalManager$setBackVideo$1.Z$0 = ok4;
                        fakeHalManager$setBackVideo$1.label = 6;
                        if (fakeHalManager4.mirrorActiveBackToFront(fakeHalManager$setBackVideo$1) == $result) {
                        }
                        ok = ok4;
                        fakeHalManager = fakeHalManager4;
                        if (ok) {
                        }
                        break;
                    case 6:
                        ok4 = fakeHalManager$setBackVideo$1.Z$0;
                        fakeHalManager4 = (FakeHalManager) fakeHalManager$setBackVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        ok = ok4;
                        fakeHalManager = fakeHalManager4;
                        if (ok) {
                        }
                        break;
                    case 7:
                        ResultKt.throwOnFailure($result2);
                        return $result2;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$setBackVideo$1 = new FakeHalManager$setBackVideo$1(this, continuation);
        Object $result22 = fakeHalManager$setBackVideo$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$setBackVideo$1.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object activateBackSlot(String slot, Continuation<? super Boolean> continuation) {
        FakeHalManager$activateBackSlot$1 fakeHalManager$activateBackSlot$1;
        Object exec;
        FakeHalManager fakeHalManager;
        String normalizedSlot;
        RootShell.Result result;
        FakeHalManager fakeHalManager2;
        boolean z;
        if (continuation instanceof FakeHalManager$activateBackSlot$1) {
            FakeHalManager$activateBackSlot$1 fakeHalManager$activateBackSlot$12 = (FakeHalManager$activateBackSlot$1) continuation;
            if ((fakeHalManager$activateBackSlot$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$activateBackSlot$12.label -= Integer.MIN_VALUE;
                fakeHalManager$activateBackSlot$1 = fakeHalManager$activateBackSlot$12;
                Object $result = fakeHalManager$activateBackSlot$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$activateBackSlot$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String slot2 = Intrinsics.areEqual(slot, "B") ? "B" : ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        String source = Intrinsics.areEqual(slot2, "B") ? BACK_VIDEO_B : BACK_VIDEO_A;
                        fakeHalManager$activateBackSlot$1.L$0 = this;
                        fakeHalManager$activateBackSlot$1.L$1 = slot2;
                        fakeHalManager$activateBackSlot$1.label = 1;
                        exec = RootShell.INSTANCE.exec("[ -f '" + source + "' ] && cp '" + source + "' '/data/local/tmp/fake_video.mp4' && chmod 644 '/data/local/tmp/fake_video.mp4' && (chcon u:object_r:shell_data_file:s0 '/data/local/tmp/fake_video.mp4' 2>/dev/null || true) && echo OK", fakeHalManager$activateBackSlot$1);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        String str = slot2;
                        fakeHalManager = this;
                        normalizedSlot = str;
                        result = (RootShell.Result) exec;
                        if (StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "OK", false, 2, (Object) null)) {
                            return Boxing.boxBoolean(false);
                        }
                        fakeHalManager$activateBackSlot$1.L$0 = fakeHalManager;
                        fakeHalManager$activateBackSlot$1.L$1 = normalizedSlot;
                        fakeHalManager$activateBackSlot$1.label = 2;
                        if (fakeHalManager.mirrorActiveBackToFront(fakeHalManager$activateBackSlot$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager2 = fakeHalManager;
                        if (Intrinsics.areEqual(normalizedSlot, "B")) {
                            z = true;
                            fakeHalManager$activateBackSlot$1.L$0 = null;
                            fakeHalManager$activateBackSlot$1.L$1 = null;
                            fakeHalManager$activateBackSlot$1.label = 4;
                            if (writeConfig$default(fakeHalManager2, null, null, null, BACK_VIDEO_A, null, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$activateBackSlot$1, 262103, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            z = true;
                            fakeHalManager$activateBackSlot$1.L$0 = null;
                            fakeHalManager$activateBackSlot$1.L$1 = null;
                            fakeHalManager$activateBackSlot$1.label = 3;
                            if (writeConfig$default(fakeHalManager2, null, null, null, null, BACK_VIDEO_B, "B", null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$activateBackSlot$1, 262095, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Boxing.boxBoolean(z);
                    case 1:
                        normalizedSlot = (String) fakeHalManager$activateBackSlot$1.L$1;
                        fakeHalManager = (FakeHalManager) fakeHalManager$activateBackSlot$1.L$0;
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        result = (RootShell.Result) exec;
                        if (StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "OK", false, 2, (Object) null)) {
                        }
                        break;
                    case 2:
                        normalizedSlot = (String) fakeHalManager$activateBackSlot$1.L$1;
                        FakeHalManager fakeHalManager3 = (FakeHalManager) fakeHalManager$activateBackSlot$1.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager2 = fakeHalManager3;
                        if (Intrinsics.areEqual(normalizedSlot, "B")) {
                        }
                        return Boxing.boxBoolean(z);
                    case 3:
                        ResultKt.throwOnFailure($result);
                        z = true;
                        return Boxing.boxBoolean(z);
                    case 4:
                        ResultKt.throwOnFailure($result);
                        z = true;
                        return Boxing.boxBoolean(z);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$activateBackSlot$1 = new FakeHalManager$activateBackSlot$1(this, continuation);
        Object $result2 = fakeHalManager$activateBackSlot$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$activateBackSlot$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setBackSlotA(Context context, Uri uri, Continuation<? super Boolean> continuation) {
        FakeHalManager$setBackSlotA$1 fakeHalManager$setBackSlotA$1;
        FakeHalManager$setBackSlotA$1 fakeHalManager$setBackSlotA$12;
        Object copyMediaToTarget;
        FakeHalManager fakeHalManager;
        boolean ok;
        Object activateBackSlot;
        if (continuation instanceof FakeHalManager$setBackSlotA$1) {
            fakeHalManager$setBackSlotA$1 = (FakeHalManager$setBackSlotA$1) continuation;
            if ((fakeHalManager$setBackSlotA$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$setBackSlotA$1.label -= Integer.MIN_VALUE;
                fakeHalManager$setBackSlotA$12 = fakeHalManager$setBackSlotA$1;
                Object $result = fakeHalManager$setBackSlotA$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$setBackSlotA$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$setBackSlotA$12.L$0 = this;
                        fakeHalManager$setBackSlotA$12.label = 1;
                        copyMediaToTarget = copyMediaToTarget(context, uri, BACK_VIDEO_A, fakeHalManager$setBackSlotA$12);
                        if (copyMediaToTarget == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (ok) {
                            fakeHalManager$setBackSlotA$12.L$0 = fakeHalManager;
                            fakeHalManager$setBackSlotA$12.Z$0 = ok;
                            fakeHalManager$setBackSlotA$12.label = 2;
                            activateBackSlot = fakeHalManager.activateBackSlot(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, fakeHalManager$setBackSlotA$12);
                            if (activateBackSlot == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (!((Boolean) activateBackSlot).booleanValue()) {
                                return Boxing.boxBoolean(false);
                            }
                        }
                        if (!ok) {
                            return Boxing.boxBoolean(false);
                        }
                        fakeHalManager$setBackSlotA$12.L$0 = null;
                        fakeHalManager$setBackSlotA$12.label = 3;
                        Object start = fakeHalManager.start(fakeHalManager$setBackSlotA$12);
                        return start == coroutine_suspended ? coroutine_suspended : start;
                    case 1:
                        FakeHalManager fakeHalManager2 = (FakeHalManager) fakeHalManager$setBackSlotA$12.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager = fakeHalManager2;
                        copyMediaToTarget = $result;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (ok) {
                        }
                        if (!ok) {
                        }
                        break;
                    case 2:
                        ok = fakeHalManager$setBackSlotA$12.Z$0;
                        fakeHalManager = (FakeHalManager) fakeHalManager$setBackSlotA$12.L$0;
                        ResultKt.throwOnFailure($result);
                        activateBackSlot = $result;
                        if (!((Boolean) activateBackSlot).booleanValue()) {
                        }
                        if (!ok) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$setBackSlotA$1 = new FakeHalManager$setBackSlotA$1(this, continuation);
        fakeHalManager$setBackSlotA$12 = fakeHalManager$setBackSlotA$1;
        Object $result2 = fakeHalManager$setBackSlotA$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$setBackSlotA$12.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setBackSlotB(Context context, Uri uri, Continuation<? super Boolean> continuation) {
        FakeHalManager$setBackSlotB$1 fakeHalManager$setBackSlotB$1;
        FakeHalManager$setBackSlotB$1 fakeHalManager$setBackSlotB$12;
        Object copyMediaToTarget;
        FakeHalManager fakeHalManager;
        boolean ok;
        Object activateBackSlot;
        if (continuation instanceof FakeHalManager$setBackSlotB$1) {
            fakeHalManager$setBackSlotB$1 = (FakeHalManager$setBackSlotB$1) continuation;
            if ((fakeHalManager$setBackSlotB$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$setBackSlotB$1.label -= Integer.MIN_VALUE;
                fakeHalManager$setBackSlotB$12 = fakeHalManager$setBackSlotB$1;
                Object $result = fakeHalManager$setBackSlotB$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$setBackSlotB$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$setBackSlotB$12.L$0 = this;
                        fakeHalManager$setBackSlotB$12.label = 1;
                        copyMediaToTarget = copyMediaToTarget(context, uri, BACK_VIDEO_B, fakeHalManager$setBackSlotB$12);
                        if (copyMediaToTarget == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (ok) {
                            fakeHalManager$setBackSlotB$12.L$0 = fakeHalManager;
                            fakeHalManager$setBackSlotB$12.Z$0 = ok;
                            fakeHalManager$setBackSlotB$12.label = 2;
                            activateBackSlot = fakeHalManager.activateBackSlot("B", fakeHalManager$setBackSlotB$12);
                            if (activateBackSlot == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (!((Boolean) activateBackSlot).booleanValue()) {
                                return Boxing.boxBoolean(false);
                            }
                        }
                        if (!ok) {
                            return Boxing.boxBoolean(false);
                        }
                        fakeHalManager$setBackSlotB$12.L$0 = null;
                        fakeHalManager$setBackSlotB$12.label = 3;
                        Object start = fakeHalManager.start(fakeHalManager$setBackSlotB$12);
                        return start == coroutine_suspended ? coroutine_suspended : start;
                    case 1:
                        FakeHalManager fakeHalManager2 = (FakeHalManager) fakeHalManager$setBackSlotB$12.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager = fakeHalManager2;
                        copyMediaToTarget = $result;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (ok) {
                        }
                        if (!ok) {
                        }
                        break;
                    case 2:
                        ok = fakeHalManager$setBackSlotB$12.Z$0;
                        fakeHalManager = (FakeHalManager) fakeHalManager$setBackSlotB$12.L$0;
                        ResultKt.throwOnFailure($result);
                        activateBackSlot = $result;
                        if (!((Boolean) activateBackSlot).booleanValue()) {
                        }
                        if (!ok) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$setBackSlotB$1 = new FakeHalManager$setBackSlotB$1(this, continuation);
        fakeHalManager$setBackSlotB$12 = fakeHalManager$setBackSlotB$1;
        Object $result2 = fakeHalManager$setBackSlotB$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$setBackSlotB$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readActiveSlot(Continuation<? super String> continuation) {
        FakeHalManager$readActiveSlot$1 fakeHalManager$readActiveSlot$1;
        FakeHalManager$readActiveSlot$1 fakeHalManager$readActiveSlot$12;
        Object exec;
        if (continuation instanceof FakeHalManager$readActiveSlot$1) {
            fakeHalManager$readActiveSlot$1 = (FakeHalManager$readActiveSlot$1) continuation;
            if ((fakeHalManager$readActiveSlot$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$readActiveSlot$1.label -= Integer.MIN_VALUE;
                fakeHalManager$readActiveSlot$12 = fakeHalManager$readActiveSlot$1;
                Object $result = fakeHalManager$readActiveSlot$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$readActiveSlot$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$readActiveSlot$12.label = 1;
                        exec = rootShell.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$readActiveSlot$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                RootShell.Result confResult = (RootShell.Result) exec;
                String current = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                for (String line : StringsKt.lines(confResult.getOutput())) {
                    if (StringsKt.startsWith$default(line, "active_slot=", false, 2, (Object) null)) {
                        current = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                    }
                }
                return current;
            }
        }
        fakeHalManager$readActiveSlot$1 = new FakeHalManager$readActiveSlot$1(this, continuation);
        fakeHalManager$readActiveSlot$12 = fakeHalManager$readActiveSlot$1;
        Object $result2 = fakeHalManager$readActiveSlot$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$readActiveSlot$12.label) {
        }
        RootShell.Result confResult2 = (RootShell.Result) exec;
        String current2 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
        while (r3.hasNext()) {
        }
        return current2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object mirrorActiveBackToFront(Continuation<? super Unit> continuation) {
        Object exec = RootShell.INSTANCE.exec("[ -f '/data/local/tmp/fake_video.mp4' ] && cp '/data/local/tmp/fake_video.mp4' '/data/local/tmp/fake_video_front.mp4' && chmod 644 '/data/local/tmp/fake_video_front.mp4' && (chcon u:object_r:shell_data_file:s0 '/data/local/tmp/fake_video_front.mp4' 2>/dev/null || true)", continuation);
        return exec == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? exec : Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object swapBackSlot(Continuation<? super String> continuation) {
        FakeHalManager$swapBackSlot$1 fakeHalManager$swapBackSlot$1;
        FakeHalManager$swapBackSlot$1 fakeHalManager$swapBackSlot$12;
        Object readActiveSlot;
        FakeHalManager fakeHalManager;
        String current;
        Object activateBackSlot;
        String newSlot;
        String current2;
        Object start;
        String newSlot2;
        String newSlot3;
        if (continuation instanceof FakeHalManager$swapBackSlot$1) {
            fakeHalManager$swapBackSlot$1 = (FakeHalManager$swapBackSlot$1) continuation;
            if ((fakeHalManager$swapBackSlot$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$swapBackSlot$1.label -= Integer.MIN_VALUE;
                fakeHalManager$swapBackSlot$12 = fakeHalManager$swapBackSlot$1;
                Object $result = fakeHalManager$swapBackSlot$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$swapBackSlot$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$swapBackSlot$12.L$0 = this;
                        fakeHalManager$swapBackSlot$12.label = 1;
                        readActiveSlot = readActiveSlot(fakeHalManager$swapBackSlot$12);
                        if (readActiveSlot == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        current = (String) readActiveSlot;
                        String newSlot4 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        if (Intrinsics.areEqual(current, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                            newSlot4 = "B";
                        }
                        fakeHalManager$swapBackSlot$12.L$0 = fakeHalManager;
                        fakeHalManager$swapBackSlot$12.L$1 = current;
                        fakeHalManager$swapBackSlot$12.L$2 = newSlot4;
                        fakeHalManager$swapBackSlot$12.label = 2;
                        activateBackSlot = fakeHalManager.activateBackSlot(newSlot4, fakeHalManager$swapBackSlot$12);
                        if (activateBackSlot != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        String str = newSlot4;
                        newSlot = current;
                        current2 = str;
                        if (((Boolean) activateBackSlot).booleanValue()) {
                            return newSlot;
                        }
                        fakeHalManager$swapBackSlot$12.L$0 = newSlot;
                        fakeHalManager$swapBackSlot$12.L$1 = current2;
                        fakeHalManager$swapBackSlot$12.L$2 = null;
                        fakeHalManager$swapBackSlot$12.label = 3;
                        start = fakeHalManager.start(fakeHalManager$swapBackSlot$12);
                        if (start == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        newSlot2 = current2;
                        newSlot3 = newSlot;
                        return !((Boolean) start).booleanValue() ? newSlot2 : newSlot3;
                    case 1:
                        FakeHalManager fakeHalManager2 = (FakeHalManager) fakeHalManager$swapBackSlot$12.L$0;
                        ResultKt.throwOnFailure($result);
                        readActiveSlot = $result;
                        fakeHalManager = fakeHalManager2;
                        current = (String) readActiveSlot;
                        String newSlot42 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        if (Intrinsics.areEqual(current, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                        }
                        fakeHalManager$swapBackSlot$12.L$0 = fakeHalManager;
                        fakeHalManager$swapBackSlot$12.L$1 = current;
                        fakeHalManager$swapBackSlot$12.L$2 = newSlot42;
                        fakeHalManager$swapBackSlot$12.label = 2;
                        activateBackSlot = fakeHalManager.activateBackSlot(newSlot42, fakeHalManager$swapBackSlot$12);
                        if (activateBackSlot != coroutine_suspended) {
                        }
                        break;
                    case 2:
                        current2 = (String) fakeHalManager$swapBackSlot$12.L$2;
                        newSlot = (String) fakeHalManager$swapBackSlot$12.L$1;
                        fakeHalManager = (FakeHalManager) fakeHalManager$swapBackSlot$12.L$0;
                        ResultKt.throwOnFailure($result);
                        activateBackSlot = $result;
                        if (((Boolean) activateBackSlot).booleanValue()) {
                        }
                        break;
                    case 3:
                        newSlot2 = (String) fakeHalManager$swapBackSlot$12.L$1;
                        newSlot3 = (String) fakeHalManager$swapBackSlot$12.L$0;
                        ResultKt.throwOnFailure($result);
                        start = $result;
                        if (!((Boolean) start).booleanValue()) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$swapBackSlot$1 = new FakeHalManager$swapBackSlot$1(this, continuation);
        fakeHalManager$swapBackSlot$12 = fakeHalManager$swapBackSlot$1;
        Object $result2 = fakeHalManager$swapBackSlot$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$swapBackSlot$12.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setFrontVideo(Context context, Uri uri, Continuation<? super Boolean> continuation) {
        FakeHalManager$setFrontVideo$1 fakeHalManager$setFrontVideo$1;
        Object copyMediaToTarget;
        FakeHalManager fakeHalManager;
        boolean ok;
        Object $result;
        boolean ok2;
        FakeHalManager fakeHalManager2;
        if (continuation instanceof FakeHalManager$setFrontVideo$1) {
            FakeHalManager$setFrontVideo$1 fakeHalManager$setFrontVideo$12 = (FakeHalManager$setFrontVideo$1) continuation;
            if ((fakeHalManager$setFrontVideo$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$setFrontVideo$12.label -= Integer.MIN_VALUE;
                fakeHalManager$setFrontVideo$1 = fakeHalManager$setFrontVideo$12;
                Object $result2 = fakeHalManager$setFrontVideo$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$setFrontVideo$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        fakeHalManager$setFrontVideo$1.L$0 = this;
                        fakeHalManager$setFrontVideo$1.label = 1;
                        copyMediaToTarget = copyMediaToTarget(context, uri, FRONT_VIDEO, fakeHalManager$setFrontVideo$1);
                        if (copyMediaToTarget == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (!ok) {
                            FakeHalManager fakeHalManager3 = fakeHalManager;
                            fakeHalManager$setFrontVideo$1.L$0 = fakeHalManager3;
                            fakeHalManager$setFrontVideo$1.Z$0 = ok;
                            fakeHalManager$setFrontVideo$1.label = 2;
                            $result = coroutine_suspended;
                            if (writeConfig$default(fakeHalManager3, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$setFrontVideo$1, 262143, null) == $result) {
                                return $result;
                            }
                            ok2 = ok;
                            fakeHalManager2 = fakeHalManager3;
                            ok = ok2;
                            fakeHalManager = fakeHalManager2;
                            if (!ok) {
                                return Boxing.boxBoolean(false);
                            }
                            fakeHalManager$setFrontVideo$1.L$0 = null;
                            fakeHalManager$setFrontVideo$1.label = 3;
                            Object start = fakeHalManager.start(fakeHalManager$setFrontVideo$1);
                            return start == $result ? $result : start;
                        }
                        $result = coroutine_suspended;
                        if (!ok) {
                        }
                    case 1:
                        FakeHalManager fakeHalManager4 = (FakeHalManager) fakeHalManager$setFrontVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        copyMediaToTarget = $result2;
                        fakeHalManager = fakeHalManager4;
                        ok = ((Boolean) copyMediaToTarget).booleanValue();
                        if (!ok) {
                        }
                        break;
                    case 2:
                        ok2 = fakeHalManager$setFrontVideo$1.Z$0;
                        fakeHalManager2 = (FakeHalManager) fakeHalManager$setFrontVideo$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        ok = ok2;
                        fakeHalManager = fakeHalManager2;
                        if (!ok) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result2);
                        return $result2;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$setFrontVideo$1 = new FakeHalManager$setFrontVideo$1(this, continuation);
        Object $result22 = fakeHalManager$setFrontVideo$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$setFrontVideo$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0037. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0396 A[PHI: r15
      0x0396: PHI (r15v22 '$result' java.lang.Object) = (r15v21 '$result' java.lang.Object), (r15v0 '$result' java.lang.Object) binds: [B:15:0x0393, B:11:0x0042] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0395 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0388 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x034b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x033e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object applyWithTransform(Context context, Uri uri, String camera, ImageToVideoConverter.TransformConfig config, Continuation<? super Boolean> continuation) {
        FakeHalManager$applyWithTransform$1 fakeHalManager$applyWithTransform$1;
        boolean z;
        Object $result;
        String str;
        File tempFile;
        Continuation $continuation;
        Throwable th;
        Throwable th2;
        CharSequence charSequence;
        File transcodedFile;
        FakeHalManager$applyWithTransform$1 fakeHalManager$applyWithTransform$12;
        Object exec;
        Object obj;
        String target;
        FakeHalManager fakeHalManager;
        String camera2;
        File tempMp4;
        Object convert;
        String target2;
        String target3;
        FakeHalManager fakeHalManager2;
        boolean ok;
        Object exec2;
        FakeHalManager fakeHalManager3;
        RootShell.Result result;
        boolean z2;
        String target4;
        FakeHalManager fakeHalManager4;
        Object $result2;
        FakeHalManager fakeHalManager5;
        Object readActiveSlot;
        RootShell.Result result2;
        RootShell rootShell;
        String str2;
        Object obj2;
        if (continuation instanceof FakeHalManager$applyWithTransform$1) {
            fakeHalManager$applyWithTransform$1 = (FakeHalManager$applyWithTransform$1) continuation;
            if ((fakeHalManager$applyWithTransform$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$applyWithTransform$1.label -= Integer.MIN_VALUE;
                Object $result3 = fakeHalManager$applyWithTransform$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$applyWithTransform$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result3);
                        String target5 = Intrinsics.areEqual(camera, "front") ? FRONT_VIDEO : "/data/local/tmp/fake_video.mp4";
                        boolean isImage = isImageMime(context, uri);
                        boolean isVideo = !isImage && (isVideoMime(context, uri) || isKnownVideoExtension(context, uri));
                        if (!isImage && !isVideo) {
                            return Boxing.boxBoolean(false);
                        }
                        z = false;
                        if (!isImage) {
                            String target6 = target5;
                            $result = $result3;
                            str = "cp '";
                            File cacheDir = context.getCacheDir();
                            Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
                            tempFile = FilesKt.resolve(cacheDir, "temp_video_transform.mp4");
                            try {
                                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                                if (openInputStream != null) {
                                    try {
                                        InputStream inputStream = openInputStream;
                                        try {
                                            InputStream input = inputStream;
                                            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                                            try {
                                                FileOutputStream output = fileOutputStream;
                                                try {
                                                    FakeHalManager$applyWithTransform$1 fakeHalManager$applyWithTransform$13 = fakeHalManager$applyWithTransform$1;
                                                    charSequence = "OK";
                                                    try {
                                                        ByteStreamsKt.copyTo$default(input, output, 0, 2, null);
                                                        try {
                                                            CloseableKt.closeFinally(fileOutputStream, null);
                                                            try {
                                                                CloseableKt.closeFinally(inputStream, null);
                                                                File cacheDir2 = context.getCacheDir();
                                                                Intrinsics.checkNotNullExpressionValue(cacheDir2, "getCacheDir(...)");
                                                                transcodedFile = FilesKt.resolve(cacheDir2, "temp_video_transform_1080p.mp4");
                                                                VideoResampler videoResampler = VideoResampler.INSTANCE;
                                                                String absolutePath = tempFile.getAbsolutePath();
                                                                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                                                                String absolutePath2 = transcodedFile.getAbsolutePath();
                                                                Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                                                                boolean transcoded = videoResampler.transcode(absolutePath, absolutePath2);
                                                                File sourceFile = transcoded ? transcodedFile : tempFile;
                                                                RootShell rootShell2 = RootShell.INSTANCE;
                                                                String str3 = str + sourceFile.getAbsolutePath() + "' '" + target6 + "' && chmod 644 '" + target6 + "' && (chcon u:object_r:shell_data_file:s0 '" + target6 + "' 2>/dev/null || true) && echo OK";
                                                                fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$13;
                                                                try {
                                                                    fakeHalManager$applyWithTransform$12.L$0 = this;
                                                                    fakeHalManager$applyWithTransform$12.L$1 = camera;
                                                                    fakeHalManager$applyWithTransform$12.L$2 = target6;
                                                                    fakeHalManager$applyWithTransform$12.L$3 = tempFile;
                                                                    fakeHalManager$applyWithTransform$12.L$4 = transcodedFile;
                                                                    fakeHalManager$applyWithTransform$12.label = 3;
                                                                    exec = rootShell2.exec(str3, fakeHalManager$applyWithTransform$12);
                                                                    obj = coroutine_suspended;
                                                                    if (exec == obj) {
                                                                        return obj;
                                                                    }
                                                                    target = target6;
                                                                    fakeHalManager = this;
                                                                    camera2 = camera;
                                                                    result2 = (RootShell.Result) exec;
                                                                    tempFile.delete();
                                                                    transcodedFile.delete();
                                                                    if (StringsKt.contains$default((CharSequence) result2.getOutput(), charSequence, false, 2, (Object) null)) {
                                                                        try {
                                                                            return Boxing.boxBoolean(false);
                                                                        } catch (Exception e) {
                                                                        }
                                                                    } else {
                                                                        target4 = target;
                                                                        target2 = camera2;
                                                                        fakeHalManager4 = fakeHalManager;
                                                                        if (!Intrinsics.areEqual(target2, "back")) {
                                                                        }
                                                                    }
                                                                } catch (Exception e2) {
                                                                }
                                                            } catch (Exception e3) {
                                                            }
                                                        } catch (Throwable th3) {
                                                            $continuation = fakeHalManager$applyWithTransform$13;
                                                            th = th3;
                                                            try {
                                                                throw th;
                                                            } catch (Throwable th4) {
                                                                try {
                                                                    CloseableKt.closeFinally(inputStream, th);
                                                                    throw th4;
                                                                } catch (Exception e4) {
                                                                }
                                                            }
                                                        }
                                                    } catch (Throwable th5) {
                                                        $continuation = fakeHalManager$applyWithTransform$13;
                                                        th2 = th5;
                                                        try {
                                                            throw th2;
                                                        } catch (Throwable th6) {
                                                            try {
                                                                CloseableKt.closeFinally(fileOutputStream, th2);
                                                                throw th6;
                                                            } catch (Throwable th7) {
                                                                th = th7;
                                                                throw th;
                                                            }
                                                        }
                                                    }
                                                } catch (Throwable th8) {
                                                    $continuation = fakeHalManager$applyWithTransform$1;
                                                    th2 = th8;
                                                }
                                            } catch (Throwable th9) {
                                                $continuation = fakeHalManager$applyWithTransform$1;
                                                th2 = th9;
                                            }
                                        } catch (Throwable th10) {
                                            $continuation = fakeHalManager$applyWithTransform$1;
                                            th = th10;
                                        }
                                    } catch (Exception e5) {
                                    }
                                } else {
                                    try {
                                        return Boxing.boxBoolean(false);
                                    } catch (Exception e6) {
                                    }
                                }
                            } catch (Exception e7) {
                            }
                            tempFile.delete();
                            return Boxing.boxBoolean(false);
                        }
                        File cacheDir3 = context.getCacheDir();
                        Intrinsics.checkNotNullExpressionValue(cacheDir3, "getCacheDir(...)");
                        tempMp4 = FilesKt.resolve(cacheDir3, "converted_transform.mp4");
                        ImageToVideoConverter imageToVideoConverter = ImageToVideoConverter.INSTANCE;
                        fakeHalManager$applyWithTransform$1.L$0 = this;
                        fakeHalManager$applyWithTransform$1.L$1 = camera;
                        fakeHalManager$applyWithTransform$1.L$2 = target5;
                        fakeHalManager$applyWithTransform$1.L$3 = tempMp4;
                        fakeHalManager$applyWithTransform$1.label = 1;
                        $result = $result3;
                        String target7 = target5;
                        str = "cp '";
                        convert = imageToVideoConverter.convert(context, uri, tempMp4, config, fakeHalManager$applyWithTransform$1);
                        if (convert == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        target2 = camera;
                        target3 = target7;
                        fakeHalManager2 = this;
                        ok = ((Boolean) convert).booleanValue();
                        if (ok) {
                            return Boxing.boxBoolean(z);
                        }
                        RootShell rootShell3 = RootShell.INSTANCE;
                        String str4 = str + tempMp4.getAbsolutePath() + "' '" + target3 + "' && chmod 644 '" + target3 + "' && (chcon u:object_r:shell_data_file:s0 '" + target3 + "' 2>/dev/null || true) && echo OK";
                        fakeHalManager$applyWithTransform$1.L$0 = fakeHalManager2;
                        fakeHalManager$applyWithTransform$1.L$1 = target2;
                        fakeHalManager$applyWithTransform$1.L$2 = target3;
                        fakeHalManager$applyWithTransform$1.L$3 = tempMp4;
                        fakeHalManager$applyWithTransform$1.label = 2;
                        exec2 = rootShell3.exec(str4, fakeHalManager$applyWithTransform$1);
                        if (exec2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager3 = fakeHalManager2;
                        result = (RootShell.Result) exec2;
                        tempMp4.delete();
                        z2 = z;
                        if (StringsKt.contains$default(result.getOutput(), "OK", z2, 2, (Object) null)) {
                            return Boxing.boxBoolean(z2);
                        }
                        fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                        target4 = target3;
                        fakeHalManager4 = fakeHalManager3;
                        obj = coroutine_suspended;
                        if (!Intrinsics.areEqual(target2, "back")) {
                            fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager4;
                            fakeHalManager$applyWithTransform$12.L$1 = target4;
                            fakeHalManager$applyWithTransform$12.L$2 = null;
                            fakeHalManager$applyWithTransform$12.L$3 = null;
                            fakeHalManager$applyWithTransform$12.L$4 = null;
                            fakeHalManager$applyWithTransform$12.label = 4;
                            readActiveSlot = fakeHalManager4.readActiveSlot(fakeHalManager$applyWithTransform$12);
                            if (readActiveSlot == obj) {
                                return obj;
                            }
                            String activeSlot = (String) readActiveSlot;
                            String slotTarget = !Intrinsics.areEqual(activeSlot, "B") ? BACK_VIDEO_B : BACK_VIDEO_A;
                            rootShell = RootShell.INSTANCE;
                            str2 = str + target4 + "' '" + slotTarget + "' && chmod 644 '" + slotTarget + "'";
                            fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager4;
                            $result2 = null;
                            fakeHalManager$applyWithTransform$12.L$1 = null;
                            fakeHalManager$applyWithTransform$12.label = 5;
                            if (rootShell.exec(str2, fakeHalManager$applyWithTransform$12) != obj) {
                                return obj;
                            }
                            fakeHalManager5 = fakeHalManager4;
                            fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                            fakeHalManager$applyWithTransform$12.label = 6;
                            if (fakeHalManager5.mirrorActiveBackToFront(fakeHalManager$applyWithTransform$12) == obj) {
                                return obj;
                            }
                            Object obj3 = $result2;
                            fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                            fakeHalManager$applyWithTransform$12.L$1 = obj3;
                            fakeHalManager$applyWithTransform$12.L$2 = obj3;
                            fakeHalManager$applyWithTransform$12.L$3 = obj3;
                            fakeHalManager$applyWithTransform$12.L$4 = obj3;
                            fakeHalManager$applyWithTransform$12.label = 7;
                            obj2 = obj3;
                            if (writeConfig$default(fakeHalManager5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$applyWithTransform$12, 262143, null) == obj) {
                                return obj;
                            }
                            fakeHalManager$applyWithTransform$12.L$0 = obj2;
                            fakeHalManager$applyWithTransform$12.label = 8;
                            $result3 = fakeHalManager5.start(fakeHalManager$applyWithTransform$12);
                            return $result3 == obj ? obj : $result3;
                        }
                        $result2 = null;
                        fakeHalManager5 = fakeHalManager4;
                        Object obj32 = $result2;
                        fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                        fakeHalManager$applyWithTransform$12.L$1 = obj32;
                        fakeHalManager$applyWithTransform$12.L$2 = obj32;
                        fakeHalManager$applyWithTransform$12.L$3 = obj32;
                        fakeHalManager$applyWithTransform$12.L$4 = obj32;
                        fakeHalManager$applyWithTransform$12.label = 7;
                        obj2 = obj32;
                        if (writeConfig$default(fakeHalManager5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$applyWithTransform$12, 262143, null) == obj) {
                        }
                        fakeHalManager$applyWithTransform$12.L$0 = obj2;
                        fakeHalManager$applyWithTransform$12.label = 8;
                        $result3 = fakeHalManager5.start(fakeHalManager$applyWithTransform$12);
                        if ($result3 == obj) {
                        }
                        break;
                    case 1:
                        tempMp4 = (File) fakeHalManager$applyWithTransform$1.L$3;
                        target3 = (String) fakeHalManager$applyWithTransform$1.L$2;
                        target2 = (String) fakeHalManager$applyWithTransform$1.L$1;
                        fakeHalManager2 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        convert = $result3;
                        $result = convert;
                        z = false;
                        str = "cp '";
                        ok = ((Boolean) convert).booleanValue();
                        if (ok) {
                        }
                        break;
                    case 2:
                        tempMp4 = (File) fakeHalManager$applyWithTransform$1.L$3;
                        target3 = (String) fakeHalManager$applyWithTransform$1.L$2;
                        target2 = (String) fakeHalManager$applyWithTransform$1.L$1;
                        fakeHalManager3 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        exec2 = $result3;
                        $result = exec2;
                        z = false;
                        str = "cp '";
                        result = (RootShell.Result) exec2;
                        tempMp4.delete();
                        z2 = z;
                        if (StringsKt.contains$default(result.getOutput(), "OK", z2, 2, (Object) null)) {
                        }
                        break;
                    case 3:
                        File transcodedFile2 = (File) fakeHalManager$applyWithTransform$1.L$4;
                        tempFile = (File) fakeHalManager$applyWithTransform$1.L$3;
                        target = (String) fakeHalManager$applyWithTransform$1.L$2;
                        camera2 = (String) fakeHalManager$applyWithTransform$1.L$1;
                        fakeHalManager = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result3);
                            fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                            charSequence = "OK";
                            $result = $result3;
                            obj = coroutine_suspended;
                            transcodedFile = transcodedFile2;
                            exec = $result;
                            str = "cp '";
                            result2 = (RootShell.Result) exec;
                            tempFile.delete();
                            transcodedFile.delete();
                            if (StringsKt.contains$default((CharSequence) result2.getOutput(), charSequence, false, 2, (Object) null)) {
                            }
                        } catch (Exception e8) {
                            break;
                        }
                        break;
                    case 4:
                        target4 = (String) fakeHalManager$applyWithTransform$1.L$1;
                        fakeHalManager4 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                        $result = $result3;
                        obj = coroutine_suspended;
                        readActiveSlot = $result;
                        str = "cp '";
                        String activeSlot2 = (String) readActiveSlot;
                        String slotTarget2 = !Intrinsics.areEqual(activeSlot2, "B") ? BACK_VIDEO_B : BACK_VIDEO_A;
                        rootShell = RootShell.INSTANCE;
                        str2 = str + target4 + "' '" + slotTarget2 + "' && chmod 644 '" + slotTarget2 + "'";
                        fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager4;
                        $result2 = null;
                        fakeHalManager$applyWithTransform$12.L$1 = null;
                        fakeHalManager$applyWithTransform$12.label = 5;
                        if (rootShell.exec(str2, fakeHalManager$applyWithTransform$12) != obj) {
                        }
                        break;
                    case 5:
                        fakeHalManager5 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                        $result = $result3;
                        $result2 = null;
                        obj = coroutine_suspended;
                        fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                        fakeHalManager$applyWithTransform$12.label = 6;
                        if (fakeHalManager5.mirrorActiveBackToFront(fakeHalManager$applyWithTransform$12) == obj) {
                        }
                        Object obj322 = $result2;
                        fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                        fakeHalManager$applyWithTransform$12.L$1 = obj322;
                        fakeHalManager$applyWithTransform$12.L$2 = obj322;
                        fakeHalManager$applyWithTransform$12.L$3 = obj322;
                        fakeHalManager$applyWithTransform$12.L$4 = obj322;
                        fakeHalManager$applyWithTransform$12.label = 7;
                        obj2 = obj322;
                        if (writeConfig$default(fakeHalManager5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$applyWithTransform$12, 262143, null) == obj) {
                        }
                        fakeHalManager$applyWithTransform$12.L$0 = obj2;
                        fakeHalManager$applyWithTransform$12.label = 8;
                        $result3 = fakeHalManager5.start(fakeHalManager$applyWithTransform$12);
                        if ($result3 == obj) {
                        }
                        break;
                    case 6:
                        fakeHalManager5 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                        $result = $result3;
                        $result2 = null;
                        obj = coroutine_suspended;
                        Object obj3222 = $result2;
                        fakeHalManager$applyWithTransform$12.L$0 = fakeHalManager5;
                        fakeHalManager$applyWithTransform$12.L$1 = obj3222;
                        fakeHalManager$applyWithTransform$12.L$2 = obj3222;
                        fakeHalManager$applyWithTransform$12.L$3 = obj3222;
                        fakeHalManager$applyWithTransform$12.L$4 = obj3222;
                        fakeHalManager$applyWithTransform$12.label = 7;
                        obj2 = obj3222;
                        if (writeConfig$default(fakeHalManager5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$applyWithTransform$12, 262143, null) == obj) {
                        }
                        fakeHalManager$applyWithTransform$12.L$0 = obj2;
                        fakeHalManager$applyWithTransform$12.label = 8;
                        $result3 = fakeHalManager5.start(fakeHalManager$applyWithTransform$12);
                        if ($result3 == obj) {
                        }
                        break;
                    case 7:
                        fakeHalManager5 = (FakeHalManager) fakeHalManager$applyWithTransform$1.L$0;
                        ResultKt.throwOnFailure($result3);
                        fakeHalManager$applyWithTransform$12 = fakeHalManager$applyWithTransform$1;
                        obj = coroutine_suspended;
                        obj2 = null;
                        fakeHalManager$applyWithTransform$12.L$0 = obj2;
                        fakeHalManager$applyWithTransform$12.label = 8;
                        $result3 = fakeHalManager5.start(fakeHalManager$applyWithTransform$12);
                        if ($result3 == obj) {
                        }
                        break;
                    case 8:
                        ResultKt.throwOnFailure($result3);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$applyWithTransform$1 = new FakeHalManager$applyWithTransform$1(this, continuation);
        Object $result32 = fakeHalManager$applyWithTransform$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$applyWithTransform$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00bc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateNoiseLevel(float level, Continuation<? super Boolean> continuation) {
        FakeHalManager$updateNoiseLevel$1 fakeHalManager$updateNoiseLevel$1;
        Object isProviderRunning;
        FakeHalManager fakeHalManager;
        float level2;
        Float boxFloat;
        FakeHalManager fakeHalManager2;
        boolean z;
        Object $result;
        boolean wasRunning;
        FakeHalManager fakeHalManager3;
        if (continuation instanceof FakeHalManager$updateNoiseLevel$1) {
            FakeHalManager$updateNoiseLevel$1 fakeHalManager$updateNoiseLevel$12 = (FakeHalManager$updateNoiseLevel$1) continuation;
            if ((fakeHalManager$updateNoiseLevel$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$updateNoiseLevel$12.label -= Integer.MIN_VALUE;
                fakeHalManager$updateNoiseLevel$1 = fakeHalManager$updateNoiseLevel$12;
                Object $result2 = fakeHalManager$updateNoiseLevel$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$updateNoiseLevel$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        fakeHalManager$updateNoiseLevel$1.L$0 = this;
                        fakeHalManager$updateNoiseLevel$1.F$0 = level;
                        fakeHalManager$updateNoiseLevel$1.label = 1;
                        isProviderRunning = isProviderRunning(fakeHalManager$updateNoiseLevel$1);
                        if (isProviderRunning == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        level2 = level;
                        boolean wasRunning2 = ((Boolean) isProviderRunning).booleanValue();
                        boxFloat = Boxing.boxFloat(level2);
                        fakeHalManager2 = fakeHalManager;
                        z = true;
                        fakeHalManager$updateNoiseLevel$1.L$0 = fakeHalManager2;
                        fakeHalManager$updateNoiseLevel$1.Z$0 = wasRunning2;
                        fakeHalManager$updateNoiseLevel$1.label = 2;
                        $result = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager2, boxFloat, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateNoiseLevel$1, 262142, null) != $result) {
                            return $result;
                        }
                        wasRunning = wasRunning2;
                        fakeHalManager3 = fakeHalManager2;
                        boolean z2 = !wasRunning ? z : false;
                        fakeHalManager$updateNoiseLevel$1.L$0 = null;
                        fakeHalManager$updateNoiseLevel$1.label = 3;
                        Object restartIfNeeded = fakeHalManager3.restartIfNeeded(z2, fakeHalManager$updateNoiseLevel$1);
                        return restartIfNeeded != $result ? $result : restartIfNeeded;
                    case 1:
                        float level3 = fakeHalManager$updateNoiseLevel$1.F$0;
                        FakeHalManager fakeHalManager4 = (FakeHalManager) fakeHalManager$updateNoiseLevel$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        isProviderRunning = $result2;
                        level2 = level3;
                        fakeHalManager = fakeHalManager4;
                        boolean wasRunning22 = ((Boolean) isProviderRunning).booleanValue();
                        boxFloat = Boxing.boxFloat(level2);
                        fakeHalManager2 = fakeHalManager;
                        z = true;
                        fakeHalManager$updateNoiseLevel$1.L$0 = fakeHalManager2;
                        fakeHalManager$updateNoiseLevel$1.Z$0 = wasRunning22;
                        fakeHalManager$updateNoiseLevel$1.label = 2;
                        $result = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager2, boxFloat, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateNoiseLevel$1, 262142, null) != $result) {
                        }
                        break;
                    case 2:
                        wasRunning = fakeHalManager$updateNoiseLevel$1.Z$0;
                        fakeHalManager3 = (FakeHalManager) fakeHalManager$updateNoiseLevel$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        z = true;
                        if (!wasRunning) {
                        }
                        fakeHalManager$updateNoiseLevel$1.L$0 = null;
                        fakeHalManager$updateNoiseLevel$1.label = 3;
                        Object restartIfNeeded2 = fakeHalManager3.restartIfNeeded(z2, fakeHalManager$updateNoiseLevel$1);
                        if (restartIfNeeded2 != $result) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result2);
                        return $result2;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$updateNoiseLevel$1 = new FakeHalManager$updateNoiseLevel$1(this, continuation);
        Object $result22 = fakeHalManager$updateNoiseLevel$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$updateNoiseLevel$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateIsoRange(int min, int max, Continuation<? super Boolean> continuation) {
        FakeHalManager$updateIsoRange$1 fakeHalManager$updateIsoRange$1;
        Object isProviderRunning;
        FakeHalManager fakeHalManager;
        int max2;
        int min2;
        Integer boxInt;
        Integer boxInt2;
        FakeHalManager fakeHalManager2;
        boolean z;
        Object $result;
        boolean wasRunning;
        FakeHalManager fakeHalManager3;
        if (continuation instanceof FakeHalManager$updateIsoRange$1) {
            FakeHalManager$updateIsoRange$1 fakeHalManager$updateIsoRange$12 = (FakeHalManager$updateIsoRange$1) continuation;
            if ((fakeHalManager$updateIsoRange$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$updateIsoRange$12.label -= Integer.MIN_VALUE;
                fakeHalManager$updateIsoRange$1 = fakeHalManager$updateIsoRange$12;
                Object $result2 = fakeHalManager$updateIsoRange$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$updateIsoRange$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        fakeHalManager$updateIsoRange$1.L$0 = this;
                        fakeHalManager$updateIsoRange$1.I$0 = min;
                        fakeHalManager$updateIsoRange$1.I$1 = max;
                        fakeHalManager$updateIsoRange$1.label = 1;
                        isProviderRunning = isProviderRunning(fakeHalManager$updateIsoRange$1);
                        if (isProviderRunning == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        max2 = max;
                        min2 = min;
                        boolean wasRunning2 = ((Boolean) isProviderRunning).booleanValue();
                        boxInt = Boxing.boxInt(min2);
                        boxInt2 = Boxing.boxInt(max2);
                        fakeHalManager2 = fakeHalManager;
                        z = true;
                        fakeHalManager$updateIsoRange$1.L$0 = fakeHalManager2;
                        fakeHalManager$updateIsoRange$1.Z$0 = wasRunning2;
                        fakeHalManager$updateIsoRange$1.label = 2;
                        $result = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager2, null, boxInt, boxInt2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateIsoRange$1, 262137, null) != $result) {
                            return $result;
                        }
                        wasRunning = wasRunning2;
                        fakeHalManager3 = fakeHalManager2;
                        boolean z2 = !wasRunning ? z : false;
                        fakeHalManager$updateIsoRange$1.L$0 = null;
                        fakeHalManager$updateIsoRange$1.label = 3;
                        Object restartIfNeeded = fakeHalManager3.restartIfNeeded(z2, fakeHalManager$updateIsoRange$1);
                        return restartIfNeeded != $result ? $result : restartIfNeeded;
                    case 1:
                        int max3 = fakeHalManager$updateIsoRange$1.I$1;
                        int min3 = fakeHalManager$updateIsoRange$1.I$0;
                        FakeHalManager fakeHalManager4 = (FakeHalManager) fakeHalManager$updateIsoRange$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        isProviderRunning = $result2;
                        max2 = max3;
                        min2 = min3;
                        fakeHalManager = fakeHalManager4;
                        boolean wasRunning22 = ((Boolean) isProviderRunning).booleanValue();
                        boxInt = Boxing.boxInt(min2);
                        boxInt2 = Boxing.boxInt(max2);
                        fakeHalManager2 = fakeHalManager;
                        z = true;
                        fakeHalManager$updateIsoRange$1.L$0 = fakeHalManager2;
                        fakeHalManager$updateIsoRange$1.Z$0 = wasRunning22;
                        fakeHalManager$updateIsoRange$1.label = 2;
                        $result = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager2, null, boxInt, boxInt2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateIsoRange$1, 262137, null) != $result) {
                        }
                        break;
                    case 2:
                        wasRunning = fakeHalManager$updateIsoRange$1.Z$0;
                        fakeHalManager3 = (FakeHalManager) fakeHalManager$updateIsoRange$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = coroutine_suspended;
                        z = true;
                        if (!wasRunning) {
                        }
                        fakeHalManager$updateIsoRange$1.L$0 = null;
                        fakeHalManager$updateIsoRange$1.label = 3;
                        Object restartIfNeeded2 = fakeHalManager3.restartIfNeeded(z2, fakeHalManager$updateIsoRange$1);
                        if (restartIfNeeded2 != $result) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result2);
                        return $result2;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$updateIsoRange$1 = new FakeHalManager$updateIsoRange$1(this, continuation);
        Object $result22 = fakeHalManager$updateIsoRange$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$updateIsoRange$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateQrMode(boolean enabled, Continuation<? super Boolean> continuation) {
        FakeHalManager$updateQrMode$1 fakeHalManager$updateQrMode$1;
        Object isProviderRunning;
        FakeHalManager fakeHalManager;
        boolean wasRunning;
        Boolean boxBoolean;
        boolean z;
        Object obj;
        boolean wasRunning2;
        FakeHalManager fakeHalManager2;
        if (continuation instanceof FakeHalManager$updateQrMode$1) {
            FakeHalManager$updateQrMode$1 fakeHalManager$updateQrMode$12 = (FakeHalManager$updateQrMode$1) continuation;
            if ((fakeHalManager$updateQrMode$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$updateQrMode$12.label -= Integer.MIN_VALUE;
                fakeHalManager$updateQrMode$1 = fakeHalManager$updateQrMode$12;
                Object $result = fakeHalManager$updateQrMode$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$updateQrMode$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$updateQrMode$1.L$0 = this;
                        fakeHalManager$updateQrMode$1.Z$0 = enabled;
                        fakeHalManager$updateQrMode$1.label = 1;
                        isProviderRunning = isProviderRunning(fakeHalManager$updateQrMode$1);
                        if (isProviderRunning == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        wasRunning = enabled;
                        boolean wasRunning3 = ((Boolean) isProviderRunning).booleanValue();
                        boxBoolean = Boxing.boxBoolean(!wasRunning);
                        fakeHalManager$updateQrMode$1.L$0 = fakeHalManager;
                        fakeHalManager$updateQrMode$1.Z$0 = wasRunning3;
                        fakeHalManager$updateQrMode$1.label = 2;
                        FakeHalManager fakeHalManager3 = fakeHalManager;
                        z = true;
                        obj = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager, null, null, null, null, null, null, boxBoolean, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateQrMode$1, 262079, null) != obj) {
                            return obj;
                        }
                        wasRunning2 = wasRunning3;
                        fakeHalManager2 = fakeHalManager3;
                        boolean wasRunning4 = !wasRunning2 ? z : false;
                        fakeHalManager$updateQrMode$1.L$0 = null;
                        fakeHalManager$updateQrMode$1.label = 3;
                        Object restartIfNeeded = fakeHalManager2.restartIfNeeded(wasRunning4, fakeHalManager$updateQrMode$1);
                        return restartIfNeeded != obj ? obj : restartIfNeeded;
                    case 1:
                        wasRunning = fakeHalManager$updateQrMode$1.Z$0;
                        FakeHalManager fakeHalManager4 = (FakeHalManager) fakeHalManager$updateQrMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        isProviderRunning = $result;
                        fakeHalManager = fakeHalManager4;
                        boolean wasRunning32 = ((Boolean) isProviderRunning).booleanValue();
                        boxBoolean = Boxing.boxBoolean(!wasRunning);
                        fakeHalManager$updateQrMode$1.L$0 = fakeHalManager;
                        fakeHalManager$updateQrMode$1.Z$0 = wasRunning32;
                        fakeHalManager$updateQrMode$1.label = 2;
                        FakeHalManager fakeHalManager32 = fakeHalManager;
                        z = true;
                        obj = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager, null, null, null, null, null, null, boxBoolean, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateQrMode$1, 262079, null) != obj) {
                        }
                        break;
                    case 2:
                        wasRunning2 = fakeHalManager$updateQrMode$1.Z$0;
                        FakeHalManager fakeHalManager5 = (FakeHalManager) fakeHalManager$updateQrMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager2 = fakeHalManager5;
                        z = true;
                        obj = coroutine_suspended;
                        if (!wasRunning2) {
                        }
                        fakeHalManager$updateQrMode$1.L$0 = null;
                        fakeHalManager$updateQrMode$1.label = 3;
                        Object restartIfNeeded2 = fakeHalManager2.restartIfNeeded(wasRunning4, fakeHalManager$updateQrMode$1);
                        if (restartIfNeeded2 != obj) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$updateQrMode$1 = new FakeHalManager$updateQrMode$1(this, continuation);
        Object $result2 = fakeHalManager$updateQrMode$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$updateQrMode$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x010c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateSensorSettings(float f, int i, int isoMax, boolean qrMode, Continuation<? super Boolean> continuation) {
        FakeHalManager$updateSensorSettings$1 fakeHalManager$updateSensorSettings$1;
        int isoMin;
        float noiseLevel;
        Object isProviderRunning;
        FakeHalManager fakeHalManager;
        boolean wasRunning;
        int isoMax2;
        Float boxFloat;
        Integer boxInt;
        Integer boxInt2;
        Boolean boxBoolean;
        boolean z;
        Object obj;
        boolean wasRunning2;
        FakeHalManager fakeHalManager2;
        if (continuation instanceof FakeHalManager$updateSensorSettings$1) {
            FakeHalManager$updateSensorSettings$1 fakeHalManager$updateSensorSettings$12 = (FakeHalManager$updateSensorSettings$1) continuation;
            if ((fakeHalManager$updateSensorSettings$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$updateSensorSettings$12.label -= Integer.MIN_VALUE;
                fakeHalManager$updateSensorSettings$1 = fakeHalManager$updateSensorSettings$12;
                Object $result = fakeHalManager$updateSensorSettings$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$updateSensorSettings$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        isoMin = i;
                        noiseLevel = f;
                        fakeHalManager$updateSensorSettings$1.L$0 = this;
                        fakeHalManager$updateSensorSettings$1.F$0 = noiseLevel;
                        fakeHalManager$updateSensorSettings$1.I$0 = isoMin;
                        fakeHalManager$updateSensorSettings$1.I$1 = isoMax;
                        fakeHalManager$updateSensorSettings$1.Z$0 = qrMode;
                        fakeHalManager$updateSensorSettings$1.label = 1;
                        isProviderRunning = isProviderRunning(fakeHalManager$updateSensorSettings$1);
                        if (isProviderRunning == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager = this;
                        wasRunning = qrMode;
                        isoMax2 = isoMax;
                        boolean wasRunning3 = ((Boolean) isProviderRunning).booleanValue();
                        boxFloat = Boxing.boxFloat(RangesKt.coerceIn(noiseLevel, 0.0f, 3.0f));
                        boxInt = Boxing.boxInt(RangesKt.coerceIn(isoMin, 50, 6400));
                        boxInt2 = Boxing.boxInt(RangesKt.coerceIn(isoMax2, 50, 6400));
                        boxBoolean = Boxing.boxBoolean(!wasRunning);
                        fakeHalManager$updateSensorSettings$1.L$0 = fakeHalManager;
                        fakeHalManager$updateSensorSettings$1.Z$0 = wasRunning3;
                        fakeHalManager$updateSensorSettings$1.label = 2;
                        FakeHalManager fakeHalManager3 = fakeHalManager;
                        z = true;
                        obj = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager, boxFloat, boxInt, boxInt2, null, null, null, boxBoolean, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateSensorSettings$1, 262072, null) != obj) {
                            return obj;
                        }
                        wasRunning2 = wasRunning3;
                        fakeHalManager2 = fakeHalManager3;
                        boolean wasRunning4 = !wasRunning2 ? z : false;
                        fakeHalManager$updateSensorSettings$1.L$0 = null;
                        fakeHalManager$updateSensorSettings$1.label = 3;
                        Object restartIfNeeded = fakeHalManager2.restartIfNeeded(wasRunning4, fakeHalManager$updateSensorSettings$1);
                        return restartIfNeeded != obj ? obj : restartIfNeeded;
                    case 1:
                        wasRunning = fakeHalManager$updateSensorSettings$1.Z$0;
                        isoMax2 = fakeHalManager$updateSensorSettings$1.I$1;
                        isoMin = fakeHalManager$updateSensorSettings$1.I$0;
                        noiseLevel = fakeHalManager$updateSensorSettings$1.F$0;
                        fakeHalManager = (FakeHalManager) fakeHalManager$updateSensorSettings$1.L$0;
                        ResultKt.throwOnFailure($result);
                        isProviderRunning = $result;
                        boolean wasRunning32 = ((Boolean) isProviderRunning).booleanValue();
                        boxFloat = Boxing.boxFloat(RangesKt.coerceIn(noiseLevel, 0.0f, 3.0f));
                        boxInt = Boxing.boxInt(RangesKt.coerceIn(isoMin, 50, 6400));
                        boxInt2 = Boxing.boxInt(RangesKt.coerceIn(isoMax2, 50, 6400));
                        boxBoolean = Boxing.boxBoolean(!wasRunning);
                        fakeHalManager$updateSensorSettings$1.L$0 = fakeHalManager;
                        fakeHalManager$updateSensorSettings$1.Z$0 = wasRunning32;
                        fakeHalManager$updateSensorSettings$1.label = 2;
                        FakeHalManager fakeHalManager32 = fakeHalManager;
                        z = true;
                        obj = coroutine_suspended;
                        if (writeConfig$default(fakeHalManager, boxFloat, boxInt, boxInt2, null, null, null, boxBoolean, null, null, null, null, null, null, null, null, null, null, null, fakeHalManager$updateSensorSettings$1, 262072, null) != obj) {
                        }
                        break;
                    case 2:
                        wasRunning2 = fakeHalManager$updateSensorSettings$1.Z$0;
                        FakeHalManager fakeHalManager4 = (FakeHalManager) fakeHalManager$updateSensorSettings$1.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager2 = fakeHalManager4;
                        z = true;
                        obj = coroutine_suspended;
                        if (!wasRunning2) {
                        }
                        fakeHalManager$updateSensorSettings$1.L$0 = null;
                        fakeHalManager$updateSensorSettings$1.label = 3;
                        Object restartIfNeeded2 = fakeHalManager2.restartIfNeeded(wasRunning4, fakeHalManager$updateSensorSettings$1);
                        if (restartIfNeeded2 != obj) {
                        }
                        break;
                    case 3:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$updateSensorSettings$1 = new FakeHalManager$updateSensorSettings$1(this, continuation);
        Object $result2 = fakeHalManager$updateSensorSettings$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$updateSensorSettings$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateGeoSpoof(boolean enabled, String lat, String lon, Continuation<? super Boolean> continuation) {
        FakeHalManager$updateGeoSpoof$1 fakeHalManager$updateGeoSpoof$1;
        FakeHalManager$updateGeoSpoof$1 fakeHalManager$updateGeoSpoof$12;
        String d;
        String safeLon;
        Object exec;
        if (continuation instanceof FakeHalManager$updateGeoSpoof$1) {
            fakeHalManager$updateGeoSpoof$1 = (FakeHalManager$updateGeoSpoof$1) continuation;
            if ((fakeHalManager$updateGeoSpoof$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$updateGeoSpoof$1.label -= Integer.MIN_VALUE;
                fakeHalManager$updateGeoSpoof$12 = fakeHalManager$updateGeoSpoof$1;
                Object $result = fakeHalManager$updateGeoSpoof$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$updateGeoSpoof$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Double doubleOrNull = StringsKt.toDoubleOrNull(lat);
                        if (doubleOrNull == null || (d = Boxing.boxDouble(RangesKt.coerceIn(doubleOrNull.doubleValue(), -90.0d, 90.0d)).toString()) == null) {
                            return Boxing.boxBoolean(false);
                        }
                        Double doubleOrNull2 = StringsKt.toDoubleOrNull(lon);
                        if (doubleOrNull2 == null || (safeLon = Boxing.boxDouble(RangesKt.coerceIn(doubleOrNull2.doubleValue(), -180.0d, 180.0d)).toString()) == null) {
                            return Boxing.boxBoolean(false);
                        }
                        String command = StringsKt.trimIndent("\n            mkdir -p /data/local/tmp/spoofkit\n            touch /data/local/tmp/spoofkit/active_config\n            update_key() {\n              key=\"$1\"\n              value=\"$2\"\n              file=/data/local/tmp/spoofkit/active_config\n              if grep -q \"^$key=\" \"$file\" 2>/dev/null; then\n                sed -i \"s|^$key=.*|$key=$value|\" \"$file\"\n              else\n                echo \"$key=$value\" >> \"$file\"\n              fi\n            }\n            update_key gps_enabled '" + (enabled ? "true" : "false") + "'\n            update_key gps_lat '" + d + "'\n            update_key gps_lon '" + safeLon + "'\n            update_key gps_alt '0.0'\n            update_key gps_acc '5.0'\n            update_key gps_speed '0.0'\n            update_key gps_bearing '0.0'\n            chmod 644 /data/local/tmp/spoofkit/active_config\n            chcon u:object_r:shell_data_file:s0 /data/local/tmp/spoofkit/active_config 2>/dev/null\n            echo OK\n            ");
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$updateGeoSpoof$12.label = 1;
                        exec = rootShell.exec(command, fakeHalManager$updateGeoSpoof$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "OK", false, 2, (Object) null));
            }
        }
        fakeHalManager$updateGeoSpoof$1 = new FakeHalManager$updateGeoSpoof$1(this, continuation);
        fakeHalManager$updateGeoSpoof$12 = fakeHalManager$updateGeoSpoof$1;
        Object $result2 = fakeHalManager$updateGeoSpoof$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$updateGeoSpoof$12.label) {
        }
        return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "OK", false, 2, (Object) null));
    }

    public final Object setLiveTransform(int rotation, boolean mirrorH, boolean mirrorV, Continuation<? super Unit> continuation) {
        int r = ((rotation % 360) + 360) % 360;
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, null, null, null, null, null, Boxing.boxInt(r), Boxing.boxBoolean(mirrorH), Boxing.boxBoolean(mirrorV), null, null, continuation, 204799, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setGyroEnabled(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, continuation, 196607, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setBackRotate180(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, null, null, null, null, null, null, null, null, null, continuation, 262015, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setFrontRotate180(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, null, null, null, null, null, null, null, null, continuation, 261887, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setFrontMirror(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, null, null, null, null, null, null, null, continuation, 261631, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setChromeFrontRotate180(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, null, null, null, null, null, null, continuation, 261119, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    public final Object setChromeFrontMirror(boolean enabled, Continuation<? super Unit> continuation) {
        Object writeConfig$default = writeConfig$default(this, null, null, null, null, null, null, null, null, null, null, null, Boxing.boxBoolean(enabled), null, null, null, null, null, null, continuation, 260095, null);
        return writeConfig$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeConfig$default : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readTransformState(Continuation<? super TransformState> continuation) {
        FakeHalManager$readTransformState$1 fakeHalManager$readTransformState$1;
        FakeHalManager$readTransformState$1 fakeHalManager$readTransformState$12;
        Object exec;
        Iterator<T> it;
        Object obj;
        String substringAfter$default;
        String obj2;
        Integer intOrNull;
        if (continuation instanceof FakeHalManager$readTransformState$1) {
            fakeHalManager$readTransformState$1 = (FakeHalManager$readTransformState$1) continuation;
            if ((fakeHalManager$readTransformState$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$readTransformState$1.label -= Integer.MIN_VALUE;
                fakeHalManager$readTransformState$12 = fakeHalManager$readTransformState$1;
                Object $result = fakeHalManager$readTransformState$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$readTransformState$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$readTransformState$12.label = 1;
                        exec = rootShell.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$readTransformState$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String conf = ((RootShell.Result) exec).getOutput();
                boolean readTransformState$flag = readTransformState$flag(conf, "back_rotate_180", false);
                boolean readTransformState$flag2 = readTransformState$flag(conf, "front_rotate_180", true);
                boolean readTransformState$flag3 = readTransformState$flag(conf, "front_mirror", true);
                boolean readTransformState$flag4 = readTransformState$flag(conf, "chrome_front_rotate_180", false);
                boolean readTransformState$flag5 = readTransformState$flag(conf, "chrome_front_mirror", false);
                it = StringsKt.lines(conf).iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj = null;
                    } else {
                        obj = it.next();
                        String it2 = (String) obj;
                        if (StringsKt.startsWith$default(it2, "preview_rotation=", false, 2, (Object) null)) {
                        }
                    }
                }
                String str = (String) obj;
                return new TransformState(readTransformState$flag, readTransformState$flag2, readTransformState$flag3, readTransformState$flag4, readTransformState$flag5, (str != null || (substringAfter$default = StringsKt.substringAfter$default(str, "=", (String) null, 2, (Object) null)) == null || (obj2 = StringsKt.trim((CharSequence) substringAfter$default).toString()) == null || (intOrNull = StringsKt.toIntOrNull(obj2)) == null) ? 0 : intOrNull.intValue());
            }
        }
        fakeHalManager$readTransformState$1 = new FakeHalManager$readTransformState$1(this, continuation);
        fakeHalManager$readTransformState$12 = fakeHalManager$readTransformState$1;
        Object $result2 = fakeHalManager$readTransformState$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$readTransformState$12.label) {
        }
        String conf2 = ((RootShell.Result) exec).getOutput();
        boolean readTransformState$flag6 = readTransformState$flag(conf2, "back_rotate_180", false);
        boolean readTransformState$flag22 = readTransformState$flag(conf2, "front_rotate_180", true);
        boolean readTransformState$flag32 = readTransformState$flag(conf2, "front_mirror", true);
        boolean readTransformState$flag42 = readTransformState$flag(conf2, "chrome_front_rotate_180", false);
        boolean readTransformState$flag52 = readTransformState$flag(conf2, "chrome_front_mirror", false);
        it = StringsKt.lines(conf2).iterator();
        while (true) {
            if (it.hasNext()) {
            }
        }
        String str2 = (String) obj;
        return new TransformState(readTransformState$flag6, readTransformState$flag22, readTransformState$flag32, readTransformState$flag42, readTransformState$flag52, (str2 != null || (substringAfter$default = StringsKt.substringAfter$default(str2, "=", (String) null, 2, (Object) null)) == null || (obj2 = StringsKt.trim((CharSequence) substringAfter$default).toString()) == null || (intOrNull = StringsKt.toIntOrNull(obj2)) == null) ? 0 : intOrNull.intValue());
    }

    static /* synthetic */ boolean readTransformState$flag$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return readTransformState$flag(str, str2, z);
    }

    private static final boolean readTransformState$flag(String conf, String key, boolean z) {
        Object obj;
        String substringAfter$default;
        String it;
        Iterator<T> it2 = StringsKt.lines(conf).iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (StringsKt.startsWith$default((String) obj, key + "=", false, 2, (Object) null)) {
                break;
            }
        }
        String str = (String) obj;
        if (str != null && (substringAfter$default = StringsKt.substringAfter$default(str, "=", (String) null, 2, (Object) null)) != null && (it = StringsKt.trim((CharSequence) substringAfter$default).toString()) != null) {
            return INSTANCE.parseConfigBool(it, z);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0348 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object writeConfig(Float f, Integer num, Integer isoMax, String str, String backSlotB, String activeSlot, Boolean qrMode, Boolean backRotate180, Boolean frontRotate180, Boolean frontMirror, Boolean chromeFrontRotate180, Boolean chromeFrontMirror, Float gyroStrength, Integer previewRotation, Boolean previewMirrorH, Boolean previewMirrorV, Boolean gyroEnabled, String streamCamera, Continuation<? super Unit> continuation) {
        FakeHalManager$writeConfig$1 fakeHalManager$writeConfig$1;
        Integer isoMin;
        String backSlotA;
        Float noiseLevel;
        Boolean chromeFrontMirror2;
        Boolean chromeFrontRotate1802;
        Boolean frontMirror2;
        Boolean frontRotate1802;
        Boolean backRotate1802;
        FakeHalManager fakeHalManager;
        Boolean chromeFrontMirror3;
        Boolean frontMirror3;
        Boolean chromeFrontRotate1803;
        Boolean frontRotate1803;
        Integer previewRotation2;
        Float gyroStrength2;
        String backSlotB2;
        String streamCamera2;
        String activeSlot2;
        Integer isoMax2;
        Iterator<String> it;
        if (continuation instanceof FakeHalManager$writeConfig$1) {
            FakeHalManager$writeConfig$1 fakeHalManager$writeConfig$12 = (FakeHalManager$writeConfig$1) continuation;
            if ((fakeHalManager$writeConfig$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$writeConfig$12.label -= Integer.MIN_VALUE;
                fakeHalManager$writeConfig$1 = fakeHalManager$writeConfig$12;
                Object $result = fakeHalManager$writeConfig$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$writeConfig$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        isoMin = num;
                        backSlotA = str;
                        noiseLevel = f;
                        RootShell rootShell = RootShell.INSTANCE;
                        fakeHalManager$writeConfig$1.L$0 = this;
                        fakeHalManager$writeConfig$1.L$1 = noiseLevel;
                        fakeHalManager$writeConfig$1.L$2 = isoMin;
                        fakeHalManager$writeConfig$1.L$3 = isoMax;
                        fakeHalManager$writeConfig$1.L$4 = backSlotA;
                        fakeHalManager$writeConfig$1.L$5 = backSlotB;
                        fakeHalManager$writeConfig$1.L$6 = activeSlot;
                        fakeHalManager$writeConfig$1.L$7 = qrMode;
                        fakeHalManager$writeConfig$1.L$8 = backRotate180;
                        fakeHalManager$writeConfig$1.L$9 = frontRotate180;
                        fakeHalManager$writeConfig$1.L$10 = frontMirror;
                        fakeHalManager$writeConfig$1.L$11 = chromeFrontRotate180;
                        fakeHalManager$writeConfig$1.L$12 = chromeFrontMirror;
                        fakeHalManager$writeConfig$1.L$13 = gyroStrength;
                        fakeHalManager$writeConfig$1.L$14 = previewRotation;
                        fakeHalManager$writeConfig$1.L$15 = previewMirrorH;
                        fakeHalManager$writeConfig$1.L$16 = previewMirrorV;
                        fakeHalManager$writeConfig$1.L$17 = gyroEnabled;
                        fakeHalManager$writeConfig$1.L$18 = streamCamera;
                        fakeHalManager$writeConfig$1.label = 1;
                        $result = rootShell.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$writeConfig$1);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        chromeFrontMirror2 = chromeFrontMirror;
                        chromeFrontRotate1802 = chromeFrontRotate180;
                        frontMirror2 = frontMirror;
                        frontRotate1802 = frontRotate180;
                        backRotate1802 = backRotate180;
                        fakeHalManager = this;
                        chromeFrontMirror3 = qrMode;
                        frontMirror3 = previewMirrorV;
                        chromeFrontRotate1803 = gyroEnabled;
                        frontRotate1803 = previewMirrorH;
                        previewRotation2 = previewRotation;
                        gyroStrength2 = gyroStrength;
                        backSlotB2 = backSlotB;
                        streamCamera2 = streamCamera;
                        activeSlot2 = activeSlot;
                        isoMax2 = isoMax;
                        RootShell.Result confResult = (RootShell.Result) $result;
                        float curNoise = 1.0f;
                        int curIsoMin = 100;
                        int curIsoMax = 800;
                        String curSlotA = "";
                        String curSlotB = "";
                        String curActiveSlot = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        boolean curQrMode = false;
                        boolean curBackRotate180 = false;
                        boolean curFrontRotate180 = true;
                        boolean curFrontMirror = true;
                        boolean curChromeFrontRotate180 = false;
                        boolean curChromeFrontMirror = false;
                        int curPreviewRotation = 0;
                        boolean curPreviewMirrorH = false;
                        boolean curPreviewMirrorV = false;
                        boolean curGyroEnabled = false;
                        float curGyroStrength = 0.1f;
                        boolean curFrontUserSet = true;
                        it = StringsKt.lines(confResult.getOutput()).iterator();
                        String curStreamCamera = "both";
                        while (true) {
                            Object obj = coroutine_suspended;
                            if (it.hasNext()) {
                                FakeHalManager$writeConfig$1 fakeHalManager$writeConfig$13 = fakeHalManager$writeConfig$1;
                                Integer isoMax3 = isoMax2;
                                String backSlotA2 = backSlotA;
                                String backSlotB3 = backSlotB2;
                                String activeSlot3 = activeSlot2;
                                if (!CollectionsKt.listOf((Object[]) new String[]{"back", "front", "both"}).contains(curStreamCamera)) {
                                    curStreamCamera = "both";
                                }
                                if (streamCamera2 == null) {
                                    streamCamera2 = curStreamCamera;
                                }
                                String resolvedStreamCamera = streamCamera2;
                                if (chromeFrontMirror3 != null) {
                                    curQrMode = chromeFrontMirror3.booleanValue();
                                }
                                boolean resolvedQrMode = curQrMode;
                                if (previewRotation2 != null) {
                                    curPreviewRotation = previewRotation2.intValue();
                                }
                                int i = curPreviewRotation;
                                if (frontRotate1803 != null) {
                                    curPreviewMirrorH = frontRotate1803.booleanValue();
                                }
                                boolean resolvedPreviewH = curPreviewMirrorH;
                                if (frontMirror3 != null) {
                                    curPreviewMirrorV = frontMirror3.booleanValue();
                                }
                                boolean resolvedPreviewV = curPreviewMirrorV;
                                if (chromeFrontRotate1803 != null) {
                                    curGyroEnabled = chromeFrontRotate1803.booleanValue();
                                }
                                boolean resolvedGyro = curGyroEnabled;
                                if (backRotate1802 != null) {
                                    curBackRotate180 = backRotate1802.booleanValue();
                                }
                                boolean resolvedBackRotate180 = curBackRotate180;
                                if (frontRotate1802 != null) {
                                    curFrontRotate180 = frontRotate1802.booleanValue();
                                }
                                boolean resolvedFrontRotate180 = curFrontRotate180;
                                if (frontMirror2 != null) {
                                    curFrontMirror = frontMirror2.booleanValue();
                                }
                                boolean resolvedFrontMirror = curFrontMirror;
                                if (chromeFrontRotate1802 != null) {
                                    curChromeFrontRotate180 = chromeFrontRotate1802.booleanValue();
                                }
                                boolean resolvedChromeFrontRotate180 = curChromeFrontRotate180;
                                if (chromeFrontMirror2 != null) {
                                    curChromeFrontMirror = chromeFrontMirror2.booleanValue();
                                }
                                boolean resolvedChromeFrontMirror = curChromeFrontMirror;
                                if (gyroStrength2 != null) {
                                    curGyroStrength = gyroStrength2.floatValue();
                                }
                                float resolvedGyroStrength = curGyroStrength;
                                float floatValue = noiseLevel != null ? noiseLevel.floatValue() : curNoise;
                                int intValue = isoMin != null ? isoMin.intValue() : curIsoMin;
                                int intValue2 = isoMax3 != null ? isoMax3.intValue() : curIsoMax;
                                String str2 = backSlotA2 == null ? curSlotA : backSlotA2;
                                String resolvedStreamCamera2 = backSlotB3 == null ? curSlotB : backSlotB3;
                                String conf = StringsKt.trimIndent("\n            enabled=1\n            back_video=/data/local/tmp/fake_video.mp4\n            front_video=/data/local/tmp/fake_video_front.mp4\n            noise_level=" + floatValue + "\n            iso_min=" + intValue + "\n            iso_max=" + intValue2 + "\n            back_slot_a=" + str2 + "\n            back_slot_b=" + resolvedStreamCamera2 + "\n            active_slot=" + (activeSlot3 == null ? curActiveSlot : activeSlot3) + "\n            qr_mode=" + (resolvedQrMode ? 1 : 0) + "\n            back_rotate_180=" + (resolvedBackRotate180 ? 1 : 0) + "\n            front_rotate_180=" + (resolvedFrontRotate180 ? 1 : 0) + "\n            front_mirror=" + (resolvedFrontMirror ? 1 : 0) + "\n            chrome_front_rotate_180=" + (resolvedChromeFrontRotate180 ? 1 : 0) + "\n            chrome_front_mirror=" + (resolvedChromeFrontMirror ? 1 : 0) + "\n            preview_rotation=" + i + "\n            preview_mirror_h=" + (resolvedPreviewH ? 1 : 0) + "\n            preview_mirror_v=" + (resolvedPreviewV ? 1 : 0) + "\n            gyro_enabled=" + (resolvedGyro ? 1 : 0) + "\n            gyro_strength=" + resolvedGyroStrength + "\n            front_user_set=" + (curFrontUserSet ? 1 : 0) + "\n            sensor_orientation=90\n            pre_normalized=0\n            stream_camera=" + resolvedStreamCamera + "\n            ");
                                String conf2 = "cat > /data/local/tmp/fakehal.conf <<'EOF'\n" + conf + "\nEOF\nchmod 644 /data/local/tmp/fakehal.conf\nchcon u:object_r:shell_data_file:s0 /data/local/tmp/fakehal.conf 2>/dev/null || true\necho OK";
                                RootShell rootShell2 = RootShell.INSTANCE;
                                fakeHalManager$writeConfig$13.L$0 = null;
                                fakeHalManager$writeConfig$13.L$1 = null;
                                fakeHalManager$writeConfig$13.L$2 = null;
                                fakeHalManager$writeConfig$13.L$3 = null;
                                fakeHalManager$writeConfig$13.L$4 = null;
                                fakeHalManager$writeConfig$13.L$5 = null;
                                fakeHalManager$writeConfig$13.L$6 = null;
                                fakeHalManager$writeConfig$13.L$7 = null;
                                fakeHalManager$writeConfig$13.L$8 = null;
                                fakeHalManager$writeConfig$13.L$9 = null;
                                fakeHalManager$writeConfig$13.L$10 = null;
                                fakeHalManager$writeConfig$13.L$11 = null;
                                fakeHalManager$writeConfig$13.L$12 = null;
                                fakeHalManager$writeConfig$13.L$13 = null;
                                fakeHalManager$writeConfig$13.L$14 = null;
                                fakeHalManager$writeConfig$13.L$15 = null;
                                fakeHalManager$writeConfig$13.L$16 = null;
                                fakeHalManager$writeConfig$13.L$17 = null;
                                fakeHalManager$writeConfig$13.L$18 = null;
                                fakeHalManager$writeConfig$13.label = 2;
                                if (rootShell2.exec(conf2, fakeHalManager$writeConfig$13) == obj) {
                                    return obj;
                                }
                                return Unit.INSTANCE;
                            }
                            String line = it.next();
                            FakeHalManager$writeConfig$1 fakeHalManager$writeConfig$14 = fakeHalManager$writeConfig$1;
                            String backSlotA3 = backSlotA;
                            String backSlotB4 = backSlotB2;
                            String activeSlot4 = activeSlot2;
                            if (StringsKt.startsWith$default(line, "noise_level=", false, 2, (Object) null)) {
                                Float floatOrNull = StringsKt.toFloatOrNull(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null));
                                curNoise = floatOrNull != null ? floatOrNull.floatValue() : 1.0f;
                            }
                            Integer isoMax4 = isoMax2;
                            if (StringsKt.startsWith$default(line, "iso_min=", false, 2, (Object) null)) {
                                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null));
                                curIsoMin = intOrNull != null ? intOrNull.intValue() : 100;
                            }
                            if (StringsKt.startsWith$default(line, "iso_max=", false, 2, (Object) null)) {
                                Integer intOrNull2 = StringsKt.toIntOrNull(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null));
                                curIsoMax = intOrNull2 != null ? intOrNull2.intValue() : 800;
                            }
                            if (StringsKt.startsWith$default(line, "back_slot_a=", false, 2, (Object) null)) {
                                curSlotA = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(line, "back_slot_b=", false, 2, (Object) null)) {
                                curSlotB = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(line, "active_slot=", false, 2, (Object) null)) {
                                curActiveSlot = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(line, "qr_mode=", false, 2, (Object) null)) {
                                curQrMode = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "back_rotate_180=", false, 2, (Object) null)) {
                                curBackRotate180 = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "front_rotate_180=", false, 2, (Object) null)) {
                                curFrontRotate180 = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "front_mirror=", false, 2, (Object) null)) {
                                curFrontMirror = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "chrome_front_rotate_180=", false, 2, (Object) null)) {
                                curChromeFrontRotate180 = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "chrome_front_mirror=", false, 2, (Object) null)) {
                                curChromeFrontMirror = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "preview_rotation=", false, 2, (Object) null)) {
                                Integer intOrNull3 = StringsKt.toIntOrNull(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null));
                                curPreviewRotation = intOrNull3 != null ? intOrNull3.intValue() : 0;
                            }
                            if (StringsKt.startsWith$default(line, "preview_mirror_h=", false, 2, (Object) null)) {
                                curPreviewMirrorH = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "preview_mirror_v=", false, 2, (Object) null)) {
                                curPreviewMirrorV = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "gyro_enabled=", false, 2, (Object) null)) {
                                curGyroEnabled = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), false);
                            }
                            if (StringsKt.startsWith$default(line, "gyro_strength=", false, 2, (Object) null)) {
                                Float floatOrNull2 = StringsKt.toFloatOrNull(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null));
                                curGyroStrength = floatOrNull2 != null ? floatOrNull2.floatValue() : 0.1f;
                            }
                            if (StringsKt.startsWith$default(line, "front_user_set=", false, 2, (Object) null)) {
                                curFrontUserSet = fakeHalManager.parseConfigBool(StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null), true);
                            }
                            if (StringsKt.startsWith$default(line, "stream_camera=", false, 2, (Object) null)) {
                                curStreamCamera = StringsKt.trim((CharSequence) StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null)).toString();
                                activeSlot2 = activeSlot4;
                                backSlotB2 = backSlotB4;
                                backSlotA = backSlotA3;
                                isoMax2 = isoMax4;
                                fakeHalManager$writeConfig$1 = fakeHalManager$writeConfig$14;
                                coroutine_suspended = obj;
                            } else {
                                activeSlot2 = activeSlot4;
                                backSlotB2 = backSlotB4;
                                backSlotA = backSlotA3;
                                isoMax2 = isoMax4;
                                fakeHalManager$writeConfig$1 = fakeHalManager$writeConfig$14;
                                coroutine_suspended = obj;
                            }
                        }
                    case 1:
                        String streamCamera3 = (String) fakeHalManager$writeConfig$1.L$18;
                        Boolean gyroEnabled2 = (Boolean) fakeHalManager$writeConfig$1.L$17;
                        Boolean previewMirrorV2 = (Boolean) fakeHalManager$writeConfig$1.L$16;
                        Boolean previewMirrorH2 = (Boolean) fakeHalManager$writeConfig$1.L$15;
                        Integer previewRotation3 = (Integer) fakeHalManager$writeConfig$1.L$14;
                        Float gyroStrength3 = (Float) fakeHalManager$writeConfig$1.L$13;
                        Boolean chromeFrontMirror4 = (Boolean) fakeHalManager$writeConfig$1.L$12;
                        Boolean chromeFrontRotate1804 = (Boolean) fakeHalManager$writeConfig$1.L$11;
                        Boolean frontMirror4 = (Boolean) fakeHalManager$writeConfig$1.L$10;
                        Boolean frontRotate1804 = (Boolean) fakeHalManager$writeConfig$1.L$9;
                        Boolean backRotate1803 = (Boolean) fakeHalManager$writeConfig$1.L$8;
                        Boolean qrMode2 = (Boolean) fakeHalManager$writeConfig$1.L$7;
                        String activeSlot5 = (String) fakeHalManager$writeConfig$1.L$6;
                        String backSlotB5 = (String) fakeHalManager$writeConfig$1.L$5;
                        String backSlotA4 = (String) fakeHalManager$writeConfig$1.L$4;
                        Integer isoMax5 = (Integer) fakeHalManager$writeConfig$1.L$3;
                        Integer isoMin2 = (Integer) fakeHalManager$writeConfig$1.L$2;
                        Float noiseLevel2 = (Float) fakeHalManager$writeConfig$1.L$1;
                        fakeHalManager = (FakeHalManager) fakeHalManager$writeConfig$1.L$0;
                        ResultKt.throwOnFailure($result);
                        gyroStrength2 = gyroStrength3;
                        chromeFrontMirror2 = chromeFrontMirror4;
                        chromeFrontRotate1802 = chromeFrontRotate1804;
                        frontMirror2 = frontMirror4;
                        frontRotate1802 = frontRotate1804;
                        backRotate1802 = backRotate1803;
                        streamCamera2 = streamCamera3;
                        chromeFrontMirror3 = qrMode2;
                        chromeFrontRotate1803 = gyroEnabled2;
                        frontMirror3 = previewMirrorV2;
                        frontRotate1803 = previewMirrorH2;
                        previewRotation2 = previewRotation3;
                        activeSlot2 = activeSlot5;
                        backSlotB2 = backSlotB5;
                        backSlotA = backSlotA4;
                        isoMax2 = isoMax5;
                        isoMin = isoMin2;
                        noiseLevel = noiseLevel2;
                        RootShell.Result confResult2 = (RootShell.Result) $result;
                        float curNoise2 = 1.0f;
                        int curIsoMin2 = 100;
                        int curIsoMax2 = 800;
                        String curSlotA2 = "";
                        String curSlotB2 = "";
                        String curActiveSlot2 = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        boolean curQrMode2 = false;
                        boolean curBackRotate1802 = false;
                        boolean curFrontRotate1802 = true;
                        boolean curFrontMirror2 = true;
                        boolean curChromeFrontRotate1802 = false;
                        boolean curChromeFrontMirror2 = false;
                        int curPreviewRotation2 = 0;
                        boolean curPreviewMirrorH2 = false;
                        boolean curPreviewMirrorV2 = false;
                        boolean curGyroEnabled2 = false;
                        float curGyroStrength2 = 0.1f;
                        boolean curFrontUserSet2 = true;
                        it = StringsKt.lines(confResult2.getOutput()).iterator();
                        String curStreamCamera2 = "both";
                        while (true) {
                            Object obj2 = coroutine_suspended;
                            if (it.hasNext()) {
                            }
                        }
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$writeConfig$1 = new FakeHalManager$writeConfig$1(this, continuation);
        Object $result2 = fakeHalManager$writeConfig$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$writeConfig$1.label) {
        }
    }

    static /* synthetic */ Object writeConfig$default(FakeHalManager fakeHalManager, Float f, Integer num, Integer num2, String str, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Float f2, Integer num3, Boolean bool7, Boolean bool8, Boolean bool9, String str4, Continuation continuation, int i, Object obj) {
        return fakeHalManager.writeConfig((i & 1) != 0 ? null : f, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : bool, (i & 128) != 0 ? null : bool2, (i & 256) != 0 ? null : bool3, (i & 512) != 0 ? null : bool4, (i & 1024) != 0 ? null : bool5, (i & 2048) != 0 ? null : bool6, (i & 4096) != 0 ? null : f2, (i & 8192) != 0 ? null : num3, (i & 16384) != 0 ? null : bool7, (32768 & i) != 0 ? null : bool8, (65536 & i) != 0 ? null : bool9, (i & 131072) != 0 ? null : str4, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0031. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x023c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x022d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0222 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0213 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0208 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01c3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x019e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x018e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0158 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object start(Continuation<? super Boolean> continuation) {
        FakeHalManager$start$1 fakeHalManager$start$1;
        Object exec;
        boolean alreadyGood;
        Object exec2;
        String backVideo;
        String frontVideo;
        Object exec3;
        boolean frontExists;
        RootShell rootShell;
        String backVideo2;
        RootShell rootShell2;
        RootShell rootShell3;
        RootShell rootShell4;
        RootShell rootShell5;
        String str;
        RootShell rootShell6;
        Object exec4;
        if (continuation instanceof FakeHalManager$start$1) {
            FakeHalManager$start$1 fakeHalManager$start$12 = (FakeHalManager$start$1) continuation;
            if ((fakeHalManager$start$12.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$start$12.label -= Integer.MIN_VALUE;
                fakeHalManager$start$1 = fakeHalManager$start$12;
                Object $result = fakeHalManager$start$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$start$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell7 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 1;
                        exec = rootShell7.exec("PID=$(pidof fake_camera_provider 2>/dev/null); [ -n \"$PID\" ] && grep -q afbc_encoder /proc/$PID/maps && grep -q gralloc_uv_fix /proc/$PID/maps && echo YES || echo NO", fakeHalManager$start$1);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        alreadyGood = StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "YES", false, 2, (Object) null);
                        if (!alreadyGood) {
                            return Boxing.boxBoolean(true);
                        }
                        RootShell rootShell8 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 2;
                        exec2 = rootShell8.exec("cat /data/local/tmp/fakehal.conf 2>/dev/null", fakeHalManager$start$1);
                        if (exec2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell.Result confResult = (RootShell.Result) exec2;
                        backVideo = "/data/local/tmp/fake_video.mp4";
                        frontVideo = "/data/local/tmp/fake_video.mp4";
                        for (String line : StringsKt.lines(confResult.getOutput())) {
                            if (StringsKt.startsWith$default(line, "back_video=", false, 2, (Object) null)) {
                                backVideo = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                            }
                            if (StringsKt.startsWith$default(line, "front_video=", false, 2, (Object) null)) {
                                frontVideo = StringsKt.substringAfter$default(line, "=", (String) null, 2, (Object) null);
                            }
                        }
                        fakeHalManager$start$1.L$0 = backVideo;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 3;
                        exec3 = RootShell.INSTANCE.exec("[ -f '" + frontVideo + "' ] && echo YES", fakeHalManager$start$1);
                        if (exec3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        frontExists = StringsKt.contains$default((CharSequence) ((RootShell.Result) exec3).getOutput(), (CharSequence) "YES", false, 2, (Object) null);
                        if (!frontExists) {
                            frontVideo = backVideo;
                        }
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 4;
                        if (rootShell.exec("setprop fakehal.stream.port 9080", fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        backVideo2 = backVideo;
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 5;
                        if (rootShell2.exec("pkill -f fake_camera_provider 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 6;
                        if (DelayKt.delay(1000L, fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 7;
                        if (rootShell3.exec("setprop ctl.stop vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 8;
                        if (rootShell4.exec("pkill -f 'camera.provider@2.7-service-google' 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell rootShell9 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell9.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell.Result pidResult = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult.getOutput()));
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        alreadyGood = StringsKt.contains$default((CharSequence) ((RootShell.Result) exec).getOutput(), (CharSequence) "YES", false, 2, (Object) null);
                        if (!alreadyGood) {
                        }
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        exec2 = $result;
                        RootShell.Result confResult2 = (RootShell.Result) exec2;
                        backVideo = "/data/local/tmp/fake_video.mp4";
                        frontVideo = "/data/local/tmp/fake_video.mp4";
                        while (r14.hasNext()) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 3;
                        exec3 = RootShell.INSTANCE.exec("[ -f '" + frontVideo + "' ] && echo YES", fakeHalManager$start$1);
                        if (exec3 == coroutine_suspended) {
                        }
                        frontExists = StringsKt.contains$default((CharSequence) ((RootShell.Result) exec3).getOutput(), (CharSequence) "YES", false, 2, (Object) null);
                        if (!frontExists) {
                        }
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 4;
                        if (rootShell.exec("setprop fakehal.stream.port 9080", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        break;
                    case 3:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        exec3 = $result;
                        frontExists = StringsKt.contains$default((CharSequence) ((RootShell.Result) exec3).getOutput(), (CharSequence) "YES", false, 2, (Object) null);
                        if (!frontExists) {
                        }
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 4;
                        if (rootShell.exec("setprop fakehal.stream.port 9080", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        break;
                    case 4:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 5;
                        if (rootShell2.exec("pkill -f fake_camera_provider 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 6;
                        if (DelayKt.delay(1000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 7;
                        if (rootShell3.exec("setprop ctl.stop vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 8;
                        if (rootShell4.exec("pkill -f 'camera.provider@2.7-service-google' 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell92 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell92.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2.getOutput()));
                    case 5:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 6;
                        if (DelayKt.delay(1000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 7;
                        if (rootShell3.exec("setprop ctl.stop vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 8;
                        if (rootShell4.exec("pkill -f 'camera.provider@2.7-service-google' 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell922 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell922.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult22 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22.getOutput()));
                    case 6:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 7;
                        if (rootShell3.exec("setprop ctl.stop vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 8;
                        if (rootShell4.exec("pkill -f 'camera.provider@2.7-service-google' 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell9222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell9222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult222.getOutput()));
                    case 7:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 8;
                        if (rootShell4.exec("pkill -f 'camera.provider@2.7-service-google' 2>/dev/null", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell92222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell92222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2222.getOutput()));
                    case 8:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$start$1.L$0 = backVideo2;
                        fakeHalManager$start$1.L$1 = frontVideo;
                        fakeHalManager$start$1.label = 9;
                        if (DelayKt.delay(500L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell922222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell922222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult22222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22222.getOutput()));
                    case 9:
                        frontVideo = (String) fakeHalManager$start$1.L$1;
                        backVideo2 = (String) fakeHalManager$start$1.L$0;
                        ResultKt.throwOnFailure($result);
                        rootShell5 = RootShell.INSTANCE;
                        str = "cp /data/local/tmp/fakehAL.log /data/local/tmp/fakehAL.log.last 2>/dev/null; > /data/local/tmp/fakehAL.log; chmod 666 /data/local/tmp/fakehAL.log; LD_PRELOAD=/data/adb/modules/FakeHAL/afbc_encoder.so:/data/adb/modules/FakeHAL/gralloc_uv_fix.so setsid /data/local/tmp/fake_camera_provider '" + backVideo2 + "' '" + frontVideo + "' </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &";
                        fakeHalManager$start$1.L$0 = null;
                        fakeHalManager$start$1.L$1 = null;
                        fakeHalManager$start$1.label = 10;
                        if (rootShell5.exec(str, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell9222222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell9222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult222222.getOutput()));
                    case 10:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$start$1.label = 11;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell92222222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell92222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2222222.getOutput()));
                    case 11:
                        ResultKt.throwOnFailure($result);
                        rootShell6 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 12;
                        if (rootShell6.exec("setprop ctl.restart cameraserver", fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell922222222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell922222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult22222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22222222.getOutput()));
                    case 12:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$start$1.label = 13;
                        if (DelayKt.delay(3000L, fakeHalManager$start$1) == coroutine_suspended) {
                        }
                        RootShell rootShell9222222222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell9222222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult222222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult222222222.getOutput()));
                    case 13:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell92222222222 = RootShell.INSTANCE;
                        fakeHalManager$start$1.label = 14;
                        exec4 = rootShell92222222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$start$1);
                        if (exec4 == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2222222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2222222222.getOutput()));
                    case 14:
                        ResultKt.throwOnFailure($result);
                        exec4 = $result;
                        RootShell.Result pidResult22222222222 = (RootShell.Result) exec4;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22222222222.getOutput()));
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$start$1 = new FakeHalManager$start$1(this, continuation);
        Object $result2 = fakeHalManager$start$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$start$1.label) {
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object stop(Continuation<? super Boolean> continuation) {
        FakeHalManager$stop$1 fakeHalManager$stop$1;
        FakeHalManager$stop$1 fakeHalManager$stop$12;
        RootShell rootShell;
        RootShell rootShell2;
        Object exec;
        if (continuation instanceof FakeHalManager$stop$1) {
            fakeHalManager$stop$1 = (FakeHalManager$stop$1) continuation;
            if ((fakeHalManager$stop$1.label & Integer.MIN_VALUE) != 0) {
                fakeHalManager$stop$1.label -= Integer.MIN_VALUE;
                fakeHalManager$stop$12 = fakeHalManager$stop$1;
                Object $result = fakeHalManager$stop$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fakeHalManager$stop$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell3 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 1;
                        if (rootShell3.exec("pkill -f fake_camera_provider 2>/dev/null", fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$stop$12.label = 2;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 3;
                        if (rootShell.exec("setprop ctl.restart vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$stop$12.label = 4;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 5;
                        if (rootShell2.exec("setprop ctl.restart cameraserver", fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell rootShell4 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell4.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        RootShell.Result pidResult = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult.getOutput()));
                    case 1:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$stop$12.label = 2;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 3;
                        if (rootShell.exec("setprop ctl.restart vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 4;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 5;
                        if (rootShell2.exec("setprop ctl.restart cameraserver", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        RootShell rootShell42 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell42.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2.getOutput()));
                    case 2:
                        ResultKt.throwOnFailure($result);
                        rootShell = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 3;
                        if (rootShell.exec("setprop ctl.restart vendor.camera-provider-2-7-google 2>/dev/null", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 4;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 5;
                        if (rootShell2.exec("setprop ctl.restart cameraserver", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        RootShell rootShell422 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell422.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult22 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22.getOutput()));
                    case 3:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$stop$12.label = 4;
                        if (DelayKt.delay(1000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 5;
                        if (rootShell2.exec("setprop ctl.restart cameraserver", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        RootShell rootShell4222 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell4222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult222 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult222.getOutput()));
                    case 4:
                        ResultKt.throwOnFailure($result);
                        rootShell2 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 5;
                        if (rootShell2.exec("setprop ctl.restart cameraserver", fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        RootShell rootShell42222 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell42222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult2222 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2222.getOutput()));
                    case 5:
                        ResultKt.throwOnFailure($result);
                        fakeHalManager$stop$12.label = 6;
                        if (DelayKt.delay(2000L, fakeHalManager$stop$12) == coroutine_suspended) {
                        }
                        RootShell rootShell422222 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell422222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult22222 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult22222.getOutput()));
                    case 6:
                        ResultKt.throwOnFailure($result);
                        RootShell rootShell4222222 = RootShell.INSTANCE;
                        fakeHalManager$stop$12.label = 7;
                        exec = rootShell4222222.exec("pidof fake_camera_provider 2>/dev/null", fakeHalManager$stop$12);
                        if (exec == coroutine_suspended) {
                        }
                        RootShell.Result pidResult222222 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult222222.getOutput()));
                    case 7:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        RootShell.Result pidResult2222222 = (RootShell.Result) exec;
                        return Boxing.boxBoolean(StringsKt.isBlank(pidResult2222222.getOutput()));
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fakeHalManager$stop$1 = new FakeHalManager$stop$1(this, continuation);
        fakeHalManager$stop$12 = fakeHalManager$stop$1;
        Object $result2 = fakeHalManager$stop$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fakeHalManager$stop$12.label) {
        }
    }
}

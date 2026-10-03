package com.fakehal.controller;

import com.fakehal.controller.ImageToVideoConverter;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* compiled from: TransformConfigManager.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003!\"#B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0019J&\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0011J\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0011J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u0011J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/fakehal/controller/TransformConfigManager;", "", "()V", "CONFIG_PATH", "", "TAG", "cameraConfigFromJson", "Lcom/fakehal/controller/TransformConfigManager$CameraConfig;", "j", "Lorg/json/JSONObject;", "cameraConfigToJson", "cc", "fullConfigFromJson", "Lcom/fakehal/controller/TransformConfigManager$FullConfig;", "fullConfigToJson", "fc", "getStreamConfig", "Lcom/fakehal/controller/TransformConfigManager$StreamConfig;", "config", "camera", "mode", "load", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "", "(Lcom/fakehal/controller/TransformConfigManager$FullConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setStreamConfig", "sc", "streamConfigFromJson", "streamConfigToJson", "toConverterConfig", "Lcom/fakehal/controller/ImageToVideoConverter$TransformConfig;", "toFakeHalConf", "CameraConfig", "FullConfig", "StreamConfig", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TransformConfigManager {
    public static final int $stable = 0;
    private static final String CONFIG_PATH = "/data/local/tmp/fakehal_transform.json";
    public static final TransformConfigManager INSTANCE = new TransformConfigManager();
    private static final String TAG = "TransformConfigMgr";

    private TransformConfigManager() {
    }

    /* compiled from: TransformConfigManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\bHÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014¨\u0006&"}, d2 = {"Lcom/fakehal/controller/TransformConfigManager$StreamConfig;", "", "rotation", "", "mirrorX", "", "mirrorY", "fitMode", "", "sensorCompensation", "jpegOrientation", "clearExif", "clearRotateMetadata", "(IZZLjava/lang/String;IIZZ)V", "getClearExif", "()Z", "getClearRotateMetadata", "getFitMode", "()Ljava/lang/String;", "getJpegOrientation", "()I", "getMirrorX", "getMirrorY", "getRotation", "getSensorCompensation", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class StreamConfig {
        public static final int $stable = 0;
        private final boolean clearExif;
        private final boolean clearRotateMetadata;
        private final String fitMode;
        private final int jpegOrientation;
        private final boolean mirrorX;
        private final boolean mirrorY;
        private final int rotation;
        private final int sensorCompensation;

        public StreamConfig() {
            this(0, false, false, null, 0, 0, false, false, 255, null);
        }

        /* renamed from: component1, reason: from getter */
        public final int getRotation() {
            return this.rotation;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getMirrorX() {
            return this.mirrorX;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getMirrorY() {
            return this.mirrorY;
        }

        /* renamed from: component4, reason: from getter */
        public final String getFitMode() {
            return this.fitMode;
        }

        /* renamed from: component5, reason: from getter */
        public final int getSensorCompensation() {
            return this.sensorCompensation;
        }

        /* renamed from: component6, reason: from getter */
        public final int getJpegOrientation() {
            return this.jpegOrientation;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getClearExif() {
            return this.clearExif;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getClearRotateMetadata() {
            return this.clearRotateMetadata;
        }

        public final StreamConfig copy(int rotation, boolean mirrorX, boolean mirrorY, String fitMode, int sensorCompensation, int jpegOrientation, boolean clearExif, boolean clearRotateMetadata) {
            Intrinsics.checkNotNullParameter(fitMode, "fitMode");
            return new StreamConfig(rotation, mirrorX, mirrorY, fitMode, sensorCompensation, jpegOrientation, clearExif, clearRotateMetadata);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamConfig)) {
                return false;
            }
            StreamConfig streamConfig = (StreamConfig) other;
            return this.rotation == streamConfig.rotation && this.mirrorX == streamConfig.mirrorX && this.mirrorY == streamConfig.mirrorY && Intrinsics.areEqual(this.fitMode, streamConfig.fitMode) && this.sensorCompensation == streamConfig.sensorCompensation && this.jpegOrientation == streamConfig.jpegOrientation && this.clearExif == streamConfig.clearExif && this.clearRotateMetadata == streamConfig.clearRotateMetadata;
        }

        public int hashCode() {
            return (((((((((((((Integer.hashCode(this.rotation) * 31) + Boolean.hashCode(this.mirrorX)) * 31) + Boolean.hashCode(this.mirrorY)) * 31) + this.fitMode.hashCode()) * 31) + Integer.hashCode(this.sensorCompensation)) * 31) + Integer.hashCode(this.jpegOrientation)) * 31) + Boolean.hashCode(this.clearExif)) * 31) + Boolean.hashCode(this.clearRotateMetadata);
        }

        public String toString() {
            return "StreamConfig(rotation=" + this.rotation + ", mirrorX=" + this.mirrorX + ", mirrorY=" + this.mirrorY + ", fitMode=" + this.fitMode + ", sensorCompensation=" + this.sensorCompensation + ", jpegOrientation=" + this.jpegOrientation + ", clearExif=" + this.clearExif + ", clearRotateMetadata=" + this.clearRotateMetadata + ")";
        }

        public StreamConfig(int rotation, boolean mirrorX, boolean mirrorY, String fitMode, int sensorCompensation, int jpegOrientation, boolean clearExif, boolean clearRotateMetadata) {
            Intrinsics.checkNotNullParameter(fitMode, "fitMode");
            this.rotation = rotation;
            this.mirrorX = mirrorX;
            this.mirrorY = mirrorY;
            this.fitMode = fitMode;
            this.sensorCompensation = sensorCompensation;
            this.jpegOrientation = jpegOrientation;
            this.clearExif = clearExif;
            this.clearRotateMetadata = clearRotateMetadata;
        }

        public /* synthetic */ StreamConfig(int i, boolean z, boolean z2, String str, int i2, int i3, boolean z3, boolean z4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? false : z, (i4 & 4) != 0 ? false : z2, (i4 & 8) != 0 ? "centerCrop" : str, (i4 & 16) != 0 ? 90 : i2, (i4 & 32) == 0 ? i3 : 0, (i4 & 64) != 0 ? true : z3, (i4 & 128) == 0 ? z4 : true);
        }

        public final int getRotation() {
            return this.rotation;
        }

        public final boolean getMirrorX() {
            return this.mirrorX;
        }

        public final boolean getMirrorY() {
            return this.mirrorY;
        }

        public final String getFitMode() {
            return this.fitMode;
        }

        public final int getSensorCompensation() {
            return this.sensorCompensation;
        }

        public final int getJpegOrientation() {
            return this.jpegOrientation;
        }

        public final boolean getClearExif() {
            return this.clearExif;
        }

        public final boolean getClearRotateMetadata() {
            return this.clearRotateMetadata;
        }
    }

    /* compiled from: TransformConfigManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/fakehal/controller/TransformConfigManager$CameraConfig;", "", "preview", "Lcom/fakehal/controller/TransformConfigManager$StreamConfig;", "capture", "video", "(Lcom/fakehal/controller/TransformConfigManager$StreamConfig;Lcom/fakehal/controller/TransformConfigManager$StreamConfig;Lcom/fakehal/controller/TransformConfigManager$StreamConfig;)V", "getCapture", "()Lcom/fakehal/controller/TransformConfigManager$StreamConfig;", "getPreview", "getVideo", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class CameraConfig {
        public static final int $stable = 0;
        private final StreamConfig capture;
        private final StreamConfig preview;
        private final StreamConfig video;

        public CameraConfig() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ CameraConfig copy$default(CameraConfig cameraConfig, StreamConfig streamConfig, StreamConfig streamConfig2, StreamConfig streamConfig3, int i, Object obj) {
            if ((i & 1) != 0) {
                streamConfig = cameraConfig.preview;
            }
            if ((i & 2) != 0) {
                streamConfig2 = cameraConfig.capture;
            }
            if ((i & 4) != 0) {
                streamConfig3 = cameraConfig.video;
            }
            return cameraConfig.copy(streamConfig, streamConfig2, streamConfig3);
        }

        /* renamed from: component1, reason: from getter */
        public final StreamConfig getPreview() {
            return this.preview;
        }

        /* renamed from: component2, reason: from getter */
        public final StreamConfig getCapture() {
            return this.capture;
        }

        /* renamed from: component3, reason: from getter */
        public final StreamConfig getVideo() {
            return this.video;
        }

        public final CameraConfig copy(StreamConfig preview, StreamConfig capture, StreamConfig video) {
            Intrinsics.checkNotNullParameter(preview, "preview");
            Intrinsics.checkNotNullParameter(capture, "capture");
            Intrinsics.checkNotNullParameter(video, "video");
            return new CameraConfig(preview, capture, video);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CameraConfig)) {
                return false;
            }
            CameraConfig cameraConfig = (CameraConfig) other;
            return Intrinsics.areEqual(this.preview, cameraConfig.preview) && Intrinsics.areEqual(this.capture, cameraConfig.capture) && Intrinsics.areEqual(this.video, cameraConfig.video);
        }

        public int hashCode() {
            return (((this.preview.hashCode() * 31) + this.capture.hashCode()) * 31) + this.video.hashCode();
        }

        public String toString() {
            return "CameraConfig(preview=" + this.preview + ", capture=" + this.capture + ", video=" + this.video + ")";
        }

        public CameraConfig(StreamConfig preview, StreamConfig capture, StreamConfig video) {
            Intrinsics.checkNotNullParameter(preview, "preview");
            Intrinsics.checkNotNullParameter(capture, "capture");
            Intrinsics.checkNotNullParameter(video, "video");
            this.preview = preview;
            this.capture = capture;
            this.video = video;
        }

        public /* synthetic */ CameraConfig(StreamConfig streamConfig, StreamConfig streamConfig2, StreamConfig streamConfig3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null) : streamConfig, (i & 2) != 0 ? new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null) : streamConfig2, (i & 4) != 0 ? new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null) : streamConfig3);
        }

        public final StreamConfig getPreview() {
            return this.preview;
        }

        public final StreamConfig getCapture() {
            return this.capture;
        }

        public final StreamConfig getVideo() {
            return this.video;
        }
    }

    /* compiled from: TransformConfigManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/fakehal/controller/TransformConfigManager$FullConfig;", "", "front", "Lcom/fakehal/controller/TransformConfigManager$CameraConfig;", "back", "(Lcom/fakehal/controller/TransformConfigManager$CameraConfig;Lcom/fakehal/controller/TransformConfigManager$CameraConfig;)V", "getBack", "()Lcom/fakehal/controller/TransformConfigManager$CameraConfig;", "getFront", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class FullConfig {
        public static final int $stable = 0;
        private final CameraConfig back;
        private final CameraConfig front;

        /* JADX WARN: Multi-variable type inference failed */
        public FullConfig() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ FullConfig copy$default(FullConfig fullConfig, CameraConfig cameraConfig, CameraConfig cameraConfig2, int i, Object obj) {
            if ((i & 1) != 0) {
                cameraConfig = fullConfig.front;
            }
            if ((i & 2) != 0) {
                cameraConfig2 = fullConfig.back;
            }
            return fullConfig.copy(cameraConfig, cameraConfig2);
        }

        /* renamed from: component1, reason: from getter */
        public final CameraConfig getFront() {
            return this.front;
        }

        /* renamed from: component2, reason: from getter */
        public final CameraConfig getBack() {
            return this.back;
        }

        public final FullConfig copy(CameraConfig front, CameraConfig back) {
            Intrinsics.checkNotNullParameter(front, "front");
            Intrinsics.checkNotNullParameter(back, "back");
            return new FullConfig(front, back);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FullConfig)) {
                return false;
            }
            FullConfig fullConfig = (FullConfig) other;
            return Intrinsics.areEqual(this.front, fullConfig.front) && Intrinsics.areEqual(this.back, fullConfig.back);
        }

        public int hashCode() {
            return (this.front.hashCode() * 31) + this.back.hashCode();
        }

        public String toString() {
            return "FullConfig(front=" + this.front + ", back=" + this.back + ")";
        }

        public FullConfig(CameraConfig front, CameraConfig back) {
            Intrinsics.checkNotNullParameter(front, "front");
            Intrinsics.checkNotNullParameter(back, "back");
            this.front = front;
            this.back = back;
        }

        public /* synthetic */ FullConfig(CameraConfig cameraConfig, CameraConfig cameraConfig2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new CameraConfig(null, null, null, 7, null) : cameraConfig, (i & 2) != 0 ? new CameraConfig(null, null, null, 7, null) : cameraConfig2);
        }

        public final CameraConfig getFront() {
            return this.front;
        }

        public final CameraConfig getBack() {
            return this.back;
        }
    }

    public final JSONObject streamConfigToJson(StreamConfig sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        JSONObject $this$streamConfigToJson_u24lambda_u240 = new JSONObject();
        $this$streamConfigToJson_u24lambda_u240.put("rotation", sc.getRotation());
        $this$streamConfigToJson_u24lambda_u240.put("mirrorX", sc.getMirrorX());
        $this$streamConfigToJson_u24lambda_u240.put("mirrorY", sc.getMirrorY());
        $this$streamConfigToJson_u24lambda_u240.put("fitMode", sc.getFitMode());
        $this$streamConfigToJson_u24lambda_u240.put("sensorCompensation", sc.getSensorCompensation());
        $this$streamConfigToJson_u24lambda_u240.put("jpegOrientation", sc.getJpegOrientation());
        $this$streamConfigToJson_u24lambda_u240.put("clearExif", sc.getClearExif());
        $this$streamConfigToJson_u24lambda_u240.put("clearRotateMetadata", sc.getClearRotateMetadata());
        return $this$streamConfigToJson_u24lambda_u240;
    }

    public final StreamConfig streamConfigFromJson(JSONObject j) {
        Intrinsics.checkNotNullParameter(j, "j");
        int optInt = j.optInt("rotation", 0);
        boolean optBoolean = j.optBoolean("mirrorX", false);
        boolean optBoolean2 = j.optBoolean("mirrorY", false);
        String optString = j.optString("fitMode", "centerCrop");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        return new StreamConfig(optInt, optBoolean, optBoolean2, optString, j.optInt("sensorCompensation", 90), j.optInt("jpegOrientation", 0), j.optBoolean("clearExif", true), j.optBoolean("clearRotateMetadata", true));
    }

    public final JSONObject cameraConfigToJson(CameraConfig cc) {
        Intrinsics.checkNotNullParameter(cc, "cc");
        JSONObject $this$cameraConfigToJson_u24lambda_u241 = new JSONObject();
        $this$cameraConfigToJson_u24lambda_u241.put("preview", INSTANCE.streamConfigToJson(cc.getPreview()));
        $this$cameraConfigToJson_u24lambda_u241.put("capture", INSTANCE.streamConfigToJson(cc.getCapture()));
        $this$cameraConfigToJson_u24lambda_u241.put("video", INSTANCE.streamConfigToJson(cc.getVideo()));
        return $this$cameraConfigToJson_u24lambda_u241;
    }

    public final CameraConfig cameraConfigFromJson(JSONObject j) {
        StreamConfig streamConfig;
        StreamConfig streamConfig2;
        StreamConfig streamConfig3;
        Intrinsics.checkNotNullParameter(j, "j");
        if (j.has("preview")) {
            JSONObject jSONObject = j.getJSONObject("preview");
            Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
            streamConfig = streamConfigFromJson(jSONObject);
        } else {
            streamConfig = new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null);
        }
        if (j.has("capture")) {
            JSONObject jSONObject2 = j.getJSONObject("capture");
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
            streamConfig2 = streamConfigFromJson(jSONObject2);
        } else {
            streamConfig2 = new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null);
        }
        if (j.has("video")) {
            JSONObject jSONObject3 = j.getJSONObject("video");
            Intrinsics.checkNotNullExpressionValue(jSONObject3, "getJSONObject(...)");
            streamConfig3 = streamConfigFromJson(jSONObject3);
        } else {
            streamConfig3 = new StreamConfig(0, false, false, null, 0, 0, false, false, 255, null);
        }
        return new CameraConfig(streamConfig, streamConfig2, streamConfig3);
    }

    public final JSONObject fullConfigToJson(FullConfig fc) {
        Intrinsics.checkNotNullParameter(fc, "fc");
        JSONObject $this$fullConfigToJson_u24lambda_u242 = new JSONObject();
        $this$fullConfigToJson_u24lambda_u242.put("front", INSTANCE.cameraConfigToJson(fc.getFront()));
        $this$fullConfigToJson_u24lambda_u242.put("back", INSTANCE.cameraConfigToJson(fc.getBack()));
        return $this$fullConfigToJson_u24lambda_u242;
    }

    public final FullConfig fullConfigFromJson(JSONObject j) {
        CameraConfig cameraConfig;
        CameraConfig cameraConfig2;
        Intrinsics.checkNotNullParameter(j, "j");
        if (j.has("front")) {
            JSONObject jSONObject = j.getJSONObject("front");
            Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
            cameraConfig = cameraConfigFromJson(jSONObject);
        } else {
            cameraConfig = new CameraConfig(null, null, null, 7, null);
        }
        if (j.has("back")) {
            JSONObject jSONObject2 = j.getJSONObject("back");
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
            cameraConfig2 = cameraConfigFromJson(jSONObject2);
        } else {
            cameraConfig2 = new CameraConfig(null, null, null, 7, null);
        }
        return new FullConfig(cameraConfig, cameraConfig2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0024. Please report as an issue. */
    public final StreamConfig getStreamConfig(FullConfig config, String camera, String mode) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter(mode, "mode");
        CameraConfig cam = Intrinsics.areEqual(camera, "front") ? config.getFront() : config.getBack();
        switch (mode.hashCode()) {
            case -318184504:
                if (mode.equals("preview")) {
                    return cam.getPreview();
                }
                return cam.getPreview();
            case 112202875:
                if (mode.equals("video")) {
                    return cam.getVideo();
                }
                return cam.getPreview();
            case 552585030:
                if (mode.equals("capture")) {
                    return cam.getCapture();
                }
                return cam.getPreview();
            default:
                return cam.getPreview();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final FullConfig setStreamConfig(FullConfig config, String camera, String mode, StreamConfig sc) {
        CameraConfig newCam;
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(sc, "sc");
        CameraConfig cam = Intrinsics.areEqual(camera, "front") ? config.getFront() : config.getBack();
        switch (mode.hashCode()) {
            case -318184504:
                if (mode.equals("preview")) {
                    newCam = CameraConfig.copy$default(cam, sc, null, null, 6, null);
                    break;
                }
                newCam = cam;
                break;
            case 112202875:
                if (mode.equals("video")) {
                    newCam = CameraConfig.copy$default(cam, null, null, sc, 3, null);
                    break;
                }
                newCam = cam;
                break;
            case 552585030:
                if (mode.equals("capture")) {
                    newCam = CameraConfig.copy$default(cam, null, sc, null, 5, null);
                    break;
                }
                newCam = cam;
                break;
            default:
                newCam = cam;
                break;
        }
        return Intrinsics.areEqual(camera, "front") ? FullConfig.copy$default(config, newCam, null, 2, null) : FullConfig.copy$default(config, null, newCam, 1, null);
    }

    public final ImageToVideoConverter.TransformConfig toConverterConfig(StreamConfig sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        return new ImageToVideoConverter.TransformConfig(sc.getRotation(), sc.getMirrorX(), sc.getMirrorY(), sc.getSensorCompensation(), Intrinsics.areEqual(sc.getFitMode(), "fitWithPadding") ? "pad" : "crop", false, 32, null);
    }

    public final Object load(Continuation<? super FullConfig> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TransformConfigManager$load$2(null), continuation);
    }

    public final Object save(FullConfig config, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TransformConfigManager$save$2(config, null), continuation);
    }

    public final String toFakeHalConf(FullConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        StringBuilder sb = new StringBuilder();
        StringBuilder append = sb.append("# FakeHAL transform config (auto-generated)");
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append(...)");
        StreamConfig bp = config.getBack().getPreview();
        StreamConfig fp = config.getFront().getPreview();
        StringBuilder append2 = sb.append("back_rotate_180=" + (bp.getRotation() == 180 ? "1" : "0"));
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append2.append('\n'), "append(...)");
        StringBuilder append3 = sb.append("front_rotate_180=" + (fp.getRotation() == 180 ? "1" : "0"));
        Intrinsics.checkNotNullExpressionValue(append3, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append3.append('\n'), "append(...)");
        StringBuilder append4 = sb.append("front_mirror=" + (fp.getMirrorX() ? "1" : "0"));
        Intrinsics.checkNotNullExpressionValue(append4, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append4.append('\n'), "append(...)");
        StringBuilder append5 = sb.append("chrome_front_rotate_180=" + (fp.getRotation() == 180 ? "1" : "0"));
        Intrinsics.checkNotNullExpressionValue(append5, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append5.append('\n'), "append(...)");
        StringBuilder append6 = sb.append("chrome_front_mirror=" + (fp.getMirrorX() ? "1" : "0"));
        Intrinsics.checkNotNullExpressionValue(append6, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append6.append('\n'), "append(...)");
        StringBuilder append7 = sb.append("# Full JSON config at: /data/local/tmp/fakehal_transform.json");
        Intrinsics.checkNotNullExpressionValue(append7, "append(...)");
        Intrinsics.checkNotNullExpressionValue(append7.append('\n'), "append(...)");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}

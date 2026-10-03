package com.fakehal.controller;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: VideoResampler.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/fakehal/controller/VideoResampler;", "", "()V", "TAG", "", "TARGET_BITRATE", "", "TARGET_FPS", "TARGET_HEIGHT", "TARGET_MIME", "TARGET_WIDTH", "TIMEOUT_US", "", "convertAviViaVps", "", "srcPath", "dstPath", "transcode", "transcodeInternal", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoResampler {
    public static final int $stable = 0;
    public static final VideoResampler INSTANCE = new VideoResampler();
    private static final String TAG = "VideoResampler";
    private static final int TARGET_BITRATE = 8000000;
    private static final int TARGET_FPS = 30;
    private static final int TARGET_HEIGHT = 1080;
    private static final String TARGET_MIME = "video/avc";
    private static final int TARGET_WIDTH = 1920;
    private static final long TIMEOUT_US = 10000;

    private VideoResampler() {
    }

    public final boolean transcode(String srcPath, String dstPath) {
        Intrinsics.checkNotNullParameter(srcPath, "srcPath");
        Intrinsics.checkNotNullParameter(dstPath, "dstPath");
        String lowerCase = srcPath.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        boolean isAvi = StringsKt.endsWith$default(lowerCase, ".avi", false, 2, (Object) null);
        if (isAvi) {
            AviConverter.INSTANCE.logAviInstructions(srcPath);
            Log.w(TAG, "AVI: returning false to trigger copy fallback. Use AviConverter.vpsConvertCommand() for proper conversion.");
            return false;
        }
        try {
            transcodeInternal(srcPath, dstPath);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Transcode failed: " + e.getMessage() + ", falling back to copy", e);
            return false;
        }
    }

    public final boolean convertAviViaVps(String srcPath, String dstPath) {
        Intrinsics.checkNotNullParameter(srcPath, "srcPath");
        Intrinsics.checkNotNullParameter(dstPath, "dstPath");
        try {
            Process push = Runtime.getRuntime().exec(new String[]{"sh", "-c", "adb -s localhost:5557 push '" + srcPath + "' '/sdcard/fakehal_avi_input.avi'"});
            push.waitFor();
            Log.w(TAG, "AVI conversion via VPS not yet implemented, use ffmpeg on host");
        } catch (Exception e) {
            Log.e(TAG, "AVI VPS convert failed: " + e.getMessage());
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00e3 A[LOOP:2: B:21:0x00bd->B:28:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void transcodeInternal(String srcPath, String dstPath) {
        Object element$iv;
        int videoTrackIndex;
        String decoderMime;
        MediaExtractor probeEx;
        boolean z;
        MediaExtractor extractor = new MediaExtractor();
        extractor.setDataSource(srcPath);
        MediaExtractor probeEx2 = new MediaExtractor();
        probeEx2.setDataSource(srcPath);
        Iterable $this$mapNotNull$iv = RangesKt.until(0, probeEx2.getTrackCount());
        Collection destination$iv$iv = new ArrayList();
        Iterator<Integer> it = $this$mapNotNull$iv.iterator();
        while (it.hasNext()) {
            int element$iv$iv$iv = ((IntIterator) it).nextInt();
            Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            String string = probeEx2.getTrackFormat(element$iv$iv$iv).getString("mime");
            if (string != null) {
                destination$iv$iv.add(string);
            }
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
        }
        Iterable $this$firstOrNull$iv = (List) destination$iv$iv;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            String it3 = (String) element$iv;
            Intrinsics.checkNotNull(it3);
            if (StringsKt.startsWith$default(it3, "video/", false, 2, (Object) null)) {
                break;
            }
        }
        String str = (String) element$iv;
        if (str == null) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        String inputMime = str;
        probeEx2.release();
        Log.d(TAG, "Transcode input MIME: " + inputMime + ", src: " + srcPath);
        int videoTrackIndex2 = -1;
        MediaFormat inputFormat = null;
        int i = 0;
        int trackCount = extractor.getTrackCount();
        while (true) {
            if (i >= trackCount) {
                break;
            }
            MediaFormat fmt = extractor.getTrackFormat(i);
            Intrinsics.checkNotNullExpressionValue(fmt, "getTrackFormat(...)");
            String string2 = fmt.getString("mime");
            if (string2 != null) {
                probeEx = probeEx2;
                if (StringsKt.startsWith$default(string2, "video/", false, 2, (Object) null)) {
                    z = true;
                    if (!z) {
                        videoTrackIndex2 = i;
                        inputFormat = fmt;
                        break;
                    } else {
                        i++;
                        probeEx2 = probeEx;
                    }
                }
            } else {
                probeEx = probeEx2;
            }
            z = false;
            if (!z) {
            }
        }
        if (videoTrackIndex2 < 0 || inputFormat == null) {
            throw new Exception("No video track found in " + srcPath);
        }
        extractor.selectTrack(videoTrackIndex2);
        int srcWidth = inputFormat.getInteger("width");
        int srcHeight = inputFormat.getInteger("height");
        Log.d(TAG, "Source: " + srcWidth + "x" + srcHeight + " -> Target: 1920x1080");
        if (srcWidth == TARGET_WIDTH && srcHeight == TARGET_HEIGHT) {
            Log.d(TAG, "Already target resolution, copying directly");
            extractor.release();
            FilesKt.copyTo$default(new File(srcPath), new File(dstPath), true, 0, 4, null);
            return;
        }
        MediaFormat encoderFormat = MediaFormat.createVideoFormat(TARGET_MIME, TARGET_WIDTH, TARGET_HEIGHT);
        encoderFormat.setInteger("color-format", 2130708361);
        encoderFormat.setInteger("bitrate", TARGET_BITRATE);
        encoderFormat.setInteger("frame-rate", 30);
        encoderFormat.setInteger("i-frame-interval", 1);
        Intrinsics.checkNotNullExpressionValue(encoderFormat, "apply(...)");
        MediaCodec encoder = MediaCodec.createEncoderByType(TARGET_MIME);
        Intrinsics.checkNotNullExpressionValue(encoder, "createEncoderByType(...)");
        String tmpOut = dstPath + ".tmp";
        MediaMuxer muxer = new MediaMuxer(tmpOut, 0);
        encoder.configure(encoderFormat, (Surface) null, (MediaCrypto) null, 1);
        Surface encoderSurface = encoder.createInputSurface();
        Intrinsics.checkNotNullExpressionValue(encoderSurface, "createInputSurface(...)");
        String decoderMime2 = inputFormat.getString("mime");
        Intrinsics.checkNotNull(decoderMime2);
        MediaCodec decoder = MediaCodec.createDecoderByType(decoderMime2);
        Intrinsics.checkNotNullExpressionValue(decoder, "createDecoderByType(...)");
        decoder.configure(inputFormat, encoderSurface, (MediaCrypto) null, 0);
        decoder.start();
        encoder.start();
        int muxerTrackIndex = -1;
        boolean muxerStarted = false;
        MediaCodec.BufferInfo bufInfo = new MediaCodec.BufferInfo();
        boolean decoderDone = false;
        boolean encoderDone = false;
        while (!encoderDone) {
            MediaFormat inputFormat2 = inputFormat;
            int srcHeight2 = srcHeight;
            if (decoderDone) {
                videoTrackIndex = videoTrackIndex2;
            } else {
                videoTrackIndex = videoTrackIndex2;
                int inIdx = decoder.dequeueInputBuffer(TIMEOUT_US);
                if (inIdx >= 0) {
                    ByteBuffer buf = decoder.getInputBuffer(inIdx);
                    Intrinsics.checkNotNull(buf);
                    int sampleSize = extractor.readSampleData(buf, 0);
                    if (sampleSize < 0) {
                        decoder.queueInputBuffer(inIdx, 0, 0, 0L, 4);
                        decoderDone = true;
                    } else {
                        decoder.queueInputBuffer(inIdx, 0, sampleSize, extractor.getSampleTime(), 0);
                        extractor.advance();
                    }
                }
            }
            MediaFormat encoderFormat2 = encoderFormat;
            MediaCodec.BufferInfo bufInfo2 = bufInfo;
            int decOutIdx = decoder.dequeueOutputBuffer(bufInfo2, TIMEOUT_US);
            if (decOutIdx >= 0) {
                boolean render = bufInfo2.size > 0;
                decoder.releaseOutputBuffer(decOutIdx, render);
                if ((bufInfo2.flags & 4) != 0) {
                    encoder.signalEndOfInputStream();
                }
            }
            int encOutIdx = encoder.dequeueOutputBuffer(bufInfo2, TIMEOUT_US);
            if (encOutIdx == -2) {
                muxerTrackIndex = muxer.addTrack(encoder.getOutputFormat());
                muxer.start();
                muxerStarted = true;
                bufInfo = bufInfo2;
                encoderFormat = encoderFormat2;
                inputFormat = inputFormat2;
                srcHeight = srcHeight2;
                videoTrackIndex2 = videoTrackIndex;
            } else {
                if (encOutIdx >= 0) {
                    if (!muxerStarted || bufInfo2.size <= 0) {
                        decoderMime = decoderMime2;
                    } else {
                        ByteBuffer encBuf = encoder.getOutputBuffer(encOutIdx);
                        Intrinsics.checkNotNull(encBuf);
                        encBuf.position(bufInfo2.offset);
                        decoderMime = decoderMime2;
                        encBuf.limit(bufInfo2.offset + bufInfo2.size);
                        muxer.writeSampleData(muxerTrackIndex, encBuf, bufInfo2);
                    }
                    encoder.releaseOutputBuffer(encOutIdx, false);
                    if ((bufInfo2.flags & 4) != 0) {
                        encoderDone = true;
                        bufInfo = bufInfo2;
                        encoderFormat = encoderFormat2;
                        decoderMime2 = decoderMime;
                        inputFormat = inputFormat2;
                        srcHeight = srcHeight2;
                        videoTrackIndex2 = videoTrackIndex;
                    }
                } else {
                    decoderMime = decoderMime2;
                }
                bufInfo = bufInfo2;
                encoderFormat = encoderFormat2;
                decoderMime2 = decoderMime;
                inputFormat = inputFormat2;
                srcHeight = srcHeight2;
                videoTrackIndex2 = videoTrackIndex;
            }
        }
        decoder.stop();
        decoder.release();
        encoder.stop();
        encoder.release();
        extractor.release();
        muxer.stop();
        muxer.release();
        encoderSurface.release();
        new File(tmpOut).renameTo(new File(dstPath));
        Log.d(TAG, "Transcode complete: " + dstPath);
    }
}

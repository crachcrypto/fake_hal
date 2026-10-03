package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {565, 657}, m = "writeConfig", n = {"this", "noiseLevel", "isoMin", "isoMax", "backSlotA", "backSlotB", "activeSlot", "qrMode", "backRotate180", "frontRotate180", "frontMirror", "chromeFrontRotate180", "chromeFrontMirror", "gyroStrength", "previewRotation", "previewMirrorH", "previewMirrorV", "gyroEnabled", "streamCamera"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18"})
/* loaded from: classes3.dex */
public final class FakeHalManager$writeConfig$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$16;
    Object L$17;
    Object L$18;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$writeConfig$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$writeConfig$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object writeConfig;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        writeConfig = this.this$0.writeConfig(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, this);
        return writeConfig;
    }
}

package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT, 64, 66, 69, 93, 100, 101}, m = "getStatus", n = {"this", "isRunning", "isRunning", "frameCount", "backVideo", "frontVideo", "backSlotA", "backSlotB", "activeSlot", "geoLat", "geoLon", "isRunning", "frameCount", "noiseLevel", "isoMin", "isoMax", "qrMode", "geoEnabled", "backVideo", "frontVideo", "backSlotA", "backSlotB", "activeSlot", "geoLat", "geoLon", "isRunning", "frameCount", "noiseLevel", "isoMin", "isoMax", "qrMode", "geoEnabled", "backVideo", "frontVideo", "backSlotA", "backSlotB", "activeSlot", "geoLat", "geoLon", "isRunning", "frameCount", "noiseLevel", "isoMin", "isoMax", "qrMode", "geoEnabled", "slotAExists"}, s = {"L$0", "Z$0", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "F$0", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "F$0", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "F$0", "I$1", "I$2", "I$3", "I$4", "Z$1"})
/* loaded from: classes3.dex */
public final class FakeHalManager$getStatus$1 extends ContinuationImpl {
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$getStatus$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$getStatus$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getStatus(this);
    }
}

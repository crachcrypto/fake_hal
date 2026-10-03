package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 0, 0, 0, 0, 1, 1}, l = {434, 435, 441}, m = "updateSensorSettings", n = {"this", "noiseLevel", "isoMin", "isoMax", "qrMode", "this", "wasRunning"}, s = {"L$0", "F$0", "I$0", "I$1", "Z$0", "L$0", "Z$0"})
/* loaded from: classes3.dex */
public final class FakeHalManager$updateSensorSettings$1 extends ContinuationImpl {
    float F$0;
    int I$0;
    int I$1;
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$updateSensorSettings$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$updateSensorSettings$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateSensorSettings(0.0f, 0, 0, false, this);
    }
}

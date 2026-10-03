package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 0, 1, 1}, l = {408, 409, 410}, m = "updateNoiseLevel", n = {"this", "level", "this", "wasRunning"}, s = {"L$0", "F$0", "L$0", "Z$0"})
/* loaded from: classes3.dex */
public final class FakeHalManager$updateNoiseLevel$1 extends ContinuationImpl {
    float F$0;
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$updateNoiseLevel$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$updateNoiseLevel$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateNoiseLevel(0.0f, this);
    }
}

package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5}, l = {249, 251, 253, 258, 260, 262, 265}, m = "setBackVideo", n = {"this", "this", "ok", "this", "activeSlot", "ok", "this", "ok", "this", "ok", "this", "ok"}, s = {"L$0", "L$0", "Z$0", "L$0", "L$1", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
/* loaded from: classes3.dex */
public final class FakeHalManager$setBackVideo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$setBackVideo$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$setBackVideo$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.setBackVideo(null, null, this);
    }
}

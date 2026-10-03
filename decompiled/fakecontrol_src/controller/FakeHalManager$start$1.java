package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8}, l = {668, 677, 685, 689, 692, 693, 696, 697, 698, 705, 712, 715, 716, 718}, m = "start", n = {"backVideo", "frontVideo", "backVideo", "frontVideo", "backVideo", "frontVideo", "backVideo", "frontVideo", "backVideo", "frontVideo", "backVideo", "frontVideo", "backVideo", "frontVideo"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class FakeHalManager$start$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$start$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$start$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.start(this);
    }
}

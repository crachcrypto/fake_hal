package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {0, 0, 1, 2, 2}, l = {171, 175, 197}, m = "copyMediaToTarget", n = {"target", "tempMp4", "tempMp4", "tempFile", "transcodedFile"}, s = {"L$0", "L$1", "L$0", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class FakeHalManager$copyMediaToTarget$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$copyMediaToTarget$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$copyMediaToTarget$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.copyMediaToTarget(null, null, null, this);
    }
}

package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FakeHalManager.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.FakeHalManager", f = "FakeHalManager.kt", i = {}, l = {56}, m = "isProviderRunning", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FakeHalManager$isProviderRunning$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FakeHalManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FakeHalManager$isProviderRunning$1(FakeHalManager fakeHalManager, Continuation<? super FakeHalManager$isProviderRunning$1> continuation) {
        super(continuation);
        this.this$0 = fakeHalManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object isProviderRunning;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        isProviderRunning = this.this$0.isProviderRunning(this);
        return isProviderRunning;
    }
}

package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: RootShell.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/fakehal/controller/RootShell;", "", "()V", "exec", "Lcom/fakehal/controller/RootShell$Result;", "command", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isRootAvailable", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Result", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RootShell {
    public static final int $stable = 0;
    public static final RootShell INSTANCE = new RootShell();

    /* compiled from: RootShell.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/fakehal/controller/RootShell$Result;", "", "exitCode", "", "output", "", "error", "(ILjava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getExitCode", "()I", "getOutput", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class Result {
        public static final int $stable = 0;
        private final String error;
        private final int exitCode;
        private final String output;

        public static /* synthetic */ Result copy$default(Result result, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = result.exitCode;
            }
            if ((i2 & 2) != 0) {
                str = result.output;
            }
            if ((i2 & 4) != 0) {
                str2 = result.error;
            }
            return result.copy(i, str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getExitCode() {
            return this.exitCode;
        }

        /* renamed from: component2, reason: from getter */
        public final String getOutput() {
            return this.output;
        }

        /* renamed from: component3, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final Result copy(int exitCode, String output, String error) {
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(error, "error");
            return new Result(exitCode, output, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.exitCode == result.exitCode && Intrinsics.areEqual(this.output, result.output) && Intrinsics.areEqual(this.error, result.error);
        }

        public int hashCode() {
            return (((Integer.hashCode(this.exitCode) * 31) + this.output.hashCode()) * 31) + this.error.hashCode();
        }

        public String toString() {
            return "Result(exitCode=" + this.exitCode + ", output=" + this.output + ", error=" + this.error + ")";
        }

        public Result(int exitCode, String output, String error) {
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(error, "error");
            this.exitCode = exitCode;
            this.output = output;
            this.error = error;
        }

        public final int getExitCode() {
            return this.exitCode;
        }

        public final String getOutput() {
            return this.output;
        }

        public final String getError() {
            return this.error;
        }
    }

    private RootShell() {
    }

    public final Object exec(String command, Continuation<? super Result> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RootShell$exec$2(command, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object isRootAvailable(Continuation<? super Boolean> continuation) {
        RootShell$isRootAvailable$1 rootShell$isRootAvailable$1;
        RootShell$isRootAvailable$1 rootShell$isRootAvailable$12;
        Object exec;
        if (continuation instanceof RootShell$isRootAvailable$1) {
            rootShell$isRootAvailable$1 = (RootShell$isRootAvailable$1) continuation;
            if ((rootShell$isRootAvailable$1.label & Integer.MIN_VALUE) != 0) {
                rootShell$isRootAvailable$1.label -= Integer.MIN_VALUE;
                rootShell$isRootAvailable$12 = rootShell$isRootAvailable$1;
                Object $result = rootShell$isRootAvailable$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (rootShell$isRootAvailable$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        rootShell$isRootAvailable$12.label = 1;
                        exec = exec("id", rootShell$isRootAvailable$12);
                        if (exec == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        exec = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Result result = (Result) exec;
                return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) result.getOutput(), (CharSequence) "uid=0", false, 2, (Object) null));
            }
        }
        rootShell$isRootAvailable$1 = new RootShell$isRootAvailable$1(this, continuation);
        rootShell$isRootAvailable$12 = rootShell$isRootAvailable$1;
        Object $result2 = rootShell$isRootAvailable$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (rootShell$isRootAvailable$12.label) {
        }
        Result result2 = (Result) exec;
        return Boxing.boxBoolean(StringsKt.contains$default((CharSequence) result2.getOutput(), (CharSequence) "uid=0", false, 2, (Object) null));
    }
}

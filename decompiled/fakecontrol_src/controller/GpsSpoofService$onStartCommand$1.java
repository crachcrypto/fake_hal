package com.fakehal.controller;

import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: GpsSpoofService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.GpsSpoofService$onStartCommand$1", f = "GpsSpoofService.kt", i = {0, 0}, l = {117}, m = "invokeSuspend", n = {"$this$launch", "lm"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
final class GpsSpoofService$onStartCommand$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lon;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ GpsSpoofService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GpsSpoofService$onStartCommand$1(GpsSpoofService gpsSpoofService, double d, double d2, Continuation<? super GpsSpoofService$onStartCommand$1> continuation) {
        super(2, continuation);
        this.this$0 = gpsSpoofService;
        this.$lat = d;
        this.$lon = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GpsSpoofService$onStartCommand$1 gpsSpoofService$onStartCommand$1 = new GpsSpoofService$onStartCommand$1(this.this$0, this.$lat, this.$lon, continuation);
        gpsSpoofService$onStartCommand$1.L$0 = obj;
        return gpsSpoofService$onStartCommand$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GpsSpoofService$onStartCommand$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        GpsSpoofService$onStartCommand$1 gpsSpoofService$onStartCommand$1;
        CoroutineScope $this$launch;
        LocationManager lm;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                gpsSpoofService$onStartCommand$1 = this;
                $this$launch = (CoroutineScope) gpsSpoofService$onStartCommand$1.L$0;
                Object systemService = gpsSpoofService$onStartCommand$1.this$0.getSystemService("location");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
                lm = (LocationManager) systemService;
                try {
                    lm.removeTestProvider("gps");
                } catch (Exception e) {
                }
                try {
                    lm.addTestProvider("gps", false, false, false, false, true, true, true, 1, 1);
                    lm.setTestProviderEnabled("gps", true);
                    break;
                } catch (Exception e2) {
                    Log.e("GpsSpoofService", "Failed to add test provider", e2);
                    gpsSpoofService$onStartCommand$1.this$0.stopSelf();
                    return Unit.INSTANCE;
                }
            case 1:
                LocationManager lm2 = (LocationManager) this.L$1;
                $this$launch = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                lm = lm2;
                gpsSpoofService$onStartCommand$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch)) {
            try {
                Location loc = new Location("gps");
                double d = gpsSpoofService$onStartCommand$1.$lat;
                double d2 = gpsSpoofService$onStartCommand$1.$lon;
                loc.setLatitude(d);
                loc.setLongitude(d2);
                loc.setAltitude(0.0d);
                loc.setAccuracy(5.0f);
                loc.setSpeed(0.0f);
                loc.setBearing(0.0f);
                loc.setTime(System.currentTimeMillis());
                loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                lm.setTestProviderLocation("gps", loc);
            } catch (Exception e3) {
                Log.e("GpsSpoofService", "Failed to set location", e3);
            }
            gpsSpoofService$onStartCommand$1.L$0 = $this$launch;
            gpsSpoofService$onStartCommand$1.L$1 = lm;
            gpsSpoofService$onStartCommand$1.label = 1;
            if (DelayKt.delay(1000L, gpsSpoofService$onStartCommand$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}

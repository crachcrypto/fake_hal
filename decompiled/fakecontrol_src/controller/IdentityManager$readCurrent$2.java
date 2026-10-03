package com.fakehal.controller;

import android.util.Log;
import com.fakehal.controller.IdentityManager;
import com.fakehal.controller.RootShell;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/fakehal/controller/IdentityManager$DeviceProfile;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.fakehal.controller.IdentityManager$readCurrent$2", f = "IdentityManager.kt", i = {0, 0, 1}, l = {48, 62}, m = "invokeSuspend", n = {"profile", "field", "profile"}, s = {"L$0", "L$2", "L$0"})
/* loaded from: classes3.dex */
public final class IdentityManager$readCurrent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IdentityManager.DeviceProfile>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityManager$readCurrent$2(Continuation<? super IdentityManager$readCurrent$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IdentityManager$readCurrent$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IdentityManager.DeviceProfile> continuation) {
        return ((IdentityManager$readCurrent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x013c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0018. Please report as an issue. */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x02c1: MOVE (r3 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]), block:B:86:0x02c1 */
    /* JADX WARN: Removed duplicated region for block: B:28:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d6 A[Catch: Exception -> 0x02b9, TRY_LEAVE, TryCatch #3 {Exception -> 0x02b9, blocks: (B:31:0x00d0, B:33:0x00d6), top: B:30:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0141 A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0167 A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018f A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b7 A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01df A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0207 A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x022f A[Catch: Exception -> 0x0260, TryCatch #0 {Exception -> 0x0260, blocks: (B:26:0x0126, B:27:0x013c, B:51:0x0141, B:54:0x0148, B:56:0x0167, B:59:0x016e, B:60:0x018f, B:63:0x0196, B:64:0x01b7, B:67:0x01be, B:68:0x01df, B:71:0x01e7, B:72:0x0207, B:75:0x020f, B:76:0x022f, B:78:0x0235), top: B:25:0x0126 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x011e -> B:24:0x0126). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        Object $result3;
        Object obj;
        IdentityManager.DeviceProfile profile;
        Iterator it;
        Object $result4;
        boolean z;
        IdentityManager.DeviceProfile profile2;
        String field;
        Object $result5;
        IdentityManager$readCurrent$2 identityManager$readCurrent$2;
        IdentityManager.DeviceProfile copy;
        IdentityManager.DeviceProfile copy2;
        IdentityManager.DeviceProfile copy3;
        IdentityManager.DeviceProfile copy4;
        IdentityManager.DeviceProfile copy5;
        IdentityManager.DeviceProfile copy6;
        IdentityManager.DeviceProfile copy7;
        IdentityManager.DeviceProfile copy8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (Exception e) {
            e = e;
            $result3 = $result2;
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Map props = MapsKt.mapOf(TuplesKt.to("manufacturer", "ro.product.manufacturer"), TuplesKt.to("model", "ro.product.model"), TuplesKt.to("brand", "ro.product.brand"), TuplesKt.to("device", "ro.product.device"), TuplesKt.to("product", "ro.product.name"), TuplesKt.to("fingerprint", "ro.build.fingerprint"), TuplesKt.to("buildId", "ro.build.display.id"));
                IdentityManager.DeviceProfile profile3 = new IdentityManager.DeviceProfile(null, null, null, null, null, null, null, null, null, null, 1023, null);
                Iterator it2 = props.entrySet().iterator();
                Object $result6 = $result;
                IdentityManager$readCurrent$2 identityManager$readCurrent$22 = this;
                obj = coroutine_suspended;
                profile = profile3;
                it = it2;
                try {
                    if (!it.hasNext()) {
                        try {
                            Map.Entry entry = (Map.Entry) it.next();
                            String field2 = (String) entry.getKey();
                            String prop = (String) entry.getValue();
                            RootShell rootShell = RootShell.INSTANCE;
                            String str = "getprop " + prop + " 2>/dev/null";
                            IdentityManager$readCurrent$2 identityManager$readCurrent$23 = identityManager$readCurrent$22;
                            identityManager$readCurrent$22.L$0 = profile;
                            identityManager$readCurrent$22.L$1 = it;
                            identityManager$readCurrent$22.L$2 = field2;
                            z = true;
                            identityManager$readCurrent$22.label = 1;
                            Object exec = rootShell.exec(str, identityManager$readCurrent$23);
                            if (exec == obj) {
                                return obj;
                            }
                            profile2 = profile;
                            field = field2;
                            IdentityManager$readCurrent$2 identityManager$readCurrent$24 = identityManager$readCurrent$22;
                            $result5 = exec;
                            identityManager$readCurrent$2 = identityManager$readCurrent$24;
                            try {
                                RootShell.Result result = (RootShell.Result) $result5;
                                String value = StringsKt.trim((CharSequence) result.getOutput()).toString();
                                switch (field.hashCode()) {
                                    case -1969347631:
                                        if (field.equals("manufacturer")) {
                                            copy = r27.copy((r22 & 1) != 0 ? r27.manufacturer : value, (r22 & 2) != 0 ? r27.model : null, (r22 & 4) != 0 ? r27.brand : null, (r22 & 8) != 0 ? r27.device : null, (r22 & 16) != 0 ? r27.product : null, (r22 & 32) != 0 ? r27.fingerprint : null, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                                $result4 = $result6;
                                                identityManager$readCurrent$22.L$0 = profile;
                                                identityManager$readCurrent$22.L$1 = null;
                                                identityManager$readCurrent$22.L$2 = null;
                                                identityManager$readCurrent$22.label = 2;
                                                $result3 = RootShell.INSTANCE.exec("getprop ro.serialno 2>/dev/null", identityManager$readCurrent$22);
                                                if ($result3 != obj) {
                                                    try {
                                                        RootShell.Result serialResult = (RootShell.Result) $result3;
                                                        copy8 = r19.copy((r22 & 1) != 0 ? r19.manufacturer : null, (r22 & 2) != 0 ? r19.model : null, (r22 & 4) != 0 ? r19.brand : null, (r22 & 8) != 0 ? r19.device : null, (r22 & 16) != 0 ? r19.product : null, (r22 & 32) != 0 ? r19.fingerprint : null, (r22 & 64) != 0 ? r19.buildId : null, (r22 & 128) != 0 ? r19.serial : StringsKt.trim((CharSequence) serialResult.getOutput()).toString(), (r22 & 256) != 0 ? r19.androidId : null, (r22 & 512) != 0 ? profile.imei : null);
                                                        return copy8;
                                                    } catch (Exception e2) {
                                                        e = e2;
                                                        $result3 = $result4;
                                                        break;
                                                    }
                                                } else {
                                                    return obj;
                                                }
                                            }
                                        }
                                        profile = profile2;
                                        identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                        $result6 = $result4;
                                        if (!it.hasNext()) {
                                        }
                                        break;
                                    case -1375934236:
                                        if (field.equals("fingerprint")) {
                                            copy2 = r27.copy((r22 & 1) != 0 ? r27.manufacturer : null, (r22 & 2) != 0 ? r27.model : null, (r22 & 4) != 0 ? r27.brand : null, (r22 & 8) != 0 ? r27.device : null, (r22 & 16) != 0 ? r27.product : null, (r22 & 32) != 0 ? r27.fingerprint : value, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy2;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    case -1335157162:
                                        if (field.equals("device")) {
                                            copy3 = r27.copy((r22 & 1) != 0 ? r27.manufacturer : null, (r22 & 2) != 0 ? r27.model : null, (r22 & 4) != 0 ? r27.brand : null, (r22 & 8) != 0 ? r27.device : value, (r22 & 16) != 0 ? r27.product : null, (r22 & 32) != 0 ? r27.fingerprint : null, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy3;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    case -309474065:
                                        if (field.equals("product")) {
                                            copy4 = r27.copy((r22 & 1) != 0 ? r27.manufacturer : null, (r22 & 2) != 0 ? r27.model : null, (r22 & 4) != 0 ? r27.brand : null, (r22 & 8) != 0 ? r27.device : null, (r22 & 16) != 0 ? r27.product : value, (r22 & 32) != 0 ? r27.fingerprint : null, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy4;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    case 93997959:
                                        if (field.equals("brand")) {
                                            copy5 = r27.copy((r22 & 1) != 0 ? r27.manufacturer : null, (r22 & 2) != 0 ? r27.model : null, (r22 & 4) != 0 ? r27.brand : value, (r22 & 8) != 0 ? r27.device : null, (r22 & 16) != 0 ? r27.product : null, (r22 & 32) != 0 ? r27.fingerprint : null, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy5;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    case 104069929:
                                        if (field.equals("model")) {
                                            copy6 = r27.copy((r22 & 1) != 0 ? r27.manufacturer : null, (r22 & 2) != 0 ? r27.model : value, (r22 & 4) != 0 ? r27.brand : null, (r22 & 8) != 0 ? r27.device : null, (r22 & 16) != 0 ? r27.product : null, (r22 & 32) != 0 ? r27.fingerprint : null, (r22 & 64) != 0 ? r27.buildId : null, (r22 & 128) != 0 ? r27.serial : null, (r22 & 256) != 0 ? r27.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy6;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    case 230943785:
                                        if (field.equals("buildId")) {
                                            copy7 = r19.copy((r22 & 1) != 0 ? r19.manufacturer : null, (r22 & 2) != 0 ? r19.model : null, (r22 & 4) != 0 ? r19.brand : null, (r22 & 8) != 0 ? r19.device : null, (r22 & 16) != 0 ? r19.product : null, (r22 & 32) != 0 ? r19.fingerprint : null, (r22 & 64) != 0 ? r19.buildId : value, (r22 & 128) != 0 ? r19.serial : null, (r22 & 256) != 0 ? r19.androidId : null, (r22 & 512) != 0 ? profile2.imei : null);
                                            profile2 = copy7;
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        } else {
                                            profile = profile2;
                                            identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                            $result6 = $result4;
                                            if (!it.hasNext()) {
                                            }
                                        }
                                        break;
                                    default:
                                        profile = profile2;
                                        identityManager$readCurrent$22 = identityManager$readCurrent$2;
                                        $result6 = $result4;
                                        if (!it.hasNext()) {
                                        }
                                        break;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                $result3 = $result4;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            $result3 = $result4;
                        }
                        $result4 = $result6;
                    }
                    e = e4;
                    $result3 = $result4;
                } catch (Exception e5) {
                    e = e5;
                    $result3 = $result6;
                }
                Log.e("IdentityManager", "readCurrent failed", e);
                return new IdentityManager.DeviceProfile(null, null, null, null, null, null, null, null, null, null, 1023, null);
            case 1:
                $result5 = $result;
                String field3 = (String) this.L$2;
                it = (Iterator) this.L$1;
                profile2 = (IdentityManager.DeviceProfile) this.L$0;
                ResultKt.throwOnFailure($result5);
                identityManager$readCurrent$2 = this;
                $result4 = $result5;
                obj = coroutine_suspended;
                field = field3;
                z = true;
                RootShell.Result result2 = (RootShell.Result) $result5;
                String value2 = StringsKt.trim((CharSequence) result2.getOutput()).toString();
                switch (field.hashCode()) {
                    case -1969347631:
                        break;
                    case -1375934236:
                        break;
                    case -1335157162:
                        break;
                    case -309474065:
                        break;
                    case 93997959:
                        break;
                    case 104069929:
                        break;
                    case 230943785:
                        break;
                }
            case 2:
                $result3 = $result;
                profile = (IdentityManager.DeviceProfile) this.L$0;
                try {
                    ResultKt.throwOnFailure($result3);
                    $result4 = $result3;
                    RootShell.Result serialResult2 = (RootShell.Result) $result3;
                    copy8 = r19.copy((r22 & 1) != 0 ? r19.manufacturer : null, (r22 & 2) != 0 ? r19.model : null, (r22 & 4) != 0 ? r19.brand : null, (r22 & 8) != 0 ? r19.device : null, (r22 & 16) != 0 ? r19.product : null, (r22 & 32) != 0 ? r19.fingerprint : null, (r22 & 64) != 0 ? r19.buildId : null, (r22 & 128) != 0 ? r19.serial : StringsKt.trim((CharSequence) serialResult2.getOutput()).toString(), (r22 & 256) != 0 ? r19.androidId : null, (r22 & 512) != 0 ? profile.imei : null);
                    return copy8;
                } catch (Exception e6) {
                    e = e6;
                    break;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

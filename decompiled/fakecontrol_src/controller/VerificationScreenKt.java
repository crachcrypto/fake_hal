package com.fakehal.controller;

import android.content.Context;
import android.hardware.SensorManager;
import android.net.Uri;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ExpandLessKt;
import androidx.compose.material.icons.filled.ExpandMoreKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.FilterChipDefaults;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.fakehal.controller.ImageToVideoConverter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VerificationScreen.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001añ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\f\u001a\u00020\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u000e\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0003¢\u0006\u0002\u0010\u0019\u001a\u001b\u0010\u001a\u001a\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0007¢\u0006\u0002\u0010\u001c\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001fH\u0003¨\u0006 ²\u0006\n\u0010\u0002\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\nX\u008a\u008e\u0002²\u0006\f\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u008a\u008e\u0002"}, d2 = {"ControlPanel", "", "selectedCamera", "", "onCameraSwitch", "Lkotlin/Function1;", "rotation", "", "onRotationChange", "mirrorH", "", "onMirrorHChange", "mirrorV", "onMirrorVChange", "gyroEnabled", "onGyroToggle", "Lkotlin/Function0;", "hasGyro", "isApplying", "onPickPhoto", "onPickVideo", "statusText", "urlText", "onUrlChange", "onGo", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function0;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "VerificationScreen", "onBack", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "setupWebView", "webView", "Landroid/webkit/WebView;", "app_debug", "showControls"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VerificationScreenKt {
    public static final void VerificationScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv$iv$iv;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv8;
        Object value$iv9;
        Object value$iv10;
        Object value$iv11;
        Object value$iv12;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(-411094291);
        ComposerKt.sourceInformation($composer3, "C(VerificationScreen)39@1524L7,40@1548L24,43@1613L35,44@1690L33,45@1743L34,46@1797L34,47@1855L34,48@1912L36,49@1971L34,50@2025L39,51@2089L33,54@2184L78,55@2281L74,59@2407L1228,87@3687L1377,118@5110L43,120@5159L3374:VerificationScreen.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 11) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-411094291, $dirty2, -1, "com.fakehal.controller.VerificationScreen (VerificationScreen.kt:38)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910735);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("back", null, 2, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState selectedCamera$delegate = (MutableState) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910658);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableIntState rotation$delegate = (MutableIntState) value$iv2;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910605);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState mirrorH$delegate = (MutableState) value$iv3;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910551);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState mirrorV$delegate = (MutableState) value$iv4;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910493);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState gyroEnabled$delegate = (MutableState) value$iv5;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910436);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv6 = $composer3.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("Ready", null, 2, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            final MutableState statusText$delegate = (MutableState) value$iv6;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910377);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv7 = $composer3.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            final MutableState isApplying$delegate = (MutableState) value$iv7;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910323);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv8 = $composer3.rememberedValue();
            if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                value$iv8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("https://", null, 2, null);
                $composer3.updateRememberedValue(value$iv8);
            } else {
                value$iv8 = it$iv8;
            }
            final MutableState urlText$delegate = (MutableState) value$iv8;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910259);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv9 = $composer3.rememberedValue();
            if (it$iv9 == Composer.INSTANCE.getEmpty()) {
                value$iv9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(value$iv9);
            } else {
                value$iv9 = it$iv9;
            }
            final MutableState showControls$delegate = (MutableState) value$iv9;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910164);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv10 = $composer3.rememberedValue();
            if (it$iv10 == Composer.INSTANCE.getEmpty()) {
                Object systemService = context.getSystemService("sensor");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
                value$iv10 = (SensorManager) systemService;
                $composer3.updateRememberedValue(value$iv10);
            } else {
                value$iv10 = it$iv10;
            }
            SensorManager sensorManager = (SensorManager) value$iv10;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-253910067);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv11 = $composer3.rememberedValue();
            if (it$iv11 == Composer.INSTANCE.getEmpty()) {
                value$iv11 = Boolean.valueOf(sensorManager.getDefaultSensor(4) != null);
                $composer3.updateRememberedValue(value$iv11);
            } else {
                value$iv11 = it$iv11;
            }
            final boolean hasGyro = ((Boolean) value$iv11).booleanValue();
            $composer3.endReplaceableGroup();
            final ManagedActivityResultLauncher photoPicker = ActivityResultRegistryKt.rememberLauncherForActivityResult(new ActivityResultContracts.GetContent(), new Function1<Uri, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$photoPicker$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Uri uri) {
                    invoke2(uri);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: VerificationScreen.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "com.fakehal.controller.VerificationScreenKt$VerificationScreen$photoPicker$1$1", f = "VerificationScreen.kt", i = {0}, l = {Base64.mimeLineLength}, m = "invokeSuspend", n = {"cam"}, s = {"L$0"})
                /* renamed from: com.fakehal.controller.VerificationScreenKt$VerificationScreen$photoPicker$1$1, reason: invalid class name */
                /* loaded from: classes3.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    final /* synthetic */ MutableState<Boolean> $isApplying$delegate;
                    final /* synthetic */ MutableState<Boolean> $mirrorH$delegate;
                    final /* synthetic */ MutableState<Boolean> $mirrorV$delegate;
                    final /* synthetic */ MutableIntState $rotation$delegate;
                    final /* synthetic */ MutableState<String> $selectedCamera$delegate;
                    final /* synthetic */ MutableState<String> $statusText$delegate;
                    final /* synthetic */ Uri $uri;
                    Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Context context, Uri uri, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableIntState mutableIntState, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$context = context;
                        this.$uri = uri;
                        this.$isApplying$delegate = mutableState;
                        this.$selectedCamera$delegate = mutableState2;
                        this.$statusText$delegate = mutableState3;
                        this.$rotation$delegate = mutableIntState;
                        this.$mirrorH$delegate = mutableState4;
                        this.$mirrorV$delegate = mutableState5;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$context, this.$uri, this.$isApplying$delegate, this.$selectedCamera$delegate, this.$statusText$delegate, this.$rotation$delegate, this.$mirrorH$delegate, this.$mirrorV$delegate, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
                    /* JADX WARN: Removed duplicated region for block: B:13:0x00a4 A[Catch: Exception -> 0x00be, TryCatch #1 {Exception -> 0x00be, blocks: (B:11:0x009a, B:13:0x00a4, B:14:0x00ba), top: B:10:0x009a }] */
                    /* JADX WARN: Removed duplicated region for block: B:18:0x00b8  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object invokeSuspend(Object $result) {
                        AnonymousClass1 anonymousClass1;
                        String VerificationScreen$lambda$1;
                        AnonymousClass1 anonymousClass12;
                        String VerificationScreen$lambda$12;
                        String cam;
                        int VerificationScreen$lambda$4;
                        boolean VerificationScreen$lambda$7;
                        boolean VerificationScreen$lambda$10;
                        Object applyWithTransform;
                        String cam2;
                        Object $result2;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                anonymousClass1 = this;
                                VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, true);
                                MutableState<String> mutableState = anonymousClass1.$statusText$delegate;
                                VerificationScreen$lambda$1 = VerificationScreenKt.VerificationScreen$lambda$1(anonymousClass1.$selectedCamera$delegate);
                                mutableState.setValue("Applying photo to " + VerificationScreen$lambda$1 + "...");
                                try {
                                    VerificationScreen$lambda$12 = VerificationScreenKt.VerificationScreen$lambda$1(anonymousClass1.$selectedCamera$delegate);
                                    cam = Intrinsics.areEqual(VerificationScreen$lambda$12, "back") ? "back" : "front";
                                    VerificationScreen$lambda$4 = VerificationScreenKt.VerificationScreen$lambda$4(anonymousClass1.$rotation$delegate);
                                    VerificationScreen$lambda$7 = VerificationScreenKt.VerificationScreen$lambda$7(anonymousClass1.$mirrorH$delegate);
                                    VerificationScreen$lambda$10 = VerificationScreenKt.VerificationScreen$lambda$10(anonymousClass1.$mirrorV$delegate);
                                    ImageToVideoConverter.TransformConfig config = new ImageToVideoConverter.TransformConfig(VerificationScreen$lambda$4, VerificationScreen$lambda$7, VerificationScreen$lambda$10, 90, "crop", Intrinsics.areEqual(cam, "front"));
                                    anonymousClass1.L$0 = cam;
                                    anonymousClass1.label = 1;
                                    applyWithTransform = FakeHalManager.INSTANCE.applyWithTransform(anonymousClass1.$context, anonymousClass1.$uri, cam, config, anonymousClass1);
                                } catch (Exception e) {
                                    e = e;
                                    anonymousClass12 = anonymousClass1;
                                    anonymousClass12.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    anonymousClass1 = anonymousClass12;
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                if (applyWithTransform == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                cam2 = cam;
                                $result2 = $result;
                                $result = applyWithTransform;
                                try {
                                    boolean ok = ((Boolean) $result).booleanValue();
                                    anonymousClass1.$statusText$delegate.setValue(!ok ? "✓ Applied to " + cam2 : "✗ Failed");
                                } catch (Exception e2) {
                                    e = e2;
                                    $result = $result2;
                                    anonymousClass12 = anonymousClass1;
                                    anonymousClass12.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    anonymousClass1 = anonymousClass12;
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                return Unit.INSTANCE;
                            case 1:
                                anonymousClass12 = this;
                                cam2 = (String) anonymousClass12.L$0;
                                try {
                                    ResultKt.throwOnFailure($result);
                                    anonymousClass1 = anonymousClass12;
                                    $result2 = $result;
                                    boolean ok2 = ((Boolean) $result).booleanValue();
                                    anonymousClass1.$statusText$delegate.setValue(!ok2 ? "✓ Applied to " + cam2 : "✗ Failed");
                                } catch (Exception e3) {
                                    e = e3;
                                    anonymousClass12.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    anonymousClass1 = anonymousClass12;
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Uri uri) {
                    if (uri != null) {
                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(context, uri, isApplying$delegate, selectedCamera$delegate, statusText$delegate, rotation$delegate, mirrorH$delegate, mirrorV$delegate, null), 3, null);
                    }
                }
            }, $composer3, 8);
            final ManagedActivityResultLauncher videoPicker = ActivityResultRegistryKt.rememberLauncherForActivityResult(new ActivityResultContracts.GetContent(), new Function1<Uri, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$videoPicker$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Uri uri) {
                    invoke2(uri);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: VerificationScreen.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "com.fakehal.controller.VerificationScreenKt$VerificationScreen$videoPicker$1$1", f = "VerificationScreen.kt", i = {0}, l = {96}, m = "invokeSuspend", n = {"cam"}, s = {"L$0"})
                /* renamed from: com.fakehal.controller.VerificationScreenKt$VerificationScreen$videoPicker$1$1, reason: invalid class name */
                /* loaded from: classes3.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    final /* synthetic */ MutableState<Boolean> $isApplying$delegate;
                    final /* synthetic */ MutableState<Boolean> $mirrorH$delegate;
                    final /* synthetic */ MutableState<Boolean> $mirrorV$delegate;
                    final /* synthetic */ MutableIntState $rotation$delegate;
                    final /* synthetic */ MutableState<String> $selectedCamera$delegate;
                    final /* synthetic */ MutableState<String> $statusText$delegate;
                    final /* synthetic */ Uri $uri;
                    Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Context context, Uri uri, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableIntState mutableIntState, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$context = context;
                        this.$uri = uri;
                        this.$isApplying$delegate = mutableState;
                        this.$selectedCamera$delegate = mutableState2;
                        this.$statusText$delegate = mutableState3;
                        this.$rotation$delegate = mutableIntState;
                        this.$mirrorH$delegate = mutableState4;
                        this.$mirrorV$delegate = mutableState5;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$context, this.$uri, this.$isApplying$delegate, this.$selectedCamera$delegate, this.$statusText$delegate, this.$rotation$delegate, this.$mirrorH$delegate, this.$mirrorV$delegate, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
                    /* JADX WARN: Removed duplicated region for block: B:13:0x00ac A[Catch: Exception -> 0x00c6, TryCatch #1 {Exception -> 0x00c6, blocks: (B:11:0x00a1, B:13:0x00ac, B:14:0x00c2), top: B:10:0x00a1 }] */
                    /* JADX WARN: Removed duplicated region for block: B:18:0x00c0  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object invokeSuspend(Object obj) {
                        Object $result;
                        String VerificationScreen$lambda$1;
                        AnonymousClass1 anonymousClass1;
                        Object $result2;
                        String VerificationScreen$lambda$12;
                        String cam;
                        int VerificationScreen$lambda$4;
                        boolean VerificationScreen$lambda$7;
                        boolean VerificationScreen$lambda$10;
                        Object applyWithTransform;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure(obj);
                                $result = obj;
                                VerificationScreenKt.VerificationScreen$lambda$20(this.$isApplying$delegate, true);
                                MutableState<String> mutableState = this.$statusText$delegate;
                                VerificationScreen$lambda$1 = VerificationScreenKt.VerificationScreen$lambda$1(this.$selectedCamera$delegate);
                                mutableState.setValue("Applying video to " + VerificationScreen$lambda$1 + "...");
                                try {
                                    VerificationScreen$lambda$12 = VerificationScreenKt.VerificationScreen$lambda$1(this.$selectedCamera$delegate);
                                    cam = Intrinsics.areEqual(VerificationScreen$lambda$12, "back") ? "back" : "front";
                                    FakeHalManager fakeHalManager = FakeHalManager.INSTANCE;
                                    Context context = this.$context;
                                    Uri uri = this.$uri;
                                    VerificationScreen$lambda$4 = VerificationScreenKt.VerificationScreen$lambda$4(this.$rotation$delegate);
                                    VerificationScreen$lambda$7 = VerificationScreenKt.VerificationScreen$lambda$7(this.$mirrorH$delegate);
                                    VerificationScreen$lambda$10 = VerificationScreenKt.VerificationScreen$lambda$10(this.$mirrorV$delegate);
                                    this.L$0 = cam;
                                    this.label = 1;
                                    applyWithTransform = fakeHalManager.applyWithTransform(context, uri, cam, new ImageToVideoConverter.TransformConfig(VerificationScreen$lambda$4, VerificationScreen$lambda$7, VerificationScreen$lambda$10, 90, "crop", Intrinsics.areEqual(cam, "front")), this);
                                } catch (Exception e) {
                                    e = e;
                                    anonymousClass1 = this;
                                    $result2 = $result;
                                    anonymousClass1.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                if (applyWithTransform == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                anonymousClass1 = this;
                                $result2 = applyWithTransform;
                                try {
                                    boolean ok = ((Boolean) $result2).booleanValue();
                                    anonymousClass1.$statusText$delegate.setValue(!ok ? "✓ Video applied to " + cam : "✗ Failed");
                                } catch (Exception e2) {
                                    e = e2;
                                    $result2 = $result;
                                    anonymousClass1.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                return Unit.INSTANCE;
                            case 1:
                                anonymousClass1 = this;
                                $result2 = obj;
                                cam = (String) anonymousClass1.L$0;
                                try {
                                    ResultKt.throwOnFailure($result2);
                                    $result = $result2;
                                    boolean ok2 = ((Boolean) $result2).booleanValue();
                                    anonymousClass1.$statusText$delegate.setValue(!ok2 ? "✓ Video applied to " + cam : "✗ Failed");
                                } catch (Exception e3) {
                                    e = e3;
                                    anonymousClass1.$statusText$delegate.setValue("✗ Error: " + e.getMessage());
                                    VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                    return Unit.INSTANCE;
                                }
                                VerificationScreenKt.VerificationScreen$lambda$20(anonymousClass1.$isApplying$delegate, false);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Uri uri) {
                    if (uri != null) {
                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(context, uri, isApplying$delegate, selectedCamera$delegate, statusText$delegate, rotation$delegate, mirrorH$delegate, mirrorV$delegate, null), 3, null);
                    }
                }
            }, $composer3, 8);
            $composer3.startReplaceableGroup(-253907238);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            Object it$iv12 = $composer3.rememberedValue();
            if (it$iv12 == Composer.INSTANCE.getEmpty()) {
                value$iv12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv12);
            } else {
                value$iv12 = it$iv12;
            }
            final MutableState webView$delegate = (MutableState) value$iv12;
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            ScaffoldKt.m1784ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, -1864795471, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C139@5974L155,122@5200L944:VerificationScreen.kt#2o9c7b");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1864795471, $changed2, -1, "com.fakehal.controller.VerificationScreen.<anonymous> (VerificationScreen.kt:122)");
                        }
                        Function2<Composer, Integer, Unit> m6077getLambda1$app_debug = ComposableSingletons$VerificationScreenKt.INSTANCE.m6077getLambda1$app_debug();
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 95333035, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C125@5334L135:VerificationScreen.kt#2o9c7b");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(95333035, $changed3, -1, "com.fakehal.controller.VerificationScreen.<anonymous>.<anonymous> (VerificationScreen.kt:125)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6079getLambda2$app_debug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        final MutableState<Boolean> mutableState = showControls$delegate;
                        AppBarKt.TopAppBar(m6077getLambda1$app_debug, null, composableLambda, ComposableLambdaKt.composableLambda($composer4, 1721963170, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                invoke(rowScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(RowScope TopAppBar, Composer $composer5, int $changed3) {
                                Object value$iv13;
                                Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
                                ComposerKt.sourceInformation($composer5, "C131@5608L32,131@5587L304:VerificationScreen.kt#2o9c7b");
                                if (($changed3 & 81) == 16 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1721963170, $changed3, -1, "com.fakehal.controller.VerificationScreen.<anonymous>.<anonymous> (VerificationScreen.kt:131)");
                                }
                                $composer5.startReplaceableGroup(-1349312626);
                                ComposerKt.sourceInformation($composer5, "CC(remember):VerificationScreen.kt#9igjgp");
                                final MutableState<Boolean> mutableState2 = mutableState;
                                Object it$iv13 = $composer5.rememberedValue();
                                if (it$iv13 == Composer.INSTANCE.getEmpty()) {
                                    value$iv13 = new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$1$2$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            boolean VerificationScreen$lambda$25;
                                            MutableState<Boolean> mutableState3 = mutableState2;
                                            VerificationScreen$lambda$25 = VerificationScreenKt.VerificationScreen$lambda$25(mutableState2);
                                            VerificationScreenKt.VerificationScreen$lambda$26(mutableState3, !VerificationScreen$lambda$25);
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv13);
                                } else {
                                    value$iv13 = it$iv13;
                                }
                                $composer5.endReplaceableGroup();
                                final MutableState<Boolean> mutableState3 = mutableState;
                                IconButtonKt.IconButton((Function0) value$iv13, null, false, null, null, ComposableLambdaKt.composableLambda($composer5, 471515135, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt.VerificationScreen.1.2.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer6, int $changed4) {
                                        boolean VerificationScreen$lambda$25;
                                        ComposerKt.sourceInformation($composer6, "C132@5668L201:VerificationScreen.kt#2o9c7b");
                                        if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(471515135, $changed4, -1, "com.fakehal.controller.VerificationScreen.<anonymous>.<anonymous>.<anonymous> (VerificationScreen.kt:132)");
                                            }
                                            VerificationScreen$lambda$25 = VerificationScreenKt.VerificationScreen$lambda$25(mutableState3);
                                            IconKt.m1601Iconww6aTOc(VerificationScreen$lambda$25 ? ExpandLessKt.getExpandLess(Icons.INSTANCE.getDefault()) : ExpandMoreKt.getExpandMore(Icons.INSTANCE.getDefault()), "Toggle controls", (Modifier) null, 0L, $composer6, 48, 12);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer6.skipToGroupEnd();
                                    }
                                }), $composer5, 196614, 30);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), null, TopAppBarDefaults.INSTANCE.m2288topAppBarColorszjMxDiM(ColorKt.Color(4279176995L), 0L, 0L, Color.INSTANCE.m3450getWhite0d7_KjU(), 0L, $composer4, (TopAppBarDefaults.$stable << 15) | 3078, 22), null, $composer4, 3462, 82);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), null, null, null, 0, ColorKt.Color(4279176995L), 0L, null, ComposableLambdaKt.composableLambda($composer3, 1089534396, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                    invoke(paddingValues, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:31:0x01a9  */
                /* JADX WARN: Removed duplicated region for block: B:52:0x039a  */
                /* JADX WARN: Removed duplicated region for block: B:55:0x03f5  */
                /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:58:0x03aa  */
                /* JADX WARN: Removed duplicated region for block: B:65:0x0371  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke(PaddingValues padding, Composer $composer4, int $changed2) {
                    Function0 factory$iv$iv$iv;
                    boolean VerificationScreen$lambda$25;
                    Composer $composer$iv;
                    String str;
                    MutableState<WebView> mutableState;
                    Object it$iv13;
                    Object value$iv13;
                    String VerificationScreen$lambda$1;
                    int VerificationScreen$lambda$4;
                    Object value$iv14;
                    boolean VerificationScreen$lambda$7;
                    Object value$iv15;
                    boolean VerificationScreen$lambda$10;
                    Object value$iv16;
                    boolean VerificationScreen$lambda$13;
                    boolean VerificationScreen$lambda$19;
                    String VerificationScreen$lambda$16;
                    String VerificationScreen$lambda$22;
                    final MutableState<String> mutableState2;
                    Object value$iv17;
                    String str2;
                    final MutableState<WebView> mutableState3;
                    Intrinsics.checkNotNullParameter(padding, "padding");
                    ComposerKt.sourceInformation($composer4, "C147@6227L2300:VerificationScreen.kt#2o9c7b");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(padding) ? 4 : 2;
                    }
                    if (($dirty3 & 91) == 18 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1089534396, $dirty3, -1, "com.fakehal.controller.VerificationScreen.<anonymous> (VerificationScreen.kt:147)");
                    }
                    Modifier modifier$iv = PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding);
                    boolean z = hasGyro;
                    MutableState<Boolean> mutableState4 = showControls$delegate;
                    final MutableState<String> mutableState5 = selectedCamera$delegate;
                    final MutableState<String> mutableState6 = statusText$delegate;
                    final MutableIntState mutableIntState = rotation$delegate;
                    final MutableState<Boolean> mutableState7 = mirrorH$delegate;
                    final MutableState<Boolean> mutableState8 = mirrorV$delegate;
                    final MutableState<Boolean> mutableState9 = gyroEnabled$delegate;
                    final CoroutineScope coroutineScope = scope;
                    MutableState<Boolean> mutableState10 = isApplying$delegate;
                    final ManagedActivityResultLauncher<String, Uri> managedActivityResultLauncher = photoPicker;
                    final ManagedActivityResultLauncher<String, Uri> managedActivityResultLauncher2 = videoPicker;
                    MutableState<String> mutableState11 = urlText$delegate;
                    MutableState<WebView> mutableState12 = webView$delegate;
                    $composer4.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    $composer4.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv2 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
                    int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        factory$iv$iv$iv = factory$iv$iv$iv2;
                        $composer4.createNode(factory$iv$iv$iv);
                    } else {
                        factory$iv$iv$iv = factory$iv$iv$iv2;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer4);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                        int i2 = ((0 >> 6) & 112) | 6;
                        ColumnScope $this$invoke_u24lambda_u247 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1349311810, "C188@7893L477,187@7854L663:VerificationScreen.kt#2o9c7b");
                        $composer4.startReplaceableGroup(-1349311810);
                        ComposerKt.sourceInformation($composer4, "157@6564L127,162@6773L17,164@6869L16,166@6964L16,181@7710L16,182@7755L29,155@6460L1343");
                        VerificationScreen$lambda$25 = VerificationScreenKt.VerificationScreen$lambda$25(mutableState4);
                        if (VerificationScreen$lambda$25) {
                            $composer$iv = $composer4;
                            str = "CC(remember):VerificationScreen.kt#9igjgp";
                            mutableState = mutableState12;
                        } else {
                            VerificationScreen$lambda$1 = VerificationScreenKt.VerificationScreen$lambda$1(mutableState5);
                            $composer4.startReplaceableGroup(-1349311670);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object value$iv18 = $composer4.rememberedValue();
                            if (value$iv18 == Composer.INSTANCE.getEmpty()) {
                                value$iv18 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$1$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                        invoke2(str3);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(String cam) {
                                        Intrinsics.checkNotNullParameter(cam, "cam");
                                        mutableState5.setValue(cam);
                                        mutableState6.setValue("Camera: " + cam);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv18);
                            }
                            Function1 function1 = (Function1) value$iv18;
                            $composer4.endReplaceableGroup();
                            VerificationScreen$lambda$4 = VerificationScreenKt.VerificationScreen$lambda$4(mutableIntState);
                            $composer4.startReplaceableGroup(-1349311461);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object it$iv14 = $composer4.rememberedValue();
                            $composer$iv = $composer4;
                            if (it$iv14 == Composer.INSTANCE.getEmpty()) {
                                value$iv14 = (Function1) new Function1<Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$2$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(int it) {
                                        MutableIntState.this.setIntValue(it);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv14);
                            } else {
                                value$iv14 = it$iv14;
                            }
                            Function1 function12 = (Function1) value$iv14;
                            $composer4.endReplaceableGroup();
                            VerificationScreen$lambda$7 = VerificationScreenKt.VerificationScreen$lambda$7(mutableState7);
                            $composer4.startReplaceableGroup(-1349311365);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object it$iv15 = $composer4.rememberedValue();
                            if (it$iv15 == Composer.INSTANCE.getEmpty()) {
                                value$iv15 = (Function1) new Function1<Boolean, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$3$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean it) {
                                        VerificationScreenKt.VerificationScreen$lambda$8(mutableState7, it);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv15);
                            } else {
                                value$iv15 = it$iv15;
                            }
                            Function1 function13 = (Function1) value$iv15;
                            $composer4.endReplaceableGroup();
                            VerificationScreen$lambda$10 = VerificationScreenKt.VerificationScreen$lambda$10(mutableState8);
                            $composer4.startReplaceableGroup(-1349311270);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object it$iv16 = $composer4.rememberedValue();
                            if (it$iv16 == Composer.INSTANCE.getEmpty()) {
                                value$iv16 = (Function1) new Function1<Boolean, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$4$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean it) {
                                        VerificationScreenKt.VerificationScreen$lambda$11(mutableState8, it);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv16);
                            } else {
                                value$iv16 = it$iv16;
                            }
                            Function1 function14 = (Function1) value$iv16;
                            $composer4.endReplaceableGroup();
                            VerificationScreen$lambda$13 = VerificationScreenKt.VerificationScreen$lambda$13(mutableState9);
                            Function0<Unit> function0 = new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$5
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* compiled from: VerificationScreen.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                @DebugMetadata(c = "com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$5$1", f = "VerificationScreen.kt", i = {}, l = {172}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$5$1, reason: invalid class name */
                                /* loaded from: classes3.dex */
                                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ MutableState<Boolean> $gyroEnabled$delegate;
                                    final /* synthetic */ MutableState<String> $statusText$delegate;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(MutableState<Boolean> mutableState, MutableState<String> mutableState2, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$gyroEnabled$delegate = mutableState;
                                        this.$statusText$delegate = mutableState2;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$gyroEnabled$delegate, this.$statusText$delegate, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        boolean VerificationScreen$lambda$13;
                                        AnonymousClass1 anonymousClass1;
                                        boolean VerificationScreen$lambda$132;
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                FakeHalManager fakeHalManager = FakeHalManager.INSTANCE;
                                                VerificationScreen$lambda$13 = VerificationScreenKt.VerificationScreen$lambda$13(this.$gyroEnabled$delegate);
                                                this.label = 1;
                                                if (fakeHalManager.setGyroEnabled(VerificationScreen$lambda$13, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                anonymousClass1 = this;
                                                break;
                                            case 1:
                                                anonymousClass1 = this;
                                                ResultKt.throwOnFailure($result);
                                                break;
                                            default:
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        MutableState<String> mutableState = anonymousClass1.$statusText$delegate;
                                        VerificationScreen$lambda$132 = VerificationScreenKt.VerificationScreen$lambda$13(anonymousClass1.$gyroEnabled$delegate);
                                        mutableState.setValue(VerificationScreen$lambda$132 ? "Gyroscope ON" : "Gyroscope OFF");
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    boolean VerificationScreen$lambda$132;
                                    MutableState<Boolean> mutableState13 = mutableState9;
                                    VerificationScreen$lambda$132 = VerificationScreenKt.VerificationScreen$lambda$13(mutableState9);
                                    VerificationScreenKt.VerificationScreen$lambda$14(mutableState13, !VerificationScreen$lambda$132);
                                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState9, mutableState6, null), 3, null);
                                }
                            };
                            VerificationScreen$lambda$19 = VerificationScreenKt.VerificationScreen$lambda$19(mutableState10);
                            Function0<Unit> function02 = new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$6
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    managedActivityResultLauncher.launch("image/*");
                                }
                            };
                            Function0<Unit> function03 = new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$7
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    managedActivityResultLauncher2.launch("video/*");
                                }
                            };
                            VerificationScreen$lambda$16 = VerificationScreenKt.VerificationScreen$lambda$16(mutableState6);
                            VerificationScreen$lambda$22 = VerificationScreenKt.VerificationScreen$lambda$22(mutableState11);
                            $composer4.startReplaceableGroup(-1349310524);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object it$iv17 = $composer4.rememberedValue();
                            if (it$iv17 == Composer.INSTANCE.getEmpty()) {
                                mutableState2 = mutableState11;
                                value$iv17 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$8$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                        invoke2(str3);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(String it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        mutableState2.setValue(it);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv17);
                            } else {
                                mutableState2 = mutableState11;
                                value$iv17 = it$iv17;
                            }
                            Function1 function15 = (Function1) value$iv17;
                            $composer4.endReplaceableGroup();
                            $composer4.startReplaceableGroup(-1349310479);
                            ComposerKt.sourceInformation($composer4, "CC(remember):VerificationScreen.kt#9igjgp");
                            Object value$iv19 = $composer4.rememberedValue();
                            if (value$iv19 == Composer.INSTANCE.getEmpty()) {
                                str2 = "CC(remember):VerificationScreen.kt#9igjgp";
                                mutableState3 = mutableState12;
                                value$iv19 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$9$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        WebView VerificationScreen$lambda$30;
                                        String VerificationScreen$lambda$222;
                                        VerificationScreen$lambda$30 = VerificationScreenKt.VerificationScreen$lambda$30(mutableState3);
                                        if (VerificationScreen$lambda$30 != null) {
                                            VerificationScreen$lambda$222 = VerificationScreenKt.VerificationScreen$lambda$22(mutableState2);
                                            VerificationScreen$lambda$30.loadUrl(VerificationScreen$lambda$222);
                                        }
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv19);
                            } else {
                                str2 = "CC(remember):VerificationScreen.kt#9igjgp";
                                mutableState3 = mutableState12;
                            }
                            $composer4.endReplaceableGroup();
                            mutableState = mutableState3;
                            str = str2;
                            VerificationScreenKt.ControlPanel(VerificationScreen$lambda$1, function1, VerificationScreen$lambda$4, function12, VerificationScreen$lambda$7, function13, VerificationScreen$lambda$10, function14, VerificationScreen$lambda$13, function0, z, VerificationScreen$lambda$19, function02, function03, VerificationScreen$lambda$16, VerificationScreen$lambda$22, function15, (Function0) value$iv19, $composer4, 12782640, 14155782);
                        }
                        $composer4.endReplaceableGroup();
                        $composer4.startReplaceableGroup(-1349310341);
                        ComposerKt.sourceInformation($composer4, str);
                        it$iv13 = $composer4.rememberedValue();
                        if (it$iv13 != Composer.INSTANCE.getEmpty()) {
                            final MutableState<WebView> mutableState13 = mutableState;
                            value$iv13 = new Function1<Context, WebView>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$2$1$10$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final WebView invoke(Context ctx) {
                                    Intrinsics.checkNotNullParameter(ctx, "ctx");
                                    WebView $this$invoke_u24lambda_u240 = new WebView(ctx);
                                    MutableState<WebView> mutableState14 = mutableState13;
                                    $this$invoke_u24lambda_u240.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                                    VerificationScreenKt.setupWebView($this$invoke_u24lambda_u240);
                                    mutableState14.setValue($this$invoke_u24lambda_u240);
                                    $this$invoke_u24lambda_u240.loadUrl("about:blank");
                                    return $this$invoke_u24lambda_u240;
                                }
                            };
                            $composer4.updateRememberedValue(value$iv13);
                        } else {
                            value$iv13 = it$iv13;
                        }
                        $composer4.endReplaceableGroup();
                        AndroidView_androidKt.AndroidView((Function1) value$iv13, ColumnScope.weight$default($this$invoke_u24lambda_u247, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), null, $composer4, 6, 4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            return;
                        }
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                    skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                    $composer4.startReplaceableGroup(2058660585);
                    int i3 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                    int i22 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$invoke_u24lambda_u2472 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1349311810, "C188@7893L477,187@7854L663:VerificationScreen.kt#2o9c7b");
                    $composer4.startReplaceableGroup(-1349311810);
                    ComposerKt.sourceInformation($composer4, "157@6564L127,162@6773L17,164@6869L16,166@6964L16,181@7710L16,182@7755L29,155@6460L1343");
                    VerificationScreen$lambda$25 = VerificationScreenKt.VerificationScreen$lambda$25(mutableState4);
                    if (VerificationScreen$lambda$25) {
                    }
                    $composer4.endReplaceableGroup();
                    $composer4.startReplaceableGroup(-1349310341);
                    ComposerKt.sourceInformation($composer4, str);
                    it$iv13 = $composer4.rememberedValue();
                    if (it$iv13 != Composer.INSTANCE.getEmpty()) {
                    }
                    $composer4.endReplaceableGroup();
                    AndroidView_androidKt.AndroidView((Function1) value$iv13, ColumnScope.weight$default($this$invoke_u24lambda_u2472, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), null, $composer4, 6, 4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer4.endReplaceableGroup();
                    $composer4.endNode();
                    $composer4.endReplaceableGroup();
                    $composer4.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
            }), $composer3, 806879280, 445);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$VerificationScreen$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    VerificationScreenKt.VerificationScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String VerificationScreen$lambda$1(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int VerificationScreen$lambda$4(MutableIntState $rotation$delegate) {
        MutableIntState $this$getValue$iv = $rotation$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean VerificationScreen$lambda$7(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerificationScreen$lambda$8(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean VerificationScreen$lambda$10(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerificationScreen$lambda$11(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean VerificationScreen$lambda$13(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerificationScreen$lambda$14(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String VerificationScreen$lambda$16(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean VerificationScreen$lambda$19(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerificationScreen$lambda$20(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String VerificationScreen$lambda$22(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean VerificationScreen$lambda$25(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerificationScreen$lambda$26(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WebView VerificationScreen$lambda$30(MutableState<WebView> mutableState) {
        MutableState<WebView> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupWebView(WebView webView) {
        WebSettings $this$setupWebView_u24lambda_u2432 = webView.getSettings();
        $this$setupWebView_u24lambda_u2432.setJavaScriptEnabled(true);
        $this$setupWebView_u24lambda_u2432.setDomStorageEnabled(true);
        $this$setupWebView_u24lambda_u2432.setMediaPlaybackRequiresUserGesture(false);
        $this$setupWebView_u24lambda_u2432.setAllowFileAccess(true);
        $this$setupWebView_u24lambda_u2432.setAllowContentAccess(true);
        $this$setupWebView_u24lambda_u2432.setDatabaseEnabled(true);
        $this$setupWebView_u24lambda_u2432.setJavaScriptCanOpenWindowsAutomatically(true);
        $this$setupWebView_u24lambda_u2432.setMixedContentMode(0);
        $this$setupWebView_u24lambda_u2432.setUserAgentString("Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36");
        webView.setWebChromeClient(new WebChromeClient() { // from class: com.fakehal.controller.VerificationScreenKt$setupWebView$2
            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest request) {
                Intrinsics.checkNotNullParameter(request, "request");
                request.grant(request.getResources());
            }
        });
        webView.setWebViewClient(new WebViewClient() { // from class: com.fakehal.controller.VerificationScreenKt$setupWebView$3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x07a5  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x07b6  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x085e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x086b  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x092a  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0957  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0963  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x09f1  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x09fd  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0a36  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0c60  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0c6c  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0c9f  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0d58  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0ea1  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0eac  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0eb9  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0f67  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0f70  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0f7d  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x104d  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x10e4  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x112a  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x1059  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0f95 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0f72  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0f69  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0ece A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0eae  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0ea3  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0cb5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0c70  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0a4c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0a03  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0959  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0930  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0878 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x07c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x072b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:301:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void ControlPanel(final String selectedCamera, final Function1<? super String, Unit> function1, final int rotation, final Function1<? super Integer, Unit> function12, final boolean mirrorH, final Function1<? super Boolean, Unit> function13, final boolean mirrorV, final Function1<? super Boolean, Unit> function14, final boolean gyroEnabled, final Function0<Unit> function0, final boolean hasGyro, final boolean isApplying, final Function0<Unit> function02, final Function0<Unit> function03, final String statusText, final String urlText, final Function1<? super String, Unit> function15, final Function0<Unit> function04, Composer $composer, final int $changed, final int $changed1) {
        Object obj;
        Function0 factory$iv$iv$iv;
        Function0 factory$iv$iv$iv2;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Function0 factory$iv$iv$iv3;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        boolean invalid$iv;
        Object it$iv;
        Object value$iv;
        boolean invalid$iv2;
        Object it$iv2;
        Object value$iv2;
        boolean invalid$iv3;
        Object value$iv3;
        Function0 factory$iv$iv$iv4;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        Composer $composer2;
        boolean invalid$iv4;
        Object it$iv3;
        Object value$iv4;
        boolean invalid$iv5;
        Object it$iv4;
        Object value$iv5;
        int $dirty;
        Function0 factory$iv$iv$iv5;
        Object value$iv6;
        Composer $composer3 = $composer.startRestartGroup(-1163237682);
        ComposerKt.sourceInformation($composer3, "C(ControlPanel)P(15,5,14,12,3,8,4,9!1,7!2,10,11,16,17,13)261@10151L7033:VerificationScreen.kt#2o9c7b");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(selectedCamera) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(rotation) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changedInstance(function12) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty2 |= $composer3.changed(mirrorH) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty2 |= $composer3.changedInstance(function13) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(mirrorV) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changedInstance(function14) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(gyroEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 1879048192) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changed(hasGyro) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty1 |= $composer3.changed(isApplying) ? 32 : 16;
        }
        if (($changed1 & 896) == 0) {
            $dirty1 |= $composer3.changedInstance(function02) ? 256 : 128;
        }
        if (($changed1 & 7168) == 0) {
            $dirty1 |= $composer3.changedInstance(function03) ? 2048 : 1024;
        }
        if (($changed1 & 57344) == 0) {
            obj = statusText;
            $dirty1 |= $composer3.changed(obj) ? 16384 : 8192;
        } else {
            obj = statusText;
        }
        if (($changed1 & 458752) == 0) {
            $dirty1 |= $composer3.changed(urlText) ? 131072 : 65536;
        }
        if (($changed1 & 3670016) == 0) {
            $dirty1 |= $composer3.changedInstance(function15) ? 1048576 : 524288;
        }
        if (($changed1 & 29360128) == 0) {
            $dirty1 |= $composer3.changedInstance(function04) ? 8388608 : 4194304;
        }
        int $dirty12 = $dirty1;
        if (($dirty2 & 1533916891) == 306783378 && (23967451 & $dirty12) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $dirty = $dirty2;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1163237682, $dirty2, $dirty12, "com.fakehal.controller.ControlPanel (VerificationScreen.kt:260)");
            }
            Modifier modifier$iv = PaddingKt.m565paddingVpY3zN4(BackgroundKt.m211backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4279900718L), null, 2, null), Dp.m5734constructorimpl(8), Dp.m5734constructorimpl(4));
            $composer3.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv6 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                factory$iv$iv$iv = factory$iv$iv$iv6;
                $composer3.createNode(factory$iv$iv$iv);
            } else {
                factory$iv$iv$iv = factory$iv$iv$iv6;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv5 = Updater.m2943constructorimpl($composer3);
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1286356883, "C269@10382L1297,299@11689L29,302@11789L2783,373@14582L29,376@14666L2287:VerificationScreen.kt#2o9c7b");
                Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                $composer3.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv2 = (390 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv7 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    factory$iv$iv$iv2 = factory$iv$iv$iv7;
                    $composer3.useNode();
                } else {
                    factory$iv$iv$iv2 = factory$iv$iv$iv7;
                    $composer3.createNode(factory$iv$iv$iv2);
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    int i4 = ((390 >> 6) & 112) | 6;
                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2433 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -775429443, "C277@10721L7,280@10880L216,273@10519L693,287@11225L28,292@11447L48,288@11266L403:VerificationScreen.kt#2o9c7b");
                    Modifier m599height3ABfNKs = SizeKt.m599height3ABfNKs(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2433, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5734constructorimpl(48));
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    OutlinedTextFieldKt.OutlinedTextField(urlText, function15, m599height3ABfNKs, false, false, TextStyle.m5247copyp1EtxEg$default((TextStyle) consume, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$VerificationScreenKt.INSTANCE.m6080getLambda3$app_debug(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, OutlinedTextFieldDefaults.INSTANCE.m1731colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, null, ColorKt.Color(4286336511L), ColorKt.Color(4282006108L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 432, 0, 0, 3072, 2147477247, 4095), $composer3, (($dirty12 >> 15) & 14) | 12582912 | (($dirty12 >> 15) & 112), 12582912, 0, 4063064);
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Composer $composer4 = $composer3;
                    ButtonKt.Button(function04, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(48)), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4286336511L), 0L, 0L, 0L, $composer3, (ButtonDefaults.$stable << 12) | 6, 14), null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(12), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6081getLambda4$app_debug(), $composer3, (($dirty12 >> 21) & 14) | 817889328, 356);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.HorizontalOrVertical m473spacedBy0680j_4 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                    $composer3.startReplaceableGroup(693286680);
                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv3 = (438 << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv8 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv3 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default);
                    int $i$f$Row = $changed$iv$iv3 << 9;
                    int $changed$iv$iv$iv3 = ($i$f$Row & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        factory$iv$iv$iv3 = factory$iv$iv$iv8;
                        $composer3.useNode();
                    } else {
                        factory$iv$iv$iv3 = factory$iv$iv$iv8;
                        $composer3.createNode(factory$iv$iv$iv3);
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer3);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        skippableUpdate$iv$iv$iv3.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i5 = ($changed$iv$iv$iv3 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                        int i6 = ((438 >> 6) & 112) | 6;
                        RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2438 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
                        boolean areEqual = Intrinsics.areEqual(selectedCamera, "back");
                        $composer3.startReplaceableGroup(-775427838);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        invalid$iv = ($dirty2 & 112) != 32;
                        it$iv = $composer3.rememberedValue();
                        if (!invalid$iv && it$iv != Composer.INSTANCE.getEmpty()) {
                            value$iv = it$iv;
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(areEqual, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                            boolean areEqual2 = Intrinsics.areEqual(selectedCamera, "front");
                            $composer3.startReplaceableGroup(-775427408);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            invalid$iv2 = ($dirty2 & 112) != 32;
                            it$iv2 = $composer3.rememberedValue();
                            if (!invalid$iv2 && it$iv2 != Composer.INSTANCE.getEmpty()) {
                                value$iv2 = it$iv2;
                                $composer3.endReplaceableGroup();
                                ChipKt.FilterChip(areEqual2, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                                Modifier m211backgroundbw27NRU$default = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                                $composer3.startReplaceableGroup(-775426703);
                                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                invalid$iv3 = (1879048192 & $dirty2) != 536870912;
                                Object it$iv5 = $composer3.rememberedValue();
                                if (!invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function0.invoke();
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv5;
                                }
                                $composer3.endReplaceableGroup();
                                Modifier modifier$iv3 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default, hasGyro, null, null, (Function0) value$iv3, 6, null);
                                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                                $composer3.startReplaceableGroup(733328855);
                                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                int $changed$iv$iv4 = (48 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                                CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv9 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv4 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
                                if (!($composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer3.startReusableNode();
                                if ($composer3.getInserting()) {
                                    factory$iv$iv$iv4 = factory$iv$iv$iv9;
                                    $composer3.useNode();
                                } else {
                                    factory$iv$iv$iv4 = factory$iv$iv$iv9;
                                    $composer3.createNode(factory$iv$iv$iv4);
                                }
                                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                    skippableUpdate$iv$iv$iv4.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
                                    $composer3.startReplaceableGroup(2058660585);
                                    int i7 = ($changed$iv$iv$iv4 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i8 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                                    TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                    ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                                    ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                                    Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.HorizontalOrVertical m473spacedBy0680j_42 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                                    int $changed$iv = 438;
                                    $composer3.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicy$iv5 = RowKt.rowMeasurePolicy(m473spacedBy0680j_42, centerVertically2, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                    int $changed$iv$iv5 = (438 << 3) & 112;
                                    int $i$f$Layout = 0;
                                    $composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                                    CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
                                    Function0 factory$iv$iv$iv10 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3 skippableUpdate$iv$iv$iv5 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default2);
                                    int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 9) & 7168) | 6;
                                    if (!($composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer3.startReusableNode();
                                    if ($composer3.getInserting()) {
                                        $composer3.useNode();
                                    } else {
                                        $composer3.createNode(factory$iv$iv$iv10);
                                    }
                                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2 block$iv$iv$iv5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                                        skippableUpdate$iv$iv$iv5.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5 >> 3) & 112));
                                        $composer3.startReplaceableGroup(2058660585);
                                        int i9 = ($changed$iv$iv$iv5 >> 9) & 14;
                                        int i10 = 0;
                                        ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                        int $changed2 = ((438 >> 6) & 112) | 6;
                                        RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2443 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                                        $composer3.startReplaceableGroup(-775425039);
                                        ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                                        Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                                        int $i$f$forEach = 0;
                                        for (Object element$iv : $this$forEach$iv) {
                                            Iterable $this$forEach$iv2 = $this$forEach$iv;
                                            final int deg = ((Number) element$iv).intValue();
                                            int $i$f$forEach2 = $i$f$forEach;
                                            boolean z = rotation == deg;
                                            $composer3.startReplaceableGroup(1894231437);
                                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                            int i11 = i10;
                                            Composer $composer5 = $composer4;
                                            boolean invalid$iv6 = (($dirty2 & 7168) == 2048) | $composer5.changed(deg);
                                            int $changed$iv2 = $changed$iv;
                                            Object it$iv6 = $composer3.rememberedValue();
                                            if (!invalid$iv6 && it$iv6 != Composer.INSTANCE.getEmpty()) {
                                                value$iv6 = it$iv6;
                                                factory$iv$iv$iv5 = factory$iv$iv$iv10;
                                                $composer3.endReplaceableGroup();
                                                ChipKt.FilterChip(z, (Function0) value$iv6, ComposableLambdaKt.composableLambda($composer3, 875165137, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$1$2
                                                    /* JADX INFO: Access modifiers changed from: package-private */
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                        invoke(composer, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer $composer6, int $changed3) {
                                                        ComposerKt.sourceInformation($composer6, "C386@15103L31:VerificationScreen.kt#2o9c7b");
                                                        if (($changed3 & 11) == 2 && $composer6.getSkipping()) {
                                                            $composer6.skipToGroupEnd();
                                                            return;
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(875165137, $changed3, -1, "com.fakehal.controller.ControlPanel.<anonymous>.<anonymous>.<anonymous>.<anonymous> (VerificationScreen.kt:386)");
                                                        }
                                                        TextKt.m2129Text4IGK_g(deg + "°", (Modifier) null, 0L, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3072, 0, 131062);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                        }
                                                    }
                                                }), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4294940672L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                                                $this$forEach$iv = $this$forEach$iv2;
                                                $i$f$forEach = $i$f$forEach2;
                                                $changed$iv = $changed$iv2;
                                                factory$iv$iv$iv10 = factory$iv$iv$iv5;
                                                $changed2 = $changed2;
                                                $i$f$Layout = $i$f$Layout;
                                                $composer4 = $composer5;
                                                i10 = i11;
                                            }
                                            factory$iv$iv$iv5 = factory$iv$iv$iv10;
                                            value$iv6 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$1$1$1
                                                /* JADX INFO: Access modifiers changed from: package-private */
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(0);
                                                }

                                                @Override // kotlin.jvm.functions.Function0
                                                public /* bridge */ /* synthetic */ Unit invoke() {
                                                    invoke2();
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2() {
                                                    function12.invoke(Integer.valueOf(deg));
                                                }
                                            };
                                            $composer3.updateRememberedValue(value$iv6);
                                            $composer3.endReplaceableGroup();
                                            ChipKt.FilterChip(z, (Function0) value$iv6, ComposableLambdaKt.composableLambda($composer3, 875165137, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$1$2
                                                /* JADX INFO: Access modifiers changed from: package-private */
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                    invoke(composer, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer $composer6, int $changed3) {
                                                    ComposerKt.sourceInformation($composer6, "C386@15103L31:VerificationScreen.kt#2o9c7b");
                                                    if (($changed3 & 11) == 2 && $composer6.getSkipping()) {
                                                        $composer6.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(875165137, $changed3, -1, "com.fakehal.controller.ControlPanel.<anonymous>.<anonymous>.<anonymous>.<anonymous> (VerificationScreen.kt:386)");
                                                    }
                                                    TextKt.m2129Text4IGK_g(deg + "°", (Modifier) null, 0L, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3072, 0, 131062);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4294940672L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                                            $this$forEach$iv = $this$forEach$iv2;
                                            $i$f$forEach = $i$f$forEach2;
                                            $changed$iv = $changed$iv2;
                                            factory$iv$iv$iv10 = factory$iv$iv$iv5;
                                            $changed2 = $changed2;
                                            $i$f$Layout = $i$f$Layout;
                                            $composer4 = $composer5;
                                            i10 = i11;
                                        }
                                        $composer2 = $composer4;
                                        $composer3.endReplaceableGroup();
                                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                                        $composer3.startReplaceableGroup(-775424395);
                                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                        invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                                        it$iv3 = $composer3.rememberedValue();
                                        if (!invalid$iv4 && it$iv3 != Composer.INSTANCE.getEmpty()) {
                                            value$iv4 = it$iv3;
                                            $composer3.endReplaceableGroup();
                                            ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                            $composer3.startReplaceableGroup(-775423958);
                                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                            invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                            it$iv4 = $composer3.rememberedValue();
                                            if (!invalid$iv5 && it$iv4 != Composer.INSTANCE.getEmpty()) {
                                                value$iv5 = it$iv4;
                                                $composer3.endReplaceableGroup();
                                                ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                                $dirty = $dirty2;
                                                TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                                $composer3.endReplaceableGroup();
                                                $composer3.endNode();
                                                $composer3.endReplaceableGroup();
                                                $composer3.endReplaceableGroup();
                                                $composer3.startReplaceableGroup(1780649284);
                                                ComposerKt.sourceInformation($composer3, "443@17022L146");
                                                if (isApplying) {
                                                    ProgressIndicatorKt.m1751LinearProgressIndicator2cYBFYY(SizeKt.m599height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5734constructorimpl(2)), ColorKt.Color(4286336511L), 0L, 0, $composer3, 54, 12);
                                                }
                                                $composer3.endReplaceableGroup();
                                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                                $composer2.endReplaceableGroup();
                                                $composer2.endNode();
                                                $composer2.endReplaceableGroup();
                                                $composer2.endReplaceableGroup();
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                            value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                                /* JADX INFO: Access modifiers changed from: package-private */
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(0);
                                                }

                                                @Override // kotlin.jvm.functions.Function0
                                                public /* bridge */ /* synthetic */ Unit invoke() {
                                                    invoke2();
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2() {
                                                    function14.invoke(Boolean.valueOf(!mirrorV));
                                                }
                                            };
                                            $composer3.updateRememberedValue(value$iv5);
                                            $composer3.endReplaceableGroup();
                                            ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                            $dirty = $dirty2;
                                            TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            $composer3.endReplaceableGroup();
                                            $composer3.endNode();
                                            $composer3.endReplaceableGroup();
                                            $composer3.endReplaceableGroup();
                                            $composer3.startReplaceableGroup(1780649284);
                                            ComposerKt.sourceInformation($composer3, "443@17022L146");
                                            if (isApplying) {
                                            }
                                            $composer3.endReplaceableGroup();
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            $composer2.endReplaceableGroup();
                                            $composer2.endNode();
                                            $composer2.endReplaceableGroup();
                                            $composer2.endReplaceableGroup();
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                        }
                                        value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                function13.invoke(Boolean.valueOf(!mirrorH));
                                            }
                                        };
                                        $composer3.updateRememberedValue(value$iv4);
                                        $composer3.endReplaceableGroup();
                                        ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                        $composer3.startReplaceableGroup(-775423958);
                                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                        invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                        it$iv4 = $composer3.rememberedValue();
                                        if (!invalid$iv5) {
                                            value$iv5 = it$iv4;
                                            $composer3.endReplaceableGroup();
                                            ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                            $dirty = $dirty2;
                                            TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            $composer3.endReplaceableGroup();
                                            $composer3.endNode();
                                            $composer3.endReplaceableGroup();
                                            $composer3.endReplaceableGroup();
                                            $composer3.startReplaceableGroup(1780649284);
                                            ComposerKt.sourceInformation($composer3, "443@17022L146");
                                            if (isApplying) {
                                            }
                                            $composer3.endReplaceableGroup();
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            $composer2.endReplaceableGroup();
                                            $composer2.endNode();
                                            $composer2.endReplaceableGroup();
                                            $composer2.endReplaceableGroup();
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                        }
                                        value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                function14.invoke(Boolean.valueOf(!mirrorV));
                                            }
                                        };
                                        $composer3.updateRememberedValue(value$iv5);
                                        $composer3.endReplaceableGroup();
                                        ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                        $dirty = $dirty2;
                                        TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer3.endReplaceableGroup();
                                        $composer3.endNode();
                                        $composer3.endReplaceableGroup();
                                        $composer3.endReplaceableGroup();
                                        $composer3.startReplaceableGroup(1780649284);
                                        ComposerKt.sourceInformation($composer3, "443@17022L146");
                                        if (isApplying) {
                                        }
                                        $composer3.endReplaceableGroup();
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer2.endReplaceableGroup();
                                        $composer2.endNode();
                                        $composer2.endReplaceableGroup();
                                        $composer2.endReplaceableGroup();
                                        if (ComposerKt.isTraceInProgress()) {
                                        }
                                    }
                                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv5), block$iv$iv$iv5);
                                    skippableUpdate$iv$iv$iv5.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5 >> 3) & 112));
                                    $composer3.startReplaceableGroup(2058660585);
                                    int i92 = ($changed$iv$iv$iv5 >> 9) & 14;
                                    int i102 = 0;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                    int $changed22 = ((438 >> 6) & 112) | 6;
                                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24432 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                                    $composer3.startReplaceableGroup(-775425039);
                                    ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                                    Iterable $this$forEach$iv3 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                                    int $i$f$forEach3 = 0;
                                    while (r16.hasNext()) {
                                    }
                                    $composer2 = $composer4;
                                    $composer3.endReplaceableGroup();
                                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                                    $composer3.startReplaceableGroup(-775424395);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                    invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                                    it$iv3 = $composer3.rememberedValue();
                                    if (!invalid$iv4) {
                                        value$iv4 = it$iv3;
                                        $composer3.endReplaceableGroup();
                                        ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                        $composer3.startReplaceableGroup(-775423958);
                                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                        invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                        it$iv4 = $composer3.rememberedValue();
                                        if (!invalid$iv5) {
                                        }
                                        value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                function14.invoke(Boolean.valueOf(!mirrorV));
                                            }
                                        };
                                        $composer3.updateRememberedValue(value$iv5);
                                        $composer3.endReplaceableGroup();
                                        ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                        $dirty = $dirty2;
                                        TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer3.endReplaceableGroup();
                                        $composer3.endNode();
                                        $composer3.endReplaceableGroup();
                                        $composer3.endReplaceableGroup();
                                        $composer3.startReplaceableGroup(1780649284);
                                        ComposerKt.sourceInformation($composer3, "443@17022L146");
                                        if (isApplying) {
                                        }
                                        $composer3.endReplaceableGroup();
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer2.endReplaceableGroup();
                                        $composer2.endNode();
                                        $composer2.endReplaceableGroup();
                                        $composer2.endReplaceableGroup();
                                        if (ComposerKt.isTraceInProgress()) {
                                        }
                                    }
                                    value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function13.invoke(Boolean.valueOf(!mirrorH));
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv4);
                                    $composer3.endReplaceableGroup();
                                    ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                    $composer3.startReplaceableGroup(-775423958);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                    invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                    it$iv4 = $composer3.rememberedValue();
                                    if (!invalid$iv5) {
                                    }
                                    value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function14.invoke(Boolean.valueOf(!mirrorV));
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv5);
                                    $composer3.endReplaceableGroup();
                                    ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                    $dirty = $dirty2;
                                    TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    $composer3.startReplaceableGroup(1780649284);
                                    ComposerKt.sourceInformation($composer3, "443@17022L146");
                                    if (isApplying) {
                                    }
                                    $composer3.endReplaceableGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer2.endReplaceableGroup();
                                    $composer2.endNode();
                                    $composer2.endReplaceableGroup();
                                    $composer2.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                }
                                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv4), block$iv$iv$iv4);
                                skippableUpdate$iv$iv$iv4.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
                                $composer3.startReplaceableGroup(2058660585);
                                int i72 = ($changed$iv$iv$iv4 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                int i82 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                                TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                                ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                                Modifier fillMaxWidth$default22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Alignment.Vertical centerVertically22 = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical m473spacedBy0680j_422 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                                int $changed$iv3 = 438;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicy$iv52 = RowKt.rowMeasurePolicy(m473spacedBy0680j_422, centerVertically22, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                int $changed$iv$iv52 = (438 << 3) & 112;
                                int $i$f$Layout2 = 0;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv52 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                                CompositionLocalMap localMap$iv$iv52 = $composer3.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv102 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv52 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default22);
                                int $changed$iv$iv$iv52 = (($changed$iv$iv52 << 9) & 7168) | 6;
                                if (!($composer3.getApplier() instanceof Applier)) {
                                }
                                $composer3.startReusableNode();
                                if ($composer3.getInserting()) {
                                }
                                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv52, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv52, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv52 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                                    skippableUpdate$iv$iv$iv52.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv52 >> 3) & 112));
                                    $composer3.startReplaceableGroup(2058660585);
                                    int i922 = ($changed$iv$iv$iv52 >> 9) & 14;
                                    int i1022 = 0;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                    int $changed222 = ((438 >> 6) & 112) | 6;
                                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u244322 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                                    $composer3.startReplaceableGroup(-775425039);
                                    ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                                    Iterable $this$forEach$iv32 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                                    int $i$f$forEach32 = 0;
                                    while (r16.hasNext()) {
                                    }
                                    $composer2 = $composer4;
                                    $composer3.endReplaceableGroup();
                                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                                    $composer3.startReplaceableGroup(-775424395);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                    invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                                    it$iv3 = $composer3.rememberedValue();
                                    if (!invalid$iv4) {
                                    }
                                    value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function13.invoke(Boolean.valueOf(!mirrorH));
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv4);
                                    $composer3.endReplaceableGroup();
                                    ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                    $composer3.startReplaceableGroup(-775423958);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                    invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                    it$iv4 = $composer3.rememberedValue();
                                    if (!invalid$iv5) {
                                    }
                                    value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function14.invoke(Boolean.valueOf(!mirrorV));
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv5);
                                    $composer3.endReplaceableGroup();
                                    ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u244322, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                    $dirty = $dirty2;
                                    TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    $composer3.startReplaceableGroup(1780649284);
                                    ComposerKt.sourceInformation($composer3, "443@17022L146");
                                    if (isApplying) {
                                    }
                                    $composer3.endReplaceableGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer2.endReplaceableGroup();
                                    $composer2.endNode();
                                    $composer2.endReplaceableGroup();
                                    $composer2.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                }
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv52));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv52), block$iv$iv$iv52);
                                skippableUpdate$iv$iv$iv52.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv52 >> 3) & 112));
                                $composer3.startReplaceableGroup(2058660585);
                                int i9222 = ($changed$iv$iv$iv52 >> 9) & 14;
                                int i10222 = 0;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                int $changed2222 = ((438 >> 6) & 112) | 6;
                                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2443222 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                                $composer3.startReplaceableGroup(-775425039);
                                ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                                Iterable $this$forEach$iv322 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                                int $i$f$forEach322 = 0;
                                while (r16.hasNext()) {
                                }
                                $composer2 = $composer4;
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                                $composer3.startReplaceableGroup(-775424395);
                                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                                it$iv3 = $composer3.rememberedValue();
                                if (!invalid$iv4) {
                                }
                                value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function13.invoke(Boolean.valueOf(!mirrorH));
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv4);
                                $composer3.endReplaceableGroup();
                                ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                $composer3.startReplaceableGroup(-775423958);
                                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                it$iv4 = $composer3.rememberedValue();
                                if (!invalid$iv5) {
                                }
                                value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function14.invoke(Boolean.valueOf(!mirrorV));
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv5);
                                $composer3.endReplaceableGroup();
                                ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                $dirty = $dirty2;
                                TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                $composer3.startReplaceableGroup(1780649284);
                                ComposerKt.sourceInformation($composer3, "443@17022L146");
                                if (isApplying) {
                                }
                                $composer3.endReplaceableGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer2.endReplaceableGroup();
                                $composer2.endNode();
                                $composer2.endReplaceableGroup();
                                $composer2.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                            }
                            value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke("front");
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(areEqual2, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                            Modifier m211backgroundbw27NRU$default2 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                            $composer3.startReplaceableGroup(-775426703);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            if ((1879048192 & $dirty2) != 536870912) {
                            }
                            Object it$iv52 = $composer3.rememberedValue();
                            if (invalid$iv3) {
                            }
                            value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function0.invoke();
                                }
                            };
                            $composer3.updateRememberedValue(value$iv3);
                            $composer3.endReplaceableGroup();
                            Modifier modifier$iv32 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default2, hasGyro, null, null, (Function0) value$iv3, 6, null);
                            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv42 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            int $changed$iv$iv42 = (48 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv42 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv42 = $composer3.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv92 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv42 = LayoutKt.modifierMaterializerOf(modifier$iv32);
                            int $changed$iv$iv$iv42 = (($changed$iv$iv42 << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv42, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv42, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv42 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                                skippableUpdate$iv$iv$iv42.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv42 >> 3) & 112));
                                $composer3.startReplaceableGroup(2058660585);
                                int i722 = ($changed$iv$iv$iv42 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                                int i822 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                                TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                                ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                                Modifier fillMaxWidth$default222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Alignment.Vertical centerVertically222 = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical m473spacedBy0680j_4222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                                int $changed$iv32 = 438;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicy$iv522 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4222, centerVertically222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                int $changed$iv$iv522 = (438 << 3) & 112;
                                int $i$f$Layout22 = 0;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv522 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                                CompositionLocalMap localMap$iv$iv522 = $composer3.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv1022 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv522 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default222);
                                int $changed$iv$iv$iv522 = (($changed$iv$iv522 << 9) & 7168) | 6;
                                if (!($composer3.getApplier() instanceof Applier)) {
                                }
                                $composer3.startReusableNode();
                                if ($composer3.getInserting()) {
                                }
                                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv522, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv522, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv522 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                                }
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv522));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv522), block$iv$iv$iv522);
                                skippableUpdate$iv$iv$iv522.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv522 >> 3) & 112));
                                $composer3.startReplaceableGroup(2058660585);
                                int i92222 = ($changed$iv$iv$iv522 >> 9) & 14;
                                int i102222 = 0;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                int $changed22222 = ((438 >> 6) & 112) | 6;
                                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24432222 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                                $composer3.startReplaceableGroup(-775425039);
                                ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                                Iterable $this$forEach$iv3222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                                int $i$f$forEach3222 = 0;
                                while (r16.hasNext()) {
                                }
                                $composer2 = $composer4;
                                $composer3.endReplaceableGroup();
                                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                                $composer3.startReplaceableGroup(-775424395);
                                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                                it$iv3 = $composer3.rememberedValue();
                                if (!invalid$iv4) {
                                }
                                value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function13.invoke(Boolean.valueOf(!mirrorH));
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv4);
                                $composer3.endReplaceableGroup();
                                ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                                $composer3.startReplaceableGroup(-775423958);
                                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                                invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                                it$iv4 = $composer3.rememberedValue();
                                if (!invalid$iv5) {
                                }
                                value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function14.invoke(Boolean.valueOf(!mirrorV));
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv5);
                                $composer3.endReplaceableGroup();
                                ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                                $dirty = $dirty2;
                                TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                $composer3.startReplaceableGroup(1780649284);
                                ComposerKt.sourceInformation($composer3, "443@17022L146");
                                if (isApplying) {
                                }
                                $composer3.endReplaceableGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer2.endReplaceableGroup();
                                $composer2.endNode();
                                $composer2.endReplaceableGroup();
                                $composer2.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                            }
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv42));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv42), block$iv$iv$iv42);
                            skippableUpdate$iv$iv$iv42.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv42 >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i7222 = ($changed$iv$iv$iv42 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                            int i8222 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                            TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                            ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                            ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                            Modifier fillMaxWidth$default2222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical centerVertically2222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical m473spacedBy0680j_42222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                            int $changed$iv322 = 438;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv5222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_42222, centerVertically2222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                            int $changed$iv$iv5222 = (438 << 3) & 112;
                            int $i$f$Layout222 = 0;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv5222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv5222 = $composer3.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv10222 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv5222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default2222);
                            int $changed$iv$iv$iv5222 = (($changed$iv$iv5222 << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv5222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5222));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv5222), block$iv$iv$iv5222);
                            skippableUpdate$iv$iv$iv5222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5222 >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i922222 = ($changed$iv$iv$iv5222 >> 9) & 14;
                            int i1022222 = 0;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                            int $changed222222 = ((438 >> 6) & 112) | 6;
                            RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u244322222 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                            $composer3.startReplaceableGroup(-775425039);
                            ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                            Iterable $this$forEach$iv32222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                            int $i$f$forEach32222 = 0;
                            while (r16.hasNext()) {
                            }
                            $composer2 = $composer4;
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                            $composer3.startReplaceableGroup(-775424395);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                            it$iv3 = $composer3.rememberedValue();
                            if (!invalid$iv4) {
                            }
                            value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function13.invoke(Boolean.valueOf(!mirrorH));
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                            $composer3.startReplaceableGroup(-775423958);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                            it$iv4 = $composer3.rememberedValue();
                            if (!invalid$iv5) {
                            }
                            value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function14.invoke(Boolean.valueOf(!mirrorV));
                                }
                            };
                            $composer3.updateRememberedValue(value$iv5);
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u244322222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                            $dirty = $dirty2;
                            TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            $composer3.startReplaceableGroup(1780649284);
                            ComposerKt.sourceInformation($composer3, "443@17022L146");
                            if (isApplying) {
                            }
                            $composer3.endReplaceableGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer2.endReplaceableGroup();
                            $composer2.endNode();
                            $composer2.endReplaceableGroup();
                            $composer2.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                        }
                        value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke("back");
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(areEqual, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                        boolean areEqual22 = Intrinsics.areEqual(selectedCamera, "front");
                        $composer3.startReplaceableGroup(-775427408);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        if (($dirty2 & 112) != 32) {
                        }
                        it$iv2 = $composer3.rememberedValue();
                        if (!invalid$iv2) {
                            value$iv2 = it$iv2;
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(areEqual22, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                            Modifier m211backgroundbw27NRU$default22 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                            $composer3.startReplaceableGroup(-775426703);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            if ((1879048192 & $dirty2) != 536870912) {
                            }
                            Object it$iv522 = $composer3.rememberedValue();
                            if (invalid$iv3) {
                            }
                            value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function0.invoke();
                                }
                            };
                            $composer3.updateRememberedValue(value$iv3);
                            $composer3.endReplaceableGroup();
                            Modifier modifier$iv322 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default22, hasGyro, null, null, (Function0) value$iv3, 6, null);
                            Alignment contentAlignment$iv22 = Alignment.INSTANCE.getCenter();
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv422 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv22, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            int $changed$iv$iv422 = (48 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv422 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv422 = $composer3.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv922 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv422 = LayoutKt.modifierMaterializerOf(modifier$iv322);
                            int $changed$iv$iv$iv422 = (($changed$iv$iv422 << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv422, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv422, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv422 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv422));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv422), block$iv$iv$iv422);
                            skippableUpdate$iv$iv$iv422.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv422 >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i72222 = ($changed$iv$iv$iv422 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                            int i82222 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                            TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                            ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                            ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                            Modifier fillMaxWidth$default22222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical centerVertically22222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical m473spacedBy0680j_422222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                            int $changed$iv3222 = 438;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv52222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_422222, centerVertically22222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                            int $changed$iv$iv52222 = (438 << 3) & 112;
                            int $i$f$Layout2222 = 0;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv52222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv52222 = $composer3.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv102222 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv52222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default22222);
                            int $changed$iv$iv$iv52222 = (($changed$iv$iv52222 << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv52222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv52222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv52222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv52222));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv52222), block$iv$iv$iv52222);
                            skippableUpdate$iv$iv$iv52222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv52222 >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i9222222 = ($changed$iv$iv$iv52222 >> 9) & 14;
                            int i10222222 = 0;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                            int $changed2222222 = ((438 >> 6) & 112) | 6;
                            RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                            $composer3.startReplaceableGroup(-775425039);
                            ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                            Iterable $this$forEach$iv322222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                            int $i$f$forEach322222 = 0;
                            while (r16.hasNext()) {
                            }
                            $composer2 = $composer4;
                            $composer3.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                            $composer3.startReplaceableGroup(-775424395);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                            it$iv3 = $composer3.rememberedValue();
                            if (!invalid$iv4) {
                            }
                            value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function13.invoke(Boolean.valueOf(!mirrorH));
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                            $composer3.startReplaceableGroup(-775423958);
                            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                            invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                            it$iv4 = $composer3.rememberedValue();
                            if (!invalid$iv5) {
                            }
                            value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function14.invoke(Boolean.valueOf(!mirrorV));
                                }
                            };
                            $composer3.updateRememberedValue(value$iv5);
                            $composer3.endReplaceableGroup();
                            ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                            $dirty = $dirty2;
                            TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            $composer3.startReplaceableGroup(1780649284);
                            ComposerKt.sourceInformation($composer3, "443@17022L146");
                            if (isApplying) {
                            }
                            $composer3.endReplaceableGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer2.endReplaceableGroup();
                            $composer2.endNode();
                            $composer2.endReplaceableGroup();
                            $composer2.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                        }
                        value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke("front");
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(areEqual22, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                        Modifier m211backgroundbw27NRU$default222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                        $composer3.startReplaceableGroup(-775426703);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        if ((1879048192 & $dirty2) != 536870912) {
                        }
                        Object it$iv5222 = $composer3.rememberedValue();
                        if (invalid$iv3) {
                        }
                        value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function0.invoke();
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                        $composer3.endReplaceableGroup();
                        Modifier modifier$iv3222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                        Alignment contentAlignment$iv222 = Alignment.INSTANCE.getCenter();
                        $composer3.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv4222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                        int $changed$iv$iv4222 = (48 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv4222 = $composer3.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv9222 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv4222 = LayoutKt.modifierMaterializerOf(modifier$iv3222);
                        int $changed$iv$iv$iv4222 = (($changed$iv$iv4222 << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv4222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4222));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv4222), block$iv$iv$iv4222);
                        skippableUpdate$iv$iv$iv4222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4222 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i722222 = ($changed$iv$iv$iv4222 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
                        int i822222 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                        TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                        ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                        ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                        Modifier fillMaxWidth$default222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical centerVertically222222 = Alignment.INSTANCE.getCenterVertically();
                        Arrangement.HorizontalOrVertical m473spacedBy0680j_4222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                        int $changed$iv32222 = 438;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv522222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4222222, centerVertically222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                        int $changed$iv$iv522222 = (438 << 3) & 112;
                        int $i$f$Layout22222 = 0;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv522222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv522222 = $composer3.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv1022222 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv522222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default222222);
                        int $changed$iv$iv$iv522222 = (($changed$iv$iv522222 << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv522222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv522222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv522222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv522222));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv522222), block$iv$iv$iv522222);
                        skippableUpdate$iv$iv$iv522222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv522222 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i92222222 = ($changed$iv$iv$iv522222 >> 9) & 14;
                        int i102222222 = 0;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                        int $changed22222222 = ((438 >> 6) & 112) | 6;
                        RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                        $composer3.startReplaceableGroup(-775425039);
                        ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                        Iterable $this$forEach$iv3222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                        int $i$f$forEach3222222 = 0;
                        while (r16.hasNext()) {
                        }
                        $composer2 = $composer4;
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                        $composer3.startReplaceableGroup(-775424395);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                        it$iv3 = $composer3.rememberedValue();
                        if (!invalid$iv4) {
                        }
                        value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function13.invoke(Boolean.valueOf(!mirrorH));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                        $composer3.startReplaceableGroup(-775423958);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                        it$iv4 = $composer3.rememberedValue();
                        if (!invalid$iv5) {
                        }
                        value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function14.invoke(Boolean.valueOf(!mirrorV));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                        $dirty = $dirty2;
                        TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        $composer3.startReplaceableGroup(1780649284);
                        ComposerKt.sourceInformation($composer3, "443@17022L146");
                        if (isApplying) {
                        }
                        $composer3.endReplaceableGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endReplaceableGroup();
                        $composer2.endNode();
                        $composer2.endReplaceableGroup();
                        $composer2.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv3), block$iv$iv$iv3);
                    skippableUpdate$iv$iv$iv3.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i52 = ($changed$iv$iv$iv3 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    int i62 = ((438 >> 6) & 112) | 6;
                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24382 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
                    boolean areEqual3 = Intrinsics.areEqual(selectedCamera, "back");
                    $composer3.startReplaceableGroup(-775427838);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if (($dirty2 & 112) != 32) {
                    }
                    it$iv = $composer3.rememberedValue();
                    if (!invalid$iv) {
                        value$iv = it$iv;
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(areEqual3, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                        boolean areEqual222 = Intrinsics.areEqual(selectedCamera, "front");
                        $composer3.startReplaceableGroup(-775427408);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        if (($dirty2 & 112) != 32) {
                        }
                        it$iv2 = $composer3.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke("front");
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(areEqual222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                        Modifier m211backgroundbw27NRU$default2222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                        $composer3.startReplaceableGroup(-775426703);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        if ((1879048192 & $dirty2) != 536870912) {
                        }
                        Object it$iv52222 = $composer3.rememberedValue();
                        if (invalid$iv3) {
                        }
                        value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function0.invoke();
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                        $composer3.endReplaceableGroup();
                        Modifier modifier$iv32222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default2222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                        Alignment contentAlignment$iv2222 = Alignment.INSTANCE.getCenter();
                        $composer3.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv42222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                        int $changed$iv$iv42222 = (48 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv42222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv42222 = $composer3.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv92222 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv42222 = LayoutKt.modifierMaterializerOf(modifier$iv32222);
                        int $changed$iv$iv$iv42222 = (($changed$iv$iv42222 << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv42222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv42222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv42222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv42222));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv42222), block$iv$iv$iv42222);
                        skippableUpdate$iv$iv$iv42222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv42222 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i7222222 = ($changed$iv$iv$iv42222 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance222222 = BoxScopeInstance.INSTANCE;
                        int i8222222 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                        TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24382, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                        ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                        ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                        Modifier fillMaxWidth$default2222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical centerVertically2222222 = Alignment.INSTANCE.getCenterVertically();
                        Arrangement.HorizontalOrVertical m473spacedBy0680j_42222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                        int $changed$iv322222 = 438;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv5222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_42222222, centerVertically2222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                        int $changed$iv$iv5222222 = (438 << 3) & 112;
                        int $i$f$Layout222222 = 0;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv5222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv5222222 = $composer3.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv10222222 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv5222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default2222222);
                        int $changed$iv$iv$iv5222222 = (($changed$iv$iv5222222 << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv5222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5222222));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv5222222), block$iv$iv$iv5222222);
                        skippableUpdate$iv$iv$iv5222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5222222 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i922222222 = ($changed$iv$iv$iv5222222 >> 9) & 14;
                        int i1022222222 = 0;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                        int $changed222222222 = ((438 >> 6) & 112) | 6;
                        RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u244322222222 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                        $composer3.startReplaceableGroup(-775425039);
                        ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                        Iterable $this$forEach$iv32222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                        int $i$f$forEach32222222 = 0;
                        while (r16.hasNext()) {
                        }
                        $composer2 = $composer4;
                        $composer3.endReplaceableGroup();
                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                        $composer3.startReplaceableGroup(-775424395);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                        it$iv3 = $composer3.rememberedValue();
                        if (!invalid$iv4) {
                        }
                        value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function13.invoke(Boolean.valueOf(!mirrorH));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                        $composer3.startReplaceableGroup(-775423958);
                        ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                        invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                        it$iv4 = $composer3.rememberedValue();
                        if (!invalid$iv5) {
                        }
                        value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function14.invoke(Boolean.valueOf(!mirrorV));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                        $composer3.endReplaceableGroup();
                        ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                        SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u244322222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                        $dirty = $dirty2;
                        TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        $composer3.startReplaceableGroup(1780649284);
                        ComposerKt.sourceInformation($composer3, "443@17022L146");
                        if (isApplying) {
                        }
                        $composer3.endReplaceableGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endReplaceableGroup();
                        $composer2.endNode();
                        $composer2.endReplaceableGroup();
                        $composer2.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                    }
                    value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke("back");
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(areEqual3, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                    boolean areEqual2222 = Intrinsics.areEqual(selectedCamera, "front");
                    $composer3.startReplaceableGroup(-775427408);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if (($dirty2 & 112) != 32) {
                    }
                    it$iv2 = $composer3.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke("front");
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(areEqual2222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Modifier m211backgroundbw27NRU$default22222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                    $composer3.startReplaceableGroup(-775426703);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if ((1879048192 & $dirty2) != 536870912) {
                    }
                    Object it$iv522222 = $composer3.rememberedValue();
                    if (invalid$iv3) {
                    }
                    value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function0.invoke();
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    $composer3.endReplaceableGroup();
                    Modifier modifier$iv322222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default22222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                    Alignment contentAlignment$iv22222 = Alignment.INSTANCE.getCenter();
                    $composer3.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv422222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv22222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    int $changed$iv$iv422222 = (48 << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv422222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv422222 = $composer3.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv922222 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv422222 = LayoutKt.modifierMaterializerOf(modifier$iv322222);
                    int $changed$iv$iv$iv422222 = (($changed$iv$iv422222 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv422222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv422222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv422222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv422222));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv422222), block$iv$iv$iv422222);
                    skippableUpdate$iv$iv$iv422222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv422222 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i72222222 = ($changed$iv$iv$iv422222 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2222222 = BoxScopeInstance.INSTANCE;
                    int i82222222 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                    TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24382, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                    ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                    ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Modifier fillMaxWidth$default22222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical centerVertically22222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.HorizontalOrVertical m473spacedBy0680j_422222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                    int $changed$iv3222222 = 438;
                    $composer3.startReplaceableGroup(693286680);
                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv52222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_422222222, centerVertically22222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv52222222 = (438 << 3) & 112;
                    int $i$f$Layout2222222 = 0;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv52222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv52222222 = $composer3.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv102222222 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv52222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default22222222);
                    int $changed$iv$iv$iv52222222 = (($changed$iv$iv52222222 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv52222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv52222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv52222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv52222222));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv52222222), block$iv$iv$iv52222222);
                    skippableUpdate$iv$iv$iv52222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv52222222 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i9222222222 = ($changed$iv$iv$iv52222222 >> 9) & 14;
                    int i10222222222 = 0;
                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    int $changed2222222222 = ((438 >> 6) & 112) | 6;
                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                    $composer3.startReplaceableGroup(-775425039);
                    ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                    Iterable $this$forEach$iv322222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                    int $i$f$forEach322222222 = 0;
                    while (r16.hasNext()) {
                    }
                    $composer2 = $composer4;
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                    $composer3.startReplaceableGroup(-775424395);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                    it$iv3 = $composer3.rememberedValue();
                    if (!invalid$iv4) {
                    }
                    value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function13.invoke(Boolean.valueOf(!mirrorH));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                    $composer3.startReplaceableGroup(-775423958);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                    it$iv4 = $composer3.rememberedValue();
                    if (!invalid$iv5) {
                    }
                    value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function14.invoke(Boolean.valueOf(!mirrorV));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                    $dirty = $dirty2;
                    TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    $composer3.startReplaceableGroup(1780649284);
                    ComposerKt.sourceInformation($composer3, "443@17022L146");
                    if (isApplying) {
                    }
                    $composer3.endReplaceableGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                    $composer2.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv2), block$iv$iv$iv2);
                skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i32 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int i42 = ((390 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24332 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775429443, "C277@10721L7,280@10880L216,273@10519L693,287@11225L28,292@11447L48,288@11266L403:VerificationScreen.kt#2o9c7b");
                Modifier m599height3ABfNKs2 = SizeKt.m599height3ABfNKs(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24332, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5734constructorimpl(48));
                ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2 = $composer3.consume(localTextStyle2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(urlText, function15, m599height3ABfNKs2, false, false, TextStyle.m5247copyp1EtxEg$default((TextStyle) consume2, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$VerificationScreenKt.INSTANCE.m6080getLambda3$app_debug(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, OutlinedTextFieldDefaults.INSTANCE.m1731colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, null, ColorKt.Color(4286336511L), ColorKt.Color(4282006108L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 432, 0, 0, 3072, 2147477247, 4095), $composer3, (($dirty12 >> 15) & 14) | 12582912 | (($dirty12 >> 15) & 112), 12582912, 0, 4063064);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Composer $composer42 = $composer3;
                ButtonKt.Button(function04, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(48)), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4286336511L), 0L, 0L, 0L, $composer3, (ButtonDefaults.$stable << 12) | 6, 14), null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(12), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6081getLambda4$app_debug(), $composer3, (($dirty12 >> 21) & 14) | 817889328, 356);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical m473spacedBy0680j_43 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                $composer3.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv32 = RowKt.rowMeasurePolicy(m473spacedBy0680j_43, centerVertically3, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv32 = (438 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv32 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv32 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv82 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv32 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default3);
                int $i$f$Row2 = $changed$iv$iv32 << 9;
                int $changed$iv$iv$iv32 = ($i$f$Row2 & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv32 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    skippableUpdate$iv$iv$iv32.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv32 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i522 = ($changed$iv$iv$iv32 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    int i622 = ((438 >> 6) & 112) | 6;
                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u243822 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
                    boolean areEqual32 = Intrinsics.areEqual(selectedCamera, "back");
                    $composer3.startReplaceableGroup(-775427838);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if (($dirty2 & 112) != 32) {
                    }
                    it$iv = $composer3.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke("back");
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(areEqual32, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                    boolean areEqual22222 = Intrinsics.areEqual(selectedCamera, "front");
                    $composer3.startReplaceableGroup(-775427408);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if (($dirty2 & 112) != 32) {
                    }
                    it$iv2 = $composer3.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke("front");
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(areEqual22222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Modifier m211backgroundbw27NRU$default222222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                    $composer3.startReplaceableGroup(-775426703);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    if ((1879048192 & $dirty2) != 536870912) {
                    }
                    Object it$iv5222222 = $composer3.rememberedValue();
                    if (invalid$iv3) {
                    }
                    value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function0.invoke();
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    $composer3.endReplaceableGroup();
                    Modifier modifier$iv3222222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default222222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                    Alignment contentAlignment$iv222222 = Alignment.INSTANCE.getCenter();
                    $composer3.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv4222222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv222222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    int $changed$iv$iv4222222 = (48 << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv4222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv4222222 = $composer3.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv9222222 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv4222222 = LayoutKt.modifierMaterializerOf(modifier$iv3222222);
                    int $changed$iv$iv$iv4222222 = (($changed$iv$iv4222222 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv4222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4222222));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv4222222), block$iv$iv$iv4222222);
                    skippableUpdate$iv$iv$iv4222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4222222 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i722222222 = ($changed$iv$iv$iv4222222 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance22222222 = BoxScopeInstance.INSTANCE;
                    int i822222222 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                    TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u243822, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                    ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                    ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                    Modifier fillMaxWidth$default222222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical centerVertically222222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.HorizontalOrVertical m473spacedBy0680j_4222222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                    int $changed$iv32222222 = 438;
                    $composer3.startReplaceableGroup(693286680);
                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv522222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4222222222, centerVertically222222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv522222222 = (438 << 3) & 112;
                    int $i$f$Layout22222222 = 0;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv522222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv522222222 = $composer3.getCurrentCompositionLocalMap();
                    Function0 factory$iv$iv$iv1022222222 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3 skippableUpdate$iv$iv$iv522222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default222222222);
                    int $changed$iv$iv$iv522222222 = (($changed$iv$iv522222222 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv522222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv522222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2 block$iv$iv$iv522222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv522222222));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv522222222), block$iv$iv$iv522222222);
                    skippableUpdate$iv$iv$iv522222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv522222222 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i92222222222 = ($changed$iv$iv$iv522222222 >> 9) & 14;
                    int i102222222222 = 0;
                    ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    int $changed22222222222 = ((438 >> 6) & 112) | 6;
                    RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                    $composer3.startReplaceableGroup(-775425039);
                    ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                    Iterable $this$forEach$iv3222222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                    int $i$f$forEach3222222222 = 0;
                    while (r16.hasNext()) {
                    }
                    $composer2 = $composer42;
                    $composer3.endReplaceableGroup();
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                    $composer3.startReplaceableGroup(-775424395);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                    it$iv3 = $composer3.rememberedValue();
                    if (!invalid$iv4) {
                    }
                    value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function13.invoke(Boolean.valueOf(!mirrorH));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                    $composer3.startReplaceableGroup(-775423958);
                    ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                    invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                    it$iv4 = $composer3.rememberedValue();
                    if (!invalid$iv5) {
                    }
                    value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function14.invoke(Boolean.valueOf(!mirrorV));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                    $composer3.endReplaceableGroup();
                    ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                    SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                    $dirty = $dirty2;
                    TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    $composer3.startReplaceableGroup(1780649284);
                    ComposerKt.sourceInformation($composer3, "443@17022L146");
                    if (isApplying) {
                    }
                    $composer3.endReplaceableGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                    $composer2.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv32));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv32), block$iv$iv$iv32);
                skippableUpdate$iv$iv$iv32.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv32 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i5222 = ($changed$iv$iv$iv32 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int i6222 = ((438 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2438222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
                boolean areEqual322 = Intrinsics.areEqual(selectedCamera, "back");
                $composer3.startReplaceableGroup(-775427838);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if (($dirty2 & 112) != 32) {
                }
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv) {
                }
                value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke("back");
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(areEqual322, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                boolean areEqual222222 = Intrinsics.areEqual(selectedCamera, "front");
                $composer3.startReplaceableGroup(-775427408);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if (($dirty2 & 112) != 32) {
                }
                it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke("front");
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(areEqual222222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier m211backgroundbw27NRU$default2222222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                $composer3.startReplaceableGroup(-775426703);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if ((1879048192 & $dirty2) != 536870912) {
                }
                Object it$iv52222222 = $composer3.rememberedValue();
                if (invalid$iv3) {
                }
                value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function0.invoke();
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                $composer3.endReplaceableGroup();
                Modifier modifier$iv32222222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default2222222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                Alignment contentAlignment$iv2222222 = Alignment.INSTANCE.getCenter();
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv42222222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2222222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                int $changed$iv$iv42222222 = (48 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv42222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv42222222 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv92222222 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv42222222 = LayoutKt.modifierMaterializerOf(modifier$iv32222222);
                int $changed$iv$iv$iv42222222 = (($changed$iv$iv42222222 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv42222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv42222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv42222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv42222222));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv42222222), block$iv$iv$iv42222222);
                skippableUpdate$iv$iv$iv42222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv42222222 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i7222222222 = ($changed$iv$iv$iv42222222 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance222222222 = BoxScopeInstance.INSTANCE;
                int i8222222222 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2438222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier fillMaxWidth$default2222222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically2222222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical m473spacedBy0680j_42222222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                int $changed$iv322222222 = 438;
                $composer3.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv5222222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_42222222222, centerVertically2222222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv5222222222 = (438 << 3) & 112;
                int $i$f$Layout222222222 = 0;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv5222222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv5222222222 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv10222222222 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv5222222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default2222222222);
                int $changed$iv$iv$iv5222222222 = (($changed$iv$iv5222222222 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv5222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5222222222));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv5222222222), block$iv$iv$iv5222222222);
                skippableUpdate$iv$iv$iv5222222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5222222222 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i922222222222 = ($changed$iv$iv$iv5222222222 >> 9) & 14;
                int i1022222222222 = 0;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int $changed222222222222 = ((438 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u244322222222222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                $composer3.startReplaceableGroup(-775425039);
                ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                Iterable $this$forEach$iv32222222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                int $i$f$forEach32222222222 = 0;
                while (r16.hasNext()) {
                }
                $composer2 = $composer42;
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                $composer3.startReplaceableGroup(-775424395);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                it$iv3 = $composer3.rememberedValue();
                if (!invalid$iv4) {
                }
                value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function13.invoke(Boolean.valueOf(!mirrorH));
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                $composer3.startReplaceableGroup(-775423958);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                it$iv4 = $composer3.rememberedValue();
                if (!invalid$iv5) {
                }
                value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function14.invoke(Boolean.valueOf(!mirrorV));
                    }
                };
                $composer3.updateRememberedValue(value$iv5);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u244322222222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                $dirty = $dirty2;
                TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                $composer3.startReplaceableGroup(1780649284);
                ComposerKt.sourceInformation($composer3, "443@17022L146");
                if (isApplying) {
                }
                $composer3.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                }
            }
            $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i12 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i22 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1286356883, "C269@10382L1297,299@11689L29,302@11789L2783,373@14582L29,376@14666L2287:VerificationScreen.kt#2o9c7b");
            Modifier modifier$iv22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getCenterVertically();
            $composer3.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv22 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv22 = (390 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv22 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv22 = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv72 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv22 = LayoutKt.modifierMaterializerOf(modifier$iv22);
            int $changed$iv$iv$iv22 = (($changed$iv$iv22 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer3);
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i322 = ($changed$iv$iv$iv22 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int i422 = ((390 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u243322 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775429443, "C277@10721L7,280@10880L216,273@10519L693,287@11225L28,292@11447L48,288@11266L403:VerificationScreen.kt#2o9c7b");
                Modifier m599height3ABfNKs22 = SizeKt.m599height3ABfNKs(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u243322, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5734constructorimpl(48));
                ProvidableCompositionLocal<TextStyle> localTextStyle22 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume22 = $composer3.consume(localTextStyle22);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(urlText, function15, m599height3ABfNKs22, false, false, TextStyle.m5247copyp1EtxEg$default((TextStyle) consume22, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$VerificationScreenKt.INSTANCE.m6080getLambda3$app_debug(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, OutlinedTextFieldDefaults.INSTANCE.m1731colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, null, ColorKt.Color(4286336511L), ColorKt.Color(4282006108L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 432, 0, 0, 3072, 2147477247, 4095), $composer3, (($dirty12 >> 15) & 14) | 12582912 | (($dirty12 >> 15) & 112), 12582912, 0, 4063064);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Composer $composer422 = $composer3;
                ButtonKt.Button(function04, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(48)), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4286336511L), 0L, 0L, 0L, $composer3, (ButtonDefaults.$stable << 12) | 6, 14), null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(12), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6081getLambda4$app_debug(), $composer3, (($dirty12 >> 21) & 14) | 817889328, 356);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier fillMaxWidth$default32 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically32 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical m473spacedBy0680j_432 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                $composer3.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv322 = RowKt.rowMeasurePolicy(m473spacedBy0680j_432, centerVertically32, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv322 = (438 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv322 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv322 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv822 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv322 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default32);
                int $i$f$Row22 = $changed$iv$iv322 << 9;
                int $changed$iv$iv$iv322 = ($i$f$Row22 & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv322, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv322 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv322));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv322), block$iv$iv$iv322);
                skippableUpdate$iv$iv$iv322.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv322 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i52222 = ($changed$iv$iv$iv322 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int i62222 = ((438 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24382222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
                boolean areEqual3222 = Intrinsics.areEqual(selectedCamera, "back");
                $composer3.startReplaceableGroup(-775427838);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if (($dirty2 & 112) != 32) {
                }
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv) {
                }
                value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke("back");
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(areEqual3222, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                boolean areEqual2222222 = Intrinsics.areEqual(selectedCamera, "front");
                $composer3.startReplaceableGroup(-775427408);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if (($dirty2 & 112) != 32) {
                }
                it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke("front");
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(areEqual2222222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier m211backgroundbw27NRU$default22222222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
                $composer3.startReplaceableGroup(-775426703);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                if ((1879048192 & $dirty2) != 536870912) {
                }
                Object it$iv522222222 = $composer3.rememberedValue();
                if (invalid$iv3) {
                }
                value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function0.invoke();
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                $composer3.endReplaceableGroup();
                Modifier modifier$iv322222222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default22222222, hasGyro, null, null, (Function0) value$iv3, 6, null);
                Alignment contentAlignment$iv22222222 = Alignment.INSTANCE.getCenter();
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv422222222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv22222222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                int $changed$iv$iv422222222 = (48 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv422222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv422222222 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv922222222 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv422222222 = LayoutKt.modifierMaterializerOf(modifier$iv322222222);
                int $changed$iv$iv$iv422222222 = (($changed$iv$iv422222222 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv422222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv422222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv422222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv422222222));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv422222222), block$iv$iv$iv422222222);
                skippableUpdate$iv$iv$iv422222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv422222222 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i72222222222 = ($changed$iv$iv$iv422222222 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2222222222 = BoxScopeInstance.INSTANCE;
                int i82222222222 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
                TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24382222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
                ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
                Modifier fillMaxWidth$default22222222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically22222222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical m473spacedBy0680j_422222222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
                int $changed$iv3222222222 = 438;
                $composer3.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv52222222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_422222222222, centerVertically22222222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv52222222222 = (438 << 3) & 112;
                int $i$f$Layout2222222222 = 0;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv52222222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv52222222222 = $composer3.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv102222222222 = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv52222222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default22222222222);
                int $changed$iv$iv$iv52222222222 = (($changed$iv$iv52222222222 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv52222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv52222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv52222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv52222222222));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv52222222222), block$iv$iv$iv52222222222);
                skippableUpdate$iv$iv$iv52222222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv52222222222 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i9222222222222 = ($changed$iv$iv$iv52222222222 >> 9) & 14;
                int i10222222222222 = 0;
                ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                int $changed2222222222222 = ((438 >> 6) & 112) | 6;
                RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222222222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
                $composer3.startReplaceableGroup(-775425039);
                ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
                Iterable $this$forEach$iv322222222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
                int $i$f$forEach322222222222 = 0;
                while (r16.hasNext()) {
                }
                $composer2 = $composer422;
                $composer3.endReplaceableGroup();
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
                $composer3.startReplaceableGroup(-775424395);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
                it$iv3 = $composer3.rememberedValue();
                if (!invalid$iv4) {
                }
                value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function13.invoke(Boolean.valueOf(!mirrorH));
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
                $composer3.startReplaceableGroup(-775423958);
                ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
                invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
                it$iv4 = $composer3.rememberedValue();
                if (!invalid$iv5) {
                }
                value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function14.invoke(Boolean.valueOf(!mirrorV));
                    }
                };
                $composer3.updateRememberedValue(value$iv5);
                $composer3.endReplaceableGroup();
                ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
                SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2443222222222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
                $dirty = $dirty2;
                TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                $composer3.startReplaceableGroup(1780649284);
                ComposerKt.sourceInformation($composer3, "443@17022L146");
                if (isApplying) {
                }
                $composer3.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv22));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv22), block$iv$iv$iv22);
            skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i3222 = ($changed$iv$iv$iv22 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            int i4222 = ((390 >> 6) & 112) | 6;
            RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u2433222 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -775429443, "C277@10721L7,280@10880L216,273@10519L693,287@11225L28,292@11447L48,288@11266L403:VerificationScreen.kt#2o9c7b");
            Modifier m599height3ABfNKs222 = SizeKt.m599height3ABfNKs(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u2433222, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5734constructorimpl(48));
            ProvidableCompositionLocal<TextStyle> localTextStyle222 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume222 = $composer3.consume(localTextStyle222);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            OutlinedTextFieldKt.OutlinedTextField(urlText, function15, m599height3ABfNKs222, false, false, TextStyle.m5247copyp1EtxEg$default((TextStyle) consume222, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$VerificationScreenKt.INSTANCE.m6080getLambda3$app_debug(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, OutlinedTextFieldDefaults.INSTANCE.m1731colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, null, ColorKt.Color(4286336511L), ColorKt.Color(4282006108L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 432, 0, 0, 3072, 2147477247, 4095), $composer3, (($dirty12 >> 15) & 14) | 12582912 | (($dirty12 >> 15) & 112), 12582912, 0, 4063064);
            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
            Composer $composer4222 = $composer3;
            ButtonKt.Button(function04, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(48)), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4286336511L), 0L, 0L, 0L, $composer3, (ButtonDefaults.$stable << 12) | 6, 14), null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(12), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6081getLambda4$app_debug(), $composer3, (($dirty12 >> 21) & 14) | 817889328, 356);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
            Modifier fillMaxWidth$default322 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically322 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical m473spacedBy0680j_4322 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
            $composer3.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv3222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4322, centerVertically322, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv3222 = (438 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3222 = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv8222 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv3222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default322);
            int $i$f$Row222 = $changed$iv$iv3222 << 9;
            int $changed$iv$iv$iv3222 = ($i$f$Row222 & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer3);
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv3222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3222));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv3222), block$iv$iv$iv3222);
            skippableUpdate$iv$iv$iv3222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3222 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i522222 = ($changed$iv$iv$iv3222 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            int i622222 = ((438 >> 6) & 112) | 6;
            RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u243822222 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -775427929, "C310@12124L26,314@12327L107,308@12033L416,320@12554L27,324@12759L107,318@12462L419,329@12895L28,338@13259L18,332@12969L487,344@13470L27,347@13539L489,360@14070L492:VerificationScreen.kt#2o9c7b");
            boolean areEqual32222 = Intrinsics.areEqual(selectedCamera, "back");
            $composer3.startReplaceableGroup(-775427838);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            if (($dirty2 & 112) != 32) {
            }
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function1.invoke("back");
                }
            };
            $composer3.updateRememberedValue(value$iv);
            $composer3.endReplaceableGroup();
            ChipKt.FilterChip(areEqual32222, (Function0) value$iv, ComposableSingletons$VerificationScreenKt.INSTANCE.m6082getLambda5$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
            boolean areEqual22222222 = Intrinsics.areEqual(selectedCamera, "front");
            $composer3.startReplaceableGroup(-775427408);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            if (($dirty2 & 112) != 32) {
            }
            it$iv2 = $composer3.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function1.invoke("front");
                }
            };
            $composer3.updateRememberedValue(value$iv2);
            $composer3.endReplaceableGroup();
            ChipKt.FilterChip(areEqual22222222, (Function0) value$iv2, ComposableSingletons$VerificationScreenKt.INSTANCE.m6083getLambda6$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, 3456, 0, 3824);
            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
            Modifier m211backgroundbw27NRU$default222222222 = BackgroundKt.m211backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(!gyroEnabled ? 4283215696L : 4294922834L), null, 2, null);
            $composer3.startReplaceableGroup(-775426703);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            if ((1879048192 & $dirty2) != 536870912) {
            }
            Object it$iv5222222222 = $composer3.rememberedValue();
            if (invalid$iv3) {
            }
            value$iv3 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$2$3$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function0.invoke();
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            $composer3.endReplaceableGroup();
            Modifier modifier$iv3222222222 = ClickableKt.m246clickableXHw0xAI$default(m211backgroundbw27NRU$default222222222, hasGyro, null, null, (Function0) value$iv3, 6, null);
            Alignment contentAlignment$iv222222222 = Alignment.INSTANCE.getCenter();
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv4222222222 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv222222222, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv4222222222 = (48 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv4222222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4222222222 = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv9222222222 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv4222222222 = LayoutKt.modifierMaterializerOf(modifier$iv3222222222);
            int $changed$iv$iv$iv4222222222 = (($changed$iv$iv4222222222 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer3);
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv4222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4222222222));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv4222222222), block$iv$iv$iv4222222222);
            skippableUpdate$iv$iv$iv4222222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4222222222 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i722222222222 = ($changed$iv$iv$iv4222222222 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance22222222222 = BoxScopeInstance.INSTANCE;
            int i822222222222 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1894229755, "C341@13364L78:VerificationScreen.kt#2o9c7b");
            TextKt.m2129Text4IGK_g("G", (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 200070, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u243822222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            ButtonKt.FilledTonalButton(function02, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6084getLambda7$app_debug(), $composer3, (($dirty12 >> 6) & 14) | 817889328, 368);
            ButtonKt.FilledTonalButton(function03, SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), !isApplying, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8)), null, null, null, PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5734constructorimpl(8), 0.0f, 2, null), null, ComposableSingletons$VerificationScreenKt.INSTANCE.m6085getLambda8$app_debug(), $composer3, (($dirty12 >> 9) & 14) | 817889328, 368);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer3, 6);
            Modifier fillMaxWidth$default222222222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically222222222222 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical m473spacedBy0680j_4222222222222 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(4));
            int $changed$iv32222222222 = 438;
            $composer3.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv522222222222 = RowKt.rowMeasurePolicy(m473spacedBy0680j_4222222222222, centerVertically222222222222, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv522222222222 = (438 << 3) & 112;
            int $i$f$Layout22222222222 = 0;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv522222222222 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv522222222222 = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv1022222222222 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv522222222222 = LayoutKt.modifierMaterializerOf(fillMaxWidth$default222222222222);
            int $changed$iv$iv$iv522222222222 = (($changed$iv$iv522222222222 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4 = Updater.m2943constructorimpl($composer3);
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv522222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv522222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv522222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv522222222222));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv522222222222), block$iv$iv$iv522222222222);
            skippableUpdate$iv$iv$iv522222222222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv522222222222 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i92222222222222 = ($changed$iv$iv$iv522222222222 >> 9) & 14;
            int i102222222222222 = 0;
            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            int $changed22222222222222 = ((438 >> 6) & 112) | 6;
            RowScope $this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222222222 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -775425063, "C395@15427L28,400@15567L29,404@15770L107,398@15493L399,412@16004L29,416@16207L107,410@15930L399,421@16343L27,424@16406L537:VerificationScreen.kt#2o9c7b");
            $composer3.startReplaceableGroup(-775425039);
            ComposerKt.sourceInformation($composer3, "*385@15046L25,389@15265L115,383@14956L443");
            Iterable $this$forEach$iv3222222222222 = CollectionsKt.listOf((Object[]) new Integer[]{0, 90, 180, 270});
            int $i$f$forEach3222222222222 = 0;
            while (r16.hasNext()) {
            }
            $composer2 = $composer4222;
            $composer3.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(2)), $composer3, 6);
            $composer3.startReplaceableGroup(-775424395);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            invalid$iv4 = ((57344 & $dirty2) != 16384) | (($dirty2 & 458752) != 131072);
            it$iv3 = $composer3.rememberedValue();
            if (!invalid$iv4) {
            }
            value$iv4 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function13.invoke(Boolean.valueOf(!mirrorH));
                }
            };
            $composer3.updateRememberedValue(value$iv4);
            $composer3.endReplaceableGroup();
            ChipKt.FilterChip(mirrorH, (Function0) value$iv4, ComposableSingletons$VerificationScreenKt.INSTANCE.m6086getLambda9$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 12) & 14) | 3456, 0, 3824);
            $composer3.startReplaceableGroup(-775423958);
            ComposerKt.sourceInformation($composer3, "CC(remember):VerificationScreen.kt#9igjgp");
            invalid$iv5 = ((29360128 & $dirty2) != 8388608) | (($dirty2 & 3670016) != 1048576);
            it$iv4 = $composer3.rememberedValue();
            if (!invalid$iv5) {
            }
            value$iv5 = (Function0) new Function0<Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$1$3$3$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function14.invoke(Boolean.valueOf(!mirrorV));
                }
            };
            $composer3.updateRememberedValue(value$iv5);
            $composer3.endReplaceableGroup();
            ChipKt.FilterChip(mirrorV, (Function0) value$iv5, ComposableSingletons$VerificationScreenKt.INSTANCE.m6078getLambda10$app_debug(), SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28)), false, null, null, null, FilterChipDefaults.INSTANCE.m1566filterChipColorsXqyqHi0(0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4280391411L), 0L, 0L, 0L, 0L, $composer3, 12582912, FilterChipDefaults.$stable << 6, 3967), null, null, null, $composer3, (($dirty2 >> 18) & 14) | 3456, 0, 3824);
            SpacerKt.Spacer(RowScope.weight$default($this$ControlPanel_u24lambda_u2444_u24lambda_u24432222222222222, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            $dirty = $dirty2;
            TextKt.m2129Text4IGK_g(statusText, SizeKt.m620widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(100), 1, null), !StringsKt.startsWith$default(statusText, "✓", false, 2, (Object) null) ? ColorKt.Color(4283215696L) : StringsKt.startsWith$default(statusText, "✗", false, 2, (Object) null) ? ColorKt.Color(4294922834L) : ColorKt.Color(4289769672L), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5676getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty12 >> 12) & 14) | 3120, 3120, 120816);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(1780649284);
            ComposerKt.sourceInformation($composer3, "443@17022L146");
            if (isApplying) {
            }
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.VerificationScreenKt$ControlPanel$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i13) {
                    VerificationScreenKt.ControlPanel(selectedCamera, function1, rotation, function12, mirrorH, function13, mirrorV, function14, gyroEnabled, function0, hasGyro, isApplying, function02, function03, statusText, urlText, function15, function04, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }
}

package com.fakehal.controller;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CameraAltKt;
import androidx.compose.material.icons.filled.FingerprintKt;
import androidx.compose.material.icons.filled.LanguageKt;
import androidx.compose.material.icons.filled.PhotoCameraKt;
import androidx.compose.material.icons.filled.SecurityKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f85lambda1 = ComposableLambdaKt.composableLambdaInstance(-1506119231, false, ComposableSingletons$MainActivityKt$lambda1$1.INSTANCE);

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f96lambda2 = ComposableLambdaKt.composableLambdaInstance(99610313, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C43@1539L1497:MainActivity.kt#2o9c7b");
            if (($changed & 11) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(99610313, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-2.<anonymous> (MainActivity.kt:43)");
                }
                FakeHalThemeKt.FakeHalTheme(ComposableSingletons$MainActivityKt.INSTANCE.m6052getLambda1$app_debug(), $composer, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f101lambda3 = ComposableLambdaKt.composableLambdaInstance(-2000218239, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x01e1  */
        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C183@6868L300:MainActivity.kt#2o9c7b");
            if (($changed & 11) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2000218239, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-3.<anonymous> (MainActivity.kt:183)");
                }
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                $composer.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation($composer, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                Modifier modifier$iv = Modifier.INSTANCE;
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv = (384 << 3) & 112;
                $composer.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
                Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(factory$iv$iv$iv);
                } else {
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer);
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer)), $composer, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                    $composer.startReplaceableGroup(2058660585);
                    int i = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i2 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 2116243828, "C184@6946L63,185@7034L28,186@7087L59:MainActivity.kt#2o9c7b");
                    TextKt.m2129Text4IGK_g("FakeHAL", (Modifier) null, 0L, TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
                    SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(8)), $composer, 6);
                    TextKt.m2129Text4IGK_g("v7.6.7", (Modifier) null, ColorKt.Color(4286336511L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3462, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceableGroup();
                    $composer.endNode();
                    $composer.endReplaceableGroup();
                    $composer.endReplaceableGroup();
                    if (!ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer)), $composer, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer.startReplaceableGroup(2058660585);
                int i3 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i22 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 2116243828, "C184@6946L63,185@7034L28,186@7087L59:MainActivity.kt#2o9c7b");
                TextKt.m2129Text4IGK_g("FakeHAL", (Modifier) null, 0L, TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(8)), $composer, 6);
                TextKt.m2129Text4IGK_g("v7.6.7", (Modifier) null, ColorKt.Color(4286336511L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3462, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceableGroup();
                $composer.endNode();
                $composer.endReplaceableGroup();
                $composer.endReplaceableGroup();
                if (!ComposerKt.isTraceInProgress()) {
                }
            } else {
                $composer.skipToGroupEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f102lambda4 = ComposableLambdaKt.composableLambdaInstance(984843197, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C191@7323L11,190@7251L114,181@6811L569:MainActivity.kt#2o9c7b");
            if (($changed & 11) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(984843197, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-4.<anonymous> (MainActivity.kt:181)");
                }
                AppBarKt.TopAppBar(ComposableSingletons$MainActivityKt.INSTANCE.m6068getLambda3$app_debug(), null, null, null, null, TopAppBarDefaults.INSTANCE.m2288topAppBarColorszjMxDiM(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), null, $composer, 6, 94);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f103lambda5 = ComposableLambdaKt.composableLambdaInstance(-197997966, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C256@10356L31:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-197997966, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-5.<anonymous> (MainActivity.kt:256)");
            }
            TextKt.m2129Text4IGK_g("Start", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f104lambda6 = ComposableLambdaKt.composableLambdaInstance(-1569763724, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C270@11105L30:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1569763724, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-6.<anonymous> (MainActivity.kt:270)");
            }
            TextKt.m2129Text4IGK_g("Stop", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f105lambda7 = ComposableLambdaKt.composableLambdaInstance(857022364, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C283@11652L57,284@11730L28,285@11779L70:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(857022364, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-7.<anonymous> (MainActivity.kt:283)");
                }
                IconKt.m1601Iconww6aTOc(CameraAltKt.getCameraAlt(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Camera Control", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-8, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f106lambda8 = ComposableLambdaKt.composableLambdaInstance(-1810587947, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C295@12217L59,296@12297L28,297@12346L34:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1810587947, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-8.<anonymous> (MainActivity.kt:295)");
                }
                IconKt.m1601Iconww6aTOc(FingerprintKt.getFingerprint(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Identity", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-9, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f107lambda9 = ComposableLambdaKt.composableLambdaInstance(-1432436468, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-9$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C305@12643L56,306@12720L28,307@12769L38:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1432436468, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-9.<anonymous> (MainActivity.kt:305)");
                }
                IconKt.m1601Iconww6aTOc(LanguageKt.getLanguage(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Verification", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-10, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f86lambda10 = ComposableLambdaKt.composableLambdaInstance(-592308330, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-10$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C317@13175L56,318@13252L28,319@13301L34:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-592308330, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-10.<anonymous> (MainActivity.kt:317)");
                }
                IconKt.m1601Iconww6aTOc(SecurityKt.getSecurity(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Security", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-11, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f87lambda11 = ComposableLambdaKt.composableLambdaInstance(625971287, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-11$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C516@22948L59,517@23028L28,518@23077L34:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(625971287, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-11.<anonymous> (MainActivity.kt:516)");
                }
                IconKt.m1601Iconww6aTOc(PhotoCameraKt.getPhotoCamera(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Test Cam", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-12, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f88lambda12 = ComposableLambdaKt.composableLambdaInstance(1004122766, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-12$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C531@23660L56,532@23737L28,533@23786L37:MainActivity.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1004122766, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-12.<anonymous> (MainActivity.kt:531)");
                }
                IconKt.m1601Iconww6aTOc(LanguageKt.getLanguage(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Chrome Test", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-13, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f89lambda13 = ComposableLambdaKt.composableLambdaInstance(843007805, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-13$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C558@24986L34:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(843007805, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-13.<anonymous> (MainActivity.kt:558)");
            }
            TextKt.m2129Text4IGK_g("Set Back", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-14, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f90lambda14 = ComposableLambdaKt.composableLambdaInstance(1447078207, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-14$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C565@25349L32:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1447078207, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-14.<anonymous> (MainActivity.kt:565)");
            }
            TextKt.m2129Text4IGK_g("Slot A", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-15, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f91lambda15 = ComposableLambdaKt.composableLambdaInstance(-82628042, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-15$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C572@25710L32:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-82628042, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-15.<anonymous> (MainActivity.kt:572)");
            }
            TextKt.m2129Text4IGK_g("Slot B", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-16, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f92lambda16 = ComposableLambdaKt.composableLambdaInstance(1355424823, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-16$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C586@26442L30:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1355424823, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-16.<anonymous> (MainActivity.kt:586)");
            }
            TextKt.m2129Text4IGK_g("Swap", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-17, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f93lambda17 = ComposableLambdaKt.composableLambdaInstance(1104523162, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-17$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C604@27225L41:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1104523162, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-17.<anonymous> (MainActivity.kt:604)");
            }
            TextKt.m2129Text4IGK_g("Set Front Video", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-18, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f94lambda18 = ComposableLambdaKt.composableLambdaInstance(1020808124, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-18$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C681@30163L15:MainActivity.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1020808124, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-18.<anonymous> (MainActivity.kt:681)");
            }
            TextKt.m2129Text4IGK_g("ISO Min", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-19, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f95lambda19 = ComposableLambdaKt.composableLambdaInstance(990362533, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-19$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C694@30758L15:MainActivity.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(990362533, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-19.<anonymous> (MainActivity.kt:694)");
            }
            TextKt.m2129Text4IGK_g("ISO Max", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-20, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f97lambda20 = ComposableLambdaKt.composableLambdaInstance(-609914614, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-20$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C727@32012L29:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-609914614, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-20.<anonymous> (MainActivity.kt:727)");
            }
            TextKt.m2129Text4IGK_g("Apply Sensor Settings", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-21, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f98lambda21 = ComposableLambdaKt.composableLambdaInstance(1443819777, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-21$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C760@33259L16:MainActivity.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1443819777, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-21.<anonymous> (MainActivity.kt:760)");
            }
            TextKt.m2129Text4IGK_g("Latitude", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-22, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f99lambda22 = ComposableLambdaKt.composableLambdaInstance(-230900374, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-22$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C773@33849L17:MainActivity.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-230900374, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-22.<anonymous> (MainActivity.kt:773)");
            }
            TextKt.m2129Text4IGK_g("Longitude", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-23, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f100lambda23 = ComposableLambdaKt.composableLambdaInstance(-1519108762, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-23$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C790@34546L17:MainActivity.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1519108762, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-23.<anonymous> (MainActivity.kt:790)");
            }
            TextKt.m2129Text4IGK_g("Apply GPS", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6052getLambda1$app_debug() {
        return f85lambda1;
    }

    /* renamed from: getLambda-10$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6053getLambda10$app_debug() {
        return f86lambda10;
    }

    /* renamed from: getLambda-11$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6054getLambda11$app_debug() {
        return f87lambda11;
    }

    /* renamed from: getLambda-12$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6055getLambda12$app_debug() {
        return f88lambda12;
    }

    /* renamed from: getLambda-13$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6056getLambda13$app_debug() {
        return f89lambda13;
    }

    /* renamed from: getLambda-14$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6057getLambda14$app_debug() {
        return f90lambda14;
    }

    /* renamed from: getLambda-15$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6058getLambda15$app_debug() {
        return f91lambda15;
    }

    /* renamed from: getLambda-16$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6059getLambda16$app_debug() {
        return f92lambda16;
    }

    /* renamed from: getLambda-17$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6060getLambda17$app_debug() {
        return f93lambda17;
    }

    /* renamed from: getLambda-18$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6061getLambda18$app_debug() {
        return f94lambda18;
    }

    /* renamed from: getLambda-19$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6062getLambda19$app_debug() {
        return f95lambda19;
    }

    /* renamed from: getLambda-2$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6063getLambda2$app_debug() {
        return f96lambda2;
    }

    /* renamed from: getLambda-20$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6064getLambda20$app_debug() {
        return f97lambda20;
    }

    /* renamed from: getLambda-21$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6065getLambda21$app_debug() {
        return f98lambda21;
    }

    /* renamed from: getLambda-22$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6066getLambda22$app_debug() {
        return f99lambda22;
    }

    /* renamed from: getLambda-23$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6067getLambda23$app_debug() {
        return f100lambda23;
    }

    /* renamed from: getLambda-3$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6068getLambda3$app_debug() {
        return f101lambda3;
    }

    /* renamed from: getLambda-4$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6069getLambda4$app_debug() {
        return f102lambda4;
    }

    /* renamed from: getLambda-5$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6070getLambda5$app_debug() {
        return f103lambda5;
    }

    /* renamed from: getLambda-6$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6071getLambda6$app_debug() {
        return f104lambda6;
    }

    /* renamed from: getLambda-7$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6072getLambda7$app_debug() {
        return f105lambda7;
    }

    /* renamed from: getLambda-8$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6073getLambda8$app_debug() {
        return f106lambda8;
    }

    /* renamed from: getLambda-9$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6074getLambda9$app_debug() {
        return f107lambda9;
    }
}

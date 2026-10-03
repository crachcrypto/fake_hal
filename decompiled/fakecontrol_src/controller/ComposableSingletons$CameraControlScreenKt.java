package com.fakehal.controller;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.CameraFrontKt;
import androidx.compose.material.icons.filled.CameraRearKt;
import androidx.compose.material.icons.filled.FlipKt;
import androidx.compose.material.icons.filled.FlipToBackKt;
import androidx.compose.material.icons.filled.LanguageKt;
import androidx.compose.material.icons.filled.PhotoCameraKt;
import androidx.compose.material.icons.filled.PhotoKt;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material.icons.filled.RestartAltKt;
import androidx.compose.material.icons.filled.RotateLeftKt;
import androidx.compose.material.icons.filled.RotateRightKt;
import androidx.compose.material.icons.filled.VideocamKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraControlScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$CameraControlScreenKt {
    public static final ComposableSingletons$CameraControlScreenKt INSTANCE = new ComposableSingletons$CameraControlScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f56lambda1 = ComposableLambdaKt.composableLambdaInstance(-2014274993, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C91@3598L52:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2014274993, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-1.<anonymous> (CameraControlScreen.kt:91)");
            }
            TextKt.m2129Text4IGK_g("Camera Control", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f67lambda2 = ComposableLambdaKt.composableLambdaInstance(-1724928112, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C94@3776L37:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1724928112, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-2.<anonymous> (CameraControlScreen.kt:94)");
            }
            IconKt.m1601Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.INSTANCE.getDefault()), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f73lambda3 = ComposableLambdaKt.composableLambdaInstance(-537547756, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C119@4836L53,120@4914L28,121@4967L31:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-537547756, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-3.<anonymous> (CameraControlScreen.kt:119)");
                }
                IconKt.m1601Iconww6aTOc(PhotoKt.getPhoto(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Photo", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f74lambda4 = ComposableLambdaKt.composableLambdaInstance(-743020718, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C128@5289L56,129@5370L28,130@5423L31:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-743020718, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-4.<anonymous> (CameraControlScreen.kt:128)");
                }
                IconKt.m1601Iconww6aTOc(VideocamKt.getVideocam(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(6)), $composer, 6);
                TextKt.m2129Text4IGK_g("Video", (Modifier) null, 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
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
    public static Function2<Composer, Integer, Unit> f75lambda5 = ComposableLambdaKt.composableLambdaInstance(1938957235, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C171@7488L90:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1938957235, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-5.<anonymous> (CameraControlScreen.kt:171)");
            }
            IconKt.m1601Iconww6aTOc(RotateLeftKt.getRotateLeft(Icons.INSTANCE.getDefault()), "CCW", SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(26)), Color.INSTANCE.m3450getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f76lambda6 = ComposableLambdaKt.composableLambdaInstance(-1899748964, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C181@8134L90:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1899748964, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-6.<anonymous> (CameraControlScreen.kt:181)");
            }
            IconKt.m1601Iconww6aTOc(RotateRightKt.getRotateRight(Icons.INSTANCE.getDefault()), "CW", SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(26)), Color.INSTANCE.m3450getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f77lambda7 = ComposableLambdaKt.composableLambdaInstance(1781528719, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C190@8803L87:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1781528719, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-7.<anonymous> (CameraControlScreen.kt:190)");
            }
            IconKt.m1601Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), "180", SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(22)), Color.INSTANCE.m3450getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-8, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f78lambda8 = ComposableLambdaKt.composableLambdaInstance(1000081080, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C194@9201L98:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1000081080, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-8.<anonymous> (CameraControlScreen.kt:194)");
            }
            IconKt.m1601Iconww6aTOc(RestartAltKt.getRestartAlt(Icons.INSTANCE.getDefault()), "Reset", SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(22)), ColorKt.Color(4294940672L), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-9, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f79lambda9 = ComposableLambdaKt.composableLambdaInstance(667483610, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-9$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C218@10419L16:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(667483610, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-9.<anonymous> (CameraControlScreen.kt:218)");
            }
            TextKt.m2129Text4IGK_g("Mirror H", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-10, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f57lambda10 = ComposableLambdaKt.composableLambdaInstance(1906202231, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-10$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C219@10479L52:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1906202231, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-10.<anonymous> (CameraControlScreen.kt:219)");
            }
            IconKt.m1601Iconww6aTOc(FlipKt.getFlip(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-11, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f58lambda11 = ComposableLambdaKt.composableLambdaInstance(-1556226237, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-11$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C225@10843L16:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1556226237, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-11.<anonymous> (CameraControlScreen.kt:225)");
            }
            TextKt.m2129Text4IGK_g("Mirror V", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-12, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f59lambda12 = ComposableLambdaKt.composableLambdaInstance(-853572448, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-12$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C226@10903L58:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-853572448, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-12.<anonymous> (CameraControlScreen.kt:226)");
            }
            IconKt.m1601Iconww6aTOc(FlipToBackKt.getFlipToBack(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-13, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f60lambda13 = ComposableLambdaKt.composableLambdaInstance(1722532710, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-13$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C237@11437L16:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1722532710, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-13.<anonymous> (CameraControlScreen.kt:237)");
            }
            TextKt.m2129Text4IGK_g("Back (0)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-14, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f61lambda14 = ComposableLambdaKt.composableLambdaInstance(1543781929, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-14$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C238@11501L58:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1543781929, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-14.<anonymous> (CameraControlScreen.kt:238)");
            }
            IconKt.m1601Iconww6aTOc(CameraRearKt.getCameraRear(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-15, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f62lambda15 = ComposableLambdaKt.composableLambdaInstance(1456848861, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-15$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C244@11841L17:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456848861, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-15.<anonymous> (CameraControlScreen.kt:244)");
            }
            TextKt.m2129Text4IGK_g("Front (1)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-16, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f63lambda16 = ComposableLambdaKt.composableLambdaInstance(1476040160, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-16$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C245@11906L59:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1476040160, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-16.<anonymous> (CameraControlScreen.kt:245)");
            }
            IconKt.m1601Iconww6aTOc(CameraFrontKt.getCameraFront(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-17, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f64lambda17 = ComposableLambdaKt.composableLambdaInstance(1495160174, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-17$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C271@13208L19:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1495160174, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-17.<anonymous> (CameraControlScreen.kt:271)");
            }
            TextKt.m2129Text4IGK_g("Center Crop", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-18, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f65lambda18 = ComposableLambdaKt.composableLambdaInstance(-423948251, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-18$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C277@13577L17:CameraControlScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-423948251, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-18.<anonymous> (CameraControlScreen.kt:277)");
            }
            TextKt.m2129Text4IGK_g("Fit + Pad", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-19, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f66lambda19 = ComposableLambdaKt.composableLambdaInstance(-1374704038, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-19$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C380@19205L35:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1374704038, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-19.<anonymous> (CameraControlScreen.kt:380)");
            }
            TextKt.m2129Text4IGK_g("Apply All", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-20, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f68lambda20 = ComposableLambdaKt.composableLambdaInstance(-274093181, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-20$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C390@19787L46:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-274093181, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-20.<anonymous> (CameraControlScreen.kt:390)");
            }
            TextKt.m2129Text4IGK_g("Preview→Capture", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-21, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f69lambda21 = ComposableLambdaKt.composableLambdaInstance(778943586, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-21$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C400@20380L46:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(778943586, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-21.<anonymous> (CameraControlScreen.kt:400)");
            }
            TextKt.m2129Text4IGK_g("Capture→Preview", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-22, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f70lambda22 = ComposableLambdaKt.composableLambdaInstance(422746425, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-22$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C421@21421L55,422@21501L28,423@21554L36:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(422746425, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-22.<anonymous> (CameraControlScreen.kt:421)");
                }
                IconKt.m1601Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Reload HAL", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-23, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f71lambda23 = ComposableLambdaKt.composableLambdaInstance(1523357282, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-23$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C441@22441L59,442@22525L28,443@22578L34:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1523357282, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-23.<anonymous> (CameraControlScreen.kt:441)");
                }
                IconKt.m1601Iconww6aTOc(PhotoCameraKt.getPhotoCamera(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Test Cam", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-24, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f72lambda24 = ComposableLambdaKt.composableLambdaInstance(-1718573247, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$CameraControlScreenKt$lambda-24$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C461@23465L56,462@23546L28,463@23599L32:CameraControlScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1718573247, $changed, -1, "com.fakehal.controller.ComposableSingletons$CameraControlScreenKt.lambda-24.<anonymous> (CameraControlScreen.kt:461)");
                }
                IconKt.m1601Iconww6aTOc(LanguageKt.getLanguage(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Chrome", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6023getLambda1$app_debug() {
        return f56lambda1;
    }

    /* renamed from: getLambda-10$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6024getLambda10$app_debug() {
        return f57lambda10;
    }

    /* renamed from: getLambda-11$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6025getLambda11$app_debug() {
        return f58lambda11;
    }

    /* renamed from: getLambda-12$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6026getLambda12$app_debug() {
        return f59lambda12;
    }

    /* renamed from: getLambda-13$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6027getLambda13$app_debug() {
        return f60lambda13;
    }

    /* renamed from: getLambda-14$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6028getLambda14$app_debug() {
        return f61lambda14;
    }

    /* renamed from: getLambda-15$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6029getLambda15$app_debug() {
        return f62lambda15;
    }

    /* renamed from: getLambda-16$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6030getLambda16$app_debug() {
        return f63lambda16;
    }

    /* renamed from: getLambda-17$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6031getLambda17$app_debug() {
        return f64lambda17;
    }

    /* renamed from: getLambda-18$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6032getLambda18$app_debug() {
        return f65lambda18;
    }

    /* renamed from: getLambda-19$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6033getLambda19$app_debug() {
        return f66lambda19;
    }

    /* renamed from: getLambda-2$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6034getLambda2$app_debug() {
        return f67lambda2;
    }

    /* renamed from: getLambda-20$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6035getLambda20$app_debug() {
        return f68lambda20;
    }

    /* renamed from: getLambda-21$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6036getLambda21$app_debug() {
        return f69lambda21;
    }

    /* renamed from: getLambda-22$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6037getLambda22$app_debug() {
        return f70lambda22;
    }

    /* renamed from: getLambda-23$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6038getLambda23$app_debug() {
        return f71lambda23;
    }

    /* renamed from: getLambda-24$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6039getLambda24$app_debug() {
        return f72lambda24;
    }

    /* renamed from: getLambda-3$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6040getLambda3$app_debug() {
        return f73lambda3;
    }

    /* renamed from: getLambda-4$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6041getLambda4$app_debug() {
        return f74lambda4;
    }

    /* renamed from: getLambda-5$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6042getLambda5$app_debug() {
        return f75lambda5;
    }

    /* renamed from: getLambda-6$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6043getLambda6$app_debug() {
        return f76lambda6;
    }

    /* renamed from: getLambda-7$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6044getLambda7$app_debug() {
        return f77lambda7;
    }

    /* renamed from: getLambda-8$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6045getLambda8$app_debug() {
        return f78lambda8;
    }

    /* renamed from: getLambda-9$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6046getLambda9$app_debug() {
        return f79lambda9;
    }
}

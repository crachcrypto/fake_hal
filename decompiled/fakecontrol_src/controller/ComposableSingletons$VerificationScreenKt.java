package com.fakehal.controller;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.PhotoKt;
import androidx.compose.material.icons.filled.VideocamKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
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

/* compiled from: VerificationScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$VerificationScreenKt {
    public static final ComposableSingletons$VerificationScreenKt INSTANCE = new ComposableSingletons$VerificationScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f110lambda1 = ComposableLambdaKt.composableLambdaInstance(1018500461, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C123@5237L38:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1018500461, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-1.<anonymous> (VerificationScreen.kt:123)");
            }
            TextKt.m2129Text4IGK_g("Verification", (Modifier) null, 0L, TextUnitKt.getSp(18), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f112lambda2 = ComposableLambdaKt.composableLambdaInstance(1644564654, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C126@5389L58:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1644564654, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-2.<anonymous> (VerificationScreen.kt:126)");
            }
            IconKt.m1601Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.INSTANCE.getDefault()), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f113lambda3 = ComposableLambdaKt.composableLambdaInstance(519810011, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C285@11130L65:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(519810011, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-3.<anonymous> (VerificationScreen.kt:285)");
            }
            TextKt.m2129Text4IGK_g("Enter URL...", (Modifier) null, ColorKt.Color(4284901000L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3462, 0, 131058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f114lambda4 = ComposableLambdaKt.composableLambdaInstance(-450294972, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C295@11597L58:VerificationScreen.kt#2o9c7b");
            if (($changed & 81) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-450294972, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-4.<anonymous> (VerificationScreen.kt:295)");
            }
            TextKt.m2129Text4IGK_g("Go", (Modifier) null, 0L, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f115lambda5 = ComposableLambdaKt.composableLambdaInstance(1295348234, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C311@12178L30:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1295348234, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-5.<anonymous> (VerificationScreen.kt:311)");
            }
            TextKt.m2129Text4IGK_g("Back", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f116lambda6 = ComposableLambdaKt.composableLambdaInstance(-1352428813, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C321@12609L31:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1352428813, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-6.<anonymous> (VerificationScreen.kt:321)");
            }
            TextKt.m2129Text4IGK_g("Front", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f117lambda7 = ComposableLambdaKt.composableLambdaInstance(-1350325941, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope FilledTonalButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(FilledTonalButton, "$this$FilledTonalButton");
            ComposerKt.sourceInformation($composer, "C354@13836L85,355@13938L28,356@13983L31:VerificationScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1350325941, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-7.<anonymous> (VerificationScreen.kt:354)");
                }
                IconKt.m1601Iconww6aTOc(PhotoKt.getPhoto(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Photo", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
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
    public static Function3<RowScope, Composer, Integer, Unit> f118lambda8 = ComposableLambdaKt.composableLambdaInstance(-1224671756, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope FilledTonalButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(FilledTonalButton, "$this$FilledTonalButton");
            ComposerKt.sourceInformation($composer, "C367@14367L88,368@14472L28,369@14517L31:VerificationScreen.kt#2o9c7b");
            if (($changed & 81) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1224671756, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-8.<anonymous> (VerificationScreen.kt:367)");
                }
                IconKt.m1601Iconww6aTOc(VideocamKt.getVideocam(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer, 6);
                TextKt.m2129Text4IGK_g("Video", (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
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
    public static Function2<Composer, Integer, Unit> f119lambda9 = ComposableLambdaKt.composableLambdaInstance(-706963095, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-9$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C401@15624L27:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-706963095, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-9.<anonymous> (VerificationScreen.kt:401)");
            }
            TextKt.m2129Text4IGK_g("↔", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-10, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f111lambda10 = ComposableLambdaKt.composableLambdaInstance(940227154, false, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.ComposableSingletons$VerificationScreenKt$lambda-10$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C413@16061L27:VerificationScreen.kt#2o9c7b");
            if (($changed & 11) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(940227154, $changed, -1, "com.fakehal.controller.ComposableSingletons$VerificationScreenKt.lambda-10.<anonymous> (VerificationScreen.kt:413)");
            }
            TextKt.m2129Text4IGK_g("↕", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6077getLambda1$app_debug() {
        return f110lambda1;
    }

    /* renamed from: getLambda-10$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6078getLambda10$app_debug() {
        return f111lambda10;
    }

    /* renamed from: getLambda-2$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6079getLambda2$app_debug() {
        return f112lambda2;
    }

    /* renamed from: getLambda-3$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6080getLambda3$app_debug() {
        return f113lambda3;
    }

    /* renamed from: getLambda-4$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6081getLambda4$app_debug() {
        return f114lambda4;
    }

    /* renamed from: getLambda-5$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6082getLambda5$app_debug() {
        return f115lambda5;
    }

    /* renamed from: getLambda-6$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6083getLambda6$app_debug() {
        return f116lambda6;
    }

    /* renamed from: getLambda-7$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6084getLambda7$app_debug() {
        return f117lambda7;
    }

    /* renamed from: getLambda-8$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6085getLambda8$app_debug() {
        return f118lambda8;
    }

    /* renamed from: getLambda-9$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6086getLambda9$app_debug() {
        return f119lambda9;
    }
}

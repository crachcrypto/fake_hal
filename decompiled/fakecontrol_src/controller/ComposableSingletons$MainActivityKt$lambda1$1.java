package com.fakehal.controller;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* renamed from: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1, reason: invalid class name */
/* loaded from: classes3.dex */
final class ComposableSingletons$MainActivityKt$lambda1$1 extends Lambda implements Function2<Composer, Integer, Unit> {
    public static final ComposableSingletons$MainActivityKt$lambda1$1 INSTANCE = new ComposableSingletons$MainActivityKt$lambda1$1();

    /* compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$WhenMappings */
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ControllerRoute.values().length];
            try {
                iArr[ControllerRoute.Home.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ControllerRoute.Identity.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ControllerRoute.Verification.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ControllerRoute.CameraControl.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ControllerRoute.Security.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    ComposableSingletons$MainActivityKt$lambda1$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    private static final ControllerRoute invoke$lambda$1(MutableState<ControllerRoute> mutableState) {
        MutableState<ControllerRoute> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public final void invoke(Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv8;
        Object value$iv9;
        ComposerKt.sourceInformation($composer, "C44@1583L49:MainActivity.kt#2o9c7b");
        if (($changed & 11) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1506119231, $changed, -1, "com.fakehal.controller.ComposableSingletons$MainActivityKt.lambda-1.<anonymous> (MainActivity.kt:44)");
        }
        $composer.startReplaceableGroup(1921034402);
        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ControllerRoute.Home, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        final MutableState route$delegate = (MutableState) value$iv;
        $composer.endReplaceableGroup();
        switch (WhenMappings.$EnumSwitchMapping$0[invoke$lambda$1(route$delegate).ordinal()]) {
            case 1:
                $composer.startReplaceableGroup(1921034527);
                ComposerKt.sourceInformation($composer, "48@1794L36,49@1881L40,50@1973L41,51@2061L36,47@1734L390");
                $composer.startReplaceableGroup(1921034613);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv2 = $composer.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$1$1
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
                            route$delegate.setValue(ControllerRoute.Identity);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                Function0 function0 = (Function0) value$iv2;
                $composer.endReplaceableGroup();
                $composer.startReplaceableGroup(1921034700);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv3 = $composer.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$2$1
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
                            route$delegate.setValue(ControllerRoute.Verification);
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                Function0 function02 = (Function0) value$iv3;
                $composer.endReplaceableGroup();
                $composer.startReplaceableGroup(1921034792);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv4 = $composer.rememberedValue();
                if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$3$1
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
                            route$delegate.setValue(ControllerRoute.CameraControl);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv4;
                }
                Function0 function03 = (Function0) value$iv4;
                $composer.endReplaceableGroup();
                $composer.startReplaceableGroup(1921034880);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv5 = $composer.rememberedValue();
                if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$4$1
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
                            route$delegate.setValue(ControllerRoute.Security);
                        }
                    };
                    $composer.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv5;
                }
                $composer.endReplaceableGroup();
                MainActivityKt.FakeHalScreen(function0, function02, function03, (Function0) value$iv5, $composer, 3510);
                $composer.endReplaceableGroup();
                break;
            case 2:
                $composer.startReplaceableGroup(1921035015);
                ComposerKt.sourceInformation($composer, "57@2275L32,56@2222L112");
                $composer.startReplaceableGroup(1921035094);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv6 = $composer.rememberedValue();
                if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$5$1
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
                            route$delegate.setValue(ControllerRoute.Home);
                        }
                    };
                    $composer.updateRememberedValue(value$iv6);
                } else {
                    value$iv6 = it$iv6;
                }
                $composer.endReplaceableGroup();
                IdentityScreenKt.IdentityScreen((Function0) value$iv6, $composer, 6);
                $composer.endReplaceableGroup();
                break;
            case 3:
                $composer.startReplaceableGroup(1921035229);
                ComposerKt.sourceInformation($composer, "63@2493L32,62@2436L116");
                $composer.startReplaceableGroup(1921035312);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv7 = $composer.rememberedValue();
                if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                    value$iv7 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$6$1
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
                            route$delegate.setValue(ControllerRoute.Home);
                        }
                    };
                    $composer.updateRememberedValue(value$iv7);
                } else {
                    value$iv7 = it$iv7;
                }
                $composer.endReplaceableGroup();
                VerificationScreenKt.VerificationScreen((Function0) value$iv7, $composer, 6);
                $composer.endReplaceableGroup();
                break;
            case 4:
                $composer.startReplaceableGroup(1921035448);
                ComposerKt.sourceInformation($composer, "69@2713L32,68@2655L117");
                $composer.startReplaceableGroup(1921035532);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv8 = $composer.rememberedValue();
                if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                    value$iv8 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$7$1
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
                            route$delegate.setValue(ControllerRoute.Home);
                        }
                    };
                    $composer.updateRememberedValue(value$iv8);
                } else {
                    value$iv8 = it$iv8;
                }
                $composer.endReplaceableGroup();
                CameraControlScreenKt.CameraControlScreen((Function0) value$iv8, $composer, 6);
                $composer.endReplaceableGroup();
                break;
            case 5:
                $composer.startReplaceableGroup(1921035663);
                ComposerKt.sourceInformation($composer, "75@2923L32,74@2870L112");
                $composer.startReplaceableGroup(1921035742);
                ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                Object it$iv9 = $composer.rememberedValue();
                if (it$iv9 == Composer.INSTANCE.getEmpty()) {
                    value$iv9 = new Function0<Unit>() { // from class: com.fakehal.controller.ComposableSingletons$MainActivityKt$lambda-1$1$8$1
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
                            route$delegate.setValue(ControllerRoute.Home);
                        }
                    };
                    $composer.updateRememberedValue(value$iv9);
                } else {
                    value$iv9 = it$iv9;
                }
                $composer.endReplaceableGroup();
                SecurityScreenKt.SecurityScreen((Function0) value$iv9, $composer, 6);
                $composer.endReplaceableGroup();
                break;
            default:
                $composer.startReplaceableGroup(1921035841);
                $composer.endReplaceableGroup();
                break;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}

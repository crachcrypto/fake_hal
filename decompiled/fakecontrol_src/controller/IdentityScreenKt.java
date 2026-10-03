package com.fakehal.controller;

import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
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
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
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
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.profileinstaller.ProfileVerifier;
import com.fakehal.controller.IdentityManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: IdentityScreen.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006H\u0003¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"IdentityField", "", "label", "", "value", "onValueChange", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "IdentityScreen", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_debug", "manufacturer", "model", "brand", "device", "fingerprint", "serial", "statusText"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IdentityScreenKt {
    public static final void IdentityScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv$iv$iv;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv8;
        MutableState mutableStateOf$default;
        MutableState mutableStateOf$default2;
        MutableState mutableStateOf$default3;
        MutableState mutableStateOf$default4;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer2 = $composer.startRestartGroup(237182759);
        ComposerKt.sourceInformation($composer2, "C(IdentityScreen)23@909L24,24@958L31,25@1007L31,26@1056L31,27@1106L31,28@1161L31,29@1211L31,30@1265L41,32@1333L316,32@1312L337,43@1655L5190:IdentityScreen.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 11) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(237182759, $dirty2, -1, "com.fakehal.controller.IdentityScreen (IdentityScreen.kt:22)");
            }
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13614049);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState manufacturer$delegate = (MutableState) value$iv;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13614000);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState model$delegate = (MutableState) value$iv2;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13613951);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                value$iv3 = mutableStateOf$default4;
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState brand$delegate = (MutableState) value$iv3;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13613901);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                value$iv4 = mutableStateOf$default3;
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState device$delegate = (MutableState) value$iv4;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13613846);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv5 = $composer2.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                value$iv5 = mutableStateOf$default2;
                $composer2.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState fingerprint$delegate = (MutableState) value$iv5;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13613796);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv6 = $composer2.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                value$iv6 = mutableStateOf$default;
                $composer2.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            final MutableState serial$delegate = (MutableState) value$iv6;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-13613742);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv7 = $composer2.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("Loading...", null, 2, null);
                $composer2.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            final MutableState statusText$delegate = (MutableState) value$iv7;
            $composer2.endReplaceableGroup();
            Unit unit = Unit.INSTANCE;
            $composer2.startReplaceableGroup(-13613674);
            ComposerKt.sourceInformation($composer2, "CC(remember):IdentityScreen.kt#9igjgp");
            Object it$iv8 = $composer2.rememberedValue();
            if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                value$iv8 = new IdentityScreenKt$IdentityScreen$1$1(manufacturer$delegate, model$delegate, brand$delegate, device$delegate, fingerprint$delegate, serial$delegate, statusText$delegate, null);
                $composer2.updateRememberedValue(value$iv8);
            } else {
                value$iv8 = it$iv8;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv8, $composer2, 70);
            FakeHalThemeKt.FakeHalTheme(ComposableLambdaKt.composableLambda($composer2, -375607777, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C44@1678L5161:IdentityScreen.kt#2o9c7b");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-375607777, $changed2, -1, "com.fakehal.controller.IdentityScreen.<anonymous> (IdentityScreen.kt:44)");
                        }
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, -389610781, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C55@2174L11,54@2098L122,46@1727L512:IdentityScreen.kt#2o9c7b");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-389610781, $changed3, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous> (IdentityScreen.kt:46)");
                                    }
                                    Function2<Composer, Integer, Unit> m6047getLambda1$app_debug = ComposableSingletons$IdentityScreenKt.INSTANCE.m6047getLambda1$app_debug();
                                    final Function0<Unit> function02 = function0;
                                    AppBarKt.TopAppBar(m6047getLambda1$app_debug, null, ComposableLambdaKt.composableLambda($composer4, -1909299363, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt.IdentityScreen.2.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C49@1881L122:IdentityScreen.kt#2o9c7b");
                                            if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1909299363, $changed4, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous>.<anonymous> (IdentityScreen.kt:49)");
                                                }
                                                IconButtonKt.IconButton(function02, null, false, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6048getLambda2$app_debug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }), null, null, TopAppBarDefaults.INSTANCE.m2288topAppBarColorszjMxDiM(MaterialTheme.INSTANCE.getColorScheme($composer4, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, 0L, $composer4, TopAppBarDefaults.$stable << 15, 30), null, $composer4, 390, 90);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        });
                        final MutableState<String> mutableState = manufacturer$delegate;
                        final MutableState<String> mutableState2 = model$delegate;
                        final MutableState<String> mutableState3 = brand$delegate;
                        final MutableState<String> mutableState4 = device$delegate;
                        final MutableState<String> mutableState5 = fingerprint$delegate;
                        final MutableState<String> mutableState6 = serial$delegate;
                        final CoroutineScope coroutineScope = scope;
                        final MutableState<String> mutableState7 = statusText$delegate;
                        ScaffoldKt.m1784ScaffoldTvnljyQ(null, composableLambda, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, -313400914, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                                invoke(paddingValues, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:31:0x01ee  */
                            /* JADX WARN: Removed duplicated region for block: B:34:0x0235  */
                            /* JADX WARN: Removed duplicated region for block: B:37:0x0276  */
                            /* JADX WARN: Removed duplicated region for block: B:40:0x02b8  */
                            /* JADX WARN: Removed duplicated region for block: B:43:0x02fa  */
                            /* JADX WARN: Removed duplicated region for block: B:46:0x033a  */
                            /* JADX WARN: Removed duplicated region for block: B:49:0x03d7  */
                            /* JADX WARN: Removed duplicated region for block: B:52:0x03e3  */
                            /* JADX WARN: Removed duplicated region for block: B:60:0x061c  */
                            /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
                            /* JADX WARN: Removed duplicated region for block: B:65:0x03e9  */
                            /* JADX WARN: Removed duplicated region for block: B:66:0x034a  */
                            /* JADX WARN: Removed duplicated region for block: B:67:0x030a  */
                            /* JADX WARN: Removed duplicated region for block: B:68:0x02c8  */
                            /* JADX WARN: Removed duplicated region for block: B:69:0x0286  */
                            /* JADX WARN: Removed duplicated region for block: B:70:0x0245  */
                            /* JADX WARN: Removed duplicated region for block: B:71:0x0200  */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                            */
                            public final void invoke(PaddingValues padding, Composer $composer4, int $changed3) {
                                Function0 factory$iv$iv$iv;
                                String IdentityScreen$lambda$1;
                                Object value$iv9;
                                String IdentityScreen$lambda$4;
                                Object it$iv9;
                                Object value$iv10;
                                String IdentityScreen$lambda$7;
                                Object value$iv11;
                                String IdentityScreen$lambda$10;
                                Object value$iv12;
                                String IdentityScreen$lambda$13;
                                Object value$iv13;
                                String IdentityScreen$lambda$16;
                                Object it$iv10;
                                Object value$iv14;
                                Function0 factory$iv$iv$iv2;
                                Composer $this$Layout_u24lambda_u240$iv$iv;
                                Intrinsics.checkNotNullParameter(padding, "padding");
                                ComposerKt.sourceInformation($composer4, "C66@2528L21,60@2290L4539:IdentityScreen.kt#2o9c7b");
                                int $dirty3 = $changed3;
                                if (($changed3 & 14) == 0) {
                                    $dirty3 |= $composer4.changed(padding) ? 4 : 2;
                                }
                                if (($dirty3 & 91) == 18 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-313400914, $dirty3, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous> (IdentityScreen.kt:60)");
                                }
                                Modifier modifier$iv = ScrollKt.verticalScroll$default(PaddingKt.m566paddingVpY3zN4$default(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding), Dp.m5734constructorimpl(12), 0.0f, 2, null), ScrollKt.rememberScrollState(0, $composer4, 0, 1), false, null, false, 14, null);
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(10));
                                final MutableState<String> mutableState8 = mutableState;
                                final MutableState<String> mutableState9 = mutableState2;
                                final MutableState<String> mutableState10 = mutableState3;
                                final MutableState<String> mutableState11 = mutableState4;
                                final MutableState<String> mutableState12 = mutableState5;
                                final MutableState<String> mutableState13 = mutableState6;
                                final CoroutineScope coroutineScope2 = coroutineScope;
                                final MutableState<String> mutableState14 = mutableState7;
                                $composer4.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                int $changed$iv$iv = (48 << 3) & 112;
                                $composer4.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv3 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
                                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                    factory$iv$iv$iv = factory$iv$iv$iv3;
                                    $composer4.createNode(factory$iv$iv$iv);
                                } else {
                                    factory$iv$iv$iv = factory$iv$iv$iv3;
                                    $composer4.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer4);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                    skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                    int i2 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 2043754527, "C69@2651L29,71@2742L21,71@2698L65,72@2810L14,72@2780L44,73@2871L14,73@2841L44,74@2934L15,74@2902L47,75@3008L20,75@2966L62,76@3077L15,76@3045L47,78@3110L3180,146@6401L46,144@6308L459,157@6785L30:IdentityScreen.kt#2o9c7b");
                                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer4, 6);
                                    IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(mutableState8);
                                    $composer4.startReplaceableGroup(2043754618);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    value$iv9 = $composer4.rememberedValue();
                                    if (value$iv9 != Composer.INSTANCE.getEmpty()) {
                                        value$iv9 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$1$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState8.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv9);
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField("Manufacturer", IdentityScreen$lambda$1, (Function1) value$iv9, $composer4, 390);
                                    IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(mutableState9);
                                    $composer4.startReplaceableGroup(2043754686);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    it$iv9 = $composer4.rememberedValue();
                                    if (it$iv9 != Composer.INSTANCE.getEmpty()) {
                                        value$iv10 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$2$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState9.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv10);
                                    } else {
                                        value$iv10 = it$iv9;
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField(ExifInterface.TAG_MODEL, IdentityScreen$lambda$4, (Function1) value$iv10, $composer4, 390);
                                    IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(mutableState10);
                                    $composer4.startReplaceableGroup(2043754747);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    value$iv11 = $composer4.rememberedValue();
                                    if (value$iv11 != Composer.INSTANCE.getEmpty()) {
                                        value$iv11 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$3$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState10.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv11);
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField("Brand", IdentityScreen$lambda$7, (Function1) value$iv11, $composer4, 390);
                                    IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(mutableState11);
                                    $composer4.startReplaceableGroup(2043754810);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    value$iv12 = $composer4.rememberedValue();
                                    if (value$iv12 != Composer.INSTANCE.getEmpty()) {
                                        value$iv12 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$4$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState11.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv12);
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField("Device", IdentityScreen$lambda$10, (Function1) value$iv12, $composer4, 390);
                                    IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(mutableState12);
                                    $composer4.startReplaceableGroup(2043754884);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    value$iv13 = $composer4.rememberedValue();
                                    if (value$iv13 != Composer.INSTANCE.getEmpty()) {
                                        value$iv13 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$5$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState12.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv13);
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField("Fingerprint", IdentityScreen$lambda$13, (Function1) value$iv13, $composer4, 390);
                                    IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(mutableState13);
                                    $composer4.startReplaceableGroup(2043754953);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                    it$iv10 = $composer4.rememberedValue();
                                    if (it$iv10 != Composer.INSTANCE.getEmpty()) {
                                        value$iv14 = (Function1) new Function1<String, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$6$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                                invoke2(str);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState13.setValue(it);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv14);
                                    } else {
                                        value$iv14 = it$iv10;
                                    }
                                    $composer4.endReplaceableGroup();
                                    IdentityScreenKt.IdentityField("Serial", IdentityScreen$lambda$16, (Function1) value$iv14, $composer4, 390);
                                    Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(8));
                                    $composer4.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                                    int $i$f$Row = ((54 >> 3) & 14) | ((54 >> 3) & 112);
                                    MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, $i$f$Row);
                                    int $changed$iv$iv2 = (54 << 3) & 112;
                                    $composer4.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                    CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                                    Function0 factory$iv$iv$iv4 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3 skippableUpdate$iv$iv$iv2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                    if (!($composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer4.startReusableNode();
                                    if ($composer4.getInserting()) {
                                        factory$iv$iv$iv2 = factory$iv$iv$iv4;
                                        $composer4.useNode();
                                    } else {
                                        factory$iv$iv$iv2 = factory$iv$iv$iv4;
                                        $composer4.createNode(factory$iv$iv$iv2);
                                    }
                                    $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer4);
                                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2 block$iv$iv$iv2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                        skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                        $composer4.startReplaceableGroup(2058660585);
                                        int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                        int i4 = ((54 >> 6) & 112) | 6;
                                        RowScope $this$invoke_u24lambda_u247_u24lambda_u246 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -1514293646, "C97@4181L48,79@3213L1289,105@4524L1033,127@5579L693:IdentityScreen.kt#2o9c7b");
                                        ButtonKt.Button(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1
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
                                            /* compiled from: IdentityScreen.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                            @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1", f = "IdentityScreen.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1, reason: invalid class name */
                                            /* loaded from: classes3.dex */
                                            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ MutableState<String> $brand$delegate;
                                                final /* synthetic */ MutableState<String> $device$delegate;
                                                final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                                final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                                final /* synthetic */ MutableState<String> $model$delegate;
                                                final /* synthetic */ MutableState<String> $serial$delegate;
                                                final /* synthetic */ MutableState<String> $statusText$delegate;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$statusText$delegate = mutableState;
                                                    this.$manufacturer$delegate = mutableState2;
                                                    this.$model$delegate = mutableState3;
                                                    this.$brand$delegate = mutableState4;
                                                    this.$device$delegate = mutableState5;
                                                    this.$fingerprint$delegate = mutableState6;
                                                    this.$serial$delegate = mutableState7;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) {
                                                    String IdentityScreen$lambda$1;
                                                    String IdentityScreen$lambda$4;
                                                    String IdentityScreen$lambda$7;
                                                    String IdentityScreen$lambda$10;
                                                    String IdentityScreen$lambda$13;
                                                    String IdentityScreen$lambda$16;
                                                    AnonymousClass1 anonymousClass1;
                                                    Object $result;
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure(obj);
                                                            this.$statusText$delegate.setValue("Saving...");
                                                            IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(this.$manufacturer$delegate);
                                                            IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(this.$model$delegate);
                                                            IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(this.$brand$delegate);
                                                            IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(this.$device$delegate);
                                                            IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(this.$fingerprint$delegate);
                                                            IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(this.$serial$delegate);
                                                            IdentityManager.DeviceProfile profile = new IdentityManager.DeviceProfile(IdentityScreen$lambda$1, IdentityScreen$lambda$4, IdentityScreen$lambda$7, IdentityScreen$lambda$10, null, IdentityScreen$lambda$13, null, IdentityScreen$lambda$16, null, null, 848, null);
                                                            this.label = 1;
                                                            Object apply = IdentityManager.INSTANCE.apply(profile, this);
                                                            if (apply != coroutine_suspended) {
                                                                anonymousClass1 = this;
                                                                $result = apply;
                                                                break;
                                                            } else {
                                                                return coroutine_suspended;
                                                            }
                                                        case 1:
                                                            anonymousClass1 = this;
                                                            $result = obj;
                                                            ResultKt.throwOnFailure($result);
                                                            break;
                                                        default:
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                    }
                                                    boolean ok = ((Boolean) $result).booleanValue();
                                                    anonymousClass1.$statusText$delegate.setValue(ok ? "Saved" : "Save failed");
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                            }
                                        }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4283215696L), 0L, 0L, 0L, $composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6049getLambda3$app_debug(), $composer4, 805306368, 484);
                                        ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2
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
                                            /* compiled from: IdentityScreen.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                            @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1", f = "IdentityScreen.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1, reason: invalid class name */
                                            /* loaded from: classes3.dex */
                                            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ MutableState<String> $brand$delegate;
                                                final /* synthetic */ MutableState<String> $device$delegate;
                                                final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                                final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                                final /* synthetic */ MutableState<String> $model$delegate;
                                                final /* synthetic */ MutableState<String> $serial$delegate;
                                                final /* synthetic */ MutableState<String> $statusText$delegate;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$statusText$delegate = mutableState;
                                                    this.$manufacturer$delegate = mutableState2;
                                                    this.$model$delegate = mutableState3;
                                                    this.$brand$delegate = mutableState4;
                                                    this.$device$delegate = mutableState5;
                                                    this.$fingerprint$delegate = mutableState6;
                                                    this.$serial$delegate = mutableState7;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    AnonymousClass1 anonymousClass1;
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            anonymousClass1 = this;
                                                            anonymousClass1.$statusText$delegate.setValue("Refreshing...");
                                                            anonymousClass1.label = 1;
                                                            Object readCurrent = IdentityManager.INSTANCE.readCurrent(anonymousClass1);
                                                            if (readCurrent != coroutine_suspended) {
                                                                $result = readCurrent;
                                                                break;
                                                            } else {
                                                                return coroutine_suspended;
                                                            }
                                                        case 1:
                                                            ResultKt.throwOnFailure($result);
                                                            anonymousClass1 = this;
                                                            break;
                                                        default:
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                    }
                                                    IdentityManager.DeviceProfile current = (IdentityManager.DeviceProfile) $result;
                                                    anonymousClass1.$manufacturer$delegate.setValue(current.getManufacturer());
                                                    anonymousClass1.$model$delegate.setValue(current.getModel());
                                                    anonymousClass1.$brand$delegate.setValue(current.getBrand());
                                                    anonymousClass1.$device$delegate.setValue(current.getDevice());
                                                    anonymousClass1.$fingerprint$delegate.setValue(current.getFingerprint());
                                                    anonymousClass1.$serial$delegate.setValue(current.getSerial());
                                                    anonymousClass1.$statusText$delegate.setValue("Refreshed");
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                            }
                                        }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6050getLambda4$app_debug(), $composer4, 805306368, 500);
                                        ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3
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
                                            /* compiled from: IdentityScreen.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                            @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1", f = "IdentityScreen.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1, reason: invalid class name */
                                            /* loaded from: classes3.dex */
                                            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ MutableState<String> $statusText$delegate;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(MutableState<String> mutableState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$statusText$delegate = mutableState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$statusText$delegate, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    AnonymousClass1 anonymousClass1;
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            anonymousClass1 = this;
                                                            anonymousClass1.$statusText$delegate.setValue("Resetting...");
                                                            anonymousClass1.label = 1;
                                                            Object reset = IdentityManager.INSTANCE.reset(anonymousClass1);
                                                            if (reset != coroutine_suspended) {
                                                                $result = reset;
                                                                break;
                                                            } else {
                                                                return coroutine_suspended;
                                                            }
                                                        case 1:
                                                            ResultKt.throwOnFailure($result);
                                                            anonymousClass1 = this;
                                                            break;
                                                        default:
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                    }
                                                    boolean ok = ((Boolean) $result).booleanValue();
                                                    anonymousClass1.$statusText$delegate.setValue(ok ? "Reset done" : "Reset failed");
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, null), 3, null);
                                            }
                                        }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6051getLambda5$app_debug(), $composer4, 805306368, 500);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        $composer4.endReplaceableGroup();
                                        $composer4.endNode();
                                        $composer4.endReplaceableGroup();
                                        $composer4.endReplaceableGroup();
                                        CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(10)), CardDefaults.INSTANCE.m1291cardColorsro_MJ88(ColorKt.Color(4280163898L), 0L, 0L, 0L, $composer4, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.composableLambda($composer4, 1655225542, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$8
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                                invoke(columnScope, composer, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(ColumnScope Card, Composer $composer5, int $changed4) {
                                                String IdentityScreen$lambda$19;
                                                Intrinsics.checkNotNullParameter(Card, "$this$Card");
                                                ComposerKt.sourceInformation($composer5, "C149@6544L205:IdentityScreen.kt#2o9c7b");
                                                if (($changed4 & 81) != 16 || !$composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1655225542, $changed4, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (IdentityScreen.kt:149)");
                                                    }
                                                    IdentityScreen$lambda$19 = IdentityScreenKt.IdentityScreen$lambda$19(mutableState14);
                                                    TextKt.m2129Text4IGK_g(IdentityScreen$lambda$19, PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(10)), ColorKt.Color(4287137962L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 3504, 0, 131056);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer5.skipToGroupEnd();
                                            }
                                        }), $composer4, 196614, 24);
                                        SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(20)), $composer4, 6);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
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
                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv2), block$iv$iv$iv2);
                                    skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i32 = ($changed$iv$iv$iv2 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                    int i42 = ((54 >> 6) & 112) | 6;
                                    RowScope $this$invoke_u24lambda_u247_u24lambda_u2462 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1514293646, "C97@4181L48,79@3213L1289,105@4524L1033,127@5579L693:IdentityScreen.kt#2o9c7b");
                                    ButtonKt.Button(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1", f = "IdentityScreen.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $brand$delegate;
                                            final /* synthetic */ MutableState<String> $device$delegate;
                                            final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                            final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                            final /* synthetic */ MutableState<String> $model$delegate;
                                            final /* synthetic */ MutableState<String> $serial$delegate;
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                                this.$manufacturer$delegate = mutableState2;
                                                this.$model$delegate = mutableState3;
                                                this.$brand$delegate = mutableState4;
                                                this.$device$delegate = mutableState5;
                                                this.$fingerprint$delegate = mutableState6;
                                                this.$serial$delegate = mutableState7;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object obj) {
                                                String IdentityScreen$lambda$1;
                                                String IdentityScreen$lambda$4;
                                                String IdentityScreen$lambda$7;
                                                String IdentityScreen$lambda$10;
                                                String IdentityScreen$lambda$13;
                                                String IdentityScreen$lambda$16;
                                                AnonymousClass1 anonymousClass1;
                                                Object $result;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure(obj);
                                                        this.$statusText$delegate.setValue("Saving...");
                                                        IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(this.$manufacturer$delegate);
                                                        IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(this.$model$delegate);
                                                        IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(this.$brand$delegate);
                                                        IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(this.$device$delegate);
                                                        IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(this.$fingerprint$delegate);
                                                        IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(this.$serial$delegate);
                                                        IdentityManager.DeviceProfile profile = new IdentityManager.DeviceProfile(IdentityScreen$lambda$1, IdentityScreen$lambda$4, IdentityScreen$lambda$7, IdentityScreen$lambda$10, null, IdentityScreen$lambda$13, null, IdentityScreen$lambda$16, null, null, 848, null);
                                                        this.label = 1;
                                                        Object apply = IdentityManager.INSTANCE.apply(profile, this);
                                                        if (apply != coroutine_suspended) {
                                                            anonymousClass1 = this;
                                                            $result = apply;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        anonymousClass1 = this;
                                                        $result = obj;
                                                        ResultKt.throwOnFailure($result);
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                boolean ok = ((Boolean) $result).booleanValue();
                                                anonymousClass1.$statusText$delegate.setValue(ok ? "Saved" : "Save failed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u2462, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4283215696L), 0L, 0L, 0L, $composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6049getLambda3$app_debug(), $composer4, 805306368, 484);
                                    ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1", f = "IdentityScreen.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $brand$delegate;
                                            final /* synthetic */ MutableState<String> $device$delegate;
                                            final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                            final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                            final /* synthetic */ MutableState<String> $model$delegate;
                                            final /* synthetic */ MutableState<String> $serial$delegate;
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                                this.$manufacturer$delegate = mutableState2;
                                                this.$model$delegate = mutableState3;
                                                this.$brand$delegate = mutableState4;
                                                this.$device$delegate = mutableState5;
                                                this.$fingerprint$delegate = mutableState6;
                                                this.$serial$delegate = mutableState7;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                AnonymousClass1 anonymousClass1;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        anonymousClass1.$statusText$delegate.setValue("Refreshing...");
                                                        anonymousClass1.label = 1;
                                                        Object readCurrent = IdentityManager.INSTANCE.readCurrent(anonymousClass1);
                                                        if (readCurrent != coroutine_suspended) {
                                                            $result = readCurrent;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                IdentityManager.DeviceProfile current = (IdentityManager.DeviceProfile) $result;
                                                anonymousClass1.$manufacturer$delegate.setValue(current.getManufacturer());
                                                anonymousClass1.$model$delegate.setValue(current.getModel());
                                                anonymousClass1.$brand$delegate.setValue(current.getBrand());
                                                anonymousClass1.$device$delegate.setValue(current.getDevice());
                                                anonymousClass1.$fingerprint$delegate.setValue(current.getFingerprint());
                                                anonymousClass1.$serial$delegate.setValue(current.getSerial());
                                                anonymousClass1.$statusText$delegate.setValue("Refreshed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u2462, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6050getLambda4$app_debug(), $composer4, 805306368, 500);
                                    ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1", f = "IdentityScreen.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                AnonymousClass1 anonymousClass1;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        anonymousClass1.$statusText$delegate.setValue("Resetting...");
                                                        anonymousClass1.label = 1;
                                                        Object reset = IdentityManager.INSTANCE.reset(anonymousClass1);
                                                        if (reset != coroutine_suspended) {
                                                            $result = reset;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                boolean ok = ((Boolean) $result).booleanValue();
                                                anonymousClass1.$statusText$delegate.setValue(ok ? "Reset done" : "Reset failed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u2462, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6051getLambda5$app_debug(), $composer4, 805306368, 500);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(10)), CardDefaults.INSTANCE.m1291cardColorsro_MJ88(ColorKt.Color(4280163898L), 0L, 0L, 0L, $composer4, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.composableLambda($composer4, 1655225542, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$8
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                            invoke(columnScope, composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(ColumnScope Card, Composer $composer5, int $changed4) {
                                            String IdentityScreen$lambda$19;
                                            Intrinsics.checkNotNullParameter(Card, "$this$Card");
                                            ComposerKt.sourceInformation($composer5, "C149@6544L205:IdentityScreen.kt#2o9c7b");
                                            if (($changed4 & 81) != 16 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1655225542, $changed4, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (IdentityScreen.kt:149)");
                                                }
                                                IdentityScreen$lambda$19 = IdentityScreenKt.IdentityScreen$lambda$19(mutableState14);
                                                TextKt.m2129Text4IGK_g(IdentityScreen$lambda$19, PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(10)), ColorKt.Color(4287137962L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 3504, 0, 131056);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }), $composer4, 196614, 24);
                                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(20)), $composer4, 6);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                }
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                                skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i5 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                int i22 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, 2043754527, "C69@2651L29,71@2742L21,71@2698L65,72@2810L14,72@2780L44,73@2871L14,73@2841L44,74@2934L15,74@2902L47,75@3008L20,75@2966L62,76@3077L15,76@3045L47,78@3110L3180,146@6401L46,144@6308L459,157@6785L30:IdentityScreen.kt#2o9c7b");
                                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(4)), $composer4, 6);
                                IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(mutableState8);
                                $composer4.startReplaceableGroup(2043754618);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                value$iv9 = $composer4.rememberedValue();
                                if (value$iv9 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField("Manufacturer", IdentityScreen$lambda$1, (Function1) value$iv9, $composer4, 390);
                                IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(mutableState9);
                                $composer4.startReplaceableGroup(2043754686);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                it$iv9 = $composer4.rememberedValue();
                                if (it$iv9 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField(ExifInterface.TAG_MODEL, IdentityScreen$lambda$4, (Function1) value$iv10, $composer4, 390);
                                IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(mutableState10);
                                $composer4.startReplaceableGroup(2043754747);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                value$iv11 = $composer4.rememberedValue();
                                if (value$iv11 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField("Brand", IdentityScreen$lambda$7, (Function1) value$iv11, $composer4, 390);
                                IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(mutableState11);
                                $composer4.startReplaceableGroup(2043754810);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                value$iv12 = $composer4.rememberedValue();
                                if (value$iv12 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField("Device", IdentityScreen$lambda$10, (Function1) value$iv12, $composer4, 390);
                                IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(mutableState12);
                                $composer4.startReplaceableGroup(2043754884);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                value$iv13 = $composer4.rememberedValue();
                                if (value$iv13 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField("Fingerprint", IdentityScreen$lambda$13, (Function1) value$iv13, $composer4, 390);
                                IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(mutableState13);
                                $composer4.startReplaceableGroup(2043754953);
                                ComposerKt.sourceInformation($composer4, "CC(remember):IdentityScreen.kt#9igjgp");
                                it$iv10 = $composer4.rememberedValue();
                                if (it$iv10 != Composer.INSTANCE.getEmpty()) {
                                }
                                $composer4.endReplaceableGroup();
                                IdentityScreenKt.IdentityField("Serial", IdentityScreen$lambda$16, (Function1) value$iv14, $composer4, 390);
                                Modifier modifier$iv22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(8));
                                $composer4.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getTop();
                                int $i$f$Row2 = ((54 >> 3) & 14) | ((54 >> 3) & 112);
                                MeasurePolicy measurePolicy$iv22 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer4, $i$f$Row2);
                                int $changed$iv$iv22 = (54 << 3) & 112;
                                $composer4.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv22 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv22 = $composer4.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv42 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv22 = LayoutKt.modifierMaterializerOf(modifier$iv22);
                                int $changed$iv$iv$iv22 = (($changed$iv$iv22 << 9) & 7168) | 6;
                                if (!($composer4.getApplier() instanceof Applier)) {
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                }
                                $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer4);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                                    skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i322 = ($changed$iv$iv$iv22 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                    int i422 = ((54 >> 6) & 112) | 6;
                                    RowScope $this$invoke_u24lambda_u247_u24lambda_u24622 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1514293646, "C97@4181L48,79@3213L1289,105@4524L1033,127@5579L693:IdentityScreen.kt#2o9c7b");
                                    ButtonKt.Button(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1", f = "IdentityScreen.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $brand$delegate;
                                            final /* synthetic */ MutableState<String> $device$delegate;
                                            final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                            final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                            final /* synthetic */ MutableState<String> $model$delegate;
                                            final /* synthetic */ MutableState<String> $serial$delegate;
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                                this.$manufacturer$delegate = mutableState2;
                                                this.$model$delegate = mutableState3;
                                                this.$brand$delegate = mutableState4;
                                                this.$device$delegate = mutableState5;
                                                this.$fingerprint$delegate = mutableState6;
                                                this.$serial$delegate = mutableState7;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object obj) {
                                                String IdentityScreen$lambda$1;
                                                String IdentityScreen$lambda$4;
                                                String IdentityScreen$lambda$7;
                                                String IdentityScreen$lambda$10;
                                                String IdentityScreen$lambda$13;
                                                String IdentityScreen$lambda$16;
                                                AnonymousClass1 anonymousClass1;
                                                Object $result;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure(obj);
                                                        this.$statusText$delegate.setValue("Saving...");
                                                        IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(this.$manufacturer$delegate);
                                                        IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(this.$model$delegate);
                                                        IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(this.$brand$delegate);
                                                        IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(this.$device$delegate);
                                                        IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(this.$fingerprint$delegate);
                                                        IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(this.$serial$delegate);
                                                        IdentityManager.DeviceProfile profile = new IdentityManager.DeviceProfile(IdentityScreen$lambda$1, IdentityScreen$lambda$4, IdentityScreen$lambda$7, IdentityScreen$lambda$10, null, IdentityScreen$lambda$13, null, IdentityScreen$lambda$16, null, null, 848, null);
                                                        this.label = 1;
                                                        Object apply = IdentityManager.INSTANCE.apply(profile, this);
                                                        if (apply != coroutine_suspended) {
                                                            anonymousClass1 = this;
                                                            $result = apply;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        anonymousClass1 = this;
                                                        $result = obj;
                                                        ResultKt.throwOnFailure($result);
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                boolean ok = ((Boolean) $result).booleanValue();
                                                anonymousClass1.$statusText$delegate.setValue(ok ? "Saved" : "Save failed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u24622, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4283215696L), 0L, 0L, 0L, $composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6049getLambda3$app_debug(), $composer4, 805306368, 484);
                                    ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1", f = "IdentityScreen.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $brand$delegate;
                                            final /* synthetic */ MutableState<String> $device$delegate;
                                            final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                            final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                            final /* synthetic */ MutableState<String> $model$delegate;
                                            final /* synthetic */ MutableState<String> $serial$delegate;
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                                this.$manufacturer$delegate = mutableState2;
                                                this.$model$delegate = mutableState3;
                                                this.$brand$delegate = mutableState4;
                                                this.$device$delegate = mutableState5;
                                                this.$fingerprint$delegate = mutableState6;
                                                this.$serial$delegate = mutableState7;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                AnonymousClass1 anonymousClass1;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        anonymousClass1.$statusText$delegate.setValue("Refreshing...");
                                                        anonymousClass1.label = 1;
                                                        Object readCurrent = IdentityManager.INSTANCE.readCurrent(anonymousClass1);
                                                        if (readCurrent != coroutine_suspended) {
                                                            $result = readCurrent;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                IdentityManager.DeviceProfile current = (IdentityManager.DeviceProfile) $result;
                                                anonymousClass1.$manufacturer$delegate.setValue(current.getManufacturer());
                                                anonymousClass1.$model$delegate.setValue(current.getModel());
                                                anonymousClass1.$brand$delegate.setValue(current.getBrand());
                                                anonymousClass1.$device$delegate.setValue(current.getDevice());
                                                anonymousClass1.$fingerprint$delegate.setValue(current.getFingerprint());
                                                anonymousClass1.$serial$delegate.setValue(current.getSerial());
                                                anonymousClass1.$statusText$delegate.setValue("Refreshed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u24622, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6050getLambda4$app_debug(), $composer4, 805306368, 500);
                                    ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3
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
                                        /* compiled from: IdentityScreen.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                        @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1", f = "IdentityScreen.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1, reason: invalid class name */
                                        /* loaded from: classes3.dex */
                                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ MutableState<String> $statusText$delegate;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(MutableState<String> mutableState, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$statusText$delegate = mutableState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$statusText$delegate, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                AnonymousClass1 anonymousClass1;
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        anonymousClass1.$statusText$delegate.setValue("Resetting...");
                                                        anonymousClass1.label = 1;
                                                        Object reset = IdentityManager.INSTANCE.reset(anonymousClass1);
                                                        if (reset != coroutine_suspended) {
                                                            $result = reset;
                                                            break;
                                                        } else {
                                                            return coroutine_suspended;
                                                        }
                                                    case 1:
                                                        ResultKt.throwOnFailure($result);
                                                        anonymousClass1 = this;
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                boolean ok = ((Boolean) $result).booleanValue();
                                                anonymousClass1.$statusText$delegate.setValue(ok ? "Reset done" : "Reset failed");
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, null), 3, null);
                                        }
                                    }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u24622, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6051getLambda5$app_debug(), $composer4, 805306368, 500);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(10)), CardDefaults.INSTANCE.m1291cardColorsro_MJ88(ColorKt.Color(4280163898L), 0L, 0L, 0L, $composer4, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.composableLambda($composer4, 1655225542, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$8
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                            invoke(columnScope, composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(ColumnScope Card, Composer $composer5, int $changed4) {
                                            String IdentityScreen$lambda$19;
                                            Intrinsics.checkNotNullParameter(Card, "$this$Card");
                                            ComposerKt.sourceInformation($composer5, "C149@6544L205:IdentityScreen.kt#2o9c7b");
                                            if (($changed4 & 81) != 16 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1655225542, $changed4, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (IdentityScreen.kt:149)");
                                                }
                                                IdentityScreen$lambda$19 = IdentityScreenKt.IdentityScreen$lambda$19(mutableState14);
                                                TextKt.m2129Text4IGK_g(IdentityScreen$lambda$19, PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(10)), ColorKt.Color(4287137962L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 3504, 0, 131056);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }), $composer4, 196614, 24);
                                    SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(20)), $composer4, 6);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                }
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv22));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv22), block$iv$iv$iv22);
                                skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i3222 = ($changed$iv$iv$iv22 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                int i4222 = ((54 >> 6) & 112) | 6;
                                RowScope $this$invoke_u24lambda_u247_u24lambda_u246222 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1514293646, "C97@4181L48,79@3213L1289,105@4524L1033,127@5579L693:IdentityScreen.kt#2o9c7b");
                                ButtonKt.Button(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1
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
                                    /* compiled from: IdentityScreen.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1", f = "IdentityScreen.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
                                    /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$1$1, reason: invalid class name */
                                    /* loaded from: classes3.dex */
                                    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ MutableState<String> $brand$delegate;
                                        final /* synthetic */ MutableState<String> $device$delegate;
                                        final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                        final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                        final /* synthetic */ MutableState<String> $model$delegate;
                                        final /* synthetic */ MutableState<String> $serial$delegate;
                                        final /* synthetic */ MutableState<String> $statusText$delegate;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$statusText$delegate = mutableState;
                                            this.$manufacturer$delegate = mutableState2;
                                            this.$model$delegate = mutableState3;
                                            this.$brand$delegate = mutableState4;
                                            this.$device$delegate = mutableState5;
                                            this.$fingerprint$delegate = mutableState6;
                                            this.$serial$delegate = mutableState7;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            String IdentityScreen$lambda$1;
                                            String IdentityScreen$lambda$4;
                                            String IdentityScreen$lambda$7;
                                            String IdentityScreen$lambda$10;
                                            String IdentityScreen$lambda$13;
                                            String IdentityScreen$lambda$16;
                                            AnonymousClass1 anonymousClass1;
                                            Object $result;
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure(obj);
                                                    this.$statusText$delegate.setValue("Saving...");
                                                    IdentityScreen$lambda$1 = IdentityScreenKt.IdentityScreen$lambda$1(this.$manufacturer$delegate);
                                                    IdentityScreen$lambda$4 = IdentityScreenKt.IdentityScreen$lambda$4(this.$model$delegate);
                                                    IdentityScreen$lambda$7 = IdentityScreenKt.IdentityScreen$lambda$7(this.$brand$delegate);
                                                    IdentityScreen$lambda$10 = IdentityScreenKt.IdentityScreen$lambda$10(this.$device$delegate);
                                                    IdentityScreen$lambda$13 = IdentityScreenKt.IdentityScreen$lambda$13(this.$fingerprint$delegate);
                                                    IdentityScreen$lambda$16 = IdentityScreenKt.IdentityScreen$lambda$16(this.$serial$delegate);
                                                    IdentityManager.DeviceProfile profile = new IdentityManager.DeviceProfile(IdentityScreen$lambda$1, IdentityScreen$lambda$4, IdentityScreen$lambda$7, IdentityScreen$lambda$10, null, IdentityScreen$lambda$13, null, IdentityScreen$lambda$16, null, null, 848, null);
                                                    this.label = 1;
                                                    Object apply = IdentityManager.INSTANCE.apply(profile, this);
                                                    if (apply != coroutine_suspended) {
                                                        anonymousClass1 = this;
                                                        $result = apply;
                                                        break;
                                                    } else {
                                                        return coroutine_suspended;
                                                    }
                                                case 1:
                                                    anonymousClass1 = this;
                                                    $result = obj;
                                                    ResultKt.throwOnFailure($result);
                                                    break;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            boolean ok = ((Boolean) $result).booleanValue();
                                            anonymousClass1.$statusText$delegate.setValue(ok ? "Saved" : "Save failed");
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                    }
                                }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246222, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), ButtonDefaults.INSTANCE.m1270buttonColorsro_MJ88(ColorKt.Color(4283215696L), 0L, 0L, 0L, $composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6049getLambda3$app_debug(), $composer4, 805306368, 484);
                                ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2
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
                                    /* compiled from: IdentityScreen.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1", f = "IdentityScreen.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
                                    /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$2$1, reason: invalid class name */
                                    /* loaded from: classes3.dex */
                                    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ MutableState<String> $brand$delegate;
                                        final /* synthetic */ MutableState<String> $device$delegate;
                                        final /* synthetic */ MutableState<String> $fingerprint$delegate;
                                        final /* synthetic */ MutableState<String> $manufacturer$delegate;
                                        final /* synthetic */ MutableState<String> $model$delegate;
                                        final /* synthetic */ MutableState<String> $serial$delegate;
                                        final /* synthetic */ MutableState<String> $statusText$delegate;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$statusText$delegate = mutableState;
                                            this.$manufacturer$delegate = mutableState2;
                                            this.$model$delegate = mutableState3;
                                            this.$brand$delegate = mutableState4;
                                            this.$device$delegate = mutableState5;
                                            this.$fingerprint$delegate = mutableState6;
                                            this.$serial$delegate = mutableState7;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$statusText$delegate, this.$manufacturer$delegate, this.$model$delegate, this.$brand$delegate, this.$device$delegate, this.$fingerprint$delegate, this.$serial$delegate, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            AnonymousClass1 anonymousClass1;
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    anonymousClass1 = this;
                                                    anonymousClass1.$statusText$delegate.setValue("Refreshing...");
                                                    anonymousClass1.label = 1;
                                                    Object readCurrent = IdentityManager.INSTANCE.readCurrent(anonymousClass1);
                                                    if (readCurrent != coroutine_suspended) {
                                                        $result = readCurrent;
                                                        break;
                                                    } else {
                                                        return coroutine_suspended;
                                                    }
                                                case 1:
                                                    ResultKt.throwOnFailure($result);
                                                    anonymousClass1 = this;
                                                    break;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            IdentityManager.DeviceProfile current = (IdentityManager.DeviceProfile) $result;
                                            anonymousClass1.$manufacturer$delegate.setValue(current.getManufacturer());
                                            anonymousClass1.$model$delegate.setValue(current.getModel());
                                            anonymousClass1.$brand$delegate.setValue(current.getBrand());
                                            anonymousClass1.$device$delegate.setValue(current.getDevice());
                                            anonymousClass1.$fingerprint$delegate.setValue(current.getFingerprint());
                                            anonymousClass1.$serial$delegate.setValue(current.getSerial());
                                            anonymousClass1.$statusText$delegate.setValue("Refreshed");
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, null), 3, null);
                                    }
                                }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246222, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6050getLambda4$app_debug(), $composer4, 805306368, 500);
                                ButtonKt.OutlinedButton(new Function0<Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3
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
                                    /* compiled from: IdentityScreen.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1", f = "IdentityScreen.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
                                    /* renamed from: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$7$3$1, reason: invalid class name */
                                    /* loaded from: classes3.dex */
                                    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ MutableState<String> $statusText$delegate;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(MutableState<String> mutableState, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$statusText$delegate = mutableState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$statusText$delegate, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            AnonymousClass1 anonymousClass1;
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    anonymousClass1 = this;
                                                    anonymousClass1.$statusText$delegate.setValue("Resetting...");
                                                    anonymousClass1.label = 1;
                                                    Object reset = IdentityManager.INSTANCE.reset(anonymousClass1);
                                                    if (reset != coroutine_suspended) {
                                                        $result = reset;
                                                        break;
                                                    } else {
                                                        return coroutine_suspended;
                                                    }
                                                case 1:
                                                    ResultKt.throwOnFailure($result);
                                                    anonymousClass1 = this;
                                                    break;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            boolean ok = ((Boolean) $result).booleanValue();
                                            anonymousClass1.$statusText$delegate.setValue(ok ? "Reset done" : "Reset failed");
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState14, null), 3, null);
                                    }
                                }, RowScope.weight$default($this$invoke_u24lambda_u247_u24lambda_u246222, Modifier.INSTANCE, 1.0f, false, 2, null), false, RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12)), null, null, null, null, null, ComposableSingletons$IdentityScreenKt.INSTANCE.m6051getLambda5$app_debug(), $composer4, 805306368, 500);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(10)), CardDefaults.INSTANCE.m1291cardColorsro_MJ88(ColorKt.Color(4280163898L), 0L, 0L, 0L, $composer4, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.composableLambda($composer4, 1655225542, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$2$2$1$8
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                        invoke(columnScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(ColumnScope Card, Composer $composer5, int $changed4) {
                                        String IdentityScreen$lambda$19;
                                        Intrinsics.checkNotNullParameter(Card, "$this$Card");
                                        ComposerKt.sourceInformation($composer5, "C149@6544L205:IdentityScreen.kt#2o9c7b");
                                        if (($changed4 & 81) != 16 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1655225542, $changed4, -1, "com.fakehal.controller.IdentityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (IdentityScreen.kt:149)");
                                            }
                                            IdentityScreen$lambda$19 = IdentityScreenKt.IdentityScreen$lambda$19(mutableState14);
                                            TextKt.m2129Text4IGK_g(IdentityScreen$lambda$19, PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(10)), ColorKt.Color(4287137962L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 3504, 0, 131056);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }), $composer4, 196614, 24);
                                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(20)), $composer4, 6);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                            }
                        }), $composer3, 805306416, 509);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityScreen$3
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
                    IdentityScreenKt.IdentityScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$1(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$4(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$7(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$10(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$13(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$16(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IdentityScreen$lambda$19(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void IdentityField(final String label, final String value, final Function1<? super String, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1178736400);
        ComposerKt.sourceInformation($composer3, "C(IdentityField)P(!1,2)176@7211L239,169@6971L486:IdentityScreen.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1178736400, $dirty2, -1, "com.fakehal.controller.IdentityField (IdentityScreen.kt:168)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldKt.OutlinedTextField(value, function1, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 1270545814, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityField$1
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
                    ComposerKt.sourceInformation($composer4, "C172@7070L11:IdentityScreen.kt#2o9c7b");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1270545814, $changed2, -1, "com.fakehal.controller.IdentityField.<anonymous> (IdentityScreen.kt:172)");
                    }
                    TextKt.m2129Text4IGK_g(label, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, OutlinedTextFieldDefaults.INSTANCE.m1731colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, null, ColorKt.Color(4286336511L), ColorKt.Color(4282006108L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4286336511L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 432, 3072, 0, 3072, 2139088639, 4095), $composer2, (($dirty2 >> 3) & 14) | 1573248 | (($dirty2 >> 3) & 112), 12582912, 0, 4063160);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.IdentityScreenKt$IdentityField$2
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

                public final void invoke(Composer composer, int i) {
                    IdentityScreenKt.IdentityField(label, value, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}

package com.fakehal.controller;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CancelKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
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
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
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
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SecurityScreen.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0007¢\u0006\u0002\u0010\u000e\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000f"}, d2 = {"SECURITY_CHECKS", "", "Lcom/fakehal/controller/SecurityCheck;", "getSECURITY_CHECKS", "()Ljava/util/List;", "SecurityCheckRow", "", "check", "result", "", "(Lcom/fakehal/controller/SecurityCheck;Ljava/lang/Boolean;Landroidx/compose/runtime/Composer;I)V", "SecurityScreen", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_debug"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SecurityScreenKt {
    private static final List<SecurityCheck> SECURITY_CHECKS = CollectionsKt.listOf((Object[]) new SecurityCheck[]{new SecurityCheck("basic_integrity", "BASIC Integrity", "Play Integrity API базовый уровень", "integrity", "getprop ro.boot.verifiedbootstate", "green", false, 64, null), new SecurityCheck("boot_locked", "Boot Locked", "Загрузчик заблокирован", "integrity", "getprop ro.boot.flash.locked", "1", false, 64, null), new SecurityCheck("release_keys", "Release Keys", "Подпись release-keys (не test-keys)", "integrity", "getprop ro.build.tags", "release-keys", false, 64, null), new SecurityCheck("build_type", "Build Type: user", "ro.build.type должен быть user", "props", "getprop ro.build.type", "user", false, 64, null), new SecurityCheck("debuggable", "Not Debuggable", "ro.debuggable = 0", "props", "getprop ro.debuggable", "0", false, 64, null), new SecurityCheck("secure", "Secure = 1", "ro.secure = 1", "props", "getprop ro.secure", "1", false, 64, null), new SecurityCheck("adb_root", "ADB Root Off", "service.adb.root = 0", "props", "getprop service.adb.root", "1", true), new SecurityCheck("selinux", "SELinux Enforcing", "SELinux в режиме Enforcing", "props", "getenforce", "Enforcing", false, 64, null), new SecurityCheck("no_magisk_sbin", "No Magisk /sbin", "Magisk не в /sbin", "files", "ls /sbin/magisk 2>/dev/null && echo FOUND || echo NOT_FOUND", "NOT_FOUND", false, 64, null), new SecurityCheck("no_magisk_db", "No magisk.db", "База данных Magisk отсутствует", "files", "ls /data/magisk.db 2>/dev/null && echo FOUND || echo NOT_FOUND", "NOT_FOUND", false, 64, null), new SecurityCheck("no_xposed_jar", "No XposedBridge", "XposedBridge.jar не в /system", "files", "ls /system/framework/XposedBridge.jar 2>/dev/null && echo FOUND || echo NOT_FOUND", "NOT_FOUND", false, 64, null), new SecurityCheck("su_hidden", "su Hidden (SUSFS)", "SUSFS скрывает /system/bin/su от приложений", "susfs", "ls /data/adb/susfs4ksu/sus_path_loop.txt 2>/dev/null | xargs grep -l system/bin/su 2>/dev/null && echo HIDDEN || echo NOT_HIDDEN", "HIDDEN", false, 64, null), new SecurityCheck("susfs_active", "SUSFS Active", "Модуль SUSFS4KSU включён", "susfs", "ls /data/adb/modules/susfs4ksu/disable 2>/dev/null && echo DISABLED || echo ACTIVE", "ACTIVE", false, 64, null), new SecurityCheck("playintegrity_mod", "PlayIntegrityFix", "Модуль PlayIntegrityFix включён", "susfs", "ls /data/adb/modules/playintegrityfix/disable 2>/dev/null && echo DISABLED || echo ACTIVE", "ACTIVE", false, 64, null), new SecurityCheck("trickystore", "TrickyStore/TEE", "TEESimulator-RS keystore активен", "susfs", "ls /data/adb/modules/tricky_store/disable 2>/dev/null && echo DISABLED || echo ACTIVE", "ACTIVE", false, 64, null)});

    public static final List<SecurityCheck> getSECURITY_CHECKS() {
        return SECURITY_CHECKS;
    }

    public static final void SecurityScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv$iv$iv;
        int count$iv;
        int count$iv2;
        boolean z;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(2028887587);
        ComposerKt.sourceInformation($composer3, "C(SecurityScreen)167@5320L50,168@5391L34,169@5442L24,198@6402L36,206@6686L4553:SecurityScreen.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 11) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2028887587, $dirty2, -1, "com.fakehal.controller.SecurityScreen (SecurityScreen.kt:165)");
            }
            $composer3.startReplaceableGroup(-1834198937);
            ComposerKt.sourceInformation($composer3, "CC(remember):SecurityScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.mutableStateMapOf();
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final SnapshotStateMap results = (SnapshotStateMap) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-1834198866);
            ComposerKt.sourceInformation($composer3, "CC(remember):SecurityScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState isRunning = (MutableState) value$iv2;
            $composer3.endReplaceableGroup();
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
            EffectsKt.LaunchedEffect(Unit.INSTANCE, new SecurityScreenKt$SecurityScreen$1(scope, isRunning, results, null), $composer3, 70);
            Iterable $this$count$iv = results.values();
            if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
                count$iv = 0;
            } else {
                count$iv = 0;
                for (Object element$iv : $this$count$iv) {
                    Boolean it = (Boolean) element$iv;
                    if (Intrinsics.areEqual((Object) it, (Object) true) && (count$iv = count$iv + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            final int passCount = count$iv;
            Iterable $this$count$iv2 = results.values();
            if (($this$count$iv2 instanceof Collection) && ((Collection) $this$count$iv2).isEmpty()) {
                count$iv2 = 0;
                z = false;
            } else {
                count$iv2 = 0;
                for (Object element$iv2 : $this$count$iv2) {
                    Boolean it2 = (Boolean) element$iv2;
                    if (Intrinsics.areEqual((Object) it2, (Object) false)) {
                        count$iv2++;
                        if (count$iv2 < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                z = false;
            }
            int failCount = count$iv2;
            final int totalCount = SECURITY_CHECKS.size();
            final boolean allPass = (failCount == 0 && passCount == totalCount) ? true : z;
            $composer2 = $composer3;
            ScaffoldKt.m1784ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, 22981095, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$2
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
                    ComposerKt.sourceInformation($composer4, "C224@7653L51,208@6727L992:SecurityScreen.kt#2o9c7b");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(22981095, $changed2, -1, "com.fakehal.controller.SecurityScreen.<anonymous> (SecurityScreen.kt:208)");
                        }
                        Function2<Composer, Integer, Unit> m6075getLambda1$app_debug = ComposableSingletons$SecurityScreenKt.INSTANCE.m6075getLambda1$app_debug();
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, -650611743, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$2.1
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
                                ComposerKt.sourceInformation($composer5, "C211@6918L161:SecurityScreen.kt#2o9c7b");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-650611743, $changed3, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous> (SecurityScreen.kt:211)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$SecurityScreenKt.INSTANCE.m6076getLambda2$app_debug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        final MutableState<Boolean> mutableState = isRunning;
                        final CoroutineScope coroutineScope = scope;
                        final SnapshotStateMap<String, Boolean> snapshotStateMap = results;
                        AppBarKt.TopAppBar(m6075getLambda1$app_debug, null, composableLambda, ComposableLambdaKt.composableLambda($composer4, -1655455016, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$2.2
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
                                Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
                                ComposerKt.sourceInformation($composer5, "C216@7147L443:SecurityScreen.kt#2o9c7b");
                                if (($changed3 & 81) != 16 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1655455016, $changed3, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous> (SecurityScreen.kt:216)");
                                    }
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    final MutableState<Boolean> mutableState2 = mutableState;
                                    final SnapshotStateMap<String, Boolean> snapshotStateMap2 = snapshotStateMap;
                                    Function0<Unit> function02 = new Function0<Unit>() { // from class: com.fakehal.controller.SecurityScreenKt.SecurityScreen.2.2.1
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
                                            SecurityScreenKt.SecurityScreen$runChecks(CoroutineScope.this, mutableState2, snapshotStateMap2);
                                        }
                                    };
                                    boolean z2 = !mutableState.getValue().booleanValue();
                                    final MutableState<Boolean> mutableState3 = mutableState;
                                    IconButtonKt.IconButton(function02, null, z2, null, null, ComposableLambdaKt.composableLambda($composer5, -873915083, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt.SecurityScreen.2.2.2
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
                                            ComposerKt.sourceInformation($composer6, "C:SecurityScreen.kt#2o9c7b");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-873915083, $changed4, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous>.<anonymous> (SecurityScreen.kt:217)");
                                                }
                                                if (mutableState3.getValue().booleanValue()) {
                                                    $composer6.startReplaceableGroup(-1184037282);
                                                    ComposerKt.sourceInformation($composer6, "218@7290L105");
                                                    ProgressIndicatorKt.m1748CircularProgressIndicatorLxG7B9w(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(20)), ColorKt.Color(4286336511L), Dp.m5734constructorimpl(2), 0L, 0, $composer6, 438, 24);
                                                    $composer6.endReplaceableGroup();
                                                } else {
                                                    $composer6.startReplaceableGroup(-1184037115);
                                                    ComposerKt.sourceInformation($composer6, "220@7457L85");
                                                    IconKt.m1601Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), "Refresh", (Modifier) null, ColorKt.Color(4286336511L), $composer6, 3120, 4);
                                                    $composer6.endReplaceableGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 26);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), null, TopAppBarDefaults.INSTANCE.m2288topAppBarColorszjMxDiM(ColorKt.Color(4279900718L), 0L, 0L, 0L, 0L, $composer4, (TopAppBarDefaults.$stable << 15) | 6, 30), null, $composer4, 3462, 82);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), null, null, null, 0, ColorKt.Color(4279176995L), 0L, null, ComposableLambdaKt.composableLambda($composer3, 434070514, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3
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

                public final void invoke(PaddingValues padding, Composer $composer4, int $changed2) {
                    Object value$iv3;
                    Intrinsics.checkNotNullParameter(padding, "padding");
                    ComposerKt.sourceInformation($composer4, "C233@8040L3193,229@7802L3431:SecurityScreen.kt#2o9c7b");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(padding) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(434070514, $dirty4, -1, "com.fakehal.controller.SecurityScreen.<anonymous> (SecurityScreen.kt:229)");
                        }
                        Modifier m566paddingVpY3zN4$default = PaddingKt.m566paddingVpY3zN4$default(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding), Dp.m5734constructorimpl(16), 0.0f, 2, null);
                        Arrangement.HorizontalOrVertical m473spacedBy0680j_4 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5734constructorimpl(8));
                        PaddingValues m559PaddingValuesYgX7TsA$default = PaddingKt.m559PaddingValuesYgX7TsA$default(0.0f, Dp.m5734constructorimpl(16), 1, null);
                        Arrangement.HorizontalOrVertical horizontalOrVertical = m473spacedBy0680j_4;
                        $composer4.startReplaceableGroup(397930152);
                        ComposerKt.sourceInformation($composer4, "CC(remember):SecurityScreen.kt#9igjgp");
                        boolean invalid$iv = $composer4.changed(allPass) | $composer4.changed(passCount) | $composer4.changed(totalCount);
                        final boolean z2 = allPass;
                        final MutableState<Boolean> mutableState = isRunning;
                        final int i = passCount;
                        final int i2 = totalCount;
                        final SnapshotStateMap<String, Boolean> snapshotStateMap = results;
                        Object it$iv3 = $composer4.rememberedValue();
                        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new Function1<LazyListScope, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                                    invoke2(lazyListScope);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(LazyListScope LazyColumn) {
                                    Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
                                    final boolean z3 = z2;
                                    final MutableState<Boolean> mutableState2 = mutableState;
                                    final int i3 = i;
                                    final int i4 = i2;
                                    LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(2087904734, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                                            invoke(lazyItemScope, composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(LazyItemScope item, Composer $composer5, int $changed3) {
                                            Intrinsics.checkNotNullParameter(item, "$this$item");
                                            ComposerKt.sourceInformation($composer5, "C236@8121L2028:SecurityScreen.kt#2o9c7b");
                                            if (($changed3 & 81) != 16 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2087904734, $changed3, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SecurityScreen.kt:236)");
                                                }
                                                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                RoundedCornerShape m835RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(12));
                                                long Color = ColorKt.Color(z3 ? 4279974427L : 4281998107L);
                                                BorderStroke m239BorderStrokecXLIe8U = BorderStrokeKt.m239BorderStrokecXLIe8U(Dp.m5734constructorimpl(1), ColorKt.Color(z3 ? 4283215696L : 4294921549L));
                                                final MutableState<Boolean> mutableState3 = mutableState2;
                                                final boolean z4 = z3;
                                                final int i5 = i3;
                                                final int i6 = i4;
                                                SurfaceKt.m1981SurfaceT9BRK9s(fillMaxWidth$default, m835RoundedCornerShape0680j_4, Color, 0L, 0.0f, 0.0f, m239BorderStrokecXLIe8U, ComposableLambdaKt.composableLambda($composer5, -1078236829, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt.SecurityScreen.3.1.1.1.1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                        invoke(composer, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* JADX WARN: Removed duplicated region for block: B:24:0x01d2  */
                                                    /* JADX WARN: Removed duplicated region for block: B:27:0x01de  */
                                                    /* JADX WARN: Removed duplicated region for block: B:35:0x029d  */
                                                    /* JADX WARN: Removed duplicated region for block: B:38:0x02b1  */
                                                    /* JADX WARN: Removed duplicated region for block: B:41:0x034e  */
                                                    /* JADX WARN: Removed duplicated region for block: B:44:0x03e9  */
                                                    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
                                                    /* JADX WARN: Removed duplicated region for block: B:47:0x0385  */
                                                    /* JADX WARN: Removed duplicated region for block: B:52:0x02b7  */
                                                    /* JADX WARN: Removed duplicated region for block: B:53:0x02a2  */
                                                    /* JADX WARN: Removed duplicated region for block: B:58:0x01e4  */
                                                    /*
                                                        Code decompiled incorrectly, please refer to instructions dump.
                                                    */
                                                    public final void invoke(Composer $composer6, int $changed4) {
                                                        Function0 factory$iv$iv$iv;
                                                        Function0 factory$iv$iv$iv2;
                                                        Composer $this$Layout_u24lambda_u240$iv$iv;
                                                        String str;
                                                        ComposerKt.sourceInformation($composer6, "C242@8468L1663:SecurityScreen.kt#2o9c7b");
                                                        if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-1078236829, $changed4, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SecurityScreen.kt:242)");
                                                            }
                                                            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(16)), 0.0f, 1, null);
                                                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                                            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                                                            MutableState<Boolean> mutableState4 = mutableState3;
                                                            boolean z5 = z4;
                                                            int i7 = i5;
                                                            int i8 = i6;
                                                            $composer6.startReplaceableGroup(693286680);
                                                            ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                                            int $changed$iv$iv = (438 << 3) & 112;
                                                            $composer6.startReplaceableGroup(-1323940314);
                                                            ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                            CompositionLocalMap localMap$iv$iv = $composer6.getCurrentCompositionLocalMap();
                                                            Function0 factory$iv$iv$iv3 = ComposeUiNode.INSTANCE.getConstructor();
                                                            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(fillMaxWidth$default2);
                                                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                                            if (!($composer6.getApplier() instanceof Applier)) {
                                                                ComposablesKt.invalidApplier();
                                                            }
                                                            $composer6.startReusableNode();
                                                            if ($composer6.getInserting()) {
                                                                factory$iv$iv$iv = factory$iv$iv$iv3;
                                                                $composer6.createNode(factory$iv$iv$iv);
                                                            } else {
                                                                factory$iv$iv$iv = factory$iv$iv$iv3;
                                                                $composer6.useNode();
                                                            }
                                                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer6);
                                                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                                skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                                $composer6.startReplaceableGroup(2058660585);
                                                                int i9 = ($changed$iv$iv$iv >> 9) & 14;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                                                int i10 = ((438 >> 6) & 112) | 6;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, -314999541, "C247@8742L964:SecurityScreen.kt#2o9c7b");
                                                                $composer6.startReplaceableGroup(-483455358);
                                                                ComposerKt.sourceInformation($composer6, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                                Modifier modifier$iv = Modifier.INSTANCE;
                                                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                                                int $changed$iv = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                                                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer6, $changed$iv);
                                                                int $changed$iv$iv2 = (0 << 3) & 112;
                                                                $composer6.startReplaceableGroup(-1323940314);
                                                                ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                                CompositionLocalMap localMap$iv$iv2 = $composer6.getCurrentCompositionLocalMap();
                                                                Function0 factory$iv$iv$iv4 = ComposeUiNode.INSTANCE.getConstructor();
                                                                Function3 skippableUpdate$iv$iv$iv2 = LayoutKt.modifierMaterializerOf(modifier$iv);
                                                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                                if (!($composer6.getApplier() instanceof Applier)) {
                                                                    ComposablesKt.invalidApplier();
                                                                }
                                                                $composer6.startReusableNode();
                                                                if (!$composer6.getInserting()) {
                                                                    factory$iv$iv$iv2 = factory$iv$iv$iv4;
                                                                    $composer6.createNode(factory$iv$iv$iv2);
                                                                } else {
                                                                    factory$iv$iv$iv2 = factory$iv$iv$iv4;
                                                                    $composer6.useNode();
                                                                }
                                                                $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer6);
                                                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                Function2 block$iv$iv$iv2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                                                    skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                                    $composer6.startReplaceableGroup(2058660585);
                                                                    int i11 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                                    ComposerKt.sourceInformationMarkerStart($composer6, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                                                    int i12 = ((0 >> 6) & 112) | 6;
                                                                    ComposerKt.sourceInformationMarkerStart($composer6, 70143953, "C248@8779L647,261@9455L225:SecurityScreen.kt#2o9c7b");
                                                                    if (!mutableState4.getValue().booleanValue()) {
                                                                        str = "Checking...";
                                                                    } else if (z5) {
                                                                        str = "✓ All Clear";
                                                                    } else {
                                                                        str = "⚠ Issues Found";
                                                                    }
                                                                    TextKt.m2129Text4IGK_g(str, (Modifier) null, ColorKt.Color(!z5 ? 4283215696L : 4294921549L), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 199680, 0, 131026);
                                                                    TextKt.m2129Text4IGK_g(i7 + " / " + i8 + " checks passed", (Modifier) null, ColorKt.Color(4289374890L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3456, 0, 131058);
                                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                    $composer6.endReplaceableGroup();
                                                                    $composer6.endNode();
                                                                    $composer6.endReplaceableGroup();
                                                                    $composer6.endReplaceableGroup();
                                                                    if (!mutableState4.getValue().booleanValue()) {
                                                                        $composer6.startReplaceableGroup(-314998531);
                                                                        ComposerKt.sourceInformation($composer6, "268@9782L85");
                                                                        ProgressIndicatorKt.m1748CircularProgressIndicatorLxG7B9w(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(32)), ColorKt.Color(4286336511L), 0.0f, 0L, 0, $composer6, 54, 28);
                                                                        $composer6.endReplaceableGroup();
                                                                    } else {
                                                                        $composer6.startReplaceableGroup(-314998384);
                                                                        ComposerKt.sourceInformation($composer6, "270@9929L154");
                                                                        TextKt.m2129Text4IGK_g(z5 ? "🛡️" : "⚠️", (Modifier) null, 0L, TextUnitKt.getSp(32), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3072, 0, 131062);
                                                                        $composer6.endReplaceableGroup();
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                    $composer6.endReplaceableGroup();
                                                                    $composer6.endNode();
                                                                    $composer6.endReplaceableGroup();
                                                                    $composer6.endReplaceableGroup();
                                                                    if (!ComposerKt.isTraceInProgress()) {
                                                                        ComposerKt.traceEventEnd();
                                                                        return;
                                                                    }
                                                                    return;
                                                                }
                                                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv2), block$iv$iv$iv2);
                                                                skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                                $composer6.startReplaceableGroup(2058660585);
                                                                int i112 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                                                int i122 = ((0 >> 6) & 112) | 6;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, 70143953, "C248@8779L647,261@9455L225:SecurityScreen.kt#2o9c7b");
                                                                if (!mutableState4.getValue().booleanValue()) {
                                                                }
                                                                TextKt.m2129Text4IGK_g(str, (Modifier) null, ColorKt.Color(!z5 ? 4283215696L : 4294921549L), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 199680, 0, 131026);
                                                                TextKt.m2129Text4IGK_g(i7 + " / " + i8 + " checks passed", (Modifier) null, ColorKt.Color(4289374890L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3456, 0, 131058);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endNode();
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endReplaceableGroup();
                                                                if (!mutableState4.getValue().booleanValue()) {
                                                                }
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endNode();
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endReplaceableGroup();
                                                                if (!ComposerKt.isTraceInProgress()) {
                                                                }
                                                            }
                                                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                                                            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                            $composer6.startReplaceableGroup(2058660585);
                                                            int i92 = ($changed$iv$iv$iv >> 9) & 14;
                                                            ComposerKt.sourceInformationMarkerStart($composer6, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                                            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                                            int i102 = ((438 >> 6) & 112) | 6;
                                                            ComposerKt.sourceInformationMarkerStart($composer6, -314999541, "C247@8742L964:SecurityScreen.kt#2o9c7b");
                                                            $composer6.startReplaceableGroup(-483455358);
                                                            ComposerKt.sourceInformation($composer6, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                            Modifier modifier$iv2 = Modifier.INSTANCE;
                                                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                                                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                                                            int $changed$iv2 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                                                            MeasurePolicy measurePolicy$iv22 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer6, $changed$iv2);
                                                            int $changed$iv$iv22 = (0 << 3) & 112;
                                                            $composer6.startReplaceableGroup(-1323940314);
                                                            ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                            int compositeKeyHash$iv$iv22 = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                            CompositionLocalMap localMap$iv$iv22 = $composer6.getCurrentCompositionLocalMap();
                                                            Function0 factory$iv$iv$iv42 = ComposeUiNode.INSTANCE.getConstructor();
                                                            Function3 skippableUpdate$iv$iv$iv22 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                                            int $changed$iv$iv$iv22 = (($changed$iv$iv22 << 9) & 7168) | 6;
                                                            if (!($composer6.getApplier() instanceof Applier)) {
                                                            }
                                                            $composer6.startReusableNode();
                                                            if (!$composer6.getInserting()) {
                                                            }
                                                            $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer6);
                                                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                            Function2 block$iv$iv$iv22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                                                                skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                                                                $composer6.startReplaceableGroup(2058660585);
                                                                int i1122 = ($changed$iv$iv$iv22 >> 9) & 14;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                ColumnScopeInstance columnScopeInstance22 = ColumnScopeInstance.INSTANCE;
                                                                int i1222 = ((0 >> 6) & 112) | 6;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, 70143953, "C248@8779L647,261@9455L225:SecurityScreen.kt#2o9c7b");
                                                                if (!mutableState4.getValue().booleanValue()) {
                                                                }
                                                                TextKt.m2129Text4IGK_g(str, (Modifier) null, ColorKt.Color(!z5 ? 4283215696L : 4294921549L), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 199680, 0, 131026);
                                                                TextKt.m2129Text4IGK_g(i7 + " / " + i8 + " checks passed", (Modifier) null, ColorKt.Color(4289374890L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3456, 0, 131058);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endNode();
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endReplaceableGroup();
                                                                if (!mutableState4.getValue().booleanValue()) {
                                                                }
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endNode();
                                                                $composer6.endReplaceableGroup();
                                                                $composer6.endReplaceableGroup();
                                                                if (!ComposerKt.isTraceInProgress()) {
                                                                }
                                                            }
                                                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv22));
                                                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv22), block$iv$iv$iv22);
                                                            skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                                                            $composer6.startReplaceableGroup(2058660585);
                                                            int i11222 = ($changed$iv$iv$iv22 >> 9) & 14;
                                                            ComposerKt.sourceInformationMarkerStart($composer6, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                            ColumnScopeInstance columnScopeInstance222 = ColumnScopeInstance.INSTANCE;
                                                            int i12222 = ((0 >> 6) & 112) | 6;
                                                            ComposerKt.sourceInformationMarkerStart($composer6, 70143953, "C248@8779L647,261@9455L225:SecurityScreen.kt#2o9c7b");
                                                            if (!mutableState4.getValue().booleanValue()) {
                                                            }
                                                            TextKt.m2129Text4IGK_g(str, (Modifier) null, ColorKt.Color(!z5 ? 4283215696L : 4294921549L), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 199680, 0, 131026);
                                                            TextKt.m2129Text4IGK_g(i7 + " / " + i8 + " checks passed", (Modifier) null, ColorKt.Color(4289374890L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 3456, 0, 131058);
                                                            ComposerKt.sourceInformationMarkerEnd($composer6);
                                                            ComposerKt.sourceInformationMarkerEnd($composer6);
                                                            $composer6.endReplaceableGroup();
                                                            $composer6.endNode();
                                                            $composer6.endReplaceableGroup();
                                                            $composer6.endReplaceableGroup();
                                                            if (!mutableState4.getValue().booleanValue()) {
                                                            }
                                                            ComposerKt.sourceInformationMarkerEnd($composer6);
                                                            ComposerKt.sourceInformationMarkerEnd($composer6);
                                                            $composer6.endReplaceableGroup();
                                                            $composer6.endNode();
                                                            $composer6.endReplaceableGroup();
                                                            $composer6.endReplaceableGroup();
                                                            if (!ComposerKt.isTraceInProgress()) {
                                                            }
                                                        } else {
                                                            $composer6.skipToGroupEnd();
                                                        }
                                                    }
                                                }), $composer5, 12582918, 56);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }), 3, null);
                                    List categories = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("integrity", "Play Integrity"), TuplesKt.to("props", "Build Properties"), TuplesKt.to("files", "Dangerous Files"), TuplesKt.to("susfs", "SUSFS & Modules")});
                                    List $this$forEach$iv = categories;
                                    final SnapshotStateMap<String, Boolean> snapshotStateMap2 = snapshotStateMap;
                                    for (Object element$iv3 : $this$forEach$iv) {
                                        Pair pair = (Pair) element$iv3;
                                        String catId = (String) pair.component1();
                                        final String catLabel = (String) pair.component2();
                                        Iterable $this$filter$iv = SecurityScreenKt.getSECURITY_CHECKS();
                                        Collection destination$iv$iv = new ArrayList();
                                        for (Object element$iv$iv : $this$filter$iv) {
                                            SecurityCheck it3 = (SecurityCheck) element$iv$iv;
                                            if (Intrinsics.areEqual(it3.getCategory(), catId)) {
                                                destination$iv$iv.add(element$iv$iv);
                                            }
                                        }
                                        final List checks = (List) destination$iv$iv;
                                        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1146727161, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1$2$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                                                invoke(lazyItemScope, composer, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(LazyItemScope item, Composer $composer5, int $changed3) {
                                                Intrinsics.checkNotNullParameter(item, "$this$item");
                                                ComposerKt.sourceInformation($composer5, "C292@10656L355:SecurityScreen.kt#2o9c7b");
                                                if (($changed3 & 81) != 16 || !$composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1146727161, $changed3, -1, "com.fakehal.controller.SecurityScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SecurityScreen.kt:292)");
                                                    }
                                                    String upperCase = catLabel.toUpperCase(Locale.ROOT);
                                                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                                                    TextKt.m2129Text4IGK_g(upperCase, PaddingKt.m568paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m5734constructorimpl(8), 0.0f, Dp.m5734constructorimpl(4), 5, null), ColorKt.Color(4286336511L), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, TextUnitKt.getSp(1.5d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 12783024, 0, 130896);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer5.skipToGroupEnd();
                                            }
                                        }), 3, null);
                                        final Function1 contentType$iv = new Function1() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1$invoke$lambda$2$$inlined$items$default$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                                                return invoke((SecurityCheck) p1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Void invoke(SecurityCheck securityCheck) {
                                                return null;
                                            }
                                        };
                                        LazyColumn.items(checks.size(), null, new Function1<Integer, Object>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1$invoke$lambda$2$$inlined$items$default$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                                return invoke(num.intValue());
                                            }

                                            public final Object invoke(int index) {
                                                return Function1.this.invoke(checks.get(index));
                                            }
                                        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$3$1$1$invoke$lambda$2$$inlined$items$default$4
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(4);
                                            }

                                            @Override // kotlin.jvm.functions.Function4
                                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(LazyItemScope $this$items, int it4, Composer $composer5, int $changed3) {
                                                ComposerKt.sourceInformation($composer5, "C148@6730L22:LazyDsl.kt#428nma");
                                                int $dirty5 = $changed3;
                                                if (($changed3 & 14) == 0) {
                                                    $dirty5 |= $composer5.changed($this$items) ? 4 : 2;
                                                }
                                                if (($changed3 & 112) == 0) {
                                                    $dirty5 |= $composer5.changed(it4) ? 32 : 16;
                                                }
                                                if (($dirty5 & 731) == 146 && $composer5.getSkipping()) {
                                                    $composer5.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-632812321, $dirty5, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:148)");
                                                }
                                                SecurityCheck check = (SecurityCheck) checks.get(it4);
                                                ComposerKt.sourceInformationMarkerStart($composer5, -527529448, "C*304@11143L48:SecurityScreen.kt#2o9c7b");
                                                Boolean result = (Boolean) snapshotStateMap2.get(check.getId());
                                                SecurityScreenKt.SecurityCheckRow(check, result, $composer5, (($dirty5 & 14) >> 3) & 14);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        categories = categories;
                                    }
                                }
                            };
                            $composer4.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        $composer4.endReplaceableGroup();
                        LazyDslKt.LazyColumn(m566paddingVpY3zN4$default, null, m559PaddingValuesYgX7TsA$default, false, horizontalOrVertical, null, null, false, (Function1) value$iv3, $composer4, 24960, 234);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityScreen$4
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
                    SecurityScreenKt.SecurityScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SecurityScreen$runChecks(CoroutineScope scope, MutableState<Boolean> mutableState, SnapshotStateMap<String, Boolean> snapshotStateMap) {
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new SecurityScreenKt$SecurityScreen$runChecks$1(mutableState, snapshotStateMap, null), 3, null);
    }

    public static final void SecurityCheckRow(final SecurityCheck check, final Boolean result, Composer $composer, final int $changed) {
        long Color;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(check, "check");
        Composer $composer3 = $composer.startRestartGroup(-1243241231);
        ComposerKt.sourceInformation($composer3, "C(SecurityCheckRow)316@11333L1508:SecurityScreen.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(check) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(result) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 91) != 18 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1243241231, $dirty2, -1, "com.fakehal.controller.SecurityCheckRow (SecurityScreen.kt:315)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            RoundedCornerShape m835RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m835RoundedCornerShape0680j_4(Dp.m5734constructorimpl(8));
            long Color2 = ColorKt.Color(4279900718L);
            float m5734constructorimpl = Dp.m5734constructorimpl(1);
            if (Intrinsics.areEqual((Object) result, (Object) true)) {
                Color = ColorKt.Color(4281236786L);
            } else if (Intrinsics.areEqual((Object) result, (Object) false)) {
                Color = ColorKt.Color(4290190364L);
            } else {
                if (result != null) {
                    throw new NoWhenBranchMatchedException();
                }
                Color = ColorKt.Color(4280953413L);
            }
            $composer2 = $composer3;
            SurfaceKt.m1981SurfaceT9BRK9s(fillMaxWidth$default, m835RoundedCornerShape0680j_4, Color2, 0L, 0.0f, 0.0f, BorderStrokeKt.m239BorderStrokecXLIe8U(m5734constructorimpl, Color), ComposableLambdaKt.composableLambda($composer3, -933485812, true, new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityCheckRow$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x01df  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x01eb  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x02ac  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x0422  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x042e  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0467  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x0590  */
                /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:53:0x047d A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:54:0x0434  */
                /* JADX WARN: Removed duplicated region for block: B:55:0x02ec  */
                /* JADX WARN: Removed duplicated region for block: B:63:0x01f1  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke(Composer $composer4, int $changed2) {
                    Function0 factory$iv$iv$iv;
                    Function0 factory$iv$iv$iv2;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    Function0 factory$iv$iv$iv3;
                    Composer $this$Layout_u24lambda_u240$iv$iv2;
                    ComposerKt.sourceInformation($composer4, "C330@11749L1086:SecurityScreen.kt#2o9c7b");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-933485812, $changed2, -1, "com.fakehal.controller.SecurityCheckRow.<anonymous> (SecurityScreen.kt:330)");
                        }
                        Modifier modifier$iv = PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5734constructorimpl(14), Dp.m5734constructorimpl(10));
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Boolean bool = result;
                        SecurityCheck securityCheck = check;
                        $composer4.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv = (390 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv4 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            factory$iv$iv$iv = factory$iv$iv$iv4;
                            $composer4.createNode(factory$iv$iv$iv);
                        } else {
                            factory$iv$iv$iv = factory$iv$iv$iv4;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2943constructorimpl($composer4);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                            int i2 = ((390 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u242 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1304468282, "C335@11947L566,346@12527L40,348@12581L244:SecurityScreen.kt#2o9c7b");
                            Modifier modifier$iv2 = SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28));
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                            int $i$f$Row = ((54 >> 3) & 14) | ((54 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, $i$f$Row);
                            int $changed$iv$iv2 = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv5 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if (!$composer4.getInserting()) {
                                factory$iv$iv$iv2 = factory$iv$iv$iv5;
                                $composer4.createNode(factory$iv$iv$iv2);
                            } else {
                                factory$iv$iv$iv2 = factory$iv$iv$iv5;
                                $composer4.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer4);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i4 = ((54 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -806938894, "C:SecurityScreen.kt#2o9c7b");
                                if (!Intrinsics.areEqual((Object) bool, (Object) true)) {
                                    $composer4.startReplaceableGroup(-806938850);
                                    ComposerKt.sourceInformation($composer4, "340@12130L96");
                                    IconKt.m1601Iconww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(22)), ColorKt.Color(4283215696L), $composer4, 3504, 0);
                                    $composer4.endReplaceableGroup();
                                } else if (Intrinsics.areEqual((Object) bool, (Object) false)) {
                                    $composer4.startReplaceableGroup(-806938724);
                                    ComposerKt.sourceInformation($composer4, "341@12256L91");
                                    IconKt.m1601Iconww6aTOc(CancelKt.getCancel(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(22)), ColorKt.Color(4294921549L), $composer4, 3504, 0);
                                    $composer4.endReplaceableGroup();
                                } else if (bool == null) {
                                    $composer4.startReplaceableGroup(-806938604);
                                    ComposerKt.sourceInformation($composer4, "342@12376L105");
                                    ProgressIndicatorKt.m1748CircularProgressIndicatorLxG7B9w(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(18)), ColorKt.Color(4286336511L), Dp.m5734constructorimpl(2), 0L, 0, $composer4, 438, 24);
                                    $composer4.endReplaceableGroup();
                                } else {
                                    $composer4.startReplaceableGroup(-806938481);
                                    $composer4.endReplaceableGroup();
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(12)), $composer4, 6);
                                Modifier modifier$iv3 = RowScope.weight$default($this$invoke_u24lambda_u242, Modifier.INSTANCE, 1.0f, false, 2, null);
                                $composer4.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv3 = (0 << 3) & 112;
                                $composer4.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                                Function0 factory$iv$iv$iv6 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3 skippableUpdate$iv$iv$iv3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if (!$composer4.getInserting()) {
                                    factory$iv$iv$iv3 = factory$iv$iv$iv6;
                                    $composer4.createNode(factory$iv$iv$iv3);
                                } else {
                                    factory$iv$iv$iv3 = factory$iv$iv$iv6;
                                    $composer4.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer4);
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2 block$iv$iv$iv3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                    skippableUpdate$iv$iv$iv3.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i5 = ($changed$iv$iv$iv3 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                    int i6 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                                    TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                                    TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    if (!ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv3), block$iv$iv$iv3);
                                skippableUpdate$iv$iv$iv3.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i52 = ($changed$iv$iv$iv3 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                int i62 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                                TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                                TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                if (!ComposerKt.isTraceInProgress()) {
                                }
                            }
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv2), block$iv$iv$iv2);
                            skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i32 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i42 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -806938894, "C:SecurityScreen.kt#2o9c7b");
                            if (!Intrinsics.areEqual((Object) bool, (Object) true)) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(12)), $composer4, 6);
                            Modifier modifier$iv32 = RowScope.weight$default($this$invoke_u24lambda_u242, Modifier.INSTANCE, 1.0f, false, 2, null);
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv32 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv32 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv32 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv32 = $composer4.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv62 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv32 = LayoutKt.modifierMaterializerOf(modifier$iv32);
                            int $changed$iv$iv$iv32 = (($changed$iv$iv32 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                            }
                            $composer4.startReusableNode();
                            if (!$composer4.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer4);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv32 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                                skippableUpdate$iv$iv$iv32.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv32 >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i522 = ($changed$iv$iv$iv32 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance22 = ColumnScopeInstance.INSTANCE;
                                int i622 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                                TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                                TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                                if (!ComposerKt.isTraceInProgress()) {
                                }
                            }
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv32));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv32), block$iv$iv$iv32);
                            skippableUpdate$iv$iv$iv32.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv32 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i5222 = ($changed$iv$iv$iv32 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance222 = ColumnScopeInstance.INSTANCE;
                            int i6222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                            TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                            TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (!ComposerKt.isTraceInProgress()) {
                            }
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                        skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i7 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                        int i22 = ((390 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2422 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1304468282, "C335@11947L566,346@12527L40,348@12581L244:SecurityScreen.kt#2o9c7b");
                        Modifier modifier$iv22 = SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(28));
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                        int $i$f$Row2 = ((54 >> 3) & 14) | ((54 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv22 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer4, $i$f$Row2);
                        int $changed$iv$iv22 = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv22 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv22 = $composer4.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv52 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv22 = LayoutKt.modifierMaterializerOf(modifier$iv22);
                        int $changed$iv$iv$iv22 = (($changed$iv$iv22 << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                        }
                        $composer4.startReusableNode();
                        if (!$composer4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv = Updater.m2943constructorimpl($composer4);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                            skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i322 = ($changed$iv$iv$iv22 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                            int i422 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -806938894, "C:SecurityScreen.kt#2o9c7b");
                            if (!Intrinsics.areEqual((Object) bool, (Object) true)) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(12)), $composer4, 6);
                            Modifier modifier$iv322 = RowScope.weight$default($this$invoke_u24lambda_u2422, Modifier.INSTANCE, 1.0f, false, 2, null);
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv22 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv22 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv322 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv22, horizontalAlignment$iv22, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv322 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv322 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv322 = $composer4.getCurrentCompositionLocalMap();
                            Function0 factory$iv$iv$iv622 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3 skippableUpdate$iv$iv$iv322 = LayoutKt.modifierMaterializerOf(modifier$iv322);
                            int $changed$iv$iv$iv322 = (($changed$iv$iv322 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                            }
                            $composer4.startReusableNode();
                            if (!$composer4.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer4);
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv322, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv322 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv322));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv322), block$iv$iv$iv322);
                            skippableUpdate$iv$iv$iv322.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv322 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i52222 = ($changed$iv$iv$iv322 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance2222 = ColumnScopeInstance.INSTANCE;
                            int i62222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                            TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                            TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (!ComposerKt.isTraceInProgress()) {
                            }
                        }
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv22));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv22), block$iv$iv$iv22);
                        skippableUpdate$iv$iv$iv22.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv22 >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i3222 = ($changed$iv$iv$iv22 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                        int i4222 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -806938894, "C:SecurityScreen.kt#2o9c7b");
                        if (!Intrinsics.areEqual((Object) bool, (Object) true)) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5734constructorimpl(12)), $composer4, 6);
                        Modifier modifier$iv3222 = RowScope.weight$default($this$invoke_u24lambda_u2422, Modifier.INSTANCE, 1.0f, false, 2, null);
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv222 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv222 = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv3222 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv222, horizontalAlignment$iv222, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv3222 = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv3222 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv3222 = $composer4.getCurrentCompositionLocalMap();
                        Function0 factory$iv$iv$iv6222 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3 skippableUpdate$iv$iv$iv3222 = LayoutKt.modifierMaterializerOf(modifier$iv3222);
                        int $changed$iv$iv$iv3222 = (($changed$iv$iv3222 << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                        }
                        $composer4.startReusableNode();
                        if (!$composer4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2943constructorimpl($composer4);
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2950setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv3222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3222));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv3222), block$iv$iv$iv3222);
                        skippableUpdate$iv$iv$iv3222.invoke(SkippableUpdater.m2934boximpl(SkippableUpdater.m2935constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv3222 >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i522222 = ($changed$iv$iv$iv3222 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance22222 = ColumnScopeInstance.INSTANCE;
                        int i622222 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -806938342, "C349@12638L88,350@12743L68:SecurityScreen.kt#2o9c7b");
                        TextKt.m2129Text4IGK_g(securityCheck.getLabel(), (Modifier) null, Color.INSTANCE.m3450getWhite0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 200064, 0, 131026);
                        TextKt.m2129Text4IGK_g(securityCheck.getDescription(), (Modifier) null, ColorKt.Color(4287137945L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3456, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (!ComposerKt.isTraceInProgress()) {
                        }
                    } else {
                        $composer4.skipToGroupEnd();
                    }
                }
            }), $composer3, 12583302, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.SecurityScreenKt$SecurityCheckRow$2
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
                    SecurityScreenKt.SecurityCheckRow(SecurityCheck.this, result, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}

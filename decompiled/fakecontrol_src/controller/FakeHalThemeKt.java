package com.fakehal.controller;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FakeHalTheme.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"DarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "FakeHalTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "app_debug"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FakeHalThemeKt {
    private static final ColorScheme DarkColorScheme = ColorSchemeKt.m1398darkColorSchemeCXl9yA$default(ColorKt.Color(4286336511L), Color.INSTANCE.m3450getWhite0d7_KjU(), ColorKt.Color(4281794739L), 0, 0, ColorKt.Color(4278442694L), Color.INSTANCE.m3439getBlack0d7_KjU(), 0, 0, 0, 0, 0, 0, ColorKt.Color(4279176995L), ColorKt.Color(4292927728L), ColorKt.Color(4279900718L), ColorKt.Color(4292927728L), ColorKt.Color(4280624448L), ColorKt.Color(4289769672L), 0, 0, 0, ColorKt.Color(4291782265L), Color.INSTANCE.m3439getBlack0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -13099112, 15, null);

    public static final void FakeHalTheme(final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(267332901);
        ComposerKt.sourceInformation($composer2, "C(FakeHalTheme)26@846L86:FakeHalTheme.kt#2o9c7b");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 11) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(267332901, $dirty2, -1, "com.fakehal.controller.FakeHalTheme (FakeHalTheme.kt:25)");
            }
            MaterialThemeKt.MaterialTheme(DarkColorScheme, null, null, content, $composer2, (($dirty2 << 9) & 7168) | 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.fakehal.controller.FakeHalThemeKt$FakeHalTheme$1
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
                    FakeHalThemeKt.FakeHalTheme(content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}

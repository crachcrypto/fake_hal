package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SecurityScreen.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, d2 = {"Lcom/fakehal/controller/SecurityCheck;", "", "id", "", "label", "description", "category", "command", "passCondition", "invertResult", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getCategory", "()Ljava/lang/String;", "getCommand", "getDescription", "getId", "getInvertResult", "()Z", "getLabel", "getPassCondition", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SecurityCheck {
    public static final int $stable = 0;
    private final String category;
    private final String command;
    private final String description;
    private final String id;
    private final boolean invertResult;
    private final String label;
    private final String passCondition;

    public static /* synthetic */ SecurityCheck copy$default(SecurityCheck securityCheck, String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = securityCheck.id;
        }
        if ((i & 2) != 0) {
            str2 = securityCheck.label;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = securityCheck.description;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = securityCheck.category;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = securityCheck.command;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = securityCheck.passCondition;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            z = securityCheck.invertResult;
        }
        return securityCheck.copy(str, str7, str8, str9, str10, str11, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCommand() {
        return this.command;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPassCondition() {
        return this.passCondition;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getInvertResult() {
        return this.invertResult;
    }

    public final SecurityCheck copy(String id, String label, String description, String category, String command, String passCondition, boolean invertResult) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(passCondition, "passCondition");
        return new SecurityCheck(id, label, description, category, command, passCondition, invertResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SecurityCheck)) {
            return false;
        }
        SecurityCheck securityCheck = (SecurityCheck) other;
        return Intrinsics.areEqual(this.id, securityCheck.id) && Intrinsics.areEqual(this.label, securityCheck.label) && Intrinsics.areEqual(this.description, securityCheck.description) && Intrinsics.areEqual(this.category, securityCheck.category) && Intrinsics.areEqual(this.command, securityCheck.command) && Intrinsics.areEqual(this.passCondition, securityCheck.passCondition) && this.invertResult == securityCheck.invertResult;
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.label.hashCode()) * 31) + this.description.hashCode()) * 31) + this.category.hashCode()) * 31) + this.command.hashCode()) * 31) + this.passCondition.hashCode()) * 31) + Boolean.hashCode(this.invertResult);
    }

    public String toString() {
        return "SecurityCheck(id=" + this.id + ", label=" + this.label + ", description=" + this.description + ", category=" + this.category + ", command=" + this.command + ", passCondition=" + this.passCondition + ", invertResult=" + this.invertResult + ")";
    }

    public SecurityCheck(String id, String label, String description, String category, String command, String passCondition, boolean invertResult) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(passCondition, "passCondition");
        this.id = id;
        this.label = label;
        this.description = description;
        this.category = category;
        this.command = command;
        this.passCondition = passCondition;
        this.invertResult = invertResult;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ SecurityCheck(String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, r8);
        boolean z2;
        if ((i & 64) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getCommand() {
        return this.command;
    }

    public final String getPassCondition() {
        return this.passCondition;
    }

    public final boolean getInvertResult() {
        return this.invertResult;
    }
}

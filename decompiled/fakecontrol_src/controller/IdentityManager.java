package com.fakehal.controller;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: IdentityManager.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0086@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0015\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fakehal/controller/IdentityManager;", "", "()V", "ACTIVE_CONFIG", "", "SAVED_PROFILE", "SPOOFKIT_DIR", "TAG", "apply", "", "profile", "Lcom/fakehal/controller/IdentityManager$DeviceProfile;", "(Lcom/fakehal/controller/IdentityManager$DeviceProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyLocation", "loc", "Lcom/fakehal/controller/IdentityManager$LocationProfile;", "(Lcom/fakehal/controller/IdentityManager$LocationProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readCurrent", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocation", "readSaved", "reset", "DeviceProfile", "LocationProfile", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IdentityManager {
    public static final int $stable = 0;
    private static final String ACTIVE_CONFIG = "/data/local/tmp/spoofkit/active_config";
    public static final IdentityManager INSTANCE = new IdentityManager();
    private static final String SAVED_PROFILE = "/data/local/tmp/spoofkit/saved_identity.json";
    private static final String SPOOFKIT_DIR = "/data/local/tmp/spoofkit";
    private static final String TAG = "IdentityManager";

    private IdentityManager() {
    }

    /* compiled from: IdentityManager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003Jm\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006*"}, d2 = {"Lcom/fakehal/controller/IdentityManager$DeviceProfile;", "", "manufacturer", "", "model", "brand", "device", "product", "fingerprint", "buildId", "serial", "androidId", "imei", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAndroidId", "()Ljava/lang/String;", "getBrand", "getBuildId", "getDevice", "getFingerprint", "getImei", "getManufacturer", "getModel", "getProduct", "getSerial", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class DeviceProfile {
        public static final int $stable = 0;
        private final String androidId;
        private final String brand;
        private final String buildId;
        private final String device;
        private final String fingerprint;
        private final String imei;
        private final String manufacturer;
        private final String model;
        private final String product;
        private final String serial;

        public DeviceProfile() {
            this(null, null, null, null, null, null, null, null, null, null, 1023, null);
        }

        /* renamed from: component1, reason: from getter */
        public final String getManufacturer() {
            return this.manufacturer;
        }

        /* renamed from: component10, reason: from getter */
        public final String getImei() {
            return this.imei;
        }

        /* renamed from: component2, reason: from getter */
        public final String getModel() {
            return this.model;
        }

        /* renamed from: component3, reason: from getter */
        public final String getBrand() {
            return this.brand;
        }

        /* renamed from: component4, reason: from getter */
        public final String getDevice() {
            return this.device;
        }

        /* renamed from: component5, reason: from getter */
        public final String getProduct() {
            return this.product;
        }

        /* renamed from: component6, reason: from getter */
        public final String getFingerprint() {
            return this.fingerprint;
        }

        /* renamed from: component7, reason: from getter */
        public final String getBuildId() {
            return this.buildId;
        }

        /* renamed from: component8, reason: from getter */
        public final String getSerial() {
            return this.serial;
        }

        /* renamed from: component9, reason: from getter */
        public final String getAndroidId() {
            return this.androidId;
        }

        public final DeviceProfile copy(String manufacturer, String model, String brand, String device, String product, String fingerprint, String buildId, String serial, String androidId, String imei) {
            Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
            Intrinsics.checkNotNullParameter(model, "model");
            Intrinsics.checkNotNullParameter(brand, "brand");
            Intrinsics.checkNotNullParameter(device, "device");
            Intrinsics.checkNotNullParameter(product, "product");
            Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
            Intrinsics.checkNotNullParameter(buildId, "buildId");
            Intrinsics.checkNotNullParameter(serial, "serial");
            Intrinsics.checkNotNullParameter(androidId, "androidId");
            Intrinsics.checkNotNullParameter(imei, "imei");
            return new DeviceProfile(manufacturer, model, brand, device, product, fingerprint, buildId, serial, androidId, imei);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeviceProfile)) {
                return false;
            }
            DeviceProfile deviceProfile = (DeviceProfile) other;
            return Intrinsics.areEqual(this.manufacturer, deviceProfile.manufacturer) && Intrinsics.areEqual(this.model, deviceProfile.model) && Intrinsics.areEqual(this.brand, deviceProfile.brand) && Intrinsics.areEqual(this.device, deviceProfile.device) && Intrinsics.areEqual(this.product, deviceProfile.product) && Intrinsics.areEqual(this.fingerprint, deviceProfile.fingerprint) && Intrinsics.areEqual(this.buildId, deviceProfile.buildId) && Intrinsics.areEqual(this.serial, deviceProfile.serial) && Intrinsics.areEqual(this.androidId, deviceProfile.androidId) && Intrinsics.areEqual(this.imei, deviceProfile.imei);
        }

        public int hashCode() {
            return (((((((((((((((((this.manufacturer.hashCode() * 31) + this.model.hashCode()) * 31) + this.brand.hashCode()) * 31) + this.device.hashCode()) * 31) + this.product.hashCode()) * 31) + this.fingerprint.hashCode()) * 31) + this.buildId.hashCode()) * 31) + this.serial.hashCode()) * 31) + this.androidId.hashCode()) * 31) + this.imei.hashCode();
        }

        public String toString() {
            return "DeviceProfile(manufacturer=" + this.manufacturer + ", model=" + this.model + ", brand=" + this.brand + ", device=" + this.device + ", product=" + this.product + ", fingerprint=" + this.fingerprint + ", buildId=" + this.buildId + ", serial=" + this.serial + ", androidId=" + this.androidId + ", imei=" + this.imei + ")";
        }

        public DeviceProfile(String manufacturer, String model, String brand, String device, String product, String fingerprint, String buildId, String serial, String androidId, String imei) {
            Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
            Intrinsics.checkNotNullParameter(model, "model");
            Intrinsics.checkNotNullParameter(brand, "brand");
            Intrinsics.checkNotNullParameter(device, "device");
            Intrinsics.checkNotNullParameter(product, "product");
            Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
            Intrinsics.checkNotNullParameter(buildId, "buildId");
            Intrinsics.checkNotNullParameter(serial, "serial");
            Intrinsics.checkNotNullParameter(androidId, "androidId");
            Intrinsics.checkNotNullParameter(imei, "imei");
            this.manufacturer = manufacturer;
            this.model = model;
            this.brand = brand;
            this.device = device;
            this.product = product;
            this.fingerprint = fingerprint;
            this.buildId = buildId;
            this.serial = serial;
            this.androidId = androidId;
            this.imei = imei;
        }

        public /* synthetic */ DeviceProfile(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7, (i & 128) != 0 ? "" : str8, (i & 256) != 0 ? "" : str9, (i & 512) == 0 ? str10 : "");
        }

        public final String getManufacturer() {
            return this.manufacturer;
        }

        public final String getModel() {
            return this.model;
        }

        public final String getBrand() {
            return this.brand;
        }

        public final String getDevice() {
            return this.device;
        }

        public final String getProduct() {
            return this.product;
        }

        public final String getFingerprint() {
            return this.fingerprint;
        }

        public final String getBuildId() {
            return this.buildId;
        }

        public final String getSerial() {
            return this.serial;
        }

        public final String getAndroidId() {
            return this.androidId;
        }

        public final String getImei() {
            return this.imei;
        }
    }

    /* compiled from: IdentityManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/fakehal/controller/IdentityManager$LocationProfile;", "", "enabled", "", "lat", "", "lon", "(ZLjava/lang/String;Ljava/lang/String;)V", "getEnabled", "()Z", "getLat", "()Ljava/lang/String;", "getLon", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class LocationProfile {
        public static final int $stable = 0;
        private final boolean enabled;
        private final String lat;
        private final String lon;

        public LocationProfile() {
            this(false, null, null, 7, null);
        }

        public static /* synthetic */ LocationProfile copy$default(LocationProfile locationProfile, boolean z, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = locationProfile.enabled;
            }
            if ((i & 2) != 0) {
                str = locationProfile.lat;
            }
            if ((i & 4) != 0) {
                str2 = locationProfile.lon;
            }
            return locationProfile.copy(z, str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLat() {
            return this.lat;
        }

        /* renamed from: component3, reason: from getter */
        public final String getLon() {
            return this.lon;
        }

        public final LocationProfile copy(boolean enabled, String lat, String lon) {
            Intrinsics.checkNotNullParameter(lat, "lat");
            Intrinsics.checkNotNullParameter(lon, "lon");
            return new LocationProfile(enabled, lat, lon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LocationProfile)) {
                return false;
            }
            LocationProfile locationProfile = (LocationProfile) other;
            return this.enabled == locationProfile.enabled && Intrinsics.areEqual(this.lat, locationProfile.lat) && Intrinsics.areEqual(this.lon, locationProfile.lon);
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.enabled) * 31) + this.lat.hashCode()) * 31) + this.lon.hashCode();
        }

        public String toString() {
            return "LocationProfile(enabled=" + this.enabled + ", lat=" + this.lat + ", lon=" + this.lon + ")";
        }

        public LocationProfile(boolean enabled, String lat, String lon) {
            Intrinsics.checkNotNullParameter(lat, "lat");
            Intrinsics.checkNotNullParameter(lon, "lon");
            this.enabled = enabled;
            this.lat = lat;
            this.lon = lon;
        }

        public /* synthetic */ LocationProfile(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? "" : str2);
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final String getLat() {
            return this.lat;
        }

        public final String getLon() {
            return this.lon;
        }
    }

    public final Object readCurrent(Continuation<? super DeviceProfile> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$readCurrent$2(null), continuation);
    }

    public final Object readSaved(Continuation<? super DeviceProfile> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$readSaved$2(null), continuation);
    }

    public final Object apply(DeviceProfile profile, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$apply$2(profile, null), continuation);
    }

    public final Object readLocation(Continuation<? super LocationProfile> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$readLocation$2(null), continuation);
    }

    public final Object applyLocation(LocationProfile loc, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$applyLocation$2(loc, null), continuation);
    }

    public final Object reset(Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new IdentityManager$reset$2(null), continuation);
    }
}

# Project overview

A single-document consolidation of the handover notes, root-cause analyses, and project maps that accumulated during development. For higher-level architecture see the top-level `README.md`.

---

## Current state (2026-08 baseline)

| Component                 | Version          | Status                                           |
|---------------------------|------------------|--------------------------------------------------|
| HAL binary (device)       | v7.8-gcam        | ~200 KB, GCam-compatible, md5 tracked in `kit/MANIFEST.md5` |
| Reference HAL binary      | v7.7.2           | 354 KB, kept for strings/behavior comparison     |
| FakeControl APK           | v7.7.2-licensed  | Jetpack Compose, Smali-patched LicenseWatchdog   |
| SpoofKit (Xposed) APK     | v1.0.0           | hook wrapper enforces license at runtime         |
| FastAPI server            | v1.0.0           | admin REST, JWT device auth, command queue       |
| Verify endpoint           | —                | read-only `GET /verify`                          |
| Telegram sales bot        | v7.7.2-aiogram   | Crypto Pay invoices, plan-based key issuance     |

Known-good deployment: **Pixel 7 (panther) running Android 13 with KernelSU + SUSFS**. Pixel 4 / 4XL / 7 Pro / 7a build from the same tree. Pixel 6 (gralloc4) is expected to work. Pixel 8 is untested.

---

## Where state lives on the device

```
/data/local/tmp/
    fake_camera_provider          HAL binary
    afbc_encoder.so               LD_PRELOAD
    gralloc_uv_fix.so             LD_PRELOAD
    fake_video.mp4                back-camera MP4
    fake_video_front.mp4          front-camera MP4
    fake_video.slot_a.mp4         A/B slot
    fake_video.slot_b.mp4         A/B slot
    fake_video_rot{0,90,180,270}.mp4   rotation_bridge variants
    fakehal.conf                  runtime config
    fake_camera_hal.xml           VINTF fragment template
    fakehal_gate.sh               license gate + watchdog
    fakehal_key                   license key (plain text, 1 line)
    fakehal_gate.status           last verify response (JSON)
    fakehal_gate.log              gate activity
    fakehAL.log                   HAL stdout/stderr
    fakehAL_alive                 one-shot marker written in main()
    watchdog.log                  fakehal_watchdog.sh activity
    rotation_bridge.log           rotation_bridge.sh activity
    .fakehal_watchdog_off         kill switch (anti-bootloop)
    .fakehal_bridge_off           disables rotation_bridge
    .rotation_bridge.pid          current bridge PID

/data/adb/modules/FakeHAL/
    system/vendor/bin/hw/android.hardware.camera.provider-fake
    system/vendor/etc/vintf/manifest/fake_camera_hal.xml
    system/vendor/etc/init/fake_camera_hal.rc
    module.prop
    service.sh                    late_start KernelSU hook
    rotation_bridge.sh            copied into TMP on boot
    fakehal_watchdog.sh           copied into TMP on boot
    afbc_encoder.so
    gralloc_uv_fix.so
```

---

## Boot sequence

```
boot_completed → KernelSU runs modules/FakeHAL/service.sh (late_start)
    │
    ├─ Wait for sys.boot_completed = 1   (max 120 s)
    ├─ Copy video / binary / libs into /data/local/tmp if missing
    ├─ Apply SELinux rules
    │     hal_camera_default → shell_data_file   (rwx + map)
    │     init → hal_camera_default              (process transition, nosuid_transition)
    │
    ├─ STEP 1  bind-mount our fake_camera_hal.xml over the stock Lyric
    │          manifest at /vendor/etc/vintf/manifest/...google-apex.xml
    │          (actually bind-mount an empty manifest; our VINTF comes
    │          through the KernelSU magic-mount overlay so stacking
    │          them creates a duplicate ICameraProvider — avoided here)
    │
    ├─ STEP 2  stop the stock camera.provider@2.7-service-google
    │          setprop ctl.stop vendor.camera-provider-2-7-google
    │          killall -9 android.hardware.camera.provider@2.7-service-google
    │          verify dead for up to 10 retries
    │
    ├─ STEP 3  launch fake HAL
    │          LD_PRELOAD=afbc_encoder.so:gralloc_uv_fix.so setsid \
    │              fake_camera_provider fake_video.mp4 fake_video_front.mp4
    │
    ├─ STEP 4  setprop ctl.restart cameraserver, poll dumpsys media.camera
    │          until it reports 2 devices (max 5 attempts)
    │
    ├─ STEP 5  re-kill stock if init respawned it
    │
    ├─ STEP 6  spawn rotation_bridge.sh daemon
    ├─ STEP 7  spawn fakehal_watchdog.sh daemon
    │
    └─ Apply iptables rules on OUTPUT :9080 for UID 10000-99999
       (block app probing of the stream server, allow the FakeControl UIDs)
```

Crash guard: `service.sh` increments `/data/local/tmp/fakehal_crash_count`. At count ≥ 4 the module aborts before doing anything to the VINTF — the device boots stock. Reset by writing `0` to the file after a successful boot.

---

## License gate flow

```
FakeControl "Start" button
    │
    └─ sh /data/local/tmp/fakehal_gate.sh once
           │
           ├─ KEY   ← cat /data/local/tmp/fakehal_key
           ├─ DEV   ← getprop ro.serialno (fallback: settings get secure android_id)
           ├─ R     ← curl http://<server>:8787/verify?key=KEY&device=DEV
           │
           ├─ store R in /data/local/tmp/fakehal_gate.status
           │
           ├─ if R.valid == true:
           │       (re)start HAL if not running
           │       setprop ctl.restart cameraserver
           │
           └─ if R.valid == false:
                   pkill -9 fake_camera_provider
                   setprop ctl.restart vendor.camera-provider-2-7-google

fakehal_gate.sh loop   # background: same thing every 300 s
```

If the phone has no public route to the server, forward the port over ADB:

```
adb -s <serial> reverse tcp:8787 tcp:8787
# gate script's VPS=... line already uses 127.0.0.1:8787 after flipping to the local path
```

The gate uses curl, falling back to wget, falling back to busybox wget on absolute path (`/data/adb/ksu/bin/busybox`).

---

## Live stream protocol (OBS mode)

`VideoFrameReader` starts a global TCP listener on `fakehal.stream.port` (default `9080`) when `fakehal.stream.camera != off`. One client at a time; HAL takes frames from the socket until the client disconnects, then falls back to the MP4 source.

Wire format (little-endian):

```
magic   char[4]  "FHAL"
width   u32      1..7680
height  u32      1..4320
payload u8[w*h*3/2]   raw NV12 (Y plane then interleaved UV plane)
```

Reference Python relay (OBS → virtual camera → socket) in `scripts/`.

---

## Fixes landed, by category

### Pixel format / layout

- **ONFIDO stripes 1080p (`docs/legacy/ONFIDO_ROOTCAUSE_0719.md` consolidation)**: Onfido allocates a software-usage BLOB stream (`format=0x23`, 640×480) that gralloc returns as a multi-fd handle: Y plane in `fd[0]`, UV plane in `fd[1]`, offset ≈70 MB. The HAL `lockYCbCr` branch wrote UV into `fd[1]`, but Onfido reads the buffer as single-fd contiguous NV12 and looks for UV at `coff = w*h` inside `fd[0]`. Finding only Y → chroma missing → the characteristic cyan/pink vertical stripes.
    - Runtime fix: `setprop fakehal.yuv23_linear 1`. The binary contains a `configureStreams: id=%d 0x23 -> NV21(0x11) LINEAR experiment` path that forces single-fd LINEAR allocation for the 0x23 stream. Not persistent; add it to `fakehal.conf` or to the KSU module's `service.sh`.
    - Permanent fix: patch `GrallocHelper::lockYCbCr` to detect multi-fd handles and either reject the usage (forcing gralloc to reallocate single-fd) or copy UV from `fd[1]` back into `fd[0] + coff` before returning.

- **AFBC + Chrome WebRTC**: Chrome asks for `IMPL_DEFINED` 1920×1080 with GPU usage. gralloc returns an AFBC-compressed buffer. The HAL path uses `EGLBlitter::blitNV12ToBuffer` (in `afbc_encoder.so`) to encode NV12 → AFBC on the GPU. Without `afbc_encoder.so` loaded, Chrome preview is black.

- **GrallocHelper plane layouts**: for Pixel 6/7 gralloc4, `AHardwareBuffer_lock` returns a planar description but `cOff` is sometimes garbage (74 MB-range values). `isPlausibleChromaOffset` sanity-checks the parsed offset against `width * height / 2 .. width * height * 2`; implausible values fall back to the mali-handle-derived `mgCOff = width * height`.

### Color

- **Blue cast after Chrome was in Recent Tasks**: `chromeWebClient_` was computed once during `configureStreams` from `dumpsys window | grep mCurrentFocus`. If Chrome was open at that moment, `chromeWebClient_` stuck to true for the whole session, including when the next client was OpenCamera. OpenCamera wants BT.601 with UV swap; Chrome wants BT.709 without swap. Mixing them produced a `+4.7%` lift on the B channel.
    - Fix (planned in current source, verify in binary): move `detectForegroundPackage()` into `processCaptureRequest()` so the detection happens per-request.

- **PAL color space in the MP4**: `fake_video.mp4` was encoded as `color_space=bt470bg` (PAL). Android `MediaCodec` ignores `bt470bg` and applies BT.709 coefficients, overshooting the blue channel. Re-encode with explicit BT.709:

  ```
  ffmpeg -i input.mp4 -c:v libx264 -crf 18 \
         -color_primaries bt709 -color_trc bt709 \
         -colorspace bt709 -color_range tv \
         fake_video_fixed.mp4
  ```

### Geometry

- **Passport looked squished in preview** (phone 5559, August): the back preview buffer was 1920×1080 landscape, the source video was 720×1280 portrait. HAL stretched the source to fit → aspect distortion. The fix was in content, not code: re-encoded to 1920×1080 SAR 1:1 with the passport composited inside and a soft-blurred background filling the rest. See `gcam_fix/` and the `docs` kept locally during the fix sprint.

- **Still JPEG came out landscape with EXIF=6**: HAL wrote the frame unrotated and only set the EXIF orientation tag. Most browsers and KYC SDKs ignore EXIF orientation and display the pixels as-is → wide passport on a portrait phone. `StillOrientation::bake(…)` now transposes pixels into portrait and writes EXIF=1.

### Google Camera compatibility

- GCam crashed on open with `java.lang.IllegalStateException` deep in `glb.<init>`. Root cause: GCam reads a set of `CameraCharacteristics` keys unconditionally during init, before `openCamera`, and the HAL did not advertise them. `gcam_fix/gcam_fix.patch` adds the missing keys with safe defaults (`FAST`/`OFF` only, `HIGH_QUALITY` where required) and switches the key counter to `sizeof(charKeys)/sizeof(charKeys[0])`.

### App-side issues

- **"FAILED TO OPEN CAMERA / May be in use by another application"** on OpenCamera: the app is set to Camera1 API, which the HAL does not implement. Fix: enable Camera2 API in OpenCamera settings (or run `scripts/set_camera2.sh` to write the pref directly). Camera2 must be reset after every reinstall of OpenCamera.

- **`fakehal.stream.camera` resets to `front` on reboot**: the KSU `service.sh` writes `setprop fakehal.stream.camera both; setprop fakehal.stream.port 0` on boot to pin it.

---

## Anti-detect notes

- `NoiseOverlay` seeds the fixed pattern noise with `FNV-1a(serial)`, so two phones produce different pixel-level fingerprints. Hot/dead pixel positions are also derived from the serial.
- `MetadataRandomizer` runs an Ornstein–Uhlenbeck drift on ISO, exposure, AWB and CCM so the capture result metadata changes between frames the way a real sensor's would.
- `GyroWarp` reads `/sys/bus/iio/devices/iio:deviceN/in_anglvel_{x,y,z}_raw` and warps the frame to match. Liveness detectors that compare phone motion to video motion need to see something.
- `JpegEncoder` writes a hand-crafted EXIF APP1 with a plausible camera model and timestamp.
- `rotation_bridge.sh` swaps the video source when the phone rotates. Without it the video content stays upright regardless of how the phone is oriented, which is a tell.
- `fh_fw` applies iptables rules on `OUTPUT :9080` that block UID 10000-99999 — regular apps probing `localhost:9080` get nothing. Only the FakeControl UIDs are allowed.
- VINTF manifest bind-mount, not file replacement, so a stock boot (kill switch tripped) leaves no on-disk changes to `/vendor`.

---

## Operational playbook

- **Something is wrong**: `tail -200 /data/local/tmp/fakehAL.log` is almost always the first move. The HAL is verbose; most issues print a line.
- **HAL dead**: `pidof fake_camera_provider` empty → `sh /data/local/tmp/fakehal_gate.sh once`. If gate reports `invalid` → fix the key or forward port 8787.
- **cameraserver sees 0 cameras**: the VINTF bind-mount probably failed. `mount | grep vintf` should show two entries; if not, the KSU module did not run. Check `/data/local/tmp/fakehAL_service.log`.
- **Boot loops**: `touch /data/local/tmp/.fakehal_watchdog_off` from recovery or ADB. `echo 1 > /data/local/tmp/fakehal_crash_count` resets the service.sh counter.
- **Buyer deployment**: push the kit, run DEPLOY, write the key, call `fakehal_gate.sh once`. If OpenCamera gives "FAILED TO OPEN": enable Camera2.
- **Rotation off**: `.fakehal_bridge_off` present means the rotation daemon is suppressed. Delete it and `setsid sh /data/local/tmp/rotation_bridge.sh &`.

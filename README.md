# FakeHAL

**A full replacement for the Android camera stack that feeds a video file or a live stream into every camera-using app on the phone as if it came from the real sensor.** Browsers, native camera apps, KYC/liveness SDKs, video-call apps — all of them see whatever you decide to show them, at the lowest possible layer, through the standard Camera2 pipeline.

> **Origin.** This software was leaked by a single hacker from a dark-web forum. The forum will not be named. The leak is a private release — source, binaries, server, deployment kit, and operator app, everything that was previously only in the hands of paying customers. It is now public.

Released free. Keep it, sell it, extend it, fork it. No support promised. Questions go in [GitHub Issues](https://github.com/crachcrypto/fake_hal/issues) in English.

Known-good device: **Google Pixel 7 (panther), stock Android 13, bootloader unlocked, KernelSU + SUSFS**. The same tree builds for Pixel 4 / 4XL / 7 Pro / 7a with no changes; Pixel 6 is expected to work (gralloc4); Pixel 8 is unverified. Any Pixel-class device with a working KernelSU install and the AOSP sources from the matching tag should be adaptable.

---

## Table of contents

1. [What this project is](#what-this-project-is)
2. [What is in the repository and where each file came from](#what-is-in-the-repository-and-where-each-file-came-from)
3. [How the stack works end-to-end](#how-the-stack-works-end-to-end)
4. [Why OpenCamera is the recommended front-end](#why-opencamera-is-the-recommended-front-end)
5. [Deployment to a phone](#deployment-to-a-phone)
6. [OBS live-stream workflow](#obs-live-stream-workflow)
7. [Runtime configuration (fakehal.conf)](#runtime-configuration-fakehalconf)
8. [Building from source](#building-from-source)
9. [Extending the software](#extending-the-software)
10. [Licensing server](#licensing-server)
11. [Known issues and fixes](#known-issues-and-fixes)
12. [Troubleshooting](#troubleshooting)
13. [License](#license)

---

## What this project is

FakeHAL is an Android **Camera HAL (Hardware Abstraction Layer) provider**, implemented against the AIDL `ICameraProvider/internal/0` interface. It registers itself as the system's camera provider in place of the vendor's stock implementation, so when any app on the phone calls `openCamera()` through the public Camera2 API, the frames they receive are not from the real sensor — they are from an MP4 file on disk or from a live TCP stream pushed to the phone.

Because the swap happens inside `cameraserver`, nothing above it needs to be modified:

- The system **Camera** app records a video from your fake source.
- **Chrome's `getUserMedia`** returns the fake stream to any website.
- **KYC / liveness SDKs** (Onfido, Jumio, Veriff, Sumsub, Smile ID, document scanners embedded in neobanks and crypto exchanges) that go through the standard `Camera2` or `CameraX` APIs — which is all of them, because iOS/Android app stores reject apps that touch the raw sensor — see the fake frames.
- **Video-call apps** (WhatsApp, Telegram, Zoom, Google Meet) show the fake video to the other side.
- **Google Camera** works too, after the `gcam_fix` characteristics patch is applied.

The provider also simulates sensor-level realism so detectors looking for a "too clean" source cannot distinguish it from a real CMOS output: physical noise fingerprint keyed to the phone's serial number, gyroscope-driven image warping, rolling-shutter row skew, lens shading, and an Ornstein-Uhlenbeck drift on ISO / exposure / white balance / color-correction metadata between frames.

---

## What is in the repository and where each file came from

Everything in this repository was pulled off a working Pixel 7 running a production build of the software, plus the server/bot infrastructure that was used to deliver and license it. The provenance of each directory:

### `hal/` — C++ source for the HAL binary

The source tree that produces `fake_camera_provider`, the vendor binary at the heart of the system. Dumped from `/root/fake_hal_aidl/` on the build server, cleaned of local build artifacts.

- `src/*.cpp` — the C++ implementation files (10 modules).
- `include/*.h` — their headers.
- `Android.bp` — Soong build file for in-tree AOSP builds.
- `CMakeLists.txt`, `Makefile` — out-of-tree syntax check / unit test build.
- `fake_camera_hal.rc` — Android init `.rc` fragment that defines the `fake_camera_hal` service.
- `fake_camera_hal.xml` — VINTF HAL manifest fragment declaring `ICameraProvider/internal/0`.
- `ksu_module/` — the KernelSU module skeleton (`module.prop`, `service.sh`, `customize.sh`) that handles the boot sequence.
- `sepolicy/fake_hal.te` — SELinux policy giving the HAL access to `shell_data_file`, `sysfs_iio`, etc.
- `vendor_overlay/` — files that end up under `/vendor/` through KSU's magic mount.
- `tests/*.cpp` — gtest unit tests for the processing nodes.

### `hal_src_may14/` — older reference source

A snapshot taken on 2026-05-14, kept because it is the last version where the source and the shipping binary were in sync. Useful for diffing when something regressed. Do not build from this.

### `gcam_fix/` — Google Camera compatibility patch

Google Camera reads a long list of `CameraCharacteristics` keys at startup, before `openCamera`, and crashes if any are missing. The patch adds the missing keys (`EDGE_AVAILABLE_EDGE_MODES`, `HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES`, `TONEMAP_*`, `SHADING_*`, `STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES`, `ANDROID_CONTROL_AE_LOCK_AVAILABLE`, `ANDROID_CONTROL_AWB_LOCK_AVAILABLE`, …) with safe `OFF` / `FAST` defaults. Includes the patched `FakeCameraDevice.cpp.fixed` for reference and `deploy_gcam.sh` which pushes the built binary.

### `apks/` — compiled Android apps

- `fakecontrol/fakecontrol-v7.7.2-licensed.apk` — Jetpack Compose app, package `com.fakehal.controller`. The operator's control panel: pick back / front camera, swap A/B video slots, import video or image, apply ISO / noise / mirror / rotation controls, trigger the GPS spoof foreground service, inspect root availability, bind the device to a license key. **This is the APK you install on the phone.**
- `fakecontrol/fakecontrol-v7.7.2.apk` — same app without the license lock, included for reference.
- `spoofkit/SpoofKit_xposed_v1.0.0.apk` — an LSPosed/Xposed module (`com.spoofkit.xposed`) that hooks system properties, Chrome user-agent, SafetyNet / Play Integrity probes, and Camera2 glue to spoof things the HAL cannot touch from below the Android framework.
- `fakehal_app/FakeHALApp-{normal,stealth}-debug.apk` — simple launcher / status reporter builds.

### `binaries/` — prebuilt native code

- `hal/fake_camera_provider-v7.8-gcam` — 200 KB, the current production HAL binary with the GCam fix applied. Built against AOSP android-13.0.0_r83.
- `hal/fake_camera_provider-v7.7.2` — 354 KB, the previous version. Older symbols useful for debugging.
- `libs/afbc_encoder.so` — `LD_PRELOAD` helper that GPU-encodes NV12 frames into AFBC-compressed buffers for Chrome WebRTC (which asks for `IMPL_DEFINED` + GPU usage, so gralloc hands it an AFBC buffer).
- `libs/gralloc_uv_fix.so` — `LD_PRELOAD` helper that handles multi-fd gralloc layouts correctly (the fix for the Onfido 1080p vertical-stripes bug).

### `decompiled/` — Jadx output of the apps

Full Java/smali decompile of FakeControl (`fakecontrol_src/`) and SpoofKit (`spoofkit_src/`). This is reference material only — the original app sources are not in this repo. You can read these to understand what the operator app does, then rebuild your own control UI in anything you want (React Native, Flutter, native Kotlin).

### `scripts/` — ~60 shell / Python scripts

Everything the build server used to deploy, patch, verify, and rebuild. Highlights:

- `fakehal_gate.sh` — the on-device license watchdog. Reads `/data/local/tmp/fakehal_key`, hits the verify server, kills the HAL if the key is invalid or expired.
- `rotation_bridge.sh` — a daemon that watches system UI rotation events and swaps the active MP4 to the pre-rendered rotated variant (`fake_video_rot0.mp4`, `fake_video_rot90.mp4`, …) so the on-screen content stays visually correct when the user rotates the phone. Without this, liveness detectors flag the video as static.
- `fakehal_watchdog.sh` — anti-bootloop watchdog with HAL / cameraserver restart budgets and a kill-switch marker file.
- `rotation_bridge.sh`, `service_v772.sh`, `run_fakehal.sh`, `restart_hal_afbc.sh`, `deploy_back.sh`, `deploy_front.sh` — the deployment and operation glue.
- `patch_fakecontrol.py` — smali patcher that injects `LicenseWatchdog` into `GpsSpoofService.onCreate`. The watchdog class source is `LicenseWatchdog.smali`.
- `patch_spoofkit.py` — smali patcher that wraps every `before/afterHookedMethod` in `com.spoofkit.xposed` to check license validity before executing the hook.
- `vbmeta_patch.sh` — one-time patch of the `vbmeta` partition to disable rollback / hash-tree verification so the modified `/vendor` passes AVB. Runs from fastboot.
- `set_camera2.sh` — forces OpenCamera to use the Camera2 API after a reinstall.
- `fh_fw.sh` — iptables rules that block regular app UIDs from probing the stream server on `localhost:9080`.
- `install_oc.sh`, `reinst_oc.sh` — OpenCamera 1.49.1 installer with verifier bypass.

### `kit/` — deployment payload for a phone

What the sales bot used to ship to a buyer. One `tar` to extract into `/data/local/tmp/` + a copy of the KernelSU module to flash. Contents:

- `payload/fake_camera_provider` — the HAL binary.
- `payload/afbc_encoder.so`, `payload/gralloc_uv_fix.so` — `LD_PRELOAD` helpers.
- `payload/fakehal.conf` — a known-good runtime configuration.
- `payload/fake_camera_hal.xml` — VINTF fragment.
- `payload/fakehal_gate.sh` — license watchdog.
- `payload/DEPLOY.sh` — a one-shot installer: lays files into the right paths, sets SELinux contexts, creates `module.prop`.
- `MANIFEST.md5` — checksums for everything in the payload.
- `VERSION.txt` — the build label.
- `verify_server.py` — a copy of the server-side verify endpoint (included for reference; the actual server is in `server/`).

### `configs/` — sample runtime configuration

- `fakehal.conf` — complete `fakehal.conf` with all keys and tested defaults.
- `fake_camera_hal.xml` — the VINTF HAL manifest to bind-mount over the stock one.

### `server/` — licensing server (optional)

If you want to charge for your builds, this is the server side. Two Python processes:

- `main.py` — FastAPI admin API on port 7070. Create keys, revoke keys, list keys, issue JWTs to devices.
- `verify_server.py` — a 70-line HTTP endpoint on port 8787. Read-only. The on-device `fakehal_gate.sh` hits this to check whether the key is still valid.
- `models.py` — Pydantic request/response schemas.
- `config.py` — reads `ADMIN_TOKEN`, `JWT_SECRET`, `PORT`, `HOST` from environment variables.
- `.env.example` — fill in your secrets.
- `start.sh`, `stop.sh` — launcher scripts.

**Skip this directory entirely if you are deploying for yourself only** and do not need a license gate — delete `fakehal_gate.sh` from the payload and the HAL will just keep running without network access.

### `docs/`

- `OVERVIEW.md` — consolidated engineering notes: boot sequence, license gate flow, stream protocol, fixes landed, operational playbook.

---

## How the stack works end-to-end

```
                ┌──────────────────────────────── Pixel 7 ─────────────────────────┐
                │                                                                  │ 
                │  KernelSU late_start boot hook                                   │   
                │    │                                                             │
                │    ▼                                                             │
                │  service.sh                                                      │
                │    - bind-mount empty VINTF over the stock Lyric manifest        │
                │    - kill vendor.camera-provider-2-7-google                      │
                │    - launch fake_camera_provider (LD_PRELOAD both helpers)       │
                │    - setprop ctl.restart cameraserver                            │
                │    - start rotation_bridge.sh and fakehal_watchdog.sh            │
                │                                                                  │
                │  fake_camera_provider  (AIDL vendor HAL)                         │
                │    - registers ICameraProvider/internal/0                        │
                │    - advertises 2 cameras: internal/0 (back), internal/1 (front) │
                │    - reads fakehal.conf on every capture                         │
                │    - frames come from one of three sources:                      │
                │                                                                  │
                │        (a) MP4 loop         (b) A/B slot swap   (c) TCP stream   │
                │        /data/local/tmp/     active_slot=A|B     port 9080        │
                │        fake_video.mp4                           "FHAL" + NV12    │
                │                                                                  │
                │    - per-frame pipeline: decode → randomize metadata →           │
                │      add noise + FPN → vignette → rolling-shutter row skew →     │
                │      gyro warp → write to gralloc buffer (NV12/NV21/AFBC)        │
                │                                                                  │
                │  cameraserver → every app that opens the camera                  │
                │                                                                  │
                │  OpenCamera / Chrome / KYC SDK / video-call app → shows fake     │
                └──────────────────────────────────────────────────────────────────┘
```

### The VINTF hijack

Android's camera stack discovers which HAL to talk to through the **VINTF manifest** at `/vendor/etc/vintf/manifest/`. On a stock Pixel 7 this points at the Lyric provider under `/apex/com.google.pixel.camera.hal/`. On a FakeHAL phone, the KSU module `service.sh` does two things at boot:

1. Overlays our `fake_camera_hal.xml` into the manifest directory through KSU's magic-mount system.
2. Bind-mounts an **empty** manifest over the stock Lyric manifest to remove the duplicate `ICameraProvider/internal/0` declaration. (Both the stock manifest and our overlay can't both declare the same interface — cameraserver crashes.)

Then it stops `vendor.camera-provider-2-7-google` and launches our binary. From cameraserver's point of view, the only `ICameraProvider/internal/0` service in the system is ours.

### The frame pipeline

Each time an app requests a frame (`processCaptureRequest`), the HAL:

1. **Pulls a source frame** — decoded from MP4 by `VideoFrameReader` (NDK `MediaCodec`), or taken from the TCP stream buffer, or from the A/B slot.
2. **Converts color format** — the MP4 decoder yields `COLOR_FormatYUV420SemiPlanar` (NV12) or `COLOR_FormatYUV420Planar` (I420). `yuv::repackNV21toNV12` and `yuv23::convertNV21toNV12_Bt601` normalize everything.
3. **Rotates and mirrors** per the client — `preview_rotation`, `back_rotate_180`, `front_rotate_180`, `front_mirror`, and the Chrome-specific overrides. For JPEG still-capture, rotation is baked into the pixel data (not just the EXIF tag), because browsers and KYC viewers ignore EXIF orientation.
4. **Randomizes the capture-result metadata** — `MetadataRandomizer` drifts ISO, exposure time, AWB gains, and the color correction matrix using an Ornstein-Uhlenbeck process so no two consecutive frames have identical metadata, which is a tell for static-image substitution.
5. **Adds physical noise** — `NoiseOverlay` adds Gaussian photon shot noise plus a Fixed Pattern Noise pattern seeded by `FNV-1a(serial_number)`, so each device fingerprints uniquely. Also sprinkles a few hot and dead pixels at serial-derived positions.
6. **Applies lens shading** — `LensShading` darkens the corners with a `cos⁴` falloff, intensity 0.4 by default.
7. **Rolling shutter** — `RollingShutter` offsets each row by `(y / height) * readout_time`, default 33 ms. Produces the shear you get when panning a real sensor.
8. **Gyro warp** — `GyroWarp` reads `/sys/bus/iio/devices/iio:deviceN/in_anglvel_{x,y,z}_raw`, converts to a per-row affine transform, and warps the frame so when the user moves the phone, the image moves. Liveness detectors that correlate phone motion with image motion need to see something.
9. **Writes to the output gralloc buffer** — `GrallocHelper` handles the three cases:
   - **Single-fd NV12 / NV21** — direct `AHardwareBuffer_lock` + `memcpy`.
   - **Multi-fd split** — Y in `fd[0]`, UV in `fd[1]`. The HAL writes UV into `fd[1]`.
   - **AFBC** (Chrome WebRTC) — `EGLBlitter::blitNV12ToBuffer` from `afbc_encoder.so` encodes NV12 → AFBC on the GPU using an EGL context.
10. **Returns `status=0`** and the client gets its frame.

### Still capture

When the app requests a JPEG, `JpegEncoder` runs the same pipeline into a staging NV21 buffer, transposes it into portrait via `StillOrientation::bake` (so the pixels themselves are portrait, not just the EXIF tag), and encodes with libjpeg-turbo plus a hand-written EXIF APP1 segment carrying a plausible camera model, make, timestamp, GPS, orientation `=1`.

---

## Why OpenCamera is the recommended front-end

**The HAL swap is invisible to apps, so it does not matter which camera app or browser the KYC flow actually opens.** OpenCamera, GCam, the stock Camera app, Chrome's `getUserMedia`, the WebView embedded in a KYC provider's native SDK — they all read from the same `cameraserver`, and `cameraserver` reads from FakeHAL. From the verifier's point of view they are indistinguishable: all it sees is a frame stream over Camera2.

So why specifically **OpenCamera 1.49.1**?

1. **It uses Camera2 cleanly.** No vendor-specific camera-service hacks like GCam, no WebRTC quirks like Chrome, no DRM / integrity checks like the stock Camera. Just open the camera, request a preview and a still output, read frames. That matches exactly what the HAL knows how to serve.
2. **It is scriptable from the shell.** You can start it (`am start -n net.sourceforge.opencamera/.MainActivity`), take a photo (`input keyevent KEYCODE_CAMERA`), pull the file. The license watchdog and the operator panel use that.
3. **Its preferences are a plain XML in `/data/data/...` — editable by root.** `set_camera2.sh` writes `preference_camera_api=preference_camera_api_camera2` directly, no UI needed.
4. **It is open source and does not phone home.** It will not update itself behind your back and change the Camera API default to Camera1 (which the HAL does not implement).
5. **It respects `ro.product.model` for the EXIF make/model string** on captured images, so the fake JPEGs look consistent with what the HAL already fakes in metadata.

**For running web-based KYC**, you don't open OpenCamera — you open Chrome (or whatever the service points you at), click "allow camera", and go. The HAL still serves the same frames into Chrome's WebRTC pipeline. OpenCamera is just the operator's debug / preview window: start it on the side to see exactly what the HAL is currently outputting, confirm the geometry is right, snap a still to inspect metadata. Then switch to the verifier's page and the stream the verifier sees is the stream you just previewed. **No code path on the HAL cares which app is foreground except for one line that detects Chrome to pick BT.709 vs BT.601 color conversion.**

If a specific verifier insists on a native app that opens the camera itself (some banks do this), you still don't need to touch anything — install the bank's APK, open it, point its camera at nothing, and it reads your `fake_video.mp4` because that's what cameraserver serves.

---

## Deployment to a phone

Prerequisites on the phone (one time):

1. **Unlock the bootloader.** `fastboot flashing unlock`. This wipes the phone — do it first.
2. **Install KernelSU.** Follow the current instructions at kernelsu.org for your device.
3. **Install SUSFS.** The KernelSU patch that hides bind-mounts from `/proc/mounts` and `cat /proc/pid/maps` so apps probing for root (that includes most KYC SDKs) cannot see the overlay.
4. **Patch vbmeta.** `scripts/vbmeta_patch.sh` from fastboot — disables rollback and hash-tree verification so the modified `/vendor` boots.
5. **Reboot into the system.**

Prerequisites on the host PC:

- `adb` (Android Platform Tools).
- Phone connected over USB with "USB debugging" enabled in Developer Options.
- Confirmed authorized: `adb devices` lists the phone as `device`, not `unauthorized`.

### Push the payload

```
# 1. Copy the deployment kit onto the phone's shared storage.
adb push kit/payload /sdcard/fakehal_ready

# 2. Run the installer as root. It lays files into /data/local/tmp,
#    sets SELinux contexts, and creates the KernelSU module stub
#    at /data/adb/modules/FakeHAL/.
adb shell su -c 'sh /sdcard/fakehal_ready/DEPLOY.sh'

# 3. Install the operator app.
adb install -r apks/fakecontrol/fakecontrol-v7.7.2-licensed.apk

# 4. (Optional) install LSPosed and the SpoofKit module if you need
#    Chrome / GMS user-agent and property spoofing on top of HAL frame
#    substitution. If you only ever use OpenCamera and the HAL frames
#    are enough, skip this.
adb install -r apks/spoofkit/SpoofKit_xposed_v1.0.0.apk

# 5. Install OpenCamera 1.49.1 (bundled in the kit; or get the APK
#    from F-Droid).
adb shell su -c 'sh /data/local/tmp/reinst_oc.sh'
# Force Camera2 API — mandatory, otherwise OpenCamera will show
# "FAILED TO OPEN CAMERA: May be in use by another application".
adb shell su -c 'am force-stop net.sourceforge.opencamera; sh /data/local/tmp/set_camera2.sh'

# 6. Point the HAL to the video you actually want to inject.
adb push my_fake_video.mp4       /sdcard/
adb shell su -c 'cp /sdcard/my_fake_video.mp4 /data/local/tmp/fake_video.mp4'
# Front camera, if different:
adb push my_fake_video_front.mp4 /sdcard/
adb shell su -c 'cp /sdcard/my_fake_video_front.mp4 /data/local/tmp/fake_video_front.mp4'
```

### Video requirements

MP4, H.264 baseline profile, `yuv420p` pixel format, no audio, `+faststart`:

```
ffmpeg -i source.mp4 \
    -c:v libx264 -profile:v baseline -level 3.1 -pix_fmt yuv420p \
    -color_primaries bt709 -color_trc bt709 -colorspace bt709 \
    -vf "scale=1920:1080,fps=30" \
    -b:v 8M -maxrate 10M -bufsize 16M \
    -movflags +faststart -an \
    -y fake_video.mp4
```

Match the resolution to the preview buffer size the apps request (for Pixel 7 that is 1920×1080 for back preview, 1440×1080 for front). Mismatched aspect ratios produce the squished-passport effect.

### Start the HAL

If `fakehal_gate.sh` is in place (license mode), write a key and run it once:

```
adb shell su -c 'echo FH-YOUR-KEY > /data/local/tmp/fakehal_key'
adb shell su -c 'sh /data/local/tmp/fakehal_gate.sh once'
```

If you removed `fakehal_gate.sh` (unlicensed mode), start the binary directly:

```
adb shell su -c '
    LD_PRELOAD=/data/local/tmp/afbc_encoder.so:/data/local/tmp/gralloc_uv_fix.so \
    setsid /data/local/tmp/fake_camera_provider \
           /data/local/tmp/fake_video.mp4 \
           /data/local/tmp/fake_video_front.mp4 \
           </dev/null >>/data/local/tmp/fakehAL.log 2>&1 &
    setprop ctl.restart cameraserver'
```

Verify:

```
adb shell su -c 'pidof fake_camera_provider'          # expect a PID
adb shell su -c 'dumpsys media.camera | grep "Number of camera devices:"'
# expect: Number of camera devices: 2
```

Open OpenCamera. You should see your fake video playing in the preview.

---

## OBS live-stream workflow

Instead of pushing a pre-recorded MP4, you can drive the camera **live from OBS Studio on a PC**. The HAL opens a TCP server on `localhost:9080` and accepts framed packets with a tiny header followed by raw NV12 pixel data. While a client is connected, the HAL takes frames from the socket instead of from the MP4.

This is the normal mode for the operator-driven workflow: an operator in OBS composes the scene (document scan, selfie, animated overlays, text, document flips), the verifier on the phone's screen sees exactly that stream, and the operator can react to prompts in real time.

### Wire format

Little-endian:

```
┌────────────────┬────────┬──────────────┐
│ magic "FHAL"   │ u32 w  │ u32 h        │  12 bytes
└────────────────┴────────┴──────────────┘
┌────────────────────────────────────────┐
│ raw NV12 bytes: w*h Y plane,           │
│ then interleaved UV plane (w*h/2)      │  w*h*3/2 bytes
└────────────────────────────────────────┘
```

Repeat the whole packet (header + payload) for every frame. The HAL double-buffers internally and does not require any precise timing — frames are consumed when the next capture-request lands. 15 to 30 fps works fine.

### Reference setup

**On the PC:**

1. Install [OBS Studio](https://obsproject.com/).
2. Create a scene. Add whatever sources you want: a cropped webcam view, images of documents, animated overlays, text, Browser Source showing a server-driven template. Resize the canvas to **1920×1080** to match the back camera buffer, or **1440×1080** for front.
3. **Start the Virtual Camera** (OBS → "Start Virtual Camera" in the main window). This exposes OBS's output as a system virtual webcam.
4. Run a short Python relay that reads the virtual camera, converts BGR → NV12, prefixes the header, and ships over TCP to `localhost:9080` (which is forwarded to the phone — see below). A minimal relay (place it anywhere; depends on `opencv-python` and `numpy`):

```python
import cv2, socket, struct

W, H = 1920, 1080
# Pick the OBS Virtual Camera index. On Windows it is usually the last
# physical camera index; on Linux it is /dev/videoN where N depends on
# the v4l2loopback module. Enumerate with `cv2.VideoCapture(i)` or
# `v4l2-ctl --list-devices`.
cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH,  W)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, H)

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(("127.0.0.1", 9080))

try:
    while True:
        ok, frame = cap.read()
        if not ok:
            continue
        # BGR → YUV420 → NV12
        yuv = cv2.cvtColor(frame, cv2.COLOR_BGR2YUV_I420)
        y   = yuv[:H].tobytes()
        u   = yuv[H:H+H//4].reshape(H//2, W//2)
        v   = yuv[H+H//4:].reshape(H//2, W//2)
        uv  = cv2.merge((u, v)).tobytes()
        header = b"FHAL" + struct.pack("<II", W, H)
        sock.sendall(header + y + uv)
finally:
    sock.close()
    cap.release()
```

**On the phone side:**

```
# Reverse the TCP port so the PC's connect-to-localhost reaches the HAL.
adb reverse tcp:9080 tcp:9080

# Turn on streaming in the HAL (it defaults to MP4-only).
adb shell su -c 'setprop fakehal.stream.port 9080'
adb shell su -c 'setprop fakehal.stream.camera both'
# Restart HAL so the stream server picks up the new props.
adb shell su -c 'sh /data/local/tmp/fakehal_gate.sh once'
```

**Run the relay on the PC.** As soon as it connects, the HAL switches from MP4 to the live stream. Open OpenCamera on the phone to preview, open the KYC flow in Chrome, point and shoot. When the relay disconnects, the HAL falls back to the MP4.

### Why this is powerful

- A document is **moved and tilted live** so motion parallax matches a real hand.
- A face can **react in real time** to the verifier's prompts ("blink", "turn head left") — the operator does the blink/turn in OBS, phone sees it instantly.
- No pre-rendering per verifier required.
- Scenes can be prepared ahead for different document types and switched with hotkeys in OBS.

### Keeping it low-latency

- Keep the OBS scene at 30 fps and the capture at 30 fps.
- Match canvas resolution to the camera buffer (`1920×1080` back, `1440×1080` front). If OBS is bigger, scale down in the OBS output filter — do not let OpenCV do the resize.
- Prefer a wired USB connection with `adb reverse` over Wi-Fi `adb tcpip`. USB latency is ~5 ms, Wi-Fi can be 50 ms+.

---

## Runtime configuration (`fakehal.conf`)

Lives at `/data/local/tmp/fakehal.conf`. The HAL re-reads it on every capture, so changes take effect without restarting the binary.

```
enabled                    = 1
back_video                 = /data/local/tmp/fake_video.mp4
front_video                = /data/local/tmp/fake_video_front.mp4
back_slot_a                = /data/local/tmp/fake_video.slot_a.mp4
back_slot_b                = /data/local/tmp/fake_video.slot_b.mp4
active_slot                = A              # A | B — operator-selectable
qr_mode                    = 1              # drops noise so QR codes stay readable
noise_level                = 0.0            # 0..3 CMOS noise strength
iso_min                    = 100
iso_max                    = 800
back_rotate_180            = 0
front_rotate_180           = 1
front_mirror               = 1
chrome_front_rotate_180    = 0              # Chrome-only override
chrome_front_mirror        = 0
preview_rotation           = 0              # 0 | 90 | 180 | 270
preview_mirror_h           = 0
preview_mirror_v           = 0
sensor_orientation         = 90
gyro_enabled               = 1
gyro_strength              = 0.1
stream_camera              = both           # back | front | both
stream_postfx              = 1              # apply noise/gyro/vignette to stream frames
auto_rotate_portrait       = 0
force_chrome_mode          = 0              # force the Chrome BT.709 branch
pre_normalized             = 0
```

System properties (`setprop`) also matter:

- `fakehal.stream.port` — TCP port for the stream server (default `9080`, set `0` to disable).
- `fakehal.stream.camera` — which cameras should pull from the stream when a client is connected (`back`, `front`, `both`, `off`).
- `fakehal.yuv23_linear` — set to `1` if any app ships vertical stripes (forces single-fd LINEAR allocation for the 0x23 BLOB stream).
- `vendor.fakehal.stillrot` — orientation to bake into stills.

---

## Building from source

### HAL binary (AOSP in-tree)

You need a full AOSP checkout at `android-13.0.0_r83` or later. 120 GB of disk, 64 GB RAM, several hours of first `repo sync`.

```
# One-time
mkdir -p ~/aosp && cd ~/aosp
repo init -u https://android.googlesource.com/platform/manifest -b android-13.0.0_r83
repo sync -c -j$(nproc)

# Place the source in vendor/
cp -r <this-repo>/hal $AOSP/vendor/fake_hal
cd $AOSP

# Build
source build/envsetup.sh
lunch aosp_cf_arm64_phone-userdebug      # or aosp_panther-userdebug for a Pixel 7 target
m android.hardware.camera.provider-fake -j$(nproc)

# Output
find out -name android.hardware.camera.provider-fake -newer /tmp
# → out/target/product/<device>/vendor/bin/hw/android.hardware.camera.provider-fake
```

### HAL binary (standalone, syntax check only)

For editing with a quick build loop you can type-check (not produce a device-ready binary) with just the NDK:

```
export NDK_ROOT=$HOME/Android/Sdk/ndk/26.1.10909125
STANDALONE=1 ./scripts/build.sh
```

### Unit tests (CMake)

```
cd hal/tests
cmake -B build -DCMAKE_BUILD_TYPE=Debug
cmake --build build -j$(nproc)
./build/run_tests
```

Tests cover `MetadataRandomizer`, `NoiseOverlay`, `GyroWarp`, `RollingShutter`, `JpegEncoder`, `GrallocHelper`, and end-to-end pipeline integration with mocked Android headers (`tests/mocks/`).

### KernelSU module

```
cd hal/ksu_module
# Pack as a KSU-flashable zip
zip -r ../../FakeHAL-ksu.zip .
```

Flash with KernelSU Manager → Modules → "Install from storage".

---

## Extending the software

The HAL is intentionally simple: one C++ class per processing stage, no dependency injection framework, no code generation beyond `Android.bp`. Add a new effect in half a day.

### Add a new per-frame processing node

1. Create `hal/include/MyEffect.h` and `hal/src/MyEffect.cpp` following the shape of `NoiseOverlay`:
    ```cpp
    namespace fake_hal {
    class MyEffect {
      public:
        void apply(uint8_t* nv21, int w, int h);
    };
    }
    ```
2. Add the file to `hal/Android.bp` under `srcs:` of both `android.hardware.camera.provider-fake` and `libfake_hal_core`.
3. Instantiate it in `FakeCameraDevice` and call `apply` after `NoiseOverlay::apply` in the frame loop (`FakeCameraDevice.cpp`, inside `processCaptureRequest`).
4. Add a config key in `RuntimeConfig` + the `fakehal.conf` parser block if you want it to be runtime-tunable.
5. Write a unit test in `hal/tests/test_my_effect.cpp`.
6. Rebuild, push the new binary with `scripts/deploy_back.sh`, restart via `fakehal_gate.sh once`.

### Add a new source (RTMP, WebRTC, custom protocol)

Look at `VideoFrameReader::startGlobalStreamServer` and `g_handleClient`. The TCP+FHAL protocol is 40 lines. Adding RTMP ingest is a matter of swapping `g_handleClient` with an FFmpeg demuxer that fills the same `g_streamBuf`.

### Port to a different device

The device-specific pieces are:

- `fake_camera_hal.rc` — service name, user, group, SELinux context.
- `fake_camera_hal.xml` — HAL FQName. Match the stock provider's declaration.
- `FakeCameraProvider::kCameraIds` in `FakeCameraProvider.cpp` — the camera IDs the stock provider exposes.
- `buildPixel7MainCharacteristics()` and `buildPixel7FrontCharacteristics()` in `FakeCameraDevice.cpp` — rewrite these to match the device's real `dumpsys media.camera` output. Sensor array size, aperture, focal length, lens shading, available stream configurations, etc.
- `ksu_module/service.sh` — `STOCK_SERVICE` and `STOCK_BINARY` names, VINTF manifest path.
- `sepolicy/fake_hal.te` — if the device has stricter SELinux rules.

### Fork the operator app

The decompiled Jadx tree under `decompiled/fakecontrol_src/` is readable Java. The architecture is standard Jetpack Compose + MVVM, so you can port it to Flutter / React Native / native Kotlin without much pain. The one thing you must preserve is: the UI's "Start" button needs to call `sh /data/local/tmp/fakehal_gate.sh once` (through Shizuku or a root shell).

---

## Licensing server

Optional. If you plan to sell builds, deploy `server/` on a VPS:

```
sudo apt install python3 python3-venv
cd server
python3 -m venv venv && . venv/bin/activate
pip install fastapi uvicorn python-jose pydantic
cp .env.example .env
# Edit .env — set long random ADMIN_TOKEN and JWT_SECRET.
```

Systemd units (not included — write your own; the server was originally driven by two units called `fakehal-bot.service` and `fakehal-verify.service`):

```
# /etc/systemd/system/fakehal-verify.service
[Unit]
Description=FakeHAL license verify endpoint
After=network.target

[Service]
Type=simple
WorkingDirectory=/opt/fakehal/server
EnvironmentFile=/opt/fakehal/server/.env
ExecStart=/usr/bin/python3 /opt/fakehal/server/verify_server.py
Restart=always

[Install]
WantedBy=multi-user.target
```

```
# /etc/systemd/system/fakehal-admin.service
[Unit]
Description=FakeHAL admin REST
After=network.target

[Service]
Type=simple
WorkingDirectory=/opt/fakehal/server
EnvironmentFile=/opt/fakehal/server/.env
ExecStart=/opt/fakehal/server/venv/bin/uvicorn main:app --host 0.0.0.0 --port 7070
Restart=always

[Install]
WantedBy=multi-user.target
```

Open port 8787 to the Internet (verify endpoint is read-only). **Do not** open port 7070 — admin API is key-protected but has no rate limiting. Reach it over an SSH tunnel.

Keys are stored in `server/keys.json`, which is created on first write.

Create a key:

```
curl -X POST http://localhost:7070/api/key/create \
     -H "X-Admin-Token: <ADMIN_TOKEN>" \
     -H "Content-Type: application/json" \
     -d '{"description":"customer A","max_devices":1,"expires_days":60}'
# → {"success":true,"key":"<generated>","expires_at":"..."}
```

Deactivate a key:

```
curl -X DELETE http://localhost:7070/api/key/<KEY> \
     -H "X-Admin-Token: <ADMIN_TOKEN>"
```

The phone's `fakehal_gate.sh` hits `GET /verify?key=<key>&device=<serial>` every 5 minutes. If `valid=false`, the gate `pkill -9 fake_camera_provider` and refuses to start it again until a working key is in `/data/local/tmp/fakehal_key`.

Point `fakehal_gate.sh` at your server by editing one line:

```
VPS="your.server.example.com:8787"
```

---

## Known issues and fixes

| id | symptom | root cause | fix |
|----|---------|------------|-----|
| 1 | Vertical cyan / pink stripes in Onfido 1080p | multi-fd gralloc split; UV lands in `fd[1]`, consumer reads single-fd NV12 at `fd[0]+coff` | `setprop fakehal.yuv23_linear 1` |
| 2 | Blue cast in OpenCamera after Chrome was recent | one-shot Chrome detection stuck between sessions | detect foreground per `processCaptureRequest`, not per `configureStreams` (fix in current source) |
| 3 | Passport looks squished | video aspect ≠ preview buffer aspect | re-encode video at `1920×1080 SAR 1:1` |
| 4 | GCam crashes on open | missing `CameraCharacteristics` keys | apply `gcam_fix/gcam_fix.patch` |
| 5 | "FAILED TO OPEN CAMERA" in OpenCamera | app is set to Camera1 API | run `scripts/set_camera2.sh` after every OpenCamera install |
| 6 | Fake stream content stays upright when phone rotates | rotation daemon not running | start `rotation_bridge.sh` and provide `fake_video_rot{0,90,180,270}.mp4` |
| 7 | HAL dies at boot but device boots | VINTF conflict (both stock and fake declare `ICameraProvider/internal/0`) | KSU `service.sh` bind-mounts an empty manifest over the stock one — ensure it ran |

Full write-up in `docs/OVERVIEW.md`.

---

## Troubleshooting

**Nothing happens when I open the camera app.**

```
adb shell su -c 'pidof fake_camera_provider'
adb shell su -c 'tail -40 /data/local/tmp/fakehAL.log'
```

If `pidof` is empty, run `sh /data/local/tmp/fakehal_gate.sh once` and tail the log again. If the gate reports `invalid`, your key is wrong or the server is unreachable.

**`dumpsys media.camera` says 0 cameras.**

The VINTF bind-mount probably did not run. Check `/data/local/tmp/fakehAL_service.log` for `--- STEP 1: VINTF bind-mount ---` and whether it succeeded. If not, `mount | grep manifest` and compare against expected.

**Stripes / artifacts in a specific KYC app.**

Save a capture:

```
adb shell su -c '
    grep -A5 "configureStreams\|lockYCbCr" /data/local/tmp/fakehAL.log |
    tail -200' > kyc.log
```

Open an [issue](https://github.com/crachcrypto/fake_hal/issues) with the log attached, the device model, the KYC provider name, and the HAL binary MD5 (`md5sum /data/local/tmp/fake_camera_provider`).

**Boot loops.**

From recovery or ADB:

```
adb shell su -c 'touch /data/local/tmp/.fakehal_watchdog_off'
adb shell su -c 'echo 1 > /data/local/tmp/fakehal_crash_count'
```

That stops the watchdog from relaunching the HAL. Reboot. Fix whatever was wrong (bad binary, missing library, broken VINTF), remove the kill switch, reboot again.

**OpenCamera shows a black screen.**

Enable Camera2 API: `scripts/set_camera2.sh` + force-stop OpenCamera. If it is already on Camera2, check `pidof fake_camera_provider` — if alive, open an issue with `/data/local/tmp/fakehAL.log` tail attached.

---

## License

Released to the public with no restrictions.

Use it personally, resell it to customers, extend it, embed it in your own projects, strip the attribution, re-brand it. No warranty, no promise of support, no obligation on either side. The original author is not interested in maintaining it further.

Questions and bug reports are welcome in [GitHub Issues](https://github.com/crachcrypto/fake_hal/issues), in English. Someone will probably answer. Nobody has to.

# FakeHAL — GCam-фикс: сборка и деплой

## Что сделано
Google Камера падала с `java.lang.IllegalStateException` в `glb.<init>` — на этапе
безусловного чтения CameraCharacteristics, ДО открытия камеры. Причина — наш HAL не
отдавал часть ключей характеристик, которые GCam читает жёстко.

## Фикс
В `FakeCameraDevice.cpp` добавлены недостающие ключи характеристик (с безопасными
дефолтами) + они внесены в список `charKeys`, счётчик заменён на автоматический
`sizeof(charKeys)/sizeof(charKeys[0])`:
- ANDROID_COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES (OFF, FAST)
- ANDROID_EDGE_AVAILABLE_EDGE_MODES (OFF, FAST)
- ANDROID_HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES (OFF, FAST)
- ANDROID_TONEMAP_AVAILABLE_TONE_MAP_MODES (FAST, HIGH_QUALITY) + MAX_CURVE_POINTS=64
- ANDROID_SHADING_AVAILABLE_MODES (OFF, FAST)
- ANDROID_STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES (OFF)
- ANDROID_STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES (OFF)
- ANDROID_SENSOR_INFO_LENS_SHADING_APPLIED (FALSE)
- ANDROID_CONTROL_AE_LOCK_AVAILABLE (TRUE)
- ANDROID_CONTROL_AWB_LOCK_AVAILABLE (TRUE)

Патч: `/root/gcam_fix.patch` (применяется `patch -p0` к src/FakeCameraDevice.cpp).
Бэкап исходника: `/root/fake_hal_aidl/src/FakeCameraDevice.cpp.bak_gcam_20260804_214346`.

## Сборка (AOSP на VPS — уже развёрнут)
- Дерево: `/root/aosp` (android-13.0.0_r83, ~105 ГБ). Сохранено, переиспользуемо.
- Исходник вложен в: `/root/aosp/hardware/google/camera/hal/fake/`
- Правка Android.bp: добавлены `header_libs: ["libarect_headers","libnativebase_headers"]`
  (иначе `android/rect.h not found` через hardware_buffer.h). Бэкап: `Android.bp.bak_arect`.
- Скрипт сборки: `/root/build_fakehal.sh`
  ```
  cd /root/aosp && source build/envsetup.sh
  lunch aosp_cf_arm64_phone-userdebug
  m android.hardware.camera.provider-fake -j8
  ```
- Результат: `out/target/product/vsoc_arm64/vendor/bin/android.hardware.camera.provider-fake`
- Готовый бинарь сохранён: `/root/fakehal_gcam_build/fake_camera_provider`
  (+ таймстемп-копия `fake_camera_provider_gcam_YYYYMMDD_HHMMSS`)
  ARM aarch64, vendor, ~200 КБ, stripped.

## Деплой на телефон (Pixel 7, localhost:5559)
ВАЖНО: сначала поднять туннель с телефона (порт 5555), иначе adb shell мёртв.
На телефоне (клиент):
```
adb tcpip 5555
ssh -N -R 5559:localhost:5555 root@144.31.148.224   # окно НЕ закрывать
```
Затем на VPS:
```
adb connect localhost:5559
D=localhost:5559
# бэкап текущего рабочего бинаря
adb -s $D shell 'su -c "cp /data/local/tmp/fake_camera_provider /data/local/tmp/fake_camera_provider.bak_pre_gcam"'
# залить новый
adb -s $D push /root/fakehal_gcam_build/fake_camera_provider /data/local/tmp/fake_camera_provider
adb -s $D shell 'su -c "chmod 755 /data/local/tmp/fake_camera_provider"'
# перезапуск HAL (через gate или swap-скрипт)
adb -s $D shell 'su -c "sh /data/local/tmp/fakehal_gate.sh once"'
# проверка
adb -s $D shell 'su -c "pidof fake_camera_provider"'
```

## Проверка GCam
```
adb -s $D shell 'su -c "am start -n com.google.android.GoogleCamera/com.android.camera.CameraLauncher"'
adb -s $D logcat -c
# открыть камеру, снять кадр; НЕ должно быть IllegalStateException / glb.<init>
# затем проверить, что Open Camera по-прежнему работает (не сломали рабочий путь)
```

## Откат (если что-то не так)
```
adb -s $D shell 'su -c "cp /data/local/tmp/fake_camera_provider.bak_pre_gcam /data/local/tmp/fake_camera_provider && chmod 755 /data/local/tmp/fake_camera_provider"'
adb -s $D shell 'su -c "sh /data/local/tmp/fakehal_gate.sh once"'
```

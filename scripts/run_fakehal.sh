#!/system/bin/sh
export LD_LIBRARY_PATH=/vendor/lib64:/system/lib64
export LD_PRELOAD=/data/local/tmp/afbc_encoder.so:/data/local/tmp/gralloc_uv_fix.so
exec /data/local/tmp/fake_camera_provider /data/local/tmp/fake_video.mp4 /data/local/tmp/fake_video.mp4

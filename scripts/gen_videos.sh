#!/bin/bash
# Generate 1920x1080 landscape videos (match preview buffer) from passport source.
# Passport rotated so OpenCamera 90deg display rotation yields upright, correct aspect.
set -e
SRC=/root/passport_src_good.jpg
cd /root
# transpose=1 => 90 clockwise, transpose=2 => 90 counter-clockwise
# Fill 1920x1080 via crop=increase (no distortion, may crop edges)
FILL="scale=1920:1080:force_original_aspect_ratio=increase,crop=1920:1080,setsar=1"
# 90 CW
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -vf "transpose=1,$FILL,format=yuv420p" -c:v libx264 -pix_fmt yuv420p /root/land_cw.mp4
# 90 CCW
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -vf "transpose=2,$FILL,format=yuv420p" -c:v libx264 -pix_fmt yuv420p /root/land_ccw.mp4
# 90 CW + 180 (=90 CCW flipped) for completeness: 270 variants
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -vf "transpose=1,transpose=1,transpose=1,$FILL,format=yuv420p" -c:v libx264 -pix_fmt yuv420p /root/land_cw270.mp4
echo "=== frames ==="
for f in land_cw land_ccw land_cw270; do
  ffmpeg -y -v error -i /root/$f.mp4 -frames:v 1 -vf scale=240:-1 /root/fr_$f.png
  ffprobe -v error -select_streams v:0 -show_entries stream=width,height -of csv=p=0 /root/$f.mp4
done
echo DONE

#!/bin/bash
set -e
SRC=/root/passport_src_good.jpg
# CCW-rotated passport, fit fully into 1920x1080 (no distortion, nothing cropped),
# background = blurred zoomed copy so frame is filled (no black bars).
FG="transpose=2,scale=1920:1080:force_original_aspect_ratio=decrease"
BG="transpose=2,scale=1920:1080:force_original_aspect_ratio=increase,crop=1920:1080,gblur=sigma=30,eq=brightness=-0.08"
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -filter_complex \
 "[0:v]$BG[bg];[0:v]$FG[fg];[bg][fg]overlay=(W-w)/2:(H-h)/2,setsar=1,format=yuv420p" \
 -c:v libx264 -pix_fmt yuv420p /root/land_ccw_bg.mp4
# same but CW (opposite rotation), in case display rotation differs
FG2="transpose=1,scale=1920:1080:force_original_aspect_ratio=decrease"
BG2="transpose=1,scale=1920:1080:force_original_aspect_ratio=increase,crop=1920:1080,gblur=sigma=30,eq=brightness=-0.08"
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -filter_complex \
 "[0:v]$BG2[bg];[0:v]$FG2[fg];[bg][fg]overlay=(W-w)/2:(H-h)/2,setsar=1,format=yuv420p" \
 -c:v libx264 -pix_fmt yuv420p /root/land_cw_bg.mp4
for f in land_ccw_bg land_cw_bg; do
  ffmpeg -y -v error -i /root/$f.mp4 -frames:v 1 -vf scale=320:-1 /root/fr_$f.png
  ffprobe -v error -select_streams v:0 -show_entries stream=width,height,sample_aspect_ratio -of csv=p=0 /root/$f.mp4
done
echo DONE

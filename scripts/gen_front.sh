#!/bin/bash
set -e
SRC=/root/passport_src_good.jpg
W=1440; H=1080
mk() { # $1 out  $2 pre-rotation filter
FG="$2 scale=$W:$H:force_original_aspect_ratio=decrease"
BG="$2 scale=$W:$H:force_original_aspect_ratio=increase,crop=$W:$H,gblur=sigma=25,eq=brightness=-0.08"
ffmpeg -y -v error -loop 1 -i $SRC -t 6 -r 30 -filter_complex \
 "[0:v]$BG[bg];[0:v]$FG[fg];[bg][fg]overlay=(W-w)/2:(H-h)/2,setsar=1,format=yuv420p" \
 -c:v libx264 -pix_fmt yuv420p $1
ffmpeg -y -v error -i $1 -frames:v 1 -vf scale=320:-1 ${1%.mp4}_fr.png
ffprobe -v error -select_streams v:0 -show_entries stream=width,height,sample_aspect_ratio -of csv=p=0 $1
}
mk /root/front_up.mp4 ""
mk /root/front_180.mp4 "transpose=1,transpose=1,"
echo DONE

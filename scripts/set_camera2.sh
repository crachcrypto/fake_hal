#!/system/bin/sh
P=/data/data/net.sourceforge.opencamera/shared_prefs/net.sourceforge.opencamera_preferences.xml
cp "$P" "${P}.bak_cam2"
# remove any existing camera_api line, then inject camera2 before </map>
grep -v 'preference_camera_api' "$P" > "${P}.tmp"
sed 's#</map>#    <string name="preference_camera_api">preference_camera_api_camera2</string>\n</map>#' "${P}.tmp" > "$P"
rm -f "${P}.tmp"
chown u0_a253:u0_a253 "$P"
chmod 660 "$P"
echo "RESULT:"
grep -i camera_api "$P"

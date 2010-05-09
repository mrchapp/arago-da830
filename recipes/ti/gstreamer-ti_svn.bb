require gstreamer-ti.inc

PV = "svnr${SRCREV}"

S = "${WORKDIR}/gstreamer_ti/ti_build/ticodecplugin"

SRCREV = "612"

SRC_URI = "svn://gforge.ti.com/svn/gstreamer_ti/trunk;module=gstreamer_ti;proto=https;user=anonymous;pswd='' \
  file://0001-gstreamer-ti-tracker-1055.patch;patch=1 \
  file://0002-add-omapl138-support.patch;patch=1 \
  file://0003-add-omapl137-support.patch;patch=1 \
  file://0004-gstreamer-ti-dm6467-usesinglecsserver.patch;patch=1 \
  file://0005-remove-mp3-mime-type.patch;patch=1 \
  file://0006-gstreamer-ti-tracker-462.patch;patch=1 \
  file://gstreamer-ti-rc.sh \
  file://gst-ti.sh \
"

# use local loadmodules.sh for these platform
# TODO: must be removed onces these loadmodules goes in gstreamer.ti.com
SRC_URI_append_dm365 = "file://loadmodules.sh"
SRC_URI_append_omapl137 = "file://loadmodules.sh"
SRC_URI_append_omapl138 = "file://loadmodules.sh "

# apply omapdmaifbsink patch on omap3 platform
# NOTE: this patch need's X11 header/libs
#SRC_URI_append_omap3 = "file://0007-add-omapdmaifbsink.patch;patch=1 \
# file://loadmodules.sh \
# "


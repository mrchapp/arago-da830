DESCRIPTION = "Arago based rootfs for nSDK development"
PR = "r1"

inherit task

# Trimmed from task-arago-base
# ideally a python way to just remove selected pacakges from the RDEPENDS
# list and include in the image recipe. For some things BAD_RECOMMENDATIONS works
# but, it seems, for task based dependencies I need to remove the task from the image
# recipe and include the subset here.
ARAGO_BASE_SUBSET ="\
    alsa-lib \
    alsa-utils-alsactl \
    alsa-utils-aplay \
    alsa-state \
    ldd \
    mtd-utils \
    curl \
    initscript-telnetd \
    devmem2 \
    base-files \
    base-passwd \
    busybox \
    initscripts \
    modutils-initscripts \
    netbase \
    update-alternatives \
    module-init-tools \
    "

# Removed for now
#    gst-plugin-rtp \
#    gst-plugin-udp \
#    gst-plugin-rtpmanager \
#    gst-plugin-rtsp \
#..
#     gst-plugin-app \
#     gst-plugin-tcp \

RDEPENDS_${PN} = "\
    ${ARAGO_BASE_SUBSET} \
    directfb \
    dbus \
    gstreamer \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-bad \
    gst-plugins-ugly \
    gst-plugin-typefindfunctions \
    gst-plugin-queue2 \
    gst-plugin-alsa \
    gst-plugin-audioconvert \
    gst-plugin-audioresample \
    gst-plugin-volume \
    gst-plugin-audiotestsrc \
    gst-plugin-decodebin \
    gst-plugin-playbin \
    gst-plugin-autodetect \
    gst-plugin-mad \
    gstd \
    thttpd \
    "

# Work around needed for some srctree based recipes
DEPENDS += "cmake-native"

# I am not sure what all the meta stuff is about but
# seems to pull in a whole host of stuff. E.g. gdk-pixbuf which pulls in gtk
# gst-plugins-base-meta \
# gst-plugins-bad-meta \
# gst-plugins-good-meta \
# gst-plugins-ugly-meta \
 
DESCRIPTION = "Arago based rootfs for nSDK development"
PR = "r9"

inherit task

# Work around needed for some srctree based recipes
DEPENDS += "cmake-native"

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
    i2c-tools \
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

RDEPENDS_${PN} = "\
    ${ARAGO_BASE_SUBSET} \
    directfb \
    dbus \
    gstreamer \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-bad \
    gst-plugin-typefindfunctions \
    gst-plugin-queue2 \
    gst-plugin-alsa \
    gst-plugin-audioconvert \
    gst-plugin-icydemux \
    gst-plugin-id3demux \
    gst-plugin-wavparse \
    gst-plugin-apetag \
    gst-plugin-audiotestsrc \
    gst-plugin-decodebin \
    gst-plugin-decodebin2 \
    gst-plugin-playbin \
    gst-plugin-souphttpsrc \
    gst-plugin-autodetect \
    gst-plugin-mms \
    gst-plugin-rtsp \
    gst-plugin-mpegtsdemux \
    gst-plugin-tipafsink \
    libnl \
    log4cplus \
    streamapp \
    qjson-hash \
    u-boot \
    "

# NTP-related components
RDEPENDS_${PN} += "\
	cron \
	tzdata \
	tzdata-asia \
	ntpdate \
	"
 

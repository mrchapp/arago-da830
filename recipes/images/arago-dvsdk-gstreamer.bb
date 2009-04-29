# dvsdk gstreamer demo image 

# The size of the uncompressed ramdisk is 32MB
IMAGE_ROOTFS_SIZE_ext2.gz = "32768"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

# Disable these for now
# util-linux-mount util-linux-umount \


IMAGE_INSTALL = "\
	task-arago-base \
    ${DISTRO_SSH_DAEMON} \
    angstrom-version \
	gstreamer \
	gst-plugins-base \
	gst-plugins-good \ 
	gst-plugins-bad \ 
	gst-plugins-ugly \ 
	gst-plugin-typefindfunctions \
	gst-plugin-queue2 \
	gst-plugin-ossaudio \
	gst-plugin-alsa \
	gst-plugin-audioconvert \
	gst-plugin-audioresample \
	gst-plugin-volume \
	gst-plugin-audiotestsrc \
	gst-plugin-videotestsrc \
	gst-plugin-video4linux2 \
	gst-plugin-videoscale \
	gst-plugin-videorate \
	gst-plugin-videomixer \
	gst-plugin-videoflip \
	gst-plugin-ffmpegcolorspace \
	gst-plugin-decodebin \
	gst-plugin-playbin \
	gst-plugin-autodetect \
	gst-plugin-rtp \
	gst-plugin-udp \
	gst-plugin-rtpmanager \
	gst-plugin-tcp \
	gst-plugin-avi \
	gst-plugin-qtdemux \
	gst-plugin-mad \
	ti-dmai-apps \
	gstreamer-ti \
	gstreamer-ti-demo-script \
    "

export IMAGE_BASENAME = "arago-dvsdk-gstreamer"
IMAGE_LINGUAS = ""

inherit image


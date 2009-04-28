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
	ti-dmai-apps \
	gstreamer \
	gst-plugins-base \
	gst-plugins-good \ 
	gst-plugins-bad \ 
	gst-plugins-ugly \ 
	gst-plugin-ossaudio \
	gst-plugin-alsa \
	gst-plugin-audioconvert \
	gst-plugin-audioresample \
	gst-plugin-videotestsrc \
	gst-plugin-volume \
	gst-plugin-audiotestsrc \
	gst-plugin-decodebin \
	gst-plugin-playbin \
	gst-plugin-rtp \
	gst-plugin-avi \
	gst-plugin-qtdemux \
	gstreamer-ti \
	gstreamer-ti-demo-script \
    "

export IMAGE_BASENAME = "arago-dvsdk-gstreamer"
IMAGE_LINGUAS = ""

inherit image


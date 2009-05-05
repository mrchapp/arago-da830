# dvsdk gstreamer demo image 

# The size of the uncompressed ramdisk is 100MB
IMAGE_ROOTFS_SIZE_ext2.gz = "102400"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

# Disable these for now
# util-linux-mount util-linux-umount \


IMAGE_INSTALL = "\
	task-arago-base \
	task-arago-dvsdk \
	task-arago-gstreamer \
	${DISTRO_SSH_DAEMON} \
	angstrom-version \
    "

export IMAGE_BASENAME = "arago-dvsdk-gstreamer"
IMAGE_LINGUAS = ""

inherit image


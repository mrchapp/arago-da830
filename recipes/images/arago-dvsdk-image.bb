# Arago base image
# gives you a small images with basic media libraries

# The size of the uncompressed ramdisk is 32MB
IMAGE_ROOTFS_SIZE_ext2.gz = "32768"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

# Disable these for now
# util-linux-mount util-linux-umount \

IMAGE_INSTALL = "\
	task-arago-base \
	task-arago-dvsdk \
    ${DISTRO_SSH_DAEMON} \
    angstrom-version \
    "

export IMAGE_BASENAME = "arago-dvsdk-image"
IMAGE_LINGUAS = ""

inherit image

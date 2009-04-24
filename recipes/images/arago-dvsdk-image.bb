# DM355 dvsdk base image

# The size of the uncompressed ramdisk is 32MB
IMAGE_ROOTFS_SIZE_ext2.gz = "32768"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

# Install DMAI packages  
DMAI_PACKAGES = "\  
	ti-cmem-module \
	ti-dm355mm-module \
	ti-dmai-apps \
	"

# Disable these for now
# util-linux-mount util-linux-umount \

IMAGE_INSTALL = "\
	task-arago-demo \
    ${DISTRO_SSH_DAEMON} \
    angstrom-version \
	${DMAI_PACKAGES} \
    "

export IMAGE_BASENAME = "arago-dvsdk-image"
IMAGE_LINGUAS = ""

inherit image

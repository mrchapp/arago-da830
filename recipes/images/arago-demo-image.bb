# Arago demo image
# gives you a small images with basic media libraries

COMPATIBLE_MACHINE = "omap3evm|omap3517-evm|beagleboard|dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm|dm357-evm|da830-omapl137-evm|da850-omapl138-evm"

# The size of the uncompressed ramdisk is 40MB
ROOTFS_SIZE = "32768"
# DM646x have many modules
ROOTFS_SIZE_dm6467-evm = "40960"
ROOTFS_SIZE_dm6467t-evm = "40960"
# Double the beagleboard ramdisk size, due to gazillions of kernel modules
ROOTFS_SIZE_beagleboard = "81920"

EXTRA_IMAGECMD_ext2.gz += "-i 4096"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

# Disable these for now
# util-linux-mount util-linux-umount \

IMAGE_INSTALL = "\
    task-arago-demo \
    ${DISTRO_SSH_DAEMON} \
    "

export IMAGE_BASENAME = "arago-demo-image"
IMAGE_LINGUAS = ""

inherit image

# Arago DVSDK image
# gives you an image with DVSDK libs and demo apps

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

# The size of the uncompressed ramdisk is 100MB
ROOTFS_SIZE = "102400"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-dvsdk \
    "

export IMAGE_BASENAME = "arago-dvsdk-image"

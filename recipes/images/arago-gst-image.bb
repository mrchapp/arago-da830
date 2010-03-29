# Arago GStreamer image
# gives you an image with DVSDK libs and GStreamer demo

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

# The size of the uncompressed ramdisk is 150MB
ROOTFS_SIZE = "153600"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-dvsdk \
    task-arago-gst \
    "

export IMAGE_BASENAME = "arago-gst-image"

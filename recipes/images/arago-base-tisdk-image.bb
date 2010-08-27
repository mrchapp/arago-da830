# Arago TI SDK base image
# gives you an image with Qt/E and other common packages shared by all TI SDKs.

require arago-image.inc

COMPATIBLE_MACHINE = "omap3|ti816x"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-base-tisdk \
    "

export IMAGE_BASENAME = "arago-base-tisdk-image"

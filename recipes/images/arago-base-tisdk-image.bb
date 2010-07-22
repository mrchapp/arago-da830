# Arago TI SDK base image
# gives you an image with Qt/E and other common packages shared by all TI SDKs.

require arago-image.inc

COMPATIBLE_MACHINE = "dm37x-evm|am37x-evm"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-base-tisdk \
    "

export IMAGE_BASENAME = "arago-base-tisdk-image"

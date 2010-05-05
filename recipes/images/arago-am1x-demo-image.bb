# Arago am1x-demo image
# gives you an image with am1x-demo app

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-qte \
    task-arago-am1x-demo \
    "

export IMAGE_BASENAME = "arago-am1x-demo-image"

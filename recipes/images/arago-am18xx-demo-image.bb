# Arago am18xx-demo image
# gives you an image with am18xx-demo app

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-am18xx-demo \
    "

export IMAGE_BASENAME = "arago-am18xx-demo-image"

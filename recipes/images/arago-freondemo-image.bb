# Arago freondemo image
# gives you an image with freondemo app

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-dvsdk \
    task-arago-gst \
    task-arago-qte \
    task-arago-freondemo \
    "

export IMAGE_BASENAME = "arago-freondemo-image"

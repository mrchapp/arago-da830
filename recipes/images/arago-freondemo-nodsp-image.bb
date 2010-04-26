# Arago freondemo image, no DSP
# gives you an image with DSP-less freondemo app

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-qte \
    task-arago-freondemo \
    "

BAD_RECOMMENDATIONS = "gstreamer-ti"

export IMAGE_BASENAME = "arago-freondemo-nodsp-image"

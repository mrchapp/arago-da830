# Arago Qt/Embedded image
# gives you an image with Qt/Embedded on top of the console image

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-qte \
    "

export IMAGE_BASENAME = "arago-qte-image"

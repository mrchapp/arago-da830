# Arago AMSDK image
# gives you an image with Qt/E and other AMSDK deps on top of the console image

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-qte \
    task-arago-amsdk \
    "

export IMAGE_BASENAME = "arago-amsdk-image"

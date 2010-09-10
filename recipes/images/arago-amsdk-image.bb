# Arago AMSDK image
# gives you an image with Qt/E and other AMSDK deps on top of the base
# product image

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-base-tisdk \
    task-arago-tisdk-bsp \
    task-arago-tisdk-graphics \
    task-arago-tisdk-wireless \
    task-arago-tisdk-addons \
    "

export IMAGE_BASENAME = "arago-amsdk-image"

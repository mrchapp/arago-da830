DESCRIPTION = "Extended task to get more basic and demo apps"
PR = "r1"

inherit task

ARAGO_TSLIB = "\
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    "

ARAGO_NCURSES_TERMINFO = "\
    ncurses-terminfo \
    "

ARAGO_FSTOOLS = "\
    e2fsprogs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    dosfstools \
    util-linux-fdisk \
    "

ARAGO_UTILS = "\
    fbset \
    usbutils \
    i2c-tools \
    "

ARAGO_DVSDK_PREREQ = "\
    zlib \
    libpng \
    jpeg \
    jpeg-tools \
    freetype \
    thttpd \
    cppstub \
    "

# cppstub is needed to install libstdc++ in the image
ARAGO_DEMO = "\
    ${ARAGO_TSLIB} \
    ${ARAGO_NCURSES_TERMINFO} \
    ${ARAGO_FSTOOLS} \
    ${ARAGO_UTILS} \
    ${ARAGO_DVSDK_PREREQ} \
    "

RDEPENDS_task-arago-demo = "\
    task-arago-base \
    ${ARAGO_DEMO} \
    "

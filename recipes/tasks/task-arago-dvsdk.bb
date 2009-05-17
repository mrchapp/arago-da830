DESCRIPTION = "Extended task to add dvsdk components"
PR = "r10"

inherit task

# alsa-utils-alsamixer depends on ncurses
ARAGO_ALSA_EXTRA = "\
    alsa-conf \
    alsa-conf-base \
    alsa-server \
    alsa-utils \
    alsa-utils-aconnect \
    alsa-utils-alsaconf \
    alsa-utils-alsactl \
    alsa-utils-alsamixer \
    alsa-utils-amixer \
    alsa-utils-iecset \
    alsa-utils-midi \
    alsa-utils-speakertest \
    "

ARAGO_TSLIB = "\
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    "

ARAGO_NCURSES = "\
    ncurses \
    ncurses-terminfo \
    ncurses-tools \
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
    gdbserver \
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

# add dvsdk components
RDEPENDS_${PN} = "\
    task-arago-base \
    ${ARAGO_ALSA_EXTRA} \
    ${ARAGO_TSLIB} \
    ${ARAGO_NCURSES} \
    ${ARAGO_FSTOOLS} \
    ${ARAGO_UTILS} \
    ${ARAGO_DVSDK_PREREQ} \
	ti-dmai-apps \
    "


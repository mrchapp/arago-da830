DESCRIPTION = "Basic task to get a device booting"
PR = "r3"

inherit task

# udev, devfsd, mdev (from busybox) or none
DISTRO_DEV_MANAGER ?= "udev"

# sysvinit, upstart
DISTRO_INIT_MANAGER ?= "sysvinit sysvinit-pidof"

# tinylogin, getty
DISTRO_LOGIN_MANAGER ?= "tinylogin"

# those ones can be set in machine config to supply packages needed to get machine booting
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

ARAGO_ALSA = "\
    alsa-lib \
    alsa-conf \
    alsa-conf-base \
    alsa-server \
    alsa-utils \
    alsa-utils-aconnect \
    alsa-utils-alsaconf \
    alsa-utils-alsactl \
    alsa-utils-alsamixer \
    alsa-utils-amixer \
    alsa-utils-aplay \
    alsa-utils-iecset \
    alsa-utils-midi \
    alsa-utils-speakertest \
    "

# ncurses-terminfo is rarely needed and won't fit in 16MB
ARAGO_NCURSES = "\
    ncurses \
#    ncurses-terminfo \
    ncurses-tools \
    "

ARAGO_BASE = "\
    ${ARAGO_ALSA} \
    ${ARAGO_NCURSES} \
    mtd-utils \
    gdbserver \
    opkg-nogpg \
    arago-feed-configs \
    "

# minimal set of packages - needed to boot
RDEPENDS_task-arago-base = "\
    base-files \
    base-passwd \
    busybox \
    initscripts \
    modutils-initscripts \
    netbase \
    update-alternatives \
    ${ARAGO_BASE} \
    ${DISTRO_DEV_MANAGER} \
    ${DISTRO_INIT_MANAGER} \
    ${DISTRO_LOGIN_MANAGER} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    "

RRECOMMENDS_task-arago-base = "\
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    "

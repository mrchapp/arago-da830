DESCRIPTION = "Basic task to get a device booting"
PR = "r4"

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

ARAGO_ALSA_BASE = "\
    alsa-lib \
    alsa-utils-aplay \
    "

ARAGO_BASE = "\
    ${ARAGO_ALSA_BASE} \
    mtd-utils \
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

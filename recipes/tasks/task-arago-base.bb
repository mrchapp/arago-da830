DESCRIPTION = "Basic task to get a device booting"
LICENSE = "MIT"
PR = "r9"

inherit task

# those ones can be set in machine config to supply packages needed to get machine booting
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

ARAGO_ALSA_BASE = "\
    alsa-lib \
    alsa-utils-aplay \
    "

ARAGO_BASE = "\
    ${ARAGO_ALSA_BASE} \
    ldd \
    mtd-utils \
    curl \
    arago-feed-configs \
    initscript-telnetd \
    devmem2 \
    "

# minimal set of packages - needed to boot
RDEPENDS_${PN} = "\
    base-files \
    base-passwd \
    busybox \
    initscripts \
    modutils-initscripts \
    netbase \
    update-alternatives \
    module-init-tools \
    ${ARAGO_BASE} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    "

RRECOMMENDS_${PN} = "\
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    "

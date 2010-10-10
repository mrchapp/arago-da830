DESCRIPTION = "Task to install graphics application sources on host"
PR = "r3"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

GRAPHICS_APPS = ""

GRAPHICS_APPS_am37x-evm = " \
    matrix-gui-e-src \
    "

GRAPHICS_APPS_ti816x = " \
    matrix-gui-e-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "

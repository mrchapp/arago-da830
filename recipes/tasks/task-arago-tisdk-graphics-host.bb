DESCRIPTION = "Task to install graphics application sources on host"
PR = "r1"
LICENSE="MIT"

inherit task

GRAPHICS_APPS = ""

GRAPHICS_APPS_am37x-evm = " \
    matrix-gui-e-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "

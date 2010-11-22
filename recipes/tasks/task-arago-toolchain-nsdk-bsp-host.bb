DESCRIPTION = "Task to build and install Board Support Package sources (or development header) packages on host"
PR = "r1"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = "\
    linux-omapl1-src \
    ti-tisdk-licenses \
    u-boot-src \
    "


DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r2"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_am37x-evm = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "

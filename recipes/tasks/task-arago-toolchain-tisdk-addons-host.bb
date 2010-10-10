DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r3"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_am37x-evm = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

UTILS_ti816x = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "

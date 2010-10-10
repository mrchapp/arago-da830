DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r3"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_ti816x = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_am37x-evm = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "

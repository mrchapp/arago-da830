DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r1"
LICENSE="MIT"

inherit task

UTILS = ""

UTILS_am37x-evm = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "

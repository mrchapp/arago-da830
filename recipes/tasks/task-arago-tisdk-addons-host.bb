DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r1"
LICENSE="MIT"

inherit task

UTILS = ""

UTILS_am37x-evm = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "

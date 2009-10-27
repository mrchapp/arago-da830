DESCRIPTION ?= "Code Generation Tools for TI DaVinci and OMAP"

PV = "6.1.12"
PR = "r3"

BASE_SRC_URI ="ftp://install.source.dir.com"
SRC_URI	= "${BASE_SRC_URI}/ti_cgt_c6000_${PV}_setup_linux_x86.bin"

BINFILE="ti_cgt_c6000_${PV}_setup_linux_x86.bin"

S = "${WORKDIR}/cgt"

# This file defines function used for extracting .bin file

TI_BIN_UNPK_CMDS="Y:qY:workdir: : "
TI_BIN_UNPK_WDEXT="/cgt"

include ti-eula-unpack.inc

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}


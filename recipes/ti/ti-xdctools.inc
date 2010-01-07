DESCRIPTION = "TI XDC tool"

BASE_PV = "3_15"
PV = "3_15_01_59"
BASE_SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/rtsc/xdctools_${BASE_PV}/exports"

SRC_URI	= "${BASE_SRC_URI}/xdctools_setuplinux_${PV}.bin"
BINFILE="xdctools_setuplinux_${PV}.bin"

S = "${WORKDIR}/xdctools_${PV}"

PR = "r19"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"

include ti-eula-unpack.inc

# This file defines function used for extracting .bin file

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

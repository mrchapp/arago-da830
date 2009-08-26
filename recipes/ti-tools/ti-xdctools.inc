DESCRIPTION = "TI XDC tool"

# download xdctools_setuplinux_${PV}.bin from https://www-a.ti.com/downloads/sds_support/targetcontent/rtsc/xdctools_3_10/xdctools_${PV}/index_external.html and copy in Arago/OE download directory

SRC_URI	= "http://install.source.dir.com/xdctools_setuplinux_${PV}.bin"
BINFILE="xdctools_setuplinux_${PV}.bin"

S = "${WORKDIR}/xdctools_${PV}"

PR = "r16"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"

include ../ti-multimedia/ti-eula-unpack.inc

# This file defines function used for extracting .bin file

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}


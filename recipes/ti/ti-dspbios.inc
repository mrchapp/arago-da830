DESCRIPTION = "TI DSP/BIOS for Davinci and OMAP processor"

PV = "5_41_00_06"
PR = "r15"

BASE_SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dspbios/${PV}/exports"
SRC_URI	= "${BASE_SRC_URI}/bios_setuplinux_${PV}.bin"
BINFILE="bios_setuplinux_${PV}.bin"

S = "${WORKDIR}/bios_${PV}"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"

include ti-eula-unpack.inc

# This file defines function used for extracting .bin file

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

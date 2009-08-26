DESCRIPTION = "TI DSP/BIOS for Davinci and OMAP processor"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"

include ../ti-multimedia/ti-eula-unpack.inc

# This file defines function used for extracting .bin file

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}


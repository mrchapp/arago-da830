TI_BIN_UNPK_CMDS="Y: qY:workdir"

include ti-eula-unpack.inc

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

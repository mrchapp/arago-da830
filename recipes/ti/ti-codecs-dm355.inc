DESCRIPTION = "DM355 Codecs"

PV = "1_13_000"
BASE_SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvsdk/codecs"

SRC_URI	= "${BASE_SRC_URI}/dm355_codecs_setuplinux_${PV}.bin \
		   file://mapdmaq \
		 "

S = "${WORKDIR}/dm355_codecs_${PV}"

require ti-multimedia-common.inc

BINFILE="dm355_codecs_setuplinux_${PV}.bin"

# Yes, the xdc stuff still breaks with a '.' in PWD
PR = "r16"

do_compile() {
	echo "Do nothing"
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True

# This file defines function used for extracting .bin file

TI_BIN_UNPK_CMDS="Y:Y: qY:workdir"

include ti-eula-unpack.inc

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}


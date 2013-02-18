DESCRIPTION = "TI IPC"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

#require ti-eula-unpack.inc
#S = "${WORKDIR}/ti/ipc_${PV}"
#SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ipc/${PV}/exports/ipc_setuplinux_${PV}.bin"
#BINFILE="ipc_setuplinux_${PV}.bin"
#TI_BIN_UNPK_CMDS="Y: q:workdir:Y"

SRC_URI = "http://t3500wsk1.am.dhcp.ti.com/~x0019444/da830-evm/ipc_${PV}.zip"
S = "${WORKDIR}/ipc_${PV}"

do_install() {
   install -d ${D}${IPC_INSTALL_DIR_RECIPE}
   cp -pPrf ${S}/* ${D}${IPC_INSTALL_DIR_RECIPE}
}


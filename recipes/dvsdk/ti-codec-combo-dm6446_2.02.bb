DESCRIPTION = "DM6446 Codec Combo 2.02"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm6446_dvsdk_combos_2_02.tar.gz"

S = "${WORKDIR}/dm6446_dvsdk_combos_2_02"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "202"
PR = "r2"

CE_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine"
CODEC_dm355-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm355"
CODEC_omap3evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_dm6446-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm6446"
FC_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
DSPBIOS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-dspbios"
CGT6x_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-cgt6x"
XDCTOOLS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-xdctools"
USER_XDC_PATH="${CE_INSTALL_DIR}/examples"

XDCARGS="eval"
export XDCARGS



XDCPATH="${CE_INSTALL_DIR}/packages;${FC_INSTALL_DIR}/packages;${DSPBIOS_DIR}/packages;"

do_configure () {
	sed -i -e s:/db/toolsrc/library/vendors2005/ti/c6x/6.0.21/Linux:${CGT6x_DIR}:g ${S}/config.bld 
}

do_compile() {
	echo "do nothing"
#	cd ${S}
#	${XDCTOOLS_DIR}/xdc clean -PR . 
#	${XDCTOOLS_DIR}/xdc XDCBUILDCFG=${S}/config.bld  --xdcpath=".;${S}/packages/;${XDCPATH}" -PR . 
}

do_install () {
    install -d ${D}/opt/ti/codec-combo
	cd ${S}
	for file in `find . -name *.x64P`; do
		cp ${file} ${D}/opt/ti/codec-combo
	done
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

FILES_${PN} = "/opt/ti/codec-combo/*"


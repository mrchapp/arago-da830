require ti-codec-combo-dm6467.inc

require ti-multimedia-common.inc

# compile time dependencies
DEPENDS="ti-codec-engine ti-dspbiosutils-native ti-dsplink-module"

installdir = "${prefix}/ti"
do_compile() {
	make CE_INSTALL_DIR=${CE_INSTALL_DIR} \
		 FC_INSTALL_DIR=${FC_INSTALL_DIR} \
		 EDMA3_LLD_INSTALL_DIR=${EDMA3_LLD_INSTALL_DIR} \
		 LINK_INSTALL_DIR=${LINK_INSTALL_DIR} \
		 CMEM_INSTALL_DIR=${CMEM_INSTALL_DIR} \
		 LPM_INSTALL_DIR=${LPM_INSTALL_DIR} \
	     BIOS_INSTALL_DIR=${BIOS_INSTALL_DIR} \
		 CODEGEN_INSTALL_DIR=${CODEGEN_INSTALL_DIR} \
		 XDC_INSTALL_DIR=${XDC_INSTALL_DIR} \
		 clean all

}

do_install () {
    install -d ${D}/${installdir}/codec-combo
	cd ${S}
	for file in `find . -name *.x64P`; do
		cp ${file} ${D}/${installdir}/codec-combo
	done
}

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"


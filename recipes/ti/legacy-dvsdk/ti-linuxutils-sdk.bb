inherit sdk

require ../ti-linuxutils.inc

PV           = "${PV_pn-ti-linuxutils}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-linuxutils}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    # Update cmem and sdma Rules.make to point correct location of main 
    # Rules.make.
    sed -i -e s:Rules.make:../Rules.make:g \
	${S}/packages/ti/sdo/linuxutils/cmem/Rules.make
    sed -i -e s:Rules.make:../Rules.make:g \
	${S}/packages/ti/sdo/linuxutils/sdma/Rules.make

    install -d ${D}/${DVSDK_PATH}/linuxutils_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/linuxutils_${PV}

    # Create rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the MFC Linux Utils package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/linuxutils.Rules.make
    echo "LINUXUTILS_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/linuxutils_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/linuxutils.Rules.make
    echo "CMEM_INSTALL_DIR=\$(LINUXUTILS_INSTALL_DIR)" >> ${STAGING_DIR_HOST}/ti-sdk-rules/linuxutils.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/linuxutils_${PV}"
INSANE_SKIP_${PN} = True

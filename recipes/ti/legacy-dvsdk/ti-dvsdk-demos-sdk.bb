inherit sdk

PV           = "${PV_pn-ti-dvsdk-demos}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-dvsdk-demos}"

require ../ti-dvsdk-demos.inc

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
	echo "do nothing"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/dvsdk_demos_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/dvsdk_demos_${PV}

    # Creates rules.make file
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the DVSDK demos are installed" > ${STAGING_DIR_HOST}/ti-sdk-rules/dvsdk-demos.Rules.make
    echo "DEMO_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dvsdk_demos_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dvsdk-demos.Rules.make    
}

FILES_${PN} = "${DVSDK_PATH}/dvsdk_demos_${PV}/*"
INSANE_SKIP_${PN} = True


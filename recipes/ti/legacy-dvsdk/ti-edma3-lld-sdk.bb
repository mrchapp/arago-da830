inherit sdk

require ../../ti-dsp/ti-edma3-lld.inc

PV      = "${PV_pn-ti-edma3-lld}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-edma3-lld}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/edma3_lld${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/edma3_lld${PV}
    chmod 755 -R ${D}/${DVSDK_PATH}/edma3_lld${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the DSPBIOS Utils package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/edma3lld.Rules.make
    echo "EDMA3_LLD_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/edma3_lld${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/edma3lld.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/edma3_lld${PV}"
INSANE_SKIP_${PN} = True

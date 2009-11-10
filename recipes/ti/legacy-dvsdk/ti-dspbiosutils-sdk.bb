inherit sdk

require ../../ti-dsp/ti-dspbiosutils.inc

PV      = "${PV_pn-ti-dspbiosutils-native}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-dspbiosutils-native}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/biosutils_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/biosutils_${PV}
    chmod 755 -R ${D}/${DVSDK_PATH}/biosutils_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the DSPBIOS Utils package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/biosutils.Rules.make
    echo "BIOSUTILS_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/biosutils_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/biosutils.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/biosutils_${PV}"

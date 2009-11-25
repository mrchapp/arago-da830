inherit sdk

require ../ti-dsplink-module.inc

PV = "${PV_pn-ti-dsplink-module}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-dsplink-module}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/dsplink_linux_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/dsplink_linux_${PV}
    chmod 755 -R ${D}/${DVSDK_PATH}/dsplink_linux_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the DSP Link package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/dlink.Rules.make
    echo "LINK_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dsplink_linux_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dlink.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/dsplink_linux_${PV}"
INSANE_SKIP_${PN} = True

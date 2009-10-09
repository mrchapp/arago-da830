inherit sdk

require ../../ti-tools/ti-xdais.inc

PV           = "${PV_pn-ti-xdais}"
PVEXTENSION  = "${PVEXTENSION_pn-ti-xdais}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-xdais}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_install() {
    install -d ${D}/${DVSDK_PATH}/xdais_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/xdais_${PV}
    chmod 755 -R ${D}/${DVSDK_PATH}/xdais_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the XDAIS package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdais.Rules.make
    echo "XDAIS_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/xdais_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdais.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/xdais_${PV}"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

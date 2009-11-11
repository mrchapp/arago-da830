inherit sdk

require ../ti-xdctools.inc
PV = "${PV_pn-ti-xdctools-native}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-xdctools-native}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/xdctools_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/xdctools_${PV}
    chmod 755 -R ${D}/${DVSDK_PATH}/xdctools_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the RTSC tools package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
    echo "XDC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/xdctools_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/xdctools_${PV}"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

inherit sdk

require ../../ti-tools/ti-xdctools.inc

BASE_PV = "${BASE_PV_pn-ti-xdctools-native}"
PV      = "${PV_pn-ti-xdctools-native}"

do_install() {
    install -d ${D}/${prefix}/dvsdk/xdctools_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/xdctools_${PV}
    chmod 755 -R ${D}/${prefix}/dvsdk/xdctools_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the RTSC tools package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
    echo "XDC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/xdctools_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/xdctools_${PV}"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

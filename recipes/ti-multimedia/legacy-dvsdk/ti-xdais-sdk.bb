inherit sdk

require ../../ti-tools/ti-xdais.inc

BASE_PV = "${BASE_PV_pn-ti-xdais}"
PV      = "${PV_pn-ti-xdais}"

do_install() {
    install -d ${D}/${prefix}/dvsdk/xdais_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/xdais_${PV}
    chmod 755 -R ${D}/${prefix}/dvsdk/xdais_${PV}
    chmod 755 -R  ${S}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the XDAIS package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdais.Rules.make
    echo "XDC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/xdais_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdais.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/xdais_${PV}"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

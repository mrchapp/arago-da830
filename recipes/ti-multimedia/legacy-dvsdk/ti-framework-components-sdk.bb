inherit sdk

require ../ti-framework-components.inc

PV      = "${PV_pn-ti-framework-components}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${prefix}/dvsdk/framework_components_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/framework_components_${PV}

    # Create rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the Framework Components package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/fc.Rules.make
    echo "FC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/framework_components_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/fc.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/framework_components_${PV}"
INSANE_SKIP_${PN} = True

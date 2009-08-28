inherit sdk

PV = "${PV_pn-ti-dvsdk-demos}"

require ../ti-dvsdk-demos.inc

do_compile () {
	echo "do nothing"
}

do_install() {
    install -d ${D}/${prefix}/dvsdk/dvsdk_demos_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/dvsdk_demos_${PV}

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	  echo "# Where the DVSDK demos are installed" > ${STAGING_DIR_HOST}/ti-sdk-rules/dvsdk-demos.Rules.make
    echo "DEMO_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dvsdk_demos_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dvsdk-demos.Rules.make    
}

FILES_${PN} = "${prefix}/dvsdk/dvsdk_demos_${PV}/*"
INSANE_SKIP_${PN} = True


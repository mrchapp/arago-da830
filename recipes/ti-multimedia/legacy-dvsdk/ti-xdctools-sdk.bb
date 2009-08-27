inherit sdk

require ../../ti-tools/ti-xdctools.inc

PV = "${PV_pn-ti-xdctools-native}"

do_install() {
    install -d ${D}/${prefix}/dvsdk/xdctools_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/xdctools_${PV}

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	  echo "# Where the RTSC tools package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
    echo "XDC_INSTALL_DIR=${prefix}/dvsdk/xdctools_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/xdctools_${PV}"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

inherit sdk

require ../ti-codec-engine.inc

PV = "${PV_pn-ti-codec-engine}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {

    install -d ${D}/${prefix}/dvsdk/codec_engine_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/codec_engine_${PV}

    # Remove duplicate packages
    rm -rf `find ${D}/${prefix}/dvsdk/codec_engine_${PV}/cetools -type d -name xdais`
    rm -rf `find ${D}/${prefix}/dvsdk/codec_engine_${PV}/cetools -type d -name fc`
    rm -rf `find ${D}/${prefix}/dvsdk/codec_engine_${PV}/cetools -type d -name linuxutils`

    # Creates rules.make file
    
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the Codec Engine package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "CE_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/codec_engine_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "# Where the DSP Link package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make    
    echo "LINK_INSTALL_DIR=\$(CE_INSTALL_DIR)/cetools" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/codec_engine_${PV}"
INSANE_SKIP_${PN} = True

inherit sdk

require ../ti-dm365mm-module.inc

PV           = "${PV_pn-ti-dm365mm-module}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-dm365mm-module}"

do_install() {
    install -d ${D}/${prefix}/dvsdk/dm365mm_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/dm365mm_${PV}

    # Creates rules.make file
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the DM365MM module is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm365mm-module.Rules.make
    echo "DM365MMAP_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dm365mm_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm365mm-module.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/dm365mm_${PV}/*"
INSANE_SKIP_${PN} = True


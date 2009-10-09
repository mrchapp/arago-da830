inherit sdk

require ../ti-codecs-dm365.inc

PV = "${PV_pn-ti-codecs-dm365}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-codecs-dm365}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_install() {
    install -d ${D}/${DVSDK_PATH}/dm365_codecs_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/dm365_codecs_${PV}

    # Creates rules.make file
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "ifeq (\$(PLATFORM),dm365)" > ${STAGING_DIR_HOST}/ti-sdk-rules/dm365-codecs.Rules.make
    echo "# Where the DM365 codecs are installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm365-codecs.Rules.make
    echo "    CODEC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dm365_codecs_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm365-codecs.Rules.make
    echo "endif" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm365-codecs.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/dm365_codecs_${PV}/*"


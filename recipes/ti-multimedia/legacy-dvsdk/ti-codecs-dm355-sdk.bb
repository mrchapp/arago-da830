inherit sdk

require ../ti-codecs-dm355.inc

PV = "${PV_pn-ti-codecs-dm355}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-codecs-dm355}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

SRC_URI = "${BASE_SRC_URI}/dm355_codecs_setuplinux_${PV}.bin \
           file://dm355mm.patch;patch=1 \
          "

do_install() {
    install -d ${D}/${DVSDK_PATH}/dm355_codecs_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/dm355_codecs_${PV}

    # Creates rules.make file
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "ifeq (\$(PLATFORM),dm355)" > ${STAGING_DIR_HOST}/ti-sdk-rules/dm355-codecs.Rules.make
    echo "# Where the DM355 codecs are installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm355-codecs.Rules.make
    echo "    CODEC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dm355_codecs_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm355-codecs.Rules.make
    echo "endif" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dm355-codecs.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/dm355_codecs_${PV}/*"


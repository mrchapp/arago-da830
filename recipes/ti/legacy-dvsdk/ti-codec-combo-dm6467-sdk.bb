inherit sdk

require ../../ti-dsp/ti-codec-combo-dm6467.inc

PV = "${PV_pn-ti-codec-combo-dm6467}"
CSVER="${CSVER_pn-ti-codec-combo-dm6467}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-codec-combo-dm6467}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {

    install -d ${D}/${DVSDK_PATH}/cs${CSVER}dm6467_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/cs${CSVER}dm6467_${PV}

    # Creates rules.make file
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "ifeq (\$(PLATFORM),dm6467)" > ${STAGING_DIR_HOST}/ti-sdk-rules/cs${CSVER}dm6467.Rules.make
    echo "# Where the cs${CSVER}dm6467 package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/cs${CSVER}dm6467.Rules.make
    echo "CODEC_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/cs${CSVER}dm6467_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/cs${CSVER}dm6467.Rules.make
    echo "endif" >> ${STAGING_DIR_HOST}/ti-sdk-rules/cs${CSVER}dm6467.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/cs${CSVER}dm6467_${PV}"
INSANE_SKIP_${PN} = True

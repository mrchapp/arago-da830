inherit sdk
require ../ti-cgt6x.inc

PV = "${PV_pn-ti-cgt6x-native}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-cgt6x-native}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}
do_install() {
    install -d ${D}/${DVSDK_PATH}/cgt6x_${PV}
#Don't copy the Codegen to the DVSDK-Legacy; hence commented out
#cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/cgt6x_${PV}

	# Creates rules.make file, irrespective of codegen copied or not
    mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the TI C6x codegen tool is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
    echo "CODEGEN_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/cgt6x_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
    echo "" >> 	${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/cgt6x_${PV}"
INSANE_SKIP_${PN} = True

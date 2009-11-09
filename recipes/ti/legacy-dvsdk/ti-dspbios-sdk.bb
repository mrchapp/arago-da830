require ../ti-dspbios.inc
inherit sdk

# download bios installer and copy in Arago/OE installation directory

PV = "${PV_pn-ti-dspbios-native}"
BASE_SRC_URI = "${BASE_SRC_URI_pn-ti-dspbios-native}"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/bios_${PV}
    cp -pPrf ${S}/* ${D}/${DVSDK_PATH}/bios_${PV}

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	  echo "# Where DSP/BIOS is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/bios.Rules.make
    echo "BIOS_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/bios_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/bios.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/bios_${PV}"
INSANE_SKIP_${PN} = True

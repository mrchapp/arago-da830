require ti-codec-engine.inc

# compile time dependencies
DEPENDS_dm6446-evm 	+= "ti-xdctools-native ti-cgt6x-native ti-dspbios-native"
DEPENDS_omap3evm   	+= "ti-xdctools-native ti-cgt6x-native ti-dspbios-native"
DEPENDS_beagleboard	+= "ti-xdctools-native ti-cgt6x-native ti-dspbios-native"
DEPENDS_dm355-evm 	+= "ti-xdctools-native"

do_compile () {
        echo "! Do not rebuild for now !"
}

# stage tree - other packages may need this
do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"


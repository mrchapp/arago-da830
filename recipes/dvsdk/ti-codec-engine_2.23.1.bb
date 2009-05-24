DESCRIPTION = "Codec Engine 2.23.01 for TI ARM/DSP processors"

# compile time dependencies
DEPENDS_dm6446-evm 	+= "ti-xdctools-native ti-cgt6x-native ti-dspbios-native ti-codec-combo-dm6446"
DEPENDS_omap3evm   	+= "ti-cgt6x-native ti-dspbios-native ti-codec-combo-omap3530 ti-xdctools-native"
DEPENDS_beagleboard	+= "ti-cgt6x-native ti-dspbios-native ti-codec-combo-omap3530 ti-xdctools-native"
DEPENDS_dm355-evm 	+= "ti-codec-combo-dm355 ti-xdctools-native"

PREFERRED_VERSION_ti-dspbios-native	= "533"
PREFERRED_VERSION_ti-cgt6x-native  	= "60"
PREFERRED_VERSION_ti-xdctools-native 	= "310"
PREFERRED_VERSION_ti-codec-combo-dm6446	= "205"

# tconf from xdctools dislikes '.' in pwd :/
PR = "r10"
PV = "2231"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23_01.tar.gz "

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23_01"

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


DESCRIPTION = "Code Generation Tools 6.0.16 for TI DaVinci and OMAP"

# NOTE: This in internal ftp running on Brijesh's linux host. 
# This will not work outside TI network and the link should be remove once 
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/cg6x_6_0_16.tar.gz"

S = "${WORKDIR}/cg6x_6_0_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "6016"
PR = "r1"

do_compile() {
	echo "Nothing to compile"
}

do_install () {
    install -d ${D}/cgt6x_6_0_16
    cp -pPrf ${S}/* ${D}/cgt6x_6_0_16
}

do_stage() {
    install -d ${STAGING_DIR}/${BUILD_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${BUILD_SYS}/${PN}/ 
}

FILES_${PN}-dev += "/cgt6x_6_0_16/*"
INSANE_SKIP_${PN}-dev = True

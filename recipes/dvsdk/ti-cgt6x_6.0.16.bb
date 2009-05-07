DESCRIPTION = "Code Generation Tools 6.0.16 for TI DaVinci and OMAP"

# NOTE: This in internal ftp running on Brijesh's linux host. 
# This will not work outside TI network and the link should be remove once 
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/cg6x_6_0_16.tar.gz"

S = "${WORKDIR}/cg6x_6_0_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "6016"
PR = "r9"

do_compile() {
	echo "Nothing to compile"
}

do_install () {
	# add this in dev pkg
    install -d ${D}/opt/ti/sdk/cgt6x_6_0_16
    cp -pPrf ${S}/* ${D}/opt/ti/sdk/cgt6x_6_0_16
}

do_stage() {
	# stage cgt, other packages will need this at compile time.
    install -d ${STAGING_DIR}/${BUILD_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${BUILD_SYS}/${PN}/ 
}

FILES_${PN}-dev += "/opt/ti/sdk/cgt6x_6_0_16/*"
INSANE_SKIP_${PN}-dev = True
INHIBIT_PACKAGE_STRIP = "1"


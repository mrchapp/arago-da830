DESCRIPTION = "XDC tool 3.10.03"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/xdctools_3_10_03.tar.gz"

S = "${WORKDIR}/xdctools_3_10_03"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "310"
PR = "r4"

do_compile() {
	echo "Nothing to compile"
}

do_install () {
    install -d ${D}/xdctools_3_10_03
    cp -pPrf ${S}/* ${D}/xdctools_3_10_03
}

do_stage() {
    install -d ${STAGING_DIR}/${BUILD_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${BUILD_SYS}/${PN}/ 
}

INSANE_SKIP_${PN}-dev = True
INHIBIT_PACKAGE_STRIP = "1"
FILES_${PN}-dev = "/xdctools_3_10_03/*"


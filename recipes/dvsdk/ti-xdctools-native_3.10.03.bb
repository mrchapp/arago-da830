inherit native
require ti-xdctools.inc

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/xdctools_3_10_03.tar.gz"

S = "${WORKDIR}/xdctools_3_10_03"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "310"
PR = "r13"

do_stage() {
    install -d ${STAGING_DIR_NATIVE}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR_NATIVE}/${PN}
}

AUTOTOOLS_NATIVE_STAGE_INSTALL="1"

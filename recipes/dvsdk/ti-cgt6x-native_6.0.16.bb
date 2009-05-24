require ti-cgt6x.inc
inherit native

# NOTE: This in internal ftp running on Brijesh's linux host. 
# This will not work outside TI network and the link should be remove once 
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/cg6x_6_0_16.tar.gz"

S = "${WORKDIR}/cg6x_6_0_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "6016"
PR = "r10"

do_stage() {
    install -d ${STAGING_DIR_NATIVE}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR_NATIVE}/${PN}/ 
}

AUTOTOOLS_NATIVE_STAGE_INSTALL="1"


DESCRIPTION = "DM355 Codec Combo 1.13"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm355_codecs_1_13_000.tar.gz"

S = "${WORKDIR}/dm355_codecs_1_13_000"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "113"
PR = "r0"

do_configure () {
  echo "Do nothing"
}

do_compile() {
	echo "Do nothing"
}

do_install () {
	echo "Do nothing"
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}


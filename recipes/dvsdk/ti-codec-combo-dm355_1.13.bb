DESCRIPTION = "DM355 Codec Combo 1.13"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm355_codecs_1_13_000.tar.gz \
		   file://mapdmaq \
		 "

S = "${WORKDIR}/dm355_codecs_1_13_000"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "113"
PR = "r9"

do_compile() {
	echo "Do nothing"
}

do_install () {
	# install mapdmaq on target
	install -d ${D}/opt/ti/codec-combo
	install -m 0755 ${WORKDIR}/mapdmaq 	${D}/opt/ti/codec-combo

	# install codec combo on dev pkg
	install -d ${D}/opt/ti/sdk/dm355_codec_combo_1_13/
	cp -pRrf ${S}/* ${D}/opt/ti/sdk/dm355_codec_combo_1_13/
}

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True
INSANE_SKIP_${PN}-dev = True
FILES_${PN} = "/opt/ti/codec-combo/mapdmaq"
FILES_${PN}-dev = "/opt/ti/sdk/dm355_codec_combo_1_13/*"


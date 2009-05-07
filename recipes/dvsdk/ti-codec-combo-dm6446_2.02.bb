DESCRIPTION = "DM6446 Codec Combo 2.02"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm6446_dvsdk_combos_2_02.tar.gz"

S = "${WORKDIR}/dm6446_dvsdk_combos_2_02"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "202"
PR = "r4"

do_compile() {
	echo "do nothing"
}

do_install () {
    install -d ${D}/opt/ti/codec-combo
	cd ${S}
	for file in `find . -name *.x64P`; do
		cp ${file} ${D}/opt/ti/codec-combo
	done

	# install dev pkgs
	install -d ${D}/${datadir}/ti/codec-combo-dm6446_2_02
	cp -pPrf ${S}/* ${D}/${datadir}/ti/codec-combo-dm6446_2_02
}

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "/opt/ti/codec-combo/*"
FILES_${PN}-dev = "${datadir}/ti/codec-combo-dm6446_2_02/*
INSANE_SKIP_${PN}-dev = True
INHIBIT_PACKAGE_STRIP = "1"


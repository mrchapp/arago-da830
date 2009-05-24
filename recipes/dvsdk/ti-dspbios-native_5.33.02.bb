require ti-dspbios.inc
inherit native

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/bios_5_33_02.tar.gz"

S = "${WORKDIR}/bios_5_33_02"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "533"
PR = "r9"

do_stage() {
    install -d ${STAGING_DIR}/${BUILD_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${BUILD_SYS}/${PN}/ 
}

FILES_${PN} ="/dvsdk/bios_5_33_02/*"
INSANE_SKIP_${PN} = True


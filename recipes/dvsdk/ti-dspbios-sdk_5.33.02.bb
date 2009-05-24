require ti-dspbios.inc
inherit sdk

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/bios_5_33_02.tar.gz"

S = "${WORKDIR}/bios_5_33_02"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "533"
PR = "r10"

do_install() {
    install -d ${D}/${prefix}/dvsdk/bios_5_33_02
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/bios_5_33_02

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	  echo "# Where DSP/BIOS is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/bios.Rules.make
    echo "BIOS_INSTALL_DIR=${prefix}/dvsdk/bios_5_33_02" >> ${STAGING_DIR_HOST}/ti-sdk-rules/bios.Rules.make
}

FILES_${PN} ="${prefix}/dvsdk/bios_5_33_02/*"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"


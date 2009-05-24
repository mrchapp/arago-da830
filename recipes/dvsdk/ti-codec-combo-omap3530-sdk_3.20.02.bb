DESCRIPTION = "OMAP3530 Codec Combo 3.20.02"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/omap3530_dvsdk_combos_3_20_02.tar.gz"

S = "${WORKDIR}/omap3530_dvsdk_combos_3_20_02"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "316"
PR = "r7"

do_compile () {
	echo "No nothing"
}

do_install() {
    install -d ${D}/${prefix}/dvsdk/omap3530_dvsdk_combos_3_20_02
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/omap3530_dvsdk_combos_3_20_02

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
		echo "# Where the codec servers are installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
    echo "CODEC_INSTALL_DIR=${prefix}/dvsdk/omap3530_dvsdk_combos_3_20_02" >> ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
}

INHIBIT_PACKAGE_STRIP = "1"
FILES_${PN} = "${prefix}/dvsdk/omap3530_dvsdk_combos_3_20_02/*"
INSANE_SKIP_${PN} = True


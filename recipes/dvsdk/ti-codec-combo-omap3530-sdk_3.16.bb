DESCRIPTION = "OMAP3530 Codec Combo 3.16"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/omap3530_dvsdk_combos_3_16.tar.gz"

S = "${WORKDIR}/omap3530_dvsdk_combos_3_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "316"
PR = "r6"

do_compile () {
	echo "No nothing"
}

do_install() {
    install -d ${D}/dvsdk/omap3530_dvsdk_combos_3_16
    cp -pPrf ${S}/* ${D}/dvsdk/omap3530_dvsdk_combos_3_16
}

FILES_${PN} = "/dvsdk/omap3530_dvsdk_combos_3_16/*"
INSANE_SKIP_${PN} = True


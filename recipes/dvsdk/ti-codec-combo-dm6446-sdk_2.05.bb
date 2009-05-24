DESCRIPTION = "DM6446 Codec Combo 2.05"
inherit sdk

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm6446_dvsdk_combos_2_05.tar.gz"

S = "${WORKDIR}/dm6446_dvsdk_combos_2_05"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "205"
PR = "r7"

# DM6446 combo has Makefile. We don't want to rebuild anything here.
do_compile() {
	echo "do nothing"
}

do_install() {
    install -d ${D}/dvsdk/dm6446_dvsdk_combos_2_05
    cp -pPrf ${S}/* ${D}/dvsdk/dm6446_dvsdk_combos_2_05
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True
FILES_${PN} = "/dvsdk/dm6446_dvsdk_combos_2_05/*"


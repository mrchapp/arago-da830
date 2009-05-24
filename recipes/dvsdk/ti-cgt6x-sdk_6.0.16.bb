require ti-cgt6x.inc
inherit sdk

# NOTE: This in internal ftp running on Brijesh's linux host. 
# This will not work outside TI network and the link should be remove once 
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/cg6x_6_0_16.tar.gz"

S = "${WORKDIR}/cg6x_6_0_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "6016"
PR = "r9"

do_install() {
    install -d ${D}/dvsdk/cg6x_6_0_16
    cp -pPrf ${S}/* ${D}/dvsdk/cg6x_6_0_16
}

FILES_${PN} = "/dvsdk/cg6x_6_0_16"
INSANE_SKIP_${PN} = True


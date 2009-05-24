inherit sdk
require ti-xdctools.inc

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/xdctools_3_10_03.tar.gz"

S = "${WORKDIR}/xdctools_3_10_03"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "310"
PR = "r12"

do_install() {
    install -d ${D}/dvsdk/xdctools_3_10_03
    cp -pPrf ${S}/* ${D}/dvsdk/xdctools_3_10_03
}

FILES_${PN} = "/dvsdk/xdctools_3_10_03"
INSANE_SKIP_${PN} = True


DESCRIPTION = "Codec Engine 2.23 for TI ARM/DSP processors"
inherit sdk

# tconf from xdctools dislikes '.' in pwd :/
PR = "r10"
PV = "223"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23.tar.gz "

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23"

do_compile () {
    echo "do not compile"
}

do_install() {
    install -d ${D}/dvsdk/codec_engine_2_23
    cp -pPrf ${S}/* ${D}/dvsdk/codec_engine_2_23
}

FILES_${PN} = "/dvsdk/codec_engine_2_23"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True


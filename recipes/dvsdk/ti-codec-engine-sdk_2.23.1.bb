DESCRIPTION = "Codec Engine 2.23.01 for TI ARM/DSP processors"
inherit sdk

# tconf from xdctools dislikes '.' in pwd :/
PR = "r9"
PV = "2231"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23_01.tar.gz "

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23_01"

do_compile () {
    echo "nothing to build"
}

do_install() {
    install -d ${D}/dvsdk/codec_engine_2_23_01
    cp -pPrf ${S}/* ${D}/dvsdk/codec_engine_2_23_01
}

FILES_${PN} = "/dvsdk/codec_engine_2_23_01"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True


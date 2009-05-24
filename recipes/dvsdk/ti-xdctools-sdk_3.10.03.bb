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
PR = "r13"

do_install() {
    install -d ${D}/${prefix}/dvsdk/xdctools_3_10_03
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/xdctools_3_10_03

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	  echo "# Where the RTSC tools package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
    echo "XDC_INSTALL_DIR=${prefix}/dvsdk/xdctools_3_10_03" >> ${STAGING_DIR_HOST}/ti-sdk-rules/xdc.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/xdctools_3_10_03"
INSANE_SKIP_${PN} = True
INHIBIT_PACKAGE_STRIP = "1"

DESCRIPTION = "DM355 Codec Combo 1.13"
inherit sdk

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm355_codecs_1_13_000.tar.gz \
		   file://dm355mm_1_30.patch;patch=1 \
		 "

S = "${WORKDIR}/dm355_codecs_1_13_000"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "113"
PR = "r10"

do_compile() {
	echo "Do nothing"
}

do_install() {
    install -d ${D}/${prefix}/dvsdk/dm355_codecs_1_13_000
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/dm355_codecs_1_13_000

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
		echo "# Where the codec servers are installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
    echo "CODEC_INSTALL_DIR=${prefix}/dvsdk/dm355_codecs_1_13_000" >> ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True
FILES_${PN} = "${prefix}/dvsdk/dm355_codecs_1_13_000/*"
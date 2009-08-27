inherit sdk

require ../ti-codecs-dm355.inc

PV = "${PV_pn-ti-codecs-dm355}"

do_install() {
    install -d ${D}/${prefix}/dvsdk/dm355_codecs_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/dm355_codecs_${PV}

    # Creates rules.make file
	  mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
		echo "# Where the codec servers are installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
    echo "CODEC_INSTALL_DIR=${prefix}/dvsdk/dm355_codecs_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/codec.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/dm355_codecs_${PV}/*"


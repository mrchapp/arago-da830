inherit sdk

BRANCH = "${BRANCH_pn-ti-dmai}"
SRCREV = "${SRCREV_pn-ti-dmai}"
SDKVER = "2_10_00_00"

require ../ti-dmai.inc

SRC_URI += "file://doxygen_templates.tar.gz \
            file://arago-tdox"

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
	echo "do nothing"
}

do_install() {
    find ${S} -name .svn -type d | xargs rm -rf 
    cp -pPrf ${WORKDIR}/doxygen_templates ${S}
    cp -pPrf ${WORKDIR}/arago-tdox ${S}/tdox
    chmod a+x ${S}/release.sh
    chmod a+x ${S}/tdox
    ${S}/release.sh ${SDKVER}
    install -d ${D}/${DVSDK_PATH}/dmai_${SDKVER}
    cp -pPrf ${S}/dmai_${SDKVER}/* ${D}/${DVSDK_PATH}/dmai_${SDKVER}

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where DMAI package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/dmai.Rules.make
    echo "DMAI_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dmai_${SDKVER}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dmai.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/dmai_${SDKVER}/*"
INSANE_SKIP_${PN} = True


inherit sdk

BRANCH = "${BRANCH_pn-ti-dmai}"
SRCREV = "${SRCREV_pn-ti-dmai}"
SDKVER = "2_10_00_00"

require ../ti-dmai.inc

DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

do_compile () {
	echo "do nothing"
}

do_install() {
    install -d ${D}/${DVSDK_PATH}/dmai_${SDKVER}
    cp -pPrf ${S}/dmai/* ${D}/${DVSDK_PATH}/dmai_${SDKVER}
    find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -name .svn -type d | xargs rm -rf
    date=`date`
    ystring=`echo "$date" | sed "s/.* \([0-9][0-9][0-9][0-9]\)$/\1/g"`

    c_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.c"`
    h_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.h"`
    xs_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.xs"`
    xdc_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.xdc"`
    bld_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.bld"`
    cfg_files=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "*.cfg"`
    Makefiles=`find ${D}/${DVSDK_PATH}/dmai_${SDKVER} -type f -name "Makefile*"`

    files="${c_files} ${h_files} ${xs_files} ${xdc_files} ${bld_files} ${cfg_files} ${Makefiles}"

    for file in $files
    do
        sed "s/\$(CPYYEAR)/$ystring/g" < $file > $file.new
        mv $file.new $file
    done

    # Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where DMAI package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/dmai.Rules.make
    echo "DMAI_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/dmai_${SDKVER}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/dmai.Rules.make
}

FILES_${PN} = "${DVSDK_PATH}/dmai_${SDKVER}/*"
INSANE_SKIP_${PN} = True


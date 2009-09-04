inherit sdk

require ../ti-linuxutils.inc

BASE_PV = "${BASE_PV_pn-ti-linuxutils}"
PV      = "${PV_pn-ti-linuxutils}"

do_compile () {
        echo "! Do not rebuild for now !"
}

do_install() {
    # Update cmem and sdma Rules.make to point correct location of main 
    # Rules.make.
    sed -i -e s:Rules.make:../Rules.make:g \
	${S}/packages/ti/sdo/linuxutils/cmem/Rules.make
    sed -i -e s:Rules.make:../Rules.make:g \
	${S}/packages/ti/sdo/linuxutils/sdma/Rules.make

    install -d ${D}/${prefix}/dvsdk/linuxutils_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/linuxutils_${PV}

    # Create rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
    echo "# Where the CMEM (contiguous memory allocator) package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/linuxutils.Rules.make
    echo "CMEM_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)/linuxutils_${PV}" >> ${STAGING_DIR_HOST}/ti-sdk-rules/linuxutils.Rules.make
}

FILES_${PN} = "${prefix}/dvsdk/linuxutils_${PV}"
INSANE_SKIP_${PN} = True

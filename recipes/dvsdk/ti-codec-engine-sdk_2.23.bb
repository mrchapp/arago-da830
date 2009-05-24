DESCRIPTION = "Codec Engine 2.23 for TI ARM/DSP processors"
inherit sdk

# tconf from xdctools dislikes '.' in pwd :/
PR = "r11"
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
    # Update cmem and sdma Rules.make to point correct location of main
    # Rules.make.
    sed -i -e s:Rules.make:../Rules.make:g \
        ${S}/cetools/packages/ti/sdo/linuxutils/cmem/Rules.make
    sed -i -e s:Rules.make:../Rules.make:g \
        ${S}/cetools/packages/ti/sdo/linuxutils/sdma/Rules.make

    install -d ${D}/${prefix}/dvsdk/codec_engine_2_23
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/codec_engine_2_23

	  echo "# Where the Codec Engine package is installed." > ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "CE_INSTALL_DIR=${prefix}/dvsdk/codec_engine_2_23" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "# Where the XDAIS package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "XDAIS_INSTALL_DIR=\$(CE_INSTALL_DIR)/cetools" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "# Where the DSP Link package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make    
    echo "LINK_INSTALL_DIR=\$(CE_INSTALL_DIR)/cetools" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "# Where the CMEM (contiguous memory allocator) package is installed." >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "CMEM_INSTALL_DIR=\$(CE_INSTALL_DIR)/cetools" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "" >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "# Where Framework Components product is installed."  >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make
    echo "FC_INSTALL_DIR=\$(CE_INSTALL_DIR)/cetools"  >> ${STAGING_DIR_HOST}/ti-sdk-rules/ce.Rules.make    
}

FILES_${PN} = "${prefix}/dvsdk/codec_engine_2_23"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True


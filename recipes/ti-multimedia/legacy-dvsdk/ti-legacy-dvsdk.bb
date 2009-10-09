DESCRIPTION = "This package creates Rules.make file and copies top label Makefile for rebuilding dvsdk components."

inherit sdk

PR="r8"

FILES=${@os.path.dirname(bb.data.getVar('FILE',d,1))}/files/dvsdk-rules
DVSDK_PATH="${@['${prefix}/dvsdk', bb.data.getVar('META_DVSDK_PATH', d, 1)][bool(bb.data.getVar('META_DVSDK_PATH', d, 1))]}"

DEPENDS  = "ti-codecs-dm355-sdk ti-codecs-dm365-sdk ti-dm365mm-module-sdk ti-xdctools-sdk ti-xdais-sdk ti-codec-engine-sdk ti-linuxutils-sdk ti-framework-components-sdk ti-dmai-sdk ti-dvsdk-demos-sdk"
DEPENDS_dm6446-evm  = "ti-codec-combo-dm6446-sdk  ti-cgt6x-sdk ti-dspbios-sdk ti-xdctools-sdk ti-xdais-sdk ti-codec-engine-sdk ti-linuxutils-sdk ti-framework-components-sdk ti-dmai-sdk"
DEPENDS_omap3evm    = "ti-codec-combo-omap3530-sdk ti-cgt6x-sdk ti-dspbios-sdk ti-xdctools-sdk ti-xdais-sdk ti-codec-engine-sdk ti-linuxutils-sdk ti-framework-components-sdk ti-dmai-sdk"
DEPENDS_beagleboard = "ti-codec-combo-omap3530-sdk ti-cgt6x-sdk ti-dspbios-sdk ti-xdctools-sdk ti-xdais-sdk ti-codec-engine-sdk ti-linuxutils-sdk ti-framework-components-sdk ti-dmai-sdk"

include ../ti-multimedia-common.inc

do_install () {
    mkdir -p  ${D}/${DVSDK_PATH}
    # Create Rules.make file by concatinating pkg Rules.make files.
    echo "# Define target platform." > ${D}/${DVSDK_PATH}/Rules.make
    echo "PLATFORM=${PLATFORM}" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "# The installation directory of the DVSDK." >> ${D}/${DVSDK_PATH}/Rules.make
    echo "DVSDK_INSTALL_DIR=${DVSDK_PATH}" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "# For backwards compatibility" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "DVEVM_INSTALL_DIR=\$(DVSDK_INSTALL_DIR)" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
  
    for file in `ls -1 ${STAGING_DIR_HOST}/ti-sdk-rules` ; do
      cat ${STAGING_DIR_HOST}/ti-sdk-rules/${file} >> ${D}/${DVSDK_PATH}/Rules.make
      echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    done

    echo "# The directory that points to your kernel source directory." >>  ${D}/${DVSDK_PATH}/Rules.make
    echo "LINUXKERNEL_INSTALL_DIR=${prefix}/${TARGET_SYS}/usr/src/kernel" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "# Where temporary Linux headers and libs are installed." >>  ${D}/${DVSDK_PATH}/Rules.make
    echo "LINUXLIBS_INSTALL_DIR=${prefix}/${TARGET_SYS}/usr" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. \"arm_v5t_le-\" or \"/opt/bin/arm_v5t_le-\"." >>  ${D}/${DVSDK_PATH}/Rules.make 
    echo "CSTOOL_DIR=${TOOLCHAIN_PATH}" >>  ${D}/${DVSDK_PATH}/Rules.make
    echo "CSTOOL_PREFIX=\$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "MVTOOL_DIR=\$(CSTOOL_DIR)" >>  ${D}/${DVSDK_PATH}/Rules.make
    echo "MVTOOL_PREFIX=\$(CSTOOL_PREFIX)" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "" >> ${D}/${DVSDK_PATH}/Rules.make
    echo "# Where to copy the resulting executables" >>  ${D}/${DVSDK_PATH}/Rules.make
    echo "EXEC_DIR=\$(HOME)/install/\$(PLATFORM)" >>  ${D}/${DVSDK_PATH}/Rules.make

    # copy Makefile and other scripts needed by Makefile
    mkdir -p ${D}/${DVSDK_PATH}/bin
    cp ${FILES}/Makefile ${D}/${DVSDK_PATH}/
    cp ${FILES}/info.sh  ${D}/${DVSDK_PATH}/bin
    cp ${FILES}/check.sh ${D}/${DVSDK_PATH}/bin
    mkdir -p ${D}/${DVSDK_PATH}/mapdmaq
    install -m 0755 ${FILES}/../mapdmaq ${D}/${DVSDK_PATH}/mapdmaq
}

FILES_${PN} = "${DVSDK_PATH}/*"

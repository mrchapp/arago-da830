require ti-dmai.inc

inherit module-base

# compile time dependencies
DEPENDS_omap3evm  += "alsa-lib ti-codec-engine ti-xdctools-native ti-dspbios-native ti-cgt6x-native ti-codec-combo-omap3530 virtual/kernel"
DEPENDS_beagleboard	+= "alsa-lib  ti-codec-engine ti-xdctools-native ti-dspbios-native ti-cgt6x-native ti-codec-combo-omap3530 virtual/kernel "
DEPENDS_dm6446-evm 	+= "alsa-lib  ti-codec-engine ti-xdctools-native ti-dspbios-native ti-cgt6x-native ti-codec-combo-dm6446 virtual/kernel "
DEPENDS_dm355-evm  	+= "alsa-lib ti-codec-engine ti-xdctools-native ti-codecs-dm355 virtual/kernel"
DEPENDS_da830-omapl137-evm 	+= "alsa-lib  ti-codec-engine ti-xdctools-native ti-dspbios-native ti-cgt6x-native ti-codec-combo-omapl137 virtual/kernel "

include ti-multimedia-common.inc

TARGET 			?= "all"
TARGET_omap3evm 	?= "o3530_al"
TARGET_beagleboard 	?= "o3530_al"
TARGET_dm355-evm 	?= "dm355_al"
TARGET_dm6446-evm 	?= "dm6446_al"
TARGET_da830-omapl137-evm 	?= "ol137_al"

PARALLEL_MAKE = ""

DMAI_INSTALL_DIR = ""

do_configure () {

	# PSP kernel is based on older DSS. we need to replace linux/omapfb.h with
	# mach/omapfb.h 

    if [ ${MACHINE} == "omap3evm" ] ; then
        sed -i -e s:linux/omapfb:mach/omapfb:g ${S}/dmai/packages/ti/sdo/dmai/linux/Display_fbdev.c
        sed -i -e s:linux/omapfb:mach/omapfb:g ${S}/dmai/packages/ti/sdo/dmai/linux/priv/_Display.h
    fi
}


do_compile () {

	cd ${S}
	make XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" clean

	#  TODO: Figure out how to pass the alsa include location, currently 
    #  LINUXLIBS_INSTALL_DIR is hard-coded for armv5te
	make CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
		CODEC_INSTALL_DIR="${CODEC_INSTALL_DIR}" \
		FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
		LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
		XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
		CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
		BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
		LINUXLIBS_INSTALL_DIR="${LINUXLIBS_INSTALL_DIR}" \
		USER_XDC_PATH="${USER_XDC_PATH}" \
		CROSS_COMPILE="${SDK_PATH}/bin/${TARGET_PREFIX}" \
		VERBOSE="true" \
		XDAIS_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \
		LINK_INSTALL_DIR="${CE_INSTALL_DIR}/cetools/packages/dsplink" \
		CMEM_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \
		LPM_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \	
		PLATFORM="${TARGET}"
}

do_install () {
	# install dmai apps on target
    install -d ${D}/dmai-apps
    cd ${S}/dmai
    make PLATFORM="${TARGET}" EXEC_DIR=${D}/${installdir}/dmai-apps install 
	install -m 0755 ${WORKDIR}/loadmodules-ti-dmai-${TARGET}.sh ${D}/${installdir}/dmai-apps/loadmodule.sh 

    cd ${S}/tests
    install -d ${D}/dmai-tests
    make PLATFORM="${TARGET}" EXEC_DIR=${D}/${installdir}/dmai-tests install 
	install -m 0755 ${WORKDIR}/loadmodules-ti-dmai-${TARGET}.sh ${D}/${installdir}/dmai-tests/loadmodule.sh 
}

pkg_postinst_ti-dmai-apps () {
	ln -sf ${CODEC_INSTALL_DIR}/* ${installdir}/dmai-apps/
}

do_stage () {
	install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
	cp -pPrf ${S}/dmai/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
}

# Disable QA check untils we figure out how to pass LDFLAGS in build
INSANE_SKIP_${PN} = True
INSANE_SKIP_ti-dmai-apps = True
INSANE_SKIP_ti-dmai-tests = True

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "ti-dmai-apps ti-dmai-tests"
FILES_ti-dmai-apps = "${installdir}/dmai-apps/*"
FILES_ti-dmai-tests = "${installdir}/dmai-tests/*"

# run time dependencies 
RDEPENDS_ti-dmai-apps_dm355-evm += "ti-dm355mm-module ti-cmem-module ti-codecs-dm355"
RDEPENDS_ti-dmai-apps_dm6446-evm += "ti-cmem-module ti-dsplink-module ti-codec-combo-dm6446"
RDEPENDS_ti-dmai-apps_omap3evm += "ti-cmem-module ti-dsplink-module ti-codec-combo-omap3530 ti-lpm-module ti-sdma-module"
RDEPENDS_ti-dmai-apps_beagleboard += "ti-cmem-module ti-dsplink-module ti-codec-combo-omap3530 ti-lpm-module ti-sdma-module"
RDEPENDS_ti-dmai-apps_da830-omapl137-evm += "ti-cmem-module ti-dsplink-module ti-codec-combo-ol137"


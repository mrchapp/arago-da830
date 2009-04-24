DESCRIPTION = "DMAI for TI ARM/DSP processors"

# compile time dependencies
DEPENDS_omap3evm 	= " ti-codec-engine ti-xdctools ti-dspbios ti-cgt6x ti-codec-combo-omap3530"
DEPENDS_dm6446-evm 	= " ti-codec-engine ti-xdctools ti-dspbios ti-cgt6x ti-codec-combo-dm6446"
DEPENDS_dm355-evm  	= " ti-codec-engine ti-xdctools ti-codec-combo-dm355"

PREFERED_VERSION_ti-codec-engine 	= "223"
PREFERED_VERSION_ti_dspbios 		= "533"
PREFERED_VERSION_ti_cgt6x   		= "60"
PREFERED_VERSION_ti_xdctools 		= "310"

# NOTE: Use Brijesh' DMAI development branch. The URL *must* be updated once
# we have stable DMAI 2.x on gforge.
SRCREV = "86"
SRC_URI = "svn://gforge.ti.com/svn/dmai/branches;module=BRIJESH_GIT_031809;proto=https;user=anonymous;pswd='' \
		file://loadmodules-ti-dmai-dm355.sh \
	"

S = "${WORKDIR}/BRIJESH_GIT_031809/davinci_multimedia_application_interface/dmai"
# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "120+svnr${SRCREV}"
PR = "r4"

# Define DMAI build time variables
TARGET 				?= "all"
TARGET_omap3evm 	?= "o3530_al"
TARGET_dm355-evm 	?= "dm355_al"
TARGET_dm6446-evm 	?= "dm6446_al"

CE_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine"
CODEC_dm355-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm355"
CODEC_omap3evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_dm6446-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm6446"
FC_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
DSPBIOS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-dspbios"
CGT6x_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-cgt6x"
XDCTOOLS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-xdctools"
USER_XDC_PATH="${CE_INSTALL_DIR}/examples"

PARALLEL_MAKE = ""
	
do_compile () {

	cd ${S}
	make XDC_INSTALL_DIR_${TARGET}="${XDCTOOLS_DIR}" clean

	#  TODO: Figure out how to pass the alsa include location, currently 
    #  LINUXLIBS_INSTALL_DIR is hard-coded for armv5te
	make CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
		CODEC_INSTALL_DIR="${CODEC}" \
		FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
		LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
		XDC_INSTALL_DIR="${XDCTOOLS_DIR}" \
		CODEGEN_INSTALL_DIR="${CGT6x_DIR}" \
		BIOS_INSTALL_DIR="${DSPBIOS_DIR}"\
		LINUXLIBS_INSTALL_DIR="${STAGING_DIR}/armv5te-none-linux-gnueabi/usr" \
		USER_XDC_PATH="${USER_XDC_PATH}" \
		CROSS_COMPILE="${META_SDK_PATH}/bin/${TARGET_PREFIX}" \
		VERBOSE="true" \
		XDAIS_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \
		LINK_INSTALL_DIR="${CE_INSTALL_DIR}/cetools/packages/dsplink" \
		CMEM_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \
		LPM_INSTALL_DIR="${CE_INSTALL_DIR}/cetools" \	
		PLATFORM="${TARGET}"
}

PLATFORM_dm355-evm ?="dm355"
PLATFORM_dm6446-evm ?="dm6446"
PLATFORM_omap3evm ?="omap3530"

do_install () {
    install -d ${D}/opt/ti-dmai-apps
	cd ${S}
    make PLATFORM="${TARGET}" EXEC_DIR=${D}/opt/ti-dmai-apps install 
	install -m 0755 ${WORKDIR}/loadmodules-ti-dmai-${PLATFORM}.sh ${D}/opt/ti-dmai-apps/loadmodule.sh 
}

do_stage () {
	install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
	cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"

# Disable QA check untils we figure out how to pass LDFLAGS in build
INSANE_SKIP_${PN} = True
INSANE_SKIP_ti-dmai-apps = True

PACKAGES =+ "ti-dmai-apps"
FILES_ti-dmai-apps = "/opt/ti-dmai-apps/*"

# run time dependencies 
RDEPENDS_ti-dmai-apps_dm355-evm =+ " ti-dm355mm-module ti-cmem-module"
RDEPENDS_ti-dmai-apps_dm6446-evm =+ " ti-cmem-module ti-dsplink-module ti-codec-combo-dm6446"
RDEPENDS_ti-dmai-apps_omap3evm =+ " ti-cmem-module ti-dsplink-module ti-codec-combo-omap3530 ti-lpm-module"


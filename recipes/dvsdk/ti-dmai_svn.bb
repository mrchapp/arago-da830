DESCRIPTION = "DMAI for TI ARM/DSP processors"

# compile time dependencies
DEPENDS_omap3evm 	+= " ti-codec-engine ti-xdctools ti-dspbios ti-cgt6x ti-codec-combo-omap3530 virtual/kernel "
DEPENDS_beagleboard	+= " ti-codec-engine ti-xdctools ti-dspbios ti-cgt6x ti-codec-combo-omap3530 virtual/kernel "
DEPENDS_dm6446-evm 	+= " ti-codec-engine ti-xdctools ti-dspbios ti-cgt6x ti-codec-combo-dm6446 virtual/kernel "
DEPENDS_dm355-evm  	+= " ti-codec-engine ti-xdctools ti-codec-combo-dm355 virtual/kernel"

PREFERRED_VERSION_ti-codec-engine 	= "2231"
PREFERRED_VERSION_ti_dspbios 		= "533"
PREFERRED_VERSION_ti_cgt6x   		= "60"
PREFERRED_VERSION_ti_xdctools 		= "310"

# NOTE: Use Brijesh' DMAI development branch. The URL *must* be updated once
# we have stable DMAI 2.x on gforge.
SRCREV = "86"
SRC_URI = "svn://gforge.ti.com/svn/dmai/branches;module=BRIJESH_GIT_031809;proto=https;user=anonymous;pswd='' \
		file://loadmodules-ti-dmai-dm355_al.sh \
		file://loadmodules-ti-dmai-dm6446_al.sh \
		file://loadmodules-ti-dmai-o3530_al.sh \
	"

S = "${WORKDIR}/BRIJESH_GIT_031809/davinci_multimedia_application_interface/dmai"
# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "120+svnr${SRCREV}"
PR = "r9"

# Define DMAI build time variables
TARGET 				?= "all"
TARGET_omap3evm 	?= "o3530_al"
TARGET_beagleboard 	?= "o3530_al"
TARGET_dm355-evm 	?= "dm355_al"
TARGET_dm6446-evm 	?= "dm6446_al"

CE_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine"
CODEC_dm355-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm355"
CODEC_omap3evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_beagleboard ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_dm6446-evm ="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm6446"
FC_INSTALL_DIR="${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
DSPBIOS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-dspbios"
CGT6x_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-cgt6x"
XDCTOOLS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-xdctools"
USER_XDC_PATH="${CE_INSTALL_DIR}/examples"

PARALLEL_MAKE = ""
	
do_compile () {

	cd ${S}
	make XDC_INSTALL_DIR="${XDCTOOLS_DIR}" clean

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

do_install () {
	# install dmai apps on target
    install -d ${D}/opt/ti/dmai-apps
	cd ${S}
    make PLATFORM="${TARGET}" EXEC_DIR=${D}/opt/ti/dmai-apps install 
	install -m 0755 ${WORKDIR}/loadmodules-ti-dmai-${TARGET}.sh ${D}/opt/ti/dmai-apps/loadmodule.sh 

	# install DMAI for dev pkg
	install -d ${D}/dmai
	cp -pPrf ${S}/* ${D}/dmai
}

pkg_postinst_ti-dmai-apps () {
	ln -sf /opt/ti/codec-combo/* /opt/ti/dmai-apps/
}

do_stage () {
	install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
	cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"

# Disable QA check untils we figure out how to pass LDFLAGS in build
INSANE_SKIP_${PN} = True
INSANE_SKIP_${PN}-dev = True
INSANE_SKIP_ti-dmai-apps = True

PACKAGES += "ti-dmai-apps"
FILES_ti-dmai-apps = "/opt/ti/dmai-apps/*"
FILES_${PN}-dev += "/dmai/*"

# run time dependencies 
RDEPENDS_ti-dmai-apps_dm355-evm += "ti-dm355mm-module ti-cmem-module ti-codec-combo-dm355"
RDEPENDS_ti-dmai-apps_dm6446-evm += "ti-cmem-module ti-dsplink-module ti-codec-combo-dm6446"
RDEPENDS_ti-dmai-apps_omap3evm += "ti-cmem-module ti-dsplink-module ti-codec-combo-omap3530 ti-lpm-module"
RDEPENDS_ti-dmai-apps_beagleboard += "ti-cmem-module ti-dsplink-module ti-codec-combo-omap3530 ti-lpm-module"


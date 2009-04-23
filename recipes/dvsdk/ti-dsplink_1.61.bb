DESCRIPTION = "DSPLINK 1.61 module for TI ARM/DSP processors"
inherit module

# compile and run time dependencies
DEPENDS 	= " virtual/kernel perl-native ti-dspbios ti-cgt6x"
RDEPENDS 	= " update-modules"

# tconf from xdctools dislikes '.' in pwd :/
PR = "r2"
PV = "161"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23.tar.gz \
		   file://loadmodules-ti-dsplink-apps.sh \
		   file://unloadmodules-ti-dsplink-apps.sh"

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23"
	
# DSPLINK - Config Variable for different platform
DSPLINKPLATFORM            ?= "DAVINCI"
DSPLINKPLATFORM_omap3evm   ?= "OMAP3530"
DSPLINKPLATFORM_dm6446-evm ?= "DAVINCI"

DSPLINKDSPCFG            ?= "DM6446GEMSHMEM"
DSPLINKDSPCFG_omap3evm   ?= "OMAP3530SHMEM"
DSPLINKDSPCFG_dm6446-evm ?= "DM6446GEMSHMEM"

DSPLINKGPPOS             ?= "MVL5G"
DSPLINKGPPOS_omap3evm    ?= "OMAPLSP"
DSPLINKGPPOS_dm6446-evm  ?= "MVL5G"

export DSPLINK="${S}/cetools/packages/dsplink"
STAGING_TI_DSPBIOS_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-dspbios"
STAGING_TI_CGT6x_DIR="${STAGING_DIR}/${BUILD_SYS}/ti-cgt6x"

do_compile() {

    # Run perl script to create appropriate makefiles (v1.60 and up)
    (
    cd ${DSPLINK}
    perl config/bin/dsplinkcfg.pl --platform=${DSPLINKPLATFORM} --nodsp=1 \
	--dspcfg_0=${DSPLINKDSPCFG} --dspos_0=DSPBIOS5XX \
	 --gppos=${DSPLINKGPPOS} --comps=ponslrm
    )

	  # dsplink makefile is hard-coded to use kbuild only on OMAP3530.
    # we are forcing  to use kbuild on other platforms.
	  sed -i  's/OMAP3530/${DSPLINKPLATFORM}/g' ${DSPLINK}/gpp/src/Makefile	

    # TODO :: KERNEL_CC, etc need replacing with user CC
    # TODO :: Need to understand why OBJDUMP is required for kernel module
    # Unset these since LDFLAGS gets picked up and used incorrectly.... need 
    # investigation

    unset CFLAGS CPPFLAGS CXXFLAGS 
    
    # Build the gpp user space library
    cd ${DSPLINK}/gpp/src/api
    make \
      CROSS_COMPILE="${TARGET_PREFIX}" \
      CC="${KERNEL_CC}" \
      AR="${KERNEL_AR}" \
      LD="${KERNEL_LD}" \
      COMPILER="${KERNEL_CC}" \
      ARCHIVER="${KERNEL_AR}" \
      KERNEL_DIR="${STAGING_KERNEL_DIR}" \
      clean all

    # Build the gpp kernel space (debug and release)
    cd ${DSPLINK}/gpp/src
    make \
      OBJDUMP="${TARGET_PREFIX}objdump" \
      CROSS_COMPILE="${TARGET_PREFIX}" \
      CC="${KERNEL_CC}" \
      AR="${KERNEL_AR}" \
      LD="${KERNEL_LD}" \
      COMPILER="${KERNEL_CC}" \
      ARCHIVER="${KERNEL_AR}" \
      KERNEL_DIR="${STAGING_KERNEL_DIR}" \
      clean default

    # Build the gpp samples
    cd ${DSPLINK}/gpp/src/samples
    make \
      BASE_TOOLCHAIN="${CROSS_DIR}" \
      BASE_CGTOOLS="${BASE_TOOLCHAIN}/bin" \
      OSINC_PLATFORM="${CROSS_DIR}/lib/gcc/${TARGET_SYS}/$(${TARGET_PREFIX}gcc -dumpversion)/include" \
      OSINC_TARGET="${BASE_TOOLCHAIN}/target/usr/include" \
      CROSS_COMPILE="${TARGET_PREFIX}" \
      CC="${KERNEL_CC}" \
      AR="${KERNEL_AR}" \
      LD="${KERNEL_LD}" \
      COMPILER="${KERNEL_CC}" \
      LINKER="${KERNEL_CC}" \
      ARCHIVER="${KERNEL_AR}" \
      KERNEL_DIR="${STAGING_KERNEL_DIR}" \
      clean all

    # Build the dsp library (debug and release)
    cd ${DSPLINK}/dsp/src
    make \
      BASE_CGTOOLS="${STAGING_TI_CGT6x_DIR}" \
      BASE_SABIOS="${STAGING_TI_DSPBIOS_DIR}" \
      clean all

    # Build the dsp samples (debug and release)
    cd ${DSPLINK}/dsp/src/samples
    make \
      BASE_CGTOOLS="${STAGING_TI_CGT6x_DIR}" \
      BASE_SABIOS="${STAGING_TI_DSPBIOS_DIR}" \
      clean all
}

do_install () {
    # DSPLINK driver - kernel module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    cp ${DSPLINK}/gpp/export/BIN/Linux/${DSPLINKPLATFORM}/RELEASE/dsplinkk.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/ 

    # DSPLINK library
    install -d ${D}/${libdir}
    install -m 0755 ${DSPLINK}/gpp/export/BIN/Linux/${DSPLINKPLATFORM}/RELEASE/dsplink.lib  ${D}/${libdir}

    # DSPLINK sample apps
    install -d ${D}/${datadir}/ti-dsplink
    
    cp ${DSPLINK}/gpp/export/BIN/Linux/${DSPLINKPLATFORM}/RELEASE/*gpp ${D}/${datadir}/ti-dsplink
    
    for i in $(find ${DSPLINK}/dsp/BUILD/ -name "*.out") ; do
        cp ${i} ${D}/${datadir}/ti-dsplink
    done

    # DSPLINK test app module un/load scripts
    install ${WORKDIR}/loadmodules-ti-dsplink-apps.sh ${D}/${datadir}/ti-dsplink
    install ${WORKDIR}/unloadmodules-ti-dsplink-apps.sh ${D}/${datadir}/ti-dsplink
}

pkg_postrm_ti-dsplink-module () {
    update-modules || true
}

pkg_postinst_ti-dsplink-module () {
    if [ -n "$D" ]; then
        exit 1
    fi
    depmod -a
    update-modules || true
}

INHIBIT_PACKAGE_STRIP = "1"

PACKAGES =+ " ti-dsplink-module ti-dsplink-apps"
FILES_ti-dsplink-module  = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/dsplinkk.ko"
FILES_ti-dsplink-apps = "${datadir}/ti-dsplink/* ${libdir}/dsplink.lib"

# Disable QA check untils we figure out how to pass LDFLAGS in build
INSANE_SKIP_${PN} = True
INSANE_SKIP_ti-dsplink-apps = True

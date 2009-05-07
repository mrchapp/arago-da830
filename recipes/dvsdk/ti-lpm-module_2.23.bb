DESCRIPTION = "LPM module for TI OMAP3 processors"

inherit module
# compile and run time dependencies
DEPENDS 	= " virtual/kernel perl-native ti-dsplink-module"
PREFERRED_VERSION_ti-dsplink = "161"
PR = "r8"
PV = "223"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23.tar.gz"

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23"

export DSPLINK="${S}/cetools/packages/dsplink"

LPMDSPPOWERSOC 				 ?= "omap3530"
LPMDSPPOWERSOC_omap3evm 	 ?= "omap3530"
LPMDSPPOWERSOC_beagleboard 	 ?= "omap3530"

do_compile () {
    # TODO :: KERNEL_CC, etc need replacing with user CC
    # TODO :: Need to understand why OBJDUMP is required for kernel module
    # Unset these since LDFLAGS gets picked up and used incorrectly.... need 
    # investigation

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${S}/cetools/packages/ti/bios/power/modules/${LPMDSPPOWERSOC}/lpm
    make \
      DSPLINK_REPO="${DSPLINK}/.." \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}" \
      .clean default
}

do_install () {

    # LPM/CMEM/SDMA drivers - kernel modules
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
	  install -m 0755 ${S}/cetools/packages/ti/bios/power/modules/${LPMDSPPOWERSOC}/lpm/*.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
}


pkg_postinst () {
    if [ -n "$D" ]; then
        exit 1
    fi
    depmod -a
    update-modules || true
}

pkg_postrm () {
    update-modules || true
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/*lpm*ko"
RDEPENDS += " ti-dsplink-module"


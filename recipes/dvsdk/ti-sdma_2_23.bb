DESCRIPTION = "SDMA module for TI OMAP3 processors"

inherit module
# compile and run time dependencies
DEPENDS 	= "virtual/kernel perl-native"
RDEPENDS 	= "update-modules"

# what is recipe provides
PROVIDES += "ti-sdma-module"
PACKAGES += "ti-sdma-module"

PR = "r0"
PV = "223"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23.tar.gz"

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23"

do_compile() {
    # SDMA - Build the sdma module
    # TODO :: KERNEL_CC, etc need replacing with user CC
    # TODO :: Need to understand why OBJDUMP is required for kernel module
    # Unset these since LDFLAGS gets picked up and used incorrectly.... need 
    # investigation

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${S}/cetools/packages/ti/sdo/linuxutils/sdma
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}" \
      UCTOOL_PREFIX="${TARGET_PREFIX}" \
      clean debug release
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/cetools/packages/ti/sdo/linuxutils/sdma/src/module/sdmak.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
}

pkg_postinst_ti-sdma-module () {
        if [ -n "$D" ]; then
                exit 1
        fi
        depmod -a
        update-modules || true
}

pkg_postrm_ti-sdma-module () {
        update-modules || true
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_ti-sdma-module = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/sdmak.ko"


DESCRIPTION = "CMEM module for TI ARM/DSP processors"

inherit module

# compile and run time dependencies
DEPENDS 	= "virtual/kernel perl-native"
RDEPENDS 	= "update-modules"

# what this recipe provides
PROVIDES += "ti-cmem-module"
PACKAGES += "ti-cmem-module"

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
    # CMEM - Build the cmem kernel module and associated test apps
    # TODO - Still need to clean up UCTOOLs - don't really want to build UC 
    # here - it's not good to just build with MVTOOLS (GLIBC) 
    # - note target default, doesn't get passed through to underlying makefiles
    # TODO :: KERNEL_CC, etc need replacing with user CC
    # TODO :: Need to understand why OBJDUMP is required for kernel module
    # Unset these since LDFLAGS gets picked up and used incorrectly.... need 
    # investigation

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${S}/cetools/packages/ti/sdo/linuxutils/cmem
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}" \
      UCTOOL_PREFIX="${TARGET_PREFIX}" \
      clean debug release
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/cetools/packages/ti/sdo/linuxutils/cmem/src/module/cmemk.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
}

pkg_postinst_ti-cmem-module () {
    if [ -n "$D" ]; then        
                exit 1
        fi
        depmod -a
        update-modules || true
}

pkg_postrm_ti-cmem-module () {
        update-modules || true
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_ti-cmem-module = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/cmemk.ko"


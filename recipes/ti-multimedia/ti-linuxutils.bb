inherit module

# compile and run time dependencies
DEPENDS 	= "virtual/kernel perl-native"
RDEPENDS 	= "update-modules"

require ti-linuxutils.inc

#This is a kernel module, don't set PR directly
MACHINE_KERNEL_PR_append = "a"

do_compile() {
    # TODO :: KERNEL_CC, etc need replacing with user CC
    # TODO :: Need to understand why OBJDUMP is required for kernel module
    # Unset these since LDFLAGS gets picked up and used incorrectly.... need
    # investigation

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    
   # Compile CMEM
    cd ${S}/packages/ti/sdo/linuxutils/cmem
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}" \
      UCTOOL_PREFIX="${TARGET_PREFIX}" \
      clean debug release

#   # Compile EDMA
#    cd ${S}/packages/ti/sdo/linuxutils/edma
#    make \
#      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
#      MVTOOL_PREFIX="${TARGET_PREFIX}" \
#      UCTOOL_PREFIX="${TARGET_PREFIX}" \
#      clean debug release
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    
    # Install CMEM
    install -m 0755 ${S}/packages/ti/sdo/linuxutils/cmem/src/module/cmemk.ko \
        ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp

    cd ${S}/packages/ti/sdo/linuxutils/cmem/apps
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}" \
      UCTOOL_PREFIX="${TARGET_PREFIX}" \
	  EXEC_DIR="${D}${prefix}/ti/ti-linuxutils-app/cmem-app" \
      install

#    # Install EDMA
#    install -m 0755 ${S}/packages/ti/sdo/linuxutils/cmem/src/module/edma.ko \
#        ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
#
#    cd ${S}/packages/ti/sdo/linuxutils/edma/apps
#    make \
#      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
#      MVTOOL_PREFIX="${TARGET_PREFIX}" \
#      UCTOOL_PREFIX="${TARGET_PREFIX}" \
#	  EXEC_DIR="${D}${prefix}/ti/ti-linuxutils-app/edma-app" \
#      install
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

FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/cmemk.ko"
PACKAGES += " ti-linuxutils-app" 
FILES_ti-linuxutils-app = "${prefix}/ti/ti-linuxutils-app/*/*"
INSANE_SKIP_ti-linuxutils-app = True

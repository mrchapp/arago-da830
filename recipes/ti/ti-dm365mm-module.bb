
require ti-dm365mm-module.inc
inherit module
# compile and run time dependencies
DEPENDS     = "virtual/kernel"
RDEPENDS     = "update-modules"

#This is a kernel module, don't set PR directly
MACHINE_KERNEL_PR_append = "a"

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    find ${S} -name "*.ko" -exec rm {} \; || true
    cd ${S}/module
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}";
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/module/dm365mmap.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
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

FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/dm365mmap.ko"

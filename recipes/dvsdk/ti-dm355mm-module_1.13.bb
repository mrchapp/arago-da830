DESCRIPTION = "User space DMA module for DM355"

inherit module
# compile and run time dependencies
DEPENDS 	= "virtual/kernel perl-native"
RDEPENDS 	= "update-modules"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/dm355_codecs_1_13_000.tar.gz \
		   file://dm355mm_1_30.patch;patch=1 \
	      "
S = "${WORKDIR}/dm355_codecs_1_13_000"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "113"
PR = "r10"

do_configure() {
	find ${S} -name "*.ko" -exec rm {} \; || true
	sed -i -e s:include:#include:g ${S}/dm355mm/Rules.make
}

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${S}/dm355mm/module
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}";
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/dm355mm/module/dm350mmap.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
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
FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/dm350mmap.ko"



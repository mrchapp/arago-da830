DESCRIPTION = "Package containing scripts to setup the development host and target board"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "\
	file://setup/setup.sh \
  	file://setup/common.sh \
  	file://setup/setup-host-check.sh \
  	file://setup/setup-minicom.sh \
  	file://setup/setup-package-install.sh \
  	file://setup/setup-targetfs-nfs.sh \
  	file://setup/setup-tftp.sh \
  	file://setup/setup-uboot-env.sh \
"

PR = "r10"

do_install () {
	install -d ${D}/${installdir}/bin
	install -m 0755 ${WORKDIR}/setup/* ${D}/${installdir}/bin/
        # Place the setup.sh script one directory higher
        mv ${D}/${installdir}/bin/setup.sh ${D}/${installdir}/setup.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"

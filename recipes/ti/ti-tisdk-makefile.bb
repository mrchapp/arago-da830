DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "\
	file://Makefile \
  	file://Rules.make \
"
PR = "r27"

do_install () {
	install -d ${D}/${installdir}
	install  ${WORKDIR}/Makefile ${D}/${installdir}/Makefile
	install  ${WORKDIR}/Rules.make ${D}/${installdir}/Rules.make
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"

DESCRIPTION = "Logging Framework for C++"
HOMEPAGE = "http://log4cplus.sourceforge.net"
LICENSE = "BSD/Apache"
DEPENDS = ""

inherit autotools

SRC_URI = "http://downloads.sourceforge.net/project/${PN}/${PN}-stable/${PV}/${P}-${PR}.tar.bz2"

S = "${WORKDIR}/${P}-${PR}"

do_install_append() {
	install -d ${D}${bindir}
}

SRC_URI[md5sum] = "d70eb0c43d0442aa6489f9de186652e5"
SRC_URI[sha256sum] = "467eca964aa4b0bd6f11c65a3bb50e3db1595549febad30df65a5414f605ae15"

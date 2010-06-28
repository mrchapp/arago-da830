DESCRIPTION = "SMBNetFS"
HOMEPAGE = "http://smbnetfs.sourceforge.net"
LICENSE = "GPL"
DEPENDS = "samba fuse"

inherit autotools

SRC_URI = "http://downloads.sourceforge.net/project/${PN}/${PN}/SMBNetFS-${PV}/${P}.tar.bz2"

do_install_append() {
	install -d ${D}${bindir}
}

SRC_URI[md5sum] = "bbbb3c2a9bdeffbd25552cce9cc6ab8d"
SRC_URI[sha256sum] = "8233e3e76a53f9b5b7a07b4964faaea842b2259d4c61ebc2db038bbb229cfee5"

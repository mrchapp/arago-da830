DESCRIPTION = "LibMMS is a common library for parsing mms:// and mmsh:// type network streams."
LICENSE = "LGPLv2.1"

DEPENDS = "glib-2.0"

SRC_URI = "http://downloads.sourceforge.net/project/libmms/libmms/${PV}/libmms-${PV}.tar.gz"
SRC_URI[md5sum] = "9f63aa363deb4874e072a45850161bff"
SRC_URI[sha256sum] = "01931b62172d7d7050fc9ef9b1b64162f3b6e9f6cc4415170192a32a0b7ea432"

inherit autotools

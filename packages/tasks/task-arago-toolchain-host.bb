DESCRIPTION = "Host packages for a standalone Arago SDK or external toolchain"
PR = "r2"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
#    gdb-cross-sdk \
    pkgconfig-sdk \
    opkg-sdk \
    libtool-sdk \
    "

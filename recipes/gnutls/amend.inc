# Set the suffix to specify amended packages
PR_append = "-arago1"

# Defined extra-dev package to pull GPLv3 components out of libgnutls-dev
PACKAGES =+ "${PN}-extra-dev"

LICENSE_${PN}-extra-dev = "GPLv3"

FILES_${PN}-extra-dev  = "${libdir}/pkgconfig/gnutls-extra.pc"
FILES_${PN}-extra-dev += "${libdir}/libgnutls-extra.so"
FILES_${PN}-extra-dev += "${libdir}/libgnutls-extra.la"
FILES_${PN}-extra-dev += "${includedir}/gnutls/extra.h"


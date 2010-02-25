DESCRIPTION = "gstd: a Gstreamer-based streaming server"
HOMEPAGE = "http://sourceforge.net/projects/harrier/"
MAINTAINER = "harrier-devel@lists.sourceforge.net"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

DEPENDS = "dbus dbus-glib gstreamer"
RDEPENDS_${PN} = "dbus dbus-glib gstreamer gst-plugins-base"
RRECOMENDS_${PN} = "gstreamer-ti"

SRCREV=d3a722e9c1db541718b3011e18995bb2ce9a64ea

PV = "1.0"
PR = "r9"
PR_append= "+gitr${SRCREV}"

FILES_${PN} += "${datadir}/dbus-1/*/*.service"
FILES_${PN}-dev += "${datadir}/dbus-1/interfaces/*.xml"

SRC_URI = "git://gstd.git.sourceforge.net/gitroot/gstd/gstd;protocol=git"
S = "${WORKDIR}/git"

inherit autotools_stage pkgconfig

# We don't want to run autoconf or automake, unless you have 
# automake > 1.11 with vala support
do_configure() {
    oe_runconf
}



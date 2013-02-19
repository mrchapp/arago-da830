DESCRIPTION = "GSstreamer sink plug-in for TI's Professional Audio Framework"
LICENSE = "Proprietary"

inherit autotools
require ti-paths.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "gstreamer gst-plugins-base ti-dsplink"

SRC_URI = "git://git@gitorious.tif.ti.com:tipafsink/gst-tipafsink.git;protocol=ssh \
    file://configure-dsplink.patch \
    file://no-linkercmd.patch"
SRCREV = "2ccbb6dad413313c0753aa044672c19c4648d169"
PV = "0.1+${PR}+gitr${SRCREV}"

S  ="${WORKDIR}/git"

TARGET_CC_ARCH += "${CFLAGS} ${LDFLAGS}"

EXTRA_OECONF = "--with-dsplink=${LINK_INSTALL_DIR}"

FILES_${PN} += "${libdir}/gstreamer-0.10/libtipafsink.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

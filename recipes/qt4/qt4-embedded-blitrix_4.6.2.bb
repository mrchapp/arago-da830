DESCRIPTION = "Qt for embedded using the blitrix NEON 2D library for TI devices"

# Add the base qt4-embedded directories to the list of
# directories to be searched for files.
# NOTE:  These are prepended because I want to pickup
#        the files in the qt4-embedded directories
#        before the "files" directory.  If this is
#        not prepended then the wrong version of files
#        such as linux.conf are obtained from the
#        qt4/files directory instead of
#        qt4/qt4-embedded-4.6.2 directory.
FILESPATHPKG =. "qt4-embedded-${PV}:qt4-embedded:"

# Add the arago-oe-dev directory to the base directories
# to search for files.  This keeps us from having to
# copy files from the OE snapshot to the Arago overlay.
FILESPATHBASE .= ":${OEBASE}/arago-oe-dev/recipes/qt4"

# Using relative path here causes bitbake to search in
# BBPATH for the first instance of qt4-embedded.inc rather
# than just within the current directory.
require recipes/qt4/qt4-embedded.inc

PR = "${INC_PR}.1"

# This recipe is valid for SOC_FAMILY devices that are based on the
# cortex-A8 with neon.
COMPATIBLE_MACHINE = "omap3|ti816x"

# Required so that this is a valid recipe for qt4-embedded target
# NOTE: In order for this recipe to be picked-up when qt4-embedded
#       is specified you should still set:
#       PREFERRED_PROVIDER_qt4-embedded = "qt4-embedded-blitrix"
#       in your configuration files.  i.e. arago-bom.conf
PROVIDES += "qt4-embedded"

# These are required to resolve the inclusion of these packages in
# the RDEPENDS sections of the task lists.
# NOTE: For now we only define the base package and dev package because
#       these are what get used in the task lists.  However, if at a
#       later date something wants to RDEPEND on qt4-embedded-dbg, etc
#       then this will need to be updated.
RPROVIDES_${PN} = "qt4-embedded"
RPROVIDES_${PN}-dev = "qt4-embedded-dev"

require recipes/qt4/qt-${PV}.inc

SRC_URI_append = "\
    https://gforge01.dal.design.ti.com/gf/download/frsrelease/416/3753/TI-Neon-BlitEngine-Qte-Arago-v1.4.patch;name=neonpatch \
    https://gforge01.dal.design.ti.com/gf/download/frsrelease/416/3782/TI-Neon-BlitEngine-Qte-v1.4.tar.gz;name=neontarball"

CXXFLAGS_append = " -I${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/include/ "
LDFLAGS_append = " -L${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/lib/ -lTICPUBLT_BX "

do_install_append () {
 	install -m 0644 ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/lib/* ${D}/${libdir}/
        install -d ${D}/${datadir}/ti/blitrix
	install -m 0755 ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/demo/bxdemo* ${D}/${datadir}/ti/blitrix
	install -m 0644 ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/demo/README ${D}/${datadir}/ti/blitrix
        cp -ar ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/demo/data ${D}/${datadir}/ti/blitrix
}

# This package will be renamed libticpublt-bx1.0 by the SHLIBS part of
# package.bbclass.
PACKAGES += "${PN}-libs "
FILES_${PN}-libs = "${libdir}/libTI*"
LICENSE_${PN}-libs = "TI"

ALLOW_EMPTY = "1"

FILES_${QT_BASE_NAME}-demos += "${datadir}/ti/blitrix/*"

# Add TI to the demos license because of the blitrix demo
LICENSE_${QT_BASE_NAME}-demos = "LGPLv2.1 TI"

INSANE_SKIP_${PN}-libs = True
INSANE_SKIP_${QT_BASE_NAME}-demos = True

pkg_postinst_${QT_BASE_NAME}-demos () {
 ln -s /usr/lib/libQtGuiE.so.4  /usr/lib/libQtGui.so.4
 ln -s /usr/lib/libQtCoreE.so.4  /usr/lib/libQtCore.so.4
}

SRC_URI[md5sum] = "eb651ee4b157c01696aa56777fc6e0e5"
SRC_URI[sha256sum] = "176f51ddb06dce67ab4b2efc6b327dc21ed8f764c5d97acc15ff1f907c2affae"

SRC_URI[neonpatch.md5sum] = "1084baa67d203dcd4342deb19b402331"
SRC_URI[neonpatch.sha256sum] = "aedc2fc0d3e446b2ad3aa81bfe0a4d81f6533086d3c44d521c392a34edaef711"

SRC_URI[neontarball.md5sum] = "62d957231be9d9a2612b639fbd0d9842"
SRC_URI[neontarball.sha256sum] = "0ab1b50e30ee23956c079baf73340c55dc62be371930bf09d478effd49ae22fb"

DESCRIPTION = "Matrix GUI"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrix_gui/"
SECTION = "multimedia"
PRIORITY = "optional"

PV = "1.0"
PR = "r1+svnr${SRCREV}"

SRCREV="35"

RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib"

SRC_URI = "svn://gforge.ti.com/svn/matrix_gui/;module=trunk;proto=https;user=anonymous;pswd='' \
file://init \
"

S = "${WORKDIR}/trunk"

INITSCRIPT_NAME = "matrix-gui"
INITSCRIPT_PARAMS = "defaults 99"

inherit qt4e update-rc.d

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/matrix_gui ${D}/${bindir}
	install -d ${D}/${datadir}/matrix/html
	install -m 0644 ${S}/*.html ${D}/${datadir}/matrix/html/
	install -d ${D}/${datadir}/matrix/images
	install -m 0644 ${S}/images/*.bmp ${D}/${datadir}/matrix/images/
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/matrix-gui
}

FILES_${PN} += "${datadir}/matrix/*"

DESCRIPTION = "AM1x Qt Sitara App Demo"
HOMEPAGE = "http://tiexpressdsp.com/index.php/Qt_Framework_Demo_Setup"
SECTION = "multimedia"
PRIORITY = "optional"

PV = "1.0"
PR = "r2+svnr${SRCREV}"

SRCREV="15"

RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib"

SRC_URI = "svn://gforge.ti.com/svn/gleslayer/;module=trunk/Packages/AM18x_SDK/Qt_sitara_app;proto=https;user=anonymous;pswd='' \
file://sitara.pro \
file://init \
"

S = "${WORKDIR}/trunk/Packages/AM18x_SDK/Qt_sitara_app/sitara"

INITSCRIPT_NAME = "am1x-demo"
INITSCRIPT_PARAMS = "defaults 99"

inherit qt4e update-rc.d

do_configure_prepend() {
	cp ${WORKDIR}/sitara.pro ${S}/
}

do_install() {
	install -d ${D}/usr/tests
	install -m 0755 ${S}/am1x-demo ${D}/usr/tests
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/am1x-demo
}

FILES_${PN} += "/usr/tests"
FILES_${PN}-dbg += "/usr/tests/.debug"

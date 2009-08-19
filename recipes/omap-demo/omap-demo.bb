DESCRIPTION = "OMAP35x LCD/touchscreen/audio demo"
PV = "1.0"
PR = "r4"

SRC_URI = "\
http://arago-project.org/files/short-term/demo/demo-app-src-${PV}.tar.gz \
http://arago-project.org/files/short-term/demo/images.tar.bz2 \
file://init \
"

INITSCRIPT_NAME = "omap-demo"
INITSCRIPT_PARAMS = "defaults 99"

S = "${WORKDIR}"

inherit update-rc.d

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -o omap-demo demo.c
}

do_install() {
	install -d ${D}/usr/tests
	install -m 0755 omap-demo ${D}/usr/tests
	install -d ${D}/usr/images
	install -m 0755 images/* ${D}/usr/images
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/omap-demo
}

PACKAGES += "${PN}-images"

FILES_${PN} += "/usr/tests"
FILES_${PN}-dbg += "/usr/tests/.debug"

FILES_${PN}-images += "/usr/images"

RDEPENDS_${PN} += "${PN}-images"

DESCRIPTION = "Initscripts for telnetd"
LICENSE = "MIT"
PR ="r2"

SRC_URI = "file://telnetd"

INITSCRIPT_NAME = "telnetd"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d

do_install () {
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/telnetd ${D}${sysconfdir}/init.d/telnetd
}

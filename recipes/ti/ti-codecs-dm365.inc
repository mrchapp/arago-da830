DESCRIPTION = "DM365 Codecs"

PV = "01_00_06"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI	= "${BASE_SRC_URI}/dm365_codecs_${PV}.tar.gz"

S = "${WORKDIR}/dm365_codecs_${PV}"
PR = "r2"

do_compile() {
	echo "Do nothing"
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True


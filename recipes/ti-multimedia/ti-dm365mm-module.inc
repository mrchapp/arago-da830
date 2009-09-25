DESCRIPTION = "User space DMA module for DM365"

PV = "01_00_01"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/dm365mm_${PV}.tar.gz "

S = "${WORKDIR}/dm365mm_${PV}"

# Yes, the xdc stuff still breaks with a '.' in PWD
PR = "r3"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True


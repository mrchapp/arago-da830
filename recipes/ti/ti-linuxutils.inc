DESCRIPTION = "MFP Linux utils for TI ARM/DSP processors"

PV = "2_25_00_03"
BASE_SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/linuxutils/${PV}/exports"
SRC_URI = "${BASE_SRC_URI}/linuxutils_${PV}.tar.gz "

S = "${WORKDIR}/linuxutils_${PV}"
PR = "r2"

INHIBIT_PACKAGE_STRIP = "1"

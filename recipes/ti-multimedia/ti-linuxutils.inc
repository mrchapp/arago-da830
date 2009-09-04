DESCRIPTION = "MFP Linux utils for TI ARM/DSP processors"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/linuxutils/${BASE_PV}/exports/linuxutils_${PV}.tar.gz "

S = "${WORKDIR}/linuxutils_${PV}"
PR = "r1"

INHIBIT_PACKAGE_STRIP = "1"

DESCRIPTION = "TI DSP Algorithm Interface Standard specification"

BASE_PV = "6.23"
PV = "6.23"

SRC_URI	= "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/xdais/${BASE_PV}/exports/xdais_${PV}.tar.gz"

S = "${WORKDIR}/xdais_${PV}"

PR = "r2"


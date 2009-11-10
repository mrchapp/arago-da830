DESCRIPTION = "TI DSP Algorithm Interface Standard specification"

PV = "6_25_00_07"
PVEXTENSION  = ""
BASE_SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/xdais/${PV}${PVEXTENSION}/exports"

SRC_URI	= "${BASE_SRC_URI}/xdais_${PV}${PVEXTENSION}.tar.gz"

S = "${WORKDIR}/xdais_${PV}${PVEXTENSION}"

PR = "r5"


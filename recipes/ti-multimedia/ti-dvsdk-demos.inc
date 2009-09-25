DESCRIPTION = "DVSDK Demo applications for TI ARM/DSP processors"

PV ?= "2_00_00_07"
BASE_SRC_URI ?= "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/dvsdk_demos_${PV}.tar.gz "

S = "${WORKDIR}/dvsdk_demos_${PV}"
PR = "r12"

INHIBIT_PACKAGE_STRIP = "1"

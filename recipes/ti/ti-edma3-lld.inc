DESCRIPTION ?= "EDMA3 LLD Components for TI ARM/DSP processors"

PR = "r2"

PV = "01_10_00_01"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/edma3_lld_${PV}.tar.gz "

# Set the source directory
S = "${WORKDIR}/edma3_lld_${PV}"

INHIBIT_PACKAGE_STRIP = "1"

DESCRIPTION ?= "TSPA DM6467 Codec Server, cs${CSVER}dm6467 for TI ARM/DSP Processors"

PR = "r4"
CSVER = "2"
PV = "1_00_00"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/cs${CSVER}dm6467_${PV}.tar.gz "

# Set the source directory
S = "${WORKDIR}/cs${CSVER}dm6467_${PV}"

INHIBIT_PACKAGE_STRIP = "1"

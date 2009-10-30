DESCRIPTION ?= "Framework Components for TI ARM/DSP processors"

PR = "r3"

PV = "2_25_00_01"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/framework_components_${PV},lite.tar.gz "

# Set the source directory
S = "${WORKDIR}/framework_components_${PV}"

INHIBIT_PACKAGE_STRIP = "1"

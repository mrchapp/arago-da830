DESCRIPTION ?= "Codec Engine for TI ARM/DSP processors"

PV = "2_25_00_01"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/codec_engine_${PV},lite.tar.gz "

# Set the source directory
S = "${WORKDIR}/codec_engine_${PV}"
PR = "r20"

INHIBIT_PACKAGE_STRIP = "1"

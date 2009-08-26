DESCRIPTION ?= "Codec Engine for TI ARM/DSP processors"

PR = "r18"

# Download codec_engine_${PV}.tar.gz from https://www-a.ti.com/downloads/sds_support/targetcontent/CE/ce_2_23/index.html and copy in Arago/OE download directory.

SRC_URI = "http://install.source.dir.com/codec_engine_${PV}.tar.gz "

# Set the source directory
S = "${WORKDIR}/codec_engine_${PV}"

INHIBIT_PACKAGE_STRIP = "1"

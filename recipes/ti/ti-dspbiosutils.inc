DESCRIPTION ?= "TI DSP/BIOS Utils for  TI ARM/DSP processors"

PR = "r1"

PV = "1_02_02"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/biosutils_${PV}.tar.gz "

# Set the source directory
S = "${WORKDIR}/biosutils_${PV}"


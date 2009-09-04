DESCRIPTION ?= "Framework Components for TI ARM/DSP processors"

PR = "r1"

# Download framework_components_${PV}.tar.gz from http://software-dl.ti.com/dsps/dsps_registered_sw/sdo_sb/targetcontent/FC/index.html and copy in Arago (or OE) download directory.

SRC_URI = "http://install.source.dir.com/framework_components_${PV}.tar.gz "

# Set the source directory
S = "${WORKDIR}/framework_components_${PV}"

INHIBIT_PACKAGE_STRIP = "1"

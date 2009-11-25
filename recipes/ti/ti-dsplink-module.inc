DESCRIPTION ?= "DSPLINK module for TI ARM/DSP processors"

#This is a kernel module, don't set PR directly

PV = "1_64_00_02"
BASE_SRC_URI = "http://install.source.dir.com"

SRC_URI = "${BASE_SRC_URI}/dsplink_linux_${PV}.tar.gz  \
		   file://loadmodules-ti-dsplink-apps.sh \
		   file://unloadmodules-ti-dsplink-apps.sh"

# Set the source directory
S = "${WORKDIR}/dsplink_linux_1_64_00_02"


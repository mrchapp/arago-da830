DESCRIPTION = "DVSDK Demo applications for TI ARM/DSP processors"

SRC_URI = "http://install.source.dir.com/dvsdk_demos_${PV}.tar.gz "
#SRC_URI = "ftp://gtftp01.gt.design.ti.com/sajesh/arago/dvsdk_demos_${PV}.tar.gz "

S = "${WORKDIR}/dvsdk_demos_${PV}"
PR = "r11"

INHIBIT_PACKAGE_STRIP = "1"

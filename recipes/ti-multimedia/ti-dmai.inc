DESCRIPTION = "DMAI for TI ARM/DSP processors"

SRC_URI = "svn://gforge.ti.com/svn/dmai/branches;module=${BRANCH};proto=https;user=anonymous;pswd='' \
		file://loadmodules-ti-dmai-dm355_al.sh \
		file://loadmodules-ti-dmai-dm6446_al.sh \
		file://loadmodules-ti-dmai-o3530_al.sh \
	"

S = "${WORKDIR}/${BRANCH}/davinci_multimedia_application_interface"
# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "svnr${SRCREV}"
PR = "r30"

INHIBIT_PACKAGE_STRIP = "1"

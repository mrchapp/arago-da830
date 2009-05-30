DESCRIPTION = "DMAI for TI ARM/DSP processors"

inherit module-base

# NOTE: Use Brijesh' DMAI development branch. The URL *must* be updated once
# we have stable DMAI 2.x on gforge.
SRCREV = "86"
SRC_URI = "svn://gforge.ti.com/svn/dmai/branches;module=BRIJESH_GIT_031809;proto=https;user=anonymous;pswd='' \
		file://loadmodules-ti-dmai-dm355_al.sh \
		file://loadmodules-ti-dmai-dm6446_al.sh \
		file://loadmodules-ti-dmai-o3530_al.sh \
	"

S = "${WORKDIR}/BRIJESH_GIT_031809/davinci_multimedia_application_interface/dmai"
# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "svnr${SRCREV}"
PR = "r20"

do_configure () {
    # If kernel version is greater than 2.6.28 replace mach/omapfb.h with
    # linux/omapfb.h

    if [ $(echo ${KERNEL_VERSION} | cut -c5,6) -gt 28 ] ; then
        sed -i -e s:mach/omapfb:linux/omapfb:g ${S}/packages/ti/sdo/dmai/linux/Display_fbdev.c
    fi

}



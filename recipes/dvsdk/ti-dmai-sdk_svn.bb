DESCRIPTION = "DMAI for TI ARM/DSP processors"
inherit sdk

# NOTE: Use Brijesh' DMAI development branch. The URL *must* be updated once
# we have stable DMAI 2.x on gforge.
SRCREV = "86"
SRC_URI = "svn://gforge.ti.com/svn/dmai/branches;module=BRIJESH_GIT_031809;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/BRIJESH_GIT_031809/davinci_multimedia_application_interface/dmai"
# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "svnr${SRCREV}"
PR = "r18"

do_install() {
    install -d ${D}/${prefix}/dvsdk/dmai_${PV}
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/dmai_${PV}
}

INHIBIT_PACKAGE_STRIP = "1"
FILES_${PN} = "${prefix}/dvsdk/dmai_${PV}/*"
INSANE_SKIP_${PN} = True


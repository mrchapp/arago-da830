require linux-omap3_psp2.inc

KVER = "2.6.32"
PSPREL = "3.0.0.4"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "b7c90b8db075f873dfc61fcbc30c203b3852e79d"

SRC_URI = "git://arago-project.org/git/people/sriram/ti-psp-omap.git;protocol=git;branch=OMAPPSP_03.00.00.04"

require linux-omap3_psp2.inc

KVER = "2.6.32"
PSPREL = "03.00.00.04"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "b7c90b8db075f873dfc61fcbc30c203b3852e79d"

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=OMAPPSP_03.00.00.04"

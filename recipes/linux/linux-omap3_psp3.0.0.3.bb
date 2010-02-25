require linux-omap3_psp2.inc

KVER = "2.6.31+2.6.32-rc5"
PSPREL = "3.0.0.3"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "a73318ee3672f46c3d463d9a5f2c75f028a406f3"

SRC_URI = "git://arago-project.org/git/people/sriram/ti-psp-omap.git;protocol=git;branch=OMAPPSP_03.00.00.03"

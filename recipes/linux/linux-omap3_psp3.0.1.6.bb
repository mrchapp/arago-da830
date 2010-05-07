require linux-omap3_psp2.inc

COMPATIBLE_MACHINE = "(omap3evm|dm3730-am3715-evm)"

KVER = "2.6.32"
PSPREL = "3.0.1.6"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "627293ad28604b22612f9a4a318f64cfab241e22"

SRC_URI = "git://arago-project.org/git/people/sriram/ti-psp-omap.git;protocol=git;branch=OMAPPSP_03.00.01.06"

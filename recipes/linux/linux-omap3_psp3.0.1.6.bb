require linux-omap3_psp2.inc

COMPATIBLE_MACHINE = "(omap3evm|dm3730-am3715-evm)"

KVER = "2.6.32"
PSPREL = "3.0.1.6"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "d843e61e5b3386a22b9e0f5c2bc2806ec367ab54"

SRC_URI = "git://arago-project.org/git/people/sriram/ti-psp-omap.git;protocol=git;branch=OMAPPSP_03.00.01.06"

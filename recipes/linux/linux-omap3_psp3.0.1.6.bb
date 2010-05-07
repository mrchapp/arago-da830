require linux-omap3_psp2.inc

COMPATIBLE_MACHINE = "(omap3evm|dm3730-am3715-evm)"

KVER = "2.6.32"
PSPREL = "3.0.1.6"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "00b5086b79e58ee8e9aa5dde15fd6a44a8d65fe3"

SRC_URI = "git://arago-project.org/git/people/sriram/ti-psp-omap.git;protocol=git;branch=OMAPPSP_03.00.01.06"

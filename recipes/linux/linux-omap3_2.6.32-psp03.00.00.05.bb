require linux-omap3_psp2.inc

COMPATIBLE_MACHINE = "(omap3evm|dm37x-evm|am37x-evm)"

KVER = "2.6.32"
PSPREL = "03.00.00.05"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "17bbb99ea76427dfed220ceeffc45a21cbc76d6c"

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=OMAPPSP_03.00.00.05"

require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "r30"
SRCREV = "c2aeffe9e835c5b6b275edc416951ce4a348b0fd"
KVER = "2.6.31+2.6.32-rc1"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
PV = "${KVER}-${PR}+gitr${SRCREV}"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

S = "${WORKDIR}/git"

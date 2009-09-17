require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "master"
SRCREV = "a547aeb440b399a5316f8d6f0534a2d3a5d21e42"
KVER = "2.6.30+2.6.31-rc7"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-static"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}-static"
PV = "${KVER}-${PR}+gitr${SRCREV}"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci-staging:linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

S = "${WORKDIR}/git"

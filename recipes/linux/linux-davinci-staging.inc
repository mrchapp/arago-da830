require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "r31"
SRCREV = "ac5232ccca310aaa8cae9cbca1791b69afab6c99"
KVER = "2.6.31+2.6.32-rc1"

PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI = "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

S = "${WORKDIR}/git"

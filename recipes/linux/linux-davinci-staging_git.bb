require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm355-evm)"

DEFAULT_PREFERENCE = "1"

SRCREV = "7626298147cabd95af41a7b15076625d57276bd5"

PV = "2.6.29+2.6.30-rc5-${PR}+gitr${SRCREV}"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/people/sneha/linux-davinci-staging.git;protocol=git \
           file://defconfig"

S = "${WORKDIR}/git"

require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm355-evm)"

DEFAULT_PREFERENCE = "1"

SRCREV = "009256edd65aec7e6645954acec2a49219808061"

PV = "2.6.30+2.6.31-rc2-${PR}+gitr${SRCREV}"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci-staging:linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/people/sneha/linux-davinci-staging.git;protocol=git;branch=next \
           file://defconfig"

S = "${WORKDIR}/git"

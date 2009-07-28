require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm355-evm)"

DEFAULT_PREFERENCE = "1"

SRCREV = "30aec0d7402844c41b505dd1e891cda98ee68dd2"

PV = "2.6.30+2.6.31-rc2-${PR}+gitr${SRCREV}"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci-staging:linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/people/sneha/linux-davinci-staging.git;protocol=git;branch=next-2 \
           file://defconfig"

S = "${WORKDIR}/git"

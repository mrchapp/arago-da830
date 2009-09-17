require linux.inc

DESCRIPTION = "Linux kernel for OMAPL1 processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "staging"
SRCREV = "1f3804f945375f699023056a462891b80ea2a36e"
KVER = "2.6.30+2.6.31-rc7"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI = "git://arago-project.org/git/people/sekhar/linux-omapl1.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

S = "${WORKDIR}/git"

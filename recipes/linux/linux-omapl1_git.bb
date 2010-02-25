require linux.inc

DESCRIPTION = "Linux kernel for OMAPL1 processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "master"
SRCREV = "6d39d2148abe848a5be5f21ee09f59523bda195f"
KVER = "2.6.32+2.6.33-rc4"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI = "git://arago-project.org/git/projects/linux-omapl1.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

S = "${WORKDIR}/git"

require multi-kernel.inc

DESCRIPTION = "Linux kernel for OMAPL1 processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm|da850-sdi)"

DEFAULT_PREFERENCE = "1"

BRANCH = "fbdev_sekhar"
BRANCH_da850-sdi = "da850sdi"

SRCREV = "1cb39e895c528c3f8bec1523fc0efbdaa9497dc4"
SRCREV_da850-sdi = "4043c639fb7419542b73e782152e17f5acd02428"

KVER = "2.6.32+2.6.33-rc4"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI = "git://arago-project.org/git/people/martin/linux-da830.git;protocol=git;branch=${BRANCH} \
           file://defconfig"

SRC_URI_append_da850-omapl138-evm = " \
          file://0001-i2c-slow.patch;patch=1 "

S = "${WORKDIR}/git"


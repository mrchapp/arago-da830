require multi-kernel.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm)"

DEFAULT_PREFERENCE = "1"

BRANCH = "r37"
SRCREV = "ac0c0869cd2c06f334657f4192625913c86d24a1"
KVER = "2.6.31+2.6.32-rc1"

PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI = "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
	file://0001-changed-driver-for-MMAP-buffer.patch;patch=1 \
	file://0002-Patch-for-adding-imagesize-corrected-for-MMAP-buffer.patch;patch=1 \
	file://0003-Patch-for-capture-driver-MMAP-buffer-allocation.-The.patch;patch=1 \
	file://0004-Patch-for-vpif-capture-driver-to-get-the-right-size-.patch;patch=1 \
	file://0005-DM365-MMAP-buffer-allocation-for-display-driver.patch;patch=1 \
	file://0006-DM365-capture-MMAP-buffer-allocation.patch;patch=1 \
	file://0007-Patch-MMAP-buffer-bufsize-support-upto-1080p-resolut.patch;patch=1 \
           file://defconfig"

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci:files:', 1)}"

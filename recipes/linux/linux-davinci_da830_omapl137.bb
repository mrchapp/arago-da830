require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(da830_omapl137-evm)"

DEFAULT_PREFERENCE = "1"

SRCREV = "ed400fa274a55d88a320ba752a94837b02defc41"

PV = "2.6.29+2.6.30-rc2+gitr${SRCREV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/khilman/linux-davinci.git;protocol=git \
           file://0001-ARM-Add-writethrough-dcache-support-for-ARM926EJS-processor.patch;patch=1 \
           file://0002-ARM-DaVinci-Remove-unused-compare-reg-defines.patch;patch=1 \
           file://0003-ARM-da830-Add-base-DA830-OMAP-L137-SoC-support.patch;patch=1 \
           file://0004-ARM-da830-Add-support-for-DA830-OMAP-L137-EVM.patch;patch=1 \
           file://defconfig"

S = "${WORKDIR}/git"

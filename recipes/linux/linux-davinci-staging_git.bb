require linux.inc

DESCRIPTION = "Linux kernel for Davinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(dm6446-evm|dm6467-evm|dm355-evm|davinci-sffsdr|dm355-leopard)"

DEFAULT_PREFERENCE = "1"

SRCREV = "cb85b33396778432fafed5c861f8561d8db563d6"

PV = "2.6.29+2.6.30-rc5-${PR}+gitr${SRCREV}"
PR = "r0"

FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci:files:', 1)}"

SRC_URI = "git://arago-project.org/git/people/sneha/linux-davinci-staging.git;protocol=git \
           file://defconfig"

SRC_URI_append_davinci-sffsdr = " \
           file://0001-USB-musb-cppi-bugfixes.patch;patch=1 \
           file://0002-ARM-Mark-unsupported-syscalls-as-IGNORE.patch;patch=1 \
           file://0003-Add-macros-for-enabling-a-UART.patch;patch=1 \
           file://0004-Davinci-Enable-MAC-address-to-be-specified-on-kerne.patch;patch=1 \
           file://0005-Add-DAS-Mini-DAS-and-AFE-USB-machine-types.patch;patch=1 \
           file://0006-ALSA-ASoC-DaVinci-Fix-SFFSDR-compilation-error.patch;patch=1 \
           file://0007-ALSA-ASoC-Davinci-Fix-SFFSDR-FPGA-module-codec-FS.patch;patch=1 \
           file://0008-ALSA-ASoC-Davinci-Fix-incorrect-machine-type-for.patch;patch=1 \
           file://0009-sound-ASoC-Fix-DaVinci-module-unload-error.patch;patch=1 \
           file://0010-Add-generic-FPGA-bitstream-loader-driver.patch;patch=1 \
           file://0011-Add-lyrvpss-example-driver-for-the-SFFSDR-board.patch;patch=1 \
           file://0012-Update-SFFSDR-to-support-FPGA-and-lyrvpss-drivers.patch;patch=1 \
          "

SRC_URI_append_dm355-leopard = " \
          file://0001-dm355-leopard-add-board-file-based-on-board-dm355-e.patch;patch=1 \
          file://vfpe.patch;patch=1 \
"

S = "${WORKDIR}/git"

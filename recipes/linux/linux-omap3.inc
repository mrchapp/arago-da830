SECTION = "kernel"
DESCRIPTION = "Linux kernel for OMAP3 EVM from PSP, based on linux-omap3 kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

inherit kernel

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git \
	file://defconfig"

PV = "${KVER}-psp${PSPREL}"

# Requires support for SOC_FAMILY matching in COMPATIBLE_MACHINE
COMPATIBLE_MACHINE = "omap3"

S = "${WORKDIR}/git"

# Do not include generic linux.inc, but copy defconfig in place
do_configure_prepend() {
	cp ${WORKDIR}/defconfig ${S}/.config
}

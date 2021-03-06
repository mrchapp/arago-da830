SECTION = "kernel"
DESCRIPTION = "Linux kernel for OMAP3 EVM from PSP, based on linux-omap2 kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/linux-omap3-psp/${MACHINE}/${PSPREL}"

PV = "${KVER}"
PR = "psp${PSPREL}"

COMPATIBLE_MACHINE = "omap3evm"

S = "${WORKDIR}/git"

inherit kernel

do_configure() {
	make omap3_evm_defconfig
}

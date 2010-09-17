SECTION = "kernel"
DESCRIPTION = "Linux kernel for OMAPL1 EVM from PSP, based on linux-omapl1 kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

inherit kernel

SRC_URI = "git://arago-project.org/git/projects/linux-omapl1.git;protocol=git \
	file://defconfig"

# Requires support for SOC_FAMILY matching in COMPATIBLE_MACHINE
COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

S = "${WORKDIR}/git"

# Do not include generic linux.inc, but copy defconfig in place
addtask setup_defconfig before do_configure after do_patch
do_setup_defconfig() {
	cp ${WORKDIR}/defconfig ${S}/.config
}

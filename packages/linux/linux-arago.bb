SECTION = "kernel"
DESCRIPTION = "Linux kernel (non-existent) for Arago virtual machine"

PV = "2.6"

inherit kernel

COMPATIBLE_MACHINE = "arago"

do_setscene() {
	echo "============================================================================"
	echo "Arago is a virtual machine for multiple platforms and it can't have a kernel"
	echo "Please specify MACHINE=... on the command line with the actual machine name"
	echo "for which to build the kernel, e.g. MACHINE=omap3evm bitbake virtual/kernel"
	echo "============================================================================"
	exit 1
}

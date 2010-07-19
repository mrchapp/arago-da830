require u-boot-omap3.inc

UVER = "1.3.4"
PSPREL = "2.1.0.2"
PV = "${UVER}"
PR ="psp${PSPREL}-r3"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-omap3-psp/${MACHINE}/${PSPREL}"

SRC_URI = "git://www.denx.de/git/u-boot-arm.git;protocol=git"
SRCREV = "cf6ec699a6dc21a538b039a0392cd38132072090"

SRC_URI_append_omap3evm = " \
file://0001-Patch-for-booting-Linux-using-NFS-filesystem.patch \
file://0002-Fix-issues-and-add-new-commands-for-onenand.patch \
file://0003-Setting-the-TFTP-MTU-size-to-512.patch \
file://0004-Changes-for-single-binary-image-for-u-boot-for-NAND.patch \
file://0005-Fix-for-nandswecc-writes.patch \
file://0006-Fix-for-timeout-issues-on-U-Boot.patch \
file://0007-Fix-for-running-examples.patch \
file://0008-Fix-for-DHCP-failure.patch \
"

COMPATIBLE_MACHINE = "omap3evm"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

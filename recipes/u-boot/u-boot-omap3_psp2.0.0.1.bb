require u-boot-omap3.inc

UVER = "1.3.4"
PSPREL = "2.0.0.1"
PV = "${UVER}"
PR ="psp${PSPREL}-r3"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-omap3-psp/${MACHINE}/${PSPREL}"

SRC_URI = "git://www.denx.de/git/u-boot.git;protocol=git"
SRCREV = "cd82919e6c8a73b363a26f34b734923844e52d1c"

SRC_URI_append_omap3evm = " \
file://0001-OMAP35x-Patchset-for-OMAP3-support-on-Denx-Baselin.patch \
file://0002-OMAP35x-Add-support-for-OneNAND-and-TFTP.patch \
file://0003-OMAP-35x-Fix-OnenNAND-help-messages.patch \
file://0004-OMAP35x-Add-Micron-NAND-support.patch \
file://0005-OMAP-35x-Support-for-NAND-OneNAND-unified-binary.patch \
file://0006-OMAP35x-Fix-Ctrl-C-not-functional-issue.patch \
"

COMPATIBLE_MACHINE = "omap3evm"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

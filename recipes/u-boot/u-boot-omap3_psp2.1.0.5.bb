require u-boot-omap3.inc

UVER = "1.3.4"
PSPREL = "2.1.0.5"
PV = "${UVER}"
PR ="psp${PSPREL}-r1"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-omap3-psp/${MACHINE}/${PSPREL}"

SRC_URI = "git://www.denx.de/git/u-boot-arm.git;protocol=git"
SRCREV = "cf6ec699a6dc21a538b039a0392cd38132072090"

SRC_URI_append_omap3evm = " \
file://0001-Patch-for-booting-Linux-using-NFS-filesystem.patch;patch=1 \
file://0002-Fix-issues-and-add-new-commands-for-onenand.patch;patch=1 \
file://0003-Setting-the-TFTP-MTU-size-to-512.patch;patch=1 \
file://0004-Changes-for-single-binary-image-for-u-boot-for-NAND.patch;patch=1 \
file://0005-Fix-for-nandswecc-writes.patch;patch=1 \
file://0006-Fix-for-timeout-issues-on-U-Boot.patch;patch=1 \
file://0007-Fix-for-running-examples.patch;patch=1 \
file://0008-Fix-for-DHCP-failure.patch;patch=1 \
file://0009-net-Fix-download-command-parsing.patch;patch=1 \
file://0010-bootm-Reduce-the-unnecessary-memmove.patch;patch=1 \
file://0011-net-Fix-TftpStart-ip-filename-bug.patch;patch=1 \
file://0012-OneNAND-Add-missing-mtd-info-struct-before-calling.patch;patch=1 \
file://0013-Bad-block-indication-offset-correction.patch;patch=1 \
file://0014-Fix-for-timout-value-for-tftp.patch;patch=1 \
file://0015-Update-CFLAGS-for-march-switch-change-to-armv7-a.patch;patch=1 \
"

COMPATIBLE_MACHINE = "omap3evm"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

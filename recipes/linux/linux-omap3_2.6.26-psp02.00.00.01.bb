KVER = "2.6.26"
PSPREL = "02.00.00.01"

require linux-omap3-psp2.inc

SRCREV = "9882ca1b309ef95e528fe048499b831f99a688a3"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/tmlind/linux-omap-2.6.git;protocol=git"

SRC_URI_append_omap3evm = " \
file://0001-OMAP35x-USB-USB-patches-on-the-2.6.26-baseline.patch \
file://0002-OMAP35x-USB-configuration-options-update.patch \
file://0003-OMAP35x-Power-Management-Patches.patch \
file://0004-OMAP35x-Fix-compilation-warnings.patch \
file://0005-OMAP35X-Add-OMAP35x-configuration.patch \
file://0006-OMAP35x-Add-Audio-Codec-support.patch \
file://0007-OMAP35x-Add-ISP-camera-support.patch \
file://0008-OMAP35x-Add-resizer-driver-Support.patch \
file://0009-OMAP35x-Add-Display-library-and-encoder-support.patch \
file://0010-OMAP35x-Add-V4L2-drivers-for-video-pipeline.patch \
file://0011-OMAP35x-Add-support-BT656-Capture-interface.patch \
file://0012-OMAP35x-Workaround-for-video-crash-issue.patch \
file://0013-OMAP-35x-Update-default-kernel-configuration.patch \
file://0014-OMAP35x-Add-FBDEV-changes.patch \
file://0015-OMAP-35x-Display-driver-makefile-update.patch \
file://0016-OMAP35x-Fix-for-FB-Sync-Loss-issue.patch \
file://0017-OMAP-35x-Merging-fixes-from-OMAP-mailing-list.patch \
file://0018-OMAP35x-Fix-for-Capture-Plug-UNplug-issue.patch \
file://0019-OMAP-35x-Update-default-OMAP-EVm-configuration.patch \
file://0020-OMAP35x-Enable-NAND-Support.patch \
file://0021-OMAP35x-fix-compiler-warning-with-ALSA-Mixer.patch \
file://0022-OMAP35x-Fix-USB-gadget-driver-registration-failure.patch \
file://0023-OMAP35x-Fix-for-lcd-clr-frame-rate-and-mem-order.patch \
file://0024-OMAP35x-Add-MMC-NAND-to-default-config.patch \
"

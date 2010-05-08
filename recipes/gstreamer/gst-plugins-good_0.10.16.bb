require gst-plugins.inc

SRC_URI += "\
file://0001-Add-input-src-property-to-specify-capture-input.patch;patch=1 \

file://0002-Fix-OSS-support-to-handle-all-supported-sample-rates.patch;patch=1 \

file://0003-Add-support-for-DaVinci-platforms-with-MontaVista-ba.patch;patch=1 \

file://0004-Try-progressive-mode-first-for-Component-input-sourc.patch;patch=1  \

file://0005-Try-progressive-mode-first-for-DM6467T.patch;patch=1 \

file://0006-Support-NV12-capture-on-DM365-using-the-IPIPE-in-on-.patch;patch=1 \

file://0007-Fix-for-streaming-encode-with-rtph264pay.patch;patch=1 \

file://0008-Add-support-for-NV16-colorspace.patch;patch=1 \

file://0009-Set-bytesperline-and-sizeimage-before-calling-the-VI.patch;patch=1 \
file://0010-Update-gst_v4l2_get_norm-to-handle-DM6467T-and-DM365.patch;patch=1 \
file://0011-Add-V4L2-ioctl-calls-to-initialize-DM6467T-and-DM365.patch;patch=1 \
file://0012-Disable-video-device-polling-by-default-on-DM6467T.patch;patch=1 \

"
SRC_URI[md5sum] = "f0af97464bb6e060a99df39bb21b7a42"
SRC_URI[sha256sum] = "64bd5177913b8ccaa1502e88a255adb3e6024202ba8983e6a956061e57fe1640"

PR = "r4"

OE_ALLOW_INSECURE_DOWNLOADS = "1"

# this recipe depends on kernel
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "gst-plugins-base virtual/kernel"

PLATFORM_dm6446        = "dm6446"
PLATFORM_dm6467        = "dm6467"
PLATFORM_omap3         = "omap3530"
PLATFORM_dm355         = "dm355"
PLATFORM_dm365         = "dm365"
PLATFORM_omapl137      = "omapl137"
PLATFORM_omapl138      = "omapl138"
PLATFORM              ?= "<UNDEFINED_PLATFORM>"
CPPFLAGS_append = " -DPlatform_${PLATFORM} -I${STAGING_KERNEL_DIR}/include"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-x " 


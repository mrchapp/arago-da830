require gst-plugins.inc

SRC_URI += "\
file://0001-rtph264pay-fix-for-streaming-encode.patch;patch=1 \

file://0002-v4l2-reset-bytesperline-to-0-before-each-call-to-S_F.patch;patch=1 \

file://0003-v4l2src-add-input-src-property-to-specify-capture-in.patch;patch=1 \

file://0004-v4l2src-keep-track-of-the-input-ID-that-will-be-used.patch;patch=1  \

file://0005-osssink-handle-all-supported-sample-rates.patch;patch=1 \

file://0006-v4l2src-add-support-for-DaVinci-platforms-using-MVL-.patch;patch=1 \

file://0007-v4l2src-support-NV12-capture-on-DM365-using-the-IPIP.patch;patch=1 \

file://0008-v4l2src-accept-EPERM-as-a-non-fatal-error-for-VIDIOC.patch;patch=1 \

file://0009-v4l2src-try-progressive-mode-first-for-component-inp.patch;patch=1 \
file://0010-v4l2src-add-support-for-NV16-colorspace.patch;patch=1 \
file://0011-v4l2src-set-bytesperline-and-sizeimage-before-callin.patch;patch=1 \
file://0012-v4l2src-update-gst_v4l2_get_norm-to-handle-DM6467T-a.patch;patch=1 \
file://0013-v4l2src-add-V4L2-ioctl-calls-to-initialize-capture-d.patch;patch=1 \
file://0014-v4l2src-disable-video-device-polling-by-default-on-D.patch;patch=1 \

"

PR = "r7"

OE_ALLOW_INSECURE_DOWNLOADS = "1"

# this recipe depends on kernel
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "gst-plugins-base virtual/kernel"

CPPFLAGS_dm6467_append = " -DPlatform_dm6467 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm365_append = " -DPlatform_dm365 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm355_append = " -DPlatform_dm355 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm6467t_append = " -DPlatform_dm6467t -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_omapl138_append = " -DPlatform_omapl138"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-x " 


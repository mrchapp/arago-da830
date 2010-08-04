DESCRIPTION = "Task to add base gstreamer and TI plugins"
LICENSE = "MIT"
PR = "r18"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# install gstreamer ti plugin on supported platforms
GST_TI_PLUGIN  ?= ""
GST_TI_PLUGIN_dm6446   = "gstreamer-ti"
GST_TI_PLUGIN_dm6467   = "gstreamer-ti"
GST_TI_PLUGIN_dm355    = "gstreamer-ti"
GST_TI_PLUGIN_dm365    = "gstreamer-ti"
GST_TI_PLUGIN_omapl137 = "gstreamer-ti"
GST_TI_PLUGIN_omapl138 = "gstreamer-ti"
GST_TI_PLUGIN_dm37x-evm = "gstreamer-ti"
GST_TI_PLUGIN_beagleboard = "gstreamer-ti"
GST_TI_PLUGIN_omap3evm = "gstreamer-ti"

RDEPENDS_${PN} = " \
    gstreamer \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-bad \
    gst-plugins-ugly \
    gst-plugins-base-meta \
    gst-plugins-good-meta \
    gst-plugins-bad-meta \
    gst-plugins-ugly-meta \
    ${GST_TI_PLUGIN} \
    "

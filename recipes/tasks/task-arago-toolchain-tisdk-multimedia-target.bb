DESCRIPTION = "Task to build and install header and libs in sdk"
PR = "r3"
LICENSE = "MIT"

inherit task

RDEPENDS_${PN} = "\
    gstreamer-dev \
    gst-plugins-base-dev \
    gst-plugins-good-dev \
    gst-plugins-ugly-dev \
    gst-plugins-bad-dev \
    "

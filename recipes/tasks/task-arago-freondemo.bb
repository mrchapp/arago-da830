DESCRIPTION = "Arago Freon demo app"
PR = "r2"

inherit task

RDEPENDS_${PN} = "\
    freondemo \
    gst-plugins-base-meta \
    gst-plugins-bad-meta \
    gst-plugins-good-meta \
    gst-plugins-ugly-meta \
    "

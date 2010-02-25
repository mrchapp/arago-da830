DESCRIPTION = "Arago Freon Demo Image"
PR = "r1"

inherit task

ARAGO_QT = "\
    qt4-embedded \
    "

FREONDEMO = "\
    freondemo \
    gst-plugins-base-meta \
    gst-plugins-bad-meta \
    gst-plugins-good-meta \
    gst-plugins-ugly-meta \
    qt4-embedded-plugin-mousedriver-tslib \
    "

RDEPENDS_${PN} = "\
    ${ARAGO_QT} \
    ${FREONDEMO} \
    "

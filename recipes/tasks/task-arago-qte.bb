DESCRIPTION = "Task to add Qt/Embedded"
PR = "r2"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    libxml2 \
    "

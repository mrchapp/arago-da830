DESCRIPTION = "Task to add Qt/Embedded and basic plugins"
PR = "r5"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \
    qt4-embedded-plugin-decoration-default \
    qt4-embedded-plugin-decoration-windows \
    "

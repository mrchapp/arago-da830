DESCRIPTION = "Task to add Qt/Embedded and basic plugins"
PR = "r6"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \
    "

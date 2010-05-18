DESCRIPTION = "Task to add Qt/Embedded and basic plugins"
LICENSE = "MIT"
PR = "r7"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \
    "

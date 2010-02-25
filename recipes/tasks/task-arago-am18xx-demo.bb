DESCRIPTION = "Arago AM18xx Demo Image"
PR = "r1"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    am18xx-demo \
    "

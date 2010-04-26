DESCRIPTION = "Task for OMAP demo app (image viewer w/ touchscreen/sound tests)"
PR = "r1"

inherit task

RDEPENDS_${PN} = "\
    omap-demo \
    "

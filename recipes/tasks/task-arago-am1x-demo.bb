DESCRIPTION = "Task for AM18xx/AM17xx demo app"
PR = "r3"

inherit task

RDEPENDS_${PN} = "\
    am1x-demo \
    "

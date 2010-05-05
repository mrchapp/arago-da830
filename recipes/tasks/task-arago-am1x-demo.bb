DESCRIPTION = "Task for AM18xx demo app"
PR = "r2"

inherit task

RDEPENDS_${PN} = "\
    am18xx-demo \
    "

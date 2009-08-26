DESCRIPTION = "Task to get some extras apps, which are not part of the Core set"
PR = "r1"

inherit task

RDEPENDS_${PN} = "\
    strace \
    gdb \
    "

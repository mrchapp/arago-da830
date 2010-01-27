DESCRIPTION = "Extended task to add dvsdk components"
PR = "r15"

inherit task

DVSDK_TARGET_PACKAGE = "ti-dvsdk-demos"

RDEPENDS_${PN} = "\
    task-arago-demo \
    ${DVSDK_TARGET_PACKAGE} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    "

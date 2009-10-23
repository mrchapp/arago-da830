DESCRIPTION = "Extended task to add dvsdk components"
PR = "r13"

inherit task

DVSDK_TARGET_PACKAGE_dm355-evm = "ti-dvsdk-demos"
DVSDK_TARGET_PACKAGE_dm365-evm = "ti-dvsdk-demos"
DVSDK_TARGET_PACKAGE_dm6467t-evm = "ti-dvsdk-demos"

RDEPENDS_${PN} = "\
    task-arago-demo \
    ${DVSDK_TARGET_PACKAGE} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    "

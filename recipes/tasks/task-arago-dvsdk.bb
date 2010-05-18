DESCRIPTION = "Extended task to add DVSDK components"
LICENSE = "MIT"
PR = "r19"

inherit task

# DVSDK demos are for DaVinci only
DVSDK_TARGET_PACKAGE_dm355 = "ti-dvsdk-demos"
DVSDK_TARGET_PACKAGE_dm365 = "ti-dvsdk-demos"
DVSDK_TARGET_PACKAGE_dm6467 = "ti-dvsdk-demos"

DVSDK_TARGET_PACKAGE = "ti-dmai-apps ti-dmai-tests"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "\
    ${DVSDK_TARGET_PACKAGE} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    "

DESCRIPTION = "Extended task to add DVSDK components"
PR = "r16"

inherit task

DVSDK_TARGET_PACKAGE = "ti-dvsdk-demos"

# omap3 seems to be missing dvsdk-demos
DVSDK_TARGET_PACKAGE_omap3 = "ti-dmai-apps ti-dmai-tests"

RDEPENDS_${PN} = "\
    ${DVSDK_TARGET_PACKAGE} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    "

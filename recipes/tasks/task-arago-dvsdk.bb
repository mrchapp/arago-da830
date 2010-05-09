DESCRIPTION = "Extended task to add DVSDK components"
LICENSE = "MIT"
PR = "r20"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DVSDK_DSP_COMPONENTS ?= ""
DVSDK_DSP_COMPONENTS_omap3  = "ti-dsplink-examples"
DVSDK_DSP_COMPONENTS_dm6446 = "ti-dsplink-examples"
DVSDK_DSP_COMPONENTS_dm6467 = "ti-dsplink-examples"

DVSDK_DEMO ?= ""
DVSDK_DEMO_dm365 = "ti-dvsdk-demos"
DVSDK_DEMO_dm6467 = "ti-dvsdk-demos"
DVSDK_DEMO_dm355 = "ti-dvsdk-demos"

RDEPENDS_${PN} = "\
    ti-codec-engine-examples \
    ti-dmai-apps \
    ${DVSDK_DSP_COMPONENTS} \
    ${DVSDK_DEMO} \
    "
RRECOMMENDS_${PN} = "\
    kernel-modules \
    "

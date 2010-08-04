DESCRIPTION = "Task to build and install multimedia source (or development header) packages on host"
PR = "r16"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_SOURCETREE = ""

MULTIMEDIA_SOURCETREE_dm37x-evm = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-omap3530-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    ti-local-power-manager-src \
    "

MULTIMEDIA_SOURCETREE_dm355 = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-dm355-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    ti-dvsdk-demos-src \
#    ti-dvtb-src \
    "

MULTIMEDIA_SOURCETREE_dm365 = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-dm365-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-dm365mm-module-src \
    ti-xdais-src \
    ti-dvsdk-demos-src \
    ti-dvtb-src \
    "

MULTIMEDIA_SOURCETREE_dm6446 = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-dm6446-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    ti-local-power-manager-src \
    "

MULTIMEDIA_SOURCETREE_dm6467 = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-dm6467-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    ti-dvsdk-demos-src \
#    ti-dvtb-src \
    "

MULTIMEDIA_SOURCETREE_da830-omapl137-evm = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-omapl137-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    "

MULTIMEDIA_SOURCETREE_da850-omapl138-evm = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-codecs-omapl138-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    "

RRECOMMENDS_${PN} = "\
    ${MULTIMEDIA_SOURCETREE} \
    "

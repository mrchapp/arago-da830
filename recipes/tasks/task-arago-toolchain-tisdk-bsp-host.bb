DESCRIPTION = "Task to build and install Board Support Package sources (or development header) packages on host"
PR = "r29"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS_COMMON = "\
    ti-tisdk-licenses-src \
    u-boot-src \
    "

BSP_COMPONENTS_dm365 = "\
    ti-linux-driver-examples-src \
    ti-tisdk-setup \
    "

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples-src \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples-src \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-linux-driver-examples-src \
    "

BSP_COMPONENTS_append_omap3 = "\
    linux-omap3-src \
    x-load-src \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_omap3evm = "\
    ti-tisdk-setup \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_am37x-evm = "\
    ti-tisdk-setup \
    flash-utility \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_dm37x-evm = "\
    ti-tisdk-setup \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS_COMMON} \
    ${BSP_COMPONENTS} \
    "

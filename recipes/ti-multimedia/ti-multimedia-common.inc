installdir = "${prefix}/ti"

CODEC_INSTALL_DIR_dm355-evm   = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codecs-dm355"
CODEC_INSTALL_DIR_dm365-evm   = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codecs-dm365"
CODEC_INSTALL_DIR_dm6446-evm  = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-dm6446"
CODEC_INSTALL_DIR_omap3evm    = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_INSTALL_DIR_beagleboard = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omap3530"
CODEC_INSTALL_DIR_da830-omapl137-evm = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-combo-omapl137"
export CODEC_INSTALL_DIR

export LINUXLIBS_INSTALL_DIR  = "${STAGING_DIR_HOST}/usr"
export CODEGEN_INSTALL_DIR    = "${STAGING_DIR_NATIVE}/ti-cgt6x-native"
export XDC_INSTALL_DIR        = "${STAGING_DIR_NATIVE}/ti-xdctools-native"
export XDAIS_INSTALL_DIR      = "${STAGING_DIR_NATIVE}/ti-xdais"
export BIOS_INSTALL_DIR       = "${STAGING_DIR_NATIVE}/ti-dspbios-native"

export CE_INSTALL_DIR         = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine"
export FC_INSTALL_DIR         = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-framework-components"
export LINUXUTILS_INSTALL_DIR = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-linuxutils"
export CMEM_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-linuxutils"
export LINK_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
export LPM_INSTALL_DIR        = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
export DMAI_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai"

export USER_XDC_PATH    = "${CE_INSTALL_DIR}/examples"
export XDC_TARGET       = gnu.targets.arm.GCArmv5T

XDC_PLATFORM_dm355-evm   = ti.platforms.evmDM355
XDC_PLATFORM_dm365-evm   = ti.platforms.evmDM365
XDC_PLATFORM_dm6446-evm  = ti.platforms.evmDM6446
XDC_PLATFORM_omap3evm    = ti.platforms.evm3530
XDC_PLATFORM_beagleboard = ti.platforms.evm3530
XDC_PLATFORM_da830-omapl137-evm = ti.platforms.omapl137
export XDC_PLATFORM

export PLATFORM_XDC = ${XDC_PLATFORM}

PLATFORM_dm355-evm   = "dm355"
PLATFORM_dm365-evm   = "dm365"
PLATFORM_dm6446-evm  = "dm6446"
PLATFORM_omap3evm    = "omap3530"
PLATFORM_beagleboard = "omap3530"
PLATFORM_da830-omapl137-evm = "ol137"
export PLATFORM

export CROSS_COMPILE = ${SDK_PATH}/bin/${TARGET_PREFIX}
export CSTOOL_DIR    = ${SDK_PATH}
export MVTOOL_DIR    = ${SDK_PATH}


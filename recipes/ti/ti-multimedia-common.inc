installdir = "${prefix}/ti"

CODEC_INSTALL_DIR   = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codecs"
export CODEC_INSTALL_DIR

export LINUXLIBS_INSTALL_DIR  = "${STAGING_DIR_HOST}/usr"
export CODEGEN_INSTALL_DIR    = "${STAGING_DIR_NATIVE}/ti-cgt6x-native"
export XDC_INSTALL_DIR        = "${STAGING_DIR_NATIVE}/ti-xdctools-native"
export XDAIS_INSTALL_DIR      = "${STAGING_DIR_NATIVE}/ti-xdais"
export BIOS_INSTALL_DIR       = "${STAGING_DIR_NATIVE}/ti-dspbios-native"
export BIOSUTILS_INSTALL_DIR  = "${STAGING_DIR_NATIVE}/ti-dspbiosutils-native"

export CE_INSTALL_DIR         = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine"
export FC_INSTALL_DIR         = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-framework-components"
export EDMA3_LLD_INSTALL_DIR  = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-edma3-lld"
export LINUXUTILS_INSTALL_DIR = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-linuxutils"
export CMEM_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-linuxutils"
export LINK_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dsplink-module"
export LPM_INSTALL_DIR        = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-codec-engine/cetools"
export DMAI_INSTALL_DIR       = "${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/ti-dmai"

export USER_XDC_PATH    = "${CE_INSTALL_DIR}/examples"
export XDC_TARGET       = gnu.targets.arm.GCArmv5T

export PLATFORM_XDC = ${XDC_PLATFORM}

# Should go into machine config
#XDC_PLATFORM_dm355-evm   = ti.platforms.evmDM355
#XDC_PLATFORM_dm365-evm   = ti.platforms.evmDM365
#XDC_PLATFORM_dm6446-evm  = ti.platforms.evmDM6446
#XDC_PLATFORM_omap3evm    = ti.platforms.evm3530
#XDC_PLATFORM_beagleboard = ti.platforms.evm3530
#XDC_PLATFORM_da830-omapl137-evm = ti.platforms.omapl137
#XDC_PLATFORM_dm6467-evm  = ti.platforms.evmDM6467
#XDC_PLATFORM_dm6467t-evm  = ti.platforms.evmDM6467
#export XDC_PLATFORM

# Should go into machine config
#PLATFORM_dm355-evm   = "dm355"
#PLATFORM_dm365-evm   = "dm365"
#PLATFORM_dm6446-evm  = "dm6446"
#PLATFORM_omap3evm    = "omap3530"
#PLATFORM_beagleboard = "omap3530"
#PLATFORM_da830-omapl137-evm = "ol137"
#PLATFORM_dm6467-evm  = "dm6467"
#PLATFORM_dm6467t-evm  = "dm6467"
#export PLATFORM

export CROSS_COMPILE = ${SDK_PATH}/bin/${TARGET_PREFIX}
export CSTOOL_DIR    = ${SDK_PATH}
export MVTOOL_DIR    = ${SDK_PATH}
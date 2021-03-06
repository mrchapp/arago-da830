DESCRIPTION = "TI Audio Soc devices example"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/apps_processors/Audio_soc_example/1_00/index_FDS.html"
SECTION = "devel"
LICENSE = "TI BSD"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

PR = "r5"

S = "${WORKDIR}/audio_soc_example_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/apps_processors/Audio_soc_example/1_00/exports/audio_soc_example_${PV}.tar.gz;name=audiosoctarball"

DEPENDS = "ti-cgt6x ti-xdctools ti-dspbios ti-biospsp ti-edma3lld ti-dsplink"
DEPENDS += "virtual/kernel"

PLATFORM_da830-omapl137-evm = "omapl137" 
PLATFORM_da850-omapl138-evm = "omapl138" 
 
BOARD_da830-omapl137-evm =  "evmOMAPL137"
BOARD_da850-omapl138-evm =  "evmOMAPL138"
 
do_compile() {
    # Build gpp sample application
    cd ${S}
    make \
    PLATFORM="${PLATFORM}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    LINUXKERNEL_INSTALL_DIR="{STAGING_KERNEL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    BIOSPSP_INSTALL_DIR="${BIOSPSP_INSTALL_DIR}" \
    EDMA3LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    gpp

    # Build dsp sample application
    make \
    PLATFORM="${PLATFORM}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    LINUXKERNEL_INSTALL_DIR="{STAGING_KERNEL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    BIOSPSP_INSTALL_DIR="${BIOSPSP_INSTALL_DIR}" \
    EDMA3LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    dsp 
}


do_install() {
    install -d ${D}${AUDIO_SOC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${AUDIO_SOC_INSTALL_DIR_RECIPE}

    install -d ${D}/${installdir}/ti-audio-soc-example
    cp -rf ${S}/gpp/${BOARD} ${D}/${installdir}/ti-audio-soc-example
    cp -rf ${S}/data/* ${D}/${installdir}/ti-audio-soc-example

    cp -rf ${S}/dsp/${BOARD}/Debug/audioSoc_dsp.out  ${D}/${installdir}/ti-audio-soc-example/${BOARD}/Debug
    cp -rf ${S}/dsp/${BOARD}/Debug/audioSoc_dsp.out  ${D}/${installdir}/ti-audio-soc-example/${BOARD}/Release

}

FILES_${PN} = "${installdir}/ti-audio-soc-example/*"
INSANE_SKIP_${PN} = "True"

RRECOMMENDS_ti_audio-soc-example += "ti-dsplink-module"


inherit module-base

include ti-dvsdk-demos.inc

include ti-multimedia-common.inc

installdir = "${prefix}/ti"

DEPENDS += "ti-dmai"
DEPENDS += "alsa-lib libpng freetype jpeg"

# Should go into machine config
TARGET           ?= "all"
TARGET_dm355-evm ?= "dm355"
TARGET_dm365-evm ?= "dm365"
TARGET_dm6467-evm ?= "dm6467"
TARGET_dm6467t-evm ?= "dm6467"

include ti-multimedia-common.inc

VERBOSE="true"

do_compile () {
        cd ${S}
        make -e clean
        make -e ${PLATFORM}
}

do_install () {
        cd ${S}
        make -e ${TARGET} EXEC_DIR=${D}/${installdir}/dvsdk-demos install
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP_${PN} = True
FILES_${PN} = "${installdir}/dvsdk-demos/*"

RDEPENDS_ti-dvsdk-demos_dm355-evm += "ti-dm355mm-module ti-linuxutils alsa-lib libpng freetype jpeg"
RDEPENDS_ti-dvsdk-demos_dm365-evm += "ti-dm365mm-module ti-linuxutils alsa-lib libpng freetype jpeg"
# Template needs to be changed after DM6467 demo is complete
RDEPENDS_ti-dvsdk-demos_dm6467-evm += "ti-dm365mm-module ti-linuxutils alsa-lib libpng freetype jpeg"
RDEPENDS_ti-dvsdk-demos_dm6467t-evm += "ti-dm365mm-module ti-linuxutils alsa-lib libpng freetype jpeg"


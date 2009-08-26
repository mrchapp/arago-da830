
inherit module-base

include ti-dvsdk-demos.inc

installdir = "${prefix}/ti"

DEPENDS 	+= "ti-dmai"
DEPENDS 	+= "alsa-lib libpng freetype jpeg"

TARGET 			?= "all"
TARGET_dm355-evm 	?= "dm355"

include ti-dvsdk-common.inc

do_install () {
    cd ${S}
    make ${TARGET} EXEC_DIR=${D}/${installdir}/dvsdk-demos install 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP_${PN} = True
FILES_${PN} = "${installdir}/dvsdk-demos/*"

RDEPENDS_ti-dvsdk-demos_dm355-evm += "ti-dm355mm-module ti-cmem-module"


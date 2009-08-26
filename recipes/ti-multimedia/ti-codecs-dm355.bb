require ti-codecs-dm355.inc

installdir = "${prefix}/ti"

do_install () {
     # install mapdmaq on target
     install -d ${D}/${installdir}/codecs
     install -m 0755 ${WORKDIR}/mapdmaq ${D}/${installdir}/codecs
}

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "${installdir}/codecs/mapdmaq"


require ti-codecs-dm355.inc

installdir = "${prefix}/ti"

do_install () {
     # install mapdmaq on target
     install -d ${D}/${installdir}/codecs
     install -m 0755 ${WORKDIR}/mapdmaq ${D}/${installdir}/codecs
}

do_stage() {
    install -d ${CODEC_INSTALL_DIR}
    cp -pPrf ${S}/* ${CODEC_INSTALL_DIR}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "${installdir}/codecs/mapdmaq"

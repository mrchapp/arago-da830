inherit native

require ti-xdctools.inc

do_stage() {
    install -d ${STAGING_DIR_NATIVE}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR_NATIVE}/${PN}
}

AUTOTOOLS_NATIVE_STAGE_INSTALL="1"


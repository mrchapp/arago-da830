DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r6"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# for now install qt-widget demo on omapl138
QTWIDGET_DEMO = ""
QTWIDGET_DEMO_omapl138 = "qt-embedded-widgets-demo"

MATRIX_APPS = " libxml2 \
 matrix-gui-e \
 am-sysinfo \
 am-benchmarks \
 ${QTWIDGET_DEMO} \
"

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \    
    ${MATRIX_APPS} \
    "

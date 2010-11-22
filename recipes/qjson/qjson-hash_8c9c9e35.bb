DESCRIPTION = "qjson (QVariantHash mod)"
LICENSE = "LGPLv2.1"

PR = "r1"

SRC_URI = "git://gitorious.org/qjson/qjson.git;protocol=git;tag=${PV} \
           file://qjson-qvarianthash.patch;apply=yes \
           file://qjson-qt4-build.patch;apply=yes \
          "

inherit qt4e cmake

S = "${WORKDIR}/git"


EXTRA_OECMAKE = "\
	-DCMAKE_LINKER:FILEPATH=${LD} \
	-DCMAKE_AR:FILEPATH=${AR} \
	-DQT_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT} \
	-DQT_QTCORE_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT}/QtCore \ 
	-DQT_MOC_EXECUTABLE=${OE_QMAKE_MOC} \
	-DQT_QTCORE_LIBRARY=QtCore${QT_LIBINFIX} \
	"


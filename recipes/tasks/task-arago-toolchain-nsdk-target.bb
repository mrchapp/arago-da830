DESCRIPTION = "Target packages nSDK"
ALLOW_EMPTY = "1"
PR = "r6"

PACKAGES = "${PN}"

RDEPENDS_${PN} = " \
    task-arago-toolchain-base-tisdk-target \
    "

# From task-arago-toolchain-tisdk-graphics-target.bb
RDEPENDS_${PN} += "\
    qt4-embedded-dev \
    qt4-embedded-mkspecs \
    libqt-embeddedphonon4-dev \
    libqt-embedded3support4-dev \
    libqt-embeddedassistantclient4-dev \
    libqt-embeddedclucene4-dev \
    libqt-embeddedcore4-dev \
    libqt-embeddeddbus4-dev \
    libqt-embeddeddesignercomponents4-dev \
    libqt-embeddeddesigner4-dev \
    libqt-embeddeduitools4-dev \
    libqt-embeddedgui4-dev \
    libqt-embeddedhelp4-dev \
    libqt-embeddednetwork4-dev \
    libqt-embeddedscript4-dev \
    libqt-embeddedscripttools4-dev \
    libqt-embeddedsvg4-dev \
    libqt-embeddedtest4-dev \
    libqt-embeddedwebkit4-dev \
    libqt-embeddedxml4-dev \
    "

# From task-arago-toolchain-tisdk-multimedia-target.bb
RDEPENDS_${PN} += " \
    gstreamer-dev \
    gst-plugins-base-dev \
    gst-plugins-good-dev \
    gst-plugins-ugly-dev \
    gst-plugins-bad-dev \
    "

RDEPENDS_${PN} += " \
    directfb-dev \
    libnl-dev \
    log4cplus-dev \
    qjson-hash-dev \
    "

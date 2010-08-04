DESCRIPTION = "Task to install graphics binaries on sdk target"
PR = "r5"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Install 3D graphics for all omap3 SOC_FAMILY devices
GRAPHICS_3D = ""
GRAPHICS_3D_omap3 = "libgles-omap3-dev"

RDEPENDS_${PN} = "\
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
    ${GRAPHICS_3D} \
    "

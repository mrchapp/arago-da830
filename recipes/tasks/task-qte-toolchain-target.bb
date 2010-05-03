DESCRIPTION = "Target packages for Qt Embedded SDK"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

PR = "r7"

RDEPENDS_${PN} += " \
        task-arago-toolchain-target \
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
        libqt-embeddedsql4-dev \
        libqt-embeddedsvg4-dev \
        libqt-embeddedtest4-dev \
        libqt-embeddedwebkit4-dev \
        libqt-embeddedxml4-dev \
#        libmysqlclient-dev \
        sqlite-dev \
        libsqlite-dev \
        libts-dev \

        dbus-dev \
        expat-dev \
        glib-2.0-dev \
        libxml2-dev \
        openssl-dev \
        libpcre-dev \
        iptables-dev \
        wpa-supplicant-dev \
        bluez4-dev \
        openobex-dev \
        libstdc++-dev \
        libgles-omap3-dev \
        "

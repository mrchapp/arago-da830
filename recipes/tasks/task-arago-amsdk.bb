DESCRIPTION = "Task for AMSDK additional dependencies"
PR = "r4"

inherit task

RDEPENDS_${PN} = "\
    dbus \
    expat \
    glib-2.0 \
    libxml2 \
    openssl \
    libpcre \
    iptables \
    wpa-supplicant \
    bluez4 \
    openobex \
    iperf \
    bluez-hcidump \
    obexftp \
    ussp-push \
    matrix-gui \
    libgles-omap3-rawdemos \
    "

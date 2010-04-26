DESCRIPTION = "Task for AMSDK additional dependencies"
PR = "r2"

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
    matrix-gui \
    "

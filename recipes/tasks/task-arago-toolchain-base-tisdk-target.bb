DESCRIPTION = "Target packages for a standalone Arago SDK or external toolchain"
ALLOW_EMPTY = "1"
PR = "r2"

PACKAGES = "${PN}"

# Stuff in this SDK is based on the task-arago-base-tisdk set of packages.
RDEPENDS_${PN} += " \
    task-arago-toolchain-target \
    dbus-dev \
    expat-dev \
    glib-2.0-dev \
    libxml2-dev \
    libpcre-dev \
    iptables-dev \
    libstdc++-dev \
    "

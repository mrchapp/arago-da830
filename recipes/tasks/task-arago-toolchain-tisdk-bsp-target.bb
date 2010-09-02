DESCRIPTION = "Task to build and install corresponding development packages for Board Support Package"
PR = "r1"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task

# Bluetooth development packages
BLUETOOTH_SUPPORT = ""

BLUETOOTH_SUPPORT_omap3 = "\
    bluez4-dev \
    openobex-dev \
    "

RRECOMMENDS_${PN} = "\
    ${BLUETOOTH_SUPPORT} \
    "

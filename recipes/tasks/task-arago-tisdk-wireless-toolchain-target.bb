DESCRIPTION = "Task to install wireless dev packages in SDK"
PR = "r0"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

WLAN_SUPPORT = "\
    openssl-dev \
    wpa-supplicant-dev \
    "

BLUETOOTH_SUPPORT = "\
    bluez4-dev \
    openobex-dev \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    ${BLUETOOTH_SUPPORT} \
    "


DESCRIPTION = "Task to install wireless packages into target FS"
PR = "r1"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl \
    wpa-supplicant \
    "

BLUETOOTH_SUPPORT = "\
    bluez4 \
    bluez4-agent \
    libasound-module-bluez \
    bluez-hcidump \
    openobex \
    openobex-apps \
    obexftp \
    ussp-push \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    ${BLUETOOTH_SUPPORT} \
    "

DESCRIPTION = "Task to install wireless dev packages in SDK"
PR = "r2"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl-dev \
    wpa-supplicant-dev \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    "

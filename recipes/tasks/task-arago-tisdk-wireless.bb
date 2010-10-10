DESCRIPTION = "Task to install wireless packages into target FS"
PR = "r2"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl \
    wpa-supplicant \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    "

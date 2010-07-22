DESCRIPTION = "Task for AMSDK additional dependencies"
LICENSE = "MIT"
PR = "r10"

inherit task

RDEPENDS_${PN} = "\
    openssl \
    wpa-supplicant \
    bluez4 \
    bluez4-agent \
    libasound-module-bluez \
    bluez-hcidump \
    openobex \
    openobex-apps \
    obexftp \
    ussp-push \
    "

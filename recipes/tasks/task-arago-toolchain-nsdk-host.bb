DESCRIPTION = "Host packages for nSDK"
PR = "r4"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    cmake-sdk \
    qt4-tools-sdk \
    task-arago-toolchain-base-tisdk-host \
    "

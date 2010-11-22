DESCRIPTION = "Host packages for nSDK"
PR = "r6"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    cmake-sdk \
    fakeroot-sdk \
    qt4-tools-sdk \
    task-arago-toolchain-base-tisdk-host \
    "

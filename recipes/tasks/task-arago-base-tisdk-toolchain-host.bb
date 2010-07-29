DESCRIPTION = "Host packages for a standalone Arago SDK or external toolchain"
PR = "r0"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    task-arago-toolchain-host \
    signgp-sdk \
    "

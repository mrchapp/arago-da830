# Arago-specific amendments to the standard OE util-linux-ng recipe

# Cut out GPLv3 code (lscpu), set license accordingly
LICENSE = "GPLv2"

RC = "-arago1"

PR_append = "-arago1"

SRC_URI[archive.md5sum] = "122fa1b2f07750a46303ef0f9ff69eb6"
SRC_URI[archive.sha256sum] = "f394d45d6522c384c83d6d281aeeec26b81abcc0fb00f64d6e1a3fac77f48cd6"

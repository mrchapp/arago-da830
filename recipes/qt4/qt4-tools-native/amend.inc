# Arago-specific amendments to the standard OE Qt native tools recipes

# Disable libsm dependency
DEPENDS := "${@oe_filter_out('libsm-native', bb.data.getVar('DEPENDS', d, 1), d)}"

EXTRA_OECONF += " \
    -no-openssl -no-x11 \
"

# Qt/E 4.6.x from Nokia is dual-licensed (LGPLv2.1 or GPLv3)
# For our distribution purposes we only use LGPLv2.1
LICENSE = "LGPLv2.1"

# Set the suffix to specify amended packages
PR_append = "-arago2"

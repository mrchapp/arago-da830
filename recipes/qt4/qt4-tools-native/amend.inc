# Arago-specific amendments to the standard OE Qt native tools recipes

# Disable libsm dependency
DEPENDS := "${@oe_filter_out('libsm-native', bb.data.getVar('DEPENDS', d, 1), d)}"

EXTRA_OECONF += " \
    -no-openssl -no-x11 \
"

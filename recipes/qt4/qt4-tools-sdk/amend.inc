# Arago-specific amendments to the standard OE Qt tools sdk recipe

LICENSE = "LGPLv2.1"

EXTRA_OECONF += " \
    -no-openssl -no-x11 \
"

# Set the suffix to specify amended packages
PR_append = "-arago1"

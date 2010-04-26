# Arago-specific amendments to the standard OE Qt/E recipes

# Disable MySQL and PostgreSQL plugins and dependencies
DEPENDS := "${@oe_filter_out('qt4-embedded', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('mysql', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('postgresql', bb.data.getVar('DEPENDS', d, 1), d)}"

QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-mysql', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-psql', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"

QT_CONFIG_FLAGS += " \
    -no-sql-mysql \
    -no-sql-psql \
    -qt-mouse-linuxinput \
"

# Qt/E 4.6.x from Nokia is dual-licensed (LGPLv2.1 or GPLv3)
# For our distribution purposes we only use LGPLv2.1
LICENSE = "LGPLv2.1"

# Set the suffix to specify amended packages
PR_append = "-arago1"

# Arago-specific amendments to the standard OE Qt/E recipes

# Disable MySQL and PostgreSQL plugins and dependencies
DEPENDS := "${@oe_filter_out('qt4-embedded', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('mysql', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('postgresql', bb.data.getVar('DEPENDS', d, 1), d)}"

QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-mysql', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-psql', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"

# Disable unneeded/unsupported build options to speed build time and limit
# packages installed to supported set.
DEPENDS := "${@oe_filter_out('directfb', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('openssl', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('sqlite', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('sqlite3', bb.data.getVar('DEPENDS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-gfx-directfb', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-no-sql-ibase', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-no-sql-odbc', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-sqlite', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-sql-sqlite2', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"

QT_CONFIG_FLAGS += " \
    -no-sql-mysql \
    -no-sql-psql \
    -qt-mouse-linuxinput \
    -no-gtkstyle \
"

# Change the default decoration style
QT_CONFIG_FLAGS := "${@oe_filter_out('-qt-decoration-styled', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-decoration-default', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"
QT_CONFIG_FLAGS := "${@oe_filter_out('-plugin-decoration-windows', bb.data.getVar('QT_CONFIG_FLAGS', d, 1), d)}"

# Qt/E 4.6.x from Nokia is dual-licensed (LGPLv2.1 or GPLv3)
# For our distribution purposes we only use LGPLv2.1
LICENSE = "LGPLv2.1"

# Set the suffix to specify amended packages
PR_append = "-arago3"

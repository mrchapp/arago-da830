# Arago-specific amendments to the standard dbus recipe to remove
# dependencies on X11.
DEPENDS := "${@oe_filter_out('virtual/libx11', bb.data.getVar('DEPENDS', d, 1), d)}"

EXTRA_OECONF_X := "${@oe_filter_out('--with-x', bb.data.getVar('EXTRA_OECONF_X', d, 1), d)}"

# explicetely tell the dbus configure script NOT to use X.  If not it may
# find X11 libraries and headers and enable X anyway.
EXTRA_OECONF_X += "no_x=yes"

# Set the suffix to specify amended packages
PR_append = "-arago1"

require gst-plugins.inc

PR = "${INC_PR}.1"

inherit gconf 

DEPENDS += "gst-plugins-base openssl popt"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-ximagesrc" 

PACKAGES =+ "gst-plugin-gconfelements"
FILES_gst-plugin-gconfelements += "${sysconfdir}/gconf"


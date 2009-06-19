require gst-plugins.inc

PR = "r2"

OE_ALLOW_INSECURE_DOWNLOADS = "1"
inherit gconf 

DEPENDS += "gst-plugins-base openssl popt"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-ximagesrc" 

PACKAGES =+ "gst-plugin-gconfelements"
FILES_gst-plugin-gconfelements += "${sysconfdir}/gconf"


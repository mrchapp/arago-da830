require gst-plugins.inc

PR = "${INC_PR}.1"

PROVIDES += "gst-plugins"

EXTRA_OECONF += "--disable-x --with-checklibname=check --disable-ogg --disable-pango --disable-vorbis --disable-examples --disable-gnome_vfs" 

# gst-plugins-base only builds the alsa plugin
# if alsa has been built and is present.  You will
# not get an error if this is not present, just 
# a missing alsa plugin
DEPENDS += "alsa-lib "


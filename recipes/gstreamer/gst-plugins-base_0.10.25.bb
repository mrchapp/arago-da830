require gst-plugins.inc

SRC_URI += "file://0001-Fix-for-playbin2.patch;patch=1 \
"
SRC_URI[md5sum] = "d29669dd79276c5cd94e1613c03cd9ab"
SRC_URI[sha256sum] = "0ab2f7e1d818e7af1be99c4eae02ba69d4a1b8f7e3527929a6426f1daa0d4607"

PR = "${INC_PR}.3"

PROVIDES += "gst-plugins"

EXTRA_OECONF += "--disable-tests --disable-examples --disable-x --disable-ogg --disable-vorbis --disable-pango --enable-alsa --disable-subparse"

# gst-plugins-base only builds the alsa plugin
# if alsa has been built and is present.  You will
# not get an error if this is not present, just 
# a missing alsa plugin
DEPENDS += "alsa-lib"

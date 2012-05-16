require gst-plugins.inc
SRC_URI += " file://001-Add-support-for-mpegtsdemux.patch "

PR = "r2"

DEPENDS += "gst-plugins-base gtk+"

SRC_URI[md5sum] = "2288f7093a54891622ec1016bc939204"
SRC_URI[sha256sum] = "eb104bec734640d8c180e02185c431e566baa2562990c323510f64396119835b"

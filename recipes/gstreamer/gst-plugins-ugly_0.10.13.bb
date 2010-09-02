require gst-plugins.inc

PR = "${INC_PR}.1"

DEPENDS += "gst-plugins-base libmad "

SRC_URI += "\
  file://gstmad_16bit.patch \
  file://gstsid_autofoo_HACK.patch \
"
EXTRA_OECONF += "ac_cv_openssldir=no --enable-lame --enable-mad" 

DEPENDS += "lame libmad"
SRC_URI[md5sum] = "1619365247ef9cd7da1cd890285bf87a"
SRC_URI[sha256sum] = "39f07a60739f5dfa4f5574a02db15de69fd05b9ea63239488338ec3491bf69a8"

python() {
	# Don't build, if we are building an ENTERPRISE distro
	enterprise = bb.data.getVar("ENTERPRISE_DISTRO", d, 1)
	if enterprise == "1":
		raise bb.parse.SkipPackage("gst-plugins-ugly will only build if ENTERPRISE_DISTRO != 1")
}

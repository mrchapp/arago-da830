inherit native
require cmake.inc

do_install() {
	oe_runmake install
}

SRC_URI[md5sum] = "feadc2e5ebbfed0efc90178583503725"
SRC_URI[sha256sum] = "7eae74ab7673974a68e395c211854d318f0af2d320590a670339ee8ee9422242"
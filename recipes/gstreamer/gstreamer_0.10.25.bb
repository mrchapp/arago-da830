require gstreamer.inc

EXTRA_OECONF += "ac_cv_func_register_printf_function=no --disable-gtk-doc --disable-tests --with-checklibname=check --disable-valgrind --disable-loadsave" 

SRC_URI += " file://0001-Always-return-NULL-for-the-last-buffer-property.patch;patch=1 "

PR = "r2"

do_configure_prepend() {
	sed -i -e s:docs::g Makefile.am
}


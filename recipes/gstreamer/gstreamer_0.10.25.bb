require gstreamer.inc

EXTRA_OECONF += "ac_cv_func_register_printf_function=no --disable-gtk-doc --disable-tests --with-checklibname=check --disable-valgrind " 

do_configure_prepend() {
	sed -i -e s:docs::g Makefile.am
}


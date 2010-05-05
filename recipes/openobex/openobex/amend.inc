# Arago-specific amendments to the standard openobex recipes

# Add apps/obex_test for testing

do_configure_prepend() {
	sed -i -e 's:bin_PROGRAMS = irxfer obex_tcp irobex_palm3:bin_PROGRAMS = irxfer obex_tcp irobex_palm3 obex_test:g' ${S}/apps/Makefile.am
	aclocal
	automake --add-missing
	autoreconf
}

PR_append = "-arago1"

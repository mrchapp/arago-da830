# Arago-specific amendments to the standard OE SysVinit recipe

# When building for "virtual" arago machine, start gettys on all 3 serial consoles
do_install_append_arago () {
	echo "S0:2345:respawn:${base_sbindir}/getty 115200 ttyS0" >> ${D}${sysconfdir}/inittab
	echo "S1:2345:respawn:${base_sbindir}/getty 115200 ttyS1" >> ${D}${sysconfdir}/inittab
	echo "S2:2345:respawn:${base_sbindir}/getty 115200 ttyS2" >> ${D}${sysconfdir}/inittab
}

# Set the suffix to specify amended packages
PR_append = "-arago1"

# Arago-specific amendments to the standard bluez4 recipes

# Add test/agent for testing

PACKAGES =+ "bluez4-agent"

do_install_append () {
	${TARGET_PREFIX}libtool --mode=install install -c 'test/agent' ${D}${bindir}/agent
}

FILES_bluez4-agent = "${bindir}/agent"

PR_append = "-arago2"

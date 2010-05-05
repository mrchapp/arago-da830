# Arago-specific amendments to the standard bluez4 recipes

# Add test/agent for testing

PACKAGES =+ "bluez4-agent"

do_install_append () {
	${STAGING_BINDIR_NATIVE}/${TARGET_PREFIX}libtool --mode=install ${STAGING_BINDIR_NATIVE}/install -c 'test/agent' ${D}${bindir}/agent
}

FILES_bluez4-agent = "${bindir}/agent"

PR_append = "-arago1"

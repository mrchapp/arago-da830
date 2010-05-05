# Arago-specific amendments to the standard OE Qt-tools recipe

# Add symlinks with generic names for cross-tools

do_install_append() {
	(cd ${D}${bindir}; \
	ln -s qmake2 qmake; \
	for i in moc uic uic3 rcc lrelease lupdate qdbuscpp2xml qdbusxml2cpp; do \
		ln -s ${i}4 ${i}; \
	done)
}

PR_append = "-arago1"

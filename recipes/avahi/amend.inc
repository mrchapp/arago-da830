# Arago-specific amendments to the standard OE base-files recipe

PR_append = "-arago5"

DEPENDS += "qt4-embedded"

EXTRA_OECONF = "\
	--with-distro=debian \
	--disable-stack-protector \
	--disable-nls \
	--disable-gdbm \
	${AVAHI_GTK} \
	--disable-mono \
	--disable-monodoc \
	--disable-qt3 \
	--enable-qt4 \
	QT4_CFLAGS='-DQT_SHARED -I${STAGING_DIR}/${BASEPKG_HOST_SYS}/usr/include/qtopia -I${STAGING_DIR}/${BASEPKG_HOST_SYS}/usr/include/qtopia/QtCore' \
	QT4_LIBS='-lQtCoreE' \
	MOC_QT4='${STAGING_DIR}/${BUILD_SYS}/usr/bin/moc4' \
	${AVAHI_PYTHON}"

do_install_append() {
	# force custom version of config file
	install -m 0644 ${FILESDIR}/avahi-daemon.conf ${D}/${sysconfdir}/avahi/avahi-daemon.conf

	# remove some standard services we don't want to publish
	rm ${D}/${sysconfdir}/avahi/services/ssh.service
	rm ${D}/${sysconfdir}/avahi/services/sftp-ssh.service
}

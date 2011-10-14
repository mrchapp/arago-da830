# Arago-specific amendments to the standard OE base-files recipe

PR_append = "-arago3"

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

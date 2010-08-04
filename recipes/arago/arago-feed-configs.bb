DESCRIPTION = "Configuration files for online package repositories aka feeds"
LICENSE = "MIT"
RRECOMMENDS_${PN} += "opkg"

#PV = "${DISTRO_VERSION}"
PR = "r10"

# Here is the deal - since we build a common filesystem for several platforms,
# we need to add all their respective feeds manually, hence next line is out
#PACKAGE_ARCH = "${MACHINE_ARCH}"

MACHS_ARMV5 = "dm6446-evm dm6467-evm dm6467t-evm dm355-evm dm365-evm dm357-evm da830-omapl137-evm da850-omapl138-evm"
MACHS_ARMV7 = "omap3evm beagleboard"

# This gets set in the distro/local configuration
ARAGO_FEED_BASEPATH ?= "feeds"

do_compile() {
        mkdir -p ${S}/${sysconfdir}/opkg/angstrom

# Populate the list of supported architectures
	rm ${S}/${sysconfdir}/opkg/arch.conf || true
#	ipkgarchs="${PACKAGE_ARCHS}"
	ipkgarchs="all any noarch arm armv4 armv4t armv5te #armv6 #armv7 #armv7a arago #omap3evm #beagleboard #dm6446-evm #dm6467-evm #dm6467t-evm #dm355-evm #dm365-evm #dm357-evm #da830-omapl137-evm #da850-omapl138-evm"
	priority=1
	for arch in $ipkgarchs; do
		disable=`echo $arch|cut -c1`
		if [ ${disable} == "#" ]; then
			newarch=`echo $arch|cut -c2-`
			echo "#arch $newarch $priority" >> ${S}/${sysconfdir}/opkg/arch.conf
		else
			echo "arch $arch $priority" >> ${S}/${sysconfdir}/opkg/arch.conf
		fi
		priority=$(expr $priority + 5)
	done

# Add all Arago supported feeds
	echo "src/gz no-arch ${DISTRO_FEED_URI}/all" > ${S}/${sysconfdir}/opkg/arago-noarch-feed.conf
	echo "src/gz arago ${DISTRO_FEED_URI}/arago" > ${S}/${sysconfdir}/opkg/arago-mach-feed.conf
	echo "src/gz armv5te ${DISTRO_FEED_URI}/armv5te" >  ${S}/${sysconfdir}/opkg/arago-armv5te-feed.conf

	echo "src/gz armv6 ${DISTRO_FEED_URI}/armv6" >  ${S}/${sysconfdir}/opkg/arago-armv6-feed.conf.sample
	echo "src/gz armv7a ${DISTRO_FEED_URI}/armv7a" >  ${S}/${sysconfdir}/opkg/arago-armv7a-feed.conf.sample

	for mach in ${MACHS_ARMV7} ${MACHS_ARMV5}; do
		echo "src/gz $mach ${DISTRO_FEED_URI}/$mach" >  ${S}/${sysconfdir}/opkg/arago-$mach-feed.conf.sample
	done

# Add Angstrom feeds as backup
	for arch in armv5te armv6 armv7a; do
		for feed in base debug perl python gstreamer ; do
			echo "src/gz ${feed} ${ANGSTROM_URI}/${FEED_BASEPATH}${arch}/${feed}" > ${S}/${sysconfdir}/opkg/angstrom/angstrom-${arch}-${feed}-feed.conf.sample
		done
	done

	# armv7a
	for mach in ${MACHS_ARMV7}; do
		echo "src/gz $mach ${ANGSTROM_URI}/${FEED_BASEPATH}armv7a/machine/$mach" >  ${S}/${sysconfdir}/opkg/angstrom/angstrom-$mach-feed.conf.sample
	done

	# armv5te
	for mach in ${MACHS_ARMV5}; do
		echo "src/gz $mach ${ANGSTROM_URI}/${FEED_BASEPATH}armv5te/machine/$mach" >  ${S}/${sysconfdir}/opkg/angstrom/angstrom-$mach-feed.conf.sample
	done

	echo "src/gz no-arch ${ANGSTROM_URI}/${FEED_BASEPATH}all" > ${S}/${sysconfdir}/opkg/angstrom/angstrom-noarch-feed.conf.sample
}

do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -d ${D}${sysconfdir}/opkg/angstrom
	install -m 0644 ${S}/${sysconfdir}/opkg/*.conf* ${D}${sysconfdir}/opkg/
	install -m 0644 ${S}/${sysconfdir}/opkg/angstrom/*.conf* ${D}${sysconfdir}/opkg/angstrom/
}

FILES_${PN} = " \
	${sysconfdir}/opkg/* \
	${sysconfdir}/opkg/angstrom/* \
"

CONFFILES_${PN} += " \
	${sysconfdir}/opkg/* \
	${sysconfdir}/opkg/angstrom/* \
"

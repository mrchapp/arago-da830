DESCRIPTION = "A demo application for wilink on WL1271 chipset"
SECTION = "network"
LICENSE = "Apache-2.0"
DEPENDS += "virtual/kernel perl-native wpa-supplicant openssl"
RDEPENDS += "wpa-supplicant openssl"

PR = "r1"

PACKAGE_ARCH = ${MACHINE_ARCH}

COMPATIBLE_MACHINE = "am37x-evm|am180x-evm|da850-omapl138-evm|da850-sdi"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/Demos/wl1271-demos_v1.tar"

S = "${WORKDIR}/wl1271-demos"

PLATFORM_am37x-evm = "omap3evm"
PLATFORM_am180x-evm = "am1808"
PLATFORM_da850-omapl138-evm = "am1808"
PLATFORM_da850-sdi = "am1808"
PLATFORM ?= "UNKNOWN"

do_compile() {
	# Build WiLink CLI application
	cd ${S}/CUDK && make \
	HOST_PLATFORM="${PLATFORM}" \
	KERNEL_DIR="${STAGING_KERNEL_DIR}" \
	CROSS_COMPILE="${TARGET_PREFIX}" \
	ARCH="arm" \
	XCC="n" \
	BUILD_SUPPL="y" \
	TI_SUPP_LIB_DIR="${STAGING_INCDIR}" \
	BASE_BUILDOS="${STAGING_KERNEL_DIR}"
}

do_install () {
	install -d ${D}${libdir}
	install -d ${D}${libdir}/firmware/
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -d ${D}${datadir}
	install -d ${D}${datadir}/wl1271-demos
	install -d ${D}${datadir}/wl1271-demos/wlan

	install -m 0755 ${S}/WLAN_firmware/firmware.bin ${D}${libdir}/firmware/
	install -m 0755 ${S}/CUDK/output/tiwlan.ini ${D}${libdir}/firmware/
	install -m 0755 ${S}/CUDK/output/tiwlan_loader ${D}${bindir}
	install -m 0755 ${S}/CUDK/output/wlan_cu ${D}${bindir}
	install -m 0755 ${S}/CUDK/output/wpa_supplicant.txt ${D}${sysconfdir}/wpa_supplicant
	install -m 0755 ${S}/script/${PLATFORM}/install-wlan.sh ${D}${datadir}/wl1271-demos/wlan
}

FILES_${PN} = " \
	${libdir}/* \
	${sysconfdir}/* \
	${datadir}/* \
	${bindir}/* \
	"

PACKAGE_STRIP = "no"

SRC_URI[md5sum] = "4915e1d5d99fcc6c203332b19c91b94e"
SRC_URI[sha256sum] = "a1f22c454f9e3976aa4515bc210c7393534831c45f34a1702e37031fcd7439d5"

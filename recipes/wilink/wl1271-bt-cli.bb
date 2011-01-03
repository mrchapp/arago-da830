DESCRIPTION = "A demo application for bluetooth on WL1271 chipset"
SECTION = "network"
LICENSE = "BSD"
DEPENDS += "openobex"
RDEPENDS += "bluez4 openobex ussp-push obexftp bluez-hcidump wl1271-bt-enable"

PR = "r1"

PACKAGE_ARCH = ${MACHINE_ARCH}

COMPATIBLE_MACHINE = "am37x-evm|am180x-evm|da850-omapl138-evm"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/Demos/wl1271-demos_v1.tar"

S = "${WORKDIR}/wl1271-demos"

PLATFORM_am37x-evm = "omap3evm"
PLATFORM_am180x-evm = "am1808"
PLATFORM_da850-omapl138-evm = "am1808"
PLATFORM ?= "UNKNOWN"

do_compile() {
	# Build opp server application
	cd ${S}/oppserver && ${CC} -o oppserver *.c ${LDFLAGS} ${CFLAGS} -lopenobex -lbluetooth -lmisc
}

do_install () {
	install -d ${D}${bindir}
	install -d ${D}${base_libdir}/firmware
	install -d ${D}${datadir}/wl1271-demos
	install -d ${D}${datadir}/wl1271-demos/bluetooth
	install -d ${D}${datadir}/wl1271-demos/bluetooth/gallery
	install -d ${D}${datadir}/wl1271-demos/bluetooth/scripts

	install -m 0755 ${S}/oppserver/oppserver ${D}${bindir}
	install -m 0755 ${S}/BT_firmware/${PLATFORM}/* ${D}${base_libdir}/firmware
	install -m 0755 ${S}/gallery/* ${D}${datadir}/wl1271-demos/bluetooth/gallery
	install -m 0755 ${S}/script/${PLATFORM}/bluetooth_scripts/* ${D}${datadir}/wl1271-demos/bluetooth/scripts
}

FILES_${PN} = " \
	${base_libdir}/* \
	${datadir}/* \
	${bindir}/* \
	"

SRC_URI[md5sum] = "4915e1d5d99fcc6c203332b19c91b94e"
SRC_URI[sha256sum] = "a1f22c454f9e3976aa4515bc210c7393534831c45f34a1702e37031fcd7439d5"

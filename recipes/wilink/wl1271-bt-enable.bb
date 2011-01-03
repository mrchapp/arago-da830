DESCRIPTION = "Bluetooth enable for WL1271 chipset"
SECTION = "network"
LICENSE = "BSD"
DEPENDS += "virtual/kernel"

MACHINE_KERNEL_PR_append = "c"

COMPATIBLE_MACHINE = "am180x-evm|da850-omapl138-evm"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/Demos/wl1271-demos_v1.tar"

S = "${WORKDIR}/wl1271-demos/GPIO"

inherit module

export BASE_BUILDOS="${STAGING_KERNEL_DIR}"

do_install () {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bt_enable
	install -m 0755 ${S}/gpio_en.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bt_enable
}

PACKAGE_STRIP = "no"

SRC_URI[md5sum] = "4915e1d5d99fcc6c203332b19c91b94e"
SRC_URI[sha256sum] = "a1f22c454f9e3976aa4515bc210c7393534831c45f34a1702e37031fcd7439d5"

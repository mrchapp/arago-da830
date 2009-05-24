DESCRIPTION = "Host packages for a standalone Arago SDK or external toolchain"
PR = "r16"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

DVSDK_HOST_PACKAGE_dm355-evm = "\
	ti-codec-engine-sdk \
	ti-codec-combo-dm355-sdk \
	ti-xdctools-sdk \
	ti-dmai-sdk \
"

DVSDK_HOST_PACKAGE_dm6446-evm = "\
	ti-codec-engine-sdk \
	ti-codec-combo-dm6446-sdk \
	ti-xdctools-sdk \
	ti-dmai-sdk \
	ti-dspbios-sdk \
	ti-cgt6x-sdk \
"
DVSDK_HOST_PACKAGE_dm6467-evm = "\
	ti-codec-engine-sdk \
	ti-codec-combo-dm6467-sdk \
	ti-xdctools-sdk \
	ti-dmai-sdk \
	ti-dspbios-sdk \
	ti-cgt6x-sdk \
"

DVSDK_HOST_PACKAGE_omap3evm = "\
	ti-codec-engine-sdk \
	ti-codec-combo-omap3530-sdk \
	ti-xdctools-sdk \
	ti-dmai-sdk \
	ti-dspbios-sdk \
	ti-cgt6x-sdk \
"
DVSDK_HOST_PACKAGE_beagleboard = "\
	ti-codec-engine-sdk \
	ti-codec-combo-omap3530-sdk \
	ti-xdctools-sdk \
	ti-dmai-sdk \
	ti-dspbios-sdk \
	ti-cgt6x-sdk \
"

RDEPENDS_${PN} = "\
	task-arago-base-host \
	${DVSDK_HOST_PACKAGE} \
	ti-rules-make-sdk \
    "


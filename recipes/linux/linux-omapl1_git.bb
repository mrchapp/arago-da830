require multi-kernel.inc

DESCRIPTION = "Linux kernel for OMAPL1 processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm|da850-sdi)"

DEFAULT_PREFERENCE = "1"

BRANCH = "wifi/logic/nsdk"
BRANCH_da850-sdi = "wifi/sdi/nsdk"
BRANCH_da830-omapl137-evm = "master"

SRCREV = "315b36ad8942380313da2cabd8ac53f6ed79eb77"
SRCREV_da850-sdi = "948ea80125c4a1969498d535dd859c5c7d30b72c"
SRCREV_da830-omapl137-evm =  "f8a7bbe25cccb7f3206337b67d1ce0748a99c2fd"

KVER = "2.6.32+2.6.33-rc4"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
PV = "${KVER}-${PR}+gitr${SRCREV}"

SRC_URI  = "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
	   file://defconfig"

sysroot_stage_all_append() {
    # in upstream oe-dev this is set in kernel.bbclass
    kerneldir=${SYSROOT_DESTDIR}${STAGING_KERNEL_DIR}
    
    if [ -d drivers/net/wireless/wilink ]
    then
	mkdir -p $kerneldir/drivers/net/wireless/wilink
	cp -fR drivers/net/wireless/wilink/* $kerneldir/drivers/net/wireless/wilink
    fi

}

SRC_URI_append_da850-omapl138-evm = " \
          file://0001-i2c-slow.patch;patch=1 "

S = "${WORKDIR}/git"


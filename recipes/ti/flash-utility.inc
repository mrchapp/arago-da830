DESCRIPTION = "Texas Instruments Flash Utility"
LICENSE = "BSD"

PR = "r0"

S = "${WORKDIR}"

installdir = "host-tools/windows/flash_util"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/FlashInstaller.msi ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*" 

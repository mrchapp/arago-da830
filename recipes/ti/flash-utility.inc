DESCRIPTION = "Texas Instruments Flash Utility"
LICENSE = "BSD"

PR = "r1"

S = "${WORKDIR}"

installdir = "psp/board-utilities/flash_utils/windows"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/FlashInstaller.msi ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*" 

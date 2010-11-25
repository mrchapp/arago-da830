require recipes/ipkg-utils/ipkg-utils_${PV}.bb

RDEPENDS_${PN} = ""
PR = "r25"

inherit sdk

NATIVE_INSTALL_WORKS = "1"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/ipkg-utils"
INSTALL += "arfile.py"

do_install() {
	install -d ${D}${bindir}
        for i in ${INSTALL}; do
                install -m 0755 $i ${D}${bindir}
        done
}

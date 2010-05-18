DESCRIPTION = "C++ stub to link against libstdc++"
LICENSE = "BSD"
PR = "r4"

SRC_URI = "file://cppstub.cpp"

S = "${WORKDIR}"

do_compile() {
	${CXX} ${CFLAGS} ${CXXFLAGS} ${LDFLAGS} -o cppstub cppstub.cpp
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 cppstub ${D}${bindir}
}

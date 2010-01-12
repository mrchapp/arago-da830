require ti-codec.inc

# Should be replaced with real http URL, but for now create codec combo tar from DVSDK installation.
SRC_URI	= "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/sdk/omap_l138/1_00/latest/exports/cs1omapl138_${PV}-v2_setup_linux.bin;name=l138codecs"

SRC_URI[l138codecs.md5sum] = "b01eee230a25725b9f9bb0ffda45312b"
SRC_URI[l138codecs.sha256sum] = "0dc974eaab3522933a7da3a970aadb43b02af99da0f2c215782d3f22f7b9347f"

require ti-eula-unpack.inc

# Specify names of the InstallJammer binary file and the tarball it extracts
BINFILE = "cs1omapl138_${PV}-v2_setup_linux.bin"
TI_BIN_UNPK_CMDS = "y:Y: qY:workdir"

S = "${WORKDIR}/cs1omapl138_${PV}"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "1_00_00"

do_compile() {
	echo "do nothing"
}

#generic codec
DSPSUFFIX_omapl138 = "x64P"

do_install () {
    install -d ${D}/${installdir}/codec-combo
	cd ${S}
	for file in `find . -name *.${DSPSUFFIX}`; do
		cp ${file} ${D}/${installdir}/codec-combo
	done
}


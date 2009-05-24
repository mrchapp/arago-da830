require ti-cgt6x.inc
inherit sdk

# NOTE: This in internal ftp running on Brijesh's linux host. 
# This will not work outside TI network and the link should be remove once 
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI	= "ftp://156.117.95.201/cg6x_6_0_16.tar.gz"

S = "${WORKDIR}/cg6x_6_0_16"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "6016"
PR = "r10"

do_install() {
	install -d ${D}/${prefix}/dvsdk/cg6x_6_0_16
    cp -pPrf ${S}/* ${D}/${prefix}/dvsdk/cg6x_6_0_16
	
	# Creates rules.make file
	mkdir -p ${STAGING_DIR_HOST}/ti-sdk-rules
	echo "# Where the TI C6x codegen tool is installed." >  ${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
	echo "CODEGEN_INSTALL_DIR=${prefix}/dvsdk/cg6x_6_0_16" >> ${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
  echo "" >> 	${STAGING_DIR_HOST}/ti-sdk-rules/cgt6x.Rules.make
}

INHIBIT_PACKAGE_STRIP = "1"
FILES_${PN} = "${prefix}/dvsdk/cg6x_6_0_16"
INSANE_SKIP_${PN} = True


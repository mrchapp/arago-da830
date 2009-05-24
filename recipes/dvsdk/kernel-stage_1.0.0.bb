DESCRIPTION="This recipe packages the kernel staging directory for sdk"

# Unfortunetly, kernel-dev package does not provide all the nessesry header
# file and Kbuild system you need to compile a module. This recipe creates ipk
# of kernel stagging directory.
# FIXME: This is a temporary workaround and just not be used in mainline. 
# We may need to extend kernel class to generate these ipk

inherit sdk

do_install() {
    install -d ${D}/${prefix}/dvsdk/kernel-stage
    cp -pPrf ${STAGING_DIR}/${MACHINE}-${PREFIX}/kernel* ${D}/${prefix}/dvsdk/kernel-stage
}

INHIBIT_PACKAGE_STRIP = "1"
FILES_${PN} = "${prefix}/dvsdk/kernel-stage"
INSANE_SKIP_${PN} = True


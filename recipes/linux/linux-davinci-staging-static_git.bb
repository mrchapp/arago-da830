require linux-davinci-staging.inc

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-static"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}-static"
FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-davinci-staging:linux-davinci:files:', 1)}"

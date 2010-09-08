# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for OMAP3 devices"

COMPATIBLE_MACHINE = "omap3"

# The TAG value should be a named tag and NOT a commit id
# where possible.
TAG = "c0a8fb217fdca7888d89f9a3dee74a4cec86562"

PR ="r1"

SRC_URI = "git://arago-project.org/git/projects/u-boot-omap3.git;protocol=git;tag=${TAG}"


S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

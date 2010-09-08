# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI816X devices"

COMPATIBLE_MACHINE = "ti816x"

# The TAG value should be a named tag and NOT a commit id
# where possible.
TAG = "308bfa87a88b1d1e2835e6712b4a89447f601f70"

PR ="r1"

SRC_URI = "git://arago-project.org/git/projects/u-boot-omap3.git;protocol=git;tag=${TAG}"


S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

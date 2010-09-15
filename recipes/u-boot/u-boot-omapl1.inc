# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for OMAPL1 devices"

COMPATIBLE_MACHINE = "(omapl137|omapl138)"

SRC_URI = "git://arago-project.org/git/projects/u-boot-omapl1.git;protocol=git"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

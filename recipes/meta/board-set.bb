DESCRIPTION = "Meta-package for Arago board sets (kernel+bootloaders)"
PR = "r5"

# Generic "arago" machine is not part of this list on purpose
COMPATIBLE_MACHINE = "(?!arago)"

DEPENDS = "virtual/kernel virtual/bootloader"

#DEPENDS_append_omap3evm = " x-load"
#DEPENDS_append_beagleboard = " x-load"
#DEPENDS_append_davinci-dvevm = " ubl"

inherit meta

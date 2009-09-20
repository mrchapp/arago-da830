DESCRIPTION = "Meta-package for Arago board sets (kernel+bootloaders)"
PR = "r4"

# Generic "arago" machine is not part of this list on purpose
# so it would require setting MACHINE explicitly
COMPATIBLE_MACHINE = "omap3evm|omap3517-evm|beagleboard|dm6446-evm|dm6467-evm|dm6467t-evm|dm355-evm|dm365-evm|da830-omapl137-evm|da850-omapl138-evm"

DEPENDS = "virtual/kernel virtual/bootloader"

#DEPENDS_append_omap3evm = " x-load"
#DEPENDS_append_beagleboard = " x-load"
#DEPENDS_append_davinci-dvevm = " ubl"

# This is not really required, as "arago" is not compatibe
DEPENDS_arago = ""

inherit meta

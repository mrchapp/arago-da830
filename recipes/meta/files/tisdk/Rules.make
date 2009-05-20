#
#  ======== Rules.make ========
#  This file specified defines used by the top-level makefile.  In a typical
#  usage scenario, the user should modify the following variables:
#
#  DVSDK_INSTALL_DIR - Defines the DVSDK install location.
#
#  XDC_INSTALL_DIR - Defines the XDC tool install location.
#
#  BIOS_INSTALL_DIR - Defines the stand-alone BIOS install location.
#
#  LINUXKERNEL_INSTALL_DIR - Defines the Linux kernel install location.
#
#  EXEC_DIR - Defines the target binaries install location (installed through
#      "host> make install").
#


# Define target platform.
PLATFORM=__PLATFORM__

# The installation directory of the DVSDK  .
DVSDK_INSTALL_DIR=__DVSDK_INSTALL_DIR__

# For backwards compatibility
DVEVM_INSTALL_DIR=$(DVSDK_INSTALL_DIR)

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__CE_INSTALL_DIR__

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(CE_INSTALL_DIR)/cetools

# Where the DSP Link package is installed.
LINK_INSTALL_DIR=$(CE_INSTALL_DIR)/cetools

# Where the CMEM (contiguous memory allocator) package is installed.
CMEM_INSTALL_DIR=$(CE_INSTALL_DIR)/cetools

# Where the codec servers are installed.
CODEC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__CODEC_INSTALL_DIR__

# Where the TI C6x codegen tool is installed.
CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__CODEGEN_INSTALL_DIR__

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__XDC_INSTALL_DIR__

# Where Framework Components product is installed.
FC_INSTALL_DIR=$(CE_INSTALL_DIR)/cetools

# Where DSP/BIOS is installed.
BIOS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__BIOS_INSTALL_DIR__

# Where biosutils package is installed.
BIOSUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__BIOSUTILS_INSTALL_DIR__

# Where DMAI package is installed.
DMAI_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__DMAI_INSTALL_DIR__

# Where the demo package is installed.
DEMO_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__DEMO_INSTALL_DIR__

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/__LINUXKERNEL_INSTALL_DIR__

# Where the local power manager is installed.
LPM_INSTALL_DIR=$(CE_INSTALL_DIR)/cetools

# Where temporary Linux headers and libs are installed.
LINUXLIBS_INSTALL_DIR=__LINUXLIBS_INSTALL_DIR__/usr

# The prefix to be added before the GNU compiler tools (optionally including
# path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=__CSTOOL_DIR__
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables and data to (when executing 'make
# install') in a proper file structure. This EXEC_DIR should either be visible
# from the target, or you will have to copy this (whole) directory onto the
# target filesystem.
EXEC_DIR=$(DVSDK_INSTALL_DIR)/install/${PLATFORM}


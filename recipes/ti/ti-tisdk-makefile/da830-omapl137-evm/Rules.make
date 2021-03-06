# Define target platform.
PLATFORM=omapl137

# The installation directory of the SDK.
SDK_INSTALL_DIR=<__SDK__INSTALL_DIR__>

# For backwards compatibility
DVEVM_INSTALL_DIR=$(SDK_INSTALL_DIR)

# Where DSP/BIOS is installed.
BIOS_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__dspbios__>

# Where the DSPBIOS Utils package is installed.
BIOSUTILS_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__biosutils__>

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__codec-engine__>

# Where the DSP Link package is installed.
LINK_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__dsplink__>

# Where the codecs are installed.
CODEC_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__codecs-omapl137__>

# Where DMAI package is installed.
DMAI_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__dmai__>

# Where the EDMA3 LLD package is installed.
EDMA3_LLD_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__edma3lld__>
EDMA3LLD_INSTALL_DIR=$(EDMA3_LLD_INSTALL_DIR)

# Where the DSP-side BIOS PSP package is installed.
BIOSPSP_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__biospsp__>

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__framework-components__>

# Where the MFC Linux Utils package is installed.
LINUXUTILS_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__linuxutils__>
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__xdais__>

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__xdctools__>

# Where the Code Gen is installed.
CODEGEN_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__cgt6x__>

# Where the PSP is installed.
PSP_INSTALL_DIR=$(SDK_INSTALL_DIR)/psp

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(SDK_INSTALL_DIR)/<__kernel__>

# Where temporary Linux headers and libs are installed.
LINUXLIBS_INSTALL_DIR=$(SDK_INSTALL_DIR)/linuxlibs

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)


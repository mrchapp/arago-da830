# Define target platform.
PLATFORM=dm355

# The installation directory of the DVSDK.
DVSDK_INSTALL_DIR=<__SDK__INSTALL_DIR__>

# For backwards compatibility
DVEVM_INSTALL_DIR=$(DVSDK_INSTALL_DIR)

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__codec-engine__>

# Where the codecs are installed.
CODEC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__codecs-dm355__>

# Where DMAI package is installed.
DMAI_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__dmai__>

# Where the SDK demos are installed
DEMO_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__dvsdk-demos__>

# Where the DVTB package is installed.
DVTB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__dvtb__>

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__framework-components__>

# Where the PSP is installed.
PSP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/psp

# Where the MFC Linux Utils package is installed.
LINUXUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__linuxutils__>
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__xdais__>

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__xdctools__>

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__kernel__>

# Where temporary Linux headers and libs are installed.
LINUXLIBS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/linuxlibs

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)


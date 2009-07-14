# Add glibc to the overrides.
OVERRIDES .= ":libc-glibc"

# The things glibc can provide.  We default to wanting glibc to provide them.
PREFERRED_PROVIDER_virtual/libiconv ?= "external-toolchain"
PREFERRED_PROVIDER_virtual/libintl ?= "external-toolchain"
PREFERRED_PROVIDER_virtual/libc = "external-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-initial = "external-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-for-gcc = "external-toolchain"

# glibc is configured for OLDEST_KERNEL kernel version, so we set minimal
# sane versions there

OLDEST_KERNEL ?= "2.4.0"

# ARM OABI is fine with 2.4.0, EABI is not, so we override on "-gnueabi"
# TARGET_OS here, not on architecture which is 'arm' in both cases
OLDEST_KERNEL_linux-gnueabi ?= "2.6.14"
OLDEST_KERNEL_sparc64 ?= "2.4.21"
OLDEST_KERNEL_ppc64 ?= "2.4.21"
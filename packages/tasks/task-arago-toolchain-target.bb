DESCRIPTION = "Target packages for a standalone Arago SDK or external toolchain"
ALLOW_EMPTY = "1"
PR = "r1"

PACKAGES = "${PN}"

# Stuff contained in this SDK is largely taken from task-sdk-base.bb.
# This is a starting point, and nothing more at present -- please fill
# this out with a reasonable set of development tools for a SlugOS image.
# Also feel free to remove stuff that's silly.

RDEPENDS_${PN} = "\
#    libgcc \
#    linux-libc-headers-dev \
#    libssl \
#    libcrypto \
#    openssl-dev \
#    libstdc++ \
#    external-toolchain-target \
#    libasound \
    alsa-dev \
    alsa-lib-dev \
    alsa-utils-dev \
    curl-dev \
    e2fsprogs-libs-dev \
    i2c-tools-dev \
    freetype-dev \
    jpeg-dev \
    lzo-dev \
    libopkg-dev \
    libpng-dev \
    libpng12-dev \
    readline-dev \
    libts-dev \
    libusb-compat-dev \
    libusb1-dev \
    libvolume-id-dev \
    zlib-dev \
    mtd-utils-dev \
    ncurses-dev \
    opkg-nogpg-dev \
    sysvinit-dev \
    "

# Not sure if we need these or not...
NOT_SURE_ABOUT = "\
    libsegfault \
    "

# This one needs further investigation; seems to be some sort
# of naming problem that breaks the SDK when it is added directly.
ODDLY_BROKEN_PACKAGES ="\
    libz-dev \
    "

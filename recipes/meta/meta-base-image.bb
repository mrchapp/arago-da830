PR = "r3"

SDK_DISTRO = "arago"
TOOLCHAIN_OUTPUTNAME = "${SDK_DISTRO}-${PN}-${MACHINE}-${SDK_SUFFIX}"

TOOLCHAIN_HOST_TASK = "task-arago-base-host"
TOOLCHAIN_TARGET_TASK = "task-arago-base-target"

TOOLCHAIN_TARGET_EXCLUDE = "\
libc6 \
libc6-dev \
glibc-extra-nss \
libgcc1 \
linux-libc-headers-dev \
libthread-db1 \
curl \
opkg-nogpg \
alsa-conf-base \
update-rc.d \
update-rc.d-dev \
tslib-conf \
pointercal \
sysvinit \
sysvinit-inittab \
i2c-tools \
mtd-utils \
"

require meta-toolchain.bb
SDK_SUFFIX = "sdk"


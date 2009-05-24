PR = "r12"

SDK_DISTRO = "arago"
TOOLCHAIN_OUTPUTNAME = "${SDK_DISTRO}-${PN}-${MACHINE}-${SDK_SUFFIX}"

TOOLCHAIN_HOST_TASK = "task-arago-dvsdk-gstreamer-host"
TOOLCHAIN_TARGET_TASK = "task-arago-dvsdk-gstreamer-target"

DVSDK_TARGET_EXCLUDE_dm355-evm = "\
       ti-cmem-module \
       ti-dm355mm-module \
       ti-codec-combo-dm355 \
       ti-dmai-apps \
"
DVSDK_TARGET_EXCLUDE_dm6446-evm = "\
       ti-cmem-module \
       ti-dsplink-module \
       ti-codec-combo-dm6446 \
       ti-dmai-apps \
"
DVSDK_TARGET_EXCLUDE_omap3evm = "\
       ti-cmem-module \
       ti-dsplink-module \
       ti-lpm-module \
       ti-codec-combo-omap3530 \
       ti-dmai-apps \
"
DVSDK_TARGET_EXCLUDE_beagleboard = "\
       ti-cmem-module \
       ti-dsplink-module \
       ti-lpm-module \
       ti-codec-combo-omap3530 \
       ti-dmai-apps \
"

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
${DVSDK_TARGET_EXCLUDE} \
"


require meta-toolchain.bb
SDK_SUFFIX = "sdk"


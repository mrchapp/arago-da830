TOOLCHAIN_HOST_TASK ?= "task-arago-toolchain-nsdk-host"
TOOLCHAIN_TARGET_TASK ?= "task-arago-toolchain-nsdk-target"

require meta-toolchain.bb

PR = "r1"
SDK_SUFFIX = "nsdk"

# Taken from meta-toolchain-arago.bb but modified to include gstreamer and gstreamer-dev
SDK_DISTRO = "arago"
TOOLCHAIN_OUTPUTNAME = "${SDK_DISTRO}-${DISTRO_VERSION}-${FEED_ARCH}-${TARGET_OS}-${SDK_SUFFIX}"

# External CSL toolchain/glibc
TOOLCHAIN_TARGET_EXCLUDE += "\
    libc6 \
    libc6-dev \
    glibc-extra-nss \
    libgcc1 \
    linux-libc-headers-dev \
    libthread-db1 \
    sln \
    "

# X11 dependencies
TOOLCHAIN_TARGET_EXCLUDE += "\
    bigreqsproto \
    bigreqsproto-dev \
    inputproto \
    inputproto-dev \
    kbproto \
    kbproto-dev \
    libice6 \
    libice-dev \
    libsm6 \
    libsm-dev \
    libx11-6 \
    libx11-dev \
    libxau6 \
    libxau-dev \
    libxdmcp6 \
    libxdmcp-dev \
    xcmiscproto \
    xcmiscproto-dev \
    xextproto \
    xextproto-dev \
    xf86bigfontproto \
    xf86bigfontproto-dev \
    xproto \
    xproto-dev \
    xtrans-dev \
    util-macros \
    util-macros-dev \
    "

# GStreamer/media dependencies
TOOLCHAIN_TARGET_EXCLUDE += "\
    gst-plugins-base \
    gst-plugins-base-dev \
#    gstreamer \
#    gstreamer-dev \
    libgstapp-0.10-0 \
    libgstaudio-0.10-0 \
    libgstcdda-0.10-0 \
    libgstfft-0.10-0 \
    libgstinterfaces-0.10-0 \
    libgstnetbuffer-0.10-0 \
    libgstpbutils-0.10-0 \
    libgstriff-0.10-0 \
    libgstrtp-0.10-0 \
    libgstrtsp-0.10-0 \
    libgstsdp-0.10-0 \
    libgsttag-0.10-0 \
    libgstvideo-0.10-0 \
    libid3tag0 \
    libid3tag-dev \
    libmad0 \
    libmad-dev \
    libmikmod \
    libmikmod-dev \
    libogg0 \
    libogg-dev \
    libvorbis0 \
    libvorbis-dev \
    libvorbisidec1 \
    libvorbisidec-dev \
    libsndfile1 \
    libsndfile-dev \
    "

# OpenSSL dependencies
#TOOLCHAIN_TARGET_EXCLUDE += "\
#    libcrypto0.9.8 \
#    libssl0.9.8 \
#    openssl \
#    openssl-dev \
#    "

TOOLCHAIN_TARGET_EXCLUDE += "\
    curl \
    opkg \
    alsa-utils \
    alsa-conf-base \
    update-rc.d \
    update-rc.d-dev \
    tslib-conf \
    pointercal \
    sysvinit \
    sysvinit-inittab \
    i2c-tools \
    mtd-utils \
    util-linux-ng-fdisk \
    util-linux-ng-cfdisk \
    util-linux-ng-sfdisk \
    util-linux-ng-mount \
    util-linux-ng-mountall \
    util-linux-ng-umount \
    util-linux-ng-losetup \
    util-linux-ng-swaponoff \
    util-linux-ng-readprofile \
    util-linux-ng-blkid \
    util-linux-ng-fsck \
    util-linux-ng \
    udev-utils \
    dbus \
    wpa-supplicant-passphrase \
    devmem2 \
    devmem2-dev \
    fbset \
    fbset-dev \
    fbset-modes \
    kernel \
    module-init-tools \
    module-init-tools-depmod \
    omap3-sgx-modules \
    update-modules \
    "

# Shamelessly stolen and modified from cmake.bbclass
# If there is a better way for re-use in an SDK environ, I don't know it
# Moreover parts of this are application specific but don't know how to re-use from the app recipe.

# Compiler flags
OECMAKE_C_FLAGS ?= "${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -DARCH_DA8xx -DARCH_DA8xx_DOUBLE_KEY_HACK -D__NSDK_RELEASE__ -D__NSDK_STACKTRACE__"

generate_cmake_toolchain_file () {
        
    outFile="${SDK_OUTPUT}/${SDKPATH}/toolchain.cmake"
    touch $outFile

    echo 'set( CMAKE_SYSTEM_NAME' `echo ${SDK_OS} | sed 's/^./\u&/'` ')' >> ${outFile}
    echo 'set( CMAKE_C_COMPILER   $ENV{TOOLCHAIN_PATH}/bin/$ENV{TARGET_SYS}-gcc )' >> ${outFile}
    echo 'set( CMAKE_CXX_COMPILER $ENV{TOOLCHAIN_PATH}/bin/$ENV{TARGET_SYS}-g++ )' >> ${outFile}

    # The AR and LINKER settings are quirky
    echo 'set( CMAKE_AR     $ENV{TOOLCHAIN_PATH}/bin/$ENV{TARGET_SYS}-ar CACHE FILEPATH "" )' >> ${outFile}
    echo 'set( CMAKE_LINKER $ENV{TOOLCHAIN_PATH}/bin/$ENV{TARGET_SYS}-ld CACHE FILEPATH "" )' >> ${outFile}

    echo 'set( CMAKE_C_FLAGS "'"${OECMAKE_C_FLAGS}"' -isystem$ENV{SDK_PATH}/$ENV{TARGET_SYS}/usr/include -I$ENV{TOOLCHAIN_PATH}/$ENV{TARGET_SYS}/libc/usr/include/" CACHE STRING "CFLAGS" )' >> ${outFile}
    echo 'set( CMAKE_CXX_FLAGS "${CMAKE_C_FLAGS} -fpermissive" CACHE STRING "CXXFLAGS" )' >> ${outFile}
    echo 'set( CMAKE_C_FLAGS_RELEASE "'"${SELECTED_OPTIMIZATION} -DNDEBUG"'" CACHE STRING "CFLAGS for release" )' >> ${outFile}
    echo 'set( CMAKE_CXX_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE}" CACHE STRING "CXXFLAGS for release" )' >> ${outFile}
    
    # only search in the paths provided (from openembedded) so cmake doesnt pick
    # up libraries and tools from the native build machine
    echo 'set( CMAKE_FIND_ROOT_PATH $ENV{SDK_PATH}/$ENV{TARGET_SYS} )' >> ${outFile}
    echo 'set( CMAKE_FIND_ROOT_PATH_MODE_PROGRAM ONLY )' >> ${outFile}
    echo 'set( CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY )' >> ${outFile}
    echo 'set( CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY )' >> ${outFile}
    
    # Use native cmake modules
    echo 'set( CMAKE_MODULE_PATH $ENV{SDK_PATH}/$ENV{TARGET_SYS}/share/cmake-2.8/Modules/ )' >> ${outFile}
    
    echo 'set( QT_INCLUDE_DIR "${CMAKE_FIND_ROOT_PATH}/usr/include/qtopia" )' >> ${outFile}
    echo 'set( QT_QTCORE_INCLUDE_DIR "${QT_INCLUDE_DIR}/QtCore" )' >> ${outFile}
    echo 'set( QT_QTDBUS_INCLUDE_DIR "${QT_INCLUDE_DIR}/QtDBus" )' >> ${outFile}
    echo 'set( QT_QTCORE_LIBRARY QtCoreE )' >> ${outFile}
    echo 'set( QT_QTDBUS_LIBRARY QtDBusE )' >> ${outFile}
    echo 'set( QT_MOC_EXECUTABLE $ENV{SDK_PATH}/bin/moc4 )' >> ${outFile}
    echo 'set( QT_LRELEASE_EXECUTABLE $ENV{SDK_PATH}/bin/lrelease4 )' >> ${outFile}

    echo 'set( BUILDTYPE_DA8xx 1 ) ' >> ${outFile}
}

# Taken from meta-toolchain-qte.bb
do_populate_sdk_append() {
    script="${SDK_OUTPUT}/${SDKPATH}/environment-setup"
    touch $script
    echo 'export OE_QMAKE_CC=${TARGET_SYS}-gcc' >> $script
    echo 'export OE_QMAKE_CXX=${TARGET_SYS}-g++' >> $script
    echo 'export OE_QMAKE_LINK=${TARGET_SYS}-g++' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=$SDK_PATH/$TARGET_SYS${libdir}' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=$SDK_PATH/$TARGET_SYS${includedir}/${QT_DIR_NAME}' >> $script
    echo 'export OE_QMAKE_MOC=$SDK_PATH/bin/moc4' >> $script
    echo 'export OE_QMAKE_UIC=$SDK_PATH/bin/uic4' >> $script
    echo 'export OE_QMAKE_UIC3=$SDK_PATH/bin/uic34' >> $script
    echo 'export OE_QMAKE_RCC=$SDK_PATH/bin/rcc4' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=$SDK_PATH/bin/qdbuscpp2xml4' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=$SDK_PATH/bin/qdbusxml2cpp4' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=$SDK_PATH/$TARGET_SYS${datadir}/${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    echo 'export QMAKESPEC=$SDK_PATH/$TARGET_SYS${datadir}/${QT_DIR_NAME}/mkspecs/linux-g++' >> $script

# TODO: are these needed (they are in tisdk-install.sh.)
#       echo 'export OE_QMAKE_LDFLAGS="-L${SDK_PATH}/${TARGET_SYS}/usr/lib -Wl,-rpath-link,${SDK_PATH}/${TARGET_SYS}/usr/lib -Wl,-O1 -Wl,--hash-style=gnu"' >> $script
#       echo 'export OE_QMAKE_STRIP="echo"' >> $script

    generate_cmake_toolchain_file
    
    # Repack SDK with new environment-setup
    cd ${SDK_OUTPUT}
    fakeroot tar cfz ${SDK_DEPLOY}/${TOOLCHAIN_OUTPUTNAME}.tar.gz .
}

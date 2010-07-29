TOOLCHAIN_HOST_TASK ?= "task-arago-base-tisdk-toolchain-host"
TOOLCHAIN_TARGET_TASK ?= "task-arago-base-tisdk-toolchain-target"

require meta-toolchain-arago.bb

PR = "r1"
SDK_SUFFIX = "sdk"

do_populate_sdk_append() {
    script = "${SDK_OUTPUT}/${SDKPATH}/environment-setup"
    touch $script

    echo 'export CC=${TARGET_PREFIX}gcc' >> $script	
    echo 'export CPP="${TARGET_PREFIX}gcc -E"' >> $script	
    echo 'export NM=${TARGET_PREFIX}nm' >> $script	
    echo 'export RANLIB=${TARGET_PREFIX}ranlib' >> $script	
    echo 'export OBJCOPY=${TARGET_PREFIX}objcopy' >> $script	
    echo 'export STRIP=${TARGET_PREFIX}strip' >> $script	
    echo 'export AS=${TARGET_PREFIX}as' >> $script	
    echo 'export AR=${TARGET_PREFIX}ar' >> $script	
    echo 'export OBJDUMP=${TARGET_PREFIX}objdump' >> $script	
    echo 'export PKG_CONFIG_ALLOW_SYSTEM_LIBS=1' >> $script
       
    # Repack SDK with new environment-setup
    cd ${SDK_OUTPUT}
    fakeroot tar cfz ${SDK_DEPLOY}/${TOOLCHAIN_OUTPUTNAME}.tar.gz .
}

TOOLCHAIN_HOST_TASK ?= "task-arago-toolchain-base-tisdk-host"
TOOLCHAIN_TARGET_TASK ?= "task-arago-toolchain-base-tisdk-target"

require meta-toolchain-arago.bb

PR = "r2"
SDK_SUFFIX = "sdk"

do_populate_sdk_append() {
    script="${SDK_OUTPUT}/${SDKPATH}/environment-setup"
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

    # Copy licenses that were found during build if any to
    # the SDK directory.
    if [ -e ${DEPLOY_DIR}/licenses ]
    then
        cp -rf ${DEPLOY_DIR}/licenses ${SDK_OUTPUT}/${SDKPATH}/

        # Add a README in the licenses directory about what these
        # licenses are for.
        readme="${SDK_OUTPUT}/${SDKPATH}/licenses/README"
        touch $readme

        echo "This directory contains a subdirectory for each" >> $readme
        echo "package used during the creation of this SDK that" >> $readme
        echo "defined its own license or copying terms within its" >> $readme
        echo "sources.  By default this is any file(s) matching the" >> $readme
        echo "pattern COPYING* or LICENSE*." >> $readme
        echo "" >> $readme
        echo "This directory may contain more packages than are" >> $readme
        echo "actually installed in your SDK.  You can verify the" >> $readme
        echo "packages installed in your SDK and their corresponding" >> $readme
        echo "licenses by viewing the software manifest in the" >> $readme
        echo "top-level docs directory of the TI SDK." >> $readme
    fi

    # Repack SDK with new environment-setup and licenses
    cd ${SDK_OUTPUT}
    fakeroot tar cfz ${SDK_DEPLOY}/${TOOLCHAIN_OUTPUTNAME}.tar.gz .
}

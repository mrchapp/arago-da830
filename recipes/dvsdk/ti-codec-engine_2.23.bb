DESCRIPTION = "Codec Engine 2.23 for TI ARM/DSP processors"

# compile time dependencies
DEPENDS             = "ti-dspbios ti-xdctools"
DEPENDS_dm6446-evm 	+= "ti-dsplink ti-cmem ti-cgt6x"
DEPENDS_omap3evm   	+= "ti-dsplink ti-cmem ti-lpm ti-cgt6x"
DEPENDS_dm355-evm 	+= "ti-cmem ti-codec-combo-dm355"

PREFERED_VERSION_ti_dspbios 		= "533"
PREFERED_VERSION_ti_cgt6x   		= "60"
PREFERED_VERSION_ti_xdctools 		= "310"
PREFERED_VERSION_ti-dsplink-module 	= "161"
PREFERED_VERSION_ti-cmem-module 	= "223"
PREFERED_VERSION_ti-lpm-module 		= "223"

# run time dependencies
RDEPENDS_dm6446-evm = "ti-dsplink-module ti-cmem-module"
RDEPENDS_omap3evm   = "ti-dsplink-module ti-cmem-module ti-lpm-module"
RDEPENDS_dm355-evm 	= "ti-cmem-module ti-codec-combo-dm355"

# what this recipe provides
PACKAGES += "ti-codec-engine-apps"
PROVIDES += "ti-codec-engine-apps"

# tconf from xdctools dislikes '.' in pwd :/
PR = "r0"
PV = "223"

# NOTE: This in internal ftp running on Brijesh's linux host.
# This will not work outside TI network and the link should be remove once
# we get external http:// URL
OE_ALLOW_INSECURE_DOWNLOADS = "1"
SRC_URI = "ftp://156.117.95.201/codec_engine_2_23.tar.gz \
           file://loadmodules-ti-codec-engine-apps.sh \
           file://unloadmodules-ti-codec-engine-apps.sh \
           file://config.bld \
"

# Set the source directory
S = "${WORKDIR}/codec_engine_2_23"

# CODEC ENGINE - This tells codec engine which targets to build
CEEXAMPLESDEVICES             ?= "DM6446"
CEEXAMPLESDEVICES_omap3evm    ?= "OMAP3530"
CEEXAMPLESDEVICES_dm644x-evm  ?= "DM6446"

PLATFORM            ?= "evm3530"
PLATFORM_omap3evm   ?= "evm3530"
PLATFORM_dm644x-evm ?= "evmDM6446"

BUILDDSP            ?= "false"
BUILDDSP_omap3evm   ?= "true"
BUILDDSP_dm644x-evm ?= "true"

DUALCPU             ?= "false"
DUALCPU_omap3evm    ?= "true"
DUALCPU_dm644x-evm  ?= "true"

PARALLEL_MAKE = ""

STAGING_TI_DSPBIOS_DIR="${STAGING_DIR}/${HOST_SYS}/ti-dspbios"
STAGING_TI_CGT6x_DIR="${STAGING_DIR}/${HOST_SYS}/ti-cgt6x"
STAGING_TI_XDCTOOLS_DIR="${STAGING_DIR}/${HOST_SYS}/xdctools"
	
do_compile () {
    # FIXME: Have some issue with building codec engine. DISABLING BUILD UNTIL
    # we figure out right way of building it
    
    echo "Do nothing"
    
    # CE - EXAMPLES
    # Now build the CE examples
    #cd ${S}/examples
    
    # cp ${WORKDIR}/config.bld ${S}/examples/
    #sed -i -e s:CROSS_DIR:${CROSS_DIR}:g ${S}/examples/config.bld 
    #sed -i -e s:PLATFORM:${PLATFORM}:g ${S}/examples/config.bld
    #sed -i -e s:doBuildArm:true:g ${S}/examples/config.bld
    #sed -i -e s:doBuildDsp:${BUILDDSP}:g ${S}/examples/config.bld
    #sed -i -e s:buildDualCpu:${DUALCPU}:g ${S}/examples/config.bld
    #sed -i -e s:CROSS_PREFIX:${TARGET_PREFIX}gcc:g ${S}/examples/config.bld
    #sed -i -e s:TICGTOOLSDIR:${TICGTOOLSDIR}:g ${S}/examples/config.bld

    # Start building the CE examples: codecs, extensions, servers (codec bundles) and ARM side apps 
    # TODO : Make clean doesn't do what you'd expect, it only cleans stuff you've enabled, so some cruft remains
    # TODO : Figure out how to pass PRODUCTS=... or alternative method, so that we don't build the 'local' versions
    
    # FIXME: Hitting compiling issue with video_copy and disabling the make so
    # we can test other recipe       
    #for i in codecs extensions servers apps ; do
    #    make \
    #      DEVICES="${CEEXAMPLESDEVICES}" \
    #      CE_INSTALL_DIR="${S}" \
    #      XDC_INSTALL_DIR="${STAGING_TI_XDCTOOLS_DIR}" \
    #      BIOS_INSTALL_DIR="${STAGING_TI_DSPBIOS_DIR}" \
    #      CC_V5T="bin/${TARGET_PREFIX}gcc" \
    #      CGTOOLS_V5T="${CROSS_DIR}" \
    #      CGTOOLS_C64P="${STAGING_TI_CGT6x_DIR}" \
    #      XDCTARGETS="gnu.targets.arm.GCArmv5T ti.targets.C64P" \ 
    #      -C ${S}/examples/ti/sdo/ce/examples/$i \
    #     clean
    # done    
}

do_install () {

    echo "Do nothing"
    
    # CE sample apps - this is very 64x / v5T specific at the moment - we really need CE to give us this list...
    #install -d ${D}/${datadir}/ti-codec-engine

    # we change pwd so that find gives us relative path to the files, which we use to create the same structure on the target
    #cd ${S}/examples/ti/sdo/ce

    # first find all the app files named '.out'
    #for i in $(find . -name "*.out") ; do
    #    # first create the directory
    #    install -d ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #    # now copy the file     
    #    install ${i} ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #done

    # next find all the app files named '.xv5T'
    #for i in $(find . -name "*.xv5T") ; do
    #    # first create the directory
    #    install -d ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #    # now copy the file     
    #    install ${i} ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #done

    # then find all the app/server files named '.x64P'
    #for i in $(find . -name "*.x64P") ; do
    #    # first create the directory
    #    install -d ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #    # now copy the file     
    #    install ${i} ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #done

    # finally find all the app files named '.dat'
    #for i in $(find . -name "*.dat") ; do
    #    # first create the directory
    #    install -d ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #    # now copy the file     
    #    install ${i} ${D}/${datadir}/ti-codec-engine/`dirname ${i}`
    #done

    # CE test app module un/load scripts
    #install ${WORKDIR}/loadmodules-ti-codec-engine-apps.sh ${D}/${datadir}/ti-codec-engine
    #install ${WORKDIR}/unloadmodules-ti-codec-engine-apps.sh ${D}/${datadir}/ti-codec-engine

    # we should install the CMEM apps as well here
        # - TODO...

    # finally, strip targets that we're not supporting here
    # - TODO...
    
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
# Codec Engine and friends need a complete tree, so stage it all - possibly could use repoman for this later
do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}


INHIBIT_PACKAGE_STRIP = "1"

FILES_ti-codec-engine = "${datadir}/ti-codec-engine/*"


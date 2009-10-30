DESCRIPTION = "Gstreamer plugin for TI Davinci and OMAP processors"

inherit autotools

DEPENDS = "ti-dmai gstreamer gst-plugins-base gst-plugins-good gst-plugins-ugly"

# Fetch source from svn repo
SRC_URI = "svn://gforge.ti.com/svn/gstreamer_ti/trunk;module=gstreamer_ti;proto=https;user=anonymous;pswd='' \
	"

# Again, no '.' in PWD allowed :(
PR = "r23"
PV = "svnr${SRCREV}"

S = "${WORKDIR}/gstreamer_ti/ti_build/ticodecplugin"

include ti-multimedia-common.inc

CPPFLAGS_append 			= " -DPlatform_${PLATFORM}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install_prepend () {
	# install gstreamer demo scripts
	install -d ${D}/${installdir}/gst
	cp -r ${WORKDIR}/gstreamer_ti/gstreamer_demo/shared ${D}/${installdir}/gst
	cp -r ${WORKDIR}/gstreamer_ti/gstreamer_demo/${PLATFORM} ${D}/${installdir}/gst

	# default loadmodule script is hard-coded for insmod, change to modprobe
	sed -i 's/insmod/modprobe/g' ${D}/${installdir}/gst/${PLATFORM}/loadmodules.sh
	sed -i 's/.ko//g' ${D}/${installdir}/gst/${PLATFORM}/loadmodules.sh
	if [ "${PLATFORM}" = "omap3530" ]; then
		echo "modprobe sdmak" >> ${D}/${installdir}/gst/${PLATFORM}/loadmodules.sh
	fi
	chmod 0755 ${D}/${installdir}/gst -R
}

pkg_postinst_gstreamer-ti-demo-script () {
	ln -sf ${installdir}/codec-combo/* ${installdir}/gst/${PLATFORM}/
}

PACKAGES += "gstreamer-ti-demo-script"
FILES_gstreamer-ti-demo-script = "${installdir}/gst/*"
RDEPENDS_gstreamer-ti-demo-script = "gstreamer-ti"

RDEPENDS_${PN} = "ti-dmai-apps"
FILES_${PN} += "${libdir}/gstreamer-0.10/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.a ${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"


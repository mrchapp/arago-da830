inherit sdk

DEPENDS_dm355-evm  = "ti-xdctools-sdk ti-codec-engine-sdk ti-codec-combo-dm355-sdk ti-dmai-sdk"

do_install () {
	mkdir -p  ${D}/${prefix}/dvsdk/
	echo "PLATFORM=${MACHINE}" > ${D}/${prefix}/dvsdk/Rules.make
	for file in `ls -1 ${TMPDIR}/ti-sdk-rules` ; do
	  cat ${TMPDIR}/ti-sdk-rules/${file} >> ${D}/${prefix}/dvsdk/Rules.make
	done
}

FILES_${PN} = "${prefix}/dvsdk/Rules.make"


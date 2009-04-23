DESCRIPTION = "DVSDK image contains DMAI"
PR = "r0"

inherit task

ARAGO_DMAI = "\
	ti-cmem-module \
	ti-dm355mm-module \
	ti-dmai-apps \
	"

RDEPENDS_task-arago-dvsdk = "\
    ${ARAGO_DMAI} \
    "

DESCRIPTION = "DVSDK image contains DMAI"
PR = "r0"

inherit task

ARAGO_DMAI = "\
	ti-dmai-apps \
	ti-cmem-module \
	ti-dm355mm-module \
	"

RDEPENDS_task-arago-dvsdk = "\
    ${ARAGO_DMAI} \
    "

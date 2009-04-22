DESCRIPTION = "DVSDK image contains DMAI"
PR = "r0"

inherit task

ARAGO_DMAI = "\
	ti-dmai-svn \
	"

RDEPENDS_task-arago-dvsdk = "\
    ${ARAGO_DMAI} \
    "

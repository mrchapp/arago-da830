DESCRIPTION = "Extended task to add dvsdk components"
PR = "r4"

inherit task

# add dvsdk components
RDEPENDS_${PN} = "\
	ti-dmai-apps \
    "


DESCRIPTION = "Extended task to add dvsdk components"
PR = "r7"

inherit task

# add dvsdk components
RDEPENDS_${PN} = "\
	ti-dmai-apps \
    "


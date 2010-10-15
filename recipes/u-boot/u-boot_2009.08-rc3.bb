require u-boot-omapl1.inc

# da850-sdi - 2009.08
SRC_URI_da850-sdi	= "git://arago-project.org/git/projects/u-boot-omapl1.git;protocol=git \
			   file://0001-da850-Add-SDI-Spectrum-Digital-board.patch;apply=yes \
			   file://0002-winbond-add-support-W25Q64-parts.patch;apply=yes \
			  "

SRCREV_da850-sdi	= "17a083a2bdfc84cc9c9894c7a5c8156d6d4d1e62"
PR			= "r1+gitr${SRCREV}"

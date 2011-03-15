require u-boot-omapl1.inc

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2009.11_DAVINCIPSP_03.20.00.14"

SRC_URI_da850-sdi	+=	"git://arago-project.org/git/projects/u-boot-omapl1.git;protocol=git \
				file://0001-da850sdi-Initial-board-support-based-on-da850evm.patch;apply=yes"

UVER = "2009.11"
PSPREL = "03.20.00.14"

PR = "r1"

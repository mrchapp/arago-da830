# Common pieces for Arago images

# 4KB per 1 inode should be enough
EXTRA_IMAGECMD_ext2.gz += "-i 4096"

# Disable this due to distribution restrictions
# DISTRO_SSH_DAEMON ?= "dropbear"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

IMAGE_INSTALL = "\
    ${DISTRO_SSH_DAEMON} \
    angstrom-version \
    "

IMAGE_LINGUAS = ""

inherit image

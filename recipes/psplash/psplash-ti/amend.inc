# Amend the psplash-ti recipe to use the configurability patch
# for version 422.  This is done to set the background color
# of the psplash screen to black instead of 0xecece1.
# NOTE: This amend can be removed once the arago-oe-dev
#       sync contains this patch and uses it in the
#       psplash-ti_svn.bb recipe
SRC_URI += "file://0001-configurability-for-rev-422.patch"


# Set the suffix to specify amended packages
PR_append = "-arago1"

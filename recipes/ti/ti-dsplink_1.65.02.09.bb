require ti-dsplink.inc

PE = "1"
PV = "1_65_02_09"
PV_dot = "1.65.02.09"
PV_major = "1_65"

PV_DL_PATH = "DSPLink/${PV_major}/${PV}/${PV_dot}"

SRC_URI[dsplinktarball.md5sum] = "898793a1d0b3e06fd4daa31826961fe8"
SRC_URI[dsplinktarball.sha256sum] = "2fdb77c071d931b1bdf54cca545f9edc1b0f1f8f280bc9cc04a01e9848b0e74c"

SRC_URI += "file://dsplink_1_65_02_09-missingheader.patch"

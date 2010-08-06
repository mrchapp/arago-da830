require flash-utility.inc

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/354/3685/setup.exe;name=setup \
    https://gforge.ti.com/gf/download/frsrelease/354/3684/FlashInstaller.msi;name=flashinstaller \
    "

SRC_URI[setup.md5sum] = "6c05b22317939f11332ce94860d253e0"
SRC_URI[setup.sha256sum] = "167a5a6e97fc657dd8668c4522d75f0eee548729ac28d461f9bd0580d394e912"

SRC_URI[flashinstaller.md5sum] = "83b065ddc224d83b670250f11935c3a8"
SRC_URI[flashinstaller.sha256sum] = "3c49c28684c5df73ed3735a6969e3236aa3fe161e24f5fb31b17fc132ddcc089"

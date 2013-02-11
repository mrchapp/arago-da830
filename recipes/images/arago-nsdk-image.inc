# Common pieces for Arago nSDK images

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-qte \
    task-arago-nsdk \
    avahi-daemon \
    "

BAD_RECOMMENDATIONS += "qt4-embedded-assistant"
BAD_RECOMMENDATIONS += "qt4-embedded-demos"
BAD_RECOMMENDATIONS += "qt4-embedded-designer"
BAD_RECOMMENDATIONS += "qt4-embedded-examples"
BAD_RECOMMENDATIONS += "qt4-embedded-fonts"
BAD_RECOMMENDATIONS += "qt4-embedded-linguist"
BAD_RECOMMENDATIONS += "qt4-embedded-mkspecs"
BAD_RECOMMENDATIONS += "qt4-embedded-qt3to4"
BAD_RECOMMENDATIONS += "qt4-embedded-tools"

BAD_RECOMMENDATIONS += "libqtassistantcliente4"
BAD_RECOMMENDATIONS += "libqtclucenee4"
BAD_RECOMMENDATIONS += "libqtdesignere4"
BAD_RECOMMENDATIONS += "libqtdesignercomponentse4"
BAD_RECOMMENDATIONS += "libqthelpe4"
BAD_RECOMMENDATIONS += "libqtmultimediae4"
BAD_RECOMMENDATIONS += "libqtscripttoolse4"
BAD_RECOMMENDATIONS += "libqtwebkite4"
BAD_RECOMMENDATIONS += "libqt3supporte4"

BAD_RECOMMENDATIONS += "libphonone4"

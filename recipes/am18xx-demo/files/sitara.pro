# -------------------------------------------------
# am18xx-demo Qt Sitara App
# Copyright Texas Instruments, 2010
# -------------------------------------------------
# qmake -o Makefile am18xx-demo.pro
# -------------------------------------------------
TARGET = am18xx-demo
TEMPLATE = app
DEPENDPATH += .
INCLUDEPATH += .
CONFIG += qt
SOURCES += main.cpp applicationmanager.cpp applicationicon.cpp automation.cpp slideshow.cpp homescreen.cpp
HEADERS += applicationmanager.h
RESOURCES += sitara.qrc
QMAKE_CLEAN += am18xx-demo

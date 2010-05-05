# -------------------------------------------------
# am1x-demo Qt Sitara App
# Copyright Texas Instruments, 2010
# -------------------------------------------------
# qmake -o Makefile am1x-demo.pro
# -------------------------------------------------
TARGET = am1x-demo
TEMPLATE = app
DEPENDPATH += .
INCLUDEPATH += .
CONFIG += qt
SOURCES += main.cpp applicationmanager.cpp applicationicon.cpp automation.cpp slideshow.cpp homescreen.cpp
HEADERS += applicationmanager.h
RESOURCES += sitara.qrc
QMAKE_CLEAN += am1x-demo

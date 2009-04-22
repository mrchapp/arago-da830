require conf/machine/include/tune-arm926ejs.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"

TARGET_ARCH = "arm"

HOTPLUG = "udev"

PREFERRED_PROVIDER_virtual/kernel = "linux-davinci"

KERNEL_IMAGETYPE = "uImage"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

#EXTRA_IMAGEDEPENDS += "u-boot ubl"

SERIAL_CONSOLE ?= "115200 ttyS0"
EXTRA_IMAGECMD_jffs2 = "--pad --little-endian --eraseblock=0x20000 -n"

#ROOT_FLASH_SIZE = "29"

MACHINE_FEATURES += " kernel26 serial usbhost alsa "

#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh

echo
echo "--------------------------------------------------------------------------------"
echo "This step will set up the u-boot variables for booting the EVM."

ipdefault=`ifconfig | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1 }'`
platform=`grep PLATFORM= $cwd/../Rules.make | cut -d= -f2`
prompt="EVM >"

echo "Autodetected the following ip address of your host, correct it if necessary"
read -p "[ $ipdefault ] " ip
echo

if [ ! -n "$ip" ]; then
    ip=$ipdefault
fi

if [ -f $cwd/../.targetfs ]; then
    rootpath=`cat $cwd/../.targetfs`
else
    echo "Where is your target filesystem extracted?"
    read -p "[ ${HOME}/targetfs ]" rootpath

    if [ ! -n "$rootpath" ]; then
        rootpath="${HOME}/targetfs"
    fi
    echo
fi

uimagesrc=`ls -1 $cwd/../psp/prebuilt-images/uImage*.bin`
uimagedefault=`basename $uimagesrc`

baseargs="console=ttyS0,115200n8 rw mem=59M"
videoargs1="video=davincifb:vid0=OFF:vid1=OFF:osd0=720x576x16,4050K"
videoargs2="dm365_imp.oper_mode=0 davinci_capture.device_type=4"
videoargs3="davinci_display.cont2_bufsize=6291456"
videoargs4="vpfe_capture.cont_bufoffset=6291456"
videoargs5="vpfe_capture.cont_bufsize=6291456"
videoargs6="davinci_enc_mngr.ch0_output=COMPONENT"
videoargs7="davinci_enc_mngr.ch0_mode=480P-60"
videoargs="$videoargs1 $videoargs2 $videoargs3 $videoargs4 $videoargs5 $videoargs6 $videoargs7"
fssdargs="root=/dev/mmcblk0p2 rootwait"
fsnfsargs="root=/dev/nfs nfsroot=$ip:$rootpath"

#echo "Select UBL location:"
#echo " 1: flash"
#echo " 2: SD card"
#echo
#read -p "[ 1 ] " ubl

#if [ ! -n "$ubl" ]; then
#    ubl="1"
#fi

echo "Select Linux kernel location:"
echo " 1: TFTP"
echo " 2: SD card"
echo
read -p "[ 1 ] " kernel

if [ ! -n "$kernel" ]; then
    kernel="1"
fi

echo
echo "Select root file system location:"
echo " 1: NFS"
echo " 2: SD card"
echo
read -p "[ 1 ] " fs

if [ ! -n "$fs" ]; then
    fs="1"
fi

if [ "$kernel" -eq "1" ]; then
    echo
    echo "Available kernel images in /tftproot:"
    for file in /tftpboot/*; do
        basefile=`basename $file`
        echo "    $basefile"
    done
    echo
    echo "Which kernel image do you want to boot from TFTP?"
    read -p "[ $uimagedefault ] " uimage

    if [ ! -n "$uimage" ]; then
        uimage=$uimagedefault
    fi

    bootcmd="setenv bootcmd 'dhcp;setenv serverip $ip;tftpboot;bootm'"
    serverip="setenv serverip $ip"
    bootfile="setenv bootfile $uimage"

    if [ "$fs" -eq "1" ]; then
        bootargs="setenv bootargs $baseargs $videoargs $fsnfsargs ip=dhcp"
        cfg="uimage-tftp_fs-nfs"
    else
        bootargs="setenv bootargs $baseargs $videoargs $fssdargs ip=off"
        cfg="uimage-tftp_fs-sd"
    fi
else
    if [ "$fs" -eq "1" ]; then
        bootargs="setenv bootargs $baseargs $videoargs $fsnfsargs ip=dhcp"
        bootcmd="setenv bootcmd 'bootm 0x80700000'"
        cfg="uimage-sd_fs-nfs"
    else
        bootargs="setenv bootargs $baseargs $videoargs $fssdargs ip=off"
        bootcmd="setenv bootcmd 'bootm 0x80700000'"
        cfg="uimage-sd_fs-sd"
    fi
fi

echo
echo "Resulting u-boot variable settings:"
echo
echo "setenv bootdelay 4"
echo "setenv baudrate 115200"
echo $bootargs
echo $bootcmd

if [ -n "$serverip" ]; then
    echo "setenv autoload no"
    echo $serverip
fi

if [ -n "$bootfile" ]; then
    echo $bootfile
fi
echo "--------------------------------------------------------------------------------"

do_expect() {
    echo "expect {" >> $3
    check_status
    echo "    $1" >> $3
    check_status
    echo "}" >> $3
    check_status
    echo $2 >> $3
    check_status
    echo >> $3
}

echo
echo "--------------------------------------------------------------------------------"
echo "Would you like to create a minicom script with the above parameters (y/n)?"
read -p "[ y ] " minicom
echo

if [ ! -n "$minicom" ]; then
    minicom="y"
fi

if [ "$minicom" == "y" ]; then
    minicomfile=setup_$platform_$cfg.minicom
    minicomfilepath=$cwd/../$minicomfile

    if [ -f $minicomfilepath ]; then
        echo "Moving existing $minicomfile to $minicomfile.old"
        mv $minicomfilepath $minicomfilepath.old
        check_status
    fi

    timeout=300
    echo "timeout $timeout" >> $minicomfilepath
    echo "verbose on" >> $minicomfilepath
    echo >> $minicomfilepath
#if [ "$ubl" -eq "2" ]; then
#    do_expect "\"\ > \"" "send \"1\"" $minicomfilepath
#fi
    do_expect "\"stop autoboot:\"" "send \"\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"setenv bootdelay 4\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"setenv baudrate 115200\"" $minicomfilepath
    do_expect "\"ENTER ...\"" "send \"\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"setenv oldbootargs \$\{bootargs\}\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"setenv bootargs $baseargs \c\"" $minicomfilepath
    echo "send \"$videoargs1 \c\"" >> $minicomfilepath
    echo "send \"$videoargs2 \c\"" >> $minicomfilepath
    echo "send \"$videoargs3 \c\"" >> $minicomfilepath
    echo "send \"$videoargs4 \c\"" >> $minicomfilepath
    echo "send \"$videoargs5 \c\"" >> $minicomfilepath
    echo "send \"$videoargs6 \c\"" >> $minicomfilepath
    echo "send \"$videoargs7 \c\"" >> $minicomfilepath
    if [ "$fs" -eq "1" ]; then
        echo "send \"$fsnfsargs \c\"" >> $minicomfilepath
        echo "send \"ip=dhcp\"" >> $minicomfilepath
    else
        echo "send \"$fssdargs \c\"" >> $minicomfilepath
        echo "send \"ip=off\"" >> $minicomfilepath
    fi
    if [ "$kernel" -eq "1" ]; then
        do_expect "\"$prompt\"" "send \"setenv autoload no\"" $minicomfilepath
        do_expect "\"$prompt\"" "send \"setenv oldserverip \$\{serverip\}\"" $minicomfilepath
        do_expect "\"$prompt\"" "send \"$serverip\"" $minicomfilepath
        do_expect "\"$prompt\"" "send \"setenv oldbootfile \$\{bootfile\}\"" $minicomfilepath
        do_expect "\"$prompt\"" "send \"$bootfile\"" $minicomfilepath
    fi
    do_expect "\"$prompt\"" "send \"setenv oldbootcmd \$\{bootcmd\}\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"$bootcmd\"" $minicomfilepath
    do_expect "\"$prompt\"" "send \"saveenv\"" $minicomfilepath
    do_expect "\"$prompt\"" "! killall -s SIGHUP minicom" $minicomfilepath

    echo "Successfully wrote $minicomfile"
    echo
    echo "Would you like to run the setup script now (y/n)? This requires you to connect"
    echo "the RS-232 cable between your host and EVM as well as your ethernet cable as"
    echo "described in the Quick Start Guide. Once answering 'y' on the prompt below"
    echo "you will have $timeout seconds to connect the board and power cycle it"
    echo "before the setup times out"
    echo
    echo "After successfully executing this script, your EVM will be set up. You will be "
    echo "able to connect to it by executing 'minicom -w' or if you prefer a windows host"
    echo "you can set up Tera Term as explained in the Software Developer's Guide."
    echo "If you connect minicom or Tera Term and power cycle the board Linux will boot."
    echo
    read -p "[ y ] " minicomsetup

    if [ ! -n "$minicomsetup" ]; then
        minicomsetup="y"
    fi

    if [ "$minicomsetup" == "y" ]; then
        pushd $cwd/..
        check_status
        minicom -S $minicomfile
        popd >> /dev/null
        check_status

    fi
    
    echo "You can manually run minicom in the future with this setup script using: minicom -S $minicomfile"
fi
echo "--------------------------------------------------------------------------------"

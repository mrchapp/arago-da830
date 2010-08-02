#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh

tftpcfg=/etc/xinetd.d/tftp
tftproot=/tftpboot

tftp() {
    echo "
service tftp
{
protocol = udp
port = 69
socket_type = dgram
wait = yes
user = nobody
server = /usr/sbin/in.tftpd
server_args = $tftproot
disable = no
}
" | sudo tee $tftpcfg > /dev/null
     check_status
     echo
     echo "$tftpcfg successfully created"
}

echo
echo "--------------------------------------------------------------------------------"
echo "This step will set up the tftp server in the $tftproot directory."
echo
echo "Note! This command requires you to have administrator priviliges (sudo access) "
echo "on your host."
read -p "Press return to continue"

if [ -d $tftproot ]; then
    echo
    echo "$tftproot already exists, not creating.."
else
    sudo mkdir -p $tftproot
    check_status
    sudo chmod 777 $tftproot
    check_status
    sudo chown nobody $tftproot
    check_status
fi

uimagesrc=`ls -1 $cwd/../psp/prebuilt-images/uImage*.bin`
uimage=`basename $uimagesrc`
if [ -f $tftproot/$uimage ]; then
    echo
    echo "$tftproot/$uimage already exists, skipping copy.."
else
    sudo cp $uimagesrc $tftproot
    check_status
    echo
    echo "Successfully copied $uimage to tftp root directory $tftproot"
fi

echo
if [ -f $tftpcfg ]; then
    echo "$tftpcfg already exists.."

    grep "$tftproot" $tftpcfg > /dev/null
    if [ "$?" -eq "0" ]; then
        echo "$tftproot already exported for TFTP, skipping.."
    else
        echo "Copying old $tftpcfg to $tftpcfg.old"
        sudo cp $tftpcfg $tftpcfg.old
        check_status
        tftp
    fi
else
    tftp
fi

echo
echo "Restarting tftp server"
sudo /etc/init.d/xinetd stop
sudo /etc/init.d/xinetd start
echo "--------------------------------------------------------------------------------"

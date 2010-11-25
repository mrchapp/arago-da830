#!/bin/sh
# Script to install packages on rootfs.
# usage
#  ./rootfs-install.sh <install_dir> [packages]
#
# Script assumes that filesystem tarball is present in $1/filesystem directory.

#
# execute command
#
execute ()
{
  $*
  if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute $*"
    exit 1;
  fi
}

#
# install packages
#
install ()
{
  cwd=$PWD
  execute "mkdir -p $rootfs_dir"
  execute "tar zxf $install_dir/filesystem/* -C $rootfs_dir"
  execute "mkdir -p ${rootfs_dir}/usr/lib/opkg"

  # install pkgs
  execute "mv $rootfs_dir/etc/opkg $rootfs_dir/etc/opkg_old"
  execute "${install_dir}/linux-devkit/bin/opkg-cl --cache $rootfs_dir/deploy/cache -o $rootfs_dir -f ${opkg_conf} update"
  execute "${install_dir}/linux-devkit/bin/opkg-cl --cache $rootfs_dir/deploy/cache -o $rootfs_dir -f ${opkg_conf} install $*"
  execute "mv $rootfs_dir/etc/opkg_old $rootfs_dir/etc/opkg"

  echo "Running preinst ..."
  for i in ${rootfs_dir}/usr/lib/opkg/info/*.preinst; do
    if [ -f $i ] && ! sh $i >/dev/null 2>&1; then
      execute "${install_dir}/linux-devkit/bin/opkg-cl -o ${rootfs_dir} -f ${opkg_conf} flag unpacked `basename $i .preinst`"
    fi
  done 

  echo "Running postinst ..."
  for i in ${rootfs_dir}/usr/lib/opkg/info/*.postinst; do
    if [ -f $i ] && ! sh $i configure >/dev/null 2>&1; then
      execute "${install_dir}/linux-devkit/bin/opkg-cl -o ${rootfs_dir} -f ${opkg_conf} flag unpacked `basename $i .postinst`"
    fi
  done 

  # Copy u-boot and uImage binaries in psp prebuilt directory
  cp -ar ${rootfs_dir}/boot/* ${install_dir}/psp/prebuilt-images/

  # remove the modules.dep to ensure that depmod re-creates this file
  rm -rf $rootfs_dir/lib/modules/*/modules.*

  execute "cd ${rootfs_dir}"
  execute "rm -rf ${install_dir}/filesystem/*.tar.gz"

  tarname="${install_dir}/filesystem/nsdk-${NSDK_INSTALL_MACHINE}-rootfs.tar.gz"
  echo "Installing nsdk-${NSDK_INSTALL_MACHINE}-rootfs.tar.gz ..."
  execute "tar zcf $tarname *"
  execute "cd $cwd"
  execute "rm -rf $rootfs_dir"
}

if [ ! -d $1/filesystem ]; then
  echo "ERROR: failed to find filesystem directory"
  exit 1
fi

install_dir=$1
opkg_conf=$1/.opkg.conf
rootfs_dir="$1/.tmp"
export D="${rootfs_dir}"
export OFFLINE_ROOT="${rootfs_dir}"
export IPKG_OFFLINE_ROOT="${rootfs_dir}"
export OPKG_OFFLINE_ROOT="${rootfs_dir}"
export OPKG_CONFDIR_TARGET="${rootfs_dir}/etc/opkg"
shift
install $*


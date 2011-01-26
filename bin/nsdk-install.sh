#! /usr/bin/env bash
#
#  Script to install nSDK
#

VERSION="0.1"

# bring in helper functions
. manifest.sh

#
# Display program usage
#
usage()
{
  echo "
Usage: `basename $1` [options] --machine <machine_name> --toolchain <toolchain path> <install_dir>

  --help                Print this help message
  --toolchain           Where is toolchain installed
"
  exit 1
}

#
# Display version 
#
version()
{
  echo "
`basename $1`: version $VERSION 
"
  exit 1
}

#
# verify if cdrom has valid arch.conf and sdktools ipk
#
verify_cdrom ()
{
  echo "Checking $machine config"
  if [ ! -f config/$machine/opkg.conf ]; then
    echo "ERROR: opkg.conf does not exist for $machine"
    exit 1;
  fi
  echo "Updating opkg.conf "
  sed  "s|file:\(..*\)ipk|file:$PWD/deploy/ipk|g" config/$machine/opkg.conf > ${install_dir}/.opkg.conf
  sed  "s|file:\(..*\)ipk|file:$PWD/deploy/ipk|g" config/$machine/opkg-sdk.conf > ${install_dir}/.opkg-sdk.conf
  opkg_conf="${install_dir}/.opkg.conf"
  opkg_sdk_conf="${install_dir}/.opkg-sdk.conf"
}

#
# execute command
#
execute ()
{
  #echo "$*"
  $*
  if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute $*"
    exit 1;
  fi
}

#
# prepare installer tools - needed to find opkg-cl and fakeroot
# 
prepare_install ()
{
  export PATH=${install_dir}/linux-devkit/bin:$PATH
  export LD_LIBRARY_PATH=${install_dir}/linux-devkit/lib:$LD_LIBRARY_PATH  
}

#
# move sourcetree in top label directory to give dvsdk finishing
#
move_to_install_dir()
{
  echo "Moving sourcetree"

  # copy prebuilt kernel image and uboot in psp/prebuilt directory
  mkdir -p ${install_dir}/psp/prebuilt-images/
  cp deploy/images/$machine/*.bin ${install_dir}/psp/prebuilt-images/
}

#
# install sourcetree and devel packages on host
#
host_install ()
{
    mkdir -p ${install_dir}/usr/lib/opkg
    echo "Installing $bsp_src"
    execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf} update"
    execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf} install $bsp_src"

    # Install any -src packages separate from the base devkit
    for file in $(find . -iname "*-src*.ipk"); do
	execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf} install $file -force-depends"
    done

    generate_sw_manifest "Packages (host):" "$install_dir" >> ${install_dir}/docs/software_manifest.htm;

    move_to_install_dir
}

#
# install arago sdk
#
install_arago_sdk ()
{
  if [ ! -f devel/arago*.tar.gz ]; then
    echo "ERROR: failed to find arago sdk tarball"
    exit 1
  fi
  arago_sdk="`ls -1 devel/arago*.tar.gz`"
  echo "Installing linux-devkit ($arago_sdk)"
  execute "tar zxf ${arago_sdk} -C ${install_dir}"

  echo "Updating Package lists"
  dir=deploy/ipk
  execute "touch $dir/Packages"
  execute "ipkg-make-index -r $dir/Packages -p $dir/Packages -l $dir/Packages.filelist -m $dir"
  for dir in deploy/ipk/*; do
      if [ -d  "$dir" ]; then
	  execute "touch $dir/Packages"
	  execute "ipkg-make-index -r $dir/Packages -p $dir/Packages -l $dir/Packages.filelist -m $dir"
      fi
  done

  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} update"

  # Install any -dev packages separate from the base devkit
  # Assume that parent package should also be installed
  for file in $(find . -iname "*-dev*.ipk"); do
      parent=$(echo $file | sed -e 's/-dev//')
      execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} install $parent -force-depends"
      execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} install $file -force-depends"
  done

  # remove these packages (see arago/meta/meta-toolchain-target.bb)
  execute "opkg-cl --cache ${install_dir}/deploy/cache -o ${install_dir}/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} remove  -force-depends  libc6 libc6-dev glibc-extra-nss libgcc1 linux-libc-headers-dev libthread-db1 sln gettext gettext-dev libgettextlib libgettextsrc"

  echo "Updating SDK_PATH env"        
  sed -i "1{s|SDK_PATH\(..*\)|SDK_PATH=$install_dir/linux-devkit/|g}" $install_dir/linux-devkit/environment-setup
  sed -i "2{s|TOOLCHAIN_PATH\(..*\)|TOOLCHAIN_PATH=${TOOLCHAIN_PATH}|g}" $install_dir/linux-devkit/environment-setup
  echo "export PS1=\"\[\e[32;1m\][linux-devkit]\[\e[0m\]:\w> \"" >> $install_dir/linux-devkit/environment-setup

  echo "Updating prefix in libtoolize "
  sed -i -e "s|/linux-devkit|$install_dir/linux-devkit|g" \
    $install_dir/linux-devkit/bin/libtoolize 

 echo "Updating PATHS in fakeroot"
  sed -i -e "s|/PATHS=\(..*\):|$install_dir/linux-devkit/bin|g" \
    $install_dir/linux-devkit/bin/fakeroot 

  sed -i "s|PATHS=/linux-devkit/lib|PATHS=$install_dir/linux-devkit/lib|g" \
      $install_dir/linux-devkit/bin/fakeroot 
}

# unset TOOLCHAIN_PATH exported by other script.
unset TOOLCHAIN_PATH

# Process command line...
while [ $# -gt 0 ]; do
  case $1 in
    --help | -h)
      usage $0
      ;;
     --version | -v)
      version $0
      ;;
   --machine)
      shift
      machine=$1;
      shift;
      ;;
    --toolchain)
      shift
      TOOLCHAIN_PATH=$1;
      shift;
      ;;
     *)
      install_dir=$1;
      shift;
      ;;
  esac
done

bsp_src="task-arago-toolchain-nsdk-bsp-host";
bsp_bin="task-arago-nsdk-bsp";

# check if machine is defined.
test -z $machine && usage $0

# check if toolchain path is defined.
test -z $TOOLCHAIN_PATH && usage $0

# check if install_dir variable is set.
test -z $install_dir && usage $0
execute "mkdir -p $install_dir"

# verify if cdrom has architecture specific opkg.conf.
verify_cdrom

# prepare installation.
prepare_install

# create software manifest header.
mkdir -p $install_dir/docs
sw_manifest_header > ${install_dir}/docs/software_manifest.htm

# install arago sdk
# NOTE:  This should be installed before any other packages so that
#        licenses are available to by copied when making the 
#        software manifest.
install_arago_sdk

# install sourcetree (or devel) ipk on host.
host_install

# Create list of IP packages to install
iplist=''
for file in $(find . -iname "*.ipk"); do
    lic=$(ipkg-list-fields $file 2>/dev/null | grep 'license' | sed 's/^.*, //')
    if [[ $lic =~ (C|c)ommercial ]] || [[ $lic =~ (P|p)roprietary ]] || [[ $file =~ streamapp ]] && [[ ! $file =~ '-dev' ]]; then 
	pkg=$(basename $file | sed 's/_.*//')
	iplist=${iplist}" $pkg"
    fi
done

# install binary ipk on target.
mkdir -p ${install_dir}/filesystem
cp deploy/images/$machine/*.tar.gz ${install_dir}/filesystem
export NSDK_INSTALL_MACHINE="$machine"
echo "Installing  $install_dir $bsp_bin $iplist"
fakeroot ./rootfs-install.sh $install_dir $bsp_bin $iplist

tar zxf `ls -1 ${install_dir}/filesystem/*.tar.gz` -C $install_dir/filesystem --wildcards *.control*
generate_sw_manifest "Packages (target):" "$install_dir/filesystem" >> ${install_dir}/docs/software_manifest.htm
rm -rf ${install_dir}/filesystem/usr

# copy original image on filesystem directory.
cp deploy/images/$machine/*.tar.gz ${install_dir}/filesystem

generate_sw_manifest "Development packages (host):" "$install_dir/linux-devkit" >> ${install_dir}/docs/software_manifest.htm

generate_sw_manifest "Development packages (target):" "$install_dir/linux-devkit/arm-none-linux-gnueabi" >> ${install_dir}/docs/software_manifest.htm

# add manifest footer.
sw_manifest_footer >> ${install_dir}/docs/software_manifest.htm

rm -rf ${opkg_conf}
rm -rf ${opkg_sdk_conf}
rm -rf $install_dir/var

# TODO: don't know which package is creating include directory on top level.
# for now remove the directory.
rm -rf ${install_dir}/include

# Remove licenses directory from Arago SDK since any applicable licenses
# have already been copied to the top-level docs/licenses directory
rm -rf ${install_dir}/linux-devkit/licenses

echo "Installation completed!"

exit 0


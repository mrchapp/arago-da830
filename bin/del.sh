#! /bin/bash

# bring in helper functions
. manifest.sh

install_dir=/home/user/work/nsdk_install

rm ${install_dir}/docs/software_manifest.htm
sw_manifest_header > ${install_dir}/docs/software_manifest.htm

generate_sw_manifest "Packages installed on the host machine:" "$install_dir" >> ${install_dir}/docs/software_manifest.htm;

#tar zxf `ls -1 ${install_dir}/filesystem/nsdk*.tar.gz` -C $install_dir/filesystem --wildcards *.control*
generate_sw_manifest "Packages installed on the target:" "$install_dir/filesystem" >> ${install_dir}/docs/software_manifest.htm
#rm -rf ${install_dir}/filesystem/usr

generate_sw_manifest "Packages installed on arago-sdk host side:" "$install_dir/linux-devkit" >> ${install_dir}/docs/software_manifest.htm

generate_sw_manifest "Packages installed on arago-sdk target side:" "$install_dir/linux-devkit/arm-none-linux-gnueabi" >> ${install_dir}/docs/software_manifest.htm

# add manifest footer.
sw_manifest_footer >> ${install_dir}/docs/software_manifest.htm


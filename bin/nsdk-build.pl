#!/usr/bin/perl -w

use POSIX;

################################################################################
# nSDK Arago build script
################################################################################
my $script_version = "0.1";

my @no_machines = ("arago", "include");
my @packages;

my $bsp_source = "task-arago-toolchain-nsdk-bsp-host";
my $bsp_binary = "task-arago-nsdk-bsp";

my $image = "arago-nsdk-image";
my $machine;
my $index = 0;

my $sdkpath_default = "sdk-cdrom";
my $label_default   = "nover";
my $machine_default = "da850-sdi";

my %machines = (
    "da830-omapl137-evm"     => {},
    "da850-omapl138-evm"     => {},
    "da850-sdi"              => {},
);

################################################################################
# main
################################################################################

print "Arago build script version $script_version\n\n";

if (! exists $ENV{'OEBASE'}) {
    print "ERROR: Arago environment variables not set, did you ";
    print "'source arago-setenv'?\n";
    exit 1;
}

my $arago_dir = "$ENV{'OEBASE'}";
my $arago_images_output_dir = "$arago_dir/arago-tmp/deploy/images";
my $arago_image_dir = "$arago_dir/arago/recipes/images";
my $arago_ipk_dir = "$arago_dir/arago-tmp/deploy/ipk";
my $arago_machine_dir = "$arago_dir/arago/conf/machine";
my $arago_tmp = "$arago_dir/arago-tmp";
my ($sysname, $nodename, $release, $version, $mtype) = POSIX::uname();
my $arago_staging = "$arago_dir/arago-tmp/sysroots/$mtype-linux";

if (! -d "$arago_machine_dir") {
    print "ERROR: $arago_dir/arago/conf/machine not found! Either your ";
    print "\$OE_BASE variable is not pointing correctly or your Arago ";
    print "installation is broken\n";
    exit 1;
}

parse_args();

get_input();

validate_input();

build_image();

copy_output();

print "\nCreating tarball\n";
$cmd = "tar -czf nsdk\_$machine\_$label.tar.gz -C $sdkpath .";
$result = system($cmd);
if ($result) {
    print "\nERROR: Failed to create tarball\n";
    exit 1;
}

print "\nBuild of nSDK completed\n";

################################################################################
# build_image
################################################################################
sub build_image
{
    my $result;
    my $cmd;

    foreach (@packages) {
        print "\nBuilding $_ for $machine\n";

        $cmd = "MACHINE=$machine bitbake $_";
        $result = system($cmd);
        if ($result) {
            print "\nERROR: Failed to build $_ for $machine\n";
            exit 1;
        }
    }

    print "\nBuilding meta-toolchain-arago-nsdk for $machine\n";
    $cmd = "MACHINE=$machine META_SDK_PATH=/linux-devkit bitbake meta-toolchain-arago-nsdk";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to build sdk";
        exit 1;
    }
}

################################################################################
# copy_output
################################################################################
sub copy_output
{
    my $result;
    my $cmd;
    my $march;

    if ($machine =~ m/beagleboard/ || $machine =~ m/omap3evm/ ||
        $machine =~ m/am3517-evm/ || $machine =~ m/dm37x/ ||
        $machine =~ m/am37x/) {
        $march = "armv7a";
    }
    else {
        $march = "armv5te";
    }

    # create directories
    $cmd = "mkdir -p $sdkpath/config/$machine/ $sdkpath/deploy/images/$machine $sdkpath/devel $sdkpath/deploy/ipk $sdkpath/deploy/ipk/usr/lib/opkg";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
        exit 1;
    }

    # Copy the depends ipk's
    $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg.conf -o $sdkpath/deploy/ipk update";
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg.conf -o $sdkpath/deploy/ipk install --noaction @packages";
    open(OUT, "$cmd |");
    while (<OUT>) {
        if ($_ =~m/Downloading file:/) {
            my $unused;
            my $name;

            ($unused, $name)  = split(/:/, $_);

            $name =~ s/^\s+|\s+$//g;
            chop($name);

            if ($name =~m/_all.ipk/) {
                $dir = "all";
            }
            elsif ($name =~m/_any.ipk/) {
                $dir = "any";
            }
            elsif ($name =~m/_noarch.ipk/) {
                $dir = "noarch";
            }
            elsif ($name =~m/_arm.ipk/) {
                $dir = "arm";
            }
            elsif ($name =~m/_armv4.ipk/) {
                $dir = "armv4";
            }
            elsif ($name =~m/_armv4t.ipk/) {
                $dir = "armv4t";
            }
            elsif ($name =~m/_armv5te.ipk/) {
                $dir = "armv5te";
            }
            elsif ($name =~m/_armv7a.ipk/) {
                $dir = "armv7a";
            }
            elsif ($name =~m/\_$machine.ipk/) {
                $dir = "$machine";
            }

            print "Copying $name. \n";
            my $copycmd = "mkdir -p $sdkpath/deploy/ipk/$dir ; cp $name $sdkpath/deploy/ipk/$dir";
            $result = system($copycmd);
            if ($result) {
                print "Failed to execute $copycmd\n";
                exit 1;
            }
        }
    }
    close(OUT);

    $cmd = "rm -rf $sdkpath/deploy/ipk/usr";
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    # TODO: Even if build for omap or davinci platform, opkg.conf may have 
    # entries for both armv5te and armv7a arch. creating temporary directory.
    $cmd = "mkdir -p $sdkpath/deploy/ipk/armv5te $sdkpath/deploy/ipk/armv7a  $sdkpath/deploy/ipk/$mtype-armv5te-sdk";
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    # copy image tar
    print "\nCopying $arago_images_output_dir/$machine/$image\-${machine}.tar.gz ...";
    $cmd = "cp $arago_images_output_dir/$machine/$image\-${machine}.tar.gz $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
        exit 1;
    }

    # copy u-boot and kernel binaries
    print "\nCopying u-boot and uImage binaries ...";
    $cmd = "cp $arago_images_output_dir/$machine/u-boot-$machine.bin $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
    }

    $cmd = "cp $arago_images_output_dir/$machine/uImage-$machine.bin $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
    }

    # copy install script
    print "\nCopying $arago_dir/arago/bin/nsdk-install.sh ...";
    $cmd = "cp $arago_dir/arago/bin/nsdk-install.sh $sdkpath";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy install_rootfs script
    print "\nCopying $arago_dir/arago/bin/rootfs-install.sh ...";
    $cmd = "cp $arago_dir/arago/bin/rootfs-install.sh $sdkpath";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy opkg.conf needed during opkg installation.
    print "\nCopying $arago_staging/etc/opkg.conf  ...";
    $cmd = "cp $arago_staging/etc/opkg*.conf $sdkpath/config/$machine/";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    print "\nCopying sdk tarball  ...";
    $cmd = "cp $arago_dir/arago-tmp/deploy/sdk/arago*$march-*sdk.tar.gz $sdkpath/devel/ ";    
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }
}

################################################################################
# validate_input
################################################################################
sub validate_input
{
    if (! -e "$arago_machine_dir/$machine.conf") {
        print "ERROR: Machine $arago_machine_dir/$machine.conf not found\n";
        exit 1;
    }
}

################################################################################
# get_input
################################################################################
sub get_input
{
    my $input;

    if (!$machine) {
        print "\nAvailable Arago machine types:\n";
        foreach $x (keys %machines) {
            print "    $x\n";
        }
        print "\nWhich Arago machine type to you want to build for?\n";
        print "[ $machine_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $machine = $input;
        }
        else {
            $machine = $machine_default;
        }
    }

    if ($machine =~ m/default/i) {
        $machine = $machine_default;
    }

    if (! exists $machines{ $machine }) {
        print "ERROR: Machine $machine is not a supported machine type\n";
        exit 1;
    }

    if (!$sdkpath) {
        print "\nWhere do you want to copy Arago sdk ?\n";
        print "(Relative to $arago_dir)\n";
        print "[ $sdkpath_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $sdkpath = "$arago_dir/$input";
        }
        else {
            $sdkpath = "$arago_dir/$sdkpath_default";
        }
    }

    if (-d $sdkpath) {
        print "\nERROR: failed to create '$sdkpath' \n";
        exit 1;
    }

    if ($sdkpath =~ m/default/i) {
        $sdkpath = "$arago_dir/$sdkpath_default";
    }

    if (!$label) {
        print "\nWhat label do you want to append to the tarball (nsdk_label.tar.gz)?\n";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $label = "$input";
        }
        else {
            $label = "$label_default";
        }
    }
    if ($label =~ m/default/i) {
        $label = "$label_default";
    }

    $packages[$index++] = $bsp_source;
    $packages[$index++] = $bsp_binary;
    $packages[$index++] = $image;
}

################################################################################
# parse_args
################################################################################
sub parse_args
{   
    while (@ARGV) {
        if($ARGV[0] eq '-h' || $ARGV[0] eq '--help') {
            shift(@ARGV);
            display_help();
            exit 0;
        }

        if ($ARGV[0] eq '-i' || $ARGV[0] eq '--image') {
            shift(@ARGV);
            $image = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-l' || $ARGV[0] eq '--label') {
            shift(@ARGV);
            $label = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-m' || $ARGV[0] eq '--machine') {
            shift(@ARGV);
            $machine = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--sdkpath') {
            shift(@ARGV);
            $sdkpath = shift(@ARGV);
            next;
        }

        print "Warning: Option $ARGV[0] not supported (ignored)\n";
        shift(@ARGV);
    }
}

################################################################################
# display_help
################################################################################
sub display_help
{
    print "Usage: perl sdk-build.pl [options]\n\n";
    print "    -h | --help         Display this help message.\n";
    print "    -i | --image        Image to build.\n";
    print "    -l | --label        Label to append tarball (nsdk_label.tar.gz)\n";
    print "    -m | --machine      Machine type to build for.\n";
    print "    -p | --sdkpath      Where to generate the SDK\n";
    print "\nIf an option is not given it will be queried interactively.\n";
    print "If the value \"default\" is given for any parameter, the\n";
    print "corresponding default value will be used to build the image\n\n";
}

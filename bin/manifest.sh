#! /usr/bin/env bash
#
# Functions for generating software manifest information

# ..............................................................................
sw_manifest_header()
{
    echo "
<HTML>
<HEAD>
<TITLE>
$machine nSDK Installation Summary 
</TITLE>
</HEAD>
<BODY>
<h1><CENTER> nSDK Software Manifest ($machine)</CENTER></h1>
<h2><b><u>Legend</u></b></h2>
<table border=1 width=45%>
<tr><td>Package Name</td><td>The name of the application or files</td></tr>
<tr><td>Version</td><td>Version of the application or files</td></tr>
<tr><td>License</td><td>Name of the license or licenses that apply to the Package.</td></tr>
<tr><td>Location</td><td>The directory name and path on the media (or in an archive) where the Package is located.</td></tr>
<tr><td>Delivered As</td><td>This field will either be &ldquo;Source&rdquo;, &ldquo;Binary&rdquo; or &ldquo;Source and Binary&ldquo; and is the form the content of the Package is delivered in.  If the Package is delivered in an archive format, this field applies to the contents of the archive. If the word Limited is used with Source, as in &ldquo;Limited Source&rdquo; or &ldquo;Limited Source and Binary&rdquo; then only portions of the Source for the application are provided.</td></tr>
<tr><td>Modified </td><td>This field will either be &ldquo;Yes&rdquo; or &ldquo;No&rdquo;. A &ldquo;Yes&rdquo; means TI had made changes to the Package. A &ldquo;No&rdquo; means TI has not made any changes.</td></tr>
<tr><td>Obtained from</td><td>This field specifies where TI obtained the Package from. It may be a URL to an Open Source site, a 3rd party company name or TI. If this field contains a link to an Open Source package, the date it was downloaded is also recorded.</td></tr>
</table>

<h2><b><u>Disclaimers</u></b></h2>
<h3><b><u>Export Control Classification Number (ECCN)</u></b></h3>
Any use of ECCNs listed in the Manifest is at the user's risk and without recourse to TI. Your company, as the exporter of record, is responsible for determining the correct classification of any item at the time of export. Any export classification by TI of Software is for TI's internal use only and shall not be construed as a representation or warranty regarding the proper export classification for such Software or whether an export license or other documentation is required for exporting such Software.  

<h3><b><u>Links in the Manifest</u></b></h3>
Any links appearing on this Manifest (for example in the &ldquo;Obtained from&rdquo; field) were verified at the time the Manifest was created. TI makes no guarantee that any listed links will remain active in the future.

<h3><b><u>Open Source License References</u></b></h3>
Your company is responsible for confirming the applicable license terms for any open source Software listed in this Manifest that was not &ldquo;Obtained from&rdquo; TI.  Any open source license specified in this Manifest for Software that was not&ldquo;Obtained from&rdquo; TI is for TI's internal use only and shall not be construed as a representation or warranty regarding the proper open source license terms for such Software.
"
}

# ..............................................................................
# $1 = package name
# $2 = version
# $3 = license
# $4 = source
# $5 = location
# $6 = maintainer

gen_row()
{
    lpac=$1
    lver=$2
    llic=$3
    lsrc=$4
    lloc=$5

    # The "task" packages contain no data so skip
    # NB: just skipping empty .list files results in false postives
    # i.e. skipping too many
    if [[ ${lpac} =~ "task" ]]; then return 1; fi

    case "$llic" in 
	*unknown*) highlic="bgcolor=yellow" ;;
	*GPLv3*) highlic="bgcolor=red" ;;
	*) highlic="" ;;
    esac
    
    case "$lsrc" in
	file://*) lsrc="";;
	*) ;;
    esac

    # Unless otherwise overridden set as hard coded value
    case "$lpac" in
      *-src) delivered_as="Source and Binary"
            modified="Yes" 
            ;; 
      *)    delivered_as="Binary"
            modified="No";;            
    esac

    # ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    # Special cases
    
    # glibc-conv is part of the external-toolchain-csl
    # Not sure how this works but the recipe is at
    #    arago-oe-dev/recipes/meta/external-toolchain-csl.bb
    # The corresponding package info doesn't have license info but
    # there is licensing in the files. Moreover it is obtained externally
    # from Code Sourcery.
    if [ "$lpac" = "glibc-gconv" ] || [ "$lpac" = "glibc-gconv-iso8859-1" ] ; then
	llic="LGPLv2.1"
	highlic=""
	lsrc="http://www.codesourcery.com/"
    fi

    # ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

    # if source is empty then refer to Arago
    highobt=""
    if [ "x${lsrc}" = "x" ]; then
        # turn on highlighting for debug
        #highobt="bgcolor=yellow"
	lsrc="http://arago-project.org"	    
    fi

    # if package is commercially licensed then refer to the maintainer
    if [[ $llic =~ (C|c)ommercial ]] || [[ $llic =~ (P|p)roprietary ]] ; then
	lsrc=$6
    fi

    echo "<tr>" \
	"<td rowspan=\"2\">${lpac}</td> " \
	"<td rowspan=\"2\">${lver}</td>" \
	"<td rowspan=\"2\" ${highlic}>${llic}</td> " \
	"<td rowspan=\"2\">$delivered_as</td>" \
	"<td rowspan=\"2\">$modified</td>" \
	"<td><b>Location</b></td>" \
	"<td>$lloc</td>" \
	"</tr>" \
	"<tr>" \
	"<td><b>Obtained from:</b></td>" \
	"<td ${highobt}><a href=$lsrc>$lsrc</a></td>" \
	"</tr>"

    : $((manifest_count++))

    return 0
}

# ..............................................................................
# $1 = title string
# $2 = path to installation area

generate_sw_manifest()
{
    echo "<h2><u>$1</u></h2>" \
	"<table border=1 cellspacing=1 cellpadding=1 width=80%>" \
	"<tr bgcolor=#c0c0c0  color=white>" \
	"<td><b>Software Name</b></td>" \
	"<td><b>Version</b></td>" \
	"<td><b>License Type</b></td>" \
	"<td><b>Delivered As</b></td>" \
	"<td><b>Modified by TI</b></td>" \
	"<td colspan=\"2\"></td>" \
	"</tr>"

    manifest_count=0
    for i in $2/usr/lib/opkg/info/*.control; do
	package=$(cat $i | grep Package:    | awk {'print $2'})
	version=$(cat $i | grep Version:    | awk {'print $2'} | cut -f1-2 -d-)
	license=$(cat $i | grep License:    | awk {'print $2,$3,$4'})
	source=$(cat $i  | grep Source:     | awk {'print $2'})
	maint=$(cat $i   | grep Maintainer: | sed 's/Maintainer:[[:space:]]*//')

	gen_row "${package}" "${version}" "${license}" "${source}" "$2" "${maint}"
    done

#    gen_row "FastCGiQt" "gb1061d89" "ISC" "git://gitorious.org/fastcgiqt/fastcgiqt.git" "targetfs/home/root/nSDK"
#    gen_row "Icons and Images" "beta" "Texas Instruments Commercial" "N/A" "targetfs/home/root/nSDK/skin-*/icons"
#    gen_row "Droid Fonts" "beta" "Apache License v2.0" "http://code.google.com/p/googlefontdirectory/source/browse/#hg/droid" "targetfs/home/root/nSDK/skin-*/fonts"

    echo "</table>"
    echo "Number of components = $manifest_count"
    echo "<br><br>"
}

# ..............................................................................
sw_manifest_footer()
{
    echo "
<hr>
<right> <i><font color=grey>Generated on `date`</i> </right></font>
</body>
</html>
"
}

# ..............................................................................


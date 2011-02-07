#! /bin/bash

usage()
{
    tool=$(basename $0)
    echo "Usage: ${tool} <old manifest> <new manifest>"
    exit 1
}

# note order of patterns is important to omit table tags
AWK=${AWK:-gawk}
AWKPROG='
/\/table/ {repl=0}
{if (repl) {print $0} }
$0 ~ pat {repl=1}
'

old=$1
new=$2
out=old_vs_new.htm
if [ -f ${out} ]; then
	rm ${out}
fi
touch ${out}

if [ "x${old}" == "x" ] || [ "x${new}" == "x" ]; then
    usage
fi

# Ensure headers match
# IFS is needed since populating array using strings with spaces
oldIFS=$IFS
IFS=$'\n'
headers_old=( $(grep -ie '^<h2>.*packages' ${old} | sed 's/.*<u>\(.*\):.*/\1/' | tee tmp.old) )
headers_new=( $(grep -ie '^<h2>.*packages' ${new} | sed 's/.*<u>\(.*\):.*/\1/' | tee tmp.new) )
cap=$(diff tmp.old tmp.new)
if [ $? -ne 0 ]; then
    echo "Table headers don't match. exiting."
    exit 1
fi

echo "<HTML>" >> ${out}
echo "<HEAD><TITLE>Manifest difference</TITLE></HEAD>" >> ${out}
echo "<BODY>" >> ${out}
echo "<h2><b><u>Legend</u></b></h2>" >> ${out}
echo "<table border=1>" >> ${out}
echo "<tr><td bgcolor=#808080>Dark Grey --> package removed</td></tr>" >> ${out}
echo "<tr><td bgcolor=#008000>Green --> package added</td></tr>" >> ${out}
echo "<tr><td bgcolor=#ADD8E6>Light Blue --> package info field changed</td></tr>" >> ${out}
echo "</table>" >> ${out}

# since headers match we can iterate over either array
#	| sed 's/<a href=//g' \
cnt=0
for h in ${headers_old[@]}; do

    echo '<h2><u>'"${h}"':</u></h2> <table border=1 cellspacing=1 cellpadding=1 width=80%> <tr bgcolor=#c0c0c0  color=white> <td><b>Software Name</b></td> <td><b>Version</b></td> <td><b>License Type</b></td> <td><b>Delivered As</b></td> <td><b>Modified by TI</b></td> <td colspan="2"></td> </tr>' >> ${out}

    # need to escape any parentheses for use as regexp in above awk program
    pat=$(echo $h | sed 's/(/\\\\\(/' | sed 's/)/\\\\\)/')
    $AWK -v pat="${pat}" "$AWKPROG" ${old} \
	| sed 's/<\/*tr>[[:space:]]*//g' \
	| sed 's/<\/td>[[:space:]]*<td/<\/td>,<td/g' \
	| perl -pi -e 's/<td.*?>(.*?)<\/td>/\1/g' \
	| perl -pi -e 's/<b>.*?,//g' \
	| perl -pi -e 's/<a href=.*?>//g' \
	| sed 's/<\/a>//g' \
	> tmp.old.${cnt}

    $AWK -v pat="${pat}" "$AWKPROG" ${new} \
	| sed 's/<\/*tr>[[:space:]]*//g' \
	| sed 's/<\/td>[[:space:]]*<td/<\/td>,<td/g' \
	| perl -pi -e 's/<td.*?>(.*?)<\/td>/\1/g' \
	| perl -pi -e 's/<b>.*?,//g' \
	| perl -pi -e 's/<a href=.*?>//g' \
	| sed 's/<\/a>//g' \
	> tmp.new.${cnt}

    ~/work/oe/arago/bin/diff_table.py tmp.old.${cnt} tmp.new.${cnt} >> ${out}
    echo "</table>" >> ${out}
    : $((cnt++))
done

echo "</BODY>" >> ${out}
echo "</HTML>" >> ${out}

# need to restore IFS after done using array -- unfortunate
IFS=$oldIFS

#rm tmp.old*
#rm tmp.new*


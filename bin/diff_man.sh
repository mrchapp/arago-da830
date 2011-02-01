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

# since headers match we can iterate over either array
#	| sed 's/<a href=//g' \
cnt=0
for h in ${headers_old[@]}; do
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
    : $((cnt++))
done

# need to restore IFS after done using array -- unfortunate
IFS=$oldIFS

exit

$AWK "$AWKPROG" ${new} > tmp.new
diff=$(diff tmp.old tmp.new)


#out=old_vs_new.htm
#rm ${out}
#touch ${out}
#echo "<HTML>" >> ${out}
#echo "<HEAD><TITLE>Manifest difference</TITLE></HEAD>" >> ${out}
#echo "<BODY>" >> ${out}
#echo '<h2><u>Packages (host):</u></h2> <table border=1 cellspacing=1 cellpadding=1 width=80%> <tr bgcolor=#c0c0c0  color=white> <td><b>Software Name</b></td> <td><b>Version</b></td> <td><b>License Type</b></td> <td><b>Delivered As</b></td> <td><b>Modified by TI</b></td> <td colspan="2"></td> </tr>' >> ${out}
#echo ${diff} >> ${out}
#echo "</TABLE>" >> ${out}
#echo "</BODY>" >> ${out}
#echo "</HTML>" >> ${out}

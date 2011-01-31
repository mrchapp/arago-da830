#! /bin/bash

usage()
{
    tool=$(basename $0)
    echo "Usage: ${tool} <old manifest> <new manifest>"
    exit 1
}

AWK=${AWK:-gawk}
AWKPROG='BEGIN { repl=0 }
	/Packages \(host\)/ {
		repl=1
	}
	{
		if (repl) {
			print $0
		}
	}
	/\/table/ {
		repl=0
	}

	'

old=$1
new=$2

if [ "x${old}" == "x" ] || [ "x${new}" == "x" ]; then
    usage
fi

# Ensure headers match
headers_old=$(grep -ie '^<h2>.*packages' ${old} | sed 's/.*<u>\(.*\):.*/\1/' | tee tmp.old)
headers_new=$(grep -ie '^<h2>.*packages' ${new} | sed 's/.*<u>\(.*\):.*/\1/' | tee tmp.new)
cap=$(diff tmp.old tmp.new)
if [ $? -ne 0 ]; then
    echo "Table headers don't match. exiting."
    exit 1
fi

$AWK "$AWKPROG" ${old} > tmp.old
$AWK "$AWKPROG" ${new} > tmp.new
diff=$(diff tmp.old tmp.new)

out=old_vs_new.htm
rm ${out}
touch ${out}
echo "<HTML>" >> ${out}
echo "<HEAD><TITLE>Manifest difference</TITLE></HEAD>" >> ${out}
echo "<BODY>" >> ${out}
echo '<h2><u>Packages (host):</u></h2> <table border=1 cellspacing=1 cellpadding=1 width=80%> <tr bgcolor=#c0c0c0  color=white> <td><b>Software Name</b></td> <td><b>Version</b></td> <td><b>License Type</b></td> <td><b>Delivered As</b></td> <td><b>Modified by TI</b></td> <td colspan="2"></td> </tr>' >> ${out}
echo ${diff} >> ${out}
echo "</TABLE>" >> ${out}
echo "</BODY>" >> ${out}
echo "</HTML>" >> ${out}

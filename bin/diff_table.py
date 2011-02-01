#! /usr/bin/python
#
# Diffs two csv files and generates html table
#

import sys
import csv

try:
    oldFile= open (sys.argv[1],'r')
except:
    print "Unable able to open first argument as old list."

try:
    newFile= open (sys.argv[2],'r')
except:
    print "Unable able to open second argument as new list."

oldDict = {}
for l in csv.reader (oldFile):
    oldDict[l[0]] = l

newDict = {}
for l in csv.reader (newFile):
    newDict[l[0]] = l        

# Packages in old manifest but not in new (i.e. removed)
for x in sorted(oldDict.keys()):
    if x not in newDict:
        y = oldDict[x]
        print "<tr>",
        print "<td bgcolor=#808080 rowspan=\"2\">%s</td>" % y[0],
        print "<td bgcolor=#808080 rowspan=\"2\">%s</td>" % y[1],
        print "<td bgcolor=#808080 rowspan=\"2\">%s</td>" % y[2],
        print "<td bgcolor=#808080 rowspan=\"2\">%s</td>" % y[3],
        print "<td bgcolor=#808080 rowspan=\"2\">%s</td>" % y[4],
        print "<td bgcolor=#808080> <b>Location</b></td><td bgcolor=#808080>%s</td></tr>" % y[5],
        print "<td bgcolor=#808080> <b>Obtained from:</b></td><td bgcolor=#808080>%s</td></tr>" % y[6],
        print "</tr>"

# Packages in new manifest but not in old (i.e. added)
for x in sorted(newDict.keys()):
    if x not in oldDict:
        y = newDict[x]
        print "<tr>",
        print "<td bgcolor=#008000 rowspan=\"2\">%s</td>" % y[0],
        print "<td bgcolor=#008000 rowspan=\"2\">%s</td>" % y[1],
        print "<td bgcolor=#008000 rowspan=\"2\">%s</td>" % y[2],
        print "<td bgcolor=#008000 rowspan=\"2\">%s</td>" % y[3],
        print "<td bgcolor=#008000 rowspan=\"2\">%s</td>" % y[4],
        print "<td bgcolor=#008000> <b>Location</b></td>      <td bgcolor=#008000>%s</td></tr>" % y[5],
        print "<td bgcolor=#008000> <b>Obtained from:</b></td><td bgcolor=#008000>%s</td></tr>" % y[6],
        print "</tr>"

# Packages in both (check for differences)
for x in sorted(oldDict.keys()):
    if x in newDict:
        y = oldDict[x]
        z = newDict[x]
        if y != z:
            print "<tr>",
            print "<td bgcolor=#%s rowspan=\"2\">%s</td>" % ("FFFFFF" if y[0] == z[0] else "ADD8E6", z[0]),
            print "<td bgcolor=#%s rowspan=\"2\">%s</td>" % ("FFFFFF" if y[1] == z[1] else "ADD8E6", z[1]),
            print "<td bgcolor=#%s rowspan=\"2\">%s</td>" % ("FFFFFF" if y[2] == z[2] else "ADD8E6", z[2]),
            print "<td bgcolor=#%s rowspan=\"2\">%s</td>" % ("FFFFFF" if y[3] == z[3] else "ADD8E6", z[3]),
            print "<td bgcolor=#%s rowspan=\"2\">%s</td>" % ("FFFFFF" if y[4] == z[4] else "ADD8E6", z[4]),
            print "<td> <b>Location</b></td>      <td bgcolor=#%s>%s</td></tr>" % ("FFFFFF" if y[5] == z[5] else "ADD8E6", z[5]),
            print "<td> <b>Obtained from:</b></td><td bgcolor=#%s>%s</td></tr>" % ("FFFFFF" if y[6] == z[6] else "ADD8E6", z[6]),
            print "</tr>"

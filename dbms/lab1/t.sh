#!/bin/sh

echo "@dbms/test.sql;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
echo "SET SERVEROUTPUT ON \n exec p1;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
echo "SET SERVEROUTPUT ON \n exec p2;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n exec p3;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n SELECT f1 FROM dual;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n SELECT f2 FROM dual;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n SELECT f3 FROM dual;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n SELECT f4 FROM dual;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#echo "SET SERVEROUTPUT ON \n SELECT f5 FROM dual;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
#

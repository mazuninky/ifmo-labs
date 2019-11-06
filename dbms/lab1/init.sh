#!/bin/sh

echo "@dbms/dropTable.sql;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
echo "@dbms/createTable.sql;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis
echo "@dbms/createProc.sql;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis



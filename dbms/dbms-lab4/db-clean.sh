#!/bin/bash

cp db/.bashrc .bashrc
source .bashrc

sqlplus -s /nolog > logs << SQL
 CONNECT SYS AS SYSDBA;
 shutdown immediate;
 exit;
SQL

rm -f $ORACLE_HOME/dbs/ora_control1 $ORACLE_HOME/dbs/ora_control2 $ORACLE_HOME/dbs/spfiles223412
rm -rf /u01/prd21/logs /u01/prd21/leftfood
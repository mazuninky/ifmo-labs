#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

sqlplus -s / as sysasm > logs << SQL
 shutdown immediate;
 exit;
SQL

crsctl stop resource ora.cssd -f
crsctl stat res ora.cssd -t

rm $ORACLE_HOME/dbs/initASM.223412.ora

# rm -f $ORACLE_HOME/dbs/pwd
# orapwd file=$ORACLE_HOME/dbs/pwd entries=10 password=12345

rm -fr /u01/cutechicken/
rm -fr /u01/angrycat/
rm -fr /u01/carelesscow/
rm -fr /u01/crazydog/

rm -fr /u01/unluckyturtle
rm -fr /u01/interestinghorse

rm -fr /u01/sql
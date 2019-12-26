#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

crsctl start resource ora.cssd
crsctl stat res ora.cssd -t

sqlplus -s / as sysasm > logs << SQL
 startup;
 exit;
SQL
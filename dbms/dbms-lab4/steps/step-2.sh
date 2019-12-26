#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

/usr/sbin/mkfile -n 100m carelesscow/carelesscow3
/usr/sbin/mkfile -n 100m carelesscow/carelesscow4
/usr/sbin/mkfile -n 100m carelesscow/carelesscow5

sqlplus -s / as sysasm > logs << SQL
 @/u01/sql/step2.sql;
 exit;
SQL
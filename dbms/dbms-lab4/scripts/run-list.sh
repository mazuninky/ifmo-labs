#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

sqlplus / as sysasm << SQL
 set pagesize 300
 column name format a20
 column path format a35
 select name, path, mount_status from v\$asm_disk order by path;
SQL
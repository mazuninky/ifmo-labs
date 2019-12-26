#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

sqlplus / as sysasm << SQL
 select name, allocation_unit_size from v$asm_diskgroup;
SQL
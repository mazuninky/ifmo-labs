#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

sqlplus -s / as sysasm > logs << SQL
 DROP DISKGROUP cutechicken INCLUDING CONTENTS;
 exit;
SQL

# Если группа используется, то юзать DROP DISKGROUP cutechicken FORCE INCLUDING CONTENTS;
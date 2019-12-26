#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

sqlplus -s / as sysasm > logs << SQL
 ALTER DISKGROUP interestinghorse DROP DISK interestinghorse_0000;
 exit;
SQL
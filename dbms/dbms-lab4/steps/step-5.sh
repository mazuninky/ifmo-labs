#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

/usr/sbin/mkfile -n 100m crazydog/crazydog3
/usr/sbin/mkfile -n 100m crazydog/crazydog4
/usr/sbin/mkfile -n 100m crazydog/crazydog5

sqlplus -s / as sysasm > logs << SQL
 DROP DISKGROUP crazydog;
 CREATE DISKGROUP crazydog HIGH REDUNDANCY
 FAILGROUP f1 DISK
    '/u01/crazydog/crazydog0',
    '/u01/crazydog/crazydog1'
 FAILGROUP f2 DISK
    '/u01/crazydog/crazydog2',
    '/u01/crazydog/crazydog3'
 FAILGROUP f3 DISK
    '/u01/crazydog/crazydog4',
    '/u01/crazydog/crazydog5';
 exit;
SQL
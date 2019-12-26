#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

/usr/sbin/mkfile -n 100m carelesscow/carelesscow3
/usr/sbin/mkfile -n 100m carelesscow/carelesscow4
/usr/sbin/mkfile -n 100m carelesscow/carelesscow5
/usr/sbin/mkfile -n 100m carelesscow/carelesscow6
/usr/sbin/mkfile -n 100m carelesscow/carelesscow7

sqlplus -s / as sysasm > logs << SQL
 DROP DISKGROUP carelesscow;
 CREATE DISKGROUP carelesscow NORMAL REDUNDANCY
 FAILGROUP f1 DISK
    '/u01/carelesscow/carelesscow0',
    '/u01/carelesscow/carelesscow1',
    '/u01/carelesscow/carelesscow2',
    '/u01/carelesscow/carelesscow3'
 FAILGROUP f2 DISK
    '/u01/carelesscow/carelesscow4',
    '/u01/carelesscow/carelesscow5',
    '/u01/carelesscow/carelesscow6',
    '/u01/carelesscow/carelesscow7';
 exit;
SQL
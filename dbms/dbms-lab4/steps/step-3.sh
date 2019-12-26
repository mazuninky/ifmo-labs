#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

# $1 - имя группы
# $2 - количество дисков в группе
create_disk() {
    for ((i=0;i<$2;i++)); 
    do 
        /usr/sbin/mkfile -n 100m $1/$1$i
    done
}

mkdir unluckyturtle
create_disk unluckyturtle 3

# asmcmd dsset '/u01/cutechicken/*','/u01/angrycat/*','/u01/carelesscow/*', '/u01/crazydog/*', '/u01/unluckyturtle/*'

sqlplus -s / as sysasm > logs << SQL
 CREATE DISKGROUP unluckyturtle EXTERNAL REDUNDANCY DISK
    '/u01/unluckyturtle/unluckyturtle0',
    '/u01/unluckyturtle/unluckyturtle1',
    '/u01/unluckyturtle/unluckyturtle2'
  ATTRIBUTE 'AU_SIZE'='2M';
 exit;
SQL
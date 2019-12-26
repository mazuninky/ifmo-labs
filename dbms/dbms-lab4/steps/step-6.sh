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

mkdir interestinghorse
create_disk interestinghorse 3
#asmcmd dsset '/u01/cutechicken/*','/u01/angrycat/*','/u01/carelesscow/*', '/u01/crazydog/*', '/u01/unluckyturtle/*', '/u01/interestinghorse/*'

sqlplus -s / as sysasm > logs << SQL
 CREATE DISKGROUP interestinghorse EXTERNAL REDUNDANCY DISK
    '/u01/interestinghorse/interestinghorse0',
    '/u01/interestinghorse/interestinghorse1',
    '/u01/interestinghorse/interestinghorse2',
  ATTRIBUTE 'AU_SIZE'='8M';
 exit;
SQL
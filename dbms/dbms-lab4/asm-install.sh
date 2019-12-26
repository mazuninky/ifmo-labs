#!/bin/bash

export ORACLE_HOME=/u01/app/11.2.0/grid
export ORACLE_SID=ASM.223412

export PATH=$PATH:$ORACLE_HOME/bin

crsctl start resource ora.cssd
crsctl stat res ora.cssd -t

cp asm/initASM.223412.ora $ORACLE_HOME/dbs/initASM.223412.ora

mkdir -p /u01/cutechicken/
mkdir -p /u01/angrycat/
mkdir -p /u01/carelesscow/
mkdir -p /u01/crazydog/

# $1 - имя группы
# $2 - количество дисков в группе
create_disk() {
    for ((i=0;i<$2;i++)); 
    do 
        /usr/sbin/mkfile -n 100m $1/$1$i
    done
}

create_disk cutechicken 4
create_disk angrycat 4
create_disk carelesscow 3
create_disk crazydog 3

mkdir -p /u01/sql
cp asm/initdiskgroup1.sql /u01/sql/initdiskgroup1.sql
cp asm/initdiskgroup2.sql /u01/sql/initdiskgroup2.sql
cp asm/initdiskgroup3.sql /u01/sql/initdiskgroup3.sql
cp asm/initdiskgroup4.sql /u01/sql/initdiskgroup4.sql
cp asm/step2.sql /u01/sql/step2.sql

sqlplus -s / as sysasm > logs << SQL
 startup;
 @/u01/sql/initdiskgroup1.sql;
 @/u01/sql/initdiskgroup2.sql;
 @/u01/sql/initdiskgroup3.sql;
 @/u01/sql/initdiskgroup4.sql;
 exit;
SQL
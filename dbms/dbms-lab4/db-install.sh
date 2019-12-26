#!/bin/bash

cp db/.bashrc .bashrc
source .bashrc

cp db/inits223412.ora $ORACLE_HOME/dbs/inits223412.ora

mkdir -p /u01/prd21
chown oracle:oinstall /u01/prd21
mkdir -p /u01/prd21/logs
mkdir -p /u01/prd21/leftfood
mkdir -p /u01/prd21/leftfood/node01
mkdir -p /u01/prd21/leftfood/node02
mkdir -p /u01/prd21/leftfood/node03
mkdir -p /u01/prd21/leftfood/node04

mkdir -p /u01/prd21/sql
cp db/initdb.sql /u01/prd21/sql/initdb.sql
cp db/initmem.sql /u01/prd21/sql/initmem.sql
cp db/test-asm.sql /u01/prd21/sql/test-asm.sql

mkdir -p /u01/app/oracle/flash_recovery_area

sqlplus -s /nolog << SQL
 CONNECT SYS AS SYSDBA;
 CREATE SPFILE FROM PFILE;
 STARTUP NOMOUNT;
 @/u01/prd21/sql/initdb.sql;
 @/u01/prd21/sql/initmem.sql;
 CREATE USER ops$oracle IDENTIFIED EXTERNALLY;
 GRANT CREATE SESSION TO ops$oracle;
 @?/rdbms/admin/catalog.sql;
 @?/rdbms/admin/catproc.sql;
 @?/sqlplus/admin/pupbld.sql;
 exit;
SQL
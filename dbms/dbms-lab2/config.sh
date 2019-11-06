export ORACLE_BASE=/u01/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/11.2.0/dbhome_1
export ORACLE_SID=s223412

PATH=$PATH:$ORACLE_HOME/bin
export NLS_LANG=American_America.UTF8
export NLS_SORT=AMERICAN
export NLS_DATE_LANGUAGE=AMERICAN

export ORADATA=/u01/prd21/leftfood

cd $ORACLE_HOME/dbs
vi inits223412.ora

mkdir /u01/prd21
chown oracle:oinstall /u01/prd21
mkdir /u01/prd21/logs
mkdir /u01/prd21/leftfood
mkdir /u01/prd21/leftfood/node01
mkdir /u01/prd21/leftfood/node02
mkdir /u01/prd21/leftfood/node03
mkdir /u01/prd21/leftfood/node04

cd /u01/prd21/sql
vi initdb.sql
vi initmem.sql

sqlplus /nolog
CONNECT SYS AS SYSDBA
CREATE SPFILE FROM PFILE;
STARTUP NOMOUNT;
@/u01/prd21/sql/initdb.sql;
@/u01/prd21/sql/initmem.sql;

CREATE USER ops$oracle IDENTIFIED EXTERNALLY;
GRANT CREATE SESSION TO ops$oracle;

@?/rdbms/admin/catalog.sql
@?/rdbms/admin/catproc.sql
@?/sqlplus/admin/pupbld.sql
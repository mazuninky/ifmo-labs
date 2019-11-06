export ORACLE_BASE=/u01/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/11.2.0/dbhome_1

cd $ORACLE_HOME/dbs
rm ora_control1 ora_control2 spfiles223412
rm -rf /u01/prd21/logs /u01/prd21/leftfood
#!/bin/bash

sqlplus -s / as sysdba > logs << SQL
 @/u01/prd21/sql/test-asm.sql;
SQL
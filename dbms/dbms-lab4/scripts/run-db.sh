#!/bin/bash

sqlplus -s /nolog > logs << SQL
 CONNECT SYS AS SYSDBA;
 STARTUP NOMOUNT;
 exit;
SQL
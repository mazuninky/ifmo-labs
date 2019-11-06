#!/bin/sh

echo "SET SERVEROUTPUT ON \n SELECT * FROM TRANSACTIONEXAMPLE;" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis


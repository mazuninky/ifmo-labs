#!/bin/sh

echo 'Введите название схемы: '

read schema

echo "SET SERVEROUTPUT ON \n exec RemoveNullConstraints('$schema');" | sqlplus -s s223412/2jRGTJtR@localhost:1521/orbis

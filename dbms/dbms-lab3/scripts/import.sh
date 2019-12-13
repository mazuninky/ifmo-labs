#!/bin/bash

if [ -z "$1" ]
  then
    echo "No date supplied"
    exit 1
fi

scp oracle@db128:/u01/backup/exp/$1_full.dmp /u01/backup/exp/$1_full.dmp

imp userid=lab3/password FULL=Y file=/u01/backup/exp/$1_full.dmp log=/u01/backup/exp/i$1_full.log

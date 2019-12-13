#!/bin/bash

export TIME=$(date +"%y.%m.%d:%H")
rm /u01/backup/exp/*
exp USERID=lab3/password file=/u01/backup/exp/${TIME}_full.dmp log=/u01/backup/exp/e${TIME}_full.log statistics=none
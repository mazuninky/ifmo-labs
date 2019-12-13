#!/bin/sh

cd $DATA_PUMP_DIR
rm -r $DATA_PUMP_DIR/*
expdp lab3/password FULL=Y directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp logfile=ExportFull.log
scp -r . oracle@db128:/u01/backup/pump
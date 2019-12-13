#!/bin/sh

cd $DATA_PUMP_DIR
scp -r oracle@db128:/u01/backup/pump /u01/backup/
impdp lab3/password directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp
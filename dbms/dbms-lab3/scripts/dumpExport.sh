#!/bin/sh

cd $DATA_PUMP_DIR
rm *.dmp *.log
expdp lab3/password FULL=Y directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp logfile=ExportFull.log
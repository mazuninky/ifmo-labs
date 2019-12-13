expdp lab3/password FULL=Y directory=Pump_Export_Directory dumpfile=DumpFull.dmp logfile=ExportFull.log

DATE=$"date+%Y%m%d­%H.%M"
expdp lab3/password TABLES=lab3.SECOND_HAND directory=Pump_Export_Directory  dumpfile=DumpFull.dmp logfile=ExportFull.log

impdp lab3/password TABLES=lab3.SECOND_HAND directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp TABLE_EXISTS_ACTION=REPLACE
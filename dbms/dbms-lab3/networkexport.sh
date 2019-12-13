expdp \"/ as sysdba\" FULL=Y directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp logfile=ExportFull.log network_link=base
expdp \"/ as sysdba\" FULL=Y directory=DATA_PUMP_DIR dumpfile=DumpFull.dmp logfile=ExportFull.log network_link=base

impdp \"/ as sysdba\" TABLES=lab3.TEST_DATA directory=DATA_PUMP_DIR network_link=base
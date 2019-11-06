SELECT * FROM dba_data_files where TABLESPACE_NAME = 'GOOD_GREEN_FOOD';

select value from nls_database_parameters where parameter='NLS_CHARACTERSET';

SHOW SGA;

SELECT sum(value)/1024/1024 "TOTAL SGA (MB)" FROM v$sga;

SET LINESIZE linesize
SET WRAP OFF
SET PAGESIZE 0

ALTER SYSTEM SET SGA_TARGET=0 SCOPE=SPFILE;

select bytes from v$sgainfo where name = 'Granule Size';

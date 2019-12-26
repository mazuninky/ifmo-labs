CREATE DISKGROUP crazydog EXTERNAL REDUNDANCY DISK
    '/u01/crazydog/crazydog0',
    '/u01/crazydog/crazydog1',
    '/u01/crazydog/crazydog2'
ATTRIBUTE 'COMPATIBLE.ASM'='11.2.0.0.0';
CREATE DISKGROUP angrycat EXTERNAL REDUNDANCY DISK
    '/u01/angrycat/angrycat0',
    '/u01/angrycat/angrycat1',
    '/u01/angrycat/angrycat2',
    '/u01/angrycat/angrycat3'
ATTRIBUTE 'COMPATIBLE.ASM'='11.2.0.0.0';
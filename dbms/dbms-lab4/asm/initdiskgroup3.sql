CREATE DISKGROUP carelesscow EXTERNAL REDUNDANCY DISK
    '/u01/carelesscow/carelesscow0',
    '/u01/carelesscow/carelesscow1',
    '/u01/carelesscow/carelesscow2'
ATTRIBUTE 'COMPATIBLE.ASM'='11.2.0.0.0';
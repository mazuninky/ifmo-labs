CREATE DISKGROUP cutechicken EXTERNAL REDUNDANCY DISK
    '/u01/cutechicken/cutechicken0',
    '/u01/cutechicken/cutechicken1',
    '/u01/cutechicken/cutechicken2',
    '/u01/cutechicken/cutechicken3'
ATTRIBUTE 'COMPATIBLE.ASM'='11.2.0.0.0';
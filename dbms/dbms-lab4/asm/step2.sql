DROP DISKGROUP carelesscow;
CREATE DISKGROUP carelesscow HIGH REDUNDANCY
FAILGROUP controller1 DISK '/u01/carelesscow/carelesscow0', '/u01/carelesscow/carelesscow1'
FAILGROUP controller2 DISK '/u01/carelesscow/carelesscow2', '/u01/carelesscow/carelesscow3'
FAILGROUP controller3 DISK '/u01/carelesscow/carelesscow4', '/u01/carelesscow/carelesscow5';
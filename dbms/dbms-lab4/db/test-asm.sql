create tablespace test datafile
'+CUTECHICKEN'
size 10m autoextend on next 100m
extent management local
segment space management auto;

alter tablespace test add datafile
'+CUTECHICKEN' size 10M 
autoextend on next 100M;
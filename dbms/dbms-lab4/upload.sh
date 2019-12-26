#!/bin/sh

zip db.zip scripts/* steps/* db/* db/.bashrc db-install.sh db-clean.sh asm/* asm-install.sh asm-clean.sh

scp -P 2222 db.zip s223412@se.ifmo.ru:/home/s223412/db.zip
ssh -p 2222 s223412@se.ifmo.ru << EOF
 scp /home/s223412/db.zip oracle@db102:/u01
 ssh oracle@db102 unzip -o db.zip
EOF
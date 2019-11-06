CREATE TABLESPACE LAST_YELLOW_ROLE
    DATAFILE '/u01/prd21/leftfood/node01/lastyellowrole01.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node04/lastyellowrole02.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node02/lastyellowrole03.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node03/lastyellowrole04.dbf' SIZE 10M;

CREATE TABLESPACE GOOD_GREEN_FOOD
    DATAFILE '/u01/prd21/leftfood/node01/goodgreenfood01.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node02/goodgreenfood02.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node04/goodgreenfood03.dbf' SIZE 10M,
    '/u01/prd21/leftfood/node03/goodgreenfood04.dbf' SIZE 10M;
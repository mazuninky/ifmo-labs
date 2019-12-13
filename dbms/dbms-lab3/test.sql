CREATE USER lab3 IDENTIFIED BY password DEFAULT TABLESPACE LAST_YELLOW_ROLE QUOTA 10M ON USERS;
GRANT ALL PRIVILEGES TO lab3;
GRANT CREATE SESSION TO lab3;
GRANT DATAPUMP_EXP_FULL_DATABASE to lab3;
GRANT DATAPUMP_IMP_FULL_DATABASE to lab3;

CONNECT lab3/password;

CREATE TABLE test_data (value varchar2(100) NOT NULL) TABLESPACE LAST_YELLOW_ROLE STORAGE (INITIAL 50K);
INSERT INTO test_data VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit');
INSERT INTO test_data VALUES ('Etiam tincidunt tempus lacinia.');
INSERT INTO test_data VALUES ('Pellentesque aliquam tortor ultricies, sollicitudin nunc in, porta augue');
INSERT INTO test_data VALUES ('Proin et luctus lacus, nec cursus orci. Pellentesque cursus porttitor condimentum');

CREATE TABLE second_hand (value varchar2(100) NOT NULL) TABLESPACE LAST_YELLOW_ROLE STORAGE (INITIAL 50K);
INSERT INTO second_hand VALUES ('Ut hendrerit elementum dolor ac semper.');
INSERT INTO second_hand VALUES ('Donec semper in ex nec maximus.'); 
INSERT INTO second_hand VALUES ('Vivamus dictum nisl et sem euismod, euismod feugiat tellus faucibus.');
INSERT INTO second_hand VALUES ('Duis porttitor eleifend mauris vitae pulvinar.');
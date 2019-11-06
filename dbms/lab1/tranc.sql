CREATE TABLE TestTranTable
(

    ID NUMBER not null primary key,
    X  NUMBER not null
);

CREATE OR REPLACE PROCEDURE TranProc(someArg IN varchar2)
BEGIN
    INSERT INTO TestTranTable(X) VALUES (1) SAVEPOINT A;

END;

CREATE OR REPLACE PROCEDURE TranFunc(someArg IN varchar2)
    RETURN integer
BEGIN

END;

-- Процедура с dml
CREATE OR REPLACE procedure p1
    is
begin
    insert into TRANSACTIONEXAMPLE(fromPoint, value) VALUES (10, 1);
end;

-- функция с dml
create or replace function f1
    return int as
    inserted_id int;
begin
    insert into TRANSACTIONEXAMPLE(fromPoint, value) VALUES (20, 1);
    return inserted_id;
end;

-- функция с dsql
create or replace function f2
    return int as
    inserted_id int;
begin
    EXECUTE IMMEDIATE 'insert into TRANSACTIONEXAMPLE(fromPoint, value)
                       VALUES (21, 1)';
    return inserted_id;
end;

-- функция с dml в транзакции
create or replace function f3
    return int as
    inserted_id int;
    pragma autonomous_transaction;
begin
    savepoint first_point;
    insert into TRANSACTIONEXAMPLE(fromPoint, value) VALUES (22, 1);
    commit;
    return inserted_id;
end;

-- функция с dml в транзакции c откатом
create or replace function f4
    return int as
    inserted_id int;
    pragma autonomous_transaction;
begin
    savepoint first_point;
    insert into TRANSACTIONEXAMPLE(fromPoint, value) VALUES (23, 1);
    rollback to first_point;
    commit;
    return inserted_id;
end;

-- функция с dsql в транзакции
create or replace function f5
    return int as
    inserted_id int;
    pragma autonomous_transaction;
begin
    savepoint first_point;
    EXECUTE IMMEDIATE 'insert into TRANSACTIONEXAMPLE(fromPoint, value)
                       VALUES (24, 1)';
    commit;
    return inserted_id;
end;

-- Процедура с dsql
CREATE OR REPLACE procedure p2
    is
begin
    EXECUTE IMMEDIATE 'insert into TRANSACTIONEXAMPLE(fromPoint, value)
                       VALUES (11, 1)';
end;

-- Процедура с dsql с откатом
CREATE OR REPLACE procedure p3
    is
begin
    savepoint first_point;
    EXECUTE IMMEDIATE 'insert into TRANSACTIONEXAMPLE(fromPoint, value)
                       VALUES (12, 1)';
    rollback to first_point;
end;

select f3 from dual;


CREATE OR REPLACE procedure p4
    is
begin
   CREATE
end;

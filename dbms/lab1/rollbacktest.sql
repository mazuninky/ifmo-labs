CREATE TABLE ROLLBACKEXAMLPE
(
    code  numeric not null,
    code_value numeric not null
);

CREATE SEQUENCE rollsequnce START WITH 1
    INCREMENT BY 1;

create or replace function f1
    return int as
    inserted_id int;
    pragma autonomous_transaction;
begin
    savepoint lol;
    EXECUTE IMMEDIATE 'insert into ROLLBACKEXAMLPE(code, code_value)
                       VALUES (1, rollsequnce.NEXTVAL)';
    commit;
    return inserted_id;
end;

create or replace function f2
    return int as
    inserted_id int;
    pragma autonomous_transaction;
begin
    savepoint lol;
    EXECUTE IMMEDIATE 'insert into ROLLBACKEXAMLPE(code, code_value)
                       VALUES (2, rollsequnce.NEXTVAL)';
    rollback to lol;
    commit;
    return inserted_id;
end;

select f1
from dual;
select f1
from dual;
select f2
from dual;
select f1
from dual;

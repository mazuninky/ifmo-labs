CREATE OR REPLACE PROCEDURE RemoveNullConstraints(schema IN varchar2)
    IS
    cursor c1 is
        SELECT tab.TABLE_NAME, cols.CONSTRAINT_NAME
        FROM ALL_TAB_COLUMNS tab
                 join all_cons_columns cols on tab.COLUMN_NAME = cols.COLUMN_NAME and tab.TABLE_NAME = cols.TABLE_NAME
        where tab.COLUMN_NAME not in (SELECT cols.COLUMN_NAME
                                      FROM all_constraints cons,
                                           all_cons_columns cols
                                      WHERE cons.constraint_type = 'P'
                                        AND cons.constraint_name = cols.constraint_name
                                        AND cons.owner = cols.owner
                                        and cons.OWNER = schema)
          and tab.OWNER = schema
          and NULLABLE = 'N';
    counter number;
    schema_not_found EXCEPTION;
    cnt NUMBER;
    privileges_ex exception;
    pragma exception_init( privileges_ex, -1031 );
BEGIN
    counter := 0;
    SELECT COUNT(*) INTO cnt FROM sys.ALL_USERS WHERE USERNAME = schema;

    if cnt = 0 THEN
        raise schema_not_found;
    end if;

    FOR tableRow IN c1
        LOOP
            EXECUTE IMMEDIATE 'ALTER TABLE ' || schema || '.' || tableRow.TABLE_NAME || ' DROP CONSTRAINT "' ||
                              tableRow.CONSTRAINT_NAME || '"';
            counter := counter + 1;
        END LOOP;

    DBMS_OUTPUT.PUT_LINE('Схема: ' || schema);
    DBMS_OUTPUT.PUT_LINE('Ограничений целостности типа NOT NULL отключено: ' || counter);
EXCEPTION
    WHEN schema_not_found THEN
        DBMS_OUTPUT.PUT_LINE('Нет такой схемы: ' || schema);
    WHEN privileges_ex THEN
        DBMS_OUTPUT.PUT_LINE('Недостаточно прав для доступа к схема ' || schema);
END;

CREATE OR REPLACE PROCEDURE RemoveNullConstraints2(schema IN varchar2)
    IS
    cursor c1 is
        SELECT DISTINCT OBJECT_NAME
        FROM ALL_OBJECTS
        WHERE OBJECT_TYPE = 'TABLE'
          AND OWNER = schema;
    counter number;
BEGIN
    counter := 0;
    FOR tableRow IN c1
        LOOP
            DECLARE
                cursor c2 is
                    SELECT SEARCH_CONDITION, column_name, CONSTRAINT_NAME
                    from user_constraints
                             natural join user_cons_columns
                    where table_name = tableRow.OBJECT_NAME;
            BEGIN
                FOR contraintRow in c2
                    LOOP
                        IF SUBSTR(contraintRow.SEARCH_CONDITION, 1, 32760) like '% IS NOT NULL' THEN
                            EXECUTE IMMEDIATE 'ALTER TABLE ' || tableRow.OBJECT_NAME || ' DROP CONSTRAINT "' ||
                                              contraintRow.CONSTRAINT_NAME || '"';
                            counter := counter + 1;
                        END IF;
                    end loop;
            END;
        END LOOP;
    DBMS_OUTPUT.PUT_LINE('Схема: ' || schema);
    DBMS_OUTPUT.PUT_LINE('Ограничений целостности типа NOT NULL отключено: ' || counter);
END;


create table RESULTS
(
    ID NUMBER not null primary key,
    X NUMBER not null,
    Y NUMBER not null,
    R NUMBER not null,
    INCLUDED NUMBER(1)
);

create table TEST
(
    NOTNULLPRIMARYKEY NUMBER not null
        primary key,
    NOTNULL VARCHAR2(5) not null,
    NOTNULLWITHCHECK NUMBER not null,
    FIELDISNULL NUMBER
);


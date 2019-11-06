SELECT COLUMN_NAME, TABLE_NAME
FROM ALL_TAB_COLUMNS
where COLUMN_NAME not in (SELECT cols.COLUMN_NAME
                          FROM all_constraints cons,
                               all_cons_columns cols
                          WHERE cons.constraint_type = 'P'
                            AND cons.constraint_name = cols.constraint_name
                            AND cons.owner = cols.owner and cons.OWNER = 'S223412')
  and OWNER = 'S223412' and NULLABLE='N';

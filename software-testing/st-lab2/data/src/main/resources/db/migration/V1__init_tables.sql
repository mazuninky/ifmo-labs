CREATE TABLE task
(
    id          INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        varchar NOT NULL,
    description text,
    due_date    timestamp
);

CREATE TABLE tag
(
    id   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text varchar not null unique
);

CREATE TABLE task_tag
(
    task_id INT REFERENCES task (id) ON DELETE CASCADE,
    tag_id  INT REFERENCES tag (id) ON DELETE CASCADE,
    CONSTRAINT task_tag_pkey PRIMARY KEY (task_id, tag_id)
);

CREATE TABLE state
(
    id   INT PRIMARY KEY not null,
    name varchar         not null
);

insert into state
values (1, 'Не начат'),
       (2, 'В процессе'),
       (3, 'Завершён');

CREATE TABLE task_state
(
    task_id  INT REFERENCES task (id) ON DELETE CASCADE,
    state_id INT REFERENCES state (id) ON DELETE CASCADE,
    CONSTRAINT task_state_pkey PRIMARY KEY (task_id, state_id)
);

CREATE VIEW task_view AS
SELECT task.id,
       task.name,
       task.description,
       task.due_date,
       array_agg(tag.text),
       (select state.id
        from task_state
                 inner join state on task_state.state_id = state.id
        where task_state.task_id = task.id) state_id
FROM task
         left outer join task_tag on task.id = task_tag.task_id
         left outer join tag on task_tag.tag_id = tag.id
GROUP BY (task.id,
          task.name,
          task.description,
          task.due_date);

drop table if exists productVine

create table if not exists productVine(
    id BIGINT PRIMARY KEY,
    name VARCHAR(150),
    price DECIMAL(10,2)
);
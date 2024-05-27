-- liquibase formatted sql

-- changeset Noman5237:1689223150-1
create table test.greeting
(
    id       varchar(255) not null,
    language varchar(255) not null unique,
    message  varchar(255) not null,
    constraint pk_greeting primary key (id)
);

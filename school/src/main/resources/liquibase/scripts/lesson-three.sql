--liquibase formatted sql

--changeset andreev:1

CREATE TABLE students (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
age INT CHECK (age >= 16)
);

--changeset andreev:2

CREATE INDEX students_name_index ON students(name);

--changeset andreev:3

CREATE TABLE facultys (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
colour TEXT NOT NULL
);

--changeset andreev:4

CREATE INDEX facultys_name_and_colour_index ON facultys(name, colour);


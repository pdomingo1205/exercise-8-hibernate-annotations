CREATE USER postgres WITH PASSWORD 'postgres';
DROP TABLE IF EXIST hibernate;
CREATE DATABASE hibernate OWNER postgres;

USE postgres;

CREATE TABLE contact_info (
    contact_info_id bigint NOT NULL SERIAL PRIMARY KEY,
    person_id bigint,
    contact_info character varying(255),
    contact_type character varying(255)
);


CREATE TABLE person (
    person_id bigint NOT NULL SERIAL PRIMARY KEY,
    title character varying(255),
    first_name character varying(255),
    middle_name character varying(255),
    last_name character varying(255),
    suffix character varying(255),
    street_no character varying(255),
    barangay character varying(255),
    municipality character varying(255),
    zip_code character varying(255),
    birth_day date,
    gwa double precision,
    date_hired date,
    curr_employed boolean
);

CREATE TABLE role(
	role_id SERIAL PRIMARY KEY,
	role VARCHAR(25) NOT NULL
);

CREATE TABLE person_roles(
	person_id INT REFERENCES person(person_id),
	role_id INT REFERENCES role(role_id),
	PRIMARY KEY(person_id,role_id)
);


INSERT INTO role(role) VALUES ('DEV');
INSERT INTO role(role) VALUES ('UI');
INSERT INTO role(role) VALUES ('QA');
INSERT INTO role(role) VALUES ('SA');
INSERT INTO role(role) VALUES ('WEB');

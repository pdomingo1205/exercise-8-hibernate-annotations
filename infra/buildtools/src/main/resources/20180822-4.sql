CREATE TABLE contact_info (
    contact_info_id bigint NOT NULL,
    person_id bigint,
    contact_info character varying(255),
    contact_type character varying(255)
);


CREATE TABLE person (
    person_id bigint NOT NULL,
    title character varying(255),
    first_name character varying(255),
    middle_name character varying(255),
    last_name character varying(255),
    suffix character varying(255),
    street_no integer,
    barangay character varying(255),
    municipality character varying(255),
    zip_code character varying(255),
    birth_day date,
    gwa double precision,
    date_hired date,
    curr_employed boolean
);

CREATE TABLE role (
    role_id bigint NOT NULL,
    role character varying(255),
);

CREATE TABLE person_roles (
    person_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE ONLY public.contact_info
    ADD CONSTRAINT contact_info_pkey PRIMARY KEY (contact_info_id);

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT person_roles_pkey PRIMARY KEY (person_id, role_id);

ALTER TABLE ONLY public.person_roles
ADD CONSTRAINT fk_4633eyumhu04oxdhh37eacuhy FOREIGN KEY (role_id) REFERENCES public.role(role_id);

ALTER TABLE ONLY public.person_roles
ADD CONSTRAINT fk_7xe1l042qjf6k341eb2mpnft7 FOREIGN KEY (person_id) REFERENCES public.person(person_id);

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);

ALTER TABLE ONLY public.contact_info
ADD CONSTRAINT fk_q77uyvqapotysf04dtjhy6np8 FOREIGN KEY (person_id) REFERENCES public.person(person_id);

COPY contact_info (contact_info_id, person_id, contact_info, contact_type) FROM stdin;
1	1	JohnDoe.com	Email Address
2	2	09226265536	Phone
3	3	aaaa@mega.com	Email Address
\.

COPY person (person_id, title, first_name, middle_name, last_name, suffix, street_no, barangay, municipality, zip_code, birth_day, gwa, date_hired, curr_employed) FROM stdin;
1	Mc	Donalds		Time	zz	2	a	b	3	2018-01-01	1.0	1998-05-10	t
2	Mega Star	Sharon	Ewan	Cuneta	Ate Shawie	3	555	Carne	Norte	1984-01-01	1	2000-11-12	t
3	Terminator	John	Connor	Connor	30	1	address	california	3	1998-08-08	1	1200-12-12 	f
\.

COPY public.role (role_id, role) FROM stdin;
1   Manger
2   Mechanic


COPY public.person_roles (person_id, role_id) FROM stdin;
1	1
1	2
2	1
2	2
3	1
3	2
\.

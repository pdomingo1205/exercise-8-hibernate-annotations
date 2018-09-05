TRUNCATE TABLE person,contact_info,role,person_roles

ALTER SEQUENCE contact_info_contact_info_id_seq RESTART;
ALTER SEQUENCE person_person_id_seq RESTART;
ALTER SEQUENCE role_role_id_seq RESTART;

COPY contact_info (contact_info_id, person_id, contact_info, contact_type) FROM stdin;
1	1	09090909090 Phone
\.

COPY person (person_id, title, first_name, middle_name, last_name, suffix, street_no, barangay, municipality, zip_code, birth_day, gwa, date_hired, curr_employed) FROM stdin;
1	John	Dough		Time	nothere	1	a	b	3	1999-05-05	1.0	2005-05-05	t
\.

COPY public.role (role_id, role) FROM stdin;
1   Missing

COPY public.person_roles (person_id, role_id) FROM stdin;
1	1
\.

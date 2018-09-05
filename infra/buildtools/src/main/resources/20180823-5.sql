TRUNCATE TABLE person,contact_info,role,person_roles

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

ALTER SEQUENCE contact_info_contact_info_id_seq RESTART;
ALTER SEQUENCE person_person_id_seq RESTART;
ALTER SEQUENCE role_role_id_seq RESTART;


TRUNCATE TABLE person,contact_info,role,person_roles

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

TRUNCATE TABLE person,contact_info,role,person_roles

ALTER SEQUENCE contact_info_contact_info_id_seq RESTART;
ALTER SEQUENCE person_person_id_seq RESTART;
ALTER SEQUENCE role_role_id_seq RESTART;

COPY contact_info (contact_info_id, person_id, contact_info, contact_type) FROM stdin;
1	1	09090909090 Phone
2	1	09226970552	Phone
\.

COPY person (person_id, title, first_name, middle_name, last_name, suffix, street_no, barangay, municipality, zip_code, birth_day, gwa, date_hired, curr_employed) FROM stdin;
1	John	Dough		Time	nothere	1	a	b	3	1999-05-05	1.0	2005-05-05	t
\.

COPY public.role (role_id, role) FROM stdin;
1   Missing
2   Megaman

COPY public.person_roles (person_id, role_id) FROM stdin;
1	1
\.

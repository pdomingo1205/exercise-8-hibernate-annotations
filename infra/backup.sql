
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: contact_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact_info (
    contact_info_id bigint NOT NULL,
    person_id bigint,
    contact_info character varying(255),
    contact_type character varying(255)
);


ALTER TABLE public.contact_info OWNER TO postgres;

--
-- Name: contact_info_contact_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contact_info_contact_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_info_contact_info_id_seq OWNER TO postgres;

--
-- Name: contact_info_contact_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contact_info_contact_info_id_seq OWNED BY public.contact_info.contact_info_id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
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


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_person_id_seq OWNER TO postgres;

--
-- Name: person_person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_person_id_seq OWNED BY public.person.person_id;


--
-- Name: person_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_roles (
    person_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.person_roles OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    role character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_role_id_seq OWNER TO postgres;

--
-- Name: role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;


--
-- Name: contact_info contact_info_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_info ALTER COLUMN contact_info_id SET DEFAULT nextval('public.contact_info_contact_info_id_seq'::regclass);


--
-- Name: person person_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN person_id SET DEFAULT nextval('public.person_person_id_seq'::regclass);


--
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);


--
-- Data for Name: contact_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contact_info (contact_info_id, person_id, contact_info, contact_type) FROM stdin;
4	3	a@a.com	Email Address
5	4	09226265536	Phone
2	1	mega@mega.com	Email Address
\.


--
-- Name: contact_info_contact_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contact_info_contact_info_id_seq', 7, true);


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (person_id, title, first_name, middle_name, last_name, suffix, street_no, barangay, municipality, zip_code, birth_day, gwa, date_hired, curr_employed) FROM stdin;
3	Mc	Hammer		Time	zz	2	a	b	3	0012-12-12	2.5	0101-10-10	t
4	Mega Star	Sharon	Ewan	Cuneta	Ate Shawie	3	555	Carne	Norte	0014-01-13	1	0014-01-13	t
1	Migu	Miguel	Miguelito	Miguelson	30	1	f	g	h	1205-12-05	1	0001-12-26 BC	t
\.


--
-- Name: person_person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_person_id_seq', 5, true);


--
-- Data for Name: person_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_roles (person_id, role_id) FROM stdin;
1	13
1	5
4	16
3	18
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (role_id, role) FROM stdin;
12	Manager
14	5
15	Johhny
19	Idonknow
11	Mechanic
17	Sins
18	Megalomaniac
16	ME GA
9	MamaMia
20	Johns Apprentice
5	Quacker
13	Megaman
\.


--
-- Name: role_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_role_id_seq', 20, true);


--
-- Name: contact_info contact_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_info
    ADD CONSTRAINT contact_info_pkey PRIMARY KEY (contact_info_id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);


--
-- Name: person_roles person_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT person_roles_pkey PRIMARY KEY (person_id, role_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: person_roles fk_4633eyumhu04oxdhh37eacuhy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT fk_4633eyumhu04oxdhh37eacuhy FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- Name: person_roles fk_7xe1l042qjf6k341eb2mpnft7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT fk_7xe1l042qjf6k341eb2mpnft7 FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- Name: contact_info fk_q77uyvqapotysf04dtjhy6np8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_info
    ADD CONSTRAINT fk_q77uyvqapotysf04dtjhy6np8 FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- PostgreSQL database dump complete
--


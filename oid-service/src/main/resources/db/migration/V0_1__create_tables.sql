--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: oph
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO oph;

--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: oph
--

SELECT pg_catalog.setval('hibernate_sequence', 10000, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: nodeclass; Type: TABLE; Schema: public; Owner: oph; Tablespace: 
--

-- DROP TABLE IF EXISTS nodeclass;

CREATE TABLE nodeclass (
    id bigint NOT NULL,
    version bigint NOT NULL,
    classcode character varying(255),
    description character varying(255),
    nodevalue character varying(255) NOT NULL
);


ALTER TABLE public.nodeclass OWNER TO oph;

--
-- Name: oid; Type: TABLE; Schema: public; Owner: oph; Tablespace: 
--

-- DROP TABLE IF EXISTS oid;

CREATE TABLE oid (
    oid_value bigint NOT NULL,
    checkdigit integer NOT NULL,
    node character varying(255) NOT NULL
);


ALTER TABLE public.oid OWNER TO oph;

--
-- Name: oid_base_data; Type: TABLE; Schema: public; Owner: oph; Tablespace: 
--

-- DROP TABLE IF EXISTS oid_base_data;

CREATE TABLE oid_base_data (
    id bigint NOT NULL,
    version bigint NOT NULL,
    key character varying(255),
    value character varying(255)
);


ALTER TABLE public.oid_base_data OWNER TO oph;


--
-- Name: nodeclass_nodevalue_key; Type: CONSTRAINT; Schema: public; Owner: oph; Tablespace: 
--

ALTER TABLE ONLY nodeclass
    ADD CONSTRAINT nodeclass_nodevalue_key UNIQUE (nodevalue);


--
-- Name: nodeclass_pkey; Type: CONSTRAINT; Schema: public; Owner: oph; Tablespace: 
--

ALTER TABLE ONLY nodeclass
    ADD CONSTRAINT nodeclass_pkey PRIMARY KEY (id);


--
-- Name: oid_base_data_key_key; Type: CONSTRAINT; Schema: public; Owner: oph; Tablespace: 
--

ALTER TABLE ONLY oid_base_data
    ADD CONSTRAINT oid_base_data_key_key UNIQUE (key);


--
-- Name: oid_base_data_pkey; Type: CONSTRAINT; Schema: public; Owner: oph; Tablespace: 
--

ALTER TABLE ONLY oid_base_data
    ADD CONSTRAINT oid_base_data_pkey PRIMARY KEY (id);


--
-- Name: oid_pkey; Type: CONSTRAINT; Schema: public; Owner: oph; Tablespace: 
--

ALTER TABLE ONLY oid
    ADD CONSTRAINT oid_pkey PRIMARY KEY (oid_value);


--
-- PostgreSQL database dump complete
--


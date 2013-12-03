--
-- Data for Name: nodeclass; Type: TABLE DATA; Schema: public; Owner: oph
--

INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (1, 0, 'TEKN_5', 'Teknilliset koodistot, nimikkeistöt ja luokitukset', '5');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (2, 0, 'TEKN_6', 'Teknilliset koodistot, nimikkeistöt ja luokitukset 6', '6');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (3, 0, 'TOIMIPAIKAT', 'Toimipaikat (ml. organisaatiot)', '10');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (4, 0, 'ASIAKIRJAT', 'Asiakirjat', '11');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (5, 0, 'OHJELMISTOT', 'Ohjelmistot', '12');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (6, 0, 'LAITTEET', 'Laitteet', '13');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (7, 0, 'PALVELUT', 'Palvelut', '14');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (8, 0, 'LASKUTUS', 'Laskutus (ml. maksusitoumukset)', '16');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (9, 0, 'LOGISTIIKKA', 'Logistiikka (ml. kuljetustunnus)', '17');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (10, 0, 'SANOMALIIKENNE', 'Sanomaliikenteen osapuolten tunniste', '18');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (11, 0, 'REKISTERINPITAJA', 'Rekisterinpitäjä', '19');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (12, 0, 'NAYTETUNNISTE', 'Näytetunniste', '20');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (13, 0, 'TILAP_ASIAKAS', 'Tilapäinen asiakkaan tunniste', '22');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (14, 0, 'HENKILO', 'Henkilönumero', '24');
INSERT INTO nodeclass (id, version, classcode, description, nodevalue) VALUES (15, 0, 'ROOLI', 'Roolin yksilöinti', '27');


--
-- Data for Name: oid; Type: TABLE DATA; Schema: public; Owner: oph
--

--INSERT INTO oid (oid_value, checkdigit, node) VALUES (7182768137, 7, '24');


--
-- Data for Name: oid_base_data; Type: TABLE DATA; Schema: public; Owner: oph
--

INSERT INTO oid_base_data (id, version, key, value) VALUES (1, 0, 'rootNode', '1.2.246.562');

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2018-01-01 21:34:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 17407)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    account_id bigint NOT NULL,
    address character varying(255),
    email character varying(255),
    password character varying(255),
    phone character varying(255),
    real_name character varying(255),
    branch_id bigint
);


ALTER TABLE account OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 17413)
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE account_account_id_seq OWNER TO postgres;

--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 186
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_account_id_seq OWNED BY account.account_id;


--
-- TOC entry 187 (class 1259 OID 17415)
-- Name: account_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account_role (
    account_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE account_role OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 17418)
-- Name: branch; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE branch (
    branch_id bigint NOT NULL,
    address character varying(255),
    image character varying(255),
    name character varying(255),
    phone character varying(255)
);


ALTER TABLE branch OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17424)
-- Name: branch_branch_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE branch_branch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE branch_branch_id_seq OWNER TO postgres;

--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 189
-- Name: branch_branch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE branch_branch_id_seq OWNED BY branch.branch_id;


--
-- TOC entry 190 (class 1259 OID 17426)
-- Name: branch_food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE branch_food (
    branch_id bigint NOT NULL,
    food_id bigint NOT NULL
);


ALTER TABLE branch_food OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 17429)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE category (
    category_id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE category OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 17432)
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_category_id_seq OWNER TO postgres;

--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 192
-- Name: category_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE category_category_id_seq OWNED BY category.category_id;


--
-- TOC entry 193 (class 1259 OID 17434)
-- Name: food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE food (
    food_id bigint NOT NULL,
    description character varying(255),
    food_name character varying(255),
    image character varying(255),
    price bigint,
    category_id bigint
);


ALTER TABLE food OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 17440)
-- Name: food_food_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE food_food_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food_food_id_seq OWNER TO postgres;

--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 194
-- Name: food_food_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE food_food_id_seq OWNED BY food.food_id;


--
-- TOC entry 195 (class 1259 OID 17442)
-- Name: product_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_order (
    product_order_id bigint NOT NULL,
    address character varying(255),
    date_order character varying(255),
    order_status character varying(255),
    phone character varying(255),
    total_money integer,
    transactional_person character varying(255),
    account_id bigint,
    branch_id bigint
);


ALTER TABLE product_order OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 17448)
-- Name: product_order_food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_order_food (
    product_order_id bigint NOT NULL,
    food_id bigint NOT NULL
);


ALTER TABLE product_order_food OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17451)
-- Name: product_order_product_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_order_product_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_order_product_order_id_seq OWNER TO postgres;

--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 197
-- Name: product_order_product_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_order_product_order_id_seq OWNED BY product_order.product_order_id;


--
-- TOC entry 198 (class 1259 OID 17453)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE role (
    role_id bigint NOT NULL,
    role_name character varying(255)
);


ALTER TABLE role OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17456)
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_role_id_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 199
-- Name: role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE role_role_id_seq OWNED BY role.role_id;


--
-- TOC entry 2047 (class 2604 OID 17458)
-- Name: account account_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN account_id SET DEFAULT nextval('account_account_id_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 17459)
-- Name: branch branch_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY branch ALTER COLUMN branch_id SET DEFAULT nextval('branch_branch_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 17460)
-- Name: category category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY category ALTER COLUMN category_id SET DEFAULT nextval('category_category_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 17461)
-- Name: food food_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY food ALTER COLUMN food_id SET DEFAULT nextval('food_food_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 17462)
-- Name: product_order product_order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order ALTER COLUMN product_order_id SET DEFAULT nextval('product_order_product_order_id_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 17463)
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN role_id SET DEFAULT nextval('role_role_id_seq'::regclass);


--
-- TOC entry 2200 (class 0 OID 17407)
-- Dependencies: 185
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (account_id, address, email, password, phone, real_name, branch_id) FROM stdin;
2	None	switchboard@gmail.com	$2a$10$HueK.XvpSqbjJRLFxVZHI.pQ.6n6J2wDiUCvIqesDyEc5qN3LZPCu	0968005399	Tong dai 1	\N
1	None	admin@gmail.com	$2a$10$wK8OLbCRYnrivttvpZclZuLiRbAR8iyF.d/prsst1DQ2vBzyByfOS	0968005379	NhanHoang3	\N
3	None2	branch@gmail.com	$2a$10$j6hF.pvcEdXVyUIIgKQjNuZ/NyMKhKggrV8BWq76pWkiHh/wqnsnG	0968005388	Quan ly chi nhanh 1	1
4	None	member@gmail.com	$2a$10$BzpeLhW90RJ5MDXId80voOtkQxacgALJKQpqUweZJrPiTo7FxXglW	0968205388	Member 1233	\N
\.


--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 186
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_account_id_seq', 4, true);


--
-- TOC entry 2202 (class 0 OID 17415)
-- Dependencies: 187
-- Data for Name: account_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account_role (account_id, role_id) FROM stdin;
2	3
3	4
1	1
4	2
\.


--
-- TOC entry 2203 (class 0 OID 17418)
-- Dependencies: 188
-- Data for Name: branch; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY branch (branch_id, address, image, name, phone) FROM stdin;
1	5 Bùi Thị Xuân, Quận Hai Bà Trưng	https://media.foody.vn/res/g14/136680/prof/s480x300/foody-mobile-la-salsa1-jpg-316-635675632346110249.jpg	La Salsa	09670324001
2	809 Giải Phóng, Quận Hoàng Mai	https://media.foody.vn/res/g2/15429/prof/s640x400/foody-mobile-nam-son-jpg-390-635786845267621697.jpg	Nam Sơn Cuisine	09670324002
4	34 Đường Thành,  Quận Hoàn Kiếm	https://media.foody.vn/res/g11/103383/prof/s640x400/foody-mobile-4-jpg-168-636341545102663516.jpg	Khazaana 1992	09670324004
3	6B Đường Thành,  Quận Hoàn Kiếm	https://media.foody.vn/res/g10/94782/prof/s640x400/foody-mobile-moc-jpg-271-635814523748648630.jpg	Gộc Quán	09670324003
\.


--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 189
-- Name: branch_branch_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('branch_branch_id_seq', 4, true);


--
-- TOC entry 2205 (class 0 OID 17426)
-- Dependencies: 190
-- Data for Name: branch_food; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY branch_food (branch_id, food_id) FROM stdin;
3	1
3	3
\.


--
-- TOC entry 2206 (class 0 OID 17429)
-- Dependencies: 191
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY category (category_id, name) FROM stdin;
1	món nướng
\.


--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 192
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('category_category_id_seq', 2, true);


--
-- TOC entry 2208 (class 0 OID 17434)
-- Dependencies: 193
-- Data for Name: food; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY food (food_id, description, food_name, image, price, category_id) FROM stdin;
1	\N	Mì sốt bò xay	https://media.foody.vn/res/g1/8921/s570x570/20177149407-mi-bo-xay.jpg	70000	\N
2	\N	Cơm rang gà quay	https://media.foody.vn/res/g2/13882/s570x570/foody-c%C6%A1m%20rang%20g%C3%A0%20quay-635925295629704502.jpg	80000	\N
3	\N	Cơm Bò Gạo Lứt	https://media.foody.vn/res/g70/692705/s570x570/2017108225131-com-bo-gao-lut.jpg	75000	\N
4	\N	Salad Gà Trứng	https://media.foody.vn/res/g70/692705/s570x570/2017108125914-salad-ga-trung.jpg	76000	\N
5	\N	Cơm rang thập cẩm	https://media.foody.vn/res/g2/13882/s570x570/2016817144416-com-rang-thap-cam.jpg	77000	\N
6	\N	Mì xào gà	https://media.foody.vn/res/g2/13882/s570x570/2016817144525-mi-xao-ga.jpg	78000	\N
\.


--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 194
-- Name: food_food_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('food_food_id_seq', 7, true);


--
-- TOC entry 2210 (class 0 OID 17442)
-- Dependencies: 195
-- Data for Name: product_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_order (product_order_id, address, date_order, order_status, phone, total_money, transactional_person, account_id, branch_id) FROM stdin;
28	22	1/1/2018, 6:21:56 PM	OLD	3232323	230000	\N	\N	1
7	2132	12/23/2017, 10:37:00 PM	PROCESSING	321321	231000	\N	3	3
6	None222	12/23/2017, 10:28:53 PM	PROCESSING	0968205388	453000	\N	3	1
9	None	12/30/2017, 3:13:47 PM	NEW	0968005388	310000	\N	3	\N
10	edwedw edwedw edwedw edwedw	12/30/2017, 3:15:51 PM	COOKING	332323232	70000	\N	2	1
11	None	1/1/2018, 3:13:42 PM	NEW	0968005379	300000	\N	1	\N
12	None	1/1/2018, 4:27:30 PM	NEW	0968205388	210000	\N	4	\N
30	32323	1/1/2018, 6:32:27 PM	PROCESSING	32	145000	\N	2	2
21	dwd	1/1/2018, 5:55:57 PM	NEW	wedewdew	160000	\N	2	\N
\.


--
-- TOC entry 2211 (class 0 OID 17448)
-- Dependencies: 196
-- Data for Name: product_order_food; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_order_food (product_order_id, food_id) FROM stdin;
28	1
28	2
28	2
30	1
30	3
6	3
6	3
6	3
6	4
6	4
6	4
7	2
7	3
7	4
9	2
9	2
9	3
9	3
10	1
11	3
11	3
11	3
11	3
12	1
12	1
12	1
21	2
21	2
\.


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 197
-- Name: product_order_product_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_order_product_order_id_seq', 30, true);


--
-- TOC entry 2213 (class 0 OID 17453)
-- Dependencies: 198
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY role (role_id, role_name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_MEMBER
3	ROLE_SWITCHBOARD
4	ROLE_BRANCH
\.


--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 199
-- Name: role_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('role_role_id_seq', 4, true);


--
-- TOC entry 2054 (class 2606 OID 17465)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 2060 (class 2606 OID 17467)
-- Name: account_role account_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_pkey PRIMARY KEY (account_id, role_id);


--
-- TOC entry 2062 (class 2606 OID 17469)
-- Name: branch branch_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY branch
    ADD CONSTRAINT branch_pkey PRIMARY KEY (branch_id);


--
-- TOC entry 2064 (class 2606 OID 17471)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- TOC entry 2066 (class 2606 OID 17473)
-- Name: food food_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY food
    ADD CONSTRAINT food_pkey PRIMARY KEY (food_id);


--
-- TOC entry 2070 (class 2606 OID 17475)
-- Name: product_order product_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (product_order_id);


--
-- TOC entry 2072 (class 2606 OID 17477)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 2056 (class 2606 OID 17479)
-- Name: account uk_dgdnj692f2g5ebicy1xyc2l3w; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT uk_dgdnj692f2g5ebicy1xyc2l3w UNIQUE (phone);


--
-- TOC entry 2068 (class 2606 OID 17481)
-- Name: food uk_owcaxj73hkgavmpqaivj5o4yb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY food
    ADD CONSTRAINT uk_owcaxj73hkgavmpqaivj5o4yb UNIQUE (food_name);


--
-- TOC entry 2058 (class 2606 OID 17483)
-- Name: account uk_q0uja26qgu1atulenwup9rxyr; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT uk_q0uja26qgu1atulenwup9rxyr UNIQUE (email);


--
-- TOC entry 2074 (class 2606 OID 17484)
-- Name: account_role fk1f8y4iy71kb1arff79s71j0dh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account_role
    ADD CONSTRAINT fk1f8y4iy71kb1arff79s71j0dh FOREIGN KEY (account_id) REFERENCES account(account_id);


--
-- TOC entry 2081 (class 2606 OID 17489)
-- Name: product_order_food fk4ugybdbx15gsqe0avo7pdk324; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order_food
    ADD CONSTRAINT fk4ugybdbx15gsqe0avo7pdk324 FOREIGN KEY (product_order_id) REFERENCES product_order(product_order_id);


--
-- TOC entry 2082 (class 2606 OID 17494)
-- Name: product_order_food fk56ifri9ce3ylpfwsxuafa3unf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order_food
    ADD CONSTRAINT fk56ifri9ce3ylpfwsxuafa3unf FOREIGN KEY (food_id) REFERENCES food(food_id);


--
-- TOC entry 2076 (class 2606 OID 17499)
-- Name: branch_food fk5jdrqpbu4afmnhglmsfsa61ye; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY branch_food
    ADD CONSTRAINT fk5jdrqpbu4afmnhglmsfsa61ye FOREIGN KEY (branch_id) REFERENCES branch(branch_id);


--
-- TOC entry 2079 (class 2606 OID 17504)
-- Name: product_order fk6aluxrhb21inlvmrtahpt7wr4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order
    ADD CONSTRAINT fk6aluxrhb21inlvmrtahpt7wr4 FOREIGN KEY (branch_id) REFERENCES branch(branch_id);


--
-- TOC entry 2073 (class 2606 OID 17509)
-- Name: account fkcwcof6gi0txp7t7mow4ii4584; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT fkcwcof6gi0txp7t7mow4ii4584 FOREIGN KEY (branch_id) REFERENCES branch(branch_id);


--
-- TOC entry 2077 (class 2606 OID 17514)
-- Name: branch_food fkgeeq700wei75tnuyvlbnj5mnu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY branch_food
    ADD CONSTRAINT fkgeeq700wei75tnuyvlbnj5mnu FOREIGN KEY (food_id) REFERENCES food(food_id);


--
-- TOC entry 2080 (class 2606 OID 17519)
-- Name: product_order fkig3btav002hjei1bj1n7vm9hn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_order
    ADD CONSTRAINT fkig3btav002hjei1bj1n7vm9hn FOREIGN KEY (account_id) REFERENCES account(account_id);


--
-- TOC entry 2078 (class 2606 OID 17524)
-- Name: food fkkomdx99dhk2cveaxugl2lws2u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY food
    ADD CONSTRAINT fkkomdx99dhk2cveaxugl2lws2u FOREIGN KEY (category_id) REFERENCES category(category_id);


--
-- TOC entry 2075 (class 2606 OID 17529)
-- Name: account_role fkrs2s3m3039h0xt8d5yhwbuyam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account_role
    ADD CONSTRAINT fkrs2s3m3039h0xt8d5yhwbuyam FOREIGN KEY (role_id) REFERENCES role(role_id);


-- Completed on 2018-01-01 21:34:23

--
-- PostgreSQL database dump complete
--


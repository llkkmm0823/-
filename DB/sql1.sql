
/* Drop Tables */

DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_detail CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE worker CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE cart_seq;
DROP SEQUENCE orders_seq;
DROP SEQUENCE order_detail_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE qna_seq;




/* Create Sequences */

CREATE SEQUENCE cart_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE orders_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE order_detail_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE product_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE qna_seq INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE address
(
	-- 우편번호
	zip_num varchar2(7) NOT NULL,
	sido varchar2(30) NOT NULL,
	gugun varchar2(30) NOT NULL,
	zip_code varchar2(30),
	bunji varchar2(30)
);


CREATE TABLE cart
(
	cseq number(10) NOT NULL,
	id varchar2(20) NOT NULL,
	pseq number(5) NOT NULL,
	quantity number(5) DEFAULT 1 NOT NULL,
	result char(1) DEFAULT '1' NOT NULL,
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (cseq)
);

select*from member;
CREATE TABLE member
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	email varchar2(40) NOT NULL,
	zip_num varchar2(7),
	address1 varchar2(50),
	address2 varchar2(50),
	indate date DEFAULT SYSDATE,
	useyn char(1) DEFAULT 'Y',
	PRIMARY KEY (id)
);


CREATE TABLE orders
(
	oseq number(10) NOT NULL,
	id varchar2(20) NOT NULL,
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (oseq)
);


CREATE TABLE order_detail
(
	odseq number(10) NOT NULL,
	oseq number(10) NOT NULL,
	pseq number(5) NOT NULL,
	quantity number(5) DEFAULT 1 NOT NULL,
	result char(1) DEFAULT '1',
	PRIMARY KEY (odseq)
);


CREATE TABLE product
(
	pseq number(5) NOT NULL,
	name varchar2(100) NOT NULL,
	kind char(1) NOT NULL,
	price1 number(7) NOT NULL,
	price2 number(7) NOT NULL,
	price3 number(7) NOT NULL,
	content varchar2(1000) NOT NULL,
	image varchar2(255) NOT NULL,
	useryn char(1) DEFAULT 'Y',
	bestyn char(1) DEFAULT 'Y',
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (pseq)
);


CREATE TABLE qna
(
	qseq number(5) NOT NULL,
	id varchar2(20) NOT NULL,
	subject varchar2(100) NOT NULL,
	content varchar2(1000) NOT NULL,
	reply varchar2(1000),
	rep char(1) DEFAULT '1',
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (qseq)
);


CREATE TABLE worker
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE cart
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE orders
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE qna
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (oseq)
	REFERENCES orders (oseq)
;


ALTER TABLE cart
	ADD FOREIGN KEY (pseq)
	REFERENCES product (pseq)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (pseq)
	REFERENCES product (pseq)
;


select*from order_detail;




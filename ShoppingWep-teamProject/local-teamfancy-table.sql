--유저 테이블
create table fancy_user(
    userId varchar2(20),
    userPass varchar2(20) not null,
    userName nvarchar2(5) not null,
    phone char(11) not null,
    address nvarchar2(200) not null,
    accAmount number
);
alter table fancy_user add constraint userId_pk primary key (userId);
alter table fancy_user add constraint phone_uk unique (phone);

insert into fancy_user values('chan12','1234','chan','01012341234','seoul1',0);
insert into fancy_user values('kyung12','1234','kyung','01012341235','seoul2',0);
insert into fancy_user values('lak12','1234','lak','01012341236','seoul3',0);
insert into fancy_user values('mi12','1234','mi','01012341237','seoul4',0);
insert into fancy_user values('hyun12','1234','hyun','01012341238','seoul5',0);






--리뷰 테이블
CREATE TABLE REVIEW(
    REVIEW_NUM NUMBER NOT NULL,
    userID VARCHAR2(20) NOT NULL,
    PDCODE VARCHAR2(15) NOT NULL,
    COMMENTS NVARCHAR2(80),
    EVALUATION NUMBER NOT NULL
);

ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_PK PRIMARY KEY (REVIEW_NUM);
drop SEQUENCE review_seq;
CREATE SEQUENCE REVIEW_SEQ
    START WITH 1
    INCREMENT BY 1;
    
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_USERID_FK FOREIGN KEY (USERID) 
REFERENCES FANCY_USER (USERID)on delete cascade;

ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_PDCODE_FK FOREIGN KEY (PDCODE) 
REFERENCES PRODUCT (PDCODE);
    





--쿠폰 테이블
create table coupon(
    coupon_Num number,
    userId varchar2(20) not null,
    COUPON_W NUMBER(1) not null,
    COUPON_M NUMBER(1) not null,
    COUPON_D NUMBER(1) not null
);
alter table coupon add constraint coupon_Num_pk primary key (coupon_Num);
alter table coupon add constraint coupon_user_userId_fk foreign key (userId) references fancy_user (userId) on delete cascade;
drop SEQUENCE coupon_seq;
create sequence coupon_seq
START WITH 1 -- 시작을 1로 설정
INCREMENT BY 1 -- 증가값을 1씩 증가
NOMAXVALUE -- 최대값이 무한대..
NOCACHE
NOCYCLE;
    
insert into coupon values (coupon_seq,'chan12',1,0,0);
insert into coupon values (coupon_seq,'kyung12',1,0,0);
insert into coupon values (coupon_seq,'lak12',1,0,0);
insert into coupon values (coupon_seq,'mi12',1,0,0);
insert into coupon values (coupon_seq,'hyun12',1,0,0);






create table product(
    pdCode VARCHAR2(15),
    pdName NVARCHAR2(20) not null,
    brand NVARCHAR2(20),
    category NVARCHAR2(20),
    price NUMBER(15)
);
alter table product add CONSTRAINT product_pk PRIMARY key (pdCode);
CREATE SEQUENCE PRODUCT_SEQ
    START WITH 1
    INCREMENT BY 1;


create table cart(
    order_NUM NUMBER,
    pdCode VARCHAR2(6) NOT NULL,
    userId VARCHAR2(30) NOT NULL,
    quantity NUMBER(8) NOT NULL,
    salesAmount NUMBER(12) NOT NULL,
    isPayment number(1) DEFAULT 0,
    paymentDate date
);

alter table cart add constraint cart_pk primary key(order_NUM);
alter table cart add constraint CART_PRODUCT_fk foreign key(pdCode) references product(pdCode);
alter table cart add constraint CART_USER_fk foreign key(USERID) references FANCY_USER(USERID);
create SEQUENCE cart_seq start with 1 increment by 1;



CREATE TABLE CALCULATE(
    CALCULATE_NUM NUMBER NOT NULL,  
    PDCODE VARCHAR2(15) NOT NULL,
    QUANTITY NUMBER NOT NULL,
    SALES_AMOUNT NUMBER NOT NULL,
    PAYMENT_DATE DATE
);

ALTER TABLE CALCULATE ADD CONSTRAINT CALCULATE_PK PRIMARY KEY (CALCULATE_NUM);
ALTER TABLE CALCULATE ADD CONSTRAINT PRODUCT_CALCULATE_FK FOREIGN KEY (PDCODE)
REFERENCES PRODUCT(PDCODE);


CREATE TABLE ADMIN(
    ADMIN_ID VARCHAR2(20) NOT NULL,
    ADMIN_PW VARCHAR2(20) NOT NULL
);
ALTER TABLE ADMIN ADD CONSTRAINT ADMIN_PK PRIMARY KEY (ADMIN_ID);
-------------------------------------------------------------------------------







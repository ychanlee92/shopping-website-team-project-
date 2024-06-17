--CALCULATE PROCEDURE CREATE BY JAEKYEONG
--장바구니 항목 결제시 정산테이블에 추가하는 트리거
CREATE OR REPLACE TRIGGER CAL_ADD_TRG
    AFTER UPDATE ON CART 
    for each row
BEGIN
    insert into calculate 
    values(calculate_seq.nextval,:old.pdcode,:old.order_num,:old.quantity,:old.salesAmount,:new.paymentDate);
END;
/
-------------------------------------------------------------------------------
--날짜별 매출 리스트 가져오기 프로시저
CREATE OR REPLACE PROCEDURE CAL_DATE_PROC(
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN VCURSOR FOR
  SELECT DISTINCT PAYMENTDATE,SUM(QUANTITY) AS QUANTITY ,SUM(SALESAMOUNT) AS SALESAMOUNT 
  FROM CALCULATE 
  GROUP BY PAYMENTDATE
  ORDER BY PAYMENTDATE DESC;
END;
/
-------------------------------------------------------------------------------
--상품별 매출 리스트 가져오기 프로시저
CREATE OR REPLACE PROCEDURE CAL_PD_PROC(
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN VCURSOR FOR
  SELECT DISTINCT PDCODE, SUM(QUANTITY) AS QUANTITY,SUM(SALESAMOUNT) AS SALESAMOUNT  
  FROM CALCULATE 
  GROUP BY PDCODE
  ORDER BY PDCODE ASC;
END;
/  
-------------------------------------------------------------------------------
--지정 날짜 매출 리스트 가져오기 프로시저
CREATE OR REPLACE PROCEDURE CAL_DETAIL_DATE_PROC(
VDATE IN VARCHAR2,
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN VCURSOR FOR
    SELECT * FROM CALCULATE 
    WHERE to_char(PAYMENTDATE,'YYYY/MM/DD')= to_char(to_date(vdate,'YYYYMMDD'),'YYYY/MM/DD')
    ORDER BY CALCULATE_NUM ASC;
END;
/     
-------------------------------------------------------------------------------
--지정 상품별 매출 리스트 가져오기 프로시저
CREATE OR REPLACE PROCEDURE CAL_DETAIL_PD_PROC(
VPDCODE IN CALCULATE.PDCODE%TYPE,
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN VCURSOR FOR
    SELECT * FROM CALCULATE 
    WHERE PDCODE = VPDCODE
    ORDER BY CALCULATE_NUM ASC;
END;
/
-------------------------------------------------------------------------------
--정산 테이블 count 프로시저
CREATE OR REPLACE PROCEDURE CAL_COUNT(
CNT OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO CNT FROM CALCULATE;
END;
/
-------------------------------------------------------------------------------
--지정날짜 항목 존재여부 check 프로지서
CREATE OR REPLACE PROCEDURE CAL_DATE_CHECK(
VDATE IN VARCHAR2,
CNT OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO CNT FROM CALCULATE 
    WHERE to_char(PAYMENTDATE,'YYYY/MM/DD')= to_char(to_date(vdate,'YYYYMMDD'),'YYYY/MM/DD');
END;
/
-------------------------------------------------------------------------------
--지정 상품 존재여부 check프로시저
CREATE OR REPLACE PROCEDURE CAL_PD_CHECK(
VPDCODE IN CALCULATE.PDCODE%TYPE,
CNT OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO CNT FROM CALCULATE 
    WHERE PDCODE = VPDCODE;
END;
/

--CALCULATE PROCEDURE CREATE BY JAEKYEONG
--��ٱ��� �׸� ������ �������̺� �߰��ϴ� Ʈ����
CREATE OR REPLACE TRIGGER CAL_ADD_TRG
    AFTER UPDATE ON CART 
    for each row
BEGIN
    insert into calculate 
    values(calculate_seq.nextval,:old.pdcode,:old.order_num,:old.quantity,:old.salesAmount,:new.paymentDate);
END;
/
-------------------------------------------------------------------------------
--��¥�� ���� ����Ʈ �������� ���ν���
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
--��ǰ�� ���� ����Ʈ �������� ���ν���
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
--���� ��¥ ���� ����Ʈ �������� ���ν���
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
--���� ��ǰ�� ���� ����Ʈ �������� ���ν���
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
--���� ���̺� count ���ν���
CREATE OR REPLACE PROCEDURE CAL_COUNT(
CNT OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO CNT FROM CALCULATE;
END;
/
-------------------------------------------------------------------------------
--������¥ �׸� ���翩�� check ��������
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
--���� ��ǰ ���翩�� check���ν���
CREATE OR REPLACE PROCEDURE CAL_PD_CHECK(
VPDCODE IN CALCULATE.PDCODE%TYPE,
CNT OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO CNT FROM CALCULATE 
    WHERE PDCODE = VPDCODE;
END;
/

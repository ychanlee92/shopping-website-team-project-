
create or replace NONEDITIONABLE PROCEDURE ADMIN_CHECK_PROC(
VID IN ADMIN.ADMIN_ID%TYPE,
IDCHECK OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO IDCHECK FROM ADMIN WHERE ADMIN_ID = VID;
END;
/


select pdcode, pdname, brand, category, price, comments
from (select p.pdcode, pdname, brand, category, price, comments from product p inner join review r on p.pdcode = r.pdcode) where pdname like '%����%' and rownum=1;

desc review;

select * from product;
insert into review values(review_seq.nextval, 'mi12','TO1', '���� ����Ƽ ���ƿ�', 4.5);
insert into review values(review_seq.nextval, 'mi12','TO1', '����Ƽ �̻���', 4.3);
insert into review values(review_seq.nextval, 'mi12','TO3', '��ư �ε巯��', 3.7);
select * from review;
commit;

select * from review where pd='%����%';






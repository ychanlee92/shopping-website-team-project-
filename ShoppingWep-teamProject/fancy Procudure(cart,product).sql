--CART , PRODUCT PROCEDURE CREATE BY MIKYUNG
------------------------------------------------------------------------------
--cart ���̺� ���� 
create or replace NONEDITIONABLE procedure cart_insert(
        v_pdcode in cart.PDCODE%type,
        v_userid in cart.USERID%type,
        v_quantity in cart.QUANTITY%type,
        v_salesamount in cart.SALESAMOUNT%type,
        v_ispayment in cart.ISPAYMENT%type)
    is
    begin
        insert into cart(order_num,pdcode, userid,quantity,salesamount,ispayment) values ( cart_seq.nextval, v_pdcode,v_userid,v_quantity,v_salesamount,v_ispayment);
end cart_insert;
/

------------------------------------------------------------------------------
--Ư�� userID�� cart��� ����
create or replace NONEDITIONABLE procedure cart_count(
        v_userid in cart.userid%type,
        v_count out number)
    is
    begin
        select count(*) count into v_count from cart where userid = v_userid;
end cart_count;
/
DECLARE
    v_count NUMBER;
BEGIN
    cart_count('chan12', v_count);
    DBMS_OUTPUT.PUT_LINE('Cart count: ' || v_count);
END;
/

------------------------------------------------------------------------------
--Ư�� user �� ������ ��ǰ ���
create or replace NONEDITIONABLE procedure choicelist(
        v_userid in cart.userid%type, 
        v_cursor out SYS_REFCURSOR )
is
begin
    open v_cursor for
    select c.order_num, p.pdcode, p.pdname, p.brand, p.category, c.quantity, c.salesamount
    from product p inner join cart c on p.pdcode = c.pdcode where c.userid = v_userid and c.ispayment=0 order by c.order_num;
end choicelist;
/
------------------------------------------------------------------------------
--�������� Ư�� user + pdcode ��ǰ ����Ʈ
create or replace NONEDITIONABLE procedure dlechoicelist(
        v_userid in cart.userid%type, v_orderno in cart.order_num%type , v_cursor out SYS_REFCURSOR )
is
begin
    open v_cursor for
    select c.order_num, p.pdcode, p.pdname, p.brand, p.category, c.quantity, c.salesamount
    from product p inner join cart c on p.pdcode = c.pdcode where c.userid = v_userid and order_num = v_orderno and userid = v_userid;
end dlechoicelist;
/
------------------------------------------------------------------------------
--user �� ��ٱ��� ���� ����
create or replace NONEDITIONABLE procedure cart_delete(
        v_userid in cart.userid%type,
        v_orderno in cart.order_num%type
        )
    is
    begin
        delete from cart where order_num = v_orderno and userid = v_userid;
end;
/
------------------------------------------------------------------------------
--user �� ��ٱ��� ����
create or replace NONEDITIONABLE procedure cart_clear(v_userid in cart.userid%type)
    is
    begin
        delete from cart where userid = v_userid;
end;
/


--****************************************************************************
--������� Admin��ǰ����
--product ���̺� ��ǰ ���
create or replace NONEDITIONABLE procedure productInsert(
        vPdcode in product.PDCODE%type,
        vPdname in product.PDNAME%type,
        vBrand in product.BRAND%type,
        vCategory in product.CATEGORY%type,
        vPrice in product.PRICE%type)
    is
    begin
        INSERT INTO PRODUCT VALUES(vPdcode||PRODUCT_SEQ.NEXTVAL,vPdname,vBrand,vCategory,vPrice);
end productInsert;
/
------------------------------------------------------------------------------
--product ���̺� ��ǰ ����
create or replace NONEDITIONABLE procedure productUpdate(
        vPdcode in product.PDCODE%type,
        vPdname in product.PDNAME%type,
        vBrand in product.BRAND%type,
        vCategory in product.CATEGORY%type,
        vPrice in product.PRICE%type)
    is
    begin
        update product set pdname = vPdname, brand = vBrand, category = vCategory, price = vPrice where pdcode = vPdcode;
end productUpdate;
/
------------------------------------------------------------------------------
--product ���̺� ��ǰ ����
create or replace NONEDITIONABLE procedure productDelete(vPdcode in product.PDCODE%type)
    is
    begin
        delete from product where pdcode = vPdcode;
end productDelete;
/


---------------------------------------------------------------------------------

create or replace procedure cart_purchaseAll
(
  vid in varchar2,
  msg out varchar2
)
is
  total number;
  cursor c_cart is
    select sum(salesAmount) as total_amount
    from cart
    where userid = vid;

begin
  -- ���� ó��
  update cart set isPayment = 1, paymentDate = sysdate where userid = vid;

  -- ���� �ʱ�ȭ
  update coupon set coupon_w = 0, coupon_m = 0, coupon_d = 0 where userid = vid;

  -- ���� ���� �ݾ� ���
  total := 0;
  open c_cart;
  fetch c_cart into total;
  close c_cart;

  -- ���� �߱� ���ǿ� ���� ó��
  if total >= 200000 then
    update coupon set coupon_m = 1 where userid = vid;
    msg := '������ �Ϸ�Ǿ����ϴ�. 20���� ���� ������ �߱޵Ǿ����ϴ�.';
  elsif total >= 100000 then
    update coupon set coupon_d = 1 where userid = vid;
    msg := '������ �Ϸ�Ǿ����ϴ�. 10���� ���� ������ �߱޵Ǿ����ϴ�.';
  else
    msg := '������ �Ϸ�Ǿ����ϴ�.';
  end if;
  
exception
  when no_data_found then
    msg := '������� īƮ�� ���� ������ �����ϴ�.';
  when others then
    msg := '������ �߻��߽��ϴ�.';
end cart_purchaseAll;
/

create or replace procedure cart_purchaseOne
(
  vid in varchar2,
  vnumber in number,
  msg out varchar2
)
is
  total number;
  cursor c_cart is
    select sum(salesAmount) as total_amount
    from cart
    where userid = vid and order_num = vnumber;

begin
  -- ���� ó��
  update cart set isPayment = 1, paymentDate = sysdate where userid = vid and order_num = vnumber;

  -- ���� �ʱ�ȭ
  update coupon set coupon_w = 0, coupon_m = 0, coupon_d = 0 where userid = vid;

  -- ���� ���� �ݾ� ���
  total := 0;
  open c_cart;
  fetch c_cart into total;
  close c_cart;

  -- ���� �߱� ���ǿ� ���� ó��
  if total >= 200000 then
    update coupon set coupon_m = 1 where userid = vid;
    msg := '������ �Ϸ�Ǿ����ϴ�. 20���� ���� ������ �߱޵Ǿ����ϴ�.';
  elsif total >= 100000 then
    update coupon set coupon_d = 1 where userid = vid;
    msg := '������ �Ϸ�Ǿ����ϴ�. 10���� ���� ������ �߱޵Ǿ����ϴ�.';
  else
    msg := '������ �Ϸ�Ǿ����ϴ�.';
  end if;
  
exception
  when no_data_found then
    msg := '�ش� ��ǰ�� ���� ������ �����ϴ�.';
  when others then
    msg := '������ �߻��߽��ϴ�.';
end cart_purchaseOne;
/
create or replace NONEDITIONABLE procedure pdReview(
        vSearchName in product.pdname%type, v_cursor out SYS_REFCURSOR )
is
begin
    open v_cursor for
        WITH ranked_comments AS ( SELECT p.pdcode,p.pdname,p.brand,p.category,p.price,r.comments,
        ROW_NUMBER() OVER (PARTITION BY p.pdcode ORDER BY r.comments) AS rn 
        FROM product p LEFT JOIN review r ON p.pdcode = r.pdcode WHERE p.pdname LIKE '%'||vSearchName||'%')
            SELECT pdcode,pdname,brand,category,price,NVL(comments, ' ') AS comments
            FROM ranked_comments WHERE rn = 1 ORDER BY pdcode ASC;
end pdReview;
/
--cart 테이블에 삽입------------------------------------------------
create or replace NONEDITIONABLE procedure cart_insert(
        v_pdcode in cart.PDCODE%type,
        v_userid in cart.USERID%type,
        v_quantity in cart.QUANTITY%type,
        v_salesamount in cart.SALESAMOUNT%type,
        v_ispayment in cart.ISPAYMENT%type)
    is
    begin
        insert into cart values ( cart_seq.nextval, v_pdcode,v_userid,v_quantity,v_salesamount,v_ispayment,sysdate);
end cart_insert;
exec cart_insert('SH12', 'chan12',10,2000,0);
--특정 userID별 cart목록 개수--------------------------------------------------------------
create or replace NONEDITIONABLE procedure cart_count(
        v_userid in cart.userid%type,
        v_count out number)
    is
    begin
        select count(*) count into v_count from cart where userid = v_userid;
end cart_count;

DECLARE
    v_count NUMBER;
BEGIN
    cart_count('chan12', v_count);
    DBMS_OUTPUT.PUT_LINE('Cart count: ' || v_count);
END;
/
--특정 user 별 구매한 상품 목록---------------------------------------------------------------
create or replace NONEDITIONABLE procedure choicelist(
        v_userid in cart.userid%type, 
        v_cursor out SYS_REFCURSOR )
is
begin
    open v_cursor for
    select c.order_num, p.pdcode, p.pdname, p.brand, p.category, c.quantity, c.salesamount
    from product p inner join cart c on p.pdcode = c.pdcode where c.userid = v_userid order by c.order_num;
end choicelist;

VAR v_cursor REFCURSOR;
EXEC choicelist('chan12', :v_cursor);
PRINT v_cursor;

--user 별 장바구니 선택 삭제---------------------------------------------------------------
create or replace NONEDITIONABLE procedure cart_delete(
        v_userid in cart.userid%type,
        v_orderno in cart.order_num%type
        )
    is
    begin
        delete from cart where order_num = v_orderno and userid = v_userid;
end;

exec cart_delete('chan12',4);


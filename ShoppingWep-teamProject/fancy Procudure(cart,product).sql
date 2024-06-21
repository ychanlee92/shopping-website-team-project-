--CART , PRODUCT PROCEDURE CREATE BY MIKYUNG
------------------------------------------------------------------------------
--cart 테이블에 삽입 
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
--특정 userID별 cart목록 개수
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
--특정 user 별 구매한 상품 목록
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
--삭제관련 특정 user + pdcode 상품 리스트
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
--user 별 장바구니 선택 삭제
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
--user 별 장바구니 비우기
create or replace NONEDITIONABLE procedure cart_clear(v_userid in cart.userid%type)
    is
    begin
        delete from cart where userid = v_userid;
end;
/


--****************************************************************************
--여기부터 Admin상품관리
--product 테이블 상품 등록
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
--product 테이블 상품 수정
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
--product 테이블 상품 삭제
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
  -- 결제 처리
  update cart set isPayment = 1, paymentDate = sysdate where userid = vid;

  -- 쿠폰 초기화
  update coupon set coupon_w = 0, coupon_m = 0, coupon_d = 0 where userid = vid;

  -- 누적 결제 금액 계산
  total := 0;
  open c_cart;
  fetch c_cart into total;
  close c_cart;

  -- 쿠폰 발급 조건에 따라 처리
  if total >= 200000 then
    update coupon set coupon_m = 1 where userid = vid;
    msg := '결제가 완료되었습니다. 20만원 누적 쿠폰이 발급되었습니다.';
  elsif total >= 100000 then
    update coupon set coupon_d = 1 where userid = vid;
    msg := '결제가 완료되었습니다. 10만원 누적 쿠폰이 발급되었습니다.';
  else
    msg := '결제가 완료되었습니다.';
  end if;
  
exception
  when no_data_found then
    msg := '사용자의 카트에 대한 정보가 없습니다.';
  when others then
    msg := '오류가 발생했습니다.';
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
  -- 결제 처리
  update cart set isPayment = 1, paymentDate = sysdate where userid = vid and order_num = vnumber;

  -- 쿠폰 초기화
  update coupon set coupon_w = 0, coupon_m = 0, coupon_d = 0 where userid = vid;

  -- 누적 결제 금액 계산
  total := 0;
  open c_cart;
  fetch c_cart into total;
  close c_cart;

  -- 쿠폰 발급 조건에 따라 처리
  if total >= 200000 then
    update coupon set coupon_m = 1 where userid = vid;
    msg := '결제가 완료되었습니다. 20만원 누적 쿠폰이 발급되었습니다.';
  elsif total >= 100000 then
    update coupon set coupon_d = 1 where userid = vid;
    msg := '결제가 완료되었습니다. 10만원 누적 쿠폰이 발급되었습니다.';
  else
    msg := '결제가 완료되었습니다.';
  end if;
  
exception
  when no_data_found then
    msg := '해당 상품에 대한 정보가 없습니다.';
  when others then
    msg := '오류가 발생했습니다.';
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
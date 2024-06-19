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
        insert into cart values ( cart_seq.nextval, v_pdcode,v_userid,v_quantity,v_salesamount,v_ispayment,sysdate);
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
    from product p inner join cart c on p.pdcode = c.pdcode where c.userid = v_userid order by c.order_num;
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

--상품조회 시 리뷰보여주기---
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

--procedure pdReview 테트스 코드-----
DECLARE
    v_cursor SYS_REFCURSOR;
    v_pdcode product.pdcode%TYPE;
    v_pdname product.pdname%TYPE;
    v_brand product.brand%TYPE;
    v_category product.category%TYPE;
    v_price product.price%TYPE;
    v_comments review.comments%TYPE;
BEGIN
    -- 프로시저 호출
    pdReview('반팔티', v_cursor);

    -- 커서에서 데이터를 추출하여 출력
    LOOP
        FETCH v_cursor INTO v_pdcode, v_pdname, v_brand, v_category, v_price, v_comments;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('PDCode: ' || v_pdcode || ', PDName: ' || v_pdname || ', Brand: ' || v_brand || ', Category: ' || v_category || ', Price: ' || v_price || ', Comments: ' || v_comments);
    END LOOP;

    -- 커서 닫기
    CLOSE v_cursor;
END;
/
-------------------------

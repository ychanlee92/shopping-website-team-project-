--USER, COUPON, REVIEW PROCEDURE CREATE BY YECHAN
------------------------------------------------------------------------------
--주석
create or replace procedure insertuser
(vid in varchar2, vpass in varchar2, vname in varchar2, vphone in char, vaddress in varchar2, vacc in number, msg out varchar2)
is
begin
    insert into fancy_user values( vid, vpass, vname, vphone, vaddress, vacc);
    msg := '축하드립니다. 회원가입이 완료되었습니다!';
end insertuser;
/
------------------------------------------------------------------------------
--주석
create or replace procedure updateuser
(vid in varchar2, vpass in varchar2, vphone in varchar2, vaddress in varchar2, msg out varchar2)
is
begin
    update fancy_user set  userPass = vpass, phone = vphone, address = vaddress where userId = vid;
    msg := '고객 정보가 변경되었습니다.';
end updateuser;
/
------------------------------------------------------------------------------
--주석
create or replace procedure deleteuser
(vid in varchar2, msg out varchar2)
is
begin
    delete from fancy_user where userid = vid;
    msg := '계정이 삭제되었습니다.';
end deleteuser;
/
------------------------------------------------------------------------------
--주석
create or replace procedure insertcoupon_w
(vid in varchar2)
is
begin
    insert into coupon values(coupon_seq.nextval, vid, 1, 0, 0);
end insertcoupon_w;
/

create or replace NONEDITIONABLE PROCEDURE ADMIN_CHECK_PROC(
VID IN ADMIN.ADMIN_ID%TYPE,
IDCHECK OUT NUMBER)
IS
BEGIN
    SELECT COUNT(*) INTO IDCHECK FROM ADMIN WHERE ADMIN_ID = VID;
END;
/

------------------------------------------------------------------------
create or replace procedure insertReview
(vid in varchar2, vrate in number, vcomments in varchar2, vnum in number, msg out varchar2)
is
    vpdcode varchar2(6);
    cursor c_cart is
    select pdcode
    from cart
    where order_num = vnum;
    
begin
    vpdcode := 'a';
    open c_cart;
    fetch c_cart into vpdcode;
    close c_cart;
    
    insert into review values (review_seq.nextval,vid, vpdcode, vcomments, vrate);
    msg := '리뷰가 작성되었습니다.';
end insertReview;
/

create or replace procedure review_count(
        vid in varchar2, vvcount out number)
    is
    vcount number;
    cursor c_review is
    select count(*) count
    from review
    where userid = vid;
    begin
        vcount := 0;
    open c_review;
    fetch c_review into vcount;
    close c_review;
    
    vvcount := vcount;
end review_count;
/

create or replace procedure review_countadmin(
vvcount out number)
    is
    vcount number;
    cursor c_review is
    select count(*) count
    from review;
    begin
        vcount := 0;
    open c_review;
    fetch c_review into vcount;
    close c_review;
    
    vvcount := vcount;
end review_countadmin;
/
create or replace procedure removeReview
(vid in varchar2, vnum in number, msg out varchar2)
is
begin
    delete from review where userid = vid and review_num = vnum;
    msg := '리뷰가 삭제되었습니다.';
end removeReview;
/
create or replace procedure removeReviewadmin
(vnum in number, msg out varchar2)
is
begin
    delete from review where review_num = vnum;
    msg := '리뷰가 삭제되었습니다.';
end removeReviewadmin;
/
create or replace procedure removeReviewone
(vid in varchar2, msg out varchar2)
is
begin
    delete from review where userid = vid;
    msg := '리뷰가 삭제되었습니다.';
end removeReviewone;
/

create or replace procedure removeReviewoneAdmin
(msg out varchar2)
is
begin
    delete from review;
    msg := '리뷰가 삭제되었습니다.';
end removeReviewoneAdmin;
/
create or replace procedure insertuser
(vid in varchar2, vpass in varchar2, vname in varchar2, vphone in char, vaddress in varchar2, vacc in number, msg out varchar2)
is
begin
    insert into fancy_user values( vid, vpass, vname, vphone, vaddress, vacc);
    msg := '축하드립니다. 회원가입이 완료되었습니다!';
end insertuser;

create or replace procedure updateuser
(vid in varchar2, vpass in varchar2, vphone in varchar2, vaddress in varchar2, msg out varchar2)
is
begin
    update fancy_user set  userPass = vpass, phone = vphone, address = vaddress where userId = vid;
    msg := '고객 정보가 변경되었습니다.';
end updateuser;

create or replace procedure deleteuser
(vid in varchar2, msg out varchar2)
is
begin
    delete from fancy_user where id = vid;
    msg := '계정이 삭제되었습니다.';
end deleteuser;

create or replace procedure updatecoupon1
(vid in varchar2)
is
begin
    update fancy_coupon set welcomeC = '유' where userId = vid;
end updatecoupon1;
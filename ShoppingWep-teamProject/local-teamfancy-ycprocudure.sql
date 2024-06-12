create or replace procedure insertuser
(vid in varchar2, vpass in varchar2, vname in varchar2, vphone in char, vaddress in varchar2, vacc in number, msg out varchar2)
is
begin
    insert into fancy_user values( vid, vpass, vname, vphone, vaddress, vacc);
    msg := '���ϵ帳�ϴ�. ȸ�������� �Ϸ�Ǿ����ϴ�!';
end insertuser;

create or replace procedure updateuser
(vid in varchar2, vpass in varchar2, vphone in varchar2, vaddress in varchar2, msg out varchar2)
is
begin
    update fancy_user set  userPass = vpass, phone = vphone, address = vaddress where userId = vid;
    msg := '�� ������ ����Ǿ����ϴ�.';
end updateuser;

create or replace procedure deleteuser
(vid in varchar2, msg out varchar2)
is
begin
    delete from fancy_user where id = vid;
    msg := '������ �����Ǿ����ϴ�.';
end deleteuser;

create or replace procedure updatecoupon1
(vid in varchar2)
is
begin
    update fancy_coupon set welcomeC = '��' where userId = vid;
end updatecoupon1;
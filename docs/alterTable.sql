-- Teacher, Student : gender ���� ����
alter table teacher modify gender varchar2(4);
alter table student modify gender varchar2(4);

-- Teacher : message ���� ����
alter table teacher modify message varchar2(100);

-- Course : classinfo �߰�
alter table course add classinfo varchar2(1000);

-- 4/28 Student,Teacher,Course : address/location ���� ����
alter table student modify address varchar2(100);
alter table teacher modify address varchar2(100);
alter table course modify location varchar2(100);

-- 4/29 Homeworkanswer : content �߰�
alter table homeworkanswer add content varchar2(1000);

-- 5/3
-- Homeworkanswer : filename constraint(not null) ����
-- homeworkanswer ���̺��� �������� �߿���, filename �ʵ� not null ���������� �̸��� �ְ� ����
alter table homeworkanswer drop constraint ���������̸�;
-- alter notification : from_id ����
alter table notification drop column from_id;

-- 5/4
-- Notification : is_checked �߰�
alter table notification add is_checked char(1) default 0 not null;
-- Homework : filename ���� ����
alter table homework modify filename varchar2(1000);

-- 5/6 Reply : Sequence �߰�
create sequence ReplyIdSeq start with 1;
-- Teacher, Student : gender 길이 변경
alter table teacher modify gender varchar2(4);
alter table student modify gender varchar2(4);

-- Teacher : message 길이 변경
alter table teacher modify message varchar2(100);

-- Course : classinfo 추가
alter table course add classinfo varchar2(1000);

-- 4/28 Student,Teacher,Course : address/location 길이 변경
alter table student modify address varchar2(100);
alter table teacher modify address varchar2(100);
alter table course modify location varchar2(100);

-- 4/29 Homeworkanswer : content 추가
alter table homeworkanswer add content varchar2(1000);

-- 5/3
-- Homeworkanswer : filename constraint(not null) 삭제
-- homeworkanswer 테이블의 제약조건 중에서, filename 필드 not null 제약조건의 이름을 넣고 실행
alter table homeworkanswer drop constraint 제약조건이름;
-- alter notification : from_id 삭제
alter table notification drop column from_id;

-- 5/4
-- Notification : is_checked 추가
alter table notification add is_checked char(1) default 0 not null;
-- Homework : filename 길이 변경
alter table homework modify filename varchar2(1000);

-- 5/6 Reply : Sequence 추가
create sequence ReplyIdSeq start with 1;
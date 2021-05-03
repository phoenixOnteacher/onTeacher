-- alter teacher, student : gender
alter table teacher modify gender varchar2(4);
alter table student modify gender varchar2(4);

-- alter teacher : message
alter table teacher modify message varchar2(100);

-- alter course : classinfo
alter table course add classinfo varchar2(1000);

-- 4/28 alter student,teacher,course : address/location
alter table student modify address varchar2(100);
alter table teacher modify address varchar2(100);
alter table course modify location varchar2(100);

-- 4/29 alter homeworkanswer : content
alter table homeworkanswer add content varchar2(1000);

-- 5/3
-- alter table homeworkanswer : filename constraint
-- homeworkanswer 테이블의 제약조건 중에서, filename 필드 not null 제약조건의 이름을 넣고 실행
alter table homeworkanswer drop constraint 제약조건이름;
-- alter notification : from_id
alter table notification drop column from_id;
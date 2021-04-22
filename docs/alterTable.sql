-- alter teacher, student : gender
alter table teacher modify gender varchar2(4);
alter table student modify gender varchar2(4);

-- alter teacher : message
alter table teacher modify message varchar2(100);
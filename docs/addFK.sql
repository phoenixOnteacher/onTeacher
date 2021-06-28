alter table Article add constraint FK_STUDENT_TO_ARTICLE foreign key(user_id) references Student(id);
alter table Reply add constraint FK_TEACHER_TO_REPLY foreign key(user_id) references Teacher(id);
-- User : Admin, Student, Teacher
drop sequence AdminIdSeq;
create sequence AdminIdSeq
start with 100000;

drop sequence StudentIdSeq;
create sequence StudentIdSeq
start with 200000;

drop sequence TeacherIdSeq;
create sequence TeacherIdSeq
start with 300000;

-- Class : Course, Catetgory, Review, Homework
drop sequence CourseIdSeq;
create sequence CourseIdSeq
start with 1;

drop sequence HighCategoryIdSeq;
create sequence HighCategoryIdSeq
start with 1;

drop sequence LowCategoryIdSeq;
create sequence LowCategoryIdSeq
start with 1;

drop sequence CourseReviewIdSeq;
create sequence CourseReviewIdSeq
start with 1;

drop sequence StudentReviewIdSeq;
create sequence StudentReviewIdSeq
start with 1;

drop sequence HomeworkIdSeq;
create sequence HomeworkIdSeq
start with 1;

-- etc : Notification
drop sequence NotificationIdSeq;
create sequence NotificationIdSeq
start with 1;
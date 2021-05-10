-- Create table
CREATE TABLE Teacher (
	id	number	PRIMARY KEY	NOT NULL,
	email	varchar2(50)	UNIQUE  NOT NULL,
	name	varchar2(20)		NOT NULL,
	password	varchar2(20)		NOT NULL,
	phone_number	varchar2(20)		NOT NULL,
	address	varchar2(100)		NOT NULL,
	birthday	date    NOT NULL,
	gender	varchar2(4) CONSTRAINT teacher_gender_CK CHECK(gender IN ('��','��'))		NOT NULL,
	profile_img	varchar2(100),
	introduction	varchar2(1000),
	active	char(1)	DEFAULT 0   CONSTRAINT teacher_active_CK CHECK(active IN ('0','1'))	NOT NULL,
	filename	varchar2(100)		NOT NULL,
	description	varchar2(100)		NOT NULL,
	status	varchar2(10)	DEFAULT 'submitted' CONSTRAINT teacher_status_CK CHECK(status IN ('submitted','approved','rejected'))	NOT NULL,
	message	varchar2(100)	DEFAULT '���� ���'
);

CREATE TABLE Student (
	id	number  PRIMARY KEY NOT NULL,
	email	varchar2(50)	UNIQUE  NOT NULL,
	name	varchar2(20)		NOT NULL,
	password	varchar2(20)		NOT NULL,
	phone_number	varchar2(20)		NOT NULL,
	grade	varchar2(10),
	address	varchar2(100),
	birthday	date    NOT NULL,
	gender	varchar2(4) CONSTRAINT student_gender_CK CHECK(gender IN ('��','��'))		NOT NULL,
	profile_img	varchar2(100),
	introduction	varchar2(1000)
);

CREATE TABLE Notification (
	id	number	PRIMARY KEY	NOT NULL,
	to_id number		NOT NULL,
	content	varchar2(1000)      NOT NULL,
	created_at	date	DEFAULT sysdate	NOT NULL,
    is_checked char(1) default 0 NOT NULL
);

CREATE TABLE Course (
	id	number	PRIMARY KEY	NOT NULL,
	teacher_id	number		NOT NULL,
	high_category_id	number		NOT NULL,
	low_category_id	number		NOT NULL,
	target	varchar2(20)		NULL,
	is_oneday	char(1)	CONSTRAINT course_is_oneday_CK CHECK(is_oneday IN ('0','1'))		NULL,
	start_date	date		NOT NULL,
	end_date	date		NOT NULL,
	studyday	varchar2(20),
	studytime	varchar2(50),
	is_online	char(1)	CONSTRAINT course_is_online_CK CHECK(is_online IN ('0','1')),
	location	varchar2(100),
	is_group	char(1)	DEFAULT 0   CONSTRAINT course_is_group_CK CHECK(is_group IN ('0','1'))		NOT NULL,
	min_student	number	DEFAULT 1,
	max_student	number,
	title	varchar2(200)		NOT NULL,
	classinfo_file	varchar2(100),
	status	varchar2(10)	DEFAULT 'matching'  CONSTRAINT course_status_CK CHECK(status IN ('matching','matched','studying','end'))	NOT NULL,
    classinfo varchar2(1000)
);

CREATE TABLE Matching (
	student_id	number	NOT NULL,
	course_id	number	NOT NULL,
    CONSTRAINT FK_Student_TO_Matching FOREIGN KEY (student_id) REFERENCES Student (id) ON DELETE CASCADE,
    CONSTRAINT FK_Course_TO_Matching FOREIGN KEY (course_id) REFERENCES Course (id) ON DELETE CASCADE,
    CONSTRAINT matching_id PRIMARY KEY(student_id,course_id)
);

CREATE TABLE Homework (
	id	number	PRIMARY KEY	NOT NULL,
	title	varchar2(200)		NOT NULL,
	content	varchar2(1000)		NOT NULL,
	filename	varchar2(1000),
	deadline	date		NOT NULL,
	course_id	number		NOT NULL,
    CONSTRAINT FK_Course_TO_Homework FOREIGN KEY (course_id) REFERENCES Course (id) ON DELETE CASCADE
);

CREATE TABLE Article (
	id	number	PRIMARY KEY	NOT NULL,
	title	varchar2(200)		NOT NULL,
	content	varchar2(4000)		NOT NULL,
	filename	varchar2(100),
	created_at	date	DEFAULT sysdate	NOT NULL,
	hits	number	DEFAULT 0	NOT NULL,
	user_id	number		NOT NULL,
    CONSTRAINT FK_STUDENT_TO_ARTICLE foreign key(user_id) references Student(id) ON DELETE CASCADE
);

CREATE TABLE CourseReview (
	id	number	PRIMARY KEY	NOT NULL,
	content	varchar2(4000)		NOT NULL,
	created_at	date	DEFAULT sysdate	NOT NULL,
	course_id	number		NOT NULL,
	teacher_id	number		NOT NULL,
	student_id	number		NOT NULL,
    CONSTRAINT FK_Course_TO_CourseReview FOREIGN KEY (course_id) REFERENCES Course (id) ON DELETE CASCADE,
    CONSTRAINT FK_Teacher_TO_CourseReview FOREIGN KEY (teacher_id) REFERENCES Teacher (id) ON DELETE CASCADE,
    CONSTRAINT FK_Student_TO_CourseReview FOREIGN KEY (student_id) REFERENCES Student (id) ON DELETE CASCADE
);

CREATE TABLE StudentReview (
	id	number	PRIMARY KEY	NOT NULL,
	content	varchar2(4000)		NOT NULL,
	created_at	date	DEFAULT sysdate	NOT NULL,
	course_id	number		NOT NULL,
	teacher_id	number		NOT NULL,
	student_id	number		NOT NULL,
    CONSTRAINT FK_Course_TO_StudentReview FOREIGN KEY (course_id) REFERENCES Course (id) ON DELETE CASCADE,
    CONSTRAINT FK_Teacher_TO_StudentReview FOREIGN KEY (teacher_id) REFERENCES Teacher (id) ON DELETE CASCADE,
    CONSTRAINT FK_Student_TO_StudentReview FOREIGN KEY (student_id) REFERENCES Student (id) ON DELETE CASCADE
);

CREATE TABLE HomeworkAnswer (
	filename	varchar2(100),
	created_at	date	DEFAULT sysdate	NOT NULL,
	student_id	number		NOT NULL,
	homework_id	number		NOT NULL,
    content varchar2(1000),
    CONSTRAINT FK_Student_TO_HomeworkAnswer FOREIGN KEY (student_id) REFERENCES Student (id) ON DELETE CASCADE,
    CONSTRAINT FK_Homework_TO_HomeworkAnswer FOREIGN KEY (homework_id) REFERENCES Homework (id) ON DELETE CASCADE,
    CONSTRAINT homework_answer_id PRIMARY KEY(student_id,homework_id)
    
);

CREATE TABLE Admin (
	id	number	PRIMARY KEY	NOT NULL,
	email	varchar2(50)	UNIQUE	NOT NULL,
	password	varchar2(20)		NOT NULL
);

CREATE TABLE HighCategory (
	id	number	PRIMARY KEY	NOT NULL,
	name	varchar2(20)	UNIQUE	NOT NULL
);

CREATE TABLE LowCategory (
	id	number	PRIMARY KEY	NOT NULL,
	high_category_id	number		NOT NULL,
	name	varchar2(50)	UNIQUE	NOT NULL,
    CONSTRAINT FK_HighCategory_TO_LowCategory FOREIGN KEY (high_category_id) REFERENCES HighCategory (id) ON DELETE CASCADE
);

CREATE TABLE Reply (
	id	number	PRIMARY KEY	NOT NULL,
	content	varchar2(4000)		NOT NULL,
	created_at	date	DEFAULT sysdate	NOT NULL,
	user_id	number		NOT NULL,
	article_id	number		NOT NULL,
    CONSTRAINT FK_Article_TO_Reply FOREIGN KEY (article_id) REFERENCES Article (id) ON DELETE CASCADE,
    CONSTRAINT FK_TEACHER_TO_REPLY foreign key(user_id) references Teacher(id) ON DELETE CASCADE
);


-- Create sequence
create sequence AdminIdSeq start with 100000;
create sequence StudentIdSeq start with 200000;
create sequence TeacherIdSeq start with 300000;
create sequence CourseIdSeq start with 1;
create sequence HighCategoryIdSeq start with 1;
create sequence LowCategoryIdSeq start with 1;
create sequence CourseReviewIdSeq start with 1;
create sequence StudentReviewIdSeq start with 1;
create sequence HomeworkIdSeq start with 1;
create sequence NotificationIdSeq start with 1;
create sequence ArticleIdSeq start with 1;
create sequence ReplyIdSeq start with 1;


-- Insert category data
insert into HighCategory values (HighCategoryIdSeq.nextval, '�ֿ����');
insert into HighCategory values (HighCategoryIdSeq.nextval, '��ü��');
insert into HighCategory values (HighCategoryIdSeq.nextval, '���');
insert into HighCategory values (HighCategoryIdSeq.nextval, '����');
insert into HighCategory values (HighCategoryIdSeq.nextval, '��ȸ �� �ڰ���');

insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '��ȸ');
insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 2, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 2, 'ü��');
insert into LowCategory values (LowCategoryIdSeq.nextval, 2, '�̼�');
insert into LowCategory values (LowCategoryIdSeq.nextval, 2, '����');
insert into LowCategory values (LowCategoryIdSeq.nextval, 3, '���� ���');
insert into LowCategory values (LowCategoryIdSeq.nextval, 3, '�Խ� ���');
insert into LowCategory values (LowCategoryIdSeq.nextval, 4, 'TEPS/TOEFL/TOEIC');
insert into LowCategory values (LowCategoryIdSeq.nextval, 4, 'JPT/JLPT');
insert into LowCategory values (LowCategoryIdSeq.nextval, 4, 'HSK');
insert into LowCategory values (LowCategoryIdSeq.nextval, 4, '��Ÿ��2�ܱ���');
insert into LowCategory values (LowCategoryIdSeq.nextval, 5, '�ø��ǾƵ�');
insert into LowCategory values (LowCategoryIdSeq.nextval, 5, 'IT/��ǻ��');
insert into LowCategory values (LowCategoryIdSeq.nextval, 5, '�ѱ���ɷ°�������');
insert into LowCategory values (LowCategoryIdSeq.nextval, 5, '��Ÿ ��ȸ �� �ڰ���');
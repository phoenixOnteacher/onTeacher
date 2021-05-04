# Oracle SQL

> DB 구축을 위한 SQL 파일을 만들면서 알게 된 것들을 정리합니다

## DDL

### alter table

#### 컬럼 수정

- 컬럼의 `타입` 변경

  ```sql
  alter table teacher modify message varchar2(100);
  ```

  teacher 테이블의 message 필드의 타입을 varchar2(100)으로 바꿈

#### 컬럼 삭제

```sql
ALTER TABLE 테이블명 DROP COLUMN 컬럼명
```



## Constraint (제약조건)

```sql
(필드 이름)	(타입)	CONSTRAINT (제약조건 테이블 이름) (제약조건)
```

- 제약조건 테이블 이름은 `(테이블이름)_(필드이름)_(제약조건)` 이렇게 쓰는 경우가 많은 듯?
- `constraint` 없이 바로 쓸 수 있으며, 이 경우 제약조건의 이름이 자동으로 설정됨

  ```sql
  (필드 이름)	(타입)	(제약조건)
  ```

  - 제약조건을 관리(ex. PK 변경)할 때, 이름을 지정해주면 해당 제약조건을 쉽게 찾을 수 있으므로 `constraint`를 권장

### boolean

- oracle에는 `boolean` 타입이 없으므로 CHECK 제약조건 이용

  ```sql
  "active"	char(1)	DEFAULT 0	CONSTRAINT teacher_active_CK CHECK("active" IN ('0','1'))
  ```

### FK

```sql
CONSTRAINT (제약조건 테이블 이름) FOREIGN KEY (FK를 지정할 필드 이름) REFERENCES (참조 테이블 이름) (참조 필드)
```



```sql
CREATE TABLE Student (
    "id"	(type)
    ...
)

CREATE TABLE Matching (
    ...
    "student_id"	(type)
	CONSTRAINT "FK_Student_TO_Matching" FOREIGN KEY ("student_id") REFERENCES "Student" ("id")
)
```

- Matching 테이블의 student_id 필드를 FK로 설정하고, Student의 id 필드 참조

#### 참조되는 데이터가 삭제될 경우 처리

FK 제약조건 뒤에 덧붙여서 설정해줄 수 있음

- ON DELETE **CASCADE** : 그 값을 참조하는 자식 테이블의 데이터들도 같이 삭제됨
- ON DELETE **SET NULL** : 자식 테이블의 값을 NULL로 설정



### PK 여러 개 지정

```sql
CREATE TABLE (테이블 이름) (
    필드1 타입, 
    필드2 타입,
    ...
    CONSTRAINT (기본키 이름) PRIMARY KEY(필드1, 필드2)
);
```



### 제약조건을 삭제하고 실행

STUDENT 테이블의 키를 다른 테이블에서 참조하고 있어 `DROP TABLE` 명령만으로는 삭제할 수가 없었고, 아래와 같이 `CASCADE CONSTRAINTS`를 덧붙여 실행하니 제대로 삭제됨

```sql
DROP TABLE student CASCADE CONSTRAINTS;
```

`CASCADE CONSTRAINTS`는 자신의 키를 참조하는 테이블의 제약조건을 삭제하고 명령을 실행함



### 제약조건 추가

테이블이 생성된 후 제약조건을 추가하고 싶을 때, 아래와 같이 작성하여 실행

```sql
ALTER TABLE (테이블 이름)
ADD [CONSTRAINT (제약조건 이름)]
(제약조건) (필드 이름);
```

학생이 게시물을 올리고, 선생님이 댓글을 다는 방식의 게시판을 만들기로 결정하여, 이미 생성된 Article, Reply 테이블에 FK 설정을 추가함

```sql
alter table Article add constraint FK_STUDENT_TO_ARTICLE foreign key(user_id) references Student(id);
alter table Reply add constraint FK_TEACHER_TO_REPLY foreign key(user_id) references Teacher(id);
```



### 제약조건 변경

제약조건을 변경할 수는 없고, **삭제한 후 새로 추가**해야 함

```sql
alter table teacher drop constraint TEACHER_GENDER_CK;
alter table teacher add constraint TEACHER_GENDER_CK check (gender in ('남','여'));
```





## Sequene

> 순차적으로 자동 증가하는 번호

Admin, Student, Teacher, Course, HighCategory, LowCategory, CourseReview, StudentReview, Homework, Notification 테이블의 id값을 순차적으로 주기로 함

### create sequence

```sql
create sequence (시퀀스 이름);
```

뒤에 여러 옵션을 덧붙일 수 있는데, 우리는 시퀀스 생성이 시작되는 값을 설정하기 위해 `start with`을 이용함

User 계정은 Admin, Student, Teacher로 나뉘지만, 구분할 수 있게 하기 위해 Admin은 100000, Student는 200000, Teacher는 300000부터 시작

```sql
create sequence AdminIdSeq
start with 100000;

create sequence StudentIdSeq
start with 200000;

create sequence TeacherIdSeq
start with 300000;
```

### nextval

데이터 생성할 때 SQL문은 시퀀스의 `nextval`을 이용해 아래와 같이 작성

```sql
insert into 테이블이름 values ((해당 시퀀스이름).nextval, 값1, 값2, ...);
```

:eyes: ​​예시

```sql
insert into LowCategory values (LowCategoryIdSeq.nextval, 1, '국어');
```

- LowCategory의 필드는 id, high_category_id(HighCategory의 id), name로 구성되어 있음
- LowCategoryIdSeq는 1부터 시작하는 시퀀스



## :bulb: 문제 해결

### 테이블이 존재하지 않음

1. 문제 발생 : insert 하려고 하니, 테이블이 존재하지 않는다고 함
2. 문제가 발생한 이유
   - ERDCloud에서 SQL을 가져올 때, 테이블명과 필드명에 큰따옴표가 붙어있었고, 큰따옴표를 붙이면 대소문자 구분이 됨
   - select나 insert 등 명령어를 실행할 때 테이블에 큰따옴표를 붙이지 않아, 테이블이 존재하지 않는다는 에러가 발생했고, 큰따옴표를 붙이면 제대로 실행됨
3. 해결 : 테이블 전부 수정 (선생님의 조언에 따라 테이블명과 필드명에서 큰따옴표 제거)
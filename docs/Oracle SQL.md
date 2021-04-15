# Oracle SQL

> DB 구축을 위한 SQL 파일을 만들면서 알게 된 것들을 정리합니다



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
# 개발 표준 정의서



#### **개요**

프로젝트의  개발 시 개발생산성 향상 및 운영의 효율화를 위해 반드시 준수되어야 하는 준수사항을 정의함



- 제시된 표준은 철저히 준수한다.

- 표준을 준수하지 못할 경우에는 PM에게 통보

- 변수명, 메소드명 만들 때는 직관적이고 다른 팀원이 봐도 무엇인지 알게끔 작성. 주석으로 간단히 설명.



#### **본문 구성**

1. 네이밍
2. 코딩 규칙
3. 기타





## 1. 네이밍

모든 식별자는 영문 대소문자와 숫자만 사용



#### 1-1. 클래스와 인터페이스명

반드시 명사를 사용하고 각 단어의 첫 문자를 대문자로 표시하되, 이름의 첫 문자는 소문자로 쓰는 Upper camel case에 준하여 작성. 

```
Examples:
    Customer
    SalesOrder
```

두문자어(acronym)도 같은 규칙을 사용.

```
XMLHTTPRequest  // NO!
XmlHttpRequest  // YES!

getCustomerID   // NO!
getCustomerId   // YES!

class HTML      // NO!
class Html      // YES!

String URL      // NO!
String url      // YES!

long ID         // NO!
long id         // YES!
```



#### 1-2. 패키지명

소문자만 사용. 8자 이내로 사용.

```
Examples:
    common
    core
    lang
```

복합단어를 사용하지 않는다.

```
Examples:
    subwayzone  // NO!
    zone.subway // YES!
```



#### 1-3. 그 외의 모든 식별자

필드 식별자, 변수, 메소드, 파라미터는 다음의 네이밍 규칙을 따른다.

첫 단어를 제외한 첫 글자는 대문자로 나머지는 소문자를 사용. 두문자어(acronym)는 대문자 사용하되 첫 단어인 경우는 모두 소문자를 사용. (Lower camel case)

```
Examples:
    customer
    salesOrder
    addToTotal()
    targetURL
    urlTarget
```



#### 1-4. 업무 Mapper Interface 메소드명 규칙

1.업무 Mapper 클래스의 메소드 명은 기능에 따라 다음과 같은 형태로 작성하여야 한다.

2.업무 Mapper : 데이터 구조 종속적인 단위로 개발

| **동사**      | **설명**                                        | **예**                       |
| ------------- | ----------------------------------------------- | ---------------------------- |
| insertXXX     | 한 건의 데이터를 생성하는 경우                  | void insertCode(Code)        |
| updateXXX     | 한 건의 데이터를 변경하는 경우                  | void updateCode(Code)        |
| deleteXXX     | 한 건의 데이터를 삭제하는 경우                  | void deleteCode(Code)        |
| selectXXX     | 한 건의 데이터를 조회하는 경우                  | CodeDTO selectCode(CodeDto)  |
| selectXXXList | 여러 건의 데이터를 조회하는 경우(List<DTO>리턴) | List selectCodeList(CodeDto) |
| updateXXXList | 여러 건의 데이터를 변경하는 경우                | void updateCodeList(Code...) |
| deleteXXXList | 여러 건의 데이터를 삭제하는 경우                | int deleteCodeList(Code...)  |
| insertXXXList | 여러 건의 데이터를 생성하는 경우                | void insertCodeList(Code...) |
| saveXXXList   | 여러 건의 데이터를 생성, 변경, 삭제 하는 경우   | void saveCodeList(Code...)   |



#### 1-5. 업무 SQL MAP query id명 규칙

1.업무 SQL MAP 파일 내 query Id 부여 방식은 다음과 같다.

2.업무 SQL MAP 파일 : 업무 Mapper Interface와 1:1로 매핑 ( e.g.CodeMapper.java → CodeMapper.xml )

| **동사**  | **예**                                                       |
| --------- | ------------------------------------------------------------ |
| insertXXX | <insert id="deleteCode" parameterType=""> </insert>          |
| updateXXX | <update id="updateCode" parameterType="" resultType=""> </update> |
| deleteXXX | <delete id="deleteCode" parameterType=""> </delete>          |
| selectXXX | <select id="selectCode" parameterType="" resultType=""> </select> |





## 2. 코딩 규칙



#### 클래스 멤버 정렬 순서

클래스 멤버는 필드(전역변수), 생성자, 메소드 순서로 정렬한다.

```
    class Order
    {
        // fields (attributes)

        // constructors

        // methods
    }
```



#### 초기화

변수가 사용되는 곳의 가까운 위치에 변수를 선언.

```
Examples:
    int secondWide = 12;
    int firstWide = doFoo(20, secondWide);
    doBar(firstWide, secondWide);
    int totalWide = firstWide + secondWide;
```



#### 접근

상수를 제외한 모든 필드는 private 해야 한다.



#### boolean 변수와 메소드

-  일반적으로는 is 접두사를 불리언 변수와 메소드에 사용한다.
ex) isSet, isVisible, isFinished, isFound, isOpen
-  일부 상황에서는 is 접두사가 아닌 보다 더 적합한 접두사를 사용할 수도 있습니다. has, can, should 접두사들을 그런 대안으로 활용하실 수 있을 것입니다.
  ex)
  boolean hasLicense();
  boolean canEvaluate(); 
  boolean shouldAbort = false;





## 3. 기타

- 수정사항 있을 때 클래스다이어그램과 ERD에 업데이트. 팀 협업을 위해.



#### 참고 사이트

http://developer.gaeasoft.co.kr/development-guide/java-guide/java-coding-style-guide/

https://gist.github.com/muyongseja/5da60ba519797449bd3306c13f93dec8

https://shlee0882.tistory.com/129
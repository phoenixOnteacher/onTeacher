# 👩‍💻 Front-End 지침

## 1. 용어 설명

- id
  - id 선택자는 고유성을 가지므로 유일한 요소에만 적용한다.
  - id 선택자의 스타일 설정은 **class 선택자의 스타일 설정보다 앞서서 적용**된다.
  - id 선택자를 js 또는 css 파일에서 사용할 때에는 `#id명`으로 사용해야 한다.
- class
  - 복수의 요소에 스타일 설정을 적용할 때 사용한다.
  - class 선택자를 js 또는 css 파일에서 사용할 때에는 `.class명`으로 사용해야 한다.

## 2. template.jsp

### 구성  및 역할

- head
- body
  - #wrap
    - #header: 글로벌 네비게이션바 전용(`header.jsp`)
    - #contents: 각 jsp파일(페이지)을 보여줄 콘텐츠바디 부분
  - #footer: footer 전용(`footer.jsp`)

## 3. header.jsp

### 구성 및 역할

- head (생략되어 있음)
- body (생략되어 있음)
  - #h_wrap
    - nav: 글로벌 네비게이션바
      - #logo: ON TEACHER 로고
      - #navbar_menu: 글로벌 네비게이션바 메뉴 목록

## 4. footer.jsp

### 구성 및 역할

- head (생략되어 있음)
- body (생략되어 있음)
  - #f_wrap
    - #copyright: 저작권 표시

## 5. css와 js 파일 이용방법

### 5-1. 공통 파일

- 모든 css파일에 공통적으로 적용되는 사항은 `template.css`파일에 작성합니다.

  - 예시

    ```css
    a {
        text-decoration: none;
    }
    
    body {
        margin: 0;
    }
    ```

- 글로벌 네비게이션바는 `header.css`, footer는 `footer.css`를 이용합니다.

### 5-2. 파일 분리

- template.jsp의 #contents에 표시될 각 jsp파일들을 위한 css 또는 js 파일은 필요에 따라 새로 생성합니다.
- 새로 생성할 css 또는 js 파일명은 해당 스타일이 적용될 jsp파일명과 동일해야 합니다.
  - 예: template.jsp - template.css, index.jsp - index.css

### 5-3. 작성 유의사항

- jsp파일의 기본 div 컴포넌트는 전체 컴포넌트를 감싸는 wrapper와 그 안에 들어갈 content입니다.

- id명과 class명은 해당 컴포넌트의 기능이나 역할에서 따옵니다.

  - 단, #wrap이나 #content처럼 다른 css파일에도 동일하게 쓰이는 컴포넌트의 id와 class명은 앞에 구분글자를 최소 1개이상 넣어주세요.

  - 예시

    ```jsp
    <%--index.jsp파일--%>
    <div id="i_wrap">
    	<div id="i_content">
    		<img src="${path}/resources/img/index_img.png" />
    	</div>
    </div>
    ```

    ```jsp
    <%--footer.jsp파일--%>
    <div id="f_wrap">
    	<div id="copyright">
    		<p>Copyright ⓒ 2021 ON TEACHER. All rights reserved.</p>
    	</div>
    </div>
    ```

- 다른 팀원이 유의해야 하거나 참고할 사항은 반드시 주석으로 표시합니다.

## 6. CSS 색상코드

### 6-1. 로고, 글로벌 네비게이션바, 템플릿 하단 색상

```css
logo text {
    color: #FF8184;
    color: #FFCD4A;
}

global navbar basic {
    border-bottom: 1px solid #ededed;
}

global navbar scroll effect {
    background-color: #FBFAF1;
    border-bottom: 0.2px solid #FEDEC3;
}

footer {
    background-color: #FBAD38;
}
```

### 6-2. submit 버튼

```css
button.btn-primary {
	background-color: #E9ECEF;
	border-color: #CED4DA;
	color: #555555;
}

button.btn-primary:hover {
	background-color: #FF8184;
	border-color: #FF8184;
}
```

### 6-3. 컨텐츠 박스

```css
#wrap {
    border: 1px solid #ededed;
}
```

## 7. Bootstrap 활용

### 7-1. form-control

1. input

   ```jsp
   <input type="" class="form-control" />
   ```

2. select

   ```jsp
   <select class="form-select 크기에 따라 form-select-sm 또는 form-select-lg 등">
   	<option value=""></option>
   </select>
   ```

### 7-2. 각 구성요소들

1. alert 예제

   ```jsp
   <div class="alert alert-warning alert-dismissible fade show" role="alert">
   	<strong>로그인 실패!</strong> 아이디 혹은 비밀번호를 잘못 입력하셨습니다.
   	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
   </div>
   ```

2. button 예제

   ```jsp
   <button type="submit" class="btn btn-primary" id="login_btn">로그인</button>
   ```

3. button group 예제

   ```jsp
   <div class="btn-group" role="group" aria-label="Basic outlined example">
   	<a href="/student/join" class="btn btn-outline-primary" id="gotostjoin">학생 가입</a>
   	<a href="/teacher/join" class="btn btn-outline-primary" id="gotothjoin">선생님 가입</a>
   </div>
   ```

4. card 예제

   ```jsp
   <div class="row row-cols-1 row-cols-md-4 g-4">
   	<c:forEach var="course" items="${courses }">
   		<div class="col">
   			<div class="card">
   				<img src="${path}/thprofileupload/${course.teacher.profileImg}" class="card-img-top">
   					<div class="card-body">
   						<h5 class="card-title">${course.title }</h5>
   						<p class="card-text">${course.curriculum }</p>
   					</div>
   			</div>
   		</div>
   	</c:forEach>
   </div>
   ```

   
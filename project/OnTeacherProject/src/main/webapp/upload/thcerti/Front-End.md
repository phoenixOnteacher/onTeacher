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
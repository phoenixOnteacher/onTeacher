# π©βπ» Front-End μ§μΉ¨

## 1. μ©μ΄ μ€λͺ

- id
  - id μ νμλ κ³ μ μ±μ κ°μ§λ―λ‘ μ μΌν μμμλ§ μ μ©νλ€.
  - id μ νμμ μ€νμΌ μ€μ μ **class μ νμμ μ€νμΌ μ€μ λ³΄λ€ μμμ μ μ©**λλ€.
  - id μ νμλ₯Ό js λλ css νμΌμμ μ¬μ©ν  λμλ `#idλͺ`μΌλ‘ μ¬μ©ν΄μΌ νλ€.
- class
  - λ³΅μμ μμμ μ€νμΌ μ€μ μ μ μ©ν  λ μ¬μ©νλ€.
  - class μ νμλ₯Ό js λλ css νμΌμμ μ¬μ©ν  λμλ `.classλͺ`μΌλ‘ μ¬μ©ν΄μΌ νλ€.

## 2. template.jsp

### κ΅¬μ±  λ° μ­ν 

- head
- body
  - #wrap
    - #header: κΈλ‘λ² λ€λΉκ²μ΄μλ° μ μ©(`header.jsp`)
    - #contents: κ° jspνμΌ(νμ΄μ§)μ λ³΄μ¬μ€ μ½νμΈ λ°λ λΆλΆ
  - #footer: footer μ μ©(`footer.jsp`)

## 3. header.jsp

### κ΅¬μ± λ° μ­ν 

- head (μλ΅λμ΄ μμ)
- body (μλ΅λμ΄ μμ)
  - #h_wrap
    - nav: κΈλ‘λ² λ€λΉκ²μ΄μλ°
      - #logo: ON TEACHER λ‘κ³ 
      - #navbar_menu: κΈλ‘λ² λ€λΉκ²μ΄μλ° λ©λ΄ λͺ©λ‘

## 4. footer.jsp

### κ΅¬μ± λ° μ­ν 

- head (μλ΅λμ΄ μμ)
- body (μλ΅λμ΄ μμ)
  - #f_wrap
    - #copyright: μ μκΆ νμ

## 5. cssμ js νμΌ μ΄μ©λ°©λ²

### 5-1. κ³΅ν΅ νμΌ

- λͺ¨λ  cssνμΌμ κ³΅ν΅μ μΌλ‘ μ μ©λλ μ¬ν­μ `template.css`νμΌμ μμ±ν©λλ€.

  - μμ

    ```css
    a {
        text-decoration: none;
    }
    
    body {
        margin: 0;
    }
    ```

- κΈλ‘λ² λ€λΉκ²μ΄μλ°λ `header.css`, footerλ `footer.css`λ₯Ό μ΄μ©ν©λλ€.

### 5-2. νμΌ λΆλ¦¬

- template.jspμ #contentsμ νμλ  κ° jspνμΌλ€μ μν css λλ js νμΌμ νμμ λ°λΌ μλ‘ μμ±ν©λλ€.
- μλ‘ μμ±ν  css λλ js νμΌλͺμ ν΄λΉ μ€νμΌμ΄ μ μ©λ  jspνμΌλͺκ³Ό λμΌν΄μΌ ν©λλ€.
  - μ: template.jsp - template.css, index.jsp - index.css

### 5-3. μμ± μ μμ¬ν­

- jspνμΌμ κΈ°λ³Έ div μ»΄ν¬λνΈλ μ μ²΄ μ»΄ν¬λνΈλ₯Ό κ°μΈλ wrapperμ κ·Έ μμ λ€μ΄κ° contentμλλ€.

- idλͺκ³Ό classλͺμ ν΄λΉ μ»΄ν¬λνΈμ κΈ°λ₯μ΄λ μ­ν μμ λ°μ΅λλ€.

  - λ¨, #wrapμ΄λ #contentμ²λΌ λ€λ₯Έ cssνμΌμλ λμΌνκ² μ°μ΄λ μ»΄ν¬λνΈμ idμ classλͺμ μμ κ΅¬λΆκΈμλ₯Ό μ΅μ 1κ°μ΄μ λ£μ΄μ£ΌμΈμ.

  - μμ

    ```jsp
    <%--index.jspνμΌ--%>
    <div id="i_wrap">
    	<div id="i_content">
    		<img src="${path}/resources/img/index_img.png" />
    	</div>
    </div>
    ```

    ```jsp
    <%--footer.jspνμΌ--%>
    <div id="f_wrap">
    	<div id="copyright">
    		<p>Copyright β 2021 ON TEACHER. All rights reserved.</p>
    	</div>
    </div>
    ```

- λ€λ₯Έ νμμ΄ μ μν΄μΌ νκ±°λ μ°Έκ³ ν  μ¬ν­μ λ°λμ μ£ΌμμΌλ‘ νμν©λλ€.

## 6. CSS μμμ½λ

### 6-1. λ‘κ³ , κΈλ‘λ² λ€λΉκ²μ΄μλ°, ννλ¦Ώ νλ¨ μμ

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

### 6-2. submit λ²νΌ

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

### 6-3. μ»¨νμΈ  λ°μ€

```css
#wrap {
    border: 1px solid #ededed;
}
```

## 7. Bootstrap νμ©

### 7-1. form-control

1. input

   ```jsp
   <input type="" class="form-control" />
   ```

2. select

   ```jsp
   <select class="form-select ν¬κΈ°μ λ°λΌ form-select-sm λλ form-select-lg λ±">
   	<option value=""></option>
   </select>
   ```

### 7-2. κ° κ΅¬μ±μμλ€

1. alert μμ 

   ```jsp
   <div class="alert alert-warning alert-dismissible fade show" role="alert">
   	<strong>λ‘κ·ΈμΈ μ€ν¨!</strong> μμ΄λ νΉμ λΉλ°λ²νΈλ₯Ό μλͺ» μλ ₯νμ¨μ΅λλ€.
   	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
   </div>
   ```

2. button μμ 

   ```jsp
   <button type="submit" class="btn btn-primary" id="login_btn">λ‘κ·ΈμΈ</button>
   ```

3. button group μμ 

   ```jsp
   <div class="btn-group" role="group" aria-label="Basic outlined example">
   	<a href="/student/join" class="btn btn-outline-primary" id="gotostjoin">νμ κ°μ</a>
   	<a href="/teacher/join" class="btn btn-outline-primary" id="gotothjoin">μ μλ κ°μ</a>
   </div>
   ```

4. card μμ 

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

   
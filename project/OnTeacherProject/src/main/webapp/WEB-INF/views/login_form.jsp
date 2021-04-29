<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/login_form.css" />
<div id="l_wrap">
	<c:if test="${msg == false }">
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>로그인 실패!</strong> 아이디 혹은 비밀번호를 잘못 입력하셨습니다.
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<div id="l_form_wrap">
		<div id="l_title_wrap">
			<div id="l_title">로그인</div>
		</div>
		<form action="./login" method="post" id="loginForm">
			<table>
				<tr>
					<td><input type="text" name="email" id="login_email"
						placeholder="아이디 (이메일)" class="form-control" /></td>
				</tr>
				<tr>
					<td><input type="password" name="password" id="login_pass"
						placeholder="비밀번호" class="form-control" /></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary" id="login_btn">로그인</button>
		</form>
		<div id="gotojoin">아직 회원이 아니신가요?</div>
		<div class="btn-group" role="group"
			aria-label="Basic outlined example">
			<a href="/student/join" class="btn btn-outline-primary" id="gotostjoin">학생 가입</a>
			<a href="/teacher/join" class="btn btn-outline-primary" id="gotothjoin">선생님 가입</a>
		</div>
	</div>
</div>
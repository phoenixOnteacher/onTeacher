<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="./afterlogin" method="post" id="login">
	<h3>[ 로그인 ]</h3>
	<table>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" id="login_email" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password" id="login_pass" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="로그인" /></td>
		</tr>
	</table>
</form>
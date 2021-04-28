<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>[ 선생님회원가입 입력 ]</h3>
<form action="./thjoin" method="post">
<table border="1">
	<tr>
		<td><label for = "email">이메일 : </label></td>
		<td><input type="text" name="email" id = "email"></td>
	</tr>
	<tr>
		<td><label for = "name">이름 : </label></td>
		<td><input type="text" name="name" id = "name"></td>
	</tr>
	<tr>
		<td><label for = "pass">비밀번호 : </label></td>
		<td><input type="password" name="password" id = "password"></td>
	</tr>
	<tr>
		<td><label for = "phone_number">전화번호 : </label></td>
		<td><input type="tel" name="phone_number" id = "phone_number"></td>
	</tr>
	<tr>
		<td><label for = "grade">나이 : </label></td>
		<td><input type="text" name="grade" id = "grade"></td>
	</tr>
	<tr>
		<td><label for = "address">주소 : </label></td>
		<td><input type="text" name="address" id = "address"></td>
	</tr>
	<tr>
		<td><label for = "birthday">생일 : </label></td>
		<td><input type="date" name="birthday" id = "birthday" required pattern="\d{4}/\d{2}/\d{2}"></td>
	</tr>
	<tr>
		<td><label for = "gender">성별 : </label></td>
		<td>
			<input type="radio" name="gender" value="남" id = "gender" checked>남
			<input type="radio" name="gender" value="여">여
		</td>
	</tr>
	<tr>
		<td><label for = "img">프로필사진 : </label></td>
		<td><input type="image" name="img" id = "profile_img"></td>
	</tr>
	<tr>
		<td><label for = "introduction">자기소개 : </label></td>
		<td><input type="text" name="introduction" id = "introduction"></td>
	</tr>
	<tr>
		<td><label for = "filename">자격파일 : </label></td>
		<td><input type="file" name="filename" id = "filename"></td>
	</tr>
	<tr>
		<td><label for = "description">자격파일설명 : </label></td>
		<td><input type="text" name="description" id = "description"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="submit" value="가입">
			<input type="reset" value="다시 작성">
		</td>
	</tr>
</table>
</form>
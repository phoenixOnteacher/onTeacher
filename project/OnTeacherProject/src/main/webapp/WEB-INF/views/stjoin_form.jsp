<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/stjoin_form.css" />
<div id="stj_wrap">
	<div id="stj_title_wrap">
		<div id="stj_title">회원정보</div>
	</div>
	<form action="/student/join" method="post"
		enctype="multipart/form-data" id="stJoinForm">
		<table>
			<tr>
				<td class="thead"><label for="email">이메일</label></td>
				<td class="tbody"><input type="email" name="email" id="email" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="name">이름 : </label></td>
				<td class="tbody"><input type="text" name="name" id="name" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="password">비밀번호 : </label></td>
				<td class="tbody"><input type="password" name="password" id="password" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="phoneNumber">전화번호 : </label></td>
				<td class="tbody"><input type="tel" name="phoneNumber" id="phoneNumber" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="grade">나이 : </label></td>
				<td class="tbody"><input type="text" name="grade" id="grade" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="address">주소 : </label></td>
				<td class="tbody"><input type="text" name="address" id="address" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="birthday">생일 : </label></td>
				<td class="tbody"><input type="date" name="birthday" id="birthday" required
					pattern="\d{4}/\d{2}/\d{2}" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="gender">성별 : </label></td>
				<td class="tbody"><input type="radio" name="gender" value="남" id="gender"
					checked>남 <input type="radio" name="gender" value="여">여
				</td>
			</tr>
			<tr>
				<td class="thead"><label for="file">프로필사진 : </label></td>
				<td class="tbody"><input type="file" name="file" id="profileImg" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="introduction">소개 : </label></td>
				<td class="tbody"><input type="text" name="introduction" id="introduction" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"></td>
				<td class="tbody"><input type="submit" value="가입"> <input
					type="reset" value="다시 작성"></td>
			</tr>
		</table>
	</form>
</div>
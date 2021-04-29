<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/stjoin_form.css" />
<div id="stj_wrap">
	<div id="stj_title_wrap">
		<div id="stj_title">
			학생 회원으로 가입하고<br> 온티처의 따뜻한 무료교육을 신청해보세요.
		</div>
	</div>
	<form action="/student/join" method="post"
		enctype="multipart/form-data" id="stJoinForm">
		<table>
			<tr>
				<td class="thead"><label for="email">이메일</label></td>
				<td class="tbody"><input type="email" name="email" id="email"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="name">이름</label></td>
				<td class="tbody"><input type="text" name="name" id="name"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="password">비밀번호</label></td>
				<td class="tbody"><input type="password" name="password"
					id="password" class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="phoneNumber">전화번호</label></td>
				<td class="tbody"><input type="tel" name="phoneNumber"
					id="phoneNumber" class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="grade">학년</label></td>
				<td class="tbody"><input type="text" name="grade" id="grade"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="address">주소</label></td>
				<td class="tbody"><input type="text"
					placeholder="시/도 시/군/구 (예: 서울시 동작구)" name="address" id="address"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="birthday">생일</label></td>
				<td class="tbody"><input type="date" name="birthday"
					id="birthday" required pattern="\d{4}/\d{2}/\d{2}"
					class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="gender">성별</label></td>
				<td class="tbody"><input type="radio" name="gender" value="남"
					id="gender" required="required">남 <input type="radio"
					name="gender" value="여">여</td>
			</tr>
			<tr>
				<td class="thead"><label for="file">프로필사진</label></td>
				<td class="tbody"><input type="file" name="file"
					id="profileImg" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="introduction">간단한 자기소개</label></td>
				<td class="tbody"><input type="text" name="introduction"
					id="introduction" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"></td>
				<td class="tbody"><button type="reset" class="btn btn-primary">다시
						작성하기</button>
					<button type="submit" class="btn btn-primary">가입하기</button></td>
			</tr>
		</table>
	</form>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/thjoin_form.css" />
<script src="${path}/resources/js/thjoin_form.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div id="thj_wrap">
	<div id="thj_title_wrap">
		<div id="thj_title">
			선생님 회원으로 가입하고<br> 온티처에서 교육봉사의 꿈을 펼쳐보세요.
		</div>
	</div>
	<form action="/teacher/join" method="post"
		enctype="multipart/form-data" id="thJoinForm">
		<table>
			<tr>
				<td class="thead"><label for="email">이메일</label></td>
				<td class="tbody"><input type="email" name="email" id="email"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="pass">비밀번호</label></td>
				<td class="tbody"><input type="password" name="password" placeholder="8자리 이상. 영문, 숫자, 특수문자 중 2가지 이상 조합"
					id="password" class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="name">이름</label></td>
				<td class="tbody"><input type="text" name="name" id="name"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="phoneNumber">전화번호</label></td>
				<td class="tbody"><input type="tel" name="phoneNumber"
					id="phoneNumber" placeholder="(예: 010-1234-5678)"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="address">주소</label></td>
				<td class="tbody"><input type="text" name="address"
					id="address" placeholder="시/도 시/군/구 (예: 서울시 동작구, 경북 포항시)"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="birthday">생일</label></td>
				<td class="tbody"><input type="date" name="birthday"
					id="birthday" required pattern="\d{4}/\d{2}/\d{2}"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="gender">성별</label></td>
				<td class="tbody"><input type="radio" name="gender" value="남"
					id="gender" required="required">&nbsp;남&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="gender" value="여">&nbsp;여&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="thead"><label for="file">프로필사진</label></td>
				<td class="tbody"><input type="file" name="file"
					id="profileImg" class="form-control"></td>
			</tr>
			<tr>
				<td class="thead"><label for="introduction">간단한<br>자기소개
				</label></td>
				<td class="tbody"><textarea name="introduction"
						id="introduction" class="form-control"></textarea></td>
			</tr>
		</table>
		<hr id="thj_line">
		<table>
			<tr>
				<td class="tbody" colspan="2">
					<div class="alert alert-warning" role="alert">
						온티처의 선생님 회원이 되기 위해서는 교육 관련 경험이나 경력 증빙 자료가 필수입니다.
						<hr>
						<strong>자격증빙가능 자료 종류</strong> <br>1. 교육 관련 경험 또는 경력 증명서<br>2.
						대학교 학생증 / 재학 증명서 / 휴학 증명서<br>3. 교직 이수 과목 수강 내역<br>4. 교육
						관련 전공 졸업 증명서
						<hr>
						이외의 기타 증빙 자료에 대해서도 설명과 함께 첨부해 주시면 검토 후 승인 결정을 알려드립니다.
					</div>
				</td>
			</tr>
			<tr>
				<td class="thead"><label for="file">자격증명</label></td>
				<td class="tbody"><input type="file" name="file" id="fileName"
					class="form-control" required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="description">자격증명에<br>관한
						설명
				</label></td>
				<td class="tbody"><textarea name="description" id="description"
						placeholder="첨부한 자격증명 파일에 관한 간단한 설명을 작성해주세요." class="form-control"
						required="required"></textarea></td>
			</tr>
		</table>
		<div id="thjoin_btn">
			<button type="submit" class="btn btn-primary" id="joinbtn">가입하기</button>
		</div>
	</form>
</div>
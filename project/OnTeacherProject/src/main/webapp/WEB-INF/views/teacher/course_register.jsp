<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/course_register.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/course_register.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<div id="cr_wrap">
	<div id="cr_title_wrap">
		<div id="cr_title">수업 등록</div>
	</div>
	<form action="./upload" method="post" id="courseRegForm"
		enctype="multipart/form-data">
		<input type="hidden" name="teacher_id" value="${teacher_id }" /> <input
			type="hidden" name="status" value="matching" />
		<table id="cr_table">
			<tr>
				<td class="thead"><label for="highcategory">수업 분류</label></td>
				<td class="tbody"><select required="required" id="highcategory"
					name="highCategoryId" class="form-select form-select-sm">
						<option value="">상위 분류 선택</option>
						<c:forEach var="high" items="${highCategory }">
							<option value="${high.id }">${high.name }</option>
						</c:forEach>
				</select> <select required="required" id="lowcategory" name="lowCategoryId"
					class="form-select form-select-sm">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">하위 분류 선택</option>
				</select>
			</tr>
			<tr>
				<td class="thead"><label for="target">수업 대상</label></td>
				<td class="tbody"><select required="required" id="target"
					name="target" class="form-select form-select-sm">
						<option value="">수업 대상 선택</option>
						<option value="초등">초등학생</option>
						<option value="중등">중학생</option>
						<option value="고등">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td class="thead"><label for="isOneday">수업 형태</label></td>
				<td class="tbody"><input type="radio" name="isOneday" value="1"
					id="isOneday" required>&nbsp;1일
					특강&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="isOneday" value="0" id="isOneday">&nbsp;다회차
					수업</td>
			</tr>
			<tr>
				<td class="thead"><label for="studyDate">수업 일정</label></td>
				<td class="tbody"><p id="startday">시작일</p><p id="endday">종료일</p><br><input type="date" id="studyDate"
					name="startDate" min="2000-01-01" max="2999-12-31" required
					pattern="\d{2}-\d{2}-\d{2}" class="form-control"
					required="required"> <input type="date" id="studyDate"
					name="endDate" min="2000-01-01" max="2999-12-31" required
					pattern="\d{2}-\d{2}-\d{2}" class="form-control"
					required="required"></td>
			</tr>
			<tr>
				<td class="thead"><label for="studyDay">수업 요일</label></td>
				<td class="tbody"><input type="radio" name="studyDay"
					id="studyDay" value="weekdays" required>&nbsp;주중&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="studyDay" id="studyDay" value="weekend">&nbsp;주말&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="studyDay" id="studyDay" value="anytime">&nbsp;주중+주말</td>
			</tr>
			<tr>
				<td class="thead"><label for="studyTime">수업 시간</label></td>
				<td class="tbody"><input type="radio" name="studyTime"
					id="studyTime" value="morning" required>&nbsp;오전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="studyTime" id="studyTime" value="afternoon">&nbsp;오후&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="studyTime" id="studyTime" value="discuss">&nbsp;협의</td>
			</tr>
			<tr>
				<td class="thead"><label for="isOnline">수업 방식</label></td>
				<td class="tbody"><input type="radio" name="isOnline" value="1"
					id="isOnline" required>&nbsp;온라인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="isOnline" value="0" id="isOnline">&nbsp;오프라인&nbsp;&nbsp;<label
					for="location"></label><select required="required" name="location"
					disabled="disabled" id="sido" class="form-select form-select-sm">
						<option value="">시/도 선택</option>
						<option value="서울">서울</option>
						<option value="부산">부산</option>
						<option value="대구">대구</option>
						<option value="인천">인천</option>
						<option value="광주">광주</option>
						<option value="대전">대전</option>
						<option value="울산">울산</option>
						<option value="세종">세종</option>
						<option value="경기">경기</option>
						<option value="강원">강원</option>
						<option value="충북">충북</option>
						<option value="충남">충남</option>
						<option value="전북">전북</option>
						<option value="전남">전남</option>
						<option value="경북">경북</option>
						<option value="경남">경남</option>
						<option value="제주">제주</option>
				</select> <select required="required" name="location" disabled="disabled"
					id="sigungu" class="form-select form-select-sm">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">시/군/구 선택</option>
				</select></td>
			</tr>
			<tr>
				<td class="thead"><label for="isGroup">참여인원</label></td>
				<td class="tbody"><input type="radio" name="isGroup" value="0"
					id="isGroup" required>&nbsp;1:1수업&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" name="isGroup" value="1" id="isGroup">&nbsp;그룹수업&nbsp;&nbsp;<input
					name="minStudent" type="number" min="2" disabled="disabled"
					id="minStudent" class="form-control" placeholder="최소 인원" /> <input
					name="maxStudent" type="number" min="2" disabled="disabled"
					id="maxStudent" class="form-control" placeholder="최대 인원" /></td>
			</tr>
			<tr>
				<td class="thead"><label for="title">제목</label></td>
				<td class="tbody"><input name="title" type="text" id="title"
					required="required" class="form-control" /></td>
			</tr>
			<tr>
				<td class="thead"><label for="curriculum">수업 소개</label></td>
				<td class="tbody"><textarea id="summernote" name="curriculum"
						required="required"></textarea></td>
			</tr>
			<tr>
				<td class="thead"><label for="file">참고자료</label></td>
				<td class="tbody"><input name="file" type="file" id="file"
					class="form-control" /></td>
			</tr>
		</table>
		<div id="submit_btn">
			<button type="submit" class="btn btn-primary">등록하기</button>
		</div>
	</form>
</div>

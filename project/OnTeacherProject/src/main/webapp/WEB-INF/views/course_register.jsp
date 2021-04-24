<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%-- <link rel="stylesheet" href="${path}/resources/css/index.css" /> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/course_register.js"></script>
<div id="cr_wrap">
	<form action="./upload" method="post" id="courseRegForm"
		enctype="multipart/form-data">
		<input type="hidden" name="teacher_id" value="${teacher_id }" />
		<input type="hidden" name="status" value="matching" />
		<table border="1">
			<tr>
				<td>수업 카테고리</td>
				<td><select required="required" id="highcategory" name="highCategoryId">
						<option value="">선택해주세요</option>
						<c:forEach var="high" items="${highCategory }">
							<option value="${high.id }">${high.name }</option>
						</c:forEach>
				</select> <select required="required" id="lowcategory" name="lowCategoryId">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">선택해주세요</option>
				</select>
			</tr>
			<tr>
				<td><label for="target">수업 대상</label></td>
				<td><select required="required" id="target" name="target">
						<option value="">선택해주세요</option>
						<option value="초등학생">초등학생</option>
						<option value="중학생">중학생</option>
						<option value="고등학생">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="isOneday">수업 형태</label></td>
				<td><input type="radio" name="isOneday" value="0"
					id="isOneday">1일 특강 <input type="radio" name="isOneday"
					value="1" id="isOneday">다회차 수업</td>
			</tr>
			<tr>
				<td>수업 일정</td>
				<td><input type="date" id="studyDate" name="startDate"
					min="2000-01-01" max="2999-12-31" required pattern="\d{2}-\d{2}-\d{2}"> <input type="date"
					id="studyDate" name="endDate" min="2000-01-01" max="2999-12-31" required pattern="\d{2}-\d{2}-\d{2}"></td>
			</tr>
			<tr>
				<td><label for="studyDay">수업 요일</label></td>
				<td><input type="radio" name="studyDay" id="studyDay"
					value="weekdays">주중<input type="radio" name="studyDay"
					id="studyDay" value="weekend">주말<input type="radio"
					name="studyDay" id="studyDay" value="anytime">주중+주말</td>
			</tr>
			<tr>
				<td><label for="studyTime">수업 시간</label></td>
				<td><input type="radio" name="studyTime" id="studyTime"
					value="morning">오전<input type="radio" name="studyTime"
					id="studyTime" value="afternoon">오후<input type="radio"
					name="studyTime" id="studyTime" value="discuss">협의</td>
			</tr>
			<tr>
				<td><label for="isOnline">수업 방식</label></td>
				<td><input type="radio" name="isOnline" value="0"
					id="isOnline">온라인 <input type="radio" name="isOnline"
					value="1" id="isOnline">오프라인 <label for="location"></label><select
					required="required" name="location" disabled="disabled" id="sido">
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
				</select> <select required="required" name="location" disabled="disabled" id="sigungu">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">시/군/구 선택</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="isGroup">참여인원</label></td>
				<td><input type="radio" name="isGroup" value="1"
					id="isGroup">1:1수업 <input type="radio" name="isGroup"
					value="0" id="isGroup">그룹수업<input name="minStudent"
					type="number" min="2" disabled="disabled" /> <input
					name="maxStudent" type="number" min="2" disabled="disabled" /></td>
			</tr>
			<tr>
				<td><label for="title">제목</label></td>
				<td><input name="title" type="text" id="title"
					required="required" /></td>
			</tr>
			<tr>
				<td><label for="curriculum">수업 소개</label></td>
				<td><textarea id="curriculum" name="curriculum" cols="40"
						rows="15" required="required"></textarea></td>
			</tr>
			<tr>
				<td>참고자료</td>
				<td><input name="file" type="file"
					id="file" /></td>
			</tr>
			<tr>
				<td><button type="submit">등록하기</button></td>
			</tr>
		</table>
	</form>
</div>

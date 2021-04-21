<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%-- <link rel="stylesheet" href="${path}/resources/css/index.css" /> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/course_register.js"></script>
<div id="cr_wrap">
	<form action="#" method="post" id="courseRegForm">
		<table border="1">
			<tr>
				<td><label for="highcategory">수업 카테고리</label></td>
				<td><select required="required" id="highcategory">
						<option value="">선택해주세요</option>
						<c:forEach var="high" items="${highCategory }">
							<option value="${high.id }">${high.name }</option>
						</c:forEach>
				</select> <select required="required" id="lowcategory">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">선택해주세요</option>
				</select>
			</tr>
			<tr>
				<td><label for="target">수업 대상</label></td>
				<td><select required="required" id="target">
						<option value="">선택해주세요</option>
						<option value="초등학생">초등학생</option>
						<option value="중학생">중학생</option>
						<option value="고등학생">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="classform">수업 형태</label></td>
				<td><input type="radio" name="classform" value="oneday"
					id="classform">1일 특강 <input type="radio" name="classform"
					value="multi" id="classform">다회차 수업</td>
			</tr>
			<tr>
				<td><label for="classdate">수업 일정</label></td>
				<td><input type="date" id="classdate" name="classstart"
					min="2000-01-01" max="2999-12-31"> <input type="date"
					id="classdate" name="classend" min="2000-01-01" max="2999-12-31"></td>
			</tr>
			<tr>
				<td><label for="isweekend">수업 요일</label></td>
				<td><input type="radio" name="isweekend" id="isweekend"
					value="weekdays">주중<input type="radio" name="isweekend"
					id="isweekend" value="weekend">주말<input type="radio"
					name="isweekend" id="isweekend" value="anytime">주중+주말</td>
			</tr>
			<tr>
				<td><label for="ismorning">수업 시간</label></td>
				<td><input type="radio" name="ismorning" id="ismorning"
					value="morning">오전<input type="radio" name="ismorning"
					id="ismorning" value="afternoon">오후<input type="radio"
					name="ismorning" id="ismorning" value="discuss">협의</td>
			</tr>
			<tr>
				<td><label for="isonline">수업 방식</label></td>
				<td><input type="radio" name="isonline" value="online"
					id="isonline">온라인 <input type="radio" name="isonline"
					value="offline" id="isonline">오프라인 <select
					required="required" name="sido" disabled="disabled">
						<option value="">시/도 선택</option>
						<option value="seoul">서울</option>
						<option value="busan">부산</option>
						<option value="deagu">대구</option>
						<option value="incheon">인천</option>
						<option value="gwangju">광주</option>
						<option value="daejeon">대전</option>
						<option value="ulsan">울산</option>
						<option value="sejong">세종</option>
						<option value="gyeonggi">경기</option>
						<option value="gangwon">강원</option>
						<option value="chungbuk">충북</option>
						<option value="chungnam">충남</option>
						<option value="jeonbuk">전북</option>
						<option value="jeonnam">전남</option>
						<option value="gyeongbuk">경북</option>
						<option value="gyeongnam">경남</option>
						<option value="jeju">제주</option>
				</select> <select required="required" name="sigungu" disabled="disabled">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">시/군/구 선택</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="isgroup">참여인원</label></td>
				<td><input type="radio" name="isgroup" value="onetoone"
					id="isgroup">1:1수업 <input type="radio" name="isgroup"
					value="group" id="isgroup">그룹수업<input name="mingroup"
					type="number" min="2" disabled="disabled" /> <input name="maxgroup"
					type="number" min="2" disabled="disabled" /></td>
			</tr>
			<tr>
				<td><label for="title">제목</label></td>
				<td><input name="title" type="text" id="title"
					required="required" /></td>
			</tr>
			<tr>
				<td><label for="classintro">수업 소개</label></td>
				<td><textarea id="classintro" name="classintro" cols="40"
						rows="15" required="required"></textarea></td>
			</tr>
			<tr>
				<td><label for="classinfofile">참고자료</label></td>
				<td><input name="classinfofile" type="file" id="classinfofile" /></td>
			</tr>
			<tr>
				<td><button type="submit">등록하기</button></td>
			</tr>
		</table>
	</form>
</div>

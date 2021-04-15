<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%-- <link rel="stylesheet" href="${path}/resources/css/index.css" /> --%>
<div id="cr_wrap">
	<form action="#" method="post">
		<table border="1">
			<tr>
				<td>수업 카테고리</td>
				<td><select required="required">
						<option value="주요과목" id="highcategory">주요과목</option>
						<option value="예체능" id="highcategory">예체능</option>
						<option value="상담" id="highcategory">상담</option>
						<option value="어학" id="highcategory">어학</option>
						<option value="대회 및 자격증" id="highcategory">대회 및 자격증</option>
				</select> <select required="required">
						<option value="국어" id="lowcategory">국어</option>
						<option value="영어" id="lowcategory">영어</option>
						<option value="수학" id="lowcategory">수학</option>
						<option value="사회" id="lowcategory">사회</option>
						<option value="과학" id="lowcategory">과학</option>
				</select> <select required="required">
						<option value="음악" id="lowcategory">음악</option>
						<option value="체육" id="lowcategory">체육</option>
						<option value="미술" id="lowcategory">미술</option>
						<option value="무용" id="lowcategory">무용</option>
				</select> <select required="required">
						<option value="진로상담" id="lowcategory">진로 상담</option>
						<option value="직업체험" id="lowcategory">직업 체험</option>
						<option value="입시상담" id="lowcategory">입시 상담</option>
				</select> <select required="required">
						<option value="ENG" id="lowcategory">TEPS/TOEFL/TOEIC</option>
						<option value="JPN" id="lowcategory">JPT/JLPT</option>
						<option value="CHN" id="lowcategory">HSK</option>
						<option value="ETC" id="lowcategory">기타 제2외국어</option>
				</select> <select required="required">
						<option value="olympiad" id="lowcategory">올림피아드</option>
						<option value="it" id="lowcategory">ITQ</option>
						<option value="economy" id="lowcategory">매경TEST</option>
						<option value="history" id="lowcategory">한국사능력검정시험</option>
						<option value="korean" id="lowcategory">KBS한국어능력시험</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="target">수업 대상</label></td>
				<td><select name="초/중/고" required="required">
						<option value="초등학생" id="target">초등학생</option>
						<option value="중학생" id="target">중학생</option>
						<option value="고등학생" id="target">고등학생</option>
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
				<td><input type="radio" name="isweekend" id="isweekend" value="weekdays">주중<input
					type="radio" name="isweekend" id="isweekend" value="weekend">주말<input
					type="radio" name="isweekend" id="isweekend" value="anytime">주중+주말</td>
			</tr>
			<tr>
				<td><label for="ismorning">수업 시간</label></td>
				<td><input type="radio" name="ismorning" id="ismorning" value="morning">오전<input
					type="radio" name="ismorning" id="ismorning" value="afternoon">오후<input
					type="radio" name="ismorning" id="ismorning" value="discuss">협의</td>
			</tr>
			<tr>
				<td><label for="isonline">수업 방식</label></td>
				<td><input type="radio" name="isonline" value="online"
					id="isonline">온라인 <input type="radio" name="isonline"
					value="offline" id="isonline">오프라인</td>
			</tr>
			<tr>
				<td><label for="isgroup">참여인원</label></td>
				<td><input type="radio" name="isgroup" value="onetoone"
					id="isgroup">1:1수업 <input type="radio" name="isgroup"
					value="group" id="isgroup">그룹수업<input name="mingroup"
					type="number" id="mingroup" min="2" /> <input name="maxgroup"
					type="number" id="maxgroup" min="2" /></td>
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
		</table>
	</form>
</div>

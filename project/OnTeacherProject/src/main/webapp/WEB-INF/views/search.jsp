<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/search.css" />
<script src="${path }/resources/js/search.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div id="s_wrap">
	<div id="s_title_wrap">
		<div id="s_title">수업검색</div>
	</div>
	<form action="searchCourse" method="post" id="courseSearchForm">
		<table id="s_table">
			<tr>
				<td class="thead"><label for="highcategory">수업 분류</label></td>
				<td class="tbody"><select required="required" id="highcategory"
					name="highcategory">
						<option value="">상위 분류 선택</option>
						<c:forEach var="high" items="${highCategory }">
							<option value="${high.id }">${high.name }</option>
						</c:forEach>
				</select> <select required="required" id="lowcategory" name="lowcategory">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">하위 분류 선택</option>
				</select>
			</tr>
			<tr>
				<td class="thead"><label for="target">수업 대상</label></td>
				<td class="tbody"><select required="required" id="target"
					name="target">
						<option value="">선택해주세요</option>
						<option value="초등">초등학생</option>
						<option value="중등">중학생</option>
						<option value="고등">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td class="thead"><label for="isonline">수업 방식</label></td>
				<td class="tbody"><input type="radio" name="isonline" value='1'
					id="online" class="isonline">온라인 <input type="radio"
					name="isonline" value='0' id="offline" class="isonline">오프라인</td>
			</tr>
			<tr>
				<td class="thead"></td>
				<td class="tbody">
					<button type="submit" id="submit_btn">검색하기</button>
				</td>
			</tr>
		</table>
	</form>
	<div>
		<p>&nbsp;</p>
	</div>

</div>
</body>

<div id="outter">
	<div style="float: right;">
		<table>
			<tr>
				<td><select id="browsers" name="browsers" multiple size="1"
					required="required">
						<option value="최신순">최신순</option>
						<option value="마감순">마감순</option>
						<option value="인기순">인기순</option>
				</select></td>
			</tr>
		</table>
	</div>


</div>
</body>


<body>
	<div>
		<p>&nbsp;</p>
	</div>

	<c:forEach var="course" items="${courses}">
		<table class="center2" border="1">
			<tr>
				<td>${course.title}</td>
			</tr>
			<tr>
				<td>${course.location}</td>
			</tr>
			<tr>
				<td>${course.studyDay}${course.studyTime}</td>
			</tr>
			<tr>
				<td>${course.startDate}~${course.endDate}</td>
			</tr>
		</table>
	</c:forEach>
</html>
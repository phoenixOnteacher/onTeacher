<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_list.js"></script>

<!-- 이 jsp를 include 하기위해 Controller에 추가해야할 코드 -->
<!-- 
Course course = courseService.queryCourseById(courseId);
int highCategoryId = course.getHighCategoryId();
int lowCategoryId = course.getLowCategoryId();
int teacherId = course.getTeacherId();
HighCategory highCategory = courseService.queryHighCategoryById(highCategoryId);
LowCategory lowCategory = courseService.queryLowCategoryById(lowCategoryId);
Teacher teacher = courseService.queryTeacherById(teacherId);
modelAndView.addObject("course", course);
modelAndView.addObject("highCategory", highCategory);
modelAndView.addObject("lowCategory", lowCategory);
modelAndView.addObject("teacher", teacher); 
-->

<div class="row mt-3">
	<div class="tab-content col-9">
		<h1>${course.title}</h1>
		<div>
			<c:set var="isOnline" value="${course.isOnline}" />
			<c:set var="isOneday" value="${course.isOneday}" />
			<c:set var="isGroup" value="${course.isGroup}" />
			<table class="table table-bordered">
				<tr>
					<td>수업 분류</td>

					<td>${highCategory.name} > ${lowCategory.name}</td>

					<td>${highCategory.name}>${lowCategory.name}</td>

					<td>수업 대상</td>
					<td>${course.target}</td>
				</tr>
				<tr>
					<td>수업 방식(지역)</td>
					<c:if test="${isOnline eq '0'.charAt(0)}">
						<td>오프라인 (${course.location})</td>
					</c:if>
					<c:if test="${isOnline eq '1'.charAt(0)}">
						<td>온라인</td>
					</c:if>
					<td>수업 일정</td>
					<td>${course.startDate} ~ ${course.endDate}</td>
					<td>${course.startDate}~${course.endDate}</td>
				</tr>
				<tr>
					<td>수업 형태</td>
					<td><c:choose>
							<c:when test="${isOneday eq '0'.charAt(0)}">다회차 수업</c:when>
							<c:when test="${course.isOneday eq '1'.charAt(0)}">1일 특강</c:when>
						</c:choose></td>
					<td>수업 요일</td>
					<td>${course.studyDay}</td>
				</tr>
				<tr>
					<td>참여 인원</td>
					<td><c:choose>
							<c:when test="${isGroup eq '0'.charAt(0)}">1:1 수업</c:when>
							<c:when test="${isGroup eq '1'.charAt(0)}">그룹 수업 (${course.minStudent}~${course.maxStudent}인)</c:when>
						</c:choose></td>
					<td>수업 시간</td>
					<td>${course.studyTime}</td>
				</tr>
			</table><br>
		</div>
		
			<script src="/js/matching.js">
			function appli(){
				alert("해당 수업을 신청하였습니다.");
			}</script>
				
		<table>
			<tr><td>
				<INPUT type ='button' value='수강신청' onclick='appli()'>
			</td></tr>
		</table>

		<div>
			<table class="table table-borderless">
				<tr>
					<td>선생님</td>
					<td><a href="#">${teacher.name }</a></td> <!-- TODO:선생님 상세페이지 연결 -->
				</tr>
				<tr>
					<td>수업 소개</td>
					<td>${course.curriculum}</td>
				</tr>
				<tr>
					<td>참고 자료</td>
					<td>
						<span><a href="/course/filedownload?filename=${course.curriculumFile}">${course.curriculumFile}</a></span><br />
					</td>
				</tr>

			</table>
		</div>
	</div>
</div>

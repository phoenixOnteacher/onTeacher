<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" href="${path}/resources/css/courseDetail.css" />
<link rel="stylesheet" href="${path}/resources/css/sweet_alert.css" />
<spring:eval expression="@environment.getProperty('ipaddress')" var="ipaddress" />
<spring:eval expression="@environment.getProperty('server.port')" var="port" />
<%-- <script src="${path }/resources/js/course_apply.js"></script> --%>

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

<div id="cd_wrap">
	<div id="cd_title_wrap">
		<div id="cd_title">${course.title}</div>
	</div>
	<div id="table1_wrap">
		<c:set var="isOnline" value="${course.isOnline}" />
		<c:set var="isOneday" value="${course.isOneday}" />
		<c:set var="isGroup" value="${course.isGroup}" />
		<table class="table table-bordered">
			<tr>
				<td class="left">수업 분류</td>
				<td class="right">${highCategory.name}&nbsp;>&nbsp;${lowCategory.name}</td>
				<td class="left">수업 대상</td>
				<c:choose>
					<c:when test="${course.target eq '중등'}">
						<td class="right">중학생</td>
					</c:when>
					<c:otherwise>
						<td class="right">${course.target}학생</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td class="left">수업 방식 (지역)</td>
				<c:if test="${isOnline eq '0'.charAt(0)}">
					<td>오프라인 (${course.location})</td>
				</c:if>
				<c:if test="${isOnline eq '1'.charAt(0)}">
					<td>온라인</td>
				</c:if>
				<td class="left">수업 일정</td>
				<td class="right"><fmt:parseDate value="${course.startDate}"
						var="coursestart" pattern="yy-MM-dd" /> <fmt:parseDate
						value="${course.endDate}" var="courseend" pattern="yy-MM-dd" /> <fmt:formatDate
						value="${coursestart}" pattern="yy.MM.dd" /> ~ <fmt:formatDate
						value="${courseend }" pattern="yy.MM.dd" /></td>
			</tr>
			<tr>
				<td class="left">수업 형태</td>
				<td class="right"><c:choose>
						<c:when test="${isOneday eq '0'.charAt(0)}">다회차 수업</c:when>
						<c:when test="${course.isOneday eq '1'.charAt(0)}">1일 특강</c:when>
					</c:choose></td>
				<td class="left">수업 요일</td>
				<td class="right">${course.studyDay}</td>
			</tr>
			<tr>
				<td class="left">참여 인원</td>
				<td class="right"><c:choose>
						<c:when test="${isGroup eq '0'.charAt(0)}">1:1 수업</c:when>
						<c:when test="${isGroup eq '1'.charAt(0)}">그룹 수업 (${course.minStudent}~${course.maxStudent}명)</c:when>
					</c:choose></td>
				<td class="left">수업 시간</td>
				<td class="right">${course.studyTime}</td>
			</tr>
		</table>
	</div>
	<div id="table2_wrap">
		<table class="table table-borderless" id="table2">
			<tr>
				<td class="left2">선생님</td>
				<td class="right2"><a href="/teacher/teacherDetail?teacherId=${teacher.id }" id="th_name">${teacher.name }</a></td>
			</tr>
			<tr>
				<td class="left2">수업 소개</td>
				<td class="right2" id="curriculum">${course.curriculum}</td>
			</tr>
			<tr>
				<td class="left2">참고 자료</td>
				<td class="right2"><span><a
						href="/course/filedownload?filename=${course.curriculumFile}">${course.curriculumFile}</a></span><br />
				</td>
			</tr>
		</table>
	</div>
	<div id="apply_btn_wrap">
		<button type='button' class="btn btn-primary courseApplyBtn" value="${course.id }">수강신청</button>
	</div>
</div>
<!-- 내부 js 사용 : ajax처리가 원활하지 않아 내부에서 선언하니 제대로 됨-->
<script> 
$(function(){
	// 수업 신청
    $('.courseApplyBtn').click(function () {
    	var courseTitle = $("#cd_title").text();
		swal({
		  title: "수업 신청",
		  text: courseTitle + " 수업을 신청하시겠습니까?",
		  buttons: true,
		})
		.then((res) => {
		  if (res) {
		    courseApply($(this).val());
		  }
		});
    })
	
	function courseApply(course_id) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/student/courseApply?courseId="+course_id,
			success: function(data,status) {
				swal({
				  text: data,
				  icon: "success",
				})
			    .then(() => {
					location.reload();
				});
			},
			error: function(request, status, error){
				swal({
				  text: request.responseText,
				  icon: "error",
				})
			    .then(() => {
					location.reload();
				});
			}
		})
	}
});
</script>
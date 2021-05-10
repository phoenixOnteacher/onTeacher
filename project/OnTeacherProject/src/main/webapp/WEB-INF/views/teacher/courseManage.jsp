<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/course_manage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<spring:eval expression="@environment.getProperty('ipaddress')" var="ipaddress" />
<spring:eval expression="@environment.getProperty('server.port')" var="port" />
<div id="cm-wrap" class="m-5 px-5">
	<div id="" class="container">
		<h1 class="course-manage-menu">수업 관리</h1>
		<div class="row mt-3">
			<nav class="nav flex-column col-3 course-manage-menu" id="classNav">
				<a class="nav-link active in text-reset" aria-current="page" href="#studying" data-toggle="tab" data-load="true">진행 중인 수업</a>
				<a class="nav-link text-reset" href="#match" data-toggle="tab" data-load="false">대기 중인 수업</a>
				<a class="nav-link text-reset" href="#end" data-toggle="tab" data-load="false">종료된 수업</a>
			</nav>
			<div class="tab-content col-9">
				<div class="tab-pane fade show active" id="studying">
				  	<c:forEach var="course" items="${studyingList }">
						<div class="card m-2">
						  <h5 class="card-header py-3">
							  	<a href="/teacher/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						    	<button type="button" class="btn btn-warning btn-sm float-end mx-2 cancelCourseBtn" value="${course.id }">수업 취소</button>
							    <button type="button" class="btn btn-warning btn-sm float-end mx-2" data-bs-toggle="modal" data-bs-target="#exampleModal">수업 연장</button>
						  </h5>
						  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">수업 연장</h5>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							        <form action="/teacher/${course.id}/extend" method="post">
										<div class="mb-3">
										  <label for="courseEndDate" class="form-label">종료일</label>
										  <input class="form-control" type="date" id="courseEndDate" name="extendDate" value="${course.endDate }" min="${course.endDate }">
										</div>
										<button type="submit" class="btn btn-primary float-end m-2">연장</button>
								        <button type="button" class="btn btn-secondary float-end m-2" data-bs-dismiss="modal">취소</button>
									</form>
							      </div>
							    </div>
							  </div>
						  </div>
						  <div class="card-body">
						    <h5 class="card-title mb-3">${course.target } ${course.lowCategoryName } 수업</h5>
						    <p>
						    	<i class="fas fa-user-friends"></i> 
							    <c:choose>
						    		<c:when test="${empty course.studentList }">
						    			<span>없음</span>
						    		</c:when>
						    		<c:otherwise>
									    <c:forEach var="student" items="${course.studentList }" varStatus="status">
									    	<c:if test="${status.count > 1}">
									    		<span>, </span>
									    	</c:if>
									    	<span class="card-text">${student.name } 학생</span>
									    </c:forEach>
						    		</c:otherwise>
						    	</c:choose> 
						    </p>
						    <p class="card-text">
						    	<c:choose>
									<c:when test="${empty course.location}">
										<i class="fas fa-desktop"></i> 온라인
									</c:when>
									<c:otherwise>
										<i class="fas fa-map-marker-alt"></i> ${course.location }
									</c:otherwise>
								</c:choose>
						    </p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text">
						    	<i class="far fa-calendar"></i> 
							    <fmt:parseDate value="${course.startDate}" var="courseStartDate" pattern="yyyy-MM-dd" />
							    <fmt:parseDate value="${course.endDate}" var="courseEndDate" pattern="yyyy-MM-dd" />
								<fmt:formatDate value="${courseStartDate}" pattern="yy.MM.dd" /> ~ <fmt:formatDate value="${courseEndDate }" pattern="yy.MM.dd" />
						    </p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				<div class="tab-pane fade" id="match">
					<c:forEach var="course" items="${matchingList }">
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/teacher/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<small class="btn btn-secondary float-end btn-sm disabled">매칭 대기</small>
					  	  </h5>
						  <div class="card-body">
						    <h5 class="card-title mb-3">${course.target } ${course.lowCategoryName } 수업</h5>
						    <p>
						    	<i class="fas fa-user-friends"></i> 
							    <c:choose>
						    		<c:when test="${empty course.studentList }">
						    			<span>없음</span>
						    		</c:when>
						    		<c:otherwise>
									    <c:forEach var="student" items="${course.studentList }" varStatus="status">
										    	<c:if test="${status.count > 1}">
										    		<span>, </span>
										    	</c:if>
									    	<span class="card-text">${student.name } 학생</span>
									    </c:forEach>
						    		</c:otherwise>
						    	</c:choose> 
						    </p>
						    <p class="card-text">
						    	<c:choose>
									<c:when test="${empty course.location}">
										<i class="fas fa-desktop"></i> 온라인
									</c:when>
									<c:otherwise>
										<i class="fas fa-map-marker-alt"></i> ${course.location }
									</c:otherwise>
								</c:choose>
						    </p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
							<p class="card-text">
						    	<i class="far fa-calendar"></i> 
							    <fmt:parseDate value="${course.startDate}" var="courseStartDate" pattern="yyyy-MM-dd" />
							    <fmt:parseDate value="${course.endDate}" var="courseEndDate" pattern="yyyy-MM-dd" />
								<fmt:formatDate value="${courseStartDate}" pattern="yy.MM.dd" /> ~ <fmt:formatDate value="${courseEndDate }" pattern="yy.MM.dd" />
						    </p>
						  </div>
						</div>
				  	</c:forEach>
				  	<c:forEach var="course" items="${matchedList }">
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/teacher/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle" id="title-${course.id }">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<small class="btn btn-secondary float-end btn-sm mx-2 disabled">매칭 완료</small>
						  	<button class="startCourseBtn btn btn-warning float-end btn-sm mx-2" value="${course.id }">수업 시작</button>
						  </h5>
						  <div class="card-body">
						    <h5 class="card-title mb-3">${course.target } ${course.lowCategoryName } 수업</h5>
						    <p>
						    	<i class="fas fa-user-friends"></i> 
							    <c:choose>
						    		<c:when test="${empty course.studentList }">
						    			<span>없음</span>
						    		</c:when>
						    		<c:otherwise>
									    <c:forEach var="student" items="${course.studentList }" varStatus="status">
										    	<c:if test="${status.count > 1}">
										    		<span>, </span>
										    	</c:if>
									    	<span class="card-text">${student.name } 학생</span>
									    </c:forEach>
						    		</c:otherwise>
						    	</c:choose> 
						    </p>
						    <p class="card-text">
						    	<c:choose>
									<c:when test="${empty course.location}">
										<i class="fas fa-desktop"></i> 온라인
									</c:when>
									<c:otherwise>
										<i class="fas fa-map-marker-alt"></i> ${course.location }
									</c:otherwise>
								</c:choose>
						    </p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text">
						    	<i class="far fa-calendar"></i> 
							    <fmt:parseDate value="${course.startDate}" var="courseStartDate" pattern="yyyy-MM-dd" />
							    <fmt:parseDate value="${course.endDate}" var="courseEndDate" pattern="yyyy-MM-dd" />
								<fmt:formatDate value="${courseStartDate}" pattern="yy.MM.dd" /> ~ <fmt:formatDate value="${courseEndDate }" pattern="yy.MM.dd" />
						    </p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				<div class="tab-pane fade" id="end">
					<c:forEach var="course" items="${endList }">
						<div class="card m-2">
						  <h5 class="card-header p-3"><a href="/teacher/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a></h5>
					      <div class="card-body">
						    <h5 class="card-title mb-3">${course.target } ${course.lowCategoryName } 수업</h5>
						    <p>
						    	<i class="fas fa-user-friends"></i>
						    	<c:choose>
						    		<c:when test="${empty course.studentList }">
						    			<span>없음</span>
						    		</c:when>
						    		<c:otherwise>
									    <c:forEach var="student" items="${course.studentList }" varStatus="status">
										    	<c:if test="${status.count > 1}">
										    		<span>, </span>
										    	</c:if>
									    	<span class="card-text">${student.name } 학생</span>
									    </c:forEach>
						    		</c:otherwise>
						    	</c:choose> 
						    </p>
						    <p class="card-text">
						    	<c:choose>
									<c:when test="${empty course.location}">
										<i class="fas fa-desktop"></i> 온라인
									</c:when>
									<c:otherwise>
										<i class="fas fa-map-marker-alt"></i> ${course.location }
									</c:otherwise>
								</c:choose>
						    </p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text">
						    	<i class="far fa-calendar"></i> 
							    <fmt:parseDate value="${course.startDate}" var="courseStartDate" pattern="yyyy-MM-dd" />
							    <fmt:parseDate value="${course.endDate}" var="courseEndDate" pattern="yyyy-MM-dd" />
								<fmt:formatDate value="${courseStartDate}" pattern="yy.MM.dd" /> ~ <fmt:formatDate value="${courseEndDate }" pattern="yy.MM.dd" />
						    </p>
					  	  </div>
						</div>
				  	</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	// 수업 취소
    $('.cancelCourseBtn').click(function () {
	  	swal({
		  title: "수업 취소",
		  text: "취소하면 복구할 수 없습니다.\n수업을 취소하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		    cancelCourse($(this).val());
		  }
		});
    })
	
	function cancelCourse(course_id) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id,
			success: function() {
				swal({
				  title: "수업 취소 완료",
				  text: "수업이 정상적으로 취소 되었습니다.",
				  icon: "success",
				})
			    .then((value) => {
					location.reload();
				});
			},
			error: function() {
				swal({
				  title: "에러 발생",
				  text: "페이지를 다시 로드합니다.",
				  icon: "error",
				})
			    .then((value) => {
					location.reload();
				});
			}
		})
	}
	
	// 수업 시작
    $('.startCourseBtn').click(function () {
    	var course_title = $("a[id='title-"+course_id+"']").text();
    	var course_id = $(this).val();
    	swal({
		  title: course_title,
		  text: "수업을 시작하시겠습니까?",
		  buttons: true,
		})
		.then((willStart) => {
		  if (willStart) {
		    startCourse(course_id, course_title);
		  }
		});
    })
	
	function startCourse(course_id, course_title) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/start",
			success: function() {
				swal({
				  title: "수업 시작",
				  text: course_title + " 수업을 시작합니다. 화이팅! :)",
				  icon: "success",
				})
			    .then((value) => {
					location.reload();
				});
			},
			error: function() {
				swal({
				  title: "에러 발생",
				  text: "페이지를 다시 로드합니다.",
				  icon: "error",
				})
			    .then((value) => {
					location.reload();
				});
			}
		})
	}
});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_list.js"></script>
<script src="${path }/resources/js/course_cancel.js"></script>
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<h1>수업 관리</h1>
		<nav class="nav">
		  <a class="nav-link active in text-reset h4" aria-current="page" href="#studying" data-toggle="tab" data-load="true">진행 중인 수업</a>
		  <a class="nav-link h4" href="#match" data-toggle="tab" data-load="false">대기 중인 수업</a>
		  <a class="nav-link h4" href="#end" data-toggle="tab" data-load="false">종료된 수업</a>
		</nav>
		
		<div class="tab-content">
			<div class="tab-pane fade in active" id="studying">
			  	<c:forEach var="course" items="${studyingList }">
					<div class="card m-2">
					  <h5 class="card-header py-3">
					  	<a href="./course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title } <i class="fas fa-chevron-right"></i></a>
				    	<button type="button" class="btn btn-danger btn-sm float-end mx-2 cancelCourseBtn" value="${course.id }">수업 취소</button>
					    <button type="button" class="btn btn-primary btn-sm float-end mx-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
						  수업 연장
						</button>
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
					  </h5>
					  <div class="card-body">
					    <h5 class="card-title">${course.status }</h5>
					    <c:forEach var="student" items="${course.studentList }">
					    	<p class="card-text">${student.name } 학생</p>
					    </c:forEach>
					    <p>${course.startDate } ~ ${course.endDate }</p>
					  </div>
					</div>
			  	</c:forEach>
			</div>
			<div class="tab-pane fade" id="match">
				<c:forEach var="course" items="${matchingList }">
					<div class="card m-2">
					  <h5 class="card-header p-3">
					  	<a href="./course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title } <i class="fas fa-chevron-right"></i></a>
					  	<small class="btn btn-primary float-end btn-sm">매칭 대기</small>
				  	  </h5>
					  <div class="card-body">
					    <h5 class="card-title">매칭 대기</h5>
					    <c:forEach var="student" items="${course.studentList }">
					    	<p class="card-text">${student.name } 학생</p>
					    </c:forEach>
					    <p>${course.startDate } ~ ${course.endDate }</p>
					    <a href="#" class="btn btn-primary">Go somewhere</a>
					  </div>
					</div>
			  	</c:forEach>
			  	<c:forEach var="course" items="${matchedList }">
					<div class="card m-2">
					  <h5 class="card-header p-3">
					  	<a href="./course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title } <i class="fas fa-chevron-right"></i></a>
					  	<small class="btn btn-secondary float-end btn-sm">매칭 완료</small>
					  </h5>
					  <div class="card-body">
					    <h5 class="card-title">${course.status }</h5>
					    <c:forEach var="student" items="${course.studentList }">
					    	<p class="card-text">${student.name } 학생</p>
					    </c:forEach>
					    <p>${course.startDate } ~ ${course.endDate }</p>
					    <a href="#" class="btn btn-primary">Go somewhere</a>
					  </div>
					</div>
			  	</c:forEach>
			</div>
			<div class="tab-pane fade" id="end">
				<c:forEach var="course" items="${endList }">
					<div class="card m-2">
					  <h5 class="card-header p-3"><a href="./course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title } <i class="fas fa-chevron-right"></i></a></h5>
					  <div class="card-body">
					    <h5 class="card-title">${course.status }</h5>
					    <c:forEach var="student" items="${course.studentList }">
					    	<p class="card-text">${student.name } 학생</p>
					    </c:forEach>
					    <p>${course.startDate } ~ ${course.endDate }</p>
					  </div>
					</div>
			  	</c:forEach>
			</div>
		</div>
	</div>
</div>
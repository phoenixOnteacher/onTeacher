<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path }/resources/js/course_list.js"></script>
<script src="${path }/resources/js/course_cancel.js"></script>
<div id="" class="m-5 px-5">
	<div id="">
		<h1>Course Management</h1>
		<nav class="nav">
		  <a class="nav-link active in text-reset" aria-current="page" href="#studying" data-toggle="tab" data-load="true">진행 중인 수업</a>
		  <a class="nav-link" href="#match" data-toggle="tab" data-load="false">대기 중인 수업</a>
		  <a class="nav-link" href="#end" data-toggle="tab" data-load="false">종료된 수업</a>
		</nav>
		
		<div class="tab-content">
			<div class="tab-pane fade in active" id="studying">
			  	<c:forEach var="course" items="${studyingList }">
					<div class="card m-2">
					  <h5 class="card-header py-3"><b>${course.title }</b></h5>
					  <div class="card-body">
					    <h5 class="card-title">${course.status }</h5>
					    <c:forEach var="student" items="${course.studentList }">
					    	<p class="card-text">${student.name } 학생</p>
					    </c:forEach>
					    <p>${course.startDate } ~ ${course.endDate }</p>
					    <div>
					    	<button id="cancelCourseBtn" type="button" class="btn btn-danger float-end m-2" value="${course.id }">수업 취소</button>
						    <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#exampleModal">
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
					    </div>
					  </div>
					</div>
			  	</c:forEach>
			</div>
			<div class="tab-pane fade" id="match">
				<c:forEach var="course" items="${matchingList }">
					<div class="card m-2">
					  <h5 class="card-header p-3"><b class="align-middle">${course.title }</b><small class="btn btn-primary float-end btn-sm">매칭 대기</small></h5>
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
					  <h5 class="card-header p-3"><b class="align-middle">${course.title }</b><small class="btn btn-secondary float-end btn-sm">매칭 완료</small></h5>
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
					  <h5 class="card-header p-3"><b>${course.title }</b></h5>
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
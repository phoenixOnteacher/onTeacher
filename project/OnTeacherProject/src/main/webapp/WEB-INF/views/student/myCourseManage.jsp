<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<script src="${path }/resources/js/course_manage.js"></script>
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<h1>내 수업</h1>
		<div class="row mt-3">
		
			<nav class="nav flex-column col-3" id="classNav">
				<a class="nav-link active in text-reset" aria-current="page" href="#studying" data-toggle="tab" data-load="true">진행 중인 수업</a>
				<a class="nav-link text-reset" href="#match" data-toggle="tab" data-load="false">대기 중인 수업</a>
				<a class="nav-link text-reset" href="#end" data-toggle="tab" data-load="false">종료된 수업</a>
			</nav>
			
			<div class="tab-content col-9">
				<div class="tab-pane fade show active" id="studying"> <!-- 진행 중인 수업 리스트 조회 -->
				  	<c:forEach var="course" items="${studyingList }">
						<div class="card m-2">
					
						  <h5 class="card-header py-3">
							  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						    	<%-- <button type="button" class="btn btn-danger btn-sm float-end mx-2 cancelCourseBtn" value="${course.id }">수업 취소</button>
							    <button type="button" class="btn btn-primary btn-sm float-end mx-2" data-bs-toggle="modal" data-bs-target="#exampleModal">수업 연장</button> --%>
						  </h5>
						  
						  <!-- 모달 - 나랑 상관없는것 -->
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
						    <h5 class="card-title">${course.target } Low-category</h5>
<%-- 						    <p>
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
						    </p> --%>
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				
				
				<div class="tab-pane fade" id="match"> <!-- 대기중인 수업 목록 조회 -->
					
					<c:forEach var="course" items="${matchingList }"> <!-- 매칭대기 조회 -->
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<button type="button" class="btn btn-danger btn-sm float-end mx-2 cancelCourseBtn" value="${course.id }">수업 취소</button>
						  	<small class="btn btn-primary float-end btn-sm">매칭 대기</small>
					  	  </h5>
						  <div class="card-body">
						    <h5 class="card-title">${course.target } Low-category</h5>
						    <%-- <p>
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
						    </p> --%>
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				  	
				  	<c:forEach var="course" items="${matchedList }">  <!-- 매칭완료 조회 -->
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle" id="title-${course.id }">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<small class="btn btn-secondary float-end btn-sm mx-2">매칭 완료</small>
						  	<%-- <button class="startCourseBtn btn btn-success float-end btn-sm mx-2" value="${course.id }">수업 시작</button> --%>
						  </h5>
						  <div class="card-body">
						    <h5 class="card-title">${course.target } Low-category</h5>
						    <%-- <p>
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
						    </p> --%>
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				
				
				<div class="tab-pane fade" id="end"> <!-- 종료된 수업 목록 조회 -->
					<c:forEach var="course" items="${endList }">
						<div class="card m-2">
						  <h5 class="card-header p-3"><a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a></h5>
					      <button class="startCourseBtn btn btn-success float-end btn-sm mx-2" value="${course.id }"><a href="">후기 작성</a></button>
					      <div class="card-body">
						    <h5 class="card-title">${course.target } Low-category</h5>
						    <%-- <p>
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
						    </p> --%>
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
					  	  </div>
						</div>
				  	</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/course_manage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<div id="cm-wrap" class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary">
			<a href="/student/course-manage" class="h5 text-decoration-none text-reset">내 수업</a>
			<i class="fas fa-chevron-right h5 mx-2"></i>
		</div>
		
		<h2 id="course-${course.id }" class="course-title">${course.title }</h2>
		<div class="row mt-3">
			<nav class="nav flex-column col-2" id="manageNav">
			  <a class="nav-link active in text-reset" aria-current="page" href="#detail" data-toggle="tab" data-load="true">수업 상세</a>
			  <c:choose>
			  	<c:when test="${course.status=='studying'}">
				  <a class="nav-link text-reset" href="#myTeacher" data-toggle="tab" data-load="false">내 선생님</a>
				  <a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
			  	</c:when>
			  	<c:when test="${course.status=='end'}">
	    		  <a class="nav-link text-reset" href="#myTeacher" data-toggle="tab" data-load="false">내 선생님</a>
			  	  <a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
			  	</c:when>
			  	<c:when test="${course.status=='matched'}">
	    		  <a class="nav-link text-reset" href="#myTeacher" data-toggle="tab" data-load="false">내 선생님</a>
			  	</c:when>
			  </c:choose>
			</nav>
			
			
			<div class="tab-content col-10">
			  
			  <div class="tab-pane fade" id="myTeacher"> <!-- 내 선생님 클릭시 -->
		    	<div class="card mb-3 mx-auto" style="max-width: 40vw;">
				  <div class="row g-0">
				    <div class="col-md-4">
   				      <img src="/student/fileview/thprofile/${teacher.profileImg}" style="width:200px; height:200px;" />
				    </div>
				    <div class="col-md-8">
				      <div class="card-body">
				        <h5 class="card-title h4 mb-3">${teacher.name } 선생님</h5>
				        <p class="card-text"><i class="fas fa-phone"></i> ${teacher.phoneNumber }</p>
				        <p class="card-text"><i class="fas fa-envelope"></i> ${teacher.email }</p>
				        <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${teacher.address }</p>
				        <p class="card-text"><i class="fas fa-user"></i> ${teacher.gender }자</p>
				        <p class="card-text"><i class="fas fa-birthday-cake"></i> ${teacher.birthday }</p>
				      </div>
				    </div>
				  </div>
				</div>
		  	  </div>
			  
			  
			  <div class="tab-pane fade show active" id="detail"> <!-- 수업 상세페이지 -->
				<jsp:include page="../common/courseDetail.jsp"></jsp:include>
			  </div>
			  
			  <div class="tab-pane fade" id="homework">
				<jsp:include page="../common/homeworkList.jsp"></jsp:include>
			  </div>
			</div>
		</div>
	</div>
</div>

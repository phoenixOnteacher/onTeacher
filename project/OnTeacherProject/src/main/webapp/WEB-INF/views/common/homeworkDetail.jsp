<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="${path}/resources/css/course_manage.css" />
<link rel="stylesheet" href="${path}/resources/css/homework.css" />
<script src="${path }/resources/js/course_tab.js"></script>

<div id="cm-wrap" class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary">
		<c:if test="${fn:substring(sessionScope.id,0,1)=='2'}">
			<a href="/student/course-manage" class="text-secondary h5 text-decoration-none">내수업</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
			<a href="/student/course-manage/${course.id }" class="text-secondary h5 text-decoration-none">${course.title }</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
		</c:if>
		<c:if test="${fn:substring(sessionScope.id,0,1)=='3'}">
			<a href="/teacher/course-manage" class="text-secondary h5 text-decoration-none">수업 관리</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
			<a href="/teacher/course-manage/${course.id }" class="text-secondary h5 text-decoration-none">${course.title }</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
		</c:if>
		</div>
		<h2 id="course-${course.id }" class="course-title mt-3">${homework.title }</h2>
		<table class="table table-bordered">
		  <tbody>
		    <!-- <tr>
		      <th colspan="1" class="px-3">Title</th>
		      <td colspan="3" class="text-center px-3">${homework.title }</td>
		    </tr>-->
		    <tr>
		      <th scope="row" colspan="1" class="text-center px-3">File</th>
		      <td colspan="1" class="text-center px-3">
			      <c:choose>
	  				<c:when test="${empty homework.filename }">
	  					<p class="text-secondary mb-0">파일 없음</p>
  					</c:when>
  					<c:otherwise>
		      			<i class="fas fa-paperclip"></i> <a href="/hwfiledownload?filename=${homework.filename }" class="text-decoration-none">${fn:substring(homework.filename,25,-1) }</a>
  					</c:otherwise>
 				  </c:choose>
		      </td>
		      <th scope="row" colspan="1" class="text-center px-3">Deadline</th>
		      <td colspan="1" class="text-center px-3">${fn:substring(homework.deadline,0,10) }</td>
		    </tr>
		    <tr>
		      <th colspan="1" class="text-center px-5 align-middle">Content</th>
		      <td colspan="3" class="px-3" style="white-space: pre-wrap;">${homework.content }</td>
		    </tr>
		  </tbody>
		</table>
		<c:if test="${fn:substring(user_id,0,1)=='2'}"> 
			<br>
			<jsp:include page="../student/homeworkAnswer.jsp"></jsp:include>
		</c:if>
		<c:if test="${fn:substring(user_id,0,1)=='3'}">
	  		<div class="card" style="width: 100%;">
			  <div class="card-header text-center h5">과제 확인</div>
			  <div class="list-group list-group-flush d-flex bd-highlight mb-3">
	  	  	    <div class="accordion accordion-flush" id="accordionFlushExample">
	  	  		  <c:forEach var="student" items="${students }" varStatus="status">
				    <div class="accordion-item">
				      <h2 class="accordion-header" id="flush-headingOne">
				        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne-${student.id }" aria-expanded="false" aria-controls="flush-collapseOne">
				          <h5 class="mb-0">${student.name } 학생</h5><c:if test="${empty student.homeworkAnswer }"><span class="badge bg-danger ms-2 collapse-badge">미제출</span></c:if>
				        </button>
				      </h2>
				      <c:if test="${!empty student.homeworkAnswer }">
					      <div id="flush-collapseOne-${student.id }" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
					        <div class="accordion-body">
				        	  <p class="h6" style="white-space: pre-wrap;">${student.homeworkAnswer.content }</p>
				      	  	  <c:choose>
				      	  	  	  <c:when test="${!empty student.homeworkAnswer.filename }">
									<i class="fas fa-paperclip"></i> <a href="/hafiledownload?filename=${student.homeworkAnswer.filename }">${student.homeworkAnswer.filename }</a>
					      	      </c:when>
					      	      <c:otherwise>
					      	      	파일 없음
					      	      </c:otherwise>
				      	  	  </c:choose>
						      <small class="text-secondary float-end">${fn:substring(student.homeworkAnswer.createdAt, 0, 10) } 제출</small>
				      	    </div>
					      </div>
			      	  </c:if>
				    </div>
			  	  </c:forEach>
			    </div>
  			  </div>
			</div>
		</c:if>
	</div>
</div>
<script>
	$('.collapse-badge').parent().attr('disabled',true);
</script>